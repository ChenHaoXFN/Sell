package com.cdsn.sell.dto;

import com.cdsn.sell.entity.OrderDetail;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Id;
import lombok.Data;

/**
 * 订单数据传输对象.
 *
 * @author <a href="mailto:chenhao2010h@163.com">chenhao</a>
 * @version 1.0.0
 * @since 1.0.0
 *
 * Created at 2019-01-09 14:16
 */
@Data
public class OrderDTO {

  /**
   * 订单id.
   */
  @Id
  private String orderId;

  /**
   * 买家名字.
   */
  private String buyerName;

  /**
   * 买家电话.
   */
  private String buyerPhone;

  /**
   * 买家收货地址.
   */
  private String buyerAddress;

  /**
   * 买家微信openid.
   */
  private String buyerOpenid;

  /**
   * 订单总金额.
   */
  private BigDecimal orderAmount;

  /**
   * 订单状态.
   */
  private Integer orderStatus;

  /**
   * 支付状态.
   */
  private Integer payStatus;

  private List<OrderDetail> list;

}
