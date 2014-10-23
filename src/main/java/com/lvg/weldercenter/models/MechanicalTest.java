package com.lvg.weldercenter.models;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Victor Levchenko LVG Corp. on 19.10.14.
 */
@Entity
@Table(name = "mechanical_test")
public class MechanicalTest {

    private Long mechanicalTestId;
    private Double angle;
    private String number;
    private Date protDate;

    private Evaluation evaluation;

    //Getters and Setters

    @Id
    @GeneratedValue
    @Column(name = "id_mechanical_test")
    public Long getMechanicalTestId() {
        return mechanicalTestId;
    }

    public void setMechanicalTestId(Long mechanicalTestId) {
        this.mechanicalTestId = mechanicalTestId;
    }

    public Double getAngle() {
        return angle;
    }

    public void setAngle(Double angle) {
        this.angle = angle;
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
