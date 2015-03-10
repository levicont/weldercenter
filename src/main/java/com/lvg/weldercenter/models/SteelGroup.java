package com.lvg.weldercenter.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Victor Levchenko LVG Corp. on 19.10.14.
 */
@Entity
@Table(name = "steel_group")
public class SteelGroup implements Serializable{

    private static final long serialVersionUID = -6892408262800146304L;
    private Long steelGroupId;
    private String stGroup;
    private String description;

    private List<SteelType> steelTypes = new ArrayList<SteelType>();


    //Getters and Setters

    @Id
    @GeneratedValue
    @Column(name = "id_steel_group")
    public Long getSteelGroupId() {
        return steelGroupId;
    }

    public void setSteelGroupId(Long steelGroupId) {
        this.steelGroupId = steelGroupId;
    }

    @Column(name = "st_group")
    public String getStGroup() {
        return stGroup;
    }

    public void setStGroup(String stGroup) {
        this.stGroup = stGroup;
    }

    @Column(columnDefinition = "TEXT")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @OneToMany(targetEntity = SteelType.class, mappedBy = "stGroup")
    public List<SteelType> getSteelTypes() {
        return steelTypes;
    }

    public void setSteelTypes(List<SteelType> steelTypes) {
        this.steelTypes = steelTypes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SteelGroup that = (SteelGroup) o;

        if (!description.equals(that.description)) return false;
        if (!steelGroupId.equals(that.steelGroupId)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = steelGroupId.hashCode();
        result = 31 * result + description.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "SteelGroup{" +
                "steelGroupId=" + steelGroupId +
                ", stGroup='" + stGroup + '\'' +
                '}';
    }
}
