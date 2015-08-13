package com.lvg.weldercenter.ui.entities;

import com.lvg.weldercenter.models.NDTDocument;
import com.lvg.weldercenter.models.PersonalProtocol;
import com.lvg.weldercenter.models.WeldPattern;
import com.lvg.weldercenter.ui.util.DateUtil;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Victor Levchenko LVG Corp. on 23.01.15.
 */
public class PersonalProtocolUI extends GenericEntityUI{

    private final SimpleStringProperty number = new SimpleStringProperty();
    private final SimpleObjectProperty<Date> datePeriodicalCert = new SimpleObjectProperty<Date>();
    private final SimpleStringProperty datePeriodicalCertFormat = new SimpleStringProperty();
    private final SimpleStringProperty attestType = new SimpleStringProperty();
    private final SimpleObjectProperty<WelderUI> welder = new SimpleObjectProperty<WelderUI>();
    private final SimpleObjectProperty<JournalUI> journal = new SimpleObjectProperty<JournalUI>();
    private final SimpleObjectProperty<ResolutionCertificationUI> resolutionCertification =
            new SimpleObjectProperty<ResolutionCertificationUI>();
    private final SimpleObjectProperty<TheoryTestUI> theoryTest =
            new SimpleObjectProperty<TheoryTestUI>();


    private SimpleListProperty<WeldPatternUI> weldPatterns = new SimpleListProperty<WeldPatternUI>();
    private SimpleListProperty<NDTDocumentUI> ndtDocuments = new SimpleListProperty<NDTDocumentUI>();


    public PersonalProtocolUI() {
        this.id.set(0);
        this.number.set("");
        this.datePeriodicalCert.set(DateUtil.getDate(DateUtil.DEFAULT_DATE));
        this.datePeriodicalCertFormat.set(DateUtil.format(this.datePeriodicalCert.get()));
        this.attestType.set("");
        this.welder.set(new WelderUI());
        this.journal.set(new JournalUI());
        this.resolutionCertification.set(new ResolutionCertificationUI());
        this.theoryTest.set(new TheoryTestUI());
        this.ndtDocuments.set(FXCollections.observableArrayList(new ArrayList<NDTDocumentUI>()));
        this.weldPatterns.set(FXCollections.observableArrayList(new ArrayList<WeldPatternUI>()));

    }

    public PersonalProtocolUI(WelderUI welder, JournalUI journal){
        this.id.set(0);
        this.number.set(journal.getNumber());
        this.datePeriodicalCert.set(journal.getDateEnd());
        this.datePeriodicalCertFormat.set(DateUtil.format(this.datePeriodicalCert.get()));
        this.attestType.set("");
        this.welder.set(welder);
        this.journal.set(journal);
        this.resolutionCertification.set(new ResolutionCertificationUI());
        this.theoryTest.set(new TheoryTestUI());
        this.ndtDocuments.set(FXCollections.observableArrayList(new ArrayList<NDTDocumentUI>()));
        this.weldPatterns.set(FXCollections.observableArrayList(new ArrayList<WeldPatternUI>()));
    }

    public PersonalProtocolUI(PersonalProtocol personalProtocol) {
        this.id.set(personalProtocol.getPersonalProtocolId());
        this.number.set(personalProtocol.getNumber());
        this.datePeriodicalCert.set(personalProtocol.getDatePeriodicalCert());
        this.datePeriodicalCertFormat.set(DateUtil.format(this.datePeriodicalCert.get()));
        this.attestType.set(personalProtocol.getAttestType());
        if (personalProtocol.getWelder() != null)
            this.welder.set(new WelderUI(personalProtocol.getWelder()));
        if (personalProtocol.getJournal() != null)
            this.journal.set(new JournalUI(personalProtocol.getJournal()));
        if (personalProtocol.getResolutionCertification() != null)
            this.resolutionCertification.set(new ResolutionCertificationUI(personalProtocol.getResolutionCertification()));
        if (personalProtocol.getTheoryTest() != null)
            this.theoryTest.set(new TheoryTestUI(personalProtocol.getTheoryTest()));

        this.ndtDocuments.set(FXCollections.observableArrayList(
                getNDTDocumentUIList(personalProtocol.getNdtDocuments())));
        this.weldPatterns.set(FXCollections.observableArrayList(getWeldPatternUIList(personalProtocol.getWeldPatterns())));

    }

