package com.cdsn.sell.service;

import com.cdsn.sell.entity.ProductCategory;
import java.util.List;

/**
 * 类目service
 *
 * @author <a href="mailto:chenhao2010h@163.com">chenhao</a>
 * @version 1.0.0
 * @since 1.0.0
 *
 * Created at 2019-01-07 12:52
 */
public interface ProductCategoryService {

  ProductCategory findOne(Integer id);

  List<ProductCategory> findAll();

  List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypes);

  ProductCategory save(ProductCategory productCategory);

}
