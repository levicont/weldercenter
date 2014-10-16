package com.lvg.weldercenter.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by victor on 15.10.2014.
 */
@Entity
@Table(name = "education")
public class Education {

    private Long educationId;
    private String type;
    private List<Welder> welders = new ArrayList<Welder>();

    @Id
    @GeneratedValue
    @Column(name = "id_education")
    public Long getEducationId() {
        return educationId;
    }

    public void setEducationId(Long educationId) {
        this.educationId = educationId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @OneToMany(targetEntity = Welder.class, mappedBy = "education", cascade = CascadeType.ALL)
    public List<Welder> getWelders() {
        return welders;
    }

    public void setWelders(List<Welder> welders) {
        this.welders = welders;
    }
}
