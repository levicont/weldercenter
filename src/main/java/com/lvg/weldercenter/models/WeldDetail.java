package com.lvg.weldercenter.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Victor Levchenko LVG Corp. on 19.10.14.
 */
@Entity
@Table(name = "weld_detail")
public class WeldDetail implements Serializable{


    private static final long serialVersionUID = 8520331143561937661L;
    private Long weldDetailId;
    private String type;
    private String code;

    private List<WeldPattern> weldPatterns = new ArrayList<WeldPattern>();


    //Getters and Setters

    @Id
    @GeneratedValue
    @Column(name = "id_weld_detail")
    public Long getWeldDetailId() {
        return weldDetailId;
    }

    public void setWeldDetailId(Long weldDetailId) {
        this.weldDetailId = weldDetailId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @OneToMany(targetEntity = WeldPattern.class, mappedBy = "weldDetail")
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

        WeldDetail that = (WeldDetail) o;

        if (!code.equals(that.code)) return false;
        if (!type.equals(that.type)) return false;
        if (!weldDetailId.equals(that.weldDetailId)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = weldDetailId.hashCode();
        result = 31 * result + type.hashCode();
        result = 31 * result + code.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "WeldDetail{" +
                "weldDetailId=" + weldDetailId +
                ", type='" + type + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
