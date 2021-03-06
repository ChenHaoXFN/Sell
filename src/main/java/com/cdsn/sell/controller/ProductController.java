package com.cdsn.sell.controller;

import com.cdsn.sell.entity.ProductCategory;
import com.cdsn.sell.entity.ProductInfo;
import com.cdsn.sell.repository.ProductInfoRepository;
import com.cdsn.sell.service.ProductCategoryService;
import com.cdsn.sell.service.ProductInfoService;
import com.cdsn.sell.utils.ResultVOUtil;
import com.cdsn.sell.vo.ProductCategoryVO;
import com.cdsn.sell.vo.ProductInfoVO;
import com.cdsn.sell.vo.ResultVO;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 商品control
 *
 * @author <a href="mailto:chenhao2010h@163.com">chenhao</a>
 * @version 1.0.0
 * @since 1.0.0
 *
 * Created at 2019-01-07 16:24
 */
@RestController
@RequestMapping("/buyer/product")
public class ProductController {

  @Autowired
  private ProductInfoService infoService;

  @Autowired
  private ProductCategoryService categoryService;

  @Autowired
  private ProductInfoRepository infoRepository;

  @PostMapping("findProduct")
  public ResultVO getProduct(@Value("productId") String productId) {
    // 1.根据id查询.
    ProductInfo productInfo = infoService.findOne(productId);
    if (productInfo == null) {
      return ResultVOUtil.fail("空");
    }
    return ResultVOUtil.success(productInfo);
  }

  @PostMapping("product")
  public ResultVO findProduct() {
    return ResultVOUtil.success(infoRepository.findAll());
  }

  @GetMapping("getResult")
  public String getResult(String value) {
    return value;
  }


  @GetMapping("all")
  public List<ProductInfo> getAll() {
    List<ProductInfo> upAll = infoService.findUpAll();
    return upAll;
  }

  @GetMapping("one")
  public ProductInfo getOne() {
    return infoService.findOne("123458");
  }

  @GetMapping("formatOne")
  public ResultVO getOneRes() {

    return ResultVOUtil.success(infoService.findOne("123458"));
  }


  @GetMapping("getList/{source}")
  public List<String> getParams(
      @MatrixVariable(pathVar = "source") List<String> filterParams) {
    System.out.println(filterParams);

    return null;
  }


  @PostMapping("saveProduct")
  public String saveProduct(String date, String time,@RequestBody ProductInfo productInfo ) {
    ProductInfo save = infoRepository.save(productInfo);
    System.out.println(date);
    System.out.println(time);

    return save == null ? "保存失败" : "保存成功";
  }


  @PostMapping("")


  /**
   * 查看所有类目及其对应商品.
   */
  @GetMapping("list")
  public ResultVO getAllProduct() {

    // 1. 查询所有上架中的商品.
    List<ProductInfo> upAll = infoService.findUpAll();
    // 2. 收集这些商品的条目.
    List<Integer> types = upAll.stream().map(ProductInfo::getCategoryType)
        .collect(Collectors.toList());
    // 3. 根据这些商品的类目进行类目搜索.
    List<ProductCategory> categoryList = categoryService.findByCategoryTypeIn(types);

    // 4. 拼接对应类目
    List<ProductCategoryVO> categoryVOList = new ArrayList<>();

    for (ProductCategory category : categoryList) {
      ProductCategoryVO categoryVO = new ProductCategoryVO();
      categoryVO.setCategoryName(category.getCategoryName());
      categoryVO.setCategoryType(category.getCategoryType());
      List<ProductInfoVO> productInfoVO = new ArrayList<>();
      for (ProductInfo productInfo : upAll) {
        if (productInfo.getCategoryType().equals(categoryVO.getCategoryType())) {
          ProductInfoVO infoVO = new ProductInfoVO();
          BeanUtils.copyProperties(productInfo, infoVO);
          productInfoVO.add(infoVO);
        }
      }
      categoryVO.setProductInfoVO(productInfoVO);
      categoryVOList.add(categoryVO);
    }
    return ResultVOUtil.success(categoryVOList);
  }

}
