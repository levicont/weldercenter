package com.lvg.weldercenter.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by victor on 15.10.2014.
 */
@Entity
@Table(name = "job")
public class Job implements Serializable{


    private static final long serialVersionUID = 2269970841977434066L;
    private Long jobId;
    private String name;

    private List<Welder> welders = new ArrayList<Welder>();

    //Getters and Setters
    @Id
    @GeneratedValue
    @Column(name = "id_job")
    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(targetEntity = Welder.class, mappedBy = "job")
    public List<Welder> getWelders() {
        return welders;
    }

    public void setWelders(List<Welder> welders) {
        this.welders = welders;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Job job = (Job) o;

        if (!jobId.equals(job.jobId)) return false;
        if (!name.equals(job.name)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = jobId.hashCode();
        result = 31 * result + name.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Job{" +
                "jobId=" + jobId +
                ", name='" + name + '\'' +
                '}';
    }
}
