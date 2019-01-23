package com.cdsn.sell.service.impl;

import com.cdsn.sell.dto.CarDTO;
import com.cdsn.sell.entity.ProductInfo;
import com.cdsn.sell.enums.ExceptionEnum;
import com.cdsn.sell.enums.ProductStatusEnum;
import com.cdsn.sell.exception.SellException;
import com.cdsn.sell.repository.ProductInfoRepository;
import com.cdsn.sell.service.ProductInfoService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
  @Transactional
  public ProductInfo save(ProductInfo productInfo) {
    return productInfoRepository.save(productInfo);
  }

  @Override
  public void increaseStock(List<CarDTO> cars) {
    ProductInfo productInfo = null;
    for (CarDTO car : cars) {
      productInfo = CarDTO2ProductInfo(productInfo, car);
      Integer stock = productInfo.getProductStock() + car.getProductQuantity();
      productInfo.setProductStock(stock);
      productInfoRepository.save(productInfo);
    }

  }

  /**
   * carDto 转 商品
   */
  private ProductInfo CarDTO2ProductInfo(ProductInfo productInfo, CarDTO car) {
    Optional<ProductInfo> option = productInfoRepository.findById(car.getProductId());
    if (option.isPresent()) {
      productInfo = option.get();
    }
    if (productInfo == null) {
      throw new SellException(ExceptionEnum.PRODUCT_NOT_EXIST);
    }
    return productInfo;
  }

  @Override
  public void decreaseStock(List<CarDTO> cars) {
    ProductInfo productInfo = null;
    for (CarDTO car : cars) {
      productInfo = CarDTO2ProductInfo(productInfo, car);
      Integer stock = productInfo.getProductStock() - car.getProductQuantity();
      if (stock < 0) {
        throw new SellException(ExceptionEnum.PRODUCT_STOCK_ERROR);
      }
      productInfo.setProductStock(stock);
      productInfoRepository.save(productInfo);
    }
  }
}
