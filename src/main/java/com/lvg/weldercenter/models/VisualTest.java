package com.lvg.weldercenter.models;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Victor Levchenko LVG Corp. on 19.10.14.
 */
@Entity
@Table(name = "visual_test")
public class VisualTest {

    private Long visuaLTestId;
    private String defects;
    private String number;
    private Date protDate;

    private Evaluation evaluation;

    //Getters and Setters

    @Id
    @GeneratedValue
    @Column(name = "id_visual_test")
    public Long getVisuaLTestId() {
        return visuaLTestId;
    }

    public void setVisuaLTestId(Long visuaLTestId) {
        this.visuaLTestId = visuaLTestId;
    }

    public String getDefects() {
        return defects;
    }

    public void setDefects(String defects) {
        this.defects = defects;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "prot_date")
    public Date getProtDate() {
        return protDate;
    }

    public void setProtDate(Date protDate) {
        this.protDate = protDate;
    }

    @ManyToOne(targetEntity = Evaluation.class)
    @JoinColumn(name = "id_evaluation")
    public Evaluation getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(Evaluation evaluation) {
        this.evaluation = evaluation;
    }
}
