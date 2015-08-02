package com.lvg.weldercenter.ui.entities;

import com.lvg.weldercenter.models.PatternDiameter;
import javafx.beans.property.SimpleDoubleProperty;

/**
 * Created by Victor Levchenko LVG Corp. on 23.01.15.
 */
public class PatternDiameterUI extends GenericEntityUI {
    private final SimpleDoubleProperty diameter = new SimpleDoubleProperty();
    private final String UNIT_SUFFIX = " мм";
    public PatternDiameterUI() {
        this.id.set(0);
        this.diameter.set(0);
    }

    public PatternDiameterUI(PatternDiameter patternDiameter){
        this.id.set(patternDiameter.getPatternDiameterId());
        this.diameter.set(patternDiameter.getDiameter());
    }

    //Getters and Setters

    public double getDiameter() {
        return diameter.get();
    }

    public SimpleDoubleProperty diameterProperty() {
        return diameter;
    }

    public void setDiameter(double diameter) {
        this.diameter.set(diameter);
    }

    @Override
    public String toString() {
        return getDiameter()+UNIT_SUFFIX;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        PatternDiameterUI that = (PatternDiameterUI) o;

        if (UNIT_SUFFIX != null ? !UNIT_SUFFIX.equals(that.UNIT_SUFFIX) : that.UNIT_SUFFIX != null) return false;
        if (diameter != null ? !diameter.equals(that.diameter) : that.diameter != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (diameter != null ? diameter.hashCode() : 0);
        result = 31 * result + (UNIT_SUFFIX != null ? UNIT_SUFFIX.hashCode() : 0);
        return result;
    }
}
