package com.lvg.weldercenter.ui.entities;

import com.lvg.weldercenter.models.*;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Victor Levchenko LVG Corp. on 23.01.15.
 */
public class WeldPatternUI extends GenericEntityUI {
    private final String THICKNESS_MARK = "Толщ ";
    private final String DIAMETER_MARK = "Диам ";
    private final String MEASURE_MARK = " мм ";

    private final SimpleDoubleProperty thickness  = new SimpleDoubleProperty();
    private final SimpleDoubleProperty diameter = new SimpleDoubleProperty();
    private final SimpleStringProperty mark = new SimpleStringProperty();
    private final SimpleBooleanProperty isHeating = new SimpleBooleanProperty();
    private final SimpleBooleanProperty isHeatTreatment = new SimpleBooleanProperty();
    private final SimpleStringProperty typeName = new SimpleStringProperty();
    private final SimpleStringProperty size = new SimpleStringProperty();
    private final SimpleStringProperty weldMethodName = new SimpleStringProperty();

    private final SimpleObjectProperty<PersonalProtocolUI> personalProtocol = new SimpleObjectProperty<PersonalProtocolUI>();
    private final SimpleObjectProperty<WeldMethodUI> weldMethod = new SimpleObjectProperty<WeldMethodUI>();
    private final SimpleObjectProperty<ElectrodeUI> electrode = new SimpleObjectProperty<ElectrodeUI>();
    private final SimpleObjectProperty<WeldWireUI> weldWire = new SimpleObjectProperty<WeldWireUI>();
    private final SimpleObjectProperty<WeldGasUI> weldGas = new SimpleObjectProperty<WeldGasUI>();
    private final SimpleObjectProperty<WeldDetailUI> weldDetail = new SimpleObjectProperty<WeldDetailUI>();
    private final SimpleObjectProperty<SteelTypeUI> steelType = new SimpleObjectProperty<SteelTypeUI>();

    private final SimpleObjectProperty<RadiationTestUI> radiationTest = new SimpleObjectProperty<RadiationTestUI>();
    private final SimpleObjectProperty<VisualTestUI> visualTest = new SimpleObjectProperty<VisualTestUI>();
    private final SimpleObjectProperty<MechanicalTestUI> mechanicalTest = new SimpleObjectProperty<MechanicalTestUI>();

    private final SimpleListProperty<WeldPositionUI> weldPositions = new SimpleListProperty<WeldPositionUI>(
            FXCollections.observableArrayList(new ArrayList<WeldPositionUI>()));

    public WeldPatternUI(){
        this.id.set(0);
        this.thickness.set(0);
        this.diameter.set(0);
        this.mark.set("");
        this.isHeating.set(false);
        this.isHeatTreatment.set(false);

        this.size.set(getSizeFromThicknesAndDiameter(this.diameter.get(), this.thickness.get()));

        this.personalProtocol.set(new PersonalProtocolUI());
        this.weldMethod.set(new WeldMethodUI());
        this.weldMethodName.set(this.weldMethod.get().getName());
        this.electrode.set(new ElectrodeUI());
        this.weldWire.set(new WeldWireUI());
        this.weldGas.set(new WeldGasUI());
        this.weldDetail.set(new WeldDetailUI());
        this.typeName.set(this.weldDetail.get().getType());

        this.steelType.set(new SteelTypeUI());


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

        this.size.set(getSizeFromThicknesAndDiameter(this.diameter.get(), this.thickness.get()));

        this.personalProtocol.set(new PersonalProtocolUI());
        if (weldPattern.getWeldMethod()!=null) {
            this.weldMethod.set(new WeldMethodUI(weldPattern.getWeldMethod()));
            this.weldMethodName.set(this.weldMethod.get().getName());
        }
        if (weldPattern.getElectrode()!= null)
            this.electrode.set(new ElectrodeUI(weldPattern.getElectrode()));
        if (weldPattern.getWeldWire() != null)
            this.weldWire.set(new WeldWireUI(weldPattern.getWeldWire()));
        if (weldPattern.getWeldGas() != null)
            this.weldGas.set(new WeldGasUI(weldPattern.getWeldGas()));
        if (weldPattern.getWeldDetail() != null){
            this.weldDetail.set(new WeldDetailUI(weldPattern.getWeldDetail()));
            this.typeName.set(this.weldDetail.get().getType());
        }
        if (weldPattern.getSteelType() != null)
            this.steelType.set(new SteelTypeUI(weldPattern.getSteelType()));

        if (weldPattern.getRadiationTest() != null)
            this.radiationTest.set(new RadiationTestUI(weldPattern.getRadiationTest()));
        if (weldPattern.getVisualTest() != null)
            this.visualTest.set(new VisualTestUI(weldPattern.getVisualTest()));
        if (weldPattern.getMechanicalTest() != null)
            this.mechanicalTest.set(new MechanicalTestUI(weldPattern.getMechanicalTest()));
        this.weldPositions.set(FXCollections.observableArrayList(getWeldPositionsUIFromWeldPosition(weldPattern.getWeldPositions())));
    }

