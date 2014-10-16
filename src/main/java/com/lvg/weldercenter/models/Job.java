package com.lvg.weldercenter.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by victor on 15.10.2014.
 */
@Entity
@Table(name = "job")
public class Job {

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

    @OneToMany(targetEntity = Welder.class, mappedBy = "job", cascade = CascadeType.ALL)
    public List<Welder> getWelders() {
        return welders;
    }

    public void setWelders(List<Welder> welders) {
        this.welders = welders;
    }
}
