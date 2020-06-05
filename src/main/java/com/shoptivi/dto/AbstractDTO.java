package com.shoptivi.dto;

import java.util.Date;

import com.shoptivi.entity.BaseEntity;

public abstract class AbstractDTO {

	private Long id;
	private Date createdOn;
	private Date modifiedOn;
	private String name;

	public AbstractDTO(BaseEntity entity) {
		this.id = entity.getId();
		this.createdOn = entity.getCreatedOn();
		this.modifiedOn = entity.getModifiedOn();
		this.name = entity.getName();
	}
	
	public AbstractDTO() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public Date getModifiedOn() {
		return modifiedOn;
	}

	public void setModifiedOn(Date modifiedOn) {
		this.modifiedOn = modifiedOn;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
