package com.lvg.weldercenter.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Victor Levchenko LVG Corp. on 19.10.14.
 */
@Entity
@Table(name = "radiation_test")
public class RadiationTest implements Serializable{


    private static final long serialVersionUID = -8173044539435304659L;
    private Long radiationTestId;
    private String defects;
    private String sensitivity;
    private String number;
    private Date protDate;

    private Evaluation evaluation;
    private WeldPattern weldPattern;

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

    public String getSensitivity() {
        return sensitivity;
    }

    public void setSensitivity(String sensitivity) {
        this.sensitivity = sensitivity;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RadiationTest that = (RadiationTest) o;

        if (!number.equals(that.number)) return false;
        if (protDate != null ? !protDate.equals(that.protDate) : that.protDate != null) return false;
        if (!radiationTestId.equals(that.radiationTestId)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = radiationTestId.hashCode();
        result = 31 * result + number.hashCode();
        result = 31 * result + (protDate != null ? protDate.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "RadiationTest{" +
                "radiationTestId=" + radiationTestId +
                ", number='" + number + '\'' +
                ", protDate=" + protDate +
                '}';
    }
}
