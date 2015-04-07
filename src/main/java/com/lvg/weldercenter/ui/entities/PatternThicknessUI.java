package com.lvg.weldercenter.ui.entities;

import com.lvg.weldercenter.models.PatternThickness;
import javafx.beans.property.SimpleDoubleProperty;

/**
 * Created by Victor Levchenko LVG Corp. on 23.01.15.
 */
public class PatternThicknessUI extends GenericEntityUI {
    private SimpleDoubleProperty thickness = new SimpleDoubleProperty();

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
        return "PatternThicknessUI{" +
                "thickness=" + thickness +
                '}';
    }
}
