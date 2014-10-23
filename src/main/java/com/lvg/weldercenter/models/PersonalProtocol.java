package com.lvg.weldercenter.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Victor Levchenko LVG Corp. on 20.10.14.
 */
@Entity
@Table(name = "personal_protocol")
public class PersonalProtocol {

    private Long personalProtocolId;
    private Date datePeriodicalCert;

    private Welder welder;
    private Journal journal;
    private ResolutionCertification resolutionCertification;
    private CommissionCertification commissionCertification;
    private TheoryTest theoryTest;


    private List<WeldPattern> weldPatterns = new ArrayList<WeldPattern>();
    private List<NDTDocument> ndtDocuments = new ArrayList<NDTDocument>();

    //Getters and Setters

    @Id
    @GeneratedValue
    @Column(name = "id_personal_protocol")
    public Long getPersonalProtocolId() {
        return personalProtocolId;
    }

    public void setPersonalProtocolId(Long personalProtocolId) {
        this.personalProtocolId = personalProtocolId;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "date_periodical_cert")
    public Date getDatePeriodicalCert() {
        return datePeriodicalCert;
    }

    public void setDatePeriodicalCert(Date datePeriodicalCert) {
        this.datePeriodicalCert = datePeriodicalCert;
    }

    @ManyToOne(targetEntity = Welder.class)
    @JoinColumn(name = "id_welder")
    public Welder getWelder() {
        return welder;
    }

    public void setWelder(Welder welder) {
        this.welder = welder;
    }

    @OneToMany(targetEntity = WeldPattern.class, mappedBy = "personalProtocol")
    public List<WeldPattern> getWeldPatterns() {
        return weldPatterns;
    }

    public void setWeldPatterns(List<WeldPattern> weldPatterns) {
        this.weldPatterns = weldPatterns;
    }

    @ManyToOne(targetEntity = Journal.class)
    @JoinColumn(name = "id_journal")
    public Journal getJournal() {
        return journal;
    }

    public void setJournal(Journal journal) {
        this.journal = journal;
    }


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_resolution_certification")
    public ResolutionCertification getResolutionCertification() {
        return resolutionCertification;
    }

    public void setResolutionCertification(ResolutionCertification resolutionCertification) {
        this.resolutionCertification = resolutionCertification;
    }

    @ManyToOne(targetEntity = CommissionCertification.class)
    @JoinColumn(name = "id_commission_certification")
    public CommissionCertification getCommissionCertification() {
        return commissionCertification;
    }

    public void setCommissionCertification(CommissionCertification commissionCertification) {
        this.commissionCertification = commissionCertification;
    }

    @OneToOne(targetEntity = TheoryTest.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_theory_test")
    public TheoryTest getTheoryTest() {
        return theoryTest;
    }

    public void setTheoryTest(TheoryTest theoryTest) {
        this.theoryTest = theoryTest;
    }

    @ManyToMany
    @JoinTable(name = "personal_protocol_ndt_document",
            joinColumns = {@JoinColumn(name = "id_personal_protocol")},
            inverseJoinColumns = {@JoinColumn(name = "id_ndt_document")})
    public List<NDTDocument> getNdtDocuments() {
        return ndtDocuments;
    }

    public void setNdtDocuments(List<NDTDocument> ndtDocuments) {
        this.ndtDocuments = ndtDocuments;
    }
}
