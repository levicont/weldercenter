package com.lvg.weldercenter.models;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Victor Levchenko LVG Corp. on 20.10.14.
 */
@Entity
@Table(name = "weld_pattern")
public class WeldPattern implements Serializable{

    private static final long serialVersionUID = -1618937671254365956L;
    private Long weldPatternId;
    private Double thickness;
    private Double diameter;
    private String mark;
    private Boolean isHeating;
    private Boolean isHeatTreatment;

    private PersonalProtocol personalProtocol;
    private WeldMethod weldMethod;
    private Electrode electrode;
    private WeldWire weldWire;
    private WeldGas weldGas;
    private WeldDetail weldDetail;
    private SteelType steelType;
    private WeldPosition weldPosition;

    private RadiationTest radiationTest;
    private VisualTest visualTest;
    private MechanicalTest mechanicalTest;



    //Getters and Setters

    @Id
    @GeneratedValue
    @Column(name = "id_weld_pattern")
    public Long getWeldPatternId() {
        return weldPatternId;
    }

    public void setWeldPatternId(Long weldPatternId) {
        this.weldPatternId = weldPatternId;
    }

    public Double getThickness() {
        return thickness;
    }

    public void setThickness(Double thickness) {
        this.thickness = thickness;
    }

    public Double getDiameter() {
        return diameter;
    }

    public void setDiameter(Double diameter) {
        this.diameter = diameter;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    @Column(name = "is_heating")
    public Boolean getIsHeating() {
        return isHeating;
    }

    public void setIsHeating(Boolean isHeating) {
        this.isHeating = isHeating;
    }

    @Column(name = "is_heat_treatment")
    public Boolean getIsHeatTreatment() {
        return isHeatTreatment;
    }

    public void setIsHeatTreatment(Boolean isHeatTreatment) {
        this.isHeatTreatment = isHeatTreatment;
    }

    @ManyToOne(targetEntity = PersonalProtocol.class)
    @JoinColumn(name = "id_personal_protocol")
    public PersonalProtocol getPersonalProtocol() {
        return personalProtocol;
    }

    public void setPersonalProtocol(PersonalProtocol personalProtocol) {
        this.personalProtocol = personalProtocol;
    }

    @ManyToOne(targetEntity = WeldMethod.class)
    @JoinColumn(name = "id_weld_method")
    public WeldMethod getWeldMethod() {
        return weldMethod;
    }

    public void setWeldMethod(WeldMethod weldMethod) {
        this.weldMethod = weldMethod;
    }

    @ManyToOne(targetEntity = Electrode.class)
    @JoinColumn(name = "id_electrode")
    public Electrode getElectrode() {
        return electrode;
    }

    public void setElectrode(Electrode electrode) {
        this.electrode = electrode;
    }

    @ManyToOne(targetEntity = WeldWire.class)
    @JoinColumn(name = "id_weld_wire")
    public WeldWire getWeldWire() {
        return weldWire;
    }

    public void setWeldWire(WeldWire weldWire) {
        this.weldWire = weldWire;
    }

    @ManyToOne(targetEntity = WeldGas.class)
    @JoinColumn(name = "id_weld_gas")
    public WeldGas getWeldGas() {
        return weldGas;
    }

    public void setWeldGas(WeldGas weldGas) {
        this.weldGas = weldGas;
    }

    @ManyToOne(targetEntity = WeldDetail.class)
    @JoinColumn(name = "id_weld_detail")
    public WeldDetail getWeldDetail() {
        return weldDetail;
    }

    public void setWeldDetail(WeldDetail weldDetail) {
        this.weldDetail = weldDetail;
    }

    @ManyToOne(targetEntity = SteelType.class)
    @JoinColumn(name = "id_steel_type")
    public SteelType getSteelType() {
        return steelType;
    }

    public void setSteelType(SteelType steelType) {
        this.steelType = steelType;
    }

    @ManyToOne(targetEntity = WeldPosition.class)
    @JoinColumn(name = "id_weld_position")
    public WeldPosition getWeldPosition() {
        return weldPosition;
    }

    public void setWeldPosition(WeldPosition weldPosition) {
        this.weldPosition = weldPosition;
    }

    @OneToOne
    @JoinColumn(name = "id_radiation_test")
    public RadiationTest getRadiationTest() {
        return radiationTest;
    }

    public void setRadiationTest(RadiationTest radiationTest) {
        this.radiationTest = radiationTest;
    }

    @OneToOne
    @JoinColumn(name = "id_visual_test")
    public VisualTest getVisualTest() {
        return visualTest;
    }

    public void setVisualTest(VisualTest visualTest) {
        this.visualTest = visualTest;
    }

    @OneToOne
    @JoinColumn(name = "id_mechanical_test")
    public MechanicalTest getMechanicalTest() {
        return mechanicalTest;
    }

    public void setMechanicalTest(MechanicalTest mechanicalTest) {
        this.mechanicalTest = mechanicalTest;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WeldPattern that = (WeldPattern) o;

        if (!mark.equals(that.mark)) return false;
        if (!weldPatternId.equals(that.weldPatternId)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = weldPatternId.hashCode();
        result = 31 * result + mark.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "WeldPattern{" +
                "weldPatternId=" + weldPatternId +
                ", thickness=" + thickness +
                ", diameter=" + diameter +
                ", mark='" + mark + '\'' +
                ", weldMethod=" + weldMethod +
                ", personalProtocol=" + personalProtocol +
                '}';
    }
}
