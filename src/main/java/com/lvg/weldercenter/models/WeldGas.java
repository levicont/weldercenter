package com.lvg.weldercenter.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Victor Levchenko LVG Corp. on 19.10.14.
 */
@Entity
@Table(name = "weld_gas")
public class WeldGas implements Serializable{


    private static final long serialVersionUID = -105343824819272869L;
    private Long weldGasId;
    private String type;

    private List<WeldPattern> weldPatterns = new ArrayList<WeldPattern>();

    //Getters and Setters

    @Id
    @GeneratedValue
    @Column(name = "id_weld_gas")
    public Long getWeldGasId() {
        return weldGasId;
    }

    public void setWeldGasId(Long weldGasId) {
        this.weldGasId = weldGasId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @OneToMany(targetEntity = WeldPattern.class, mappedBy = "weldGas")
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

        WeldGas gas = (WeldGas) o;

        if (!type.equals(gas.type)) return false;
        if (!weldGasId.equals(gas.weldGasId)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = weldGasId.hashCode();
        result = 31 * result + type.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "WeldGas{" +
                "weldGasId=" + weldGasId +
                ", type='" + type + '\'' +
                '}';
    }
}
