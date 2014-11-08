package com.lvg.weldercenter.models;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Victor Levchenko LVG Corp. on 19.10.14.
 */
@Entity
@Table(name = "pattern_diameter")
public class PatternDiameter implements Serializable{


    private static final long serialVersionUID = 2582260995488041417L;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PatternDiameter that = (PatternDiameter) o;

        if (diameter != null ? !diameter.equals(that.diameter) : that.diameter != null) return false;
        if (!patternDiameterId.equals(that.patternDiameterId)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = patternDiameterId.hashCode();
        result = 31 * result + (diameter != null ? diameter.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "PatternDiameter{" +
                "patternDiameterId=" + patternDiameterId +
                ", diameter=" + diameter +
                '}';
    }
}
