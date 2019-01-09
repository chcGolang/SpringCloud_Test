package com.chc.product_service.controller;


import com.chc.product_service.dataobject.ProductCategory;
import com.chc.product_service.dataobject.ProductInfo;
import com.chc.product_service.service.CategoryService;
import com.chc.product_service.service.ProductService;
import com.chc.product_service.vo.ProductInfoVo;
import com.chc.product_service.vo.ProductVo;
import com.chc.product_service.vo.ResultVo;
import common.DecreaseStockInput;
import common.ProductInfoOutput;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;
import java.util.stream.Collectors;

/**
 * @author chc
 * @create
 **/
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;
    @Autowired
    CategoryService categoryService;
    @GetMapping("/list")
    public ResultVo<List<ProductVo>> list(){
        List<ProductInfoOutput> productInfos = productService.findUpAll();
        List<Integer> types = productInfos.stream()
                .map(ProductInfoOutput::getCategoryType)
                .collect(Collectors.toList());
        List<ProductCategory> categories = categoryService.findByCategoryTypeIn(types);

        List<ProductVo> productVos = categories.stream()
                .map(categorie -> {
            ProductVo productVo = new ProductVo();
            productVo.setCategoryName(categorie.getCategoryName());
            productVo.setCategoryType(categorie.getCategoryType());

            List<ProductInfoVo> productInfoVos = productInfos.stream()
                    .filter(category -> category.getCategoryType().equals(categorie.getCategoryType()) )
                    .map(productInfo -> {
                ProductInfoVo productInfoVo = new ProductInfoVo();
                BeanUtils.copyProperties(productInfo, productInfoVo);

                return productInfoVo;
            }).collect(Collectors.toList());

            productVo.setProductInfoVoList(productInfoVos);
            return productVo;
        }).collect(Collectors.toList());


        return ResultVo.Success(productVos);

    }

    /**
     * 获取商品列表(给订单服务用)
     * @param productIdList
     * @return
     */
    @PostMapping("/listForOrder")
    public List<ProductInfoOutput> listForOrder(@RequestBody List<String> productIdList){

        return productService.findList(productIdList);
    }

    @PostMapping("/decreaseStock")
    public void decreaseStock(@RequestBody List<DecreaseStockInput> decreaseStockInputList) {
        productService.decreaseStock(decreaseStockInputList);
    }
}
