package com.cdsn.sell.exception;

import com.cdsn.sell.enums.ExceptionEnum;
import lombok.Data;

/**
 * 异常捕获.
 *
 * @author <a href="mailto:chenhao2010h@163.com">chenhao</a>
 * @version 1.0.0
 * @since 1.0.0
 *
 * Created at 2019-01-09 14:48
 */
@Data
public class SellException extends RuntimeException {

  private Integer code;

  public SellException(ExceptionEnum exceptionEnum) {
    super(exceptionEnum.getMsg());
    this.code = exceptionEnum.getCode();
  }
}
