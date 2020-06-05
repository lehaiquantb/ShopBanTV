package com.shoptivi.validator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.shoptivi.converter.VariantConverter;
import com.shoptivi.dto.VariantDTO;
import com.shoptivi.entity.VariantEntity;
import com.shoptivi.repository.ProductRepository;
import com.shoptivi.repository.VariantRepository;
import com.shoptivi.util.Duplicater;

@Component
public class VariantValidator {
	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private VariantConverter variantConverter;

	@Autowired
	private VariantRepository variantRepository;

	@Autowired
	private Duplicater duplicater;

	public List<List<VariantEntity>> getVariantsUpdate(List<VariantEntity> entitiesBePut, Long idOfProduct) {
		Long i = (long) 15;
		List<VariantEntity> entitiesFromDB = variantRepository.findAll();
		List<VariantDTO> dtos = variantConverter.toDTOs(entitiesFromDB);
		if (duplicater.isDuplicateVariant(entitiesBePut.get(0), entitiesFromDB.get(0)))
			System.out.println("hi");
		List<List<VariantEntity>> array = new ArrayList<List<VariantEntity>>();
		array.add(entitiesFromDB);
		array.add(entitiesBePut);
		return array;
	}

}
