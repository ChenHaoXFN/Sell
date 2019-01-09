package com.cdsn.sell.entity;

import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 * 订单详情对应实体类.
 *
 * @author <a href="mailto:chenhao2010h@163.com">chenhao</a>
 * @version 1.0.0
 * @since 1.0.0
 *
 * Created at 2019-01-09 09:36
 */
@Entity
@Data
@DynamicInsert
@DynamicUpdate
public class OrderDetail extends BaseEntity {

  /**
   * 订单详情id.
   */
  @Id
  private String detailId;

  /**
   * 订单id.   订单对详情 一对多.
   */
  private String orderId;

  /**
   * 商品ID.
   */
  private String productId;

  /**
   * 商品名称.
   */
  private String productName;

  /**
   * 商品价格.
   */
  private BigDecimal productPrice;

  /**
   * 商品数量.
   */
  private Integer productQuantity;

  /**
   * 商品小图.
   */
  private String productIcon;


}
