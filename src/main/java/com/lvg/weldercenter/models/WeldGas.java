package com.lvg.weldercenter.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Victor Levchenko LVG Corp. on 19.10.14.
 */
@Entity
@Table(name = "weld_gas")
public class WeldGas {

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
}
