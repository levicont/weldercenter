package com.lvg.weldercenter.ui.entities;

import com.lvg.weldercenter.models.*;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;


/**
 * Created by Victor Levchenko LVG Corp. on 23.01.15.
 */
public class WeldPatternUI extends GenericEntityUI {

    private final SimpleDoubleProperty thickness  = new SimpleDoubleProperty();
    private final SimpleDoubleProperty diameter = new SimpleDoubleProperty();
    private final SimpleStringProperty mark = new SimpleStringProperty();
    private final SimpleBooleanProperty isHeating = new SimpleBooleanProperty();
    private final SimpleBooleanProperty isHeatTreatment = new SimpleBooleanProperty();

    private final SimpleObjectProperty<PersonalProtocolUI> personalProtocol = new SimpleObjectProperty<PersonalProtocolUI>();
    private final SimpleObjectProperty<WeldMethodUI> weldMethod = new SimpleObjectProperty<WeldMethodUI>();
    private final SimpleObjectProperty<ElectrodeUI> electrode = new SimpleObjectProperty<ElectrodeUI>();
    private final SimpleObjectProperty<WeldWireUI> weldWire = new SimpleObjectProperty<WeldWireUI>();
    private final SimpleObjectProperty<WeldGasUI> weldGas = new SimpleObjectProperty<WeldGasUI>();
    private final SimpleObjectProperty<WeldDetailUI> weldDetail = new SimpleObjectProperty<WeldDetailUI>();
    private final SimpleObjectProperty<SteelTypeUI> steelType = new SimpleObjectProperty<SteelTypeUI>();
    private final SimpleObjectProperty<WeldPositionUI> weldPosition = new SimpleObjectProperty<WeldPositionUI>();

    private final SimpleObjectProperty<RadiationTestUI> radiationTest = new SimpleObjectProperty<RadiationTestUI>();
    private final SimpleObjectProperty<VisualTestUI> visualTest = new SimpleObjectProperty<VisualTestUI>();
    private final SimpleObjectProperty<MechanicalTestUI> mechanicalTest = new SimpleObjectProperty<MechanicalTestUI>();

    public WeldPatternUI(){
        this.id.set(0);
        this.thickness.set(0);
        this.diameter.set(0);
        this.mark.set("");
        this.isHeating.set(false);
        this.isHeatTreatment.set(false);

        this.personalProtocol.set(new PersonalProtocolUI());
        this.weldMethod.set(new WeldMethodUI());
        this.electrode.set(new ElectrodeUI());
        this.weldWire.set(new WeldWireUI());
        this.weldGas.set(new WeldGasUI());
        this.weldDetail.set(new WeldDetailUI());
        this.steelType.set(new SteelTypeUI());
        this.weldPosition.set(new WeldPositionUI());

        this.radiationTest.set(new RadiationTestUI());
        this.visualTest.set(new VisualTestUI());
        this.mechanicalTest.set(new MechanicalTestUI());
    }

    public WeldPatternUI(WeldPattern weldPattern){
        this.id.set(weldPattern.getWeldPatternId());
        this.thickness.set(weldPattern.getThickness());
        this.diameter.set(weldPattern.getDiameter());
        this.mark.set(weldPattern.getMark());
        this.isHeating.set(weldPattern.getIsHeating());
        this.isHeatTreatment.set(weldPattern.getIsHeatTreatment());

        this.personalProtocol.set(new PersonalProtocolUI(weldPattern.getPersonalProtocol()));
        this.weldMethod.set(new WeldMethodUI(weldPattern.getWeldMethod()));
        this.electrode.set(new ElectrodeUI(weldPattern.getElectrode()));
        this.weldWire.set(new WeldWireUI(weldPattern.getWeldWire()));
        this.weldGas.set(new WeldGasUI(weldPattern.getWeldGas()));
        this.weldDetail.set(new WeldDetailUI(weldPattern.getWeldDetail()));
        this.steelType.set(new SteelTypeUI(weldPattern.getSteelType()));
        this.weldPosition.set(new WeldPositionUI(weldPattern.getWeldPosition()));

        this.radiationTest.set(new RadiationTestUI(weldPattern.getRadiationTest()));
        this.visualTest.set(new VisualTestUI(weldPattern.getVisualTest()));
        this.mechanicalTest.set(new MechanicalTestUI(weldPattern.getMechanicalTest()));
    }

    //Getters and Setters


    public double getThickness() {
        return thickness.get();
    }

    public SimpleDoubleProperty thicknessProperty() {
        return thickness;
    }

