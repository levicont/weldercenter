package com.lvg.weldercenter.ui.control;

import com.lvg.weldercenter.models.*;
import com.lvg.weldercenter.services.*;
import com.lvg.weldercenter.spring.factories.ServiceFactory;
import com.lvg.weldercenter.spring.factories.ServiceUIFactory;
import com.lvg.weldercenter.ui.entities.*;
import com.lvg.weldercenter.ui.servicesui.*;
import com.lvg.weldercenter.ui.util.EventFXUtil;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import org.apache.log4j.Logger;
import org.controlsfx.control.action.Action;
import org.controlsfx.dialog.*;
import org.controlsfx.dialog.Dialog;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Victor on 28.07.2015.
 */
public class PropertiesController extends GenericController {
    public final String TITLE_PANE_WELD_DETAIL = "TITLE_PANE_WELD_DETAIL";
    public final String TITLE_PANE_DIAMETER = "TITLE_PANE_DIAMETER";
    public final String TITLE_PANE_THICKNESS = "TITLE_PANE_THICKNESS";
    public final String TITLE_PANE_STEEL_TYPE = "TITLE_PANE_STEEL_TYPE";
    public final String TITLE_PANE_WELD_METHOD = "TITLE_PANE_WELD_METHOD";
    public final String TITLE_PANE_ELECTRODE = "TITLE_PANE_ELECTRODE";
    public final String TITLE_PANE_WELD_WIRE = "TITLE_PANE_WELD_WIRE";
    public final String TITLE_PANE_WELD_GAS = "TITLE_PANE_WELD_GAS";
    public final String TITLE_PANE_WELD_POSITION = "TITLE_PANE_WELD_POSITION";
    public final String TITLE_PANE_EDUCATION = "TITLE_PANE_EDUCATION";
    public final String TITLE_PANE_QUALIFICATION = "TITLE_PANE_QUALIFICATION";
    public final String TITLE_PANE_JOB = "TITLE_PANE_JOB";

    private final Logger LOGGER = Logger.getLogger(PropertiesController.class);
    private final String STYLE_NOT_EDITABLE_BACKGROUND = "-fx-background-color: #deefff";
    private final String STYLE_NOT_EDIT_COMBOBOX = "-fx-opacity: 1; -fx-background-color: #deefff";
    private final String STYLE_TAB_BACKGROUND = "-fx-background-color: linear-gradient(to TOP, #f9eee0, #f9e5cc)";

    private OrganizationService organizationService = ServiceFactory.getOrganizationService();
    private WeldDetailService weldDetailService = ServiceFactory.getWeldDetailService();
    private PatternDiameterService patternDiameterService = ServiceFactory.getPatternDiameterService();
    private PatternThicknessService patternThicknessService = ServiceFactory.getPatternThicknessService();
    private SteelTypeService steelTypeService = ServiceFactory.getSteelTypeService();
    private SteelGroupService steelGroupService = ServiceFactory.getSteelGroupService();
    private WeldMethodService weldMethodService = ServiceFactory.getWeldMethodService();
    private ElectrodeService electrodeService = ServiceFactory.getElectrodeService();
    private WeldWireService weldWireService = ServiceFactory.getWeldWireService();
    private WeldGasService weldGasService = ServiceFactory.getWeldGasService();
    private WeldPositionService weldPositionService = ServiceFactory.getWeldPositionService();
    private EducationService educationService = ServiceFactory.getEducationService();
    private QualificationService qualificationService = ServiceFactory.getQualificationService();
    private JobService jobService = ServiceFactory.getJobService();
    private CommissionCertificationService commissionCertificationService
            = ServiceFactory.getCommissionCertificationService();
    private TeacherService teacherService = ServiceFactory.getTeacherService();

    private OrganizationServiceUI organizationServiceUI = ServiceUIFactory.getOrganizationServiceUI();
    private WeldDetailServiceUI weldDetailServiceUI = ServiceUIFactory.getWeldDetailServiceUI();
    private PatternDiameterServiceUI patternDiameterServiceUI = ServiceUIFactory.getPatternDiameterServiceUI();
    private PatternThicknessServiceUI patternThicknessServiceUI = ServiceUIFactory.getPatternThicknessServiceUI();
    private SteelTypeServiceUI steelTypeServiceUI = ServiceUIFactory.getSteelTypeServiceUI();
    private SteelGroupServiceUI steelGroupServiceUI = ServiceUIFactory.getSteelGroupServiceUI();
    private WeldMethodServiceUI weldMethodServiceUI = ServiceUIFactory.getWeldMethodServiceUI();
    private ElectrodeServiceUI electrodeServiceUI = ServiceUIFactory.getElectrodeServiceUI();
    private WeldWireServiceUI weldWireServiceUI = ServiceUIFactory.getWeldWireServiceUI();
    private WeldGasServiceUI weldGasServiceUI = ServiceUIFactory.getWeldGasServiceUI();
    private WeldPositionServiceUI weldPositionServiceUI = ServiceUIFactory.getWeldPositionServiceUI();
    private EducationServiceUI educationServiceUI = ServiceUIFactory.getEducationServiceUI();
    private QualificationServiceUI qualificationServiceUI = ServiceUIFactory.getQualificationServiceUI();
    private JobServiceUI jobServiceUI = ServiceUIFactory.getJobServiceUI();
    private CommissionCertificationServiceUI commissionCertificationServiceUI =
            ServiceUIFactory.getCommissionCertificationServiceUI();
    private TeacherServiceUI teacherServiceUI = ServiceUIFactory.getTeacherServiceUI();

    @FXML
    private BorderPane mainPropertiesPane;

    @FXML
    private TabPane tabPaneAllProperties;
    @FXML
    private Button btAdd;
    @FXML
    private Button btEdit;
    @FXML
    private Button btDelete;
    @FXML
    private Button btSave;


    //Organization tab
    @FXML
    private Tab tabOrganizations;
    @FXML
    private TableView<OrganizationUI> tableViewOrganizations;
    @FXML
    private TableColumn<OrganizationUI, Long> tblColumnOrgId;
    @FXML
    private TableColumn<OrganizationUI, String> tblColumnOrgName;
    @FXML
    private TableColumn<OrganizationUI, String> tblColumnOrgAdress;
    @FXML
    private TableColumn<OrganizationUI, String> tblColumnOrgPhone;
    @FXML
    private TextField txfOrganizationName;
    @FXML
    private TextField txfOrganizationAdress;
    @FXML
    private TextField txfOrganizationPhone;
    @FXML
    private TextField txfSearchOrganization;

    //WeldPatterns tab
    @FXML
    private Tab tabWeldPatterns;
    @FXML
    private FlowPane flowPaneWeldPatterns;

    @FXML
    private TitledPane titlePaneWeldPatternsTypes;
    @FXML
    private ListView<WeldDetailUI> listViewWeldPatternsTypes;
    @FXML
    private TextField txfWeldPatternTypeCode;
    @FXML
    private TextField txfWeldPatternTypeName;
    @FXML
    private Button btSaveWeldPatternType;

    @FXML
    private TitledPane titlePaneDiameters;
    @FXML
    private ListView<PatternDiameterUI> listViewDiameters;
    @FXML
    private TextField txfDiameter;
    @FXML
    private Button btSaveDiameter;

    @FXML
    private TitledPane titlePaneThickness;
    @FXML
    private ListView<PatternThicknessUI> listViewThickness;
    @FXML
    private TextField txfThickness;
    @FXML
    private Button btSaveThickness;

    @FXML
    private TitledPane titlePaneSteelTypes;
    @FXML
    private ListView<SteelTypeUI> listViewSteelTypes;
    @FXML
    private TextField txfSteelType;
    @FXML
    private ComboBox<SteelGroupUI> cbSteelTypeGroup;
    @FXML
    private Button btSaveSteelType;

    @FXML
    private TitledPane titlePaneSteelGroups;
    @FXML
    private ListView<SteelGroupUI> listViewSteelGroups;
    @FXML
    private TextField txfSteelGroup;
    @FXML
    private TextArea txtAreaSteelGroupDecription;
    @FXML
    private Button btSaveSteelGroup;

    //Weld tab
    @FXML
    private Tab tabWeld;
    @FXML
    private FlowPane flowPaneWeld;

    @FXML
    private TitledPane titlePaneWeldMethod;
    @FXML
    private ListView<WeldMethodUI> listViewWeldMethod;
    @FXML
    private TextField txfWeldMethodCode;
    @FXML
    private TextField txfWeldMethodName;
    @FXML
    private Button btSaveWeldMethod;

    @FXML
    private TitledPane titlePaneElectrode;
    @FXML
    private ListView<ElectrodeUI> listViewElectrode;
    @FXML
    private TextField txfElectrodeType;
    @FXML
    private Button btSaveElectrode;

    @FXML
    private TitledPane titlePaneWeldWire;
    @FXML
    private ListView<WeldWireUI> listViewWeldWire;
    @FXML
    private TextField txfWeldWireType;
    @FXML
    private Button btSaveWeldWire;

    @FXML
    private TitledPane titlePaneWeldGas;
    @FXML
    private ListView<WeldGasUI> listViewWeldGas;
    @FXML
    private TextField txfWeldGasType;
    @FXML
    private Button btSaveWeldGas;

    @FXML
    private TitledPane titlePaneWeldPosition;
    @FXML
    private ListView<WeldPositionUI> listViewWeldPosition;
    @FXML
    private TextField txfWeldPositionCode;
    @FXML
    private TextArea txtAreaWeldPositionDescription;
    @FXML
    private Button btSaveWeldPosition;

    //Education and etc tab
    @FXML
    private Tab tabEducationEtc;
    @FXML
    private FlowPane flowPaneEducationEtc;

    @FXML
    private TitledPane titlePaneEducation;
    @FXML
    private ListView<EducationUI> listViewEducation;
    @FXML
    private TextField txfEducationType;
    @FXML
    private Button btSaveEducation;

    @FXML
    private TitledPane titlePaneQualification;
    @FXML
    private ListView<QualificationUI> listViewQualification;
    @FXML
    private TextField txfQualificationType;
    @FXML
    private Button btSaveQualification;

    @FXML
    private TitledPane titlePaneJob;
    @FXML
    private ListView<JobUI> listViewJob;
    @FXML
    private TextField txfJobType;
    @FXML
    private Button btSaveJob;

    //Commission Certification tab
    @FXML
    private Tab tabCommission;
    @FXML
    private TableView<CommissionCertificationUI> tableViewCommissions;
    @FXML
    private TableColumn<CommissionCertificationUI, Long> tblColumnCommissionId;
    @FXML
    private TableColumn<CommissionCertificationUI, TeacherUI> tblColumnHead;
    @FXML
    private TableColumn<CommissionCertificationUI, TeacherUI> tblColumnWeldSpec;
    @FXML
    private TableColumn<CommissionCertificationUI, TeacherUI> tblColumnNDTSpec;
    @FXML
    private TableColumn<CommissionCertificationUI, TeacherUI> tblColumnSafetySpec;
    @FXML
    private ComboBox<TeacherUI> cbCommissionHead;
    @FXML
    private ComboBox<TeacherUI> cbCommissionWeldSpec;
    @FXML
    private ComboBox<TeacherUI> cbCommissionNDTSpec;
    @FXML
    private ComboBox<TeacherUI> cbCommissionSafetySpec;

    //Teachers tab
    @FXML
    private Tab tabTeachers;
    @FXML
    private TableView<TeacherUI> tableViewTeachers;
    @FXML
    private TableColumn<TeacherUI, Long> tblColumnTeacherId;
    @FXML
    private TableColumn<TeacherUI, String> tblColumnTeacherSurname;
    @FXML
    private TableColumn<TeacherUI, String> tblColumnTeacherName;
    @FXML
    private TableColumn<TeacherUI, String> tblColumnTeacherSecname;
    @FXML
    private TextField txfTeacherSurname;
    @FXML
    private TextField txfTeacherName;
    @FXML
    private TextField txfTeacherSecname;

    //NDT Documents tab
    @FXML
    private Tab tabNDTDocuments;
    @FXML
    private TableView<NDTDocumentUI> tableViewNDTDocuments;
    @FXML
    private TableColumn<NDTDocumentUI, Long> tblColumnNDTDocId;
    @FXML
    private TableColumn<NDTDocumentUI, String> tblColumnNDTDocName;
    @FXML
    private TableColumn<NDTDocumentUI, String> tblColumnNDTDocFullName;
    @FXML
    private TextField txfNDTDocumentName;
    @FXML
    private TextArea txtAreaNDTDocumentFullName;

