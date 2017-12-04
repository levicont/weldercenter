package com.lvg.weldercenter.se.ui.dto

import com.lvg.weldercenter.se.models.Job
import javafx.beans.property.SimpleStringProperty
import javafx.beans.property.StringProperty

class JobDTO extends GenericModelDTO<Job>{

    private final Job job

    private final StringProperty nameProperty = new SimpleStringProperty()

    JobDTO(Job job){
        validateModel(job)
        this.job = job

        idProperty.set(job.id == null ? DTOConstants.NULL_ID_FIELD_DEFAULT: job.id)
        nameProperty.set(job.name)
    }

    void setName(String name){
        nameProperty.set(name)
    }

    String getName(){
        return nameProperty.get()
    }

    StringProperty nameProperty(){
        return nameProperty
    }

    Job getJob(){
        job.name = getName()
        return job
    }


    @Override
    Long getId() {
        def id = getJob().id
        return id == null ? DTOConstants.NULL_ID_FIELD_DEFAULT : id
    }

    boolean equals(o) {
        if (this.is(o)) return true
        if (getClass() != o.class) return false

        JobDTO jobDTO = (JobDTO) o

        if (nameProperty != jobDTO.nameProperty) return false
        if (getId() != jobDTO.getId()) return false

        return true
    }

    int hashCode() {
        int result
        result = (getId() != null ? getId().hashCode() : 0)
        result = 31 * result + (getName() != null ? getName().hashCode() : 0)
        return result
    }

    @Override
    String toString() {
        return getJob().toString()
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return new JobDTO(getJob())
    }
}
