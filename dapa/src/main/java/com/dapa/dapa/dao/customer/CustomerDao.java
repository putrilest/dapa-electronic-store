package com.dapa.dapa.dao.customer;

import com.dapa.dapa.dto.PageResponse;
import com.dapa.dapa.entity.Customer;
import com.dapa.dapa.entity.Products;

public interface CustomerDao {
        PageResponse<Customer> findAll(String customerName, String registerDate, Products products, int page ,int size);
}
