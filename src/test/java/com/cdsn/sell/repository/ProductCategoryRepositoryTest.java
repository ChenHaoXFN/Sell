package com.cdsn.sell.repository;

import com.cdsn.sell.entity.ProductCategory;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 类目repository测试
 *
 * @author <a href="mailto:chenhao2010h@163.com">chenhao</a>
 * @version 1.0.0
 * @since 1.0.0
 *
 * Created at 2019-01-07 09:53
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryRepositoryTest {

  @Autowired
  private ProductCategoryRepository repository;

  @Test
  @Transactional
  public void saveTest() {
    ProductCategory productCategory = new ProductCategory();
    productCategory.setCategoryName("测试类目3");
    productCategory.setCategoryType(22);
    ProductCategory save = repository.save(productCategory);
    System.out.println(save);
  }

  @Test
  public void queryTest() {
    ProductCategory productCategory = null;
    Optional<ProductCategory> optional = repository.findById(1);
    if (optional.isPresent()) {
      productCategory = optional.get();
    }
    System.out.println(productCategory);
  }

  @Test
  @Transactional
  public void updateTest() {
    ProductCategory productCategory = null;
    Optional<ProductCategory> optional = repository.findById(3);
    if (optional.isPresent()) {
      productCategory = optional.get();
    }
    productCategory.setCategoryName("修改类目2");
    repository.save(productCategory);
  }

  @Test
  @Transactional
  public void delTest() {
    repository.deleteById(1);
  }

  @Test
  public void queryTest2() {
    List<ProductCategory> allBycaAndCategoryType = repository.findByCategoryTypeIn(Arrays.asList(11,22));
    Assert.assertNotEquals(0,allBycaAndCategoryType.size());
  }
}