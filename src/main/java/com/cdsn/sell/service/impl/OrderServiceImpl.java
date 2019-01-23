package com.cdsn.sell.service.impl;

import com.cdsn.sell.converter.OrderMaster2OrderDTOConverter;
import com.cdsn.sell.dto.CarDTO;
import com.cdsn.sell.dto.OrderDTO;
import com.cdsn.sell.entity.OrderDetail;
import com.cdsn.sell.entity.OrderMaster;
import com.cdsn.sell.entity.ProductInfo;
import com.cdsn.sell.enums.ExceptionEnum;
import com.cdsn.sell.enums.OrderStatusEnum;
import com.cdsn.sell.enums.PayStatusEnum;
import com.cdsn.sell.exception.SellException;
import com.cdsn.sell.repository.OrderDetailRepository;
import com.cdsn.sell.repository.OrderMasterRepository;
import com.cdsn.sell.service.OrderService;
import com.cdsn.sell.service.ProductInfoService;
import com.cdsn.sell.utils.UUIDUtil;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 订单service.
 *
 * @author <a href="mailto:chenhao2010h@163.com">chenhao</a>
 * @version 1.0.0
 * @since 1.0.0
 *
 * Created at 2019-01-09 14:26
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

  @Autowired
  private OrderDetailRepository detailRepository;

  @Autowired
  private OrderMasterRepository masterRepository;

  @Autowired
  private ProductInfoService productInfoService;

  /**
   * 创建订单.
   */
  @Override
  @Transactional
  public OrderDTO create(OrderDTO orderDTO) {
    String orderId = UUIDUtil.getId();
    BigDecimal orderAmount = new BigDecimal("0");
    // 1.查询商品（数量，价格）
    for (OrderDetail orderDetail : orderDTO.getList()) {
      ProductInfo productInfo = productInfoService.findOne(orderDetail.getProductId());
      if (productInfo == null) {
        throw new SellException(ExceptionEnum.PRODUCT_NOT_EXIST);
      }
      // 2.计算总价
      orderAmount = productInfo.getProductPrice()
          .multiply(new BigDecimal(orderDetail.getProductQuantity()))
          .add(orderAmount);
      // 3.写入数据库（ orderDetail ）
      BeanUtils.copyProperties(productInfo, orderDetail);
      orderDetail.setOrderId(orderId);
      orderDetail.setDetailId(UUIDUtil.getId());
      detailRepository.save(orderDetail);
    }
    // 3.写入数据库（ orderMaster ）
    OrderMaster orderMaster = new OrderMaster();
    orderDTO.setOrderId(orderId);
    BeanUtils.copyProperties(orderDTO, orderMaster, "list", "orderStatus", "payStatus");
    orderMaster.setOrderAmount(orderAmount);
    masterRepository.save(orderMaster);

    // 4.扣库存
    List<CarDTO> carts = orderDTO.getList().stream().map(
        orderDetail -> new CarDTO(orderDetail.getProductId(), orderDetail.getProductQuantity()))
        .collect(Collectors.toList());
    productInfoService.decreaseStock(carts);
    return orderDTO;
  }


  /**
   * 根据订单号，找出订单.
   */
  @Override
  public OrderDTO findOne(String orderId) {
    OrderMaster orderMaster = null;
    Optional<OrderMaster> optional = masterRepository.findById(orderId);
    if (optional.isPresent()) {
      orderMaster = optional.get();
    }
    if (orderMaster == null) {
      // 订单主表不存在
      throw new SellException(ExceptionEnum.ORDER_NOT_EXIST);
    }
    List<OrderDetail> details = detailRepository.findByOrderId(orderId);
    if (details.isEmpty()) {
      // 订单详情不存在
      throw new SellException(ExceptionEnum.ORDER_DETAIL_ERROR);
    }
    OrderDTO orderDTO = OrderMaster2OrderDTOConverter.converter(orderMaster);
    orderDTO.setList(details);
    return orderDTO;
  }

  /**
   * 根据 微信id 查询对应 订单
   */
  @Override
  public Page<OrderDTO> findList(String openId, Pageable pageable) {
    Page<OrderMaster> orderMasters = masterRepository.findByBuyerOpenid(openId, pageable);
    List<OrderMaster> mastersContent = orderMasters.getContent();
    if (mastersContent.isEmpty()) {
      throw new SellException(ExceptionEnum.ORDER_NOT_EXIST);
    }
    List<OrderDTO> converter = OrderMaster2OrderDTOConverter.converter(mastersContent);
    return new PageImpl<>(converter, pageable, orderMasters.getTotalElements());
  }

  /**
   * 取消订单
   */
  @Override
  @Transactional
  public OrderDTO cancel(OrderDTO orderDTO) {
    OrderMaster orderMaster = new OrderMaster();
    // 判断订单状态
    if (!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
      log.error("【取消订单】订单状态不正确，BuyerOpenID = {} , OrderStatus = {} ", orderDTO.getBuyerOpenid(),
          orderDTO.getOrderStatus());
      throw new SellException(ExceptionEnum.ORDER_STATUS_ERROR);
    }

    // 取消订单
    orderDTO.setOrderStatus(OrderStatusEnum.CANCEL.getCode());
    BeanUtils.copyProperties(orderDTO, orderMaster, "list");
    OrderMaster save = masterRepository.save(orderMaster);
    if (!save.getOrderStatus().equals(OrderStatusEnum.CANCEL.getCode())) {
      log.error("【取消订单】订单状态更新失败，orderDTO = {}", orderDTO);
      throw new SellException(ExceptionEnum.ORDER_STATUS_UPDATE_ERROR);
    }

    // 返回库存
    List<OrderDetail> orderDetails = orderDTO.getList();
    if (orderDetails.isEmpty()) {
      log.error("【取消订单】订单列表为空，orderDTO = {}", orderDTO);
      throw new SellException(ExceptionEnum.ORDER_DETAIL_ERROR);
    }
    List<CarDTO> cars = orderDetails.stream()
        .map(p -> new CarDTO(p.getProductId(), p.getProductQuantity()))
        .collect(Collectors.toList());
    productInfoService.increaseStock(cars);

    // 退款
    if (orderDTO.getPayStatus().equals(PayStatusEnum.SUCCESS)) {
      // TODO
    }
    return orderDTO;
  }

  /**
   * 完结订单
   */
  @Override
  @Transactional
  public OrderDTO finish(OrderDTO orderDTO) {
    // 判断订单状态
    if (!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
      log.error("【完结订单】订单状态不正确，BuyerOpenID = {} , OrderStatus = {} ", orderDTO.getBuyerOpenid(),
          orderDTO.getOrderStatus());
      throw new SellException(ExceptionEnum.ORDER_STATUS_ERROR);
    }
    // 修改订单状态
    OrderMaster orderMaster = new OrderMaster();
    orderDTO.setOrderStatus(OrderStatusEnum.FINISHED.getCode());
    BeanUtils.copyProperties(orderDTO, orderMaster, "list");
    OrderMaster save = masterRepository.save(orderMaster);
    if (save == null) {
      log.error("【完结订单】订单状态更新失败，orderDTO = {}", orderDTO);
      throw new SellException(ExceptionEnum.ORDER_STATUS_UPDATE_ERROR);
    }
    return orderDTO;
  }

  /**
   * 支付订单.
   */
  @Override
  @Transactional
  public OrderDTO paid(OrderDTO orderDTO) {
    // 判断订单状态
    if (!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
      log.error("【支付订单】订单状态不正确，BuyerOpenID = {} , OrderStatus = {} ", orderDTO.getBuyerOpenid(),
          orderDTO.getOrderStatus());
      throw new SellException(ExceptionEnum.ORDER_STATUS_ERROR);
    }

    // 判断支付状态
    if (!orderDTO.getPayStatus().equals(PayStatusEnum.WAIT.getCode())) {
      log.error("【支付订单】支付状态不正确,BuyerOpenID = {} , PayStatus = {} ", orderDTO.getBuyerOpenid(),
          orderDTO.getPayStatus());
      throw new SellException(ExceptionEnum.PAY_STATUS_ERROR);
    }

    // 修改支付状态
    OrderMaster orderMaster = new OrderMaster();
    orderDTO.setPayStatus(PayStatusEnum.SUCCESS.getCode());
    BeanUtils.copyProperties(orderDTO, orderMaster, "list");
    OrderMaster save = masterRepository.save(orderMaster);
    if (save == null) {
      log.error("【支付订单】支付状态更新失败，orderDTO = {}", orderDTO);
      throw new SellException(ExceptionEnum.PAY_STATUS_UPDATE_ERROR);
    }
    return orderDTO;
  }

}
