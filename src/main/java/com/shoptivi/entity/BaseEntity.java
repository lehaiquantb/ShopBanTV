package com.shoptivi.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.validator.constraints.NotBlank;

import com.shoptivi.dto.AbstractDTO;
import com.shoptivi.util.Time;

@MappedSuperclass
public abstract class BaseEntity {

	//Khi tạo mới 1 entity bất kì
	public BaseEntity(AbstractDTO dto) {
		this.modifiedOn = Time.newDate();
		this.name = dto.getName();
		this.createdOn = Time.newDate();
		this.id = dto.getId();
	}
	
	public BaseEntity() {
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	@NotBlank
	private String name;
	
	@Column
	private Date createdOn;

	@Column
	private Date modifiedOn;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
	
}
