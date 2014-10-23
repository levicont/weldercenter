package com.lvg.weldercenter.models;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "electrode")
public class Electrode {
	
	private Long electrodeId;
	private String type;

    private List<WeldPattern> weldPatterns = new ArrayList<WeldPattern>();


    //Getters and Setters

    @Id
    @GeneratedValue
    @Column (name = "id_electrode")
    public Long getElectrodeId() {
		return electrodeId;
	}
	public void setElectrodeId(Long electrodeId) {
		this.electrodeId = electrodeId;
	}

    public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

    @OneToMany(targetEntity = WeldPattern.class, mappedBy = "electrode")
    public List<WeldPattern> getWeldPatterns() {
        return weldPatterns;
    }

    public void setWeldPatterns(List<WeldPattern> weldPatterns) {
        this.weldPatterns = weldPatterns;
    }
}
