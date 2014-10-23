package com.lvg.weldercenter.models;

import javax.persistence.*;

/**
 * Created by Victor Levchenko LVG Corp. on 19.10.14.
 */
@Entity
@Table(name = "pattern_diameter")
public class PatternDiameter {

    private Long patternDiameterId;
    private Double diameter;


    //Getters and Setters

    @Id
    @GeneratedValue
    @Column(name = "id_pattern_diameter")
    public Long getPatternDiameterId() {
        return patternDiameterId;
    }

    public void setPatternDiameterId(Long patternDiameterId) {
        this.patternDiameterId = patternDiameterId;
    }

    public Double getDiameter() {
        return diameter;
    }

    public void setDiameter(Double diameter) {
        this.diameter = diameter;
    }
}
