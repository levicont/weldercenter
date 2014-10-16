package com.lvg.weldercenter.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "electrode")
public class Electrode {
	
	@Id
	@Column (name = "id_electrode", unique = true, nullable = false)
	@GenericGenerator(name="kaugen", strategy = "increment")
	@GeneratedValue(generator="kaugen")	
	private Integer id;
	
	@Column(name = "type")
	private String type;
	
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	

}
