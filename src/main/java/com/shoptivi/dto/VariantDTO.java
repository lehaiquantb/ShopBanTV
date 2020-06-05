package com.shoptivi.dto;

import java.util.Date;
import java.util.List;

import com.shoptivi.entity.VariantEntity;

public class VariantDTO extends AbstractDTO {
	
	public VariantDTO(VariantEntity entity) {
		super(entity);
		this.price = entity.getPrice();
		this.quantity = entity.getQuantity();
		
	}
	
	public VariantDTO() {
	}
	
	private List<OptionDTO> optionDTOs;

	private int quantity;

	private float price;

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public List<OptionDTO> getOptionDTOs() {
		return optionDTOs;
	}

	public void setOptionDTOs(List<OptionDTO> optionDTOs) {
		this.optionDTOs = optionDTOs;
	}

}
