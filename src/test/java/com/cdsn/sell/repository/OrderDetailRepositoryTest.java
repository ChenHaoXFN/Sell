package com.cdsn.sell.repository;

import com.cdsn.sell.entity.OrderDetail;
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
 * 订单详情Repository
 *
 * @author <a href="mailto:chenhao2010h@163.com">chenhao</a>
 * @version 1.0.0
 * @since 1.0.0
 *
 * Created at 2019-01-09 10:36
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class OrderDetailRepositoryTest {

  @Autowired
  private OrderDetailRepository repository;

  @Test
  public void save() {
    OrderDetail orderDetail = new OrderDetail();
    orderDetail.setDetailId(UUIDUtil.getId());
    orderDetail.setOrderId("2154711547001182930");
    orderDetail.setProductId("123456");
    orderDetail.setProductName("大连产荷兰豆");
    orderDetail.setProductPrice(new BigDecimal("9.9"));
    orderDetail.setProductQuantity(1);
    orderDetail.setProductIcon("http://xxxxxxxx.jpg");
    repository.save(orderDetail);
  }

  @Test
  public void query() {
    Optional<OrderDetail> optional = repository.findById("9095371547003736492");
    OrderDetail orderDetail = null;
    if (optional.isPresent()) {
      orderDetail = optional.get();
    }
    Assert.assertNotNull(orderDetail);
  }

  @Test
  public void update() {
    Optional<OrderDetail> optional = repository.findById("9095371547003736492");
    OrderDetail orderDetail = null;
    if (optional.isPresent()) {
      orderDetail = optional.get();
    }
    orderDetail.setProductName("试试看修改么有");
    OrderDetail save = repository.save(orderDetail);
    Assert.assertNotNull(save);
  }

  @Test
  public void del(){
    Optional<OrderDetail> optional = repository.findById("9095371547003736492");
    OrderDetail orderDetail = null;
    if (optional.isPresent()) {
      orderDetail = optional.get();
      repository.delete(orderDetail);
    }
  }

}