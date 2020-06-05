package com.shoptivi.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.shoptivi.entity.ProductEntity;

public class ProductDTO extends AbstractDTO {

	public ProductDTO(ProductEntity entity) {
		super(entity);
		this.content = entity.getContentTV();
		this.vendor = entity.getVendor();
	}
	
	public ProductDTO() {
	}
	
	private String content;

	private String vendor;

	private String categoryName;

	private HashMap<String, List<String>> optionValues;

	private List<VariantDTO> variantDTOs = new ArrayList<VariantDTO>();

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getVendor() {
		return vendor;
	}

	public void setVendor(String vendor) {
		this.vendor = vendor;
	}

	public HashMap<String, List<String>> getOptionValues() {
		return optionValues;
	}

	public void setOptionValues(HashMap<String, List<String>> optionValues) {
		this.optionValues = optionValues;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public List<VariantDTO> getVariantDTOs() {
		return variantDTOs;
	}

	public void setVariantDTOs(List<VariantDTO> variantDTOs) {
		this.variantDTOs = variantDTOs;
	}

}
