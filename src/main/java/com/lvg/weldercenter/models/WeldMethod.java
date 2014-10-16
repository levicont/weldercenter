package com.lvg.weldercenter.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "weld_method")
public class WeldMethod {
	

    private Long weldMethodId;
	private String code;
	private String name;
	
	private List<Welder> welders = new  ArrayList<Welder>();


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


}
