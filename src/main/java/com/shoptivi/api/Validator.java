package com.shoptivi.api;

import org.springframework.stereotype.Component;

import com.shoptivi.dto.OptionDTO;
import com.shoptivi.dto.ProductDTO;
import com.shoptivi.dto.VariantDTO;
import com.shoptivi.exception.NonNullIdException;
import com.shoptivi.exception.NullIdException;
import com.shoptivi.exception.NullNameException;
import com.shoptivi.exception.TooManyOptionException;

@Component
public class Validator {

	public boolean validateProductBePost(ProductDTO dto) {
		if (dto.getName() == null)
			throw new NullNameException();
		for (VariantDTO variantDTO : dto.getVariantDTOs()) {
			if (variantDTO.getOptionDTOs().size() > 3) {
				throw new TooManyOptionException();
			}
			if (variantDTO.getId() != null)
				throw new NonNullIdException();

			for(OptionDTO optionDTO : variantDTO.getOptionDTOs()) {
				if (optionDTO.getId() != null) 
					throw new NonNullIdException();
			}
			
		}
		return true;
	}
	
	public boolean validateProductBeDel(Long id) {
		if (id == null) {
			throw new NullIdException();
		}
		return true;
	}
	
	public boolean validateProductBeUpdate(ProductDTO dto) {
		if (dto.getId() == null) {
			throw new NullIdException();
		}
		return true;
	}
}
