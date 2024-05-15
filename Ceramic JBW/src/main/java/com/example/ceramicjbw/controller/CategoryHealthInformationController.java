package com.example.ceramicjbw.controller;

import com.example.ceramicjbw.entity.CategoryHealthinformation;
import com.example.ceramicjbw.service.Category.iCategoryHealthInformation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/category/health-information")
@AllArgsConstructor
public class CategoryHealthInformationController {
    private final iCategoryHealthInformation categoryHealthInformationService;

    @GetMapping("/{id}")
    public ResponseEntity<CategoryHealthinformation> getCategoryHealthinformationById(@PathVariable Long id) {
        try {
            CategoryHealthinformation category = categoryHealthInformationService.getCategoryHealthinformationById(id);
            return ResponseEntity.ok(category);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<CategoryHealthinformation>> getAllCategoryHealthinformation() {
        try {
            List<CategoryHealthinformation> categories = categoryHealthInformationService.getAllCategoryHealthinformation();
            return ResponseEntity.ok(categories);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}

