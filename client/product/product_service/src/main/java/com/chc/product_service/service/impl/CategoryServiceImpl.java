package com.chc.product_service.service.impl;


import com.chc.product_service.dataobject.ProductCategory;
import com.chc.product_service.repository.ProductCategoryRepository;
import com.chc.product_service.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author chc
 * @create
 **/
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    ProductCategoryRepository categoryRepository;
    @Override
    public List<ProductCategory> findByCategoryTypeIn(List<Integer> types) {
        return categoryRepository.findByCategoryTypeIn(types);

    }
}
