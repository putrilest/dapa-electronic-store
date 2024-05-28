// package com.dapa.dapa.dao.customer;

// import java.time.LocalDate;
// import java.time.format.DateTimeFormatter;
// import java.time.format.DateTimeParseException;
// import java.util.ArrayList;
// import java.util.List;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
// import org.springframework.stereotype.Repository;
// import org.springframework.web.server.ResponseStatusException;

// import com.dapa.dapa.dto.PageResponse;
// import com.dapa.dapa.entity.Customer;
// import com.dapa.dapa.entity.Products;

// import jakarta.persistence.EntityManager;
// import jakarta.persistence.criteria.CriteriaBuilder;
// import jakarta.persistence.criteria.CriteriaQuery;
// import jakarta.persistence.criteria.Predicate;
// import jakarta.persistence.criteria.Root;
// import lombok.extern.slf4j.Slf4j;

// @Slf4j
// @Repository
// public class CustomerDaoImpl implements CustomerDao{
//     @Autowired
//     EntityManager entityManager;

//     @Override
//     public PageResponse<Customer> findAll(String customerName, String registerDate, Products products, int page ,int size){
//         CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
// //total per page
//         CriteriaQuery<Customer> criteriaQuery = criteriaBuilder.createQuery(Customer.class);
//         Root<Customer> customerRoot = criteriaQuery.from(Customer.class);
//         criteriaQuery.where(createPredicate(criteriaBuilder,customerRoot,customerName,registerDate,products));
//         List<Customer> result = entityManager.createQuery(criteriaQuery)
//         .setFirstResult((page -1 ) * size)
//         .setMaxResults(size)
//         .getResultList();
// // total items
//         CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
//         Root<Customer> customerRootCount = countQuery.from(Customer.class);
//         countQuery.select(criteriaBuilder.count(customerRootCount))
//         .where(createPredicate(criteriaBuilder,customerRootCount,customerName,registerDate,products));
//         Long totalItem = entityManager.createQuery(countQuery).getSingleResult();

//         return PageResponse.succes(result, page, size, size);   
//     }

//     private Predicate[] createPredicate(CriteriaBuilder criteriaBuilder, Root<Customer> root, String customerName, String registerDate, String paymentMethod, Products products){
//         List<Predicate> predicates = new ArrayList<>();

//         if (customerName != null && !customerName.isBlank() && !customerName.isEmpty()) {
//             predicates.add(criteriaBuilder.like(root.get("customerName"),"%" + customerName+"%"));
//         }
//         if (registerDate != null && !registerDate.isBlank() && !registerDate.isEmpty()) {
//             try {
//                 LocalDate localDate = LocalDate.parse(registerDate,DateTimeFormatter.ISO_DATE);
//                 predicates.add(criteriaBuilder.equal(root.get("registerDate"),localDate));
//             } catch (DateTimeParseException e) {
//                 throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Invalid Date Fromat");
//             }
//         }
//         if (products != null) {
//             predicates.add(criteriaBuilder.like(root.get("products"),"%" + products+"%"));
//         }
//         return predicates.toArray(new Predicate[0]);
//     }
// }
