package com.lvg.weldercenter.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Victor Levchenko LVG Corp. on 20.10.14.
 */
@Entity
@Table(name = "personal_protocol")
public class PersonalProtocol implements Serializable{


    private static final long serialVersionUID = 6810873579957924634L;
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


    @OneToOne
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

    @OneToOne(targetEntity = TheoryTest.class)
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PersonalProtocol that = (PersonalProtocol) o;

        if (!personalProtocolId.equals(that.personalProtocolId)) return false;
        if (theoryTest != null ? !theoryTest.equals(that.theoryTest) : that.theoryTest != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = personalProtocolId.hashCode();
        result = 31 * result + (theoryTest != null ? theoryTest.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "PersonalProtocol{" +
                "personalProtocolId=" + personalProtocolId +
                ", datePeriodicalCert=" + datePeriodicalCert +
                ", welder=" + welder +
                ", journal=" + journal.getNumber() +
                '}';
    }
}
