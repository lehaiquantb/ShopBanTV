package com.shoptivi.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.shoptivi.dto.VariantDTO;
import com.shoptivi.entity.ProductEntity;
import com.shoptivi.entity.VariantEntity;

@Component
public class VariantConverter {

	@Autowired
	private OptionConverter optionConverter;

	public VariantDTO toDTO(VariantEntity entity) {
		VariantDTO dto = new VariantDTO(entity);
		dto.setOptionDTOs(optionConverter.toDTOs(entity.getOptions()));
		return dto;
	}

	public List<VariantDTO> toDTOs(List<VariantEntity> entities) {
		List<VariantDTO> dtos = new ArrayList<VariantDTO>();
		for (VariantEntity entity : entities) {
			dtos.add(this.toDTO(entity));
		}
		return dtos;
	}

	public VariantEntity toEntity(VariantDTO dto, ProductEntity productEntity) {
		VariantEntity entity = new VariantEntity(dto);
		
		//optionConverter.toEntities(dto.getOptionDTOs(),entity,productEntity);
		
		entity.setProduct(productEntity);
		return entity;
	}

	public List<VariantEntity> toEntities(List<VariantDTO> dtos, ProductEntity productEntity) {
		List<VariantEntity> entities = new ArrayList<VariantEntity>();
		for (VariantDTO dto : dtos) {
			entities.add(this.toEntity(dto, productEntity));
		}
		return entities;
	}

	
//	public List<OptionEntity> getOptionsFromVariantEntity() {
//		return this.optionEntities;
//	}
}
