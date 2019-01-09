package com.chc.product_service.service;



import com.chc.product_service.dataobject.ProductCategory;

import java.util.List;

/**
 * @author chc
 * @create
 **/
public interface CategoryService {


    List<ProductCategory> findByCategoryTypeIn(List<Integer> types);
}
