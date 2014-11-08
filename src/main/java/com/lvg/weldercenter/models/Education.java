package com.lvg.weldercenter.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by victor on 15.10.2014.
 */
@Entity
@Table(name = "education")
public class Education implements Serializable{


    private static final long serialVersionUID = 2064936268732369435L;
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

    @OneToMany(targetEntity = Welder.class, mappedBy = "education")
    public List<Welder> getWelders() {
        return welders;
    }

    public void setWelders(List<Welder> welders) {
        this.welders = welders;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Education education = (Education) o;

        if (!educationId.equals(education.educationId)) return false;
        if (!type.equals(education.type)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = educationId.hashCode();
        result = 31 * result + type.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Education{" +
                "educationId=" + educationId +
                ", type='" + type + '\'' +
                '}';
    }
}
