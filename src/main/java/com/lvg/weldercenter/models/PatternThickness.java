package com.lvg.weldercenter.models;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Victor Levchenko LVG Corp. on 19.10.14.
 */
@Entity
@Table(name = "pattern_thickness")
public class PatternThickness implements Serializable{

    private static final long serialVersionUID = 7059658007755395761L;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PatternThickness that = (PatternThickness) o;

        if (!patternThicknessId.equals(that.patternThicknessId)) return false;
        if (thickness != null ? !thickness.equals(that.thickness) : that.thickness != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = patternThicknessId.hashCode();
        result = 31 * result + (thickness != null ? thickness.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "PatternThickness{" +
                "patternThicknessId=" + patternThicknessId +
                ", thickness=" + thickness +
                '}';
    }

}
