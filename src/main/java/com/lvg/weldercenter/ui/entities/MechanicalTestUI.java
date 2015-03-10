package com.lvg.weldercenter.ui.entities;

import com.lvg.weldercenter.models.MechanicalTest;
import com.lvg.weldercenter.models.VisualTest;
import com.lvg.weldercenter.ui.util.DateUtil;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

import java.util.Date;

/**
 * Created by Victor Levchenko LVG Corp. on 23.01.15.
 */
public class MechanicalTestUI extends GenericEntityUI {
    private final SimpleStringProperty number = new SimpleStringProperty();
    private final SimpleDoubleProperty angle = new SimpleDoubleProperty();
    private final SimpleObjectProperty<Date> date = new SimpleObjectProperty<Date>();
    private final SimpleStringProperty dateFormat = new SimpleStringProperty();

    private final SimpleObjectProperty<EvaluationUI> evaluation = new SimpleObjectProperty<EvaluationUI>();
    private final SimpleObjectProperty<WeldPatternUI> weldPattern = new SimpleObjectProperty<WeldPatternUI>();

    public MechanicalTestUI() {
        this.id.set(0);
        this.number.set("");
        this.angle.set(0);
        this.date.set(DateUtil.getDate(DateUtil.DEFAULT_DATE));
        this.dateFormat.set(DateUtil.format(this.date.get()));
        this.evaluation.set(new EvaluationUI());
        this.weldPattern.set(new WeldPatternUI());
    }

    public MechanicalTestUI(MechanicalTest mechanicalTest){
        this.id.set(mechanicalTest.getMechanicalTestId());
        this.number.set(mechanicalTest.getNumber());
        this.angle.set(mechanicalTest.getAngle());
        this.date.set(mechanicalTest.getProtDate());
        this.dateFormat.set(DateUtil.format(this.date.get()));
        this.evaluation.set(new EvaluationUI(mechanicalTest.getEvaluation()));
        this.weldPattern.set(new WeldPatternUI(mechanicalTest.getWeldPattern()));
    }

    //Getters and Setters


    public String getNumber() {
        return number.get();
    }

    public SimpleStringProperty numberProperty() {
        return number;
    }

    public void setNumber(String number) {
        this.number.set(number);
    }

    public double getAngle() {
        return angle.get();
    }

    public SimpleDoubleProperty angleProperty() {
        return angle;
    }

    public void setAngle(double angle) {
        this.angle.set(angle);
    }

    public Date getDate() {
        return date.get();
    }

    public SimpleObjectProperty<Date> dateProperty() {
        return date;
    }

    public void setDate(Date date) {
        this.date.set(date);
    }

    public String getDateFormat() {
        return dateFormat.get();
    }

    public SimpleStringProperty dateFormatProperty() {
        return dateFormat;
    }

    public void setDateFormat(String dateFormat) {
        this.dateFormat.set(dateFormat);
    }

    public EvaluationUI getEvaluation() {
        return evaluation.get();
    }

    public SimpleObjectProperty<EvaluationUI> evaluationProperty() {
        return evaluation;
    }

    public void setEvaluation(EvaluationUI evaluation) {
        this.evaluation.set(evaluation);
    }

    public WeldPatternUI getWeldPattern() {
        return weldPattern.get();
    }

    public SimpleObjectProperty<WeldPatternUI> weldPatternProperty() {
        return weldPattern;
    }

    public void setWeldPattern(WeldPatternUI weldPattern) {
        this.weldPattern.set(weldPattern);
    }
}