    public WeldPatternUI(PersonalProtocolUI personalProtocolUI){
        this.id.set(0);
        this.thickness.set(0);
        this.diameter.set(0);
        this.mark.set("");
        this.isHeating.set(false);
        this.isHeatTreatment.set(false);
        this.size.set(getSizeFromThicknesAndDiameter(this.diameter.get(), this.thickness.get()));

        this.personalProtocol.set(personalProtocolUI);
        this.weldMethod.set(new WeldMethodUI());
        this.weldMethodName.set(this.weldMethod.get().getName());
        this.electrode.set(new ElectrodeUI());
        this.weldWire.set(new WeldWireUI());
        this.weldGas.set(new WeldGasUI());
        this.weldDetail.set(new WeldDetailUI());
        this.typeName.set(this.weldDetail.get().getType());
        this.steelType.set(new SteelTypeUI());

        this.radiationTest.set(new RadiationTestUI());
        this.visualTest.set(new VisualTestUI());
        this.mechanicalTest.set(new MechanicalTestUI());
    }

    //Getters and Setters

    private String getSizeFromThicknesAndDiameter(double diameter, double thickness){
        return DIAMETER_MARK+diameter+MEASURE_MARK+"\n"+THICKNESS_MARK+thickness+MEASURE_MARK;
    }

    private List<WeldPositionUI> getWeldPositionsUIFromWeldPosition(List<WeldPosition> weldPositionList){
        List<WeldPositionUI> result = new ArrayList<WeldPositionUI>();
        for(WeldPosition wp : weldPositionList){
            result.add(new WeldPositionUI(wp));
        }
        return result;
    }


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

    public String getTypeName() {
        return typeName.get();
    }

    public SimpleStringProperty typeNameProperty() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName.set(typeName);
    }

    public String getSize() {
        return size.get();
    }

    public SimpleStringProperty sizeProperty() {
        return size;
    }

    public void setSize(String size) {
        this.size.set(size);
    }

    public String getWeldMethodName() {
        return weldMethodName.get();
    }

    public SimpleStringProperty weldMethodNameProperty() {
        return weldMethodName;
    }

    public void setWeldMethodName(String weldMethodName) {
        this.weldMethodName.set(weldMethodName);
    }

    public ObservableList<WeldPositionUI> getWeldPositions() {
        return weldPositions.get();
    }

    public SimpleListProperty<WeldPositionUI> weldPositionsProperty() {
        return weldPositions;
    }

    public void setWeldPositions(ObservableList<WeldPositionUI> weldPositions) {
        this.weldPositions.set(weldPositions);

    }

    @Override
    public String toString() {
        return "WeldPatternUI{" +
                "THICKNESS_MARK='" + THICKNESS_MARK + '\'' +
                ", DIAMETER_MARK='" + DIAMETER_MARK + '\'' +
                ", MEASURE_MARK='" + MEASURE_MARK + '\'' +
                ", thickness=" + thickness +
                ", diameter=" + diameter +
                ", mark=" + mark +
                ", isHeating=" + isHeating +
                ", isHeatTreatment=" + isHeatTreatment +
                ", typeName=" + typeName +
                ", size=" + size +
                ", weldMethodName=" + weldMethodName +
                ", personalProtocol=" + personalProtocol +
                ", weldMethod=" + weldMethod +
                ", electrode=" + electrode +
                ", weldWire=" + weldWire +
                ", weldGas=" + weldGas +
                ", weldDetail=" + weldDetail +
                ", steelType=" + steelType +
                ", radiationTest=" + radiationTest +
                ", visualTest=" + visualTest +
                ", mechanicalTest=" + mechanicalTest +
                ", weldPositions=" + weldPositions +
                '}';
    }
}
