package com.cdsn.sell.repository;

import com.cdsn.sell.entity.OrderMaster;
import com.cdsn.sell.enums.OrderStatusEnum;
import com.cdsn.sell.enums.PayStatusEnum;
import com.cdsn.sell.utils.UUIDUtil;
import java.math.BigDecimal;
import java.util.Optional;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 测试订单主表Repository.
 *
 * @author <a href="mailto:chenhao2010h@163.com">chenhao</a>
 * @version 1.0.0
 * @since 1.0.0
 *
 * Created at 2019-01-09 10:07
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterRepositoryTest {

  @Autowired
  private OrderMasterRepository repository;


  @Test
  public void saveTest() {
    OrderMaster orderMaster = new OrderMaster();
    orderMaster.setOrderId(UUIDUtil.getId());
    orderMaster.setBuyerName("陈浩");
    orderMaster.setBuyerPhone("19089029839");
    orderMaster.setBuyerAddress("大连市开发区");
    orderMaster.setBuyerOpenid("123456");
    orderMaster.setOrderAmount(new BigDecimal("22.22"));
    orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
    orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
    repository.save(orderMaster);
  }

  @Test
  public void queryTest() {
    Optional<OrderMaster> optional = repository.findById("4784011547000822072");
    OrderMaster o = null;
    if (optional.isPresent()) {
      o = optional.get();
    }
    Assert.assertNotNull(o);
  }

  @Test
  public void update(){
    Optional<OrderMaster> optional = repository.findById("4784011547000822072");
    OrderMaster o = null;
    if (optional.isPresent()) {
      o = optional.get();
    }
    o.setBuyerName("dadadada");
    OrderMaster save = repository.save(o);
    Assert.assertNotNull(save);
  }

  @Test
  public void delTest(){
    repository.deleteById("4784011547000822072");

  }
}