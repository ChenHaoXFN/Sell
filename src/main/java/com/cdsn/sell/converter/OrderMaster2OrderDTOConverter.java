package com.cdsn.sell.converter;

import com.cdsn.sell.dto.OrderDTO;
import com.cdsn.sell.entity.OrderMaster;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.BeanUtils;

/**
 * 转换器.
 *
 * @author <a href="mailto:chenhao2010h@163.com">chenhao</a>
 * @version 1.0.0
 * @since 1.0.0
 *
 * Created at 2019-01-11 09:51
 */
public class OrderMaster2OrderDTOConverter {

  public static OrderDTO converter(OrderMaster orderMaster) {
    OrderDTO dto = new OrderDTO();
    BeanUtils.copyProperties(orderMaster, dto, "list");
    return dto;
  }

  public static List<OrderDTO> converter(List<OrderMaster> list) {
    if (list.isEmpty()) {
      return Collections.emptyList();
    }
    return list.stream().map(OrderMaster2OrderDTOConverter::converter).collect(Collectors.toList());
  }
}
