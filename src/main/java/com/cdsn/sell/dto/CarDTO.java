package com.cdsn.sell.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 购物车
 *
 * @author <a href="mailto:chenhao2010h@163.com">chenhao</a>
 * @version 1.0.0
 * @since 1.0.0
 *
 * Created at 2019-01-09 15:14
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarDTO {

  private String productId;

  private Integer productQuantity;

}
