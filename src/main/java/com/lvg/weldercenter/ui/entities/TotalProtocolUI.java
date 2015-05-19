package com.lvg.weldercenter.ui.entities;

import com.lvg.weldercenter.models.CommissionCertification;
import com.lvg.weldercenter.models.Journal;
import com.lvg.weldercenter.models.TotalProtocol;
import com.lvg.weldercenter.ui.util.DateUtil;
import javafx.beans.property.SimpleMapProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Victor Levchenko LVG Corp. on 23.01.15.
 */
public class TotalProtocolUI extends GenericEntityUI {
    private final String KEY_ID = "ID";
    private final String KEY_NUMBER = "NUMBER";
    private final String KEY_DATE_CERT = "DATE_CERT";
    private final String KEY_NEXT_DATE_CERT = "NEXT_DATE_CERT";
    private final String KEY_DATE_CERT_FORMAT = "DATE_CERT_FORMAT";
    private final String KEY_COMMISSION_CERTIFICATION_HEAD = "COMMISSION_CERTIFICATION_HEAD";
    private final String KEY_COMMISSION_CERTIFICATION_WELD_SPEC = "COMMISSION_CERTIFICATION_WELD_SPEC";
    private final String KEY_COMMISSION_CERTIFICATION_NDT_SPEC = "COMMISSION_CERTIFICATION_NDT_SPEC";
    private final String KEY_COMMISSION_CERTIFICATION_SAFETY_SPEC = "COMMISSION_CERTIFICATION_SAFETY_SPEC";
    private final String KEY_COMMISSION_CERTIFICATION_HEAD_INV = "COMMISSION_CERTIFICATION_HEAD_INV";
    private final String KEY_COMMISSION_CERTIFICATION_WELD_SPEC_INV = "COMMISSION_CERTIFICATION_WELD_SPEC_INV";
    private final String KEY_COMMISSION_CERTIFICATION_NDT_SPEC_INV = "COMMISSION_CERTIFICATION_NDT_SPEC_INV";
    private final String KEY_COMMISSION_CERTIFICATION_SAFETY_SPEC_INV = "COMMISSION_CERTIFICATION_SAFETY_SPEC_INV";

    private final SimpleStringProperty number = new SimpleStringProperty();
    private final SimpleObjectProperty<Date> dateCert = new SimpleObjectProperty<Date>();
    private final SimpleStringProperty dateCertFormat = new SimpleStringProperty();
    private final SimpleObjectProperty<JournalUI> journal = new SimpleObjectProperty<JournalUI>();
    private final SimpleObjectProperty<CommissionCertificationUI> commissionCertification =
                            new SimpleObjectProperty<CommissionCertificationUI>();
    private final Map<String, Object> parameters = new HashMap<String, Object>(){{
        put(KEY_ID,null);
        put(KEY_NUMBER,null);
        put(KEY_DATE_CERT, null);
        put(KEY_NEXT_DATE_CERT, null);
        put(KEY_DATE_CERT_FORMAT, null);
        put(KEY_COMMISSION_CERTIFICATION_HEAD, null);
        put(KEY_COMMISSION_CERTIFICATION_WELD_SPEC, null);
        put(KEY_COMMISSION_CERTIFICATION_NDT_SPEC, null);
        put(KEY_COMMISSION_CERTIFICATION_SAFETY_SPEC, null);
        put(KEY_COMMISSION_CERTIFICATION_HEAD_INV, null);
        put(KEY_COMMISSION_CERTIFICATION_WELD_SPEC_INV, null);
        put(KEY_COMMISSION_CERTIFICATION_NDT_SPEC_INV, null);
        put(KEY_COMMISSION_CERTIFICATION_SAFETY_SPEC_INV, null);
    }};

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
        fillParameters();
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
        fillParameters();

    }
    private void fillParameters(){
        parameters.replace(KEY_ID,this.getId());
        parameters.replace(KEY_NUMBER, this.getNumber());
        parameters.replace(KEY_DATE_CERT, this.getDateCert());
        parameters.replace(KEY_NEXT_DATE_CERT, this.getDateCert()!=null?
                DateUtil.format(DateUtil.getLocalDate(this.getDateCert()).plusYears(2)):"");
        parameters.replace(KEY_DATE_CERT_FORMAT, this.getDateCertFormat());

        parameters.replace(KEY_COMMISSION_CERTIFICATION_HEAD,
                this.getCommissionCertification().getHead()!=null?
                        this.getCommissionCertification().getHead().getFormatTeacherFullName("SUR-nn-sec"):"");
        parameters.replace(KEY_COMMISSION_CERTIFICATION_NDT_SPEC,
                this.getCommissionCertification().getNdtSpecialist()!=null?
                        this.getCommissionCertification().getNdtSpecialist().getFormatTeacherFullName("SUR-nn-sec"):"");
        parameters.replace(KEY_COMMISSION_CERTIFICATION_WELD_SPEC,
                this.getCommissionCertification().getWeldSpecialist()!=null?
                        this.getCommissionCertification().getWeldSpecialist().getFormatTeacherFullName("SUR-nn-sec"):"");
        parameters.replace(KEY_COMMISSION_CERTIFICATION_SAFETY_SPEC,
                this.getCommissionCertification().getSafetySpecialist()!=null?
                        this.getCommissionCertification().getSafetySpecialist().getFormatTeacherFullName("SUR-nn-sec"):"");
        parameters.replace(KEY_COMMISSION_CERTIFICATION_HEAD_INV,
                this.getCommissionCertification().getHead()!=null?
                        this.getCommissionCertification().getHead().getFormatTeacherFullName("nn-sec-SUR"):"");
        parameters.replace(KEY_COMMISSION_CERTIFICATION_NDT_SPEC_INV,
                this.getCommissionCertification().getNdtSpecialist()!=null?
                        this.getCommissionCertification().getNdtSpecialist().getFormatTeacherFullName("nn-sec-SUR"):"");
        parameters.replace(KEY_COMMISSION_CERTIFICATION_WELD_SPEC_INV,
                this.getCommissionCertification().getWeldSpecialist()!=null?
                        this.getCommissionCertification().getWeldSpecialist().getFormatTeacherFullName("nn-sec-SUR"):"");
        parameters.replace(KEY_COMMISSION_CERTIFICATION_SAFETY_SPEC_INV,
                this.getCommissionCertification().getSafetySpecialist()!=null?
                        this.getCommissionCertification().getSafetySpecialist().getFormatTeacherFullName("nn-sec-SUR"):"");

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

    public Map<String, Object> getParameters() {
        return parameters;
    }

    @Override
    public String toString() {
        return number.get()+" ( от "+dateCertFormat.get()+"г. )";
    }
}
