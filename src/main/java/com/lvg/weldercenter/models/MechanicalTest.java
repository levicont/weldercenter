package com.lvg.weldercenter.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Victor Levchenko LVG Corp. on 19.10.14.
 */
@Entity
@Table(name = "mechanical_test")
public class MechanicalTest implements Serializable{


    private static final long serialVersionUID = 6108026806480436780L;
    private Long mechanicalTestId;
    private Double angle;
    private Double clearance;
    private String number;
    private Date protDate;

    private Evaluation evaluation;
    private WeldPattern weldPattern;

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


    @Column(name = "prot_date")
    public Date getProtDate() {
        return protDate;
    }

    public void setProtDate(Date protDate) {
        this.protDate = protDate;
    }

    @OneToOne
    @JoinColumn(name = "id_weld_pattern")
    public WeldPattern getWeldPattern() {
        return weldPattern;
    }

    public void setWeldPattern(WeldPattern weldPattern) {
        this.weldPattern = weldPattern;
    }

    @ManyToOne(targetEntity = Evaluation.class)
    @JoinColumn(name = "id_evaluation")
    public Evaluation getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(Evaluation evaluation) {
        this.evaluation = evaluation;
    }

    public Double getClearance() {
        return clearance;
    }

    public void setClearance(Double clearance) {
        this.clearance = clearance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MechanicalTest that = (MechanicalTest) o;

        if (!mechanicalTestId.equals(that.mechanicalTestId)) return false;
        if (number != null ? !number.equals(that.number) : that.number != null) return false;
        if (protDate != null ? !protDate.equals(that.protDate) : that.protDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = mechanicalTestId.hashCode();
        result = 31 * result + (number != null ? number.hashCode() : 0);
        result = 31 * result + (protDate != null ? protDate.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "MechanicalTest{" +
                "mechanicalTestId=" + mechanicalTestId +
                ", angle=" + angle +
                ", clearance=" + clearance +
                ", number='" + number + '\'' +
                ", protDate=" + protDate +
                ", evaluation=" + evaluation +
                '}';
    }
}