    //Curriculum tab
    @FXML
    private Tab tabCurriculum;
    @FXML
    private TreeView<String> treeViewCurriculum;
    @FXML
    private Accordion accordPaneCurriculum;
    @FXML
    private TitledPane titledPaneCurriculum;
    @FXML
    private TitledPane titledPaneSection;
    @FXML
    private TitledPane titledPaneTopic;
    @FXML
    private TextField txfCurriculumName;
    @FXML
    private TextArea txtAreaCurriculumDescription;
    @FXML
    private TextField txfSectionName;
    @FXML
    private TextArea txtAreaSectionDescription;
    @FXML
    private TextField txfTopicName;
    @FXML
    private TextArea txtAreaTopicDescription;
    @FXML
    private TextField txfTopicHours;




    private ObservableList<OrganizationUI> allOrganization = FXCollections.observableArrayList();
    private ObservableList<WeldDetailUI> allWeldPatternTypes = FXCollections.observableArrayList();
    private ObservableList<PatternDiameterUI> allDiameters = FXCollections.observableArrayList();
    private ObservableList<PatternThicknessUI> allThickness = FXCollections.observableArrayList();
    private ObservableList<SteelTypeUI> allSteelTypes = FXCollections.observableArrayList();
    private ObservableList<SteelGroupUI> allSteelGroups = FXCollections.observableArrayList();
    private ObservableList<WeldMethodUI> allWeldMethods = FXCollections.observableArrayList();
    private ObservableList<ElectrodeUI> allElectrodes = FXCollections.observableArrayList();
    private ObservableList<WeldWireUI> allWeldWires = FXCollections.observableArrayList();
    private ObservableList<WeldGasUI> allWeldGases = FXCollections.observableArrayList();
    private ObservableList<WeldPositionUI> allWeldPositions = FXCollections.observableArrayList();
    private ObservableList<EducationUI> allEducations = FXCollections.observableArrayList();
    private ObservableList<QualificationUI> allQualifications = FXCollections.observableArrayList();
    private ObservableList<JobUI> allJobs = FXCollections.observableArrayList();
    private ObservableList<CommissionCertificationUI> allCommissions = FXCollections.observableArrayList();
    private ObservableList<TeacherUI> allTeachers = FXCollections.observableArrayList();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LOGGER.debug("INITIALIZING Properties Pane");
        init();
    }

    public void showOrganizationTab(){
        tabPaneAllProperties.getSelectionModel().select(tabOrganizations);
    }

    public void showWeldPatternTab(String titlePaneParameter){
        tabPaneAllProperties.getSelectionModel().select(tabWeldPatterns);
        if (titlePaneParameter.equals(TITLE_PANE_WELD_DETAIL)){
            setActiveListView(listViewWeldPatternsTypes);
            return;
        }
        if (titlePaneParameter.equals(TITLE_PANE_DIAMETER)){
            setActiveListView(listViewDiameters);
            return;
        }
        if (titlePaneParameter.equals(TITLE_PANE_THICKNESS)){
            setActiveListView(listViewThickness);
            return;
        }
        if (titlePaneParameter.equals(TITLE_PANE_STEEL_TYPE)){
            setActiveListView(listViewSteelTypes);
            return;
        }


    }

    public void showWeldTab(String titlePaneParameter){
        tabPaneAllProperties.getSelectionModel().select(tabWeld);
        if (titlePaneParameter.equals(TITLE_PANE_WELD_METHOD)){
            setActiveListView(listViewWeldMethod);
            return;
        }
        if (titlePaneParameter.equals(TITLE_PANE_ELECTRODE)){
            setActiveListView(listViewElectrode);
            return;
        }
        if (titlePaneParameter.equals(TITLE_PANE_WELD_WIRE)){
            setActiveListView(listViewWeldWire);
            return;
        }
        if (titlePaneParameter.equals(TITLE_PANE_WELD_GAS)){
            setActiveListView(listViewWeldGas);
            return;
        }
        if (titlePaneParameter.equals(TITLE_PANE_WELD_POSITION)){
            setActiveListView(listViewWeldPosition);
            return;
        }
    }

    public void showEducationEtcTab(String titlePaneParameter){
        tabPaneAllProperties.getSelectionModel().select(tabEducationEtc);
        if (titlePaneParameter.equals(TITLE_PANE_EDUCATION)){
            setActiveListView(listViewEducation);
            return;
        }
        if (titlePaneParameter.equals(TITLE_PANE_QUALIFICATION)){
            setActiveListView(listViewQualification);
            return;
        }
        if (titlePaneParameter.equals(TITLE_PANE_JOB)){
            setActiveListView(listViewJob);
            return;
        }
    }

    private void init(){

        initOrganizationTab();
        initWeldPatternTab();
        initWeldTab();
        initEducationEtcTab();
        initCommissionTab();
        initTabPanes(flowPaneEducationEtc,flowPaneWeldPatterns, flowPaneWeld);
        btSave.setDisable(true);
    }

    private void initEducationEtcTab(){
        initTitlePaneEducations();
        initTitlePaneQualifications();
        initTitlePaneJobs();
    }

    private void initTitlePaneJobs(){
        initListViewJobs();
        setDisabledTextFields(true, txfJobType);
        InvalidationListener invalidationListener = new TextFieldValidateListener();
        txfJobType.textProperty().addListener(invalidationListener);
    }

    private void initListViewJobs(){
        initAllJobs();
        initListView(listViewJob, allJobs);
    }

    private void initAllJobs(){
        allJobs.clear();
        for (Job j : jobService.getAll()){
            JobUI jobUI = new JobUI(j);
            allJobs.add(jobUI);
        }
    }

    private void updateJobFromFields(JobUI jobUI){
        if(jobUI == null)
            return;
        jobUI.setName(txfJobType.getText().trim());

    }

    private void initTitlePaneQualifications(){
        initListViewQualifications();
        setDisabledTextFields(true, txfQualificationType);
        InvalidationListener invalidationListener = new TextFieldValidateListener();
        txfQualificationType.textProperty().addListener(invalidationListener);
    }

    private void initListViewQualifications(){
        initAllQualifications();
        initListView(listViewQualification, allQualifications);
    }

    private void initAllQualifications(){
        allQualifications.clear();
        for (Qualification q : qualificationService.getAll()){
            QualificationUI qUI = new QualificationUI(q);
            allQualifications.add(qUI);
        }
    }

    private void updateQualificationFromFields(QualificationUI qualificationUI){
        if (qualificationUI == null)
            return;
        qualificationUI.setType(txfQualificationType.getText().trim());
    }

    private void initTitlePaneEducations(){
        initListViewEducations();
        setDisabledTextFields(true, txfEducationType);
        InvalidationListener invalidationListener = new TextFieldValidateListener();
        txfEducationType.textProperty().addListener(invalidationListener);

    }

    private void initListViewEducations(){
        initAllEducations();
        initListView(listViewEducation, allEducations);
    }

    private void initAllEducations(){
        allEducations.clear();
        for (Education edu : educationService.getAll()){
            EducationUI eduUI = new EducationUI(edu);
            allEducations.add(eduUI);
        }
    }

    private void updateEducationFromFields(EducationUI educationUI){
        if (educationUI == null)
            return;
        educationUI.setType(txfEducationType.getText().trim());
    }

    private void initWeldTab(){
        initTitlePaneWeldMethods();
        initTitlePaneElectrodes();
        initTitlePaneWeldWires();
        initTitlePaneWeldGas();
        initTitlePaneWeldPositions();
    }

    private void initTitlePaneWeldPositions(){
        initListViewWeldPositions();
        setDisabledWeldPositionsFields(true);
        InvalidationListener invalidationListener = new TextFieldValidateListener();
        txfWeldPositionCode.textProperty().addListener(invalidationListener);
        txtAreaWeldPositionDescription.textProperty().addListener(invalidationListener);
    }

    private void setDisabledWeldPositionsFields(boolean disabled){
        setDisabledTextFields(disabled, txfWeldPositionCode);
        setDisabledTextAreas(disabled,txtAreaWeldPositionDescription);
    }

    private void initListViewWeldPositions(){
        initAllWeldPositions();
        initListView(listViewWeldPosition, allWeldPositions);
    }

    private void initAllWeldPositions(){
        allWeldPositions.clear();
        for (WeldPosition wp : weldPositionService.getAll()){
            WeldPositionUI wpUI = new WeldPositionUI(wp);
            allWeldPositions.add(wpUI);
        }
    }

    private void updateWeldPositionFromFields(WeldPositionUI weldPositionUI){
        if (weldPositionUI == null)
            return;
        weldPositionUI.setType(txtAreaWeldPositionDescription.getText().trim());
        weldPositionUI.setCode(txfWeldPositionCode.getText().trim());
    }

    private void initTitlePaneWeldGas(){
        initListViewWeldGas();
        setDisabledTextFields(true, txfWeldGasType);
        InvalidationListener invalidationListener = new TextFieldValidateListener();
        txfWeldGasType.textProperty().addListener(invalidationListener);
    }

    private void initListViewWeldGas(){
        initAllWeldGases();
        initListView(listViewWeldGas, allWeldGases);
    }

    private void initAllWeldGases(){
        allWeldGases.clear();
        for (WeldGas wg : weldGasService.getAll()){
            WeldGasUI wgUI = new WeldGasUI(wg);
            allWeldGases.add(wgUI);
        }
    }

    private void updateWeldGasFromFields(WeldGasUI weldGasUI){
        if (weldGasUI == null)
            return;
        weldGasUI.setType(txfWeldGasType.getText().trim());
    }

    private void initTitlePaneWeldWires(){
        initListViewWeldWires();
        setDisabledTextFields(true, txfWeldWireType);
        InvalidationListener invalidationListener = new TextFieldValidateListener();
        txfWeldWireType.textProperty().addListener(invalidationListener);
    }

    private void initListViewWeldWires(){
        initAllWeldWires();
        initListView(listViewWeldWire, allWeldWires);
    }

    private void initAllWeldWires(){
        allWeldWires.clear();
        for (WeldWire ww : weldWireService.getAll()){
            WeldWireUI wwUI = new WeldWireUI(ww);
            allWeldWires.add(wwUI);
        }
    }

    private void updateWeldWireFromFields(WeldWireUI weldWireUI){
        if (weldWireUI == null)
            return;
        weldWireUI.setType(txfWeldWireType.getText().trim());
    }

    private void initTitlePaneElectrodes(){
        initListViewElectrodes();
        setDisabledTextFields(true, txfElectrodeType);
        InvalidationListener invalidationListener = new TextFieldValidateListener();
        txfElectrodeType.textProperty().addListener(invalidationListener);
    }

    private void initListViewElectrodes(){
        initAllElectrodes();
        listViewElectrode.setItems(allElectrodes);
        listViewElectrode.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        listViewElectrode.setEditable(false);
        ListViewEventHandler eventHandler = new ListViewEventHandler();
        listViewElectrode.addEventHandler(Event.ANY, eventHandler);
        listViewElectrode.getSelectionModel().selectFirst();
    }

    private void initAllElectrodes(){
        allElectrodes.clear();
        for (Electrode el : electrodeService.getAll()){
            ElectrodeUI elUI  = new ElectrodeUI(el);
            allElectrodes.add(elUI);
        }
    }

    private void updateElectrodesFromFields(ElectrodeUI electrodeUI){
        if (electrodeUI == null)
            return;
        electrodeUI.setType(txfElectrodeType.getText().trim());
    }

    private void initTitlePaneWeldMethods(){
        initListViewWeldMethods();
        setDisabledTextFields(true, txfWeldMethodCode, txfWeldMethodName);
        InvalidationListener invalidationListener = new TextFieldValidateListener();
        txfWeldMethodCode.textProperty().addListener(invalidationListener);
        txfWeldMethodName.textProperty().addListener(invalidationListener);

    }

    private void initListViewWeldMethods(){
        initAllWeldMethods();
        listViewWeldMethod.setItems(allWeldMethods);
        listViewWeldMethod.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        listViewWeldMethod.setEditable(false);
        ListViewEventHandler eventHandler = new ListViewEventHandler();
        listViewWeldMethod.addEventHandler(Event.ANY, eventHandler);
        listViewWeldMethod.getSelectionModel().selectFirst();
    }

    private void updateWeldMethodFromFields(WeldMethodUI weldMethodUI){
        if (weldMethodUI == null)
            return;
        weldMethodUI.setCode(txfWeldMethodCode.getText().trim());
        weldMethodUI.setName(txfWeldMethodName.getText().trim());
    }

    private void initAllWeldMethods(){
        allWeldMethods.clear();
        for (WeldMethod wm : weldMethodService.getAll()){
            WeldMethodUI wmUI = new WeldMethodUI(wm);
            allWeldMethods.add(wmUI);
        }
    }

    private void initWeldPatternTab(){
        initTitlePaneWeldPatternTypes();
        initTitlePaneDiameters();
        initTitlePaneThickness();
        initTitlePaneSteelTypes();
        initTitlePaneSteelGroups();

    }

    private void initTitlePaneSteelGroups(){
        initListViewSteelGroups();
        setDisabledSteelGroupField(true);
        InvalidationListener invalidationListener = new TextFieldValidateListener();
        txfSteelGroup.textProperty().addListener(invalidationListener);
        txtAreaSteelGroupDecription.textProperty().addListener(invalidationListener);

    }

    private void initListViewSteelGroups(){
        initAllSteelGroups();
        listViewSteelGroups.setItems(allSteelGroups);
        ListViewEventHandler eventHandler = new ListViewEventHandler();
        listViewSteelGroups.addEventHandler(Event.ANY, eventHandler);
        listViewSteelGroups.setEditable(false);
        listViewSteelGroups.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        listViewSteelGroups.getSelectionModel().selectFirst();
    }

    private void setDisabledSteelGroupField(boolean disabled){
        setDisabledTextFields(disabled, txfSteelGroup);
        setDisabledTextAreas(disabled,txtAreaSteelGroupDecription);
    }

    private void updateSteelGroupFromFields(SteelGroupUI steelGroupUI){
        if (steelGroupUI == null)
            return;
        steelGroupUI.setGroup(txfSteelGroup.getText().trim());
        steelGroupUI.setDescription(txtAreaSteelGroupDecription.getText().trim());
    }

    private void initTitlePaneSteelTypes(){
        initListViewSteelTypes();
        initComboBoxSteelGroup();
        setDisabledSteelTypesFields(true);
        InvalidationListener invalidationListener = new TextFieldValidateListener();
        txfSteelType.textProperty().addListener(invalidationListener);
        cbSteelTypeGroup.valueProperty().addListener(invalidationListener);
        listViewSteelTypes.fireEvent(EventFXUtil.getMouseClickEvent());
    }

    private void initListViewSteelTypes(){
        initAllSteelTypes();
        listViewSteelTypes.setItems(allSteelTypes);
        ListViewEventHandler eventHandler = new ListViewEventHandler();
        listViewSteelTypes.addEventHandler(Event.ANY, eventHandler);
        listViewSteelTypes.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        listViewSteelTypes.setEditable(false);
        listViewSteelTypes.getSelectionModel().selectFirst();

    }

    private void initComboBoxSteelGroup(){
        initAllSteelGroups();
        cbSteelTypeGroup.setItems(allSteelGroups);
        cbSteelTypeGroup.setEditable(false);

    }

    private void initAllSteelGroups(){
        allSteelGroups.clear();
        for (SteelGroup sg: steelGroupService.getAll()){
            SteelGroupUI sgUI = new SteelGroupUI(sg);
            allSteelGroups.add(sgUI);
        }
    }

    private void initAllSteelTypes(){
        allSteelTypes.clear();
        for (SteelType st : steelTypeService.getAll()){
            SteelTypeUI stUI = new SteelTypeUI(st);
            allSteelTypes.add(stUI);
        }

    }

    private void setDisabledSteelTypesFields(boolean disabled){
        setDisabledTextFields(disabled, txfSteelType);
        setDisabledComboBoxes(disabled,cbSteelTypeGroup);
    }

    private void updateSteelTypesFromFields(SteelTypeUI steelTypeUI){
        if (steelTypeUI == null)
            return;
        steelTypeUI.setType(txfSteelType.getText().trim());
        SteelGroupUI steelGroupUI = cbSteelTypeGroup.getValue();

        steelTypeUI.setSteelGroupUI(steelGroupUI);
        steelTypeUI.setSteelGroup(steelGroupServiceUI.getSteelGroupFromUIModel(steelGroupUI));
    }

    private void initTitlePaneThickness(){
        initListViewThickness();
        setDisabledTextFields(true, txfThickness);
        InvalidationListener invalidationListener = new TextFieldValidateListener();
        txfThickness.textProperty().addListener(invalidationListener);

    }

    private void initListViewThickness(){
        initAllThickness();
        listViewThickness.setItems(allThickness);
        ListViewEventHandler eventHandler = new ListViewEventHandler();
        listViewThickness.addEventHandler(Event.ANY, eventHandler);
        listViewThickness.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        listViewThickness.setEditable(false);
        listViewThickness.getSelectionModel().selectFirst();
    }

    private void initAllThickness(){
        allThickness.clear();
        for (PatternThickness pth : patternThicknessService.getAll()){
            PatternThicknessUI pthUI = new PatternThicknessUI(pth);
            allThickness.add(pthUI);
        }
    }

    private void updateThicknessFromFields(PatternThicknessUI thicknessUI ){
        Double thickness = 0.0;
        try{
            thickness = Double.parseDouble(txfThickness.getText().trim());

        }catch (NumberFormatException ex){
            LOGGER.warn("UPDATE THICKNESS FROM FIELD: not correct thickness");
            thickness = 0.0;
        }
        thicknessUI.setThickness(thickness);
    }

    private void initTitlePaneDiameters(){
        initListViewDiameters();
        setDisabledTextFields(true,txfDiameter);
        InvalidationListener invalidationListener = new TextFieldValidateListener();
        txfDiameter.textProperty().addListener(invalidationListener);
    }

    private  void initListViewDiameters(){
        initAllDiameters();
        listViewDiameters.setItems(allDiameters);
        listViewDiameters.setEditable(false);
        ListViewEventHandler eventHandler = new ListViewEventHandler();
        listViewDiameters.addEventHandler(Event.ANY, eventHandler);
        listViewDiameters.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        listViewDiameters.getSelectionModel().selectFirst();
    }

    private void initAllDiameters(){
        allDiameters.clear();
        for (PatternDiameter pd : patternDiameterService.getAll()){
            PatternDiameterUI pdUI = new PatternDiameterUI(pd);
            allDiameters.add(pdUI);
        }
    }

    @FXML
    private void saveDiameter(){
        setDisabledTextFields(true, txfDiameter);
        btSaveDiameter.setDisable(true);
        PatternDiameterUI selectedDiameterUI = listViewDiameters.getSelectionModel().getSelectedItem();
        if (selectedDiameterUI == null)
            return;
        updateDiameterFromFields(selectedDiameterUI);
        PatternDiameter patternDiameter = patternDiameterServiceUI.getPatternDiameterFromUIModel(selectedDiameterUI);
        if (patternDiameter.getPatternDiameterId()==null || patternDiameter.getPatternDiameterId()==0){
            Long id = patternDiameterService.insert(patternDiameter);
            LOGGER.debug("SAVE PATTERN DIAMETER: pattern diameter has been inserted in DB id="+id);
            selectedDiameterUI.setId(id);
        }else {
            patternDiameterService.update(patternDiameter);
            LOGGER.debug("SAVE PATTERN DIAMETER: pattern diameter has been updated in DB");
        }
        allDiameters.remove(selectedDiameterUI);
        allDiameters.add(selectedDiameterUI);
        listViewDiameters.getSelectionModel().select(selectedDiameterUI);

    }

    private void updateDiameterFromFields(PatternDiameterUI diameterUI){
        Double diameter = 0.0;
        try{
            diameter = Double.parseDouble(txfDiameter.getText().trim());

        }catch (NumberFormatException ex){
            LOGGER.warn("UPDATE DIAMETER FROM FIELD: not correct diameter");
            diameter = 0.0;
        }
        diameterUI.setDiameter(diameter);
    }

    private void initTitlePaneWeldPatternTypes(){
        initListViewWeldPatternTypes();
        setDisabledWeldPatternTypesFields(true);
        TextFieldValidateListener validateListener = new TextFieldValidateListener();
        txfWeldPatternTypeCode.textProperty().addListener(validateListener);
        txfWeldPatternTypeName.textProperty().addListener(validateListener);
    }

    private void initListViewWeldPatternTypes(){
        initAllWeldPatternTypes();
        listViewWeldPatternsTypes.setItems(allWeldPatternTypes);
        listViewWeldPatternsTypes.setEditable(false);
        ListViewEventHandler eventHandler = new ListViewEventHandler();
        listViewWeldPatternsTypes.addEventHandler(Event.ANY, eventHandler);
        listViewWeldPatternsTypes.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        listViewWeldPatternsTypes.getSelectionModel().selectFirst();
    }

    private void initAllWeldPatternTypes(){
        allWeldPatternTypes.clear();

        for (WeldDetail wd : weldDetailService.getAll()){
            WeldDetailUI wdUI = new WeldDetailUI(wd);
            allWeldPatternTypes.add(wdUI);
        }
    }

    private void setDisabledWeldPatternTypesFields(boolean disabled){
       setDisabledTextFields(disabled, txfWeldPatternTypeName, txfWeldPatternTypeCode);
    }

    @FXML
    private void saveWeldPatternType(){
        setDisabledWeldPatternTypesFields(true);
        btSaveWeldPatternType.setDisable(true);
        WeldDetailUI selectedWeldDetail = listViewWeldPatternsTypes.getSelectionModel().getSelectedItem();
        if (selectedWeldDetail == null)
            return;
        updateWeldPatternTypeFromFields(selectedWeldDetail);
        WeldDetail weldDetail = weldDetailServiceUI.getWeldDetailFromUIModel(selectedWeldDetail);

        if (weldDetail.getWeldDetailId()==null || weldDetail.getWeldDetailId()==0){
            Long id = weldDetailService.insert(weldDetail);
            selectedWeldDetail.setId(id);
            LOGGER.debug("SAVE WELD PATTERN TYPE: weld detail has been added to DB");
        }else {
            weldDetailService.update(weldDetail);
            LOGGER.debug("SAVE WELD PATTERN TYPE: weld detail has been updated in DB");
        }
        allWeldPatternTypes.remove(selectedWeldDetail);
        allWeldPatternTypes.add(selectedWeldDetail);
        listViewWeldPatternsTypes.getSelectionModel().select(selectedWeldDetail);
    }

    private void updateWeldPatternTypeFromFields(WeldDetailUI weldDetailUI){
        weldDetailUI.setType(txfWeldPatternTypeName.getText().trim());
        weldDetailUI.setCode(txfWeldPatternTypeCode.getText().trim());

    }

    private void initCommissionTab(){
        initTableViewCommissions();
        initComboBoxesCommissions();
        setDisabledComboBoxes(true, cbCommissionHead, cbCommissionNDTSpec,
                cbCommissionSafetySpec, cbCommissionWeldSpec);

    }

    private void initComboBoxesCommissions(){
        initAllTeachers();
        cbCommissionHead.setItems(allTeachers);
        cbCommissionWeldSpec.setItems(allTeachers);
        cbCommissionNDTSpec.setItems(allTeachers);
        cbCommissionSafetySpec.setItems(allTeachers);

        InvalidationListener invalidationListener = new TextFieldValidateListener();

        cbCommissionHead.valueProperty().addListener(invalidationListener);
        cbCommissionNDTSpec.valueProperty().addListener(invalidationListener);
        cbCommissionSafetySpec.valueProperty().addListener(invalidationListener);
        cbCommissionWeldSpec.valueProperty().addListener(invalidationListener);
    }

    private void initTableViewCommissions(){
        initAllCommissions();
        tblColumnCommissionId.setCellValueFactory(new PropertyValueFactory<CommissionCertificationUI, Long>("id"));
        tblColumnHead.setCellValueFactory(new PropertyValueFactory<CommissionCertificationUI, TeacherUI>("head"));
        tblColumnNDTSpec.setCellValueFactory(
                new PropertyValueFactory<CommissionCertificationUI, TeacherUI>("ndtSpecialist"));
        tblColumnSafetySpec.setCellValueFactory(
                new PropertyValueFactory<CommissionCertificationUI, TeacherUI>("safetySpecialist"));
        tblColumnWeldSpec.setCellValueFactory(
                new PropertyValueFactory<CommissionCertificationUI, TeacherUI>("weldSpecialist"));
        tableViewCommissions.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        tableViewCommissions.addEventHandler(Event.ANY, new TableViewEventHandler());
        tableViewCommissions.setItems(allCommissions);

    }

    private void initAllCommissions(){
        allCommissions.clear();
        for (CommissionCertification cc : commissionCertificationService.getAll()){
            CommissionCertificationUI ccUI = new CommissionCertificationUI(cc);
            allCommissions.add(ccUI);
        }
    }

    private void updateCommissionFromFields(CommissionCertificationUI commissionUI){
        if (commissionUI == null)
            return;
        commissionUI.setHead(cbCommissionHead.getValue());
        commissionUI.setWeldSpecialist(cbCommissionWeldSpec.getValue());
        commissionUI.setNdtSpecialist(cbCommissionNDTSpec.getValue());
        commissionUI.setSafetySpecialist(cbCommissionSafetySpec.getValue());
    }

    private void addCommission(){
        CommissionCertificationUI newCommiss = new CommissionCertificationUI();
        allCommissions.add(newCommiss);
        tableViewCommissions.getSelectionModel().clearSelection();
        tableViewCommissions.getSelectionModel().select(newCommiss);
        tableViewCommissions.fireEvent(EventFXUtil.getMouseClickEvent());
        editRecord();
    }

    private void editCommission(){
        if (tableViewCommissions.getSelectionModel().getSelectedItem() == null)
            return;
        setDisabledComboBoxes(false, cbCommissionHead, cbCommissionWeldSpec,
                cbCommissionNDTSpec, cbCommissionSafetySpec);
        cbCommissionHead.requestFocus();
    }

    private void deleteCommission(){
        CommissionCertificationUI delComm = tableViewCommissions.getSelectionModel().getSelectedItem();
        if (delComm == null)
            return;
        if (getResponseDeleteDialog(1)==Dialog.Actions.OK){
            if (delComm.getId() != 0){
                commissionCertificationService.delete(
                        commissionCertificationServiceUI.getCommissionCertificationFromUIModel(delComm));
                LOGGER.debug("DELETE COMMISSION: commission has been deleted from DB");
            }
            allCommissions.remove(delComm);
            tableViewCommissions.getSelectionModel().selectFirst();
            tableViewCommissions.fireEvent(EventFXUtil.getMouseClickEvent());
            tableViewCommissions.requestFocus();
        }

    }

    private void saveCommission(){
        setDisabledComboBoxes(true, cbCommissionHead, cbCommissionWeldSpec,
                cbCommissionNDTSpec, cbCommissionSafetySpec);
        btSave.setDisable(true);
        CommissionCertificationUI updCommission = tableViewCommissions.getSelectionModel().getSelectedItem();
        updateCommissionFromFields(updCommission);
        CommissionCertification commission =
                commissionCertificationServiceUI.getCommissionCertificationFromUIModel(updCommission);
        if (commission == null)
            return;
        if (commission.getCommissionCertificationId() !=null
                && commission.getCommissionCertificationId() != 0){
            commissionCertificationService.update(commission);
            LOGGER.debug("SAVE COMMISSION: commission updated in DB");
        }else{
            Long id = commissionCertificationService.insert(commission);
            updCommission.setId(id);
            LOGGER.debug("SAVE COMMISSION: commission inserted in DB");
        }
    }

    private void initAllTeachers(){
        allTeachers.clear();
        for (Teacher t : teacherService.getAll()){
            TeacherUI tUI = new TeacherUI(t);
            allTeachers.add(tUI);
        }
    }


    private void initOrganizationTab(){
        initTableViewOrganizations();
        setDisabledOrganizationFields(true);
        TextFieldValidateListener validateListener = new TextFieldValidateListener();
        SearchTextFieldValidateListener searchValidateListener = new SearchTextFieldValidateListener();

        txfOrganizationName.textProperty().addListener(validateListener);
        txfOrganizationAdress.textProperty().addListener(validateListener);
        txfOrganizationPhone.textProperty().addListener(validateListener);
        txfSearchOrganization.textProperty().addListener(searchValidateListener);
    }

    private void setDisabledOrganizationFields(boolean disabled){
       setDisabledTextFields(disabled, txfOrganizationName, txfOrganizationAdress, txfOrganizationPhone);
    }

    private void initTableViewOrganizations(){
        initAllOrganizationsList();
        tblColumnOrgId.setCellValueFactory(new PropertyValueFactory<OrganizationUI, Long>("id"));
        tblColumnOrgName.setCellValueFactory(new PropertyValueFactory<OrganizationUI, String>("name"));
        tblColumnOrgAdress.setCellValueFactory(new PropertyValueFactory<OrganizationUI, String>("adress"));
        tblColumnOrgPhone.setCellValueFactory(new PropertyValueFactory<OrganizationUI, String>("phone"));
        tableViewOrganizations.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        tableViewOrganizations.addEventHandler(Event.ANY, new TableViewEventHandler());
        tableViewOrganizations.setItems(allOrganization);

    }

    private void initAllOrganizationsList(){
        allOrganization.clear();
        for (Organization org : organizationService.getAll()){
            OrganizationUI orgUI = new OrganizationUI(org);
            allOrganization.add(orgUI);
        }
    }

    private void editOrganizationActive(){
        if (tableViewOrganizations.getSelectionModel().getSelectedItem()==null)
            return;
        setDisabledOrganizationFields(false);
        txfOrganizationName.requestFocus();
    }

    private void saveOrganization(){
        setDisabledOrganizationFields(true);
        btSave.setDisable(true);
        OrganizationUI updOrganization = tableViewOrganizations.getSelectionModel().getSelectedItem();
        updateOrganizationFromFields(updOrganization);
        Organization org = organizationServiceUI.getOrganizationFromOrganizationUI(updOrganization);
        if (org == null)
            return;
        if (org.getOrganizationId()!=null && org.getOrganizationId() != 0){
            organizationService.update(org);
            LOGGER.debug("SAVE ORGANIZATION: organization updated in DB");
        }else{
            Long id = organizationService.insert(org);
            updOrganization.setId(id);
            LOGGER.debug("SAVE ORGANIZATION: organization inserted in DB");
        }

    }

    private void addOrganization(){
        OrganizationUI newOrg = new OrganizationUI();
        allOrganization.add(newOrg);
        tableViewOrganizations.getSelectionModel().clearSelection();
        tableViewOrganizations.getSelectionModel().select(newOrg);
        tableViewOrganizations.fireEvent(EventFXUtil.getMouseClickEvent());
        editRecord();

    }

    private void deleteOrganization(){
        LOGGER.debug("DELETE button is pressed");
        ObservableList<OrganizationUI> delOrgList = tableViewOrganizations.getSelectionModel().getSelectedItems();

        if(!delOrgList.isEmpty()){
            LOGGER.debug("start DELETE dialog...");
            Action response = Dialogs.create().owner(mainPropertiesPane.getScene().getWindow())
                    .title("Удаление записей")
                    .masthead("Сделан выбор записей для удаления")
                    .message("Удалить выбранные записи? ("+delOrgList.size()+"шт.)")
                    .actions(org.controlsfx.dialog.Dialog.Actions.OK, org.controlsfx.dialog.Dialog.Actions.CANCEL)
                    .showConfirm();
            if(response == org.controlsfx.dialog.Dialog.Actions.OK){
                for(OrganizationUI orgUI : delOrgList){
                    if (orgUI.getId()!=0){
                        organizationService.delete(organizationServiceUI.getOrganizationFromOrganizationUI(orgUI));
                    }
                    else
                        LOGGER.warn("DELETE ORGANIZATION: Not possible to delete organization from DB: "+orgUI);
                }
                allOrganization.removeAll(delOrgList);
                tableViewOrganizations.getSelectionModel().clearSelection();
                tableViewOrganizations.getSelectionModel().selectFirst();
                tableViewOrganizations.fireEvent(EventFXUtil.getMouseClickEvent());
                LOGGER.debug("DELETE ORGANIZATIONS: Selected organizations has been deleted");
            }

        }
    }

    private void updateOrganizationFromFields(OrganizationUI organizationUI){
        if (organizationUI==null)
            return;
        organizationUI.setName(txfOrganizationName.getText().trim());
        organizationUI.setAdress(txfOrganizationAdress.getText().trim());
        organizationUI.setPhone(txfOrganizationPhone.getText().trim());
    }


    @FXML
    private void closePropertiesPane(){
        if(mainPropertiesPane.isVisible()){
            mainPropertiesPane.setVisible(false);
            getControllerManager().getMainFrameController().showLogo();
        }
    }

    @FXML
    private void editRecord(){
        Tab activeTab = tabPaneAllProperties.getSelectionModel().getSelectedItem();
        if (activeTab == null)
            return;
        if (activeTab.equals(tabOrganizations)){
            editOrganizationActive();
            return;
        }
        if (activeTab.equals(tabCommission)){
            editCommission();
            return;
        }
    }

    @FXML
    private void saveRecord(){
        Tab activeTab = tabPaneAllProperties.getSelectionModel().getSelectedItem();
        if (activeTab == null)
            return;
        if (activeTab.equals(tabOrganizations)){
            saveOrganization();
            return;
        }
        if (activeTab.equals(tabCommission)){
            saveCommission();
            return;
        }
    }

    @FXML
    private void addRecord(){
        Tab activeTab = tabPaneAllProperties.getSelectionModel().getSelectedItem();
        if (activeTab == null)
            return;
        if (activeTab.equals(tabOrganizations)){
            addOrganization();
            return;
        }
        if (activeTab.equals(tabCommission)){
            addCommission();
            return;
        }

    }

    @FXML
    private void deleteRecord(){
        Tab activeTab = tabPaneAllProperties.getSelectionModel().getSelectedItem();
        if (activeTab == null)
            return;
        if (activeTab.equals(tabOrganizations)){
            deleteOrganization();
            return;
        }
        if (activeTab.equals(tabCommission)){
            deleteCommission();
            return;
        }
    }

    @FXML
    private void selectFirstRecord(){
        Tab activeTab = tabPaneAllProperties.getSelectionModel().getSelectedItem();
        if (activeTab == null)
            return;
        if (activeTab.equals(tabOrganizations)){
            tableViewOrganizations.getSelectionModel().clearSelection();
            tableViewOrganizations.getSelectionModel().selectFirst();
            tableViewOrganizations.fireEvent(EventFXUtil.getMouseClickEvent());
            tableViewOrganizations.requestFocus();
            return;
        }
        if (activeTab.equals(tabCommission)){
            selectFirstTableRecord(tableViewCommissions);
            return;
        }
    }

    @FXML
    private void selectLastRecord(){
        Tab activeTab = tabPaneAllProperties.getSelectionModel().getSelectedItem();
        if (activeTab == null)
            return;
        if (activeTab.equals(tabOrganizations)){
            tableViewOrganizations.getSelectionModel().clearSelection();
            tableViewOrganizations.getSelectionModel().selectLast();
            tableViewOrganizations.fireEvent(EventFXUtil.getMouseClickEvent());
            tableViewOrganizations.requestFocus();
        }
        if (activeTab.equals(tabCommission)){
            selectLastTableRecord(tableViewCommissions);
            return;
        }
    }

    @FXML
    private void selectPrevRecord(){
        Tab activeTab = tabPaneAllProperties.getSelectionModel().getSelectedItem();
        if (activeTab == null)
            return;
        if (activeTab.equals(tabOrganizations)){
            tableViewOrganizations.getSelectionModel().selectPrevious();
            OrganizationUI selectedOrg = tableViewOrganizations.getSelectionModel().getSelectedItem();
            tableViewOrganizations.getSelectionModel().clearSelection();
            tableViewOrganizations.getSelectionModel().select(selectedOrg);
            tableViewOrganizations.fireEvent(EventFXUtil.getMouseClickEvent());
            tableViewOrganizations.requestFocus();
        }
        if (activeTab.equals(tabCommission)){
            selectPrevTableRecord(tableViewCommissions);
            return;
        }
    }

    @FXML
    private void selectNextRecord(){
        Tab activeTab = tabPaneAllProperties.getSelectionModel().getSelectedItem();
        if (activeTab == null)
            return;
        if (activeTab.equals(tabOrganizations)){
            tableViewOrganizations.getSelectionModel().selectNext();
            OrganizationUI selectedOrg = tableViewOrganizations.getSelectionModel().getSelectedItem();
            tableViewOrganizations.getSelectionModel().clearSelection();
            tableViewOrganizations.getSelectionModel().select(selectedOrg);
            tableViewOrganizations.fireEvent(EventFXUtil.getMouseClickEvent());
            tableViewOrganizations.requestFocus();
        }
        if (activeTab.equals(tabCommission)){
            selectNextTableRecord(tableViewCommissions);
            return;
        }
    }

    @FXML
    private void refreshTableView(){
        Tab activeTab = tabPaneAllProperties.getSelectionModel().getSelectedItem();
        if (activeTab == null)
            return;
        if (activeTab.equals(tabOrganizations)){
            initAllOrganizationsList();
            selectFirstRecord();
            return;
        }
        if (activeTab.equals(tabCommission)){
            initAllTeachers();
            initAllCommissions();
            selectFirstTableRecord(tableViewCommissions);
            return;
        }

    }

    // --- Weld Pattern Type (WeldDetail) --
    @FXML
    private void addWeldPatternType(){
        txfWeldPatternTypeCode.clear();
        txfWeldPatternTypeName.clear();
        listViewWeldPatternsTypes.getSelectionModel().clearSelection();
        WeldDetailUI newWD = new WeldDetailUI();
        allWeldPatternTypes.add(newWD);
        listViewWeldPatternsTypes.getSelectionModel().select(newWD);

        setDisabledWeldPatternTypesFields(false);
        txfWeldPatternTypeCode.requestFocus();
    }

    @FXML
    private void editWeldPatternType(){
        setDisabledWeldPatternTypesFields(false);
        txfWeldPatternTypeCode.requestFocus();
    }

    // --- Diameter --
    @FXML
    private void addDiameter(){
        txfDiameter.clear();
        listViewDiameters.getSelectionModel().clearSelection();
        PatternDiameterUI patternDiameterUI = new PatternDiameterUI();
        allDiameters.add(patternDiameterUI);
        listViewDiameters.getSelectionModel().select(patternDiameterUI);

        setDisabledTextFields(false, txfDiameter);
        txfDiameter.requestFocus();

    }

    @FXML
    private void deleteWeldPatternType(){
        WeldDetailUI delWeldDetailUI = listViewWeldPatternsTypes.getSelectionModel().getSelectedItem();
        if (delWeldDetailUI == null)
            return;
        if (getResponseDeleteDialog(1)== Dialog.Actions.OK){
            if (delWeldDetailUI.getId()!=0){
                weldDetailService.delete(weldDetailServiceUI.getWeldDetailFromUIModel(delWeldDetailUI));
                LOGGER.debug("DELETE WELD PATTERN TYPE: weld detail has been removed from DB");
            }
            allWeldPatternTypes.remove(delWeldDetailUI);
            listViewWeldPatternsTypes.getSelectionModel().selectFirst();
            listViewWeldPatternsTypes.fireEvent(EventFXUtil.getMouseClickEvent());
            listViewWeldPatternsTypes.requestFocus();
        }

    }

    @FXML
    private void editDiameter(){
        if(listViewDiameters.getSelectionModel().getSelectedItems()==null)
            return;
        setDisabledTextFields(false,txfDiameter);
        txfDiameter.requestFocus();

    }

    @FXML
    private void deleteDiameter(){
        PatternDiameterUI delDiameter = listViewDiameters.getSelectionModel().getSelectedItem();
        if (delDiameter == null)
            return;
        if (getResponseDeleteDialog(1)==Dialog.Actions.OK){
            if (delDiameter.getId() != 0){
                patternDiameterService.delete(patternDiameterServiceUI.getPatternDiameterFromUIModel(delDiameter));
                LOGGER.debug("DELETE DIAMETER: pattern diameter has been deleted from DB");
            }
            allDiameters.remove(delDiameter);
            listViewDiameters.getSelectionModel().selectFirst();
            listViewDiameters.fireEvent(EventFXUtil.getMouseClickEvent());
            listViewDiameters.requestFocus();
        }
    }

    // --- Thickness --
    @FXML
    private void addThickness(){
        txfThickness.clear();
        listViewThickness.getSelectionModel().clearSelection();
        PatternThicknessUI patternThicknessUI = new PatternThicknessUI();
        allThickness.add(patternThicknessUI);
        listViewThickness.getSelectionModel().select(patternThicknessUI);

        setDisabledTextFields(false, txfThickness);
        txfThickness.requestFocus();

    }

    @FXML
    private void editThickness(){
        PatternThicknessUI selectedThickness = listViewThickness.getSelectionModel().getSelectedItem();
        if (selectedThickness == null)
            return;
        listViewThickness.getSelectionModel().clearSelection();
        listViewThickness.getSelectionModel().select(selectedThickness);
        setDisabledTextFields(false, txfThickness);
        txfThickness.requestFocus();
    }

    @FXML
    private void deleteThickness(){
        PatternThicknessUI delThickness = listViewThickness.getSelectionModel().getSelectedItem();
        if (delThickness == null)
            return;
        if (getResponseDeleteDialog(1)==Dialog.Actions.OK){
            if (delThickness.getId() != 0){
                patternThicknessService.delete(patternThicknessServiceUI.getPatternThicknessFromUIModel(delThickness));
                LOGGER.debug("DELETE THICKNESS: pattern thickness has been deleted from DB");
            }else {
                LOGGER.debug("DELETE THICKNESS: pattern thickness has been deleted from list");
            }

            allThickness.remove(delThickness);
            listViewThickness.getSelectionModel().selectFirst();
            listViewThickness.fireEvent(EventFXUtil.getMouseClickEvent());
            listViewThickness.requestFocus();
        }
    }

    @FXML
    private void saveThickness(){
        setDisabledTextFields(true,txfThickness);
        btSaveThickness.setDisable(true);
        PatternThicknessUI selectedThicknessUI = listViewThickness.getSelectionModel().getSelectedItem();
        if (selectedThicknessUI == null)
            return;
        updateThicknessFromFields(selectedThicknessUI);
        PatternThickness patternThickness = patternThicknessServiceUI.getPatternThicknessFromUIModel(selectedThicknessUI);
        if (patternThickness.getPatternThicknessId()==null || patternThickness.getPatternThicknessId()==0){
            Long id = patternThicknessService.insert(patternThickness);
            LOGGER.debug("SAVE PATTERN THICKNESS: pattern thickness has been inserted in DB");
            selectedThicknessUI.setId(id);
        }else {
            patternThicknessService.update(patternThickness);
            LOGGER.debug("SAVE PATTERN THICKNESS: pattern thickness has been updated in DB");
        }
        allThickness.remove(selectedThicknessUI);
        allThickness.add(selectedThicknessUI);
        listViewThickness.getSelectionModel().select(selectedThicknessUI);

    }

    // --- Steel Type --
    @FXML
    private void addSteelType(){
        txfSteelType.clear();
        cbSteelTypeGroup.getSelectionModel().select(null);
        listViewSteelTypes.getSelectionModel().clearSelection();
        SteelTypeUI steelTypeUI = new SteelTypeUI();
        allSteelTypes.add(steelTypeUI);
        listViewSteelTypes.getSelectionModel().select(steelTypeUI);

        setDisabledSteelTypesFields(false);
        txfSteelType.requestFocus();
    }

    @FXML
    private void editSteelType(){
        SteelTypeUI selectedSteelType = listViewSteelTypes.getSelectionModel().getSelectedItem();
        if (selectedSteelType == null)
            return;
        listViewSteelTypes.getSelectionModel().clearSelection();
        listViewSteelTypes.getSelectionModel().select(selectedSteelType);
        setDisabledSteelTypesFields(false);
        txfSteelType.requestFocus();
    }

    @FXML
    private void deleteSteelType(){
        SteelTypeUI delSteelType = listViewSteelTypes.getSelectionModel().getSelectedItem();
        if (delSteelType == null)
            return;
        if (getResponseDeleteDialog(1)==Dialog.Actions.OK){
            if (delSteelType.getId() != 0){
                steelTypeService.delete(steelTypeServiceUI.getSteelTypeFromUIModel(delSteelType));
                LOGGER.debug("DELETE STEEL TYPE: steel type has been deleted from DB");
            }
            allSteelTypes.remove(delSteelType);
            listViewSteelTypes.getSelectionModel().selectFirst();
            listViewSteelTypes.fireEvent(EventFXUtil.getMouseClickEvent());
            listViewSteelTypes.requestFocus();
        }
    }

    @FXML
    private void saveSteelType(){
        setDisabledSteelTypesFields(true);
        btSaveSteelType.setDisable(true);
        SteelTypeUI selectedSteelType = listViewSteelTypes.getSelectionModel().getSelectedItem();
        if (selectedSteelType == null)
            return;
        updateSteelTypesFromFields(selectedSteelType);
        SteelType steelType = steelTypeServiceUI.getSteelTypeFromUIModel(selectedSteelType);
        if (steelType.getSteelTypeId()==null || steelType.getSteelTypeId()==0){
            Long id = steelTypeService.insert(steelType);
            LOGGER.debug("SAVE STEEL TYPE: steel types has been inserted in DB");
            selectedSteelType.setId(id);
        }else {
            steelTypeService.update(steelType);
            LOGGER.debug("SAVE STEEL TYPE: steel type has been updated in DB");
        }
        allSteelTypes.remove(selectedSteelType);
        allSteelTypes.add(selectedSteelType);
        listViewSteelTypes.getSelectionModel().select(selectedSteelType);

    }

    // --- Steel Group --
    @FXML
    private void addSteelGroup(){
        txfSteelGroup.clear();
        txtAreaSteelGroupDecription.clear();
        listViewSteelGroups.getSelectionModel().clearSelection();
        SteelGroupUI steelGroupUI = new SteelGroupUI();
        allSteelGroups.add(steelGroupUI);
        listViewSteelGroups.getSelectionModel().select(steelGroupUI);

        setDisabledSteelGroupField(false);
        txfSteelGroup.requestFocus();
    }

    @FXML
    private void editSteelGroup(){
        SteelGroupUI selectedSteelGroup = listViewSteelGroups.getSelectionModel().getSelectedItem();
        if (selectedSteelGroup == null)
            return;
        listViewSteelGroups.getSelectionModel().clearSelection();
        listViewSteelGroups.getSelectionModel().select(selectedSteelGroup);
        setDisabledSteelGroupField(false);
        txfSteelGroup.requestFocus();
    }

    @FXML
    private void saveSteelGroup(){
        setDisabledSteelGroupField(true);
        btSaveSteelGroup.setDisable(true);
        SteelGroupUI selectedSteelGroup = listViewSteelGroups.getSelectionModel().getSelectedItem();
        if (selectedSteelGroup == null)
            return;
        updateSteelGroupFromFields(selectedSteelGroup);
        SteelGroup steelGroup = steelGroupServiceUI.getSteelGroupFromUIModel(selectedSteelGroup);
        if (steelGroup.getSteelGroupId()==null || steelGroup.getSteelGroupId()==0){
            Long id = steelGroupService.insert(steelGroup);
            LOGGER.debug("SAVE STEEL GROUP: steel group has been inserted in DB");
            selectedSteelGroup.setId(id);
        }else {
            steelGroupService.update(steelGroup);
            LOGGER.debug("SAVE STEEL GROUP: steel group has been updated in DB");
        }
        allSteelGroups.remove(selectedSteelGroup);
        allSteelGroups.add(selectedSteelGroup);
        listViewSteelGroups.getSelectionModel().select(selectedSteelGroup);

    }

    @FXML
    private void deleteSteelGroup(){
        SteelGroupUI delSteelGroup = listViewSteelGroups.getSelectionModel().getSelectedItem();
        if (delSteelGroup == null)
            return;
        if (getResponseDeleteDialog(1)==Dialog.Actions.OK){
            if (delSteelGroup.getId() != 0){
                steelGroupService.delete(steelGroupServiceUI.getSteelGroupFromUIModel(delSteelGroup));
                LOGGER.debug("DELETE STEEL GROUP: steel type has been deleted from DB");
            }
            allSteelGroups.remove(delSteelGroup);
            listViewSteelGroups.getSelectionModel().selectFirst();
            listViewSteelGroups.fireEvent(EventFXUtil.getMouseClickEvent());
            listViewSteelGroups.requestFocus();
        }
    }

    // --- Weld Method --
    @FXML
    private void addWeldMethod(){
        clearTextFields(txfWeldMethodName, txfWeldMethodCode);
        listViewWeldMethod.getSelectionModel().clearSelection();
        WeldMethodUI weldMethodUI = new WeldMethodUI();
        allWeldMethods.add(weldMethodUI);
        listViewWeldMethod.getSelectionModel().select(weldMethodUI);

        setDisabledTextFields(false, txfWeldMethodCode, txfWeldMethodName);
        txfWeldMethodCode.requestFocus();
    }

    @FXML
    private void editWeldMethod(){
        WeldMethodUI selectedWeldMethod = listViewWeldMethod.getSelectionModel().getSelectedItem();
        if (selectedWeldMethod == null)
            return;
        listViewWeldMethod.getSelectionModel().clearSelection();
        listViewWeldMethod.getSelectionModel().select(selectedWeldMethod);
        setDisabledTextFields(false, txfWeldMethodCode, txfWeldMethodName);
        txfWeldMethodCode.requestFocus();
    }

    @FXML
    private void deleteWeldMethod(){
        WeldMethodUI delWeldMethod = listViewWeldMethod.getSelectionModel().getSelectedItem();
        if (delWeldMethod == null)
            return;
        if (getResponseDeleteDialog(1)==Dialog.Actions.OK){
            if (delWeldMethod.getId() != 0){
                weldMethodService.delete(weldMethodServiceUI.getWeldMethodFromUI(delWeldMethod));
                LOGGER.debug("DELETE WELD METHOD: weld method has been deleted from DB");
            }
            allWeldMethods.remove(delWeldMethod);
            listViewWeldMethod.getSelectionModel().selectFirst();
            listViewWeldMethod.fireEvent(EventFXUtil.getMouseClickEvent());
            listViewWeldMethod.requestFocus();
        }
    }

    @FXML
    private void saveWeldMethod(){
        setDisabledTextFields(true,txfWeldMethodName, txfWeldMethodCode);
        btSaveWeldMethod.setDisable(true);
        WeldMethodUI selWeldMethod = listViewWeldMethod.getSelectionModel().getSelectedItem();
        if (selWeldMethod == null)
            return;
        updateWeldMethodFromFields(selWeldMethod);
        WeldMethod weldMethod = weldMethodServiceUI.getWeldMethodFromUI(selWeldMethod);
        if (weldMethod.getWeldMethodId()==null || weldMethod.getWeldMethodId()==0){
            Long id = weldMethodService.insert(weldMethod);
            LOGGER.debug("SAVE WELD METHOD: weld method has been inserted in DB");
            selWeldMethod.setId(id);
        }else {
            weldMethodService.update(weldMethod);
            LOGGER.debug("SAVE WELD METHOD: weld method has been updated in DB");
        }
        allWeldMethods.remove(selWeldMethod);
        allWeldMethods.add(selWeldMethod);
        listViewWeldMethod.getSelectionModel().select(selWeldMethod);
    }

    // --- Electrode --
    @FXML
    private void addElectrode(){
        clearTextFields(txfElectrodeType);
        listViewElectrode.getSelectionModel().clearSelection();
        ElectrodeUI electrodeUI = new ElectrodeUI();
        allElectrodes.add(electrodeUI);
        listViewElectrode.getSelectionModel().select(electrodeUI);

        setDisabledTextFields(false, txfElectrodeType);
        txfElectrodeType.requestFocus();
    }

    @FXML
    private void deleteElectrode(){
        ElectrodeUI delElectrode = listViewElectrode.getSelectionModel().getSelectedItem();
        if (delElectrode == null)
            return;
        if (getResponseDeleteDialog(1)==Dialog.Actions.OK){
            if (delElectrode.getId() != 0){
                electrodeService.delete(electrodeServiceUI.getElectrodeFromUIModel(delElectrode));
                LOGGER.debug("DELETE ELECTRODE: elctrode has been deleted from DB");
            }
            allElectrodes.remove(delElectrode);
            listViewElectrode.getSelectionModel().selectFirst();
            listViewElectrode.fireEvent(EventFXUtil.getMouseClickEvent());
            listViewElectrode.requestFocus();
        }
    }

    @FXML
    private void editElectrode(){
        ElectrodeUI selectedElectrode = listViewElectrode.getSelectionModel().getSelectedItem();
        if (selectedElectrode == null)
            return;
        listViewElectrode.getSelectionModel().clearSelection();
        listViewElectrode.getSelectionModel().select(selectedElectrode);
        setDisabledTextFields(false, txfElectrodeType);
        txfElectrodeType.requestFocus();
    }
    @FXML
    private void saveElectrode(){
        setDisabledTextFields(true,txfElectrodeType);
        btSaveElectrode.setDisable(true);
        ElectrodeUI selectedElectrode = listViewElectrode.getSelectionModel().getSelectedItem();
        if (selectedElectrode == null)
            return;
        updateElectrodesFromFields(selectedElectrode);
        Electrode electrode = electrodeServiceUI.getElectrodeFromUIModel(selectedElectrode);
        if (electrode.getElectrodeId() == null || electrode.getElectrodeId() == 0){
            Long id = electrodeService.insert(electrode);
            LOGGER.debug("SAVE ELECTRODE: electrode has been inserted in DB");
            selectedElectrode.setId(id);
        }else {
            electrodeService.update(electrode);
            LOGGER.debug("SAVE ELECTRODE: electrode has been updated in DB");
        }
        allElectrodes.remove(selectedElectrode);
        allElectrodes.add(selectedElectrode);
        listViewElectrode.getSelectionModel().select(selectedElectrode);
    }

    // --- Weld Wire --
    @FXML
    private void addWeldWire(){
        clearTextFields(txfWeldWireType);
        listViewWeldWire.getSelectionModel().clearSelection();
        WeldWireUI weldWireUI = new WeldWireUI();
        allWeldWires.add(weldWireUI);
        listViewWeldWire.getSelectionModel().select(weldWireUI);

        setDisabledTextFields(false, txfWeldWireType);
        txfWeldWireType.requestFocus();
    }

    @FXML
    private void editWeldWire(){
        WeldWireUI selWeldWire = listViewWeldWire.getSelectionModel().getSelectedItem();
        if (selWeldWire == null)
            return;
        listViewWeldWire.getSelectionModel().clearSelection();
        listViewWeldWire.getSelectionModel().select(selWeldWire);
        setDisabledTextFields(false, txfWeldWireType);
        txfWeldWireType.requestFocus();
    }

    @FXML
    private void deleteWeldWire() {
        WeldWireUI delWeldWire = listViewWeldWire.getSelectionModel().getSelectedItem();
        if (delWeldWire == null)
            return;
        if (getResponseDeleteDialog(1) == Dialog.Actions.OK) {
            if (delWeldWire.getId() != 0) {
                weldWireService.delete(weldWireServiceUI.getWeldWireFromUIModel(delWeldWire));
                LOGGER.debug("DELETE WELD WIRE: weld wire has been deleted from DB");
            }
            allWeldWires.remove(delWeldWire);
            listViewWeldWire.getSelectionModel().selectFirst();
            listViewWeldWire.fireEvent(EventFXUtil.getMouseClickEvent());
            listViewWeldWire.requestFocus();
        }
    }

    @FXML
    private void saveWeldWire(){
        setDisabledTextFields(true,txfWeldWireType);
        btSaveWeldWire.setDisable(true);
        WeldWireUI selectedWeldWire = listViewWeldWire.getSelectionModel().getSelectedItem();
        if (selectedWeldWire == null)
            return;
        updateWeldWireFromFields(selectedWeldWire);
        WeldWire weldWire = weldWireServiceUI.getWeldWireFromUIModel(selectedWeldWire);
        if (weldWire.getWeldWireId() == null || weldWire.getWeldWireId() == 0){
            Long id = weldWireService.insert(weldWire);
            LOGGER.debug("SAVE WELD WIRE: weld wire has been inserted in DB");
            selectedWeldWire.setId(id);
        }else {
            weldWireService.update(weldWire);
            LOGGER.debug("SAVE WELD WIRE: weld wire has been updated in DB");
        }
        allWeldWires.remove(selectedWeldWire);
        allWeldWires.add(selectedWeldWire);
        listViewWeldWire.getSelectionModel().select(selectedWeldWire);
    }

    // --- Weld Gas --
    @FXML
    private void addWeldGas(){
        clearTextFields(txfWeldGasType);
        listViewWeldGas.getSelectionModel().clearSelection();
        WeldGasUI weldGasUI = new WeldGasUI();
        allWeldGases.add(weldGasUI);
        listViewWeldGas.getSelectionModel().select(weldGasUI);

        setDisabledTextFields(false, txfWeldGasType);
        txfWeldGasType.requestFocus();
    }

    @FXML
    private void editWeldGas(){
        WeldGasUI selWeldGas = listViewWeldGas.getSelectionModel().getSelectedItem();
        if (selWeldGas == null)
            return;
        listViewWeldGas.getSelectionModel().clearSelection();
        listViewWeldGas.getSelectionModel().select(selWeldGas);
        setDisabledTextFields(false, txfWeldGasType);
        txfWeldGasType.requestFocus();
    }

    @FXML
    private void deleteWeldGas(){
        WeldGasUI delWeldGas = listViewWeldGas.getSelectionModel().getSelectedItem();
        if (delWeldGas == null)
            return;
        if (getResponseDeleteDialog(1) == Dialog.Actions.OK) {
            if (delWeldGas.getId() != 0) {
                weldGasService.delete(weldGasServiceUI.getWeldGasFromUIModel(delWeldGas));
                LOGGER.debug("DELETE WELD GAS: weld gas has been deleted from DB");
            }
            allWeldGases.remove(delWeldGas);
            listViewWeldGas.getSelectionModel().selectFirst();
            listViewWeldGas.fireEvent(EventFXUtil.getMouseClickEvent());
            listViewWeldGas.requestFocus();
        }
    }

    @FXML
    private void saveWeldGas(){
        setDisabledTextFields(true,txfWeldGasType);
        btSaveWeldGas.setDisable(true);
        WeldGasUI selectedWeldGas = listViewWeldGas.getSelectionModel().getSelectedItem();
        if (selectedWeldGas == null)
            return;
        updateWeldGasFromFields(selectedWeldGas);
        WeldGas weldGas = weldGasServiceUI.getWeldGasFromUIModel(selectedWeldGas);
        if (weldGas.getWeldGasId() == null || weldGas.getWeldGasId() == 0){
            Long id = weldGasService.insert(weldGas);
            LOGGER.debug("SAVE WELD GAS: weld gas has been inserted in DB");
            selectedWeldGas.setId(id);
        }else {
            weldGasService.update(weldGas);
            LOGGER.debug("SAVE WELD GAS: weld gas has been updated in DB");
        }
        allWeldGases.remove(selectedWeldGas);
        allWeldGases.add(selectedWeldGas);
        listViewWeldGas.getSelectionModel().select(selectedWeldGas);
    }

    // --- Weld Position --
    @FXML
    private void addWeldPosition(){
        clearTextFields(txfWeldPositionCode);
        clearTextAreas(txtAreaWeldPositionDescription);
        listViewWeldPosition.getSelectionModel().clearSelection();
        WeldPositionUI weldPositionUI = new WeldPositionUI();
        allWeldPositions.add(weldPositionUI);
        listViewWeldPosition.getSelectionModel().select(weldPositionUI);

        setDisabledWeldPositionsFields(false);
        txfWeldPositionCode.requestFocus();
    }

    @FXML
    private void editWeldPosition(){
        WeldPositionUI selWeldPosition = listViewWeldPosition.getSelectionModel().getSelectedItem();
        if (selWeldPosition == null)
            return;
        listViewWeldPosition.getSelectionModel().clearSelection();
        listViewWeldPosition.getSelectionModel().select(selWeldPosition);
        setDisabledWeldPositionsFields(false);
        txfWeldPositionCode.requestFocus();
    }

    @FXML
    private void deleteWeldPosition(){
        WeldPositionUI delWeldPosition = listViewWeldPosition.getSelectionModel().getSelectedItem();
        if (delWeldPosition == null)
            return;
        if (getResponseDeleteDialog(1) == Dialog.Actions.OK) {
            if (delWeldPosition.getId() != 0) {
                weldPositionService.delete(weldPositionServiceUI.getWeldPositionFromUIModel(delWeldPosition));
                LOGGER.debug("DELETE WELD POSITION: weld position has been deleted from DB");
            }
            allWeldPositions.remove(delWeldPosition);
            listViewWeldPosition.getSelectionModel().selectFirst();
            listViewWeldPosition.fireEvent(EventFXUtil.getMouseClickEvent());
            listViewWeldPosition.requestFocus();
        }
    }

    @FXML
    private void saveWeldPosition(){
        setDisabledWeldPositionsFields(true);
        btSaveWeldPosition.setDisable(true);
        WeldPositionUI selectedWeldPosition = listViewWeldPosition.getSelectionModel().getSelectedItem();
        if (selectedWeldPosition == null)
            return;
        updateWeldPositionFromFields(selectedWeldPosition);
        WeldPosition weldPosition = weldPositionServiceUI.getWeldPositionFromUIModel(selectedWeldPosition);
        if (weldPosition.getWeldPositionId() == null || weldPosition.getWeldPositionId() == 0){
            Long id = weldPositionService.insert(weldPosition);
            LOGGER.debug("SAVE WELD POSITION: weld position has been inserted in DB");
            selectedWeldPosition.setId(id);
        }else {
            weldPositionService.update(weldPosition);
            LOGGER.debug("SAVE WELD POSITION: weld position has been updated in DB");
        }
        allWeldPositions.remove(selectedWeldPosition);
        allWeldPositions.add(selectedWeldPosition);
        listViewWeldPosition.getSelectionModel().select(selectedWeldPosition);
    }

    // --- Education --
    @FXML
    private void addEducation(){
        clearTextFields(txfEducationType);
        listViewEducation.getSelectionModel().clearSelection();
        EducationUI educationUI = new EducationUI();
        allEducations.add(educationUI);
        listViewEducation.getSelectionModel().select(educationUI);

        setDisabledTextFields(false, txfEducationType);
        txfEducationType.requestFocus();
    }

    @FXML
    private void editEducation(){
        EducationUI selEducation = listViewEducation.getSelectionModel().getSelectedItem();
        if (selEducation == null)
            return;
        listViewEducation.getSelectionModel().clearSelection();
        listViewEducation.getSelectionModel().select(selEducation);
        setDisabledTextFields(false, txfEducationType);
        txfEducationType.requestFocus();
    }

    @FXML
    private void deleteEducation(){
        EducationUI delEducation = listViewEducation.getSelectionModel().getSelectedItem();
        if (delEducation == null)
            return;
        if (getResponseDeleteDialog(1) == Dialog.Actions.OK) {
            if (delEducation.getId() != 0) {
                educationService.delete(educationServiceUI.getEducationFromUIModel(delEducation));
                LOGGER.debug("DELETE EDUCATION: education has been deleted from DB");
            }
            allEducations.remove(delEducation);
            listViewEducation.getSelectionModel().selectFirst();
            listViewEducation.fireEvent(EventFXUtil.getMouseClickEvent());
            listViewEducation.requestFocus();
        }
    }

    @FXML
    private void saveEducation(){
        setDisabledTextFields(true,txfEducationType);
        btSaveEducation.setDisable(true);
        EducationUI selectedEducation = listViewEducation.getSelectionModel().getSelectedItem();
        if (selectedEducation == null)
            return;
        updateEducationFromFields(selectedEducation);
        Education education = educationServiceUI.getEducationFromUIModel(selectedEducation);
        if (education.getEducationId() == null || education.getEducationId() == 0){
            Long id = educationService.insert(education);
            LOGGER.debug("SAVE EDUCATION: education has been inserted in DB");
            selectedEducation.setId(id);
        }else {
            educationService.update(education);
            LOGGER.debug("SAVE EDUCATION: education has been updated in DB");
        }
        allEducations.remove(selectedEducation);
        allEducations.add(selectedEducation);
        listViewEducation.getSelectionModel().select(selectedEducation);
    }

    // --- Qualification --
    @FXML
    private void addQualification(){
        clearTextFields(txfQualificationType);
        listViewQualification.getSelectionModel().clearSelection();
        QualificationUI qualificationUI = new QualificationUI();
        allQualifications.add(qualificationUI);
        listViewQualification.getSelectionModel().select(qualificationUI);

        setDisabledTextFields(false, txfQualificationType);
        txfQualificationType.requestFocus();
    }

    @FXML
    private void editQualification(){
        QualificationUI selQualification = listViewQualification.getSelectionModel().getSelectedItem();
        if (selQualification == null)
            return;
        listViewQualification.getSelectionModel().clearSelection();
        listViewQualification.getSelectionModel().select(selQualification);
        setDisabledTextFields(false, txfQualificationType);
        txfQualificationType.requestFocus();
    }

    @FXML
    private void deleteQualification(){
        QualificationUI delQualification = listViewQualification.getSelectionModel().getSelectedItem();
        if (delQualification == null)
            return;
        if (getResponseDeleteDialog(1) == Dialog.Actions.OK) {
            if (delQualification.getId() != 0) {
                qualificationService.delete(qualificationServiceUI.getQualificationFromUIModel(delQualification));
                LOGGER.debug("DELETE QUALIFICATION: qualification has been deleted from DB");
            }
            allQualifications.remove(delQualification);
            listViewQualification.getSelectionModel().selectFirst();
            listViewQualification.fireEvent(EventFXUtil.getMouseClickEvent());
            listViewQualification.requestFocus();
        }
    }

    @FXML
    private void saveQualification(){
        setDisabledTextFields(true,txfQualificationType);
        btSaveQualification.setDisable(true);
        QualificationUI selectedQualification = listViewQualification.getSelectionModel().getSelectedItem();
        if (selectedQualification == null)
            return;
        updateQualificationFromFields(selectedQualification);
        Qualification qualification = qualificationServiceUI.getQualificationFromUIModel(selectedQualification);
        if (qualification.getQualificationId() == null || qualification.getQualificationId() == 0){
            Long id = qualificationService.insert(qualification);
            LOGGER.debug("SAVE QUALIFICATION: qualification has been inserted in DB");
            selectedQualification.setId(id);
        }else {
            qualificationService.update(qualification);
            LOGGER.debug("SAVE QUALIFICATION: qualification has been updated in DB");
        }
        allQualifications.remove(selectedQualification);
        allQualifications.add(selectedQualification);
        listViewQualification.getSelectionModel().select(selectedQualification);
    }

    // --- Job --
    @FXML
    private void addJob(){
        clearTextFields(txfJobType);
        listViewJob.getSelectionModel().clearSelection();
        JobUI jobUI = new JobUI();
        allJobs.add(jobUI);
        listViewJob.getSelectionModel().select(jobUI);

        setDisabledTextFields(false, txfJobType);
        txfJobType.requestFocus();
    }

    @FXML
    private void editJob(){
        JobUI selJob = listViewJob.getSelectionModel().getSelectedItem();
        if (selJob == null)
            return;
        listViewJob.getSelectionModel().clearSelection();
        listViewJob.getSelectionModel().select(selJob);
        setDisabledTextFields(false, txfJobType);
        txfJobType.requestFocus();
    }

    @FXML
    private void deleteJob(){
        JobUI delJob = listViewJob.getSelectionModel().getSelectedItem();
        if (delJob == null)
            return;
        if (getResponseDeleteDialog(1) == Dialog.Actions.OK) {
            if (delJob.getId() != 0) {
                jobService.delete(jobServiceUI.getJobFromUIModel(delJob));
                LOGGER.debug("DELETE JOB: job has been deleted from DB");
            }
            allJobs.remove(delJob);
            listViewJob.getSelectionModel().selectFirst();
            listViewJob.fireEvent(EventFXUtil.getMouseClickEvent());
            listViewJob.requestFocus();
        }
    }

    @FXML
    private void saveJob(){
        setDisabledTextFields(true,txfJobType);
        btSaveJob.setDisable(true);
        JobUI selectedJob = listViewJob.getSelectionModel().getSelectedItem();
        if (selectedJob == null)
            return;
        updateJobFromFields(selectedJob);
        Job job = jobServiceUI.getJobFromUIModel(selectedJob);
        if (job.getJobId() == null || job.getJobId() == 0){
            Long id = jobService.insert(job);
            LOGGER.debug("SAVE JOB: job has been inserted in DB");
            selectedJob.setId(id);
        }else {
            jobService.update(job);
            LOGGER.debug("SAVE JOB: job has been updated in DB");
        }
        allJobs.remove(selectedJob);
        allJobs.add(selectedJob);
        listViewJob.getSelectionModel().select(selectedJob);
    }


    //Utilities -------------------------------------------------------------------

    private void selectFirstTableRecord(TableView tableView){
        tableView.getSelectionModel().clearSelection();
        tableView.getSelectionModel().selectFirst();
        tableView.fireEvent(EventFXUtil.getMouseClickEvent());
        tableView.requestFocus();
    }
    private void selectLastTableRecord(TableView tableView){
        tableView.getSelectionModel().clearSelection();
        tableView.getSelectionModel().selectLast();
        tableView.fireEvent(EventFXUtil.getMouseClickEvent());
        tableView.requestFocus();
    }
    private void selectNextTableRecord(TableView tableView){
        tableView.getSelectionModel().selectNext();
        Object selectedItem = tableView.getSelectionModel().getSelectedItem();
        tableView.getSelectionModel().clearSelection();
        tableView.getSelectionModel().select(selectedItem);
        tableView.fireEvent(EventFXUtil.getMouseClickEvent());
        tableView.requestFocus();
    }

    private void selectPrevTableRecord(TableView tableView){
        tableView.getSelectionModel().selectPrevious();
        Object selectedItem = tableView.getSelectionModel().getSelectedItem();
        tableView.getSelectionModel().clearSelection();
        tableView.getSelectionModel().select(selectedItem);
        tableView.fireEvent(EventFXUtil.getMouseClickEvent());
        tableView.requestFocus();
    }

    private void setActiveListView(ListView listView){
        listView.getSelectionModel().selectFirst();
        listView.fireEvent(EventFXUtil.getMouseClickEvent());
        listView.requestFocus();
    }

    private void  initListView(ListView listView, ObservableList items){
        listView.setItems(items);
        listView.setEditable(false);
        listView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        ListViewEventHandler eventHandler = new ListViewEventHandler();
        listView.addEventHandler(Event.ANY, eventHandler);
        listView.getSelectionModel().selectFirst();
    }

    private void initTabPanes(Pane... panes){
        for (Pane p : panes)
            p.setStyle(STYLE_TAB_BACKGROUND);
    }

    private Action getResponseDeleteDialog(int countOfDeletingRecords){
        Action response = Dialogs.create().owner(mainPropertiesPane.getScene().getWindow())
                .title("Удаление записей")
                .masthead("Сделан выбор записей для удаления")
                .message("Удалить выбранные записи? ("+countOfDeletingRecords+"шт.)")
                .actions(org.controlsfx.dialog.Dialog.Actions.OK, org.controlsfx.dialog.Dialog.Actions.CANCEL)
                .showConfirm();
        return response;
    }

    private void clearTextFields(TextField ... textFields){
        for (TextField tf : textFields)
            tf.clear();
    }

    private void clearTextAreas(TextArea ... textAreas){
        for (TextArea ta : textAreas)
            ta.clear();
    }

    private void setDisabledTextFields(boolean disabled, TextField ... textFields){
        if (disabled){
            for (TextField tf : textFields){
                tf.setEditable(false);
                tf.setStyle(STYLE_NOT_EDITABLE_BACKGROUND);
            }
        }else {
            for (TextField tf : textFields){
                tf.setEditable(true);
                tf.setStyle("");
            }
        }
    }
    private void setDisabledComboBoxes(boolean disabled, ComboBox ... comboBoxes){
        if (disabled){
            for (ComboBox cb : comboBoxes) {
                cb.setDisable(true);
                cb.setStyle(STYLE_NOT_EDIT_COMBOBOX);
            }
        }else
            for (ComboBox cb : comboBoxes) {
                cb.setDisable(false);
                cb.setStyle("");
            }
    }

    private void setDisabledTextAreas(boolean disable, TextArea ... textAreas){
        if (disable){
            for (TextArea ta : textAreas){
                ta.setEditable(false);
                ta.setStyle(STYLE_NOT_EDITABLE_BACKGROUND);
                ta.setOpacity(0.7);
            }
        }else {
            for (TextArea ta : textAreas){
                ta.setEditable(true);
                ta.setStyle("");
                ta.setOpacity(1.0);
            }
        }
    }

    private boolean isEventIsSelectedKey(Event event){
        if (event.getClass().equals(KeyEvent.class)) {
            if (((KeyEvent) event).getCode().equals(KeyCode.UP) ||
                    ((KeyEvent) event).getCode().equals(KeyCode.DOWN) ||
                    ((KeyEvent) event).getCode().equals(KeyCode.PAGE_UP) ||
                    ((KeyEvent) event).getCode().equals(KeyCode.PAGE_DOWN) ||
                    ((KeyEvent) event).getCode().equals(KeyCode.HOME) ||
                    ((KeyEvent) event).getCode().equals(KeyCode.END)){
                return true;
            }
        }
        return false;
    }

    private boolean isEventIsSelectedMouse(Event event){
        if (event.getClass().equals(MouseEvent.class)) {
            if (((MouseEvent)event).getEventType().equals(MouseEvent.MOUSE_CLICKED)) {
                return true;
            }
        }
        return false;
    }



    private class ListViewEventHandler implements EventHandler<Event>{

        @Override
        public void handle(Event event) {
            ListView source = (ListView)event.getSource();
            //Key event UP or DOWN or PAGE_UP or PAGE_DOWN
            if(isEventIsSelectedKey(event) || isEventIsSelectedMouse(event)){
                if (source.equals(listViewWeldPatternsTypes)){
                    doSelectWeldPatternType();
                    return;
                }
                if (source.equals(listViewDiameters)){
                    doSelectDiameter();
                    return;
                }
                if (source.equals(listViewThickness)){
                    doSelectThickness();
                    return;
                }
                if (source.equals(listViewSteelTypes)){
                    doSelectSteelType();
                    return;
                }
                if (source.equals(listViewSteelGroups)){
                    doSelectSteelGroup();
                    return;
                }
                if (source.equals(listViewWeldMethod)){
                    doSelectWeldMethod();
                    return;
                }
                if (source.equals(listViewElectrode)){
                    doSelectElectrode();
                    return;
                }
                if (source.equals(listViewWeldWire)){
                    doSelectWeldWire();
                    return;
                }
                if (source.equals(listViewWeldGas)){
                    doSelectWeldGas();
                    return;
                }
                if (source.equals(listViewWeldPosition)){
                    doSelectWeldPosition();
                    return;
                }
                if (source.equals(listViewEducation)){
                    doSelectEducation();
                    return;
                }
                if (source.equals(listViewQualification)){
                    doSelectQualification();
                    return;
                }
                if (source.equals(listViewJob)){
                    doSelectJob();
                    return;
                }
            }

        }
        private void doSelectDiameter(){
            PatternDiameterUI selectedDiameter = listViewDiameters.getSelectionModel().getSelectedItem();
            if (selectedDiameter == null)
                return;
            setDisabledTextFields(true,txfDiameter);
            txfDiameter.setText(selectedDiameter.getDiameter()+"");
            btSaveDiameter.setDisable(true);
        }

        private void doSelectWeldPatternType(){
            WeldDetailUI selectedWeldDetail = listViewWeldPatternsTypes.getSelectionModel().getSelectedItem();
            if (selectedWeldDetail==null)
                return;
            setDisabledWeldPatternTypesFields(true);
            txfWeldPatternTypeName.setText(selectedWeldDetail.getType());
            txfWeldPatternTypeCode.setText(selectedWeldDetail.getCode());
            btSaveWeldPatternType.setDisable(true);
        }

        private void doSelectThickness(){
            PatternThicknessUI selectedThickness = listViewThickness.getSelectionModel().getSelectedItem();
            if (selectedThickness == null)
                return;
            setDisabledTextFields(true,txfThickness);
            txfThickness.setText(selectedThickness.getThickness()+"");
            btSaveThickness.setDisable(true);
        }

        private void doSelectSteelType(){
            SteelTypeUI selectedSteelType = listViewSteelTypes.getSelectionModel().getSelectedItem();
            if (selectedSteelType == null)
                return;
            setDisabledSteelTypesFields(true);
            txfSteelType.setText(selectedSteelType.getType());
            cbSteelTypeGroup.getSelectionModel().select(selectedSteelType.getSteelGroupUI());
            btSaveSteelType.setDisable(true);
        }

        private void doSelectSteelGroup(){
            SteelGroupUI steelGroup = listViewSteelGroups.getSelectionModel().getSelectedItem();
            if (steelGroup == null)
                return;
            setDisabledSteelGroupField(true);
            txfSteelGroup.setText(steelGroup.getGroup());
            txtAreaSteelGroupDecription.setText(steelGroup.getDescription());
            btSaveSteelGroup.setDisable(true);
        }

        private void doSelectWeldMethod(){
            WeldMethodUI weldMethodUI = listViewWeldMethod.getSelectionModel().getSelectedItem();
            if (weldMethodUI == null)
                return;
            setDisabledTextFields(true, txfWeldMethodName, txfWeldMethodCode);
            txfWeldMethodName.setText(weldMethodUI.getName());
            txfWeldMethodCode.setText(weldMethodUI.getCode());
            btSaveWeldMethod.setDisable(true);
        }

        private void doSelectElectrode(){
            ElectrodeUI electrodeUI = listViewElectrode.getSelectionModel().getSelectedItem();
            if (electrodeUI == null)
                return;
            setDisabledTextFields(true, txfElectrodeType);
            txfElectrodeType.setText(electrodeUI.getType());
            btSaveElectrode.setDisable(true);
        }

        private void doSelectWeldWire(){
            WeldWireUI selectedWeldWire = listViewWeldWire.getSelectionModel().getSelectedItem();
            if (selectedWeldWire == null)
                return;
            setDisabledTextFields(true, txfWeldWireType);
            txfWeldWireType.setText(selectedWeldWire.getType());
            btSaveWeldWire.setDisable(true);
        }

        private void doSelectWeldGas(){
            WeldGasUI selectedWeldGas = listViewWeldGas.getSelectionModel().getSelectedItem();
            if (selectedWeldGas == null)
                return;
            setDisabledTextFields(true, txfWeldGasType);
            txfWeldGasType.setText(selectedWeldGas.getType());
            btSaveWeldGas.setDisable(true);
        }

        private void doSelectWeldPosition(){
            WeldPositionUI selectedWeldPosition = listViewWeldPosition.getSelectionModel().getSelectedItem();
            if (selectedWeldPosition == null)
                return;
            setDisabledWeldPositionsFields(true);
            txfWeldPositionCode.setText(selectedWeldPosition.getCode());
            txtAreaWeldPositionDescription.setText(selectedWeldPosition.getType());
            btSaveWeldPosition.setDisable(true);
        }

        private void doSelectEducation(){
            EducationUI selectedEducation = listViewEducation.getSelectionModel().getSelectedItem();
            if (selectedEducation == null)
                return;
            setDisabledTextFields(true, txfEducationType);
            txfEducationType.setText(selectedEducation.getType());
            btSaveEducation.setDisable(true);
        }

        private void doSelectQualification(){
            QualificationUI selectedQualification = listViewQualification.getSelectionModel().getSelectedItem();
            if (selectedQualification == null)
                return;
            setDisabledTextFields(true, txfQualificationType);
            txfQualificationType.setText(selectedQualification.getType());
            btSaveQualification.setDisable(true);
        }

        private void doSelectJob(){
            JobUI selectedJob = listViewJob.getSelectionModel().getSelectedItem();
            if (selectedJob == null)
                return;
            setDisabledTextFields(true, txfJobType);
            txfJobType.setText(selectedJob.getName());
            btSaveJob.setDisable(true);
        }


    }

    private class TableViewEventHandler implements EventHandler<javafx.event.Event>{

        @Override
        public void handle(javafx.event.Event event) {
            TableView source = (TableView) event.getSource();

            if (isEventIsSelectedMouse(event) || isEventIsSelectedKey(event)) {
                if (source.equals(tableViewOrganizations)) {
                        doSelectOrganization();
                        return;
                }
                if (source.equals(tableViewCommissions)){
                    doSelectCommission();
                    return;
                }
            }
        }

        private void doSelectOrganization(){
            OrganizationUI selectedOrganization = tableViewOrganizations.getSelectionModel().getSelectedItem();
            if (selectedOrganization == null)
                return;
            setDisabledOrganizationFields(true);
            txfOrganizationName.setText(selectedOrganization.getName());
            txfOrganizationAdress.setText(selectedOrganization.getAdress());
            txfOrganizationPhone.setText(selectedOrganization.getPhone());
            btSave.setDisable(true);
        }

        private void doSelectCommission(){
            CommissionCertificationUI selectedCommiss = tableViewCommissions.getSelectionModel().getSelectedItem();
            if (selectedCommiss == null)
                return;
            setDisabledComboBoxes(true, cbCommissionHead, cbCommissionWeldSpec,
                    cbCommissionNDTSpec, cbCommissionSafetySpec);
            cbCommissionHead.getSelectionModel().select(selectedCommiss.getHead());
            cbCommissionWeldSpec.getSelectionModel().select(selectedCommiss.getWeldSpecialist());
            cbCommissionNDTSpec.getSelectionModel().select(selectedCommiss.getNdtSpecialist());
            cbCommissionSafetySpec.getSelectionModel().select(selectedCommiss.getSafetySpecialist());
            btSave.setDisable(true);
        }
    }

    private class TextFieldValidateListener implements InvalidationListener{

        @Override
        public void invalidated(Observable observable) {
            LOGGER.debug("VALIDATE LISTENER: invalidation in textField");
            readyToSave();
        }

        private void readyToSave(){
            Tab selectedTab = tabPaneAllProperties.getSelectionModel().getSelectedItem();
            if (selectedTab == null)
                return;

            if (selectedTab.equals(tabOrganizations)) {
                if (txfOrganizationName.isEditable()) {
                    btSave.setDisable(false);
                    return;
                }
            }
            if (selectedTab.equals(tabCommission)){
                if (!cbCommissionHead.isDisabled()){
                    btSave.setDisable(false);
                    return;
                }
            }
            if (selectedTab.equals(tabWeldPatterns)) {
                if (txfWeldPatternTypeCode.isEditable()) {
                    btSaveWeldPatternType.setDisable(false);
                    return;
                }

                if (txfDiameter.isEditable()){
                    btSaveDiameter.setDisable(false);
                    return;
                }

                if (txfThickness.isEditable()){
                    btSaveThickness.setDisable(false);
                    return;
                }

                if (txfSteelType.isEditable()){
                    btSaveSteelType.setDisable(false);
                    return;
                }
                if (txfSteelGroup.isEditable()){
                    btSaveSteelGroup.setDisable(false);
                    return;
                }
            }
            if (selectedTab.equals(tabWeld)){
                if (txfWeldMethodCode.isEditable()){
                    btSaveWeldMethod.setDisable(false);
                    return;
                }
                if (txfElectrodeType.isEditable()){
                    btSaveElectrode.setDisable(false);
                    return;
                }
                if (txfWeldWireType.isEditable()){
                    btSaveWeldWire.setDisable(false);
                    return;
                }
                if (txfWeldGasType.isEditable()){
                    btSaveWeldGas.setDisable(false);
                    return;
                }
                if (txfWeldPositionCode.isEditable()){
                    btSaveWeldPosition.setDisable(false);
                    return;
                }
            }
            if (selectedTab.equals(tabEducationEtc)){
                if (txfEducationType.isEditable()){
                    btSaveEducation.setDisable(false);
                    return;
                }
                if (txfQualificationType.isEditable()){
                    btSaveQualification.setDisable(false);
                    return;
                }
                if (txfJobType.isEditable()){
                    btSaveJob.setDisable(false);
                    return;
                }
            }

        }
    }

    private class SearchTextFieldValidateListener implements InvalidationListener{
        @Override
        public void invalidated(Observable observable) {
            Tab selectedTab = tabPaneAllProperties.getSelectionModel().getSelectedItem();
            if (selectedTab==null)
                return;
            if (selectedTab.equals(tabOrganizations)){
                searchOrganization();
            }
        }

        private void searchOrganization(){
            if (txfSearchOrganization.textProperty().get().isEmpty()){
                initAllOrganizationsList();
                tableViewOrganizations.setItems(allOrganization);
                return;
            }

            ObservableList<OrganizationUI> filteredList = FXCollections.observableArrayList();
            ObservableList<TableColumn<OrganizationUI,?>> columns = tableViewOrganizations.getColumns();

            for (int i = 0; i<allOrganization.size(); i++){

                for (int j = 0; j<columns.size(); j++){
                    TableColumn<OrganizationUI,?> column = columns.get(j);
                    String cellValue = column.getCellData(allOrganization.get(i)).toString();
                    cellValue = cellValue.toLowerCase();
                    if (cellValue.contains(txfSearchOrganization.textProperty().get().toLowerCase())){
                        filteredList.add(allOrganization.get(i));
                        break;
                    }
                }
            }
            tableViewOrganizations.setItems(filteredList);
            if (!filteredList.isEmpty()){
                tableViewOrganizations.getSelectionModel().selectFirst();
            }
        }
    }
}
