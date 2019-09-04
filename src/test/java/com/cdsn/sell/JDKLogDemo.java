package com.cdsn.sell;

import java.util.logging.Logger;

/**
 * 测试jdklog
 *
 * @author chenhao
 * @version 1.0.0
 * @since 1.0.0
 *
 * Created at 2019-09-02 10:44
 */
public class JDKLogDemo {

  public static void main(String[] args) {
    Logger logger = Logger.getLogger(JDKLogDemo.class.getName());
    logger.info("info");
    logger.warning("warning");
  }

}
