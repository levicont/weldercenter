package com.lvg.weldercenter.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by victor on 15.10.2014.
 */

@Entity
@Table(name = "qualification")
public class Qualification {

    private Long qualificationId;
    private String type;
    private List<Welder> welders = new ArrayList<Welder>();

    //Getters and Setters
    @Id
    @GeneratedValue
    @Column(name = "id_qualification")
    public Long getQualificationId() {
        return qualificationId;
    }

    public void setQualificationId(Long qualificationId) {
        this.qualificationId = qualificationId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @OneToMany(targetEntity = Welder.class, mappedBy = "qualification", cascade = CascadeType.ALL)
    public List<Welder> getWelders() {
        return welders;
    }

    public void setWelders(List<Welder> welders) {
        this.welders = welders;
    }
}
