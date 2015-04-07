package com.lvg.weldercenter.ui.entities;

import com.lvg.weldercenter.models.Evaluation;
import javafx.beans.property.SimpleStringProperty;

/**
 * Created by Victor Levchenko LVG Corp. on 23.01.15.
 */
public class EvaluationUI extends GenericEntityUI {
    private final SimpleStringProperty type = new SimpleStringProperty();

    public EvaluationUI() {
        this.id.set(0);
        this.type.set("");
    }

    public EvaluationUI(Evaluation evaluation){
        this.id.set(evaluation.getEvaluationId());
        this.type.set(evaluation.getType());
    }

    //Getters and Setters

    public String getType() {
        return type.get();
    }

    public SimpleStringProperty typeProperty() {
        return type;
    }

    public void setType(String type) {
        this.type.set(type);
    }

    @Override
    public String toString() {
        return "EvaluationUI{" +
                "type=" + type +
                '}';
    }
}
