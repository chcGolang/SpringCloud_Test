package com.chc.product_service.service;




import common.DecreaseStockInput;
import common.ProductInfoOutput;

import java.util.List;

/**
 * @author chc
 * @create
 **/
public interface ProductService {

    /**
     * 查询所有在架的商品列表
     * @return
     */
    List<ProductInfoOutput> findUpAll();

    /**
     * 根据ip查询商品列表
     * @return
     */
    List<ProductInfoOutput> findList(List<String> productIdList);

    /**
     * 扣库存
     */
    void decreaseStock(List<DecreaseStockInput> decreaseStockInputList);
}

