package com.cdsn.sell.utils;

import com.cdsn.sell.vo.ResultVO;

/**
 * 返回
 *
 * @author <a href="mailto:chenhao2010h@163.com">chenhao</a>
 * @version 1.0.0
 * @since 1.0.0
 *
 * Created at 2019-01-08 10:06
 */
public class ResultVOUtil {

  private static final String SUCCESS_MSG = "成功";
  private static final Integer SUCCESS_CODE = 0;
  private static final String FAIL_MSG = "失败";
  private static final Integer FAIL_CODE = 9;


  public static ResultVO<Object> success(Object object) {
    return getResultVO(object, SUCCESS_MSG, SUCCESS_CODE);
  }

  public static ResultVO<Object> success() {
    return success(null);
  }

  public static ResultVO<Object> fail(Object object) {
    return getResultVO(object, FAIL_MSG, FAIL_CODE);
  }

  private static ResultVO<Object> getResultVO(Object object, String msg, Integer code) {
    ResultVO<Object> resultVO = new ResultVO<>();
    resultVO.setMsg(msg);
    resultVO.setCode(code);
    resultVO.setData(object);
    return resultVO;
  }


}
