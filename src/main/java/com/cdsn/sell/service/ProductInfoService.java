package com.cdsn.sell.service;

import com.cdsn.sell.dto.CarDTO;
import com.cdsn.sell.entity.ProductInfo;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * 商品service
 *
 * @author <a href="mailto:chenhao2010h@163.com">chenhao</a>
 * @version 1.0.0
 * @since 1.0.0
 *
 * Created at 2019-01-07 15:02
 */
public interface ProductInfoService {

  ProductInfo findOne(String id);

  /**
   * 查看所有商家商品
   */
  List<ProductInfo> findUpAll();

  Page<ProductInfo> findAll(Pageable pageable);

  ProductInfo save(ProductInfo productInfo);

  /**
   * 加库存.
   */
  void increaseStock(List<CarDTO> cars);

  /**
   * 减库存.
   */
  void decreaseStock(List<CarDTO> cars);

}
