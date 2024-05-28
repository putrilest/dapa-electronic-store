package com.dapa.dapa.service.product;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dapa.dapa.dao.product.ProductDao;
import com.dapa.dapa.dto.PageResponse;
import com.dapa.dapa.dto.product.ProductRequestDto;
import com.dapa.dapa.dto.product.ProductResponseDto;
import com.dapa.dapa.entity.Category;
import com.dapa.dapa.entity.Products;
import com.dapa.dapa.repository.CategoryRepository;
import com.dapa.dapa.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductDao productDao;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public PageResponse<Products> findAll(String productName, int productPrice, int productStock,
            String category, int page, int size) {
        Category category2 = categoryRepository.findByCategoryName(category);
        return productDao.findAll(productName, productPrice, productStock, category2, page, size);
    }

    @Override
    public String add(ProductRequestDto dto) {
        Products products =new Products();
        products.setProductName(dto.getProductName());
        products.setProductPrice(dto.getProductPrice());
        products.setProductStock(dto.getProductStock());
        Category category = categoryRepository.findByCategoryName(dto.getCategory());
        products.setCategory(category);
        productRepository.save(products);
        return "Successfully add new category";
    }

    @Override
    public String update(String id, ProductRequestDto dto) {
            Products products = productRepository.findById(id).orElse(null);
            if (products != null) {
                    products.setProductName(dto.getProductName());
                    products.setProductPrice(dto.getProductStock());
                    products.setProductStock(dto.getProductStock());
                    Category category = categoryRepository.findByCategoryName(dto.getCategory());
                    products.setCategory(category);
                    productRepository.save(products);
                    return "Successfully update category";
            } else {
                    return "Id not found";
            }
    }

    @Override
    public String remove(String id) {
            Products products = productRepository.findById(id).orElse(null);
            if (products != null) {
                    productRepository.delete(products);
                    return "Successfully remove category";

            } else {
                    return "Id not Found";
            }
    }

    @Override
    public List<ProductResponseDto> viewall() {
        List<Products> products = productRepository.findAll();
        return products.stream()
                .map(this::toProductDto)
                .collect(Collectors.toList());
    }

    public ProductResponseDto toProductDto(Products products) {
        return ProductResponseDto.builder()
                .id(products.getId())
                .productName(products.getProductName())
                .productPrice(products.getProductPrice())
                .productStock(products.getProductStock())
                .category(products.getCategory().getCategoryName())
                .build();
    }
}
