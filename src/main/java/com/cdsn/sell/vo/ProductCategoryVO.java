package com.cdsn.sell.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;

/**
 * 商品类目对应前端展示VO
 *
 * @author <a href="mailto:chenhao2010h@163.com">chenhao</a>
 * @version 1.0.0
 * @since 1.0.0
 *
 * Created at 2019-01-08 09:20
 */
@Data
public class ProductCategoryVO {

  /**
   * 类目名称.
   */
  @JsonProperty("name")
  private String categoryName;

  /**
   * 类目标号.
   */
  @JsonProperty("type")
  private Integer categoryType;

  /**
   * 商品.
   */
  @JsonProperty("foods")
  protected List<ProductInfoVO> productInfoVO;
}
