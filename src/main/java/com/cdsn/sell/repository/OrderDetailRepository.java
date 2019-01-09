package com.cdsn.sell.repository;

import com.cdsn.sell.entity.OrderDetail;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 商品详情Repository.
 *
 * @author <a href="mailto:chenhao2010h@163.com">chenhao</a>
 * @version 1.0.0
 * @since 1.0.0
 *
 * Created at 2019-01-09 09:44
 */
public interface OrderDetailRepository extends JpaRepository<OrderDetail, String> {

  /**
   * 根据订单id查出该订单所有订单商品详情.
   */
  List<OrderDetail> findByOrderId(String orderId);

}
