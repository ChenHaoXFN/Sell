package com.cdsn.sell.service.impl;
import java.math.BigDecimal;
import java.util.Date;

import static org.junit.Assert.*;

import com.cdsn.sell.entity.ProductInfo;
import com.cdsn.sell.service.ProductInfoService;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 商品Service 单元测试
 *
 * @author <a href="mailto:chenhao2010h@163.com">chenhao</a>
 * @version 1.0.0
 * @since 1.0.0
 *
 * Created at 2019-01-07 15:26
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoServiceImplTest {

  @Autowired
  private ProductInfoService productInfoService;

  @Test
  public void findOne() {
    ProductInfo productInfo = productInfoService.findOne("123456");
    Assert.assertNotNull(productInfo);
  }

  @Test
  public void findUpAll() {
    List<ProductInfo> upAll = productInfoService.findUpAll();
    Assert.assertNotEquals(0, upAll.size());
  }

  @Test
  public void findAll() {
    PageRequest pageRequest = PageRequest.of(0, 2);
    Page<ProductInfo> productInfos = productInfoService.findAll(pageRequest);
    Assert.assertNotEquals(0, productInfos.getTotalElements());
  }

  @Test
  public void save() {
    ProductInfo productInfo = new ProductInfo();
    productInfo.setProductId("123444");
    productInfo.setProductName("西蓝花");
    productInfo.setProductPrice(new BigDecimal("12.2"));
    productInfo.setProductStock(11);
    productInfo.setProductDescription("多吃蔬菜，健康营养");
    productInfo.setProductIcon("http://xxxxx.jpg");
    productInfo.setProductStatus(1);
    productInfo.setCategoryType(2);
    productInfoService.save(productInfo);
  }
}