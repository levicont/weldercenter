package com.lvg.weldercenter.models;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Victor Levchenko LVG Corp. on 23.10.14.
 */
@Entity
@Table(name = "commission_certification")
public class CommissionCertification implements Serializable{

    private static final long serialVersionUID = 651755708864709869L;

    private Long commissionCertificationId;

    private Teacher head;
    private Teacher weldSpecialist;
    private Teacher ndtSpecialist;
    private Teacher safetySpecialist;

    //Getters and Setters

    @Id
    @GeneratedValue
    @Column(name ="id_commission_certification")
    public Long getCommissionCertificationId() {
        return commissionCertificationId;
    }

    public void setCommissionCertificationId(Long commissionCertificationId) {
        this.commissionCertificationId = commissionCertificationId;
    }

    @ManyToOne(targetEntity = Teacher.class, cascade = {CascadeType.REFRESH})
    @JoinColumn(name = "id_teacher_head")
    public Teacher getHead() {
        return head;
    }

    public void setHead(Teacher head) {
        this.head = head;
    }

    @ManyToOne(targetEntity = Teacher.class, cascade = {CascadeType.REFRESH})
    @JoinColumn(name = "id_teacher_weld_spec")
    public Teacher getWeldSpecialist() {
        return weldSpecialist;
    }

    public void setWeldSpecialist(Teacher weldSpecialist) {
        this.weldSpecialist = weldSpecialist;
    }

    @ManyToOne(targetEntity = Teacher.class,cascade = {CascadeType.REFRESH} )
    @JoinColumn(name = "id_teacher_ndt_spec")
    public Teacher getNdtSpecialist() {
        return ndtSpecialist;
    }

    public void setNdtSpecialist(Teacher ndtSpecialist) {
        this.ndtSpecialist = ndtSpecialist;
    }

    @ManyToOne(targetEntity = Teacher.class, cascade = {CascadeType.REFRESH})
    @JoinColumn(name = "id_teacher_safety_spec")
    public Teacher getSafetySpecialist() {
        return safetySpecialist;
    }

    public void setSafetySpecialist(Teacher safetySpecialist) {
        this.safetySpecialist = safetySpecialist;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CommissionCertification that = (CommissionCertification) o;

        if (!commissionCertificationId.equals(that.commissionCertificationId)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return commissionCertificationId.hashCode();
    }

    @Override
    public String toString() {
        return "CommissionCertification{" +
                "commissionCertificationId=" + commissionCertificationId.longValue() +
                ", head=" + head.toString() +
                ", weldSpecialist=" + weldSpecialist.toString() +
                ", ndtSpecialist=" + ndtSpecialist.toString() +
                ", safetySpecialist=" + safetySpecialist.toString() +
                '}';
    }
}
