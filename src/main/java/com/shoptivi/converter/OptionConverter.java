package com.shoptivi.converter;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.stereotype.Component;

import com.shoptivi.dto.OptionDTO;
import com.shoptivi.entity.OptionEntity;
import com.shoptivi.entity.ProductEntity;
import com.shoptivi.entity.VariantEntity;
import com.shoptivi.util.Time;

@Component
public class OptionConverter {
	// chuyển từ list sang hashmap để dễ đọc hơn
	public HashMap<String, List<String>> toOptionValues(List<OptionEntity> entities) {
		HashMap<String, List<String>> optionValues = new LinkedHashMap<String, List<String>>();
		for (OptionEntity entity : entities) {
			if (optionValues.containsKey(entity.getName())) {
				optionValues.get(entity.getName()).add(entity.getValue());
			} else {
				List<String> values = new ArrayList<String>();
				values.add(entity.getValue());
				optionValues.put(entity.getName(), values);
			}
		}
		return optionValues;
	}

	public List<OptionEntity> toEntities(HashMap<String, List<String>> optionValues) {
		List<OptionEntity> entities = new ArrayList<>();
		for (String name : optionValues.keySet()) {
			for (String value : optionValues.get(name)) {
				OptionEntity entity = new OptionEntity();
				entity.setName(name);
				entity.setValue(value);
				entity.setModifiedOn(Time.newDate());
				entities.add(entity);
			}
		}
		return entities;
	}

	public OptionEntity toEntity(OptionDTO dto, VariantEntity variantEntity, ProductEntity productEntity) {
		OptionEntity entity = new OptionEntity(dto, variantEntity, productEntity);
		return entity;
	}

	public List<OptionEntity> toEntities(List<OptionDTO> dtos, VariantEntity variantEntity,
			ProductEntity productEntity) {
		List<OptionEntity> entities = new ArrayList<>();
		for (OptionDTO dto : dtos) {
			entities.add(this.toEntity(dto, variantEntity, productEntity));
		}
		return entities;
	}

	public OptionDTO toDTO(OptionEntity entity) {
		OptionDTO dto = new OptionDTO(entity);
		return dto;
	}

	public List<OptionDTO> toDTOs(List<OptionEntity> entities) {
		List<OptionDTO> dtos = new ArrayList<OptionDTO>();
		for (OptionEntity entity : entities) {
			dtos.add(this.toDTO(entity));
		}
		return dtos;
	}
}
