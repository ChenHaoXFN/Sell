package com.cdsn.sell.repository;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;

import static org.junit.Assert.*;

import com.cdsn.sell.entity.ProductInfo;
import java.util.List;
import java.util.Optional;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 测试商品
 *
 * @author <a href="mailto:chenhao2010h@163.com">chenhao</a>
 * @version 1.0.0
 * @since 1.0.0
 *
 * Created at 2019-01-07 14:43
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoRepositoryTest {

  @Autowired
  private ProductInfoRepository repository;

  @Test
  public void saveTest() {
    ProductInfo productInfo = new ProductInfo();
    productInfo.setProductId("122");
    productInfo.setProductName("土豆");
    productInfo.setProductPrice(new BigDecimal("9.9"));
    productInfo.setProductStock(50);
    productInfo.setProductDescription("本地特大土豆");
    productInfo.setProductIcon("http://xxxxxxxx.jpg");
    productInfo.setCategoryType(1);
    productInfo.setProductStatus(1);
    ProductInfo save = repository.save(productInfo);
  }

  @Test
  public void updateTest() {
    ProductInfo productInfo = null;
    Optional<ProductInfo> optional = repository.findById("123456");
    if (optional.isPresent()) {
      productInfo = optional.get();
    }
    productInfo.setProductName("大连产荷兰豆");
    ProductInfo save = repository.save(productInfo);
  }

  @Test
  public void queryTest() {
    List<ProductInfo> productInfoList = repository.findAll();
    System.out.println(productInfoList);
    List<ProductInfo> byCategoryTypeIn = repository.findByCategoryTypeIn(Arrays.asList(1, 2));
    Assert.assertNotNull(byCategoryTypeIn);
  }

  @Test
  public void delTest() {
    repository.deleteById("123458");
  }

}