    private List<WeldPatternUI> getWeldPatternUIList(List<WeldPattern> dbList){
        List<WeldPatternUI> result = new ArrayList<WeldPatternUI>();
        for(WeldPattern wp: dbList){
            WeldPatternUI weldPatternUI = new WeldPatternUI(wp);
            weldPatternUI.setPersonalProtocol(this);
            result.add(weldPatternUI);
        }
        return result;
    }

    private List<NDTDocumentUI> getNDTDocumentUIList(List<NDTDocument> dbList){
        List<NDTDocumentUI> result = new ArrayList<NDTDocumentUI>();
        for(NDTDocument doc: dbList){
            NDTDocumentUI docUI = new NDTDocumentUI(doc);
            result.add(docUI);
        }
        return result;
    }

    //Getters and Setters

    public String getNumber() {
        return number.get();
    }

    public SimpleStringProperty numberProperty() {
        return number;
    }

    public void setNumber(String number) {
        this.number.set(number);
    }

    public Date getDatePeriodicalCert() {
        return datePeriodicalCert.get();
    }

    public SimpleObjectProperty<Date> datePeriodicalCertProperty() {
        return datePeriodicalCert;
    }

    public void setDatePeriodicalCert(Date datePeriodicalCert) {
        this.datePeriodicalCert.set(datePeriodicalCert);
    }

    public String getDatePeriodicalCertFormat() {
        return datePeriodicalCertFormat.get();
    }

    public SimpleStringProperty datePeriodicalCertFormatProperty() {
        return datePeriodicalCertFormat;
    }

    public void setDatePeriodicalCertFormat(String datePeriodicalCertFormat) {
        this.datePeriodicalCertFormat.set(datePeriodicalCertFormat);
    }

    public String getAttestType() {
        return attestType.get();
    }

    public SimpleStringProperty attestTypeProperty() {
        return attestType;
    }

    public void setAttestType(String attestType) {
        this.attestType.set(attestType);
    }

    public WelderUI getWelder() {
        return welder.get();
    }

    public SimpleObjectProperty<WelderUI> welderProperty() {
        return welder;
    }

    public void setWelder(WelderUI welder) {
        this.welder.set(welder);
    }

    public JournalUI getJournal() {
        return journal.get();
    }

    public SimpleObjectProperty<JournalUI> journalProperty() {
        return journal;
    }

    public void setJournal(JournalUI journal) {
        this.journal.set(journal);
    }

    public ResolutionCertificationUI getResolutionCertification() {
        return resolutionCertification.get();
    }

    public SimpleObjectProperty<ResolutionCertificationUI> resolutionCertificationProperty() {
        return resolutionCertification;
    }

    public void setResolutionCertification(ResolutionCertificationUI resolutionCertification) {
        this.resolutionCertification.set(resolutionCertification);
    }

    public TheoryTestUI getTheoryTest() {
        return theoryTest.get();
    }

    public SimpleObjectProperty<TheoryTestUI> theoryTestProperty() {
        return theoryTest;
    }

    public void setTheoryTest(TheoryTestUI theoryTest) {
        this.theoryTest.set(theoryTest);
    }


    public ObservableList<NDTDocumentUI> getNdtDocuments() {
        return ndtDocuments.get();
    }

    public SimpleListProperty<NDTDocumentUI> ndtDocumentsProperty() {
        return ndtDocuments;
    }

    public void setNdtDocuments(ObservableList<NDTDocumentUI> ndtDocuments) {
        this.ndtDocuments.set(ndtDocuments);
    }

    public ObservableList<WeldPatternUI> getWeldPatterns() {
        return weldPatterns.get();
    }

    public SimpleListProperty<WeldPatternUI> weldPatternsProperty() {
        return weldPatterns;
    }

    public void setWeldPatterns(ObservableList<WeldPatternUI> weldPatterns) {
        this.weldPatterns.set(weldPatterns);
    }

    @Override
    public String toString() {
        if(this.welder==null)
            return "empty PersonalProtocol";
        return  welder.get().getSurname()+" "+
                welder.get().getName()+" "+
                welder.get().getSecname()+" ";
    }
}
