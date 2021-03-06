package com.cdsn.sell.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 * 类目表对应实体类
 *
 * @author <a href="mailto:chenhao2010h@163.com">chenhao</a>
 * @version 1.0.0
 * @since 1.0.0
 *
 * Created at 2019-01-07 09:47
 */
@EqualsAndHashCode(callSuper = true)
@Entity
@DynamicUpdate // 更新的时候，动态生成sql语句（只更新更新的数据）
@DynamicInsert // 新增的时候，动态生成sql语句
@Data
public class ProductCategory extends BaseEntity{

  /**
   * 类目id.
   * 主键生成策略.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer categoryId;
  /**
   * 类目名字.
   */
  private String categoryName;

  /**
   * 类目标号.
   */
  private Integer categoryType;



}
