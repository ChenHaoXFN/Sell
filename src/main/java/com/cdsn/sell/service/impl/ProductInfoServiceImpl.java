package com.cdsn.sell.service.impl;

import com.cdsn.sell.entity.ProductInfo;
import com.cdsn.sell.enums.ProductStatusEnum;
import com.cdsn.sell.repository.ProductInfoRepository;
import com.cdsn.sell.service.ProductInfoService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * 商品service
 *
 * @author <a href="mailto:chenhao2010h@163.com">chenhao</a>
 * @version 1.0.0
 * @since 1.0.0
 *
 * Created at 2019-01-07 15:06
 */
@Service
public class ProductInfoServiceImpl implements ProductInfoService {

  @Autowired
  private ProductInfoRepository productInfoRepository;

  @Override
  public ProductInfo findOne(String id) {
    Optional<ProductInfo> optional = productInfoRepository.findById(id);
    if (optional.isPresent()) {
      return optional.get();
    }
    return null;
  }

  @Override
  public List<ProductInfo> findUpAll() {
    return productInfoRepository.findByProductStatus(ProductStatusEnum.UP.getCode());
  }

  @Override
  public Page<ProductInfo> findAll(Pageable pageable) {
    return productInfoRepository.findAll(pageable);
  }

  @Override
  public ProductInfo save(ProductInfo productInfo) {
    return productInfoRepository.save(productInfo);
  }
}
