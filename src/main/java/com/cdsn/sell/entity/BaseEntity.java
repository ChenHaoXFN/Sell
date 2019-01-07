package com.cdsn.sell.entity;

import java.util.Date;
import lombok.Data;

/**
 * 所有entity都 继承本entity 提取出时间
 *
 * @author <a href="mailto:chenhao2010h@163.com">chenhao</a>
 * @version 1.0.0
 * @since 1.0.0
 *
 * Created at 2019-01-07 11:10
 */
@Data
public class BaseEntity {

  /**
   * 创建时间.
   */
  private Date createTime;

  /**
   * 更新时间.
   */
  private Date updateTime;

}
