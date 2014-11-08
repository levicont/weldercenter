package com.lvg.weldercenter.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "weld_method")
public class WeldMethod implements Serializable{


    private static final long serialVersionUID = 8767288205784425694L;
    private Long weldMethodId;
	private String code;
	private String name;
	
	private List<Welder> welders = new  ArrayList<Welder>();
    private List<WeldPattern> weldPatterns = new ArrayList<WeldPattern>();

    //Getters and Setters

    @ManyToMany
    @JoinTable(name = "welder_weld_method",
                joinColumns = {@JoinColumn(name = "id_weld_method")},
                inverseJoinColumns = {@JoinColumn(name = "id_welder")})
	public List<Welder> getWelders(){
		return welders;
	}

    public void setWelders(List<Welder> welders) {
        this.welders = welders;
    }

    @Id
    @Column(name = "id_weld_method")
    @GeneratedValue
    public Long getWeldMethodId() {
		return weldMethodId;
	}

	public void setWeldMethodId(Long weldMethodId) {
		this.weldMethodId = weldMethodId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

    @OneToMany(targetEntity = WeldPattern.class, mappedBy = "weldMethod")
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

        WeldMethod that = (WeldMethod) o;

        if (!code.equals(that.code)) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (!weldMethodId.equals(that.weldMethodId)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = weldMethodId.hashCode();
        result = 31 * result + code.hashCode();
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "WeldMethod{" +
                "weldMethodId=" + weldMethodId +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
