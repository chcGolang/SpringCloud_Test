package com.chc.product_client.cilent.hystrix;

import com.chc.product_client.cilent.ProductFeignClient;
import common.DecreaseStockInput;
import common.ProductInfoOutput;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 指定FeignClient的ProductFeignClient服务降级的方法
 * @author chc
 * @create 2019-01-14 11:57
 **/
@Component
public class ProductFeignClientHystrix implements ProductFeignClient {
    @Override
    public List<ProductInfoOutput> listForOrder(List<String> productIdList) {
        return null;
    }

    @Override
    public void decreaseStock(List<DecreaseStockInput> decreaseStockInputList) {

    }

    @Override
    public String msg() {
        return null;
    }
}
