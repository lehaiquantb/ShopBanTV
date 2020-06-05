package com.shoptivi.dto;

import com.shoptivi.entity.OptionEntity;

public class OptionDTO extends AbstractDTO {
	public OptionDTO(OptionEntity entity) {
		super(entity);
		this.value = entity.getValue();
	}
	
	public OptionDTO() {
	}

	private String value;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
