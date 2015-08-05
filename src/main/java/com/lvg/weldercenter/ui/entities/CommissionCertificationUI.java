package com.lvg.weldercenter.ui.entities;

import com.lvg.weldercenter.models.CommissionCertification;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * Created by Victor Levchenko LVG Corp. on 23.01.15.
 */
public class CommissionCertificationUI extends GenericEntityUI {

    private final SimpleObjectProperty<TeacherUI> head = new SimpleObjectProperty<TeacherUI>();
    private final SimpleObjectProperty<TeacherUI> weldSpecialist = new SimpleObjectProperty<TeacherUI>();
    private final SimpleObjectProperty<TeacherUI> ndtSpecialist = new SimpleObjectProperty<TeacherUI>();
    private final SimpleObjectProperty<TeacherUI> safetySpecialist = new SimpleObjectProperty<TeacherUI>();

//    private final SimpleStringProperty headName = new SimpleStringProperty();
//    private final SimpleStringProperty weldSpecName = new SimpleStringProperty();
//    private final SimpleStringProperty ndtSpecName = new SimpleStringProperty();
//    private final SimpleStringProperty safetySpecName = new SimpleStringProperty();


    public CommissionCertificationUI(){
        this.id.set(0);
        this.head.set(new TeacherUI());
        this.weldSpecialist.set(new TeacherUI());
        this.ndtSpecialist.set(new TeacherUI());
        this.safetySpecialist.set(new TeacherUI());
    }

    public CommissionCertificationUI(CommissionCertification commission){
        this.id.set(commission.getCommissionCertificationId());
        if (commission.getHead()!=null)
            this.head.set(new TeacherUI(commission.getHead()));
        if (commission.getWeldSpecialist()!=null)
            this.weldSpecialist.set(new TeacherUI(commission.getWeldSpecialist()));
        if (commission.getNdtSpecialist()!=null)
            this.ndtSpecialist.set(new TeacherUI(commission.getNdtSpecialist()));
        if (commission.getSafetySpecialist()!=null)
            this.safetySpecialist.set(new TeacherUI(commission.getSafetySpecialist()));
    }

    //Getters and Setters

    public TeacherUI getHead() {
        return head.get();
    }

    public SimpleObjectProperty<TeacherUI> headProperty() {
        return head;
    }

    public void setHead(TeacherUI head) {
        this.head.set(head);
    }

    public TeacherUI getWeldSpecialist() {
        return weldSpecialist.get();
    }

    public SimpleObjectProperty<TeacherUI> weldSpecialistProperty() {
        return weldSpecialist;
    }

    public void setWeldSpecialist(TeacherUI weldSpecialist) {
        this.weldSpecialist.set(weldSpecialist);
    }

    public TeacherUI getNdtSpecialist() {
        return ndtSpecialist.get();
    }

    public SimpleObjectProperty<TeacherUI> ndtSpecialistProperty() {
        return ndtSpecialist;
    }

    public void setNdtSpecialist(TeacherUI ndtSpecialist) {
        this.ndtSpecialist.set(ndtSpecialist);
    }

    public TeacherUI getSafetySpecialist() {
        return safetySpecialist.get();
    }

    public SimpleObjectProperty<TeacherUI> safetySpecialistProperty() {
        return safetySpecialist;
    }

    public void setSafetySpecialist(TeacherUI safetySpecialist) {
        this.safetySpecialist.set(safetySpecialist);
    }

    @Override
    public String toString() {
        return "CommissionCertificationUI{" +
                "head=" + head +
                ", weldSpecialist=" + weldSpecialist +
                ", ndtSpecialist=" + ndtSpecialist +
                ", safetySpecialist=" + safetySpecialist +
                '}';
    }
}
