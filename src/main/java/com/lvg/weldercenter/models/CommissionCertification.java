package com.lvg.weldercenter.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Victor Levchenko LVG Corp. on 23.10.14.
 */
@Entity
@Table(name = "commission_certification")
public class CommissionCertification {

    private Long commissionCertificationId;

    private Teacher head;
    private Teacher weldSpecialist;
    private Teacher ndtSpecialist;
    private Teacher safetySpecialist;

    private List<PersonalProtocol> personalProtocols = new ArrayList<PersonalProtocol>();

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

    @ManyToOne(targetEntity = Teacher.class)
    @JoinColumn(name = "id_teacher_head")
    public Teacher getHead() {
        return head;
    }

    public void setHead(Teacher head) {
        this.head = head;
    }

    @ManyToOne(targetEntity = Teacher.class)
    @JoinColumn(name = "id_teacher_weld_spec")
    public Teacher getWeldSpecialist() {
        return weldSpecialist;
    }

    public void setWeldSpecialist(Teacher weldSpecialist) {
        this.weldSpecialist = weldSpecialist;
    }

    @ManyToOne(targetEntity = Teacher.class)
    @JoinColumn(name = "id_teacher_ndt_spec")
    public Teacher getNdtSpecialist() {
        return ndtSpecialist;
    }

    public void setNdtSpecialist(Teacher ndtSpecialist) {
        this.ndtSpecialist = ndtSpecialist;
    }

    @ManyToOne(targetEntity = Teacher.class)
    @JoinColumn(name = "id_teacher_safety_spec")
    public Teacher getSafetySpecialist() {
        return safetySpecialist;
    }

    public void setSafetySpecialist(Teacher safetySpecialist) {
        this.safetySpecialist = safetySpecialist;
    }

    @OneToMany(targetEntity = PersonalProtocol.class, mappedBy = "commissionCertification")
    public List<PersonalProtocol> getPersonalProtocols() {
        return personalProtocols;
    }

    public void setPersonalProtocols(List<PersonalProtocol> personalProtocols) {
        this.personalProtocols = personalProtocols;
    }
}
