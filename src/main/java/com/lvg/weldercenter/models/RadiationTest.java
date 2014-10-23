package com.lvg.weldercenter.models;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Victor Levchenko LVG Corp. on 19.10.14.
 */
@Entity
@Table(name = "radiation_test")
public class RadiationTest {

    private Long radiationTestId;
    private String defects;
    private String sensitivty;
    private String number;
    private Date protDate;

    private Evaluation evaluation;

    //Getters and Setters

    @Id
    @GeneratedValue
    @Column(name = "id_radiation_test")
    public Long getRadiationTestId() {
        return radiationTestId;
    }

    public void setRadiationTestId(Long radiationTestId) {
        this.radiationTestId = radiationTestId;
    }

    public String getDefects() {
        return defects;
    }

    public void setDefects(String defects) {
        this.defects = defects;
    }

    public String getSensitivty() {
        return sensitivty;
    }

    public void setSensitivty(String sensitivty) {
        this.sensitivty = sensitivty;
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
