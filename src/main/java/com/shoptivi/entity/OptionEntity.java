package com.shoptivi.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.shoptivi.dto.OptionDTO;

@Entity
@Table(name = "optionTV")
public class OptionEntity extends BaseEntity {

	public OptionEntity() {

	}

	public OptionEntity(OptionDTO dto, VariantEntity variantEntity, ProductEntity productEntity) {
		super(dto);
		this.value = dto.getValue();
		this.variant = variantEntity;
		this.product = productEntity;
	}

	@Column
	private String value;

	@ManyToOne
	@JoinColumn(name = "product_id")
	@JsonBackReference
	private ProductEntity product;

	@ManyToOne
	@JoinColumn(name = "variant_id")
	@JsonBackReference
	private VariantEntity variant;

	public VariantEntity getVariant() {
		return variant;
	}

	public void setVariant(VariantEntity variant) {
		this.variant = variant;
	}

	public ProductEntity getProduct() {
		return product;
	}

	public void setProduct(ProductEntity product) {
		this.product = product;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
