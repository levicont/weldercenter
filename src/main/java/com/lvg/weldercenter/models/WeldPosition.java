package com.lvg.weldercenter.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Victor Levchenko LVG Corp. on 19.10.14.
 */
@Entity
@Table(name = "weld_position")
public class WeldPosition {

    private Long weldPositionId;
    private String code;
    private String type;

    private List<WeldPattern> weldPatterns = new ArrayList<WeldPattern>();

    //Getters and Setters

    @Id
    @GeneratedValue
    @Column(name = "id_weld_position")
    public Long getWeldPositionId() {
        return weldPositionId;
    }

    public void setWeldPositionId(Long weldPositionId) {
        this.weldPositionId = weldPositionId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @OneToMany(targetEntity = WeldPattern.class, mappedBy = "weldPosition")
    public List<WeldPattern> getWeldPatterns() {
        return weldPatterns;
    }

    public void setWeldPatterns(List<WeldPattern> weldPatterns) {
        this.weldPatterns = weldPatterns;
    }
}
