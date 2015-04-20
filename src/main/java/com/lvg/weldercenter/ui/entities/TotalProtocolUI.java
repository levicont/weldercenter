package com.lvg.weldercenter.ui.entities;

import com.lvg.weldercenter.models.CommissionCertification;
import com.lvg.weldercenter.models.Journal;
import com.lvg.weldercenter.models.TotalProtocol;
import com.lvg.weldercenter.ui.util.DateUtil;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

import java.util.Date;

/**
 * Created by Victor Levchenko LVG Corp. on 23.01.15.
 */
public class TotalProtocolUI extends GenericEntityUI {

    private final SimpleStringProperty number = new SimpleStringProperty();
    private final SimpleObjectProperty<Date> dateCert = new SimpleObjectProperty<Date>();
    private final SimpleStringProperty dateCertFormat = new SimpleStringProperty();
    private final SimpleObjectProperty<JournalUI> journal = new SimpleObjectProperty<JournalUI>();
    private final SimpleObjectProperty<CommissionCertificationUI> commissionCertification =
                            new SimpleObjectProperty<CommissionCertificationUI>();

    public TotalProtocolUI(TotalProtocol totalProtocol){
        this.id.set(totalProtocol.getIdTotalProtocol());
        this.number.set(totalProtocol.getNumber());
        this.dateCert.set(totalProtocol.getDateCert());
        this.dateCertFormat.set(DateUtil.format(dateCert.get()));
        this.journal.set(new JournalUI(totalProtocol.getJournal()));
        if (totalProtocol.getCommissionCertification()!=null)
            this.commissionCertification.set(
                    new CommissionCertificationUI(totalProtocol.getCommissionCertification()));
        else this.commissionCertification.set(new CommissionCertificationUI());
    }

    public TotalProtocolUI(Journal journal){
        this(new JournalUI(journal));
    }

    public TotalProtocolUI(JournalUI journalUI){
        this.id.set(0);
        this.number.set(journalUI.getNumber());
        //TODO get date from personal protocols
        this.dateCert.set(journalUI.getDateEnd());
        this.dateCertFormat.set(journalUI.getDateEndFormat());
        this.journal.set(journalUI);

    }


    //Getters and Setters


    public JournalUI getJournal() {
        return journal.get();
    }

    public SimpleObjectProperty<JournalUI> journalProperty() {
        return journal;
    }

    public void setJournal(JournalUI journal) {
        this.journal.set(journal);
    }

    public String getNumber() {
        return number.get();
    }

    public SimpleStringProperty numberProperty() {
        return number;
    }

    public void setNumber(String number) {
        this.number.set(number);
    }

    public Date getDateCert() {
        return dateCert.get();
    }

    public SimpleObjectProperty<Date> dateCertProperty() {
        return dateCert;
    }

    public void setDateCert(Date dateCert) {
        this.dateCert.set(dateCert);
    }

    public String getDateCertFormat() {
        return dateCertFormat.get();
    }

    public SimpleStringProperty dateCertFormatProperty() {
        return dateCertFormat;
    }

    public void setDateCertFormat(String dateCertFormat) {
        this.dateCertFormat.set(dateCertFormat);
    }

    public CommissionCertificationUI getCommissionCertification() {
        return commissionCertification.get();
    }

    public SimpleObjectProperty<CommissionCertificationUI> commissionCertificationProperty() {
        return commissionCertification;
    }

    public void setCommissionCertification(CommissionCertificationUI commissionCertification) {
        this.commissionCertification.set(commissionCertification);
    }

    @Override
    public String toString() {
        return number.get()+" ( от "+dateCertFormat.get()+"г. )";
    }
}
