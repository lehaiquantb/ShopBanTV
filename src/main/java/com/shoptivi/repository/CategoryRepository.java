package com.shoptivi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shoptivi.entity.CategoryEntity;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
	CategoryEntity findOneByName(String categoryName);
}
