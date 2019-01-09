package com.cdsn.sell.entity;

import com.cdsn.sell.enums.OrderStatusEnum;
import com.cdsn.sell.enums.PayStatusEnum;
import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 * 订单 对应实体类.
 *
 * @author <a href="mailto:chenhao2010h@163.com">chenhao</a>
 * @version 1.0.0
 * @since 1.0.0
 *
 * Created at 2019-01-09 09:35
 */
@Entity
@Data
@DynamicUpdate
@DynamicInsert
public class OrderMaster extends BaseEntity {

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
  private Integer orderStatus = OrderStatusEnum.NEW.getCode();

  /**
   * 支付状态.
   */
  private Integer payStatus = PayStatusEnum.WAIT.getCode();


}
