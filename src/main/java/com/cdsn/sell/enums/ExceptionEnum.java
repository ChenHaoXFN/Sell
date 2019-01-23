package com.cdsn.sell.enums;

import lombok.Getter;

/**
 * 异常枚举.
 *
 * @author <a href="mailto:chenhao2010h@163.com">chenhao</a>
 * @version 1.0.0
 * @since 1.0.0
 *
 * Created at 2019-01-09 14:49
 */
@Getter
public enum ExceptionEnum {
  PRODUCT_NOT_EXIST(10, "商品不存在"),

  PRODUCT_STOCK_ERROR(11, "库存异常"),

  ORDER_NOT_EXIST(12, "订单不存在"),

  ORDER_DETAIL_ERROR(13, "订单列表不存在"),

  ORDER_STATUS_ERROR(14, "订单状态不正确"),

  ORDER_STATUS_UPDATE_ERROR(15, "订单状态更新失败"),

  PAY_STATUS_UPDATE_ERROR(17, "支付状态更新失败"),

  PAY_STATUS_ERROR(16, "支付状态异常"),

  ;

  private Integer code;
  private String msg;

  ExceptionEnum(Integer code, String msg) {
    this.code = code;
    this.msg = msg;
  }

}
