package com.lvg.weldercenter.models;

import javax.annotation.Generated;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Victor Levchenko LVG Corp. on 19.10.14.
 */
@Entity
@Table(name = "weld_wire")
public class WeldWire {

    private Long weldWireId;
    private String type;

    private List<WeldPattern> weldPatterns = new ArrayList<WeldPattern>();




    //Getters and Setters
    @Id
    @GeneratedValue()
    @Column(name = "id_weld_wire")
    public Long getWeldWireId() {
        return weldWireId;
    }

    public void setWeldWireId(Long weldWireId) {
        this.weldWireId = weldWireId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @OneToMany(targetEntity = WeldPattern.class, mappedBy = "weldWire")
    public List<WeldPattern> getWeldPatterns() {
        return weldPatterns;
    }

    public void setWeldPatterns(List<WeldPattern> weldPatterns) {
        this.weldPatterns = weldPatterns;
    }
}
