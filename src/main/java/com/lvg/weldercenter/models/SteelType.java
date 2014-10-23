package com.lvg.weldercenter.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Victor Levchenko LVG Corp. on 19.10.14.
 */
@Entity
@Table(name = "steel_type")
public class SteelType {

    private Long steelTypeId;
    private String type;

    private SteelGroup steelGroup;


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
    public SteelGroup getSteelGroup() {
        return steelGroup;
    }

    public void setSteelGroup(SteelGroup steelGroup) {
        this.steelGroup = steelGroup;
    }

    @OneToMany(targetEntity = WeldPattern.class, mappedBy = "steelType")
    public List<WeldPattern> getWeldPatterns() {
        return weldPatterns;
    }

    public void setWeldPatterns(List<WeldPattern> weldPatterns) {
        this.weldPatterns = weldPatterns;
    }
}
