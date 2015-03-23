package com.lvg.weldercenter.ui.entities;

import com.lvg.weldercenter.models.VisualTest;
import com.lvg.weldercenter.ui.util.DateUtil;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

import java.util.Date;

/**
 * Created by Victor Levchenko LVG Corp. on 23.01.15.
 */
public class VisualTestUI extends GenericEntityUI {
    private final SimpleStringProperty number = new SimpleStringProperty();
    private final SimpleStringProperty defects = new SimpleStringProperty();
    private final SimpleObjectProperty<Date> date = new SimpleObjectProperty<Date>();
    private final SimpleStringProperty dateFormat = new SimpleStringProperty();

    private final SimpleObjectProperty<EvaluationUI> evaluation = new SimpleObjectProperty<EvaluationUI>();
   // private final SimpleObjectProperty<WeldPatternUI> weldPattern = new SimpleObjectProperty<WeldPatternUI>();

    public VisualTestUI() {
        this.id.set(0);
        this.defects.set("");
        this.number.set("");
        this.date.set(DateUtil.getDate(DateUtil.DEFAULT_DATE));
        this.dateFormat.set(DateUtil.format(this.date.get()));
        this.evaluation.set(new EvaluationUI());

    }

    public VisualTestUI(VisualTest visualTest){
        this.id.set(visualTest.getVisuaLTestId());
        this.defects.set(visualTest.getDefects());
        this.number.set(visualTest.getNumber());
        this.date.set(visualTest.getProtDate());
        this.dateFormat.set(DateUtil.format(this.date.get()));
        this.evaluation.set(new EvaluationUI(visualTest.getEvaluation()));

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

    public String getDefects() {
        return defects.get();
    }

    public SimpleStringProperty defectsProperty() {
        return defects;
    }

    public void setDefects(String defects) {
        this.defects.set(defects);
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


}
