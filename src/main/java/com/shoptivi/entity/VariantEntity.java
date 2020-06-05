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
import com.shoptivi.dto.AbstractDTO;
import com.shoptivi.dto.VariantDTO;

@Entity
@Table(name = "variant")
public class VariantEntity extends BaseEntity {

	public VariantEntity(VariantDTO dto) {
		super(dto);
		this.price = dto.getPrice();
		this.quantity = dto.getQuantity();
	}

	public VariantEntity() {
	}
	
	@Column
	private float price;

	@Column
	private int quantity;

	@ManyToOne
	@JoinColumn(name = "product_id")
	@JsonBackReference
	private ProductEntity product;

	@OneToMany(mappedBy = "variant")
	@JsonManagedReference
	private List<OptionEntity> options = new ArrayList<>();

	public List<OptionEntity> getOptions() {
		return options;
	}

	public void setOptions(List<OptionEntity> options) {
		this.options = options;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public ProductEntity getProduct() {
		return product;
	}

	public void setProduct(ProductEntity product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
