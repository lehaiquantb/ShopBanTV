package com.shoptivi.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.shoptivi.dto.ProductDTO;
import com.shoptivi.entity.CategoryEntity;
import com.shoptivi.entity.ProductEntity;
import com.shoptivi.repository.CategoryRepository;
import com.shoptivi.util.Time;

@Component
public class ProductConverter {

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private OptionConverter optionConverter;

	@Autowired
	private VariantConverter variantConverter;

	// entity != null
	public ProductDTO toDTO(ProductEntity entity) {
		ProductDTO dto = new ProductDTO(entity);
		if (entity.getVariants() != null) {
			dto.setVariantDTOs(variantConverter.toDTOs(entity.getVariants()));
		}

		if (entity.getOptions() != null) {
			dto.setOptionValues(optionConverter.toOptionValues(entity.getOptions()));
		}

		if (entity.getCategory() != null) {
			dto.setCategoryName(entity.getCategory().getName());
		}
		return dto;
	}

	public List<ProductDTO> toDTOs(List<ProductEntity> entities) {
		List<ProductDTO> dtos = new ArrayList<ProductDTO>();
		for (ProductEntity entity : entities) {
			dtos.add(this.toDTO(entity));
		}
		return dtos;
	}

	// Hàm thực hiện chuyển product từ dto sang entity
	public ProductEntity toEntity(ProductDTO dto) {
		ProductEntity entity = new ProductEntity(dto);

		// Kiểm tra danh mục đã có trong db chưa, nếu chưa thì tạo mới
		CategoryEntity categoryEntity = categoryRepository.findOneByName(dto.getCategoryName());
		if (categoryEntity == null) {
			categoryEntity = new CategoryEntity(dto);
			categoryEntity.setCreatedOn(Time.newDate());
			categoryEntity.setName(dto.getCategoryName());
		}
		
		entity.setCategory(categoryEntity);
		
		//persist danh mục
		categoryRepository.save(categoryEntity);

		return entity;
	}

}
