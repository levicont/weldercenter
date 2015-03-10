package com.lvg.weldercenter.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Victor Levchenko LVG Corp. on 03.02.15.
 */
@Entity
@Table(name = "total_protocol")
public class TotalProtocol implements Serializable {

    private static final long serialVersionUID = 8846874252222669506L;

    private Long idTotalProtocol;
    private String number;
    private Date dateCert;

    private Journal journal;
    private CommissionCertification commissionCertification;


    @Id
    @GeneratedValue
    @Column(name = "id_total_protocol")
    public Long getIdTotalProtocol() {
        return idTotalProtocol;
    }

    public void setIdTotalProtocol(Long idTotalProtocol) {
        this.idTotalProtocol = idTotalProtocol;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Column(name = "date_cert")
    public Date getDateCert() {
        return dateCert;
    }

    public void setDateCert(Date dateCert) {
        this.dateCert = dateCert;
    }

    @OneToOne
    @JoinColumn(name = "id_journal")
    public Journal getJournal() {
        return journal;
    }

    public void setJournal(Journal journal) {
        this.journal = journal;
    }

    @ManyToOne
    @JoinColumn(name = "id_commission_certification")
    public CommissionCertification getCommissionCertification() {
        return commissionCertification;
    }

    public void setCommissionCertification(CommissionCertification commissionCertification) {
        this.commissionCertification = commissionCertification;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TotalProtocol that = (TotalProtocol) o;

        if (dateCert != null ? !dateCert.equals(that.dateCert) : that.dateCert != null) return false;
        if (idTotalProtocol != null ? !idTotalProtocol.equals(that.idTotalProtocol) : that.idTotalProtocol != null)
            return false;
        if (number != null ? !number.equals(that.number) : that.number != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idTotalProtocol != null ? idTotalProtocol.hashCode() : 0;
        result = 31 * result + (number != null ? number.hashCode() : 0);
        result = 31 * result + (dateCert != null ? dateCert.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "TotalProtocol{" +
                "idTotalProtocol=" + idTotalProtocol +
                ", number='" + number + '\'' +
                ", dateCert=" + dateCert +
                ", journal=" + journal +
                '}';
    }
}
