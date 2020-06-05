package com.shoptivi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.shoptivi.entity.ProductEntity;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
	@Query(value = "SELECT TOP(1)* FROM product WHERE id = :id", nativeQuery = true)
	ProductEntity getById(@Param("id") Long id);
}
