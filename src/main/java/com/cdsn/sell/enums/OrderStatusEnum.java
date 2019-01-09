package com.cdsn.sell.enums;

import lombok.Getter;

/**
 * 订单状态枚举类.
 *
 * @author <a href="mailto:chenhao2010h@163.com">chenhao</a>
 * @version 1.0.0
 * @since 1.0.0
 *
 * Created at 2019-01-09 09:48
 */
@Getter
public enum OrderStatusEnum {

  NEW(0, "新订单"),
  FINISHED(1, "已完结"),
  CANCEL(2, "已取消"),
  ;

  private Integer code;
  private String msg;

  OrderStatusEnum(Integer code, String msg) {
    this.code = code;
    this.msg = msg;
  }
}
