package com.shoptivi.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.shoptivi.dto.ProductDTO;

@Entity
@Table(name = "product")
public class ProductEntity extends BaseEntity {
	
	public ProductEntity(ProductDTO dto) {
		super(dto);
		this.contentTV = dto.getContent();
		this.vendor = dto.getVendor();
	}
	
	public ProductEntity() {
	}
	
	
	@Column
	private String contentTV;

	@Column
	private String vendor;

	@OneToMany(mappedBy = "product")
	@JsonManagedReference
	private List<OptionEntity> options = new ArrayList<OptionEntity>();

	@OneToMany(mappedBy = "product")
	@JsonManagedReference
	private List<VariantEntity> variants = new ArrayList<VariantEntity>();

	@ManyToOne
	@JoinColumn(name = "category_id")
	@JsonBackReference
	private CategoryEntity category;

	public String getContentTV() {
		return contentTV;
	}

	public void setContentTV(String contentTV) {
		this.contentTV = contentTV;
	}

	public String getVendor() {
		return vendor;
	}

	public void setVendor(String vendor) {
		this.vendor = vendor;
	}

	public List<OptionEntity> getOptions() {
		return options;
	}

	public void setOptions(List<OptionEntity> options) {
		this.options = options;
	}

	public List<VariantEntity> getVariants() {
		return variants;
	}

	public void setVariants(List<VariantEntity> variants) {
		this.variants = variants;
	}

	public CategoryEntity getCategory() {
		return category;
	}

	public void setCategory(CategoryEntity category) {
		this.category = category;
	}

}
