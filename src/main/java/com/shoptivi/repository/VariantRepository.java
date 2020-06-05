package com.shoptivi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.shoptivi.entity.OptionEntity;
import com.shoptivi.entity.VariantEntity;

public interface VariantRepository extends JpaRepository<VariantEntity, Long> {
	@Query(value = "SELECT * FROM variant WHERE product_id = :product_id", nativeQuery = true)
	List<VariantEntity> findAllByProductId(@Param("product_id") Long productId);

	@Query(value = "SELECT COUNT(*) FROM optiontv  WHERE o.product_id = :product_id AND o.variant_id = :variant_id", nativeQuery = true)
	Long countOptionOfVariantById(@Param("product_id") Long productId, @Param("variant_id") Long variantId);

//	@Query(value = "SELECT id FROM variant WHERE product_id = :product_id", nativeQuery = true)
//	List<Long> getIdsByProductId(@Param("product_id") Long productId);
}
