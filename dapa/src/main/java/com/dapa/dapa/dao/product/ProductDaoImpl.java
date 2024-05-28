package com.dapa.dapa.dao.product;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dapa.dapa.dto.PageResponse;
import com.dapa.dapa.entity.Category;
import com.dapa.dapa.entity.Products;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class ProductDaoImpl implements ProductDao {
    @Autowired
    EntityManager entityManager;

    @Override
    public PageResponse<Products> findAll(String productName, int productPrice, int productStock, Category category,
            int page, int size) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Products> criteriaQuery = criteriaBuilder.createQuery(Products.class);
        Root<Products> productRoot = criteriaQuery.from(Products.class);
        criteriaQuery.where(createPredicate(criteriaBuilder, productRoot, productName, productPrice, productStock, category));

        List<Products> result = entityManager.createQuery(criteriaQuery).setFirstResult((page - 1) * size)
                .setMaxResults(size).getResultList();
        CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
        Root<Products> root = countQuery.from(Products.class);
        countQuery.select(criteriaBuilder.count(root))
                .where(createPredicate(criteriaBuilder, root, productName, productPrice, productStock, category));
        Long totalItem = entityManager.createQuery(countQuery).getSingleResult();

        return PageResponse.success(result, page, size, totalItem);
    }

    private Predicate[] createPredicate(CriteriaBuilder criteriaBuilder, Root<Products> root, String productName,
            int productPrice, int productStock, Category category) {
        List<Predicate> predicates = new ArrayList<>();
        if (productName != null && !productName.isBlank() && !productName.isEmpty()) {
            predicates.add(criteriaBuilder.like(root.get("productName"), "%" + productName + "%"));
        }
        if (productPrice != 0) {
            predicates.add(criteriaBuilder.like(root.get("productPrice"), "%" + productPrice + "%"));
        }
        if (productStock != 0) {
            predicates.add(criteriaBuilder.like(root.get("productStock"), "%" + productStock + "%"));
        }
        if (category != null) {
            predicates.add(criteriaBuilder.like(root.get("category"), "%" + category + "%"));
        }
        return predicates.toArray(new Predicate[0]);
    }

}
