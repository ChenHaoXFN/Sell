package com.cdsn.sell.service.impl;

import com.cdsn.sell.dto.OrderDTO;
import com.cdsn.sell.entity.OrderDetail;
import com.cdsn.sell.service.OrderService;
import java.util.ArrayList;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 订单service测试.
 *
 * @author <a href="mailto:chenhao2010h@163.com">chenhao</a>
 * @version 1.0.0
 * @since 1.0.0
 *
 * Created at 2019-01-09 15:43
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class OrderServiceImplTest {

  @Autowired
  private OrderService orderService;

  @Test
  public void create() {
    OrderDTO orderDTO = new OrderDTO();
    orderDTO.setBuyerName("张三");
    orderDTO.setBuyerPhone("18868822111");
    orderDTO.setBuyerAddress("慕课网总部");
    orderDTO.setBuyerOpenid("ew3euwhd7sjw9diwkq");

    ArrayList<OrderDetail> orderDetails = new ArrayList<>();
    OrderDetail orderDetail = new OrderDetail();
    orderDetail.setProductId("123444");
    orderDetail.setProductQuantity(3);

    orderDetails.add(orderDetail);
    orderDTO.setList(orderDetails);

    OrderDTO orderDTO1 = orderService.create(orderDTO);
    log.info("result -> {}", orderDTO1);
  }

  @Test
  public void findOne() {
    OrderDTO one = orderService.findOne("1547020176110369368");

    Assert.assertNotNull(one);

    System.out.println(one);
  }

  @Test
  public void findList() {
    PageRequest page = PageRequest.of(0, 10);
    String openId = "ew3euwhd7sjw9diwkq";
    Page<OrderDTO> list = orderService.findList(openId, page);
    Assert.assertNotNull(list);
    System.out.println(list);
  }

  @Test
  public void cancel() {
    OrderDTO one = orderService.findOne("1547020176110369368");
    OrderDTO cancel = orderService.cancel(one);
    Assert.assertNotNull(cancel);

  }

  @Test
  public void finish() {
    OrderDTO one = orderService.findOne("1547020176110369368");
    OrderDTO cancel = orderService.finish(one);
    Assert.assertNotNull(cancel);
  }

  @Test
  public void paid() {
    OrderDTO one = orderService.findOne("1547020176110369368");
    OrderDTO cancel = orderService.paid(one);
    Assert.assertNotNull(cancel);
  }
}