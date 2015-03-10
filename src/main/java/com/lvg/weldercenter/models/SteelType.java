package com.lvg.weldercenter.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Victor Levchenko LVG Corp. on 19.10.14.
 */
@Entity
@Table(name = "steel_type")
public class SteelType implements Serializable{


    private static final long serialVersionUID = -1407405188864099634L;
    private Long steelTypeId;
    private String type;

    private SteelGroup stGroup;


    private List<WeldPattern> weldPatterns = new ArrayList<WeldPattern>();

    //Getters and Setters
    @Id
    @GeneratedValue
    @Column(name = "id_steel_type")
    public Long getSteelTypeId() {
        return steelTypeId;
    }

    public void setSteelTypeId(Long steelTypeId) {
        this.steelTypeId = steelTypeId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @ManyToOne(targetEntity = SteelGroup.class)
    @JoinColumn(name = "id_steel_group")
    public SteelGroup getStGroup() {
        return stGroup;
    }

    public void setStGroup(SteelGroup stGroup) {
        this.stGroup = stGroup;
    }

    @OneToMany(targetEntity = WeldPattern.class, mappedBy = "steelType")
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

        SteelType steelType = (SteelType) o;

        if (!steelTypeId.equals(steelType.steelTypeId)) return false;
        if (!type.equals(steelType.type)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = steelTypeId.hashCode();
        result = 31 * result + type.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "SteelType{" +
                "steelTypeId=" + steelTypeId +
                ", type='" + type + '\'' +
                '}';
    }
}
