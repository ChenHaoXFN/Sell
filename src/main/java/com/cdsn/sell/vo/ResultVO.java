package com.cdsn.sell.vo;

import java.util.List;
import lombok.Data;

/**
 * 返回给前段最外层封装对象
 *
 * @author <a href="mailto:chenhao2010h@163.com">chenhao</a>
 * @version 1.0.0
 * @since 1.0.0
 *
 * Created at 2019-01-08 09:18
 */
@Data
public class ResultVO<T> {

  /**
   * 错误码.
   */
  private Integer code;

  /**
   * 错误信息.
   */
  private String msg;

  /**
   * 具体数据.
   */
  private T data;

}
