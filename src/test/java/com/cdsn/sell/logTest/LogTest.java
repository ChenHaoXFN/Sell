package com.cdsn.sell.logTest;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 日志测试类
 *
 * @author <a href="mailto:chenhao2010h@163.com">chenhao</a>
 * @version 1.0.0
 * @since 1.0.0
 *
 * Created at 2019-01-04 14:41
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class LogTest {

//  private final Logger log = LoggerFactory.getLogger(LogTest.class);

  @Test
  public void test1() {
    String name = "csdn";
    String password = "123456";
    log.info("info....");
    log.debug("debug....");
    log.warn("warn....");
    log.error("error....");
    log.info("name {} password {} ", name, password);
  }
}
