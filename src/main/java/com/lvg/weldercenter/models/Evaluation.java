package com.lvg.weldercenter.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Victor Levchenko LVG Corp. on 19.10.14.
 */
@Entity
@Table(name = "evaluation")
public class Evaluation implements Serializable{


    private static final long serialVersionUID = 4166254273280322087L;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Evaluation that = (Evaluation) o;

        if (!evaluationId.equals(that.evaluationId)) return false;
        if (!type.equals(that.type)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = evaluationId.hashCode();
        result = 31 * result + type.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Evaluation{" +
                "evaluationId=" + evaluationId +
                ", type='" + type + '\'' +
                '}';
    }
}
