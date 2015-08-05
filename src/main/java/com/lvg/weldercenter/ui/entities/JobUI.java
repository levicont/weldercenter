package com.lvg.weldercenter.ui.entities;

import com.lvg.weldercenter.models.Job;
import javafx.beans.property.SimpleStringProperty;

/**
 * Created by Victor on 05.08.2015.
 */
public class JobUI extends GenericEntityUI {

    private final SimpleStringProperty name = new SimpleStringProperty();

    public JobUI(){
        id.set(0);
        name.set("");
    }

    public JobUI(Job job){
        id.set(job.getJobId());
        name.set(job.getName());
    }

    //Getters and setters

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        JobUI jobUI = (JobUI) o;

        if (name != null ? !name.equals(jobUI.name) : jobUI.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return getName();
    }
}
