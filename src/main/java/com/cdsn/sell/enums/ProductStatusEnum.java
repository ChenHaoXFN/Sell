package com.cdsn.sell.enums;

import lombok.Getter;

/**
 * 商品状态枚举类
 *
 * @author <a href="mailto:chenhao2010h@163.com">chenhao</a>
 * @version 1.0.0
 * @since 1.0.0
 *
 * Created at 2019-01-07 15:10
 */
@Getter
public enum ProductStatusEnum {
  UP(1, "上架"),
  DOWN(2, "下架");

  private Integer code;
  private String msg;

  ProductStatusEnum(Integer code, String msg) {
    this.code = code;
    this.msg = msg;
  }
}
