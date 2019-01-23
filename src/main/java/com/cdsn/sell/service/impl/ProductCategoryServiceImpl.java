package com.cdsn.sell.service.impl;

import com.cdsn.sell.entity.ProductCategory;
import com.cdsn.sell.repository.ProductCategoryRepository;
import com.cdsn.sell.service.ProductCategoryService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 类目service
 *
 * @author <a href="mailto:chenhao2010h@163.com">chenhao</a>
 * @version 1.0.0
 * @since 1.0.0
 *
 * Created at 2019-01-07 12:55
 */
@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {

  @Autowired
  private ProductCategoryRepository repository;

  @Override
  public ProductCategory findOne(Integer id) {
    Optional<ProductCategory> optional = repository.findById(id);
    if (optional.isPresent()) {
      return optional.get();
    }
    return null;
  }

  @Override
  public List<ProductCategory> findAll() {
    return repository.findAll();
  }

  @Override
  public List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypes) {
    return repository.findByCategoryTypeIn(categoryTypes);
  }

  @Override
  @Transactional
  public ProductCategory save(ProductCategory productCategory) {
    return repository.save(productCategory);
  }
}
