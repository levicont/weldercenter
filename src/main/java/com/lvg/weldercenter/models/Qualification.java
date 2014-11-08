package com.lvg.weldercenter.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by victor on 15.10.2014.
 */

@Entity
@Table(name = "qualification")
public class Qualification implements Serializable{


    private static final long serialVersionUID = 5723986856620125609L;
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

    @OneToMany(targetEntity = Welder.class, mappedBy = "qualification")
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

        Qualification that = (Qualification) o;

        if (!qualificationId.equals(that.qualificationId)) return false;
        if (!type.equals(that.type)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = qualificationId.hashCode();
        result = 31 * result + type.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Qualification{" +
                "qualificationId=" + qualificationId +
                ", type='" + type + '\'' +
                '}';
    }
}
