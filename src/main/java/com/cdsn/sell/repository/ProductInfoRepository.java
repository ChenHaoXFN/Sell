package com.cdsn.sell.repository;

import com.cdsn.sell.entity.ProductInfo;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 商品repository
 *
 * @author <a href="mailto:chenhao2010h@163.com">chenhao</a>
 * @version 1.0.0
 * @since 1.0.0
 *
 * Created at 2019-01-07 14:39
 */
public interface ProductInfoRepository extends JpaRepository<ProductInfo, String> {

  List<ProductInfo> findByCategoryTypeIn(List<Integer> categoryTypes);

  List<ProductInfo> findByProductStatus(Integer productStatus);

}
