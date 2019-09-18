package com.product.server.controller;

import com.common.model.CartInfo;
import com.product.server.service.ProductCategoryService;
import com.product.server.service.ProductService;
import com.product.server.vo.ProductInfo;
import com.product.server.vo.ProductVO;
import com.product.server.vo.ResultInfo;
import com.respository.dataObject.ProductCategoryEntity;
import com.respository.dataObject.ProductInfoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author jason
 */
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductCategoryService productCategoryService;

    /**
     * 1. 查询所有在架的商品
     * 2. 获取类目type列表
     * 3. 查询类目
     * 4. 构造数据
     */
    @GetMapping("/list")
    public ResultInfo<List<ProductVO>> list() {

        //查询所有在架的商品
        List<ProductInfoEntity> productInfoEntities = productService.findUpAll();

        //获取类目type列表
        List<Integer> integers = productInfoEntities.stream().map(ProductInfoEntity::getCategoryType).collect(Collectors.toList());

        //查询类目
        List<ProductCategoryEntity> productCategories = productCategoryService.findByCategoryTypeIn(integers);

        //构造数据
        List<ProductVO> productVOS = new ArrayList<>();
        for (ProductCategoryEntity productCategoryEntity : productCategories) {
            ProductVO productVO = ProductVO.builder()
                    .categoryName(productCategoryEntity.getCategoryName())
                    .categoryType(productCategoryEntity.getCategoryType())
                    .build();

            List<ProductInfo> productInfoEntityVoList = new ArrayList<>();
            for (ProductInfoEntity productInfoEntity : productInfoEntities) {
                if (productInfoEntity.getCategoryType() == productVO.getCategoryType()) {
                    productInfoEntityVoList.add(ProductInfo.builder()
                            .productDescription(productInfoEntity.getProductDescription())
                            .productIcon(productInfoEntity.getProductIcon())
                            .productId(productInfoEntity.getProductId())
                            .productName(productInfoEntity.getProductName())
                            .productPrice(productInfoEntity.getProductPrice())
                            .build());
                }
            }

            productVO.setProductInfoList(productInfoEntityVoList);
            productVOS.add(productVO);
        }


        return ResultInfo.success(productVOS);
    }

    /**
     * 获取商品列表(给订单服务用的)
     *
     * @param productIdList
     * @return
     */
    @GetMapping("/listForOrder")
    public List<ProductInfoEntity> listForOrder(@RequestParam("productId") List<String> productIdList) {
        return productService.findByProductId(productIdList);
    }

    @DeleteMapping("/decreaseStock")
    public void decreaseStock(@RequestBody List<CartInfo> cartInfos) {
        productService.decreaseStock(cartInfos);
    }
}
