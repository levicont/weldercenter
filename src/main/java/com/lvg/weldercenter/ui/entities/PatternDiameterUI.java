package com.lvg.weldercenter.ui.entities;

import com.lvg.weldercenter.models.PatternDiameter;
import javafx.beans.property.SimpleDoubleProperty;

/**
 * Created by Victor Levchenko LVG Corp. on 23.01.15.
 */
public class PatternDiameterUI extends GenericEntityUI {
    private final SimpleDoubleProperty diameter = new SimpleDoubleProperty();

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
}
