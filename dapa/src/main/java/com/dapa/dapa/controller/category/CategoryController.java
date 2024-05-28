package com.dapa.dapa.controller.category;

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

import com.dapa.dapa.dto.category.CategoryRequestDto;
import com.dapa.dapa.dto.category.CategoryResponseDto;
import com.dapa.dapa.service.category.CategoryService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
// import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/category")
@Tag(name = "Category")
// @Slf4j
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @PostMapping("add/")
    @SecurityRequirement(name = "Bearer Authentication")
    public String add(@RequestBody CategoryRequestDto dto) {
        return categoryService.add(dto);
    }

    @PutMapping("update/{id}/")
    @SecurityRequirement(name = "Bearer Authentication")
    public String update(@RequestParam String id, @RequestBody CategoryRequestDto dto) {
        return categoryService.update(id, dto);
    }

    @DeleteMapping("/delete/{id}")
    @SecurityRequirement(name = "Bearer Authentication")
    public String remove(@RequestParam String id) {
        return categoryService.remove(id);
    }

    // @PreAuthorize("hasAnyRole('CUSTOMER', 'MANAGER')")
    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping("/view-all")
    public List<CategoryResponseDto> viewall(){
        return categoryService.viewall();
    }
}
