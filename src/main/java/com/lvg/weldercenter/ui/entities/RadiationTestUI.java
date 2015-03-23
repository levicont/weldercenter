package com.lvg.weldercenter.ui.entities;

import com.lvg.weldercenter.models.RadiationTest;
import com.lvg.weldercenter.ui.util.DateUtil;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by Victor Levchenko LVG Corp. on 23.01.15.
 */
public class RadiationTestUI extends GenericEntityUI {
    private static final List<Double> SENSITIVITY_LIST = new ArrayList<Double>(Arrays.asList(0.1,0.2,0.3,0.4,0.5,0.6,0.7,0.8,0.9,1.0));

    private final SimpleStringProperty number = new SimpleStringProperty();
    private final SimpleStringProperty defects = new SimpleStringProperty();
    private final SimpleStringProperty sensitivity = new SimpleStringProperty();
    private final SimpleObjectProperty<Date> date = new SimpleObjectProperty<Date>();
    private final SimpleStringProperty dateFormat = new SimpleStringProperty();

    private final SimpleObjectProperty<EvaluationUI> evaluation = new SimpleObjectProperty<EvaluationUI>();
   // private final SimpleObjectProperty<WeldPatternUI> weldPattern = new SimpleObjectProperty<WeldPatternUI>();

    public RadiationTestUI() {
        this.id.set(0);
        this.defects.set("");
        this.number.set("");
        this.sensitivity.set("");
        this.date.set(DateUtil.getDate(DateUtil.DEFAULT_DATE));
        this.dateFormat.set(DateUtil.format(this.date.get()));
        this.evaluation.set(new EvaluationUI());
          }

    public RadiationTestUI(RadiationTest radiationTest){
        this.id.set(radiationTest.getRadiationTestId());
        this.defects.set(radiationTest.getDefects());
        this.number.set(radiationTest.getNumber());
        this.sensitivity.set(radiationTest.getSensitivity());
        this.date.set(radiationTest.getProtDate());
        this.dateFormat.set(DateUtil.format(this.date.get()));
        this.evaluation.set(new EvaluationUI(radiationTest.getEvaluation()));

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

    public String getSensitivity() {
        return sensitivity.get();
    }

    public SimpleStringProperty sensitivityProperty() {
        return sensitivity;
    }

    public void setSensitivity(String sensitivity) {
        this.sensitivity.set(sensitivity);
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

    public static List<Double> getSensitivityList(){
        return SENSITIVITY_LIST;
    }


}
