package com.lvg.weldercenter.ui.entities;

import com.lvg.weldercenter.hibernate.utils.Unproximator;
import com.lvg.weldercenter.models.Journal;
import com.lvg.weldercenter.models.Teacher;
import com.lvg.weldercenter.models.Welder;
import com.lvg.weldercenter.ui.util.DateUtil;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Victor Levchenko LVG Corp. on 15.12.14.
 */
public class JournalUI extends GenericEntityUI {

    private final SimpleStringProperty number = new SimpleStringProperty();
    private final SimpleObjectProperty<Date> dateBegin = new SimpleObjectProperty<Date>();
    private final SimpleObjectProperty<Date> dateEnd = new SimpleObjectProperty<Date>();
    private final SimpleStringProperty dateBeginFormat = new SimpleStringProperty();
    private final SimpleStringProperty dateEndFormat = new SimpleStringProperty();
    private final SimpleStringProperty curriculum = new SimpleStringProperty();
    private final SimpleStringProperty weldersCount = new SimpleStringProperty();

    private final SimpleListProperty<WelderUI> welders = new SimpleListProperty<WelderUI>();
    private final SimpleListProperty<TeacherUI> teachers = new SimpleListProperty<TeacherUI>();

    public JournalUI(Journal journal){
        this.id.set(journal.getJournalId());
        this.number.set(journal.getNumber());
        this.dateBegin.set(journal.getDateBegin());
        this.dateEnd.set(journal.getDateEnd());
        this.dateBeginFormat.set(DateUtil.format(dateBegin.get()));
        this.dateEndFormat.set(DateUtil.format(dateEnd.get()));

        if (journal.getCurriculum() == null){
            this.curriculum.set("");
        }else {
            this.curriculum.set(journal.getCurriculum().getTitle());
        }

        this.teachers.set(getTeachersUI(journal));
        this.welders.set(getWeldersUI(journal));
        this.weldersCount.set(welders.get().size()+"");
    }

    public JournalUI(){
        this.id.set(0);
        this.number.set("");
        this.dateBegin.set(DateUtil.getDate(DateUtil.DEFAULT_DATE));
        this.dateEnd.set(DateUtil.getDate(DateUtil.DEFAULT_DATE));
        this.dateBeginFormat.set(DateUtil.format(DateUtil.DEFAULT_DATE));
        this.dateEndFormat.set(DateUtil.format(DateUtil.DEFAULT_DATE));
        this.curriculum.set("");
        this.weldersCount.set("0");

        this.teachers.set(FXCollections.observableArrayList(new ArrayList<TeacherUI>()));
        this.welders.set(FXCollections.observableArrayList(new ArrayList<WelderUI>()));
    }

    private ObservableList<WelderUI> getWeldersUI(Journal journal){
        ObservableList<WelderUI> result = FXCollections.observableArrayList();
        List<Welder> welderList = Unproximator.initializeAndUnproxy(journal.getWelders());
        for (Welder welder : welderList){
            result.add(new WelderUI(welder));
        }
        return result;
    }

    private ObservableList<TeacherUI> getTeachersUI(Journal journal){
        ObservableList<TeacherUI> result = FXCollections.observableArrayList();
        List<Teacher> teacherList = journal.getTeachers();
        for(Teacher teacher : teacherList){
            result.add(new TeacherUI(teacher));
        }
        return result;
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

    public Date getDateBegin() {
        return dateBegin.get();
    }

    public SimpleObjectProperty<Date> dateBeginProperty() {
        return dateBegin;
    }

    public void setDateBegin(Date dateBegin) {
        this.dateBegin.set(dateBegin);
    }

    public Date getDateEnd() {
        return dateEnd.get();
    }

    public SimpleObjectProperty<Date> dateEndProperty() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd.set(dateEnd);
    }

    public String getDateBeginFormat() {
        return dateBeginFormat.get();
    }

    public SimpleStringProperty dateBeginFormatProperty() {
        return dateBeginFormat;
    }

    public void setDateBeginFormat(String dateBeginFormat) {
        this.dateBeginFormat.set(dateBeginFormat);
    }

    public String getDateEndFormat() {
        return dateEndFormat.get();
    }

    public SimpleStringProperty dateEndFormatProperty() {
        return dateEndFormat;
    }

    public void setDateEndFormat(String dateEndFormat) {
        this.dateEndFormat.set(dateEndFormat);
    }

    public String getCurriculum() {
        return curriculum.get();
    }

    public SimpleStringProperty curriculumProperty() {
        return curriculum;
    }

    public void setCurriculum(String curriculum) {
        this.curriculum.set(curriculum);
    }

    public String getWeldersCount() {
        return weldersCount.get();
    }

    public SimpleStringProperty weldersCountProperty() {
        return weldersCount;
    }

    public void setWeldersCount(String weldersCount) {
        this.weldersCount.set(weldersCount);
    }

    public ObservableList<WelderUI> getWelders() {
        return welders.get();
    }

    public SimpleListProperty<WelderUI> weldersProperty() {
        return welders;
    }

    public void setWelders(ObservableList<WelderUI> welders) {
        this.welders.set(welders);
    }

    public ObservableList<TeacherUI> getTeachers() {
        return teachers.get();
    }

    public SimpleListProperty<TeacherUI> teachersProperty() {
        return teachers;
    }

    public void setTeachers(ObservableList<TeacherUI> teachers) {
        this.teachers.set(teachers);
    }

    @Override
    public String toString() {
        return "JournalUI{\n" +
                "id= " +id.get()+
                ", \n number=" + number.get() +
                ", \n dateBeginFormat=" + dateBeginFormat.get() +
                ", \n dateEndFormat=" + dateEndFormat.get() +
                ", \n weldersCount=" + weldersCount.get() +
                ", \n teachers=" + teachers.get()+
                '}';
    }
}
