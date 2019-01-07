package com.cdsn.sell.service.impl;

import com.cdsn.sell.entity.ProductCategory;
import com.cdsn.sell.service.ProductCategoryService;
import java.util.Arrays;
import java.util.List;
import javax.transaction.Transactional;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 类目service测试类
 *
 * @author <a href="mailto:chenhao2010h@163.com">chenhao</a>
 * @version 1.0.0
 * @since 1.0.0
 *
 * Created at 2019-01-07 12:57
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryServiceImplTest {

  @Autowired
  private ProductCategoryService productCategoryService;

  @Test
  public void findOne() {
    ProductCategory one = productCategoryService.findOne(2);
    Assert.assertNotNull(one);
  }

  @Test
  public void findAll() {
    List<ProductCategory> all = productCategoryService.findAll();
    Assert.assertNotNull(all);
  }

  @Test
  public void findByCategoryTypeIn() {
    List<ProductCategory> byCategoryTypeIn = productCategoryService
        .findByCategoryTypeIn(Arrays.asList(11, 22, 33));
    Assert.assertNotNull(byCategoryTypeIn);
  }

  @Test
  public void save() {
    ProductCategory productCategory = new ProductCategory();
    productCategory.setCategoryName("测试service");
    productCategory.setCategoryType(90);
    ProductCategory save = productCategoryService.save(productCategory);
    Assert.assertNotNull(save);
  }
}