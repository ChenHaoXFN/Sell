package com.cdsn.sell.enums;

import lombok.Getter;

/**
 * 支付状态枚举.
 *
 * @author <a href="mailto:chenhao2010h@163.com">chenhao</a>
 * @version 1.0.0
 * @since 1.0.0
 *
 * Created at 2019-01-09 09:52
 */

@Getter
public enum PayStatusEnum {
  WAIT(0, "等待支付"),
  SUCCESS(1, "支付成功"),
  ;

  private Integer code;
  private String msg;

  PayStatusEnum(Integer code, String msg) {
    this.code = code;
    this.msg = msg;
  }

}
