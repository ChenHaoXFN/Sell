package com.cdsn.sell.utils;

import java.util.Random;
import java.util.UUID;

/**
 * 生成随机码Id
 *
 * @author <a href="mailto:chenhao2010h@163.com">chenhao</a>
 * @version 1.0.0
 * @since 1.0.0
 *
 * Created at 2019-01-09 10:12
 */
public class UUIDUtil {

  public static String getId() {
    Random random = new Random();
    int i = random.nextInt(900000) + 100000;
    return System.currentTimeMillis() + String.valueOf(i);
  }

}
