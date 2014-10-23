package com.lvg.weldercenter.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Victor Levchenko LVG Corp. on 19.10.14.
 */
@Entity
@Table(name = "steel_group")
public class SteelGroup {

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

    @OneToMany(targetEntity = SteelType.class, mappedBy = "steelGroup", cascade = CascadeType.ALL)
    public List<SteelType> getSteelTypes() {
        return steelTypes;
    }

    public void setSteelTypes(List<SteelType> steelTypes) {
        this.steelTypes = steelTypes;
    }
}
