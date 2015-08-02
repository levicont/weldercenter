package com.lvg.weldercenter.ui.entities;

import com.lvg.weldercenter.models.PatternThickness;
import javafx.beans.property.SimpleDoubleProperty;

/**
 * Created by Victor Levchenko LVG Corp. on 23.01.15.
 */
public class PatternThicknessUI extends GenericEntityUI {
    private SimpleDoubleProperty thickness = new SimpleDoubleProperty();
    private final String UNIT_SUFFIX = " мм";

    public PatternThicknessUI() {
        this.id.set(0);
        this.thickness.set(0);
    }

    public PatternThicknessUI(PatternThickness patternThickness){
        this.id.set(patternThickness.getPatternThicknessId());
        this.thickness.set(patternThickness.getThickness());
    }

    //Getters and Setters

    public double getThickness() {
        return thickness.get();
    }

    public SimpleDoubleProperty thicknessProperty() {
        return thickness;
    }

    public void setThickness(double thickness) {
        this.thickness.set(thickness);
    }

    @Override
    public String toString() {
        return getThickness()+UNIT_SUFFIX;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        PatternThicknessUI that = (PatternThicknessUI) o;

        if (UNIT_SUFFIX != null ? !UNIT_SUFFIX.equals(that.UNIT_SUFFIX) : that.UNIT_SUFFIX != null) return false;
        if (thickness != null ? !thickness.equals(that.thickness) : that.thickness != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (thickness != null ? thickness.hashCode() : 0);
        result = 31 * result + (UNIT_SUFFIX != null ? UNIT_SUFFIX.hashCode() : 0);
        return result;
    }
}
