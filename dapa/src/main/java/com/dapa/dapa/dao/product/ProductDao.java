package com.dapa.dapa.dao.product;

import com.dapa.dapa.dto.PageResponse;
import com.dapa.dapa.entity.Category;
import com.dapa.dapa.entity.Products;

public interface ProductDao {
    PageResponse<Products> findAll(String productName, int productPrice, int productStock,
            Category category, int page, int size);
}
