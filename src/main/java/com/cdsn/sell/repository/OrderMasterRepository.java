package com.cdsn.sell.repository;

import com.cdsn.sell.entity.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * 订单主表Repository
 *
 * @author <a href="mailto:chenhao2010h@163.com">chenhao</a>
 * @version 1.0.0
 * @since 1.0.0
 *
 * Created at 2019-01-09 09:45
 */
public interface OrderMasterRepository extends JpaRepository<OrderMaster, String> {

  Page<OrderMaster> findByBuyerOpenid(String openId, Pageable pageable);

  /**
   * 根据订单编号修改订单状态.
   */
  @Query("update OrderMaster set orderStatus = ?2 where orderId = ?1 ")
  @Modifying
  Integer editOrderStatus(String orderId, Integer status);
}
