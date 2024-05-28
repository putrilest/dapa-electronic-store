package com.dapa.dapa.controller.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dapa.dapa.dto.product.ProductRequestDto;
import com.dapa.dapa.dto.product.ProductResponseDto;
import com.dapa.dapa.service.product.ProductService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/product")
@Tag(name = "Product")
@Slf4j
public class ProductContoller {
    @Autowired
    ProductService productService;

    // @GetMapping("/find-all")
    // @SecurityRequirement(name = "Bearer Authentication")
    // public ResponseEntity<Object> findAll(@RequestParam(required = false) String productName,
    //         @RequestParam(required = false) int productPrice, @RequestParam(required = false) int productStock,
    //         @RequestParam(required = false) String catgeory, @RequestParam int page, @RequestParam int size) {
    //     try {
    //         return ResponseEntity.ok()
    //                 .body(GenericResponse.success(
    //                         productService.findAll(productName, productPrice, productStock, catgeory, page, size),
    //                         "Successfully Fect Daata"));
    //     } catch (ResponseStatusException e) {
    //         log.info(e.getMessage());
    //         return ResponseEntity.status(e.getStatusCode()).body(GenericResponse.error(e.getReason()));
    //     } catch (Exception e) {
    //         log.info(e.getMessage());
    //         return ResponseEntity.internalServerError().body(GenericResponse.error("Internal Server Error"));
    //     }
    // }

    @PostMapping("/add")
    @SecurityRequirement(name = "Bearer Authentication")
    public String add(@RequestBody ProductRequestDto dto) {
        return productService.add(dto);
    }

    @PutMapping("update/{id}/")
    @SecurityRequirement(name = "Bearer Authentication")
    public String update(@RequestParam String id, @RequestBody ProductRequestDto dto) {
        return productService.update(id, dto);
    }

    @DeleteMapping("/delete/{id}")
    @SecurityRequirement(name = "Bearer Authentication")
    public String remove(@RequestParam String id) {
        return productService.remove(id);
    }

    @GetMapping("/view-all")
    @SecurityRequirement(name = "Bearer Authentication")
    public List<ProductResponseDto> viewall(){
        return productService.viewall();
    }

}
