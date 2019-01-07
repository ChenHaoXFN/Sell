package com.cdsn.sell.repository;

import com.cdsn.sell.entity.ProductCategory;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 类目Repository
 *
 * @author <a href="mailto:chenhao2010h@163.com">chenhao</a>
 * @version 1.0.0
 * @since 1.0.0
 *
 * Created at 2019-01-07 09:52
 */
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Integer> {

  /**
   * 根据类目标号查询出类目list.
   */
  List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypes);
}
