package com.cdsn.sell.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import lombok.Data;

/**
 * 商品前台展示用VO
 *
 * @author <a href="mailto:chenhao2010h@163.com">chenhao</a>
 * @version 1.0.0
 * @since 1.0.0
 *
 * Created at 2019-01-08 09:24
 */
@Data
public class ProductInfoVO {

  /**
   * 商品id.
   */
  @JsonProperty("id")
  private String productId;

  /**
   * 商品名称.
   */
  @JsonProperty("name")
  private String productName;

  /**
   * 商品价格.
   */
  @JsonProperty("price")
  private BigDecimal productPrice;

  /**
   * 商品描述.
   */
  @JsonProperty("description")
  private String productDescription;

  /**
   * 商品小图.
   */
  @JsonProperty("icon")
  private String productIcon;

}
