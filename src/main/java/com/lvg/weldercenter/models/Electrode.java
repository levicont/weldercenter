package com.lvg.weldercenter.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "electrode")
public class Electrode implements Serializable{


    private static final long serialVersionUID = 4252264983434283450L;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Electrode electrode = (Electrode) o;

        if (!electrodeId.equals(electrode.electrodeId)) return false;
        if (!type.equals(electrode.type)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = electrodeId.hashCode();
        result = 31 * result + type.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Electrode{" +
                "electrodeId=" + electrodeId +
                ", type='" + type + '\'' +
                '}';
    }
}
