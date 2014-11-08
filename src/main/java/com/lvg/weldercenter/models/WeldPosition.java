package com.lvg.weldercenter.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Victor Levchenko LVG Corp. on 19.10.14.
 */
@Entity
@Table(name = "weld_position")
public class WeldPosition implements Serializable{

    private static final long serialVersionUID = -7072879002003333464L;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WeldPosition that = (WeldPosition) o;

        if (!code.equals(that.code)) return false;
        if (!type.equals(that.type)) return false;
        if (!weldPositionId.equals(that.weldPositionId)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = weldPositionId.hashCode();
        result = 31 * result + code.hashCode();
        result = 31 * result + type.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "WeldPosition{" +
                "weldPositionId=" + weldPositionId +
                ", code='" + code + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
