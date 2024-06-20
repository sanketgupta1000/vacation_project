package com.project.readers.readers_community.services;

import com.project.readers.readers_community.DTOs.CategoryDTO;
import com.project.readers.readers_community.DTOs.Mapper;
import com.project.readers.readers_community.repositories.BookCategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService
{
    private final BookCategoryRepository categoryRepository;
    private final Mapper mapper;

    public CategoryService(BookCategoryRepository categoryRepository, Mapper mapper) {
        this.categoryRepository = categoryRepository;
        this.mapper = mapper;
    }

    // method to get all categories
    public List<CategoryDTO> getAllCategories()
    {
        return categoryRepository.findAll().stream().map(mapper::categoryToCategoryDTO).toList();
    }
}
