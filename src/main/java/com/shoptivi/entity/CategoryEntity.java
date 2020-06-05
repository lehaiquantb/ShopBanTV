package com.shoptivi.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.shoptivi.dto.AbstractDTO;

@Entity
@Table(name = "category")
public class CategoryEntity extends BaseEntity {
	public CategoryEntity(AbstractDTO dto) {
		super(dto);
	}
	
	public CategoryEntity() {
	}
	
	@OneToMany(mappedBy = "category")
	@JsonManagedReference
	private List<ProductEntity> products = new ArrayList<ProductEntity>();

	public List<ProductEntity> getProducts() {
		return products;
	}

	public void setProducts(List<ProductEntity> products) {
		this.products = products;
	}
	
}