    public void setThickness(double thickness) {
        this.thickness.set(thickness);
    }

    public double getDiameter() {
        return diameter.get();
    }

    public SimpleDoubleProperty diameterProperty() {
        return diameter;
    }

    public void setDiameter(double diameter) {
        this.diameter.set(diameter);
    }

    public String getMark() {
        return mark.get();
    }

    public SimpleStringProperty markProperty() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark.set(mark);
    }

    public boolean getIsHeating() {
        return isHeating.get();
    }

    public SimpleBooleanProperty isHeatingProperty() {
        return isHeating;
    }

    public void setIsHeating(boolean isHeating) {
        this.isHeating.set(isHeating);
    }

    public boolean getIsHeatTreatment() {
        return isHeatTreatment.get();
    }

    public SimpleBooleanProperty isHeatTreatmentProperty() {
        return isHeatTreatment;
    }

    public void setIsHeatTreatment(boolean isHeatTreatment) {
        this.isHeatTreatment.set(isHeatTreatment);
    }

    public PersonalProtocolUI getPersonalProtocol() {
        return personalProtocol.get();
    }

    public SimpleObjectProperty<PersonalProtocolUI> personalProtocolProperty() {
        return personalProtocol;
    }

    public void setPersonalProtocol(PersonalProtocolUI personalProtocol) {
        this.personalProtocol.set(personalProtocol);
    }

    public WeldMethodUI getWeldMethod() {
        return weldMethod.get();
    }

    public SimpleObjectProperty<WeldMethodUI> weldMethodProperty() {
        return weldMethod;
    }

    public void setWeldMethod(WeldMethodUI weldMethod) {
        this.weldMethod.set(weldMethod);
    }

    public ElectrodeUI getElectrode() {
        return electrode.get();
    }

    public SimpleObjectProperty<ElectrodeUI> electrodeProperty() {
        return electrode;
    }

    public void setElectrode(ElectrodeUI electrode) {
        this.electrode.set(electrode);
    }

    public WeldWireUI getWeldWire() {
        return weldWire.get();
    }

    public SimpleObjectProperty<WeldWireUI> weldWireProperty() {
        return weldWire;
    }

    public void setWeldWire(WeldWireUI weldWire) {
        this.weldWire.set(weldWire);
    }

    public WeldGasUI getWeldGas() {
        return weldGas.get();
    }

    public SimpleObjectProperty<WeldGasUI> weldGasProperty() {
        return weldGas;
    }

    public void setWeldGas(WeldGasUI weldGas) {
        this.weldGas.set(weldGas);
    }

    public WeldDetailUI getWeldDetail() {
        return weldDetail.get();
    }

    public SimpleObjectProperty<WeldDetailUI> weldDetailProperty() {
        return weldDetail;
    }

    public void setWeldDetail(WeldDetailUI weldDetail) {
        this.weldDetail.set(weldDetail);
    }

    public SteelTypeUI getSteelType() {
        return steelType.get();
    }

    public SimpleObjectProperty<SteelTypeUI> steelTypeProperty() {
        return steelType;
    }

    public void setSteelType(SteelTypeUI steelType) {
        this.steelType.set(steelType);
    }

    public WeldPositionUI getWeldPosition() {
        return weldPosition.get();
    }

    public SimpleObjectProperty<WeldPositionUI> weldPositionProperty() {
        return weldPosition;
    }

    public void setWeldPosition(WeldPositionUI weldPosition) {
        this.weldPosition.set(weldPosition);
    }

    public RadiationTestUI getRadiationTest() {
        return radiationTest.get();
    }

    public SimpleObjectProperty<RadiationTestUI> radiationTestProperty() {
        return radiationTest;
    }

    public void setRadiationTest(RadiationTestUI radiationTest) {
        this.radiationTest.set(radiationTest);
    }

    public VisualTestUI getVisualTest() {
        return visualTest.get();
    }

    public SimpleObjectProperty<VisualTestUI> visualTestProperty() {
        return visualTest;
    }

    public void setVisualTest(VisualTestUI visualTest) {
        this.visualTest.set(visualTest);
    }

    public MechanicalTestUI getMechanicalTest() {
        return mechanicalTest.get();
    }

    public SimpleObjectProperty<MechanicalTestUI> mechanicalTestProperty() {
        return mechanicalTest;
    }

    public void setMechanicalTest(MechanicalTestUI mechanicalTest) {
        this.mechanicalTest.set(mechanicalTest);
    }
}
