package com.cdsn.sell.repository;

import com.cdsn.sell.entity.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

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

  /**
   * 根据买家微信openId查询订单.
   */
//  Page<OrderMaster> findeByBuyerOpenId(String openId, Pageable pageable);
}
