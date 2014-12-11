package com.lvg.weldercenter.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Victor Levchenko LVG Corp. on 19.10.14.
 */
@Entity
@Table(name = "visual_test")
public class VisualTest implements Serializable{


    private static final long serialVersionUID = -7243284380841259964L;
    private Long visuaLTestId;
    private String defects;
    private String number;
    private Date protDate;

    private Evaluation evaluation;
    private WeldPattern weldPattern;

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

        VisualTest that = (VisualTest) o;

        if (!number.equals(that.number)) return false;
        if (!protDate.equals(that.protDate)) return false;
        if (!visuaLTestId.equals(that.visuaLTestId)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = visuaLTestId.hashCode();
        result = 31 * result + number.hashCode();
        result = 31 * result + protDate.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "VisualTest{" +
                "visuaLTestId=" + visuaLTestId +
                ", number='" + number + '\'' +
                ", protDate=" + protDate +
                ", evaluation=" + evaluation +
                '}';
    }
}
