package com.dapa.dapa.service.product;

import java.util.List;

import com.dapa.dapa.dto.PageResponse;
import com.dapa.dapa.dto.product.ProductRequestDto;
import com.dapa.dapa.dto.product.ProductResponseDto;
import com.dapa.dapa.entity.Products;

public interface ProductService {
    PageResponse<Products> findAll(String productName, int productPrice, int productStock, String category, int page,
            int size);

    String add(ProductRequestDto dto);
    String update(String id, ProductRequestDto dto);
    String remove(String id);
    List<ProductResponseDto> viewall();

}
