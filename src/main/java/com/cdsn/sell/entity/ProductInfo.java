package com.cdsn.sell.entity;

import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 * 商品表对应entity
 *
 * @author <a href="mailto:chenhao2010h@163.com">chenhao</a>
 * @version 1.0.0
 * @since 1.0.0
 *
 * Created at 2019-01-07 14:34
 */
@Data
@Entity
@DynamicInsert
@DynamicUpdate
public class ProductInfo extends BaseEntity {

  @Id
  private String productId;

  /**
   * 商品名称
   */
  private String productName;

  /**
   * 商品金额
   */
  private BigDecimal productPrice;

  /**
   * 商品库存
   */
  private Integer productStock;

  /**
   * 商品描述
   */
  private String productDescription;

  /**
   * 商品小图
   */
  private String productIcon;

  /**
   * 商品状态
   */
  private Integer productStatus;

  /**
   * 类目编号
   */
  private Integer categoryType;

}
