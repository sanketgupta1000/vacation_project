package com.project.readers.readers_community.controllers;

import com.project.readers.readers_community.DTOs.CategoryDTO;
import com.project.readers.readers_community.services.CategoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController
{
    private final CategoryService categoryService;

    // DI
    public CategoryController(CategoryService categoryService)
    {
        this.categoryService = categoryService;
    }

    // method to get all categories
    @GetMapping
    public List<CategoryDTO> getAllCategories()
    {
        return categoryService.getAllCategories();
    }
}
