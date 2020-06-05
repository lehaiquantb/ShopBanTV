package com.shoptivi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.shoptivi.entity.OptionEntity;

public interface OptionRepository extends JpaRepository<OptionEntity, Long> {
	OptionEntity findByValue(String value);

	@Query(value = "SELECT * FROM optiontv o WHERE o.variant_id = :variant_id AND o.product_id = :product_id", nativeQuery = true)
	List<OptionEntity> findAllByVariantId_ProductId(@Param("variant_id") Long variantId,
			@Param("product_id") Long productId);

	@Query(value = "SELECT TOP(1)* FROM optiontv o WHERE o.name = :name AND o.value = :value AND o.product_id = :product_id AND o.variant_id = :variant_id", nativeQuery = true)
	OptionEntity findOptionExist(@Param("product_id") Long productId, @Param("variant_id") Long variantId,
			@Param("name") String name, @Param("value") String value);
}
