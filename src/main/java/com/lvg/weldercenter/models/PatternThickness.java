package com.lvg.weldercenter.models;

import javax.persistence.*;

/**
 * Created by Victor Levchenko LVG Corp. on 19.10.14.
 */
@Entity
@Table(name = "pattern_thickness")
public class PatternThickness {

    private Long patternThicknessId;
    private Double thickness;

    //Getters and Setters

    @Id
    @GeneratedValue
    @Column(name = "id_pattern_thickness")
    public Long getPatternThicknessId() {
        return patternThicknessId;
    }

    public void setPatternThicknessId(Long patternThicknessId) {
        this.patternThicknessId = patternThicknessId;
    }

    public Double getThickness() {
        return thickness;
    }

    public void setThickness(Double thickness) {
        this.thickness = thickness;
    }
}
