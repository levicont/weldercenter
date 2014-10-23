package com.lvg.weldercenter.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Victor Levchenko LVG Corp. on 19.10.14.
 */
@Entity
@Table(name = "evaluation")
public class Evaluation {

    private Long evaluationId;
    private String type;

    private List<RadiationTest> radiationTests = new ArrayList<RadiationTest>();
    private List<VisualTest> visualTests = new ArrayList<VisualTest>();
    private List<MechanicalTest> mechanicalTests = new ArrayList<MechanicalTest>();


    //Getters and Setters

    @Id
    @GeneratedValue
    @Column(name = "id_evaluation")
    public Long getEvaluationId() {
        return evaluationId;
    }

    public void setEvaluationId(Long evaluationId) {
        this.evaluationId = evaluationId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @OneToMany(targetEntity = RadiationTest.class, mappedBy = "evaluation")
    public List<RadiationTest> getRadiationTests() {
        return radiationTests;
    }

    public void setRadiationTests(List<RadiationTest> radiationTests) {
        this.radiationTests = radiationTests;
    }

    @OneToMany(targetEntity = VisualTest.class, mappedBy = "evaluation")
    public List<VisualTest> getVisualTests() {
        return visualTests;
    }

    public void setVisualTests(List<VisualTest> visualTests) {
        this.visualTests = visualTests;
    }

    @OneToMany(targetEntity = MechanicalTest.class, mappedBy = "evaluation")
    public List<MechanicalTest> getMechanicalTests() {
        return mechanicalTests;
    }

    public void setMechanicalTests(List<MechanicalTest> mechanicalTests) {
        this.mechanicalTests = mechanicalTests;
    }
}
