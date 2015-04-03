package com.lvg.weldercenter.ui.control;

import com.lvg.weldercenter.models.*;
import com.lvg.weldercenter.services.*;
import com.lvg.weldercenter.spring.factories.ServiceFactory;
import com.lvg.weldercenter.spring.factories.ServiceUIFactory;
import com.lvg.weldercenter.ui.entities.*;
import com.lvg.weldercenter.ui.servicesui.*;
import com.lvg.weldercenter.ui.util.DateUtil;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import org.apache.log4j.Logger;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by Victor Levchenko LVG Corp. on 20.01.15.
 */
public class ProtocolController extends GenericController {
    private static final Logger LOGGER = Logger.getLogger(ProtocolController.class);
    private static final String TREE_ROOT_ITEM_NAME = "Общие протоколы";
    private static final String PERSONAL_PROTOCOL_PREFIX_NAME = "Перс. протокол: ";
    private static final String PERSONAL_PROTOCOL_DB_SUFFIX_NAME = " (БД)";
    private final String EMPTY_STRING_ITEM = "нет";
    private final Double EMPTY_DOUBLE_ITEM = 0.0;

    private JournalService journalService = ServiceFactory.getJournalService();
    private PersonalProtocolService personalProtocolService = ServiceFactory.getPersonalProtocolService();
    private CommissionCertificationService commissionCertificationService =
            ServiceFactory.getCommissionCertificationService();
    private TotalProtocolService totalProtocolService = ServiceFactory.getTotalProtocolService();
    private TheoryTestService theoryTestService = ServiceFactory.getTheoryTestService();
    private WeldDetailService weldDetailService = ServiceFactory.getWeldDetailService();
    private WeldMethodService weldMethodService = ServiceFactory.getWeldMethodService();
    private NDTDocumentService ndtDocumentService = ServiceFactory.getNdtDocumentService();
    private PatternDiameterService patternDiameterService = ServiceFactory.getPatternDiameterService();
    private PatternThicknessService patternThicknessService = ServiceFactory.getPatternThicknessService();
    private SteelTypeService steelTypeService = ServiceFactory.getSteelTypeService();
    private WeldPositionService weldPositionService = ServiceFactory.getWeldPositionService();
    private ElectrodeService electrodeService = ServiceFactory.getElectrodeService();
    private WeldWireService weldWireService = ServiceFactory.getWeldWireService();
    private WeldGasService weldGasService = ServiceFactory.getWeldGasService();
    private EvaluationService evaluationService = ServiceFactory.getEvaluationService();
    private RadiationTestService radiationTestService = ServiceFactory.getRadiationTestService();
    private VisualTestService visualTestService = ServiceFactory.getVisualTestService();
    private MechanicalTestService mechanicalTestService = ServiceFactory.getMechanicalTestService();

    private TotalProtocolServiceUI totalProtocolServiceUI = ServiceUIFactory.getTotalProtocolServiceUI();
    private PersonalProtocolServiceUI personalProtocolServiceUI = ServiceUIFactory.getPersonalProtocolServiceUI();
    private EvaluationServiceUI evaluationServiceUI = ServiceUIFactory.getEvaluationServiceUI();
    private RadiationTestServiceUI radiationTestServiceUI = ServiceUIFactory.getRadiationTestServiceUI();
    private VisualTestServiceUI visualTestServiceUI = ServiceUIFactory.getVisualTestServiceUI();
    private MechanicalTestServiceUI mechanicalTestServiceUI = ServiceUIFactory.getMechanicalTestServiceUI();

    @FXML
    BorderPane mainProtocolPane;
    @FXML
    TabPane tabPaneAllProtocols;

    @FXML
    TreeView<String> protocolsTreeView;

    //Total protocol components
    @FXML
    Tab tabTotalProtocol;
    @FXML
    Accordion accordionTotalProtocol;
    @FXML
    BorderPane totalProtocolBorderPane;
    @FXML
    Label lbTotalProtocolNumber;
    @FXML
    Label lbTotalProtocolDate;
    @FXML
    TitledPane titlePaneCommissionCert;
    @FXML
    ComboBox<Long> cbIDCommissionCert;
    @FXML
    TextField txfCommissionHead;
    @FXML
    TextField txfCommissionWeldSpecialist;
    @FXML
    TextField txfCommissionNDTSpecialist;
    @FXML
    TextField txfCommissionSafetySpecialist;
    @FXML
    TitledPane titlePaneTotalProtocolWelders;
    @FXML
    TableView<WelderUI> tableViewWelders;
    @FXML
    TableColumn<WelderUI, Long> tcolWelderID;
    @FXML
    TableColumn<WelderUI, String> tcolWelderSurname;
    @FXML
    TableColumn<WelderUI, String> tcolWelderName;
    @FXML
    TableColumn<WelderUI, String> tcolWelderSecname;
    @FXML
    TableColumn<WelderUI, ObservableList<String>> tcolWelderWeldMethod;
    @FXML
    TitledPane titlePaneTotalProtocolPrint;
    @FXML
    Button btPrintTotalProtocol;
    @FXML
    Button btPrintRTProtocol;
    @FXML
    Button btPrintVTProtocol;
    @FXML
    Button btPrintMechProtocol;
    @FXML
    TitledPane titlePaneTotalProtocolJournal;
    @FXML
    Label lbTotalProtocolJournalNumber;
    @FXML
    Label lbTotalProtocolJournalDateBegin;
    @FXML
    Label lbTotalProtocolJournalDateEnd;
    @FXML
    Button btPrintJournal;
    @FXML
    Button btGoToJournal;

    //Personal protocol components
    @FXML
    Tab tabPersonalProtocol;
    @FXML
    BorderPane borderPanePersonalProtocol;
    @FXML
    TextField txfPersonalProtocolNumber;
    @FXML
    DatePicker dpPersonalProtocolDate;
    @FXML
    Label lbWelderFullName;
    @FXML
    TitledPane titlePanePersProtocolWeldPattern;
    @FXML
    TableView<WeldPatternUI> tableViewWeldPatterns;
    @FXML
    TableColumn<WeldPatternUI, Long> tcolWeldPatternID;
    @FXML
    TableColumn<WeldPatternUI, String> tcolWeldPatternType;
    @FXML
    TableColumn<WeldPatternUI, String> tcolWeldPatternSize;
    @FXML
    TableColumn<WeldPatternUI, String> tcolWeldPatternWeldMethod;
    @FXML
    Button btAddWeldPattern;
    @FXML
    Button btEditWeldPattern;
    @FXML
    Button btDeleteWeldPattern;
    @FXML
    TitledPane titlePanePersProtocolTheoryTest;
    @FXML
    TextField  txfTheoryTestTicketNumber;
    @FXML
    ComboBox<String> cbTheoryTestRating;
    @FXML
    TitledPane titlePaneNDTDocuments;
    @FXML
    ListView<String> listViewAttestDocs;
    @FXML
    Button btRemoveAllDocs;
    @FXML
    Button btRemoveSingleDoc;
    @FXML
    ListView<String> listViewAllDocs;
    @FXML
    Button btAddAllDocsToProtocol;
    @FXML
    Button btAddSingleDocToProtocol;
    @FXML
    TitledPane titlePaneResolutionCert;
    @FXML
    TextArea textAreaResolutionCert;
    @FXML
    Button btCleanResolutionText;

    //Weld pattern components
    @FXML
    Tab tabWeldPattern;
    @FXML
    TitledPane titlePaneWeldPatternOption;
    @FXML
    Accordion accordionWeldPatternPane;
    @FXML
    ComboBox<String> cbWeldPatternDetail;
    @FXML
    ComboBox<Double> cbWeldPatternDiameter;
    @FXML
    ComboBox<Double> cbWeldPatternThickness;
    @FXML
    TextField txfWeldPatternMark;
    @FXML
    CheckBox chkWeldPatternHeating;
    @FXML
    CheckBox chkWeldPatternHeatTreatment;
    @FXML
    ComboBox<String> cbWeldPatternSteelType;
    @FXML
    TextField txfWeldPatternWeldPosition;
    @FXML
    MenuButton menuButtonWeldPosition;
    @FXML
    ComboBox<String> cbWeldPatternWeldMethod;
    @FXML
    CheckBox chkWeldPatternElectrode;
    @FXML
    ComboBox<String> cbWeldPatternElectrode;
    @FXML
    CheckBox chkWeldPatternWeldWire;
    @FXML
    ComboBox<String> cbWeldPatternWeldWire;
    @FXML
    CheckBox chkWeldPatternWeldGas;
    @FXML
    ComboBox<String> cbWeldPatternWeldGas;
    @FXML
    TitledPane titlePaneWeldPatternNDT;
    @FXML
    CheckBox chkWeldPatternVT;
    @FXML
    TextField txfWeldPatternVTNumber;
    @FXML
    DatePicker dpWeldPatternVTDate;
    @FXML
    TextArea textAreaWeldPatternVTDefects;
    @FXML
    ComboBox<String> cbWeldPatternVTEvaluation;
    @FXML
    CheckBox chkWeldPatternRT;
    @FXML
    TextField txfWeldPatternRTNumber;
    @FXML
    DatePicker dpWeldPatternRTDate;
    @FXML
    ComboBox<Double> cbWeldPatternRTSensitivity;
    @FXML
    TextArea textAreaWeldPatternRTDefects;
    @FXML
    ComboBox<String> cbWeldPatternRTEvaluation;
    @FXML
    CheckBox chkWeldPatternMech;
    @FXML
    TextField txfWeldPatternMechNumber;
    @FXML
    DatePicker dpWeldPatternMechDate;
    @FXML
    TextField txfWeldPatternMechAngle;
    @FXML
    ComboBox<String> cbWeldPatternMechEvaluation;
    @FXML
    Button btWeldPatternSave;
    @FXML
    Button btWeldPatternCancel;



    private TotalProtocolUI selectedTotalProtocolUI = null;
    private PersonalProtocolUI selectedPersonalProtocolUI = null;
    private WeldPatternUI selectedWeldPatternUI = null;
    private List<String> chagedFields = new ArrayList<String>();

    private ObservableList<TreeItem<String>> totalProtocols = FXCollections.observableArrayList();
    private ObservableList<TotalProtocolUI> cachedTotalProtocols = FXCollections.observableArrayList();
    private ObservableList<WelderUI> weldersUIinTotalProtocol = FXCollections.observableArrayList();
    private ObservableList<WeldPatternUI> weldPatternsUIinPersProtocol = FXCollections.observableArrayList();
    private ObservableList<Long> commissionIDList = FXCollections.observableArrayList();
    private ObservableList<CommissionCertificationUI> commissionCertificationUIList =
            FXCollections.observableArrayList();
    private ObservableList<String> theoryTestRatingsList = FXCollections.observableArrayList();
    private ObservableList<String> allNTDdocs = FXCollections.observableArrayList();
    private ObservableList<String> currentNTDdocs = FXCollections.observableArrayList();
    private ObservableList<String> weldDetailList = FXCollections.observableArrayList();
    private ObservableList<String> weldPatternWeldMethodList = FXCollections.observableArrayList();
    private ObservableList<Double> weldPatternDiameterList = FXCollections.observableArrayList();
    private ObservableList<Double> weldPatternThicknessList = FXCollections.observableArrayList();
    private ObservableList<String> weldPatternSteelTypeList = FXCollections.observableArrayList();
    private ObservableList<String> weldPatternElectrodeList = FXCollections.observableArrayList();
    private ObservableList<String> weldPatternWeldWireList = FXCollections.observableArrayList();
    private ObservableList<String> weldPatternWeldGasList = FXCollections.observableArrayList();
    private ObservableList<CheckMenuItem> weldPatternAllWeldPosition = FXCollections.observableArrayList();
    private ObservableList<String> weldPatternSelectedWeldPosition = FXCollections.observableArrayList();
    private ObservableList<String> weldPatternEvaluationList = FXCollections.observableArrayList();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LOGGER.debug("INITIALIZING ProtocolPane");
        initProtocolsTreeView();
        initTotalProtocolTab();
        initWeldPatternTab();
        initPersonalProtocolTab();
    }

    private void initPersonalProtocolTab(){
        initTheoryTestRatingsList();
        cbTheoryTestRating.setItems(theoryTestRatingsList);
        initAllNDTdocs();
        listViewAllDocs.setItems(allNTDdocs);

    }

    private void initAllNDTdocs(){
        allNTDdocs.clear();
        for (NDTDocument document: ndtDocumentService.getAll()){
            allNTDdocs.add(document.getName());
        }
    }

    private void initTheoryTestRatingsList(){
        theoryTestRatingsList.addAll(TheoryTestUI.POSITIVE_RATING_VALUE, TheoryTestUI.NEGATIVE_RATING_VALUE);
    }

    private void initProtocolsTreeView(){
        initTotalProtocols();
        TreeItem<String> rootItem = new TreeItem<String>(TREE_ROOT_ITEM_NAME);
        rootItem.getChildren().clear();
        rootItem.getChildren().addAll(totalProtocols);
        protocolsTreeView.setRoot(rootItem);
        protocolsTreeView.getRoot().setExpanded(true);
        protocolsTreeView.addEventHandler(MouseEvent.MOUSE_CLICKED, new ListViewHandler());
        protocolsTreeView.addEventHandler(KeyEvent.KEY_RELEASED, new ListViewHandler());
    }

    private void initTotalProtocols(){
        totalProtocols.clear();
        cachedTotalProtocols.clear();
        List<Journal> journalsDb = journalService.getAll();
        List<PersonalProtocol> personalProtocolsDB = personalProtocolService.getAll();
        List<TotalProtocol> totalProtocolList  = totalProtocolService.getAll();
        List<JournalUI> journalUIList = new ArrayList<JournalUI>();
        List<PersonalProtocolUI> protocolUIFromDB = new ArrayList<PersonalProtocolUI>();

        for (PersonalProtocol pp : personalProtocolsDB){
            if(pp.getJournal()!=null){
                journalUIList.add(new JournalUI(pp.getJournal()));
            }
        }

        for (TotalProtocol tp : totalProtocolList){
            TotalProtocolUI totalProtocolUI = new TotalProtocolUI(tp);
            JournalUI journalUI = totalProtocolUI.getJournal();
            TreeItem<String> treeItem = new TreeItem<String>(totalProtocolUI.toString());
            cachedTotalProtocols.add(totalProtocolUI);
            for (int i = 0; i<journalUI.getWelders().size();i++){
                //TODO load protocols from db
                PersonalProtocolUI pp;
                if(getWelderProtocolFromDB(journalUI.getWelders().get(i),journalUI)!=null){
                    pp = getWelderProtocolFromDB(journalUI.getWelders().get(i),journalUI);
                    treeItem.getChildren().add(new TreeItem<String>(PERSONAL_PROTOCOL_PREFIX_NAME
                            + pp.toString()+ PERSONAL_PROTOCOL_DB_SUFFIX_NAME));
                }else {
                    pp = new PersonalProtocolUI(journalUI.getWelders().get(i), journalUI);
                    treeItem.getChildren().add(new TreeItem<String>(PERSONAL_PROTOCOL_PREFIX_NAME
                            +pp.toString()));
                }
            }
            totalProtocols.add(treeItem);
        }

    }
    private void initTotalProtocolTab(){
        accordionTotalProtocol.setExpandedPane(titlePaneTotalProtocolWelders);
        initTotalProtocolWeldersTableView();
        initCommissionComboBox();
        //totalProtocolBorderPane.setDisable(true);
    }

    private void initTotalProtocolWeldersTableView(){
        tcolWelderID.setCellValueFactory(new PropertyValueFactory<WelderUI, Long>("id"));
        tcolWelderName.setCellValueFactory(new PropertyValueFactory<WelderUI, String>("name"));
        tcolWelderSurname.setCellValueFactory(new PropertyValueFactory<WelderUI, String>("surname"));
        tcolWelderSecname.setCellValueFactory(new PropertyValueFactory<WelderUI, String>("secname"));
        tcolWelderWeldMethod.setCellValueFactory(new PropertyValueFactory<WelderUI, ObservableList<String>>("weldMethods"));
        tableViewWelders.setItems(weldersUIinTotalProtocol);
        tableViewWelders.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }

    private void initWeldersUIinTotalProtocol(TotalProtocolUI selectedTotalProtocolUI){
        weldersUIinTotalProtocol.clear();
        weldersUIinTotalProtocol.addAll(selectedTotalProtocolUI.getJournal().getWelders());
    }

    private void initCommissionComboBox(){
        initCommissionCertificationUIList();
        cbIDCommissionCert.setItems(commissionIDList);
        cbIDCommissionCert.valueProperty().addListener(new ComboBoxCommissionHandler());
    }

    private void initCommissionTitlePane(TotalProtocolUI selectedTotalProtocolUI){
        CommissionCertificationUI currentCommission = selectedTotalProtocolUI.getCommissionCertification();

        if(currentCommission.getId() != 0){
            cbIDCommissionCert.getSelectionModel().select(currentCommission.getId());
            txfCommissionHead.setText(currentCommission.getHead().getFullTeacherName());
            txfCommissionNDTSpecialist.setText(currentCommission.getNdtSpecialist().getFullTeacherName());
            txfCommissionWeldSpecialist.setText(currentCommission.getWeldSpecialist().getFullTeacherName());
            txfCommissionSafetySpecialist.setText(currentCommission.getSafetySpecialist().getFullTeacherName());
        }else{
            clearCommissionTitlePane();
        }
    }

    private void initJournalTitledPane(TotalProtocolUI selectedTotalProtocolUI){
        JournalUI journalUI = selectedTotalProtocolUI.getJournal();
        lbTotalProtocolJournalNumber.setText(journalUI.getNumber());
        lbTotalProtocolJournalDateBegin.setText(journalUI.getDateBeginFormat());
        lbTotalProtocolJournalDateEnd.setText(journalUI.getDateEndFormat());
    }

    private void clearCommissionTitlePane(){
        cbIDCommissionCert.getSelectionModel().clearSelection();
        txfCommissionHead.setText("");
        txfCommissionSafetySpecialist.setText("");
        txfCommissionNDTSpecialist.setText("");
        txfCommissionWeldSpecialist.setText("");
    }

    private void initCommissionCertificationUIList(){
        commissionCertificationUIList.clear();
        for (CommissionCertification cc : commissionCertificationService.getAll()){
            commissionCertificationUIList.add(new CommissionCertificationUI(cc));
            commissionIDList.add(cc.getCommissionCertificationId());
        }
    }

    private void initWeldPatternTab(){
        tabWeldPattern.setDisable(true);
        tabWeldPattern.setClosable(true);
        initComboBoxDetailType();
        initComboBoxDiameter();
        initComboBoxThickness();
        initComboBoxSteelType();
        initComboBoxWeldMethod();
        initComboBoxElectrode();
        initComboBoxWeldWire();
        initComboBoxWeldGas();
        initMenuButtonWeldPosition();
        initTextFieldWeldPosition();
        initWeldPatternTestPane();

    }

    private void initTextFieldWeldPosition(){
        fillTextFieldWeldPosition();
    }

    private void fillTextFieldWeldPosition(){
        txfWeldPatternWeldPosition.clear();
        if(menuButtonWeldPosition.getItems().isEmpty())
            return;
        StringBuilder text = new StringBuilder();
        for(MenuItem mi : menuButtonWeldPosition.getItems()){
            CheckMenuItem chkItem = (CheckMenuItem)mi;
            if(chkItem.isSelected()){
                text.append(chkItem.getText());
                if(menuButtonWeldPosition.getItems().iterator().hasNext()){
                    text.append(" ");
                }
            }
        }
        txfWeldPatternWeldPosition.setText(text.toString());
    }

    private void initWeldPatternTestPane(){
        chkWeldPatternVT.addEventHandler(MouseEvent.MOUSE_CLICKED, new CheckBoxWeldPatternTestHandler());
        chkWeldPatternRT.addEventHandler(MouseEvent.MOUSE_CLICKED, new CheckBoxWeldPatternTestHandler());
        chkWeldPatternMech.addEventHandler(MouseEvent.MOUSE_CLICKED, new CheckBoxWeldPatternTestHandler());
        chkWeldPatternVT.setSelected(false);
        chkWeldPatternRT.setSelected(false);
        chkWeldPatternMech.setSelected(false);
        setDisabledRTPane(true);
        setDisabledVTPane(true);
        setDisabledMechPane(true);
        initWeldPatternEvaluationList();
        cbWeldPatternRTEvaluation.setItems(weldPatternEvaluationList);

        cbWeldPatternMechEvaluation.setItems(weldPatternEvaluationList);

        cbWeldPatternVTEvaluation.setItems(weldPatternEvaluationList);

        initComboBoxRTSensitivity();
        clearVTPane();
        clearRTPane();
        clearMechPane();
    }

    private void initComboBoxRTSensitivity(){
        cbWeldPatternRTSensitivity.setItems(FXCollections.observableArrayList(RadiationTestUI.getSensitivityList()));
    }

    private void initWeldPatternEvaluationList(){
        weldPatternEvaluationList.clear();
        for(Evaluation e : evaluationService.getAll()){
            weldPatternEvaluationList.addAll(e.getType());
        }

    }

    private void setDisabledMechPane(boolean isDisabled){
       txfWeldPatternMechNumber.setDisable(isDisabled);
       dpWeldPatternMechDate.setDisable(isDisabled);
       txfWeldPatternMechAngle.setDisable(isDisabled);
       cbWeldPatternMechEvaluation.setDisable(isDisabled);
    }

    private void setDisabledVTPane(boolean isDisabled){
        txfWeldPatternVTNumber.setDisable(isDisabled);
        dpWeldPatternVTDate.setDisable(isDisabled);
        textAreaWeldPatternVTDefects.setDisable(isDisabled);
        cbWeldPatternVTEvaluation.setDisable(isDisabled);
    }

    private void setDisabledRTPane(boolean isDisabled){
            txfWeldPatternRTNumber.setDisable(isDisabled);
            dpWeldPatternRTDate.setDisable(isDisabled);
            cbWeldPatternRTSensitivity.setDisable(isDisabled);
            textAreaWeldPatternRTDefects.setDisable(isDisabled);
            cbWeldPatternRTEvaluation.setDisable(isDisabled);
    }

    private void clearMechPane(){
        txfWeldPatternMechNumber.clear();
        dpWeldPatternMechDate.setValue(DateUtil.getLocalDate(new Date()));
        txfWeldPatternMechAngle.clear();
        cbWeldPatternMechEvaluation.getSelectionModel().clearSelection();
    }

    private void clearVTPane(){
        txfWeldPatternVTNumber.clear();
        dpWeldPatternVTDate.setValue(DateUtil.getLocalDate(new Date()));
        textAreaWeldPatternVTDefects.clear();
        cbWeldPatternVTEvaluation.getSelectionModel().clearSelection();
    }

    private void clearRTPane(){
        txfWeldPatternRTNumber.clear();
        dpWeldPatternRTDate.setValue(DateUtil.getLocalDate(new Date()));
        cbWeldPatternRTSensitivity.getSelectionModel().clearSelection();
        textAreaWeldPatternRTDefects.clear();
        cbWeldPatternRTEvaluation.getSelectionModel().clearSelection();
    }

    private void initMenuButtonWeldPosition(){
        weldPatternAllWeldPosition.clear();
        for(WeldPosition wp: weldPositionService.getAll()){
            CheckMenuItem item = new CheckMenuItem(wp.getCode());
            weldPatternAllWeldPosition.add(item);
            item.addEventHandler(ActionEvent.ACTION, new CheckMenuItemHandler());
        }
        menuButtonWeldPosition.getItems().clear();
        menuButtonWeldPosition.getItems().addAll(weldPatternAllWeldPosition);
    }


    private void initComboBoxWeldGas(){
        weldPatternWeldGasList.clear();
        weldPatternWeldGasList.add(EMPTY_STRING_ITEM);
        for(WeldGas wg: weldGasService.getAll()){
            weldPatternWeldGasList.add(wg.getType());
        }
        cbWeldPatternWeldGas.setItems(weldPatternWeldGasList);
        chkWeldPatternWeldGas.setSelected(false);
        cbWeldPatternWeldGas.setDisable(true);
    }

    private void initComboBoxWeldWire(){
        weldPatternWeldWireList.clear();
        for(WeldWire ww: weldWireService.getAll()){
            weldPatternWeldWireList.add(ww.getType());
        }
        cbWeldPatternWeldWire.setItems(weldPatternWeldWireList);
        chkWeldPatternWeldWire.setSelected(false);
        cbWeldPatternWeldWire.setDisable(true);
    }

    private void initComboBoxElectrode(){
        weldPatternElectrodeList.clear();
        for (Electrode e : electrodeService.getAll()){
            weldPatternElectrodeList.add(e.getType());
        }
        cbWeldPatternElectrode.setItems(weldPatternElectrodeList);
        chkWeldPatternElectrode.setSelected(false);
        cbWeldPatternElectrode.setDisable(true);
    }

    private void initComboBoxWeldMethod(){
        weldPatternWeldMethodList.clear();
        for(WeldMethod wm : weldMethodService.getAll() ){
            WeldMethodUI weldMethodUI = new WeldMethodUI(wm);
            weldPatternWeldMethodList.add(weldMethodUI.getNameCode());
        }
        cbWeldPatternWeldMethod.setItems(weldPatternWeldMethodList);
    }

    private void initComboBoxSteelType(){
        weldPatternSteelTypeList.clear();
        for (SteelType st: steelTypeService.getAll()){
            SteelTypeUI steelTypeUI = new SteelTypeUI(st);
            weldPatternSteelTypeList.add(steelTypeUI.getType());
        }
        cbWeldPatternSteelType.setItems(weldPatternSteelTypeList);
    }

    private void initComboBoxThickness(){
        weldPatternThicknessList.clear();
        for (PatternThickness pt : patternThicknessService.getAll()){
            PatternThicknessUI patternThicknessUI = new PatternThicknessUI(pt);
            weldPatternThicknessList.add(patternThicknessUI.getThickness());
        }
        cbWeldPatternThickness.setItems(weldPatternThicknessList);


    }

    private void initComboBoxDiameter(){
        weldPatternDiameterList.clear();
        weldPatternDiameterList.add(EMPTY_DOUBLE_ITEM);
        for(PatternDiameter pd: patternDiameterService.getAll()){
            PatternDiameterUI patternDiameterUI = new PatternDiameterUI(pd);
            weldPatternDiameterList.add(patternDiameterUI.getDiameter());
        }
        cbWeldPatternDiameter.setItems(weldPatternDiameterList);
    }

    private void initComboBoxDetailType(){
        weldDetailList.clear();
        for (WeldDetail wd : weldDetailService.getAll()){
            WeldDetailUI weldDetailUI = new WeldDetailUI(wd);
            weldDetailList.addAll(weldDetailUI.getDetailTypeCode());
        }
        cbWeldPatternDetail.setItems(weldDetailList);
    }



    private PersonalProtocolUI getWelderProtocolFromDB(WelderUI welderUI, JournalUI journalUI){
        List<PersonalProtocol> personalProtocols = personalProtocolService.getAll();
        for (PersonalProtocol pp : personalProtocols){
            if (pp.getJournal()!= null){
                JournalUI journalUIDB = new JournalUI(pp.getJournal());
                if(pp.getWelder()!=null){
                   WelderUI welderUIDB = new WelderUI(pp.getWelder());
                    if(journalUIDB.equals(journalUI) && welderUIDB.equals(welderUI)){
                        return new PersonalProtocolUI(pp);
                    }
                }
            }
        }
        return null;
    }

    private void showSelectedTotalProtocol(TotalProtocolUI selectedTotalProtocol){
        lbTotalProtocolNumber.setText(selectedTotalProtocol.getNumber());
        lbTotalProtocolDate.setText(selectedTotalProtocol.getDateCertFormat());
        initWeldersUIinTotalProtocol(selectedTotalProtocol);
        initCommissionTitlePane(selectedTotalProtocol);
        initJournalTitledPane(selectedTotalProtocol);
        tabTotalProtocol.getTabPane().getSelectionModel().select(tabTotalProtocol);


    }

    private void showSelectedPersProtocol(PersonalProtocolUI selectedPersProtocol){
        txfPersonalProtocolNumber.setText(selectedPersProtocol.getNumber());
        dpPersonalProtocolDate.setValue(DateUtil.getLocalDate(selectedPersProtocol.getDatePeriodicalCert()));
        lbWelderFullName.setText(selectedPersProtocol.toString());
        tabPersonalProtocol.getTabPane().getSelectionModel().select(tabPersonalProtocol);
        titlePanePersProtocolWeldPattern.setExpanded(true);
        initPersProtocolWeldPatterns(selectedPersProtocol);
        initTitlePanePersProtocolTheoryTest(selectedPersProtocol);
        initPersProtocolNTDdocs(selectedPersProtocol);
        initPersProtocolResolutionCert(selectedPersProtocol);


    }

    private void showSelectedWeldPattern(WeldPatternUI selectedWeldPattern){

        initComboBoxDetailType(selectedWeldPattern);
        initComboBoxDiameter(selectedWeldPattern);
        initComboBoxThickness(selectedWeldPattern);
        txfWeldPatternMark.setText(selectedWeldPattern.getMark());
        chkWeldPatternHeating.setSelected(selectedWeldPattern.getIsHeating());
        chkWeldPatternHeatTreatment.setSelected(selectedWeldPattern.getIsHeatTreatment());
        initComboBoxSteelType(selectedWeldPattern);
        initComboBoxWeldMethod(selectedWeldPattern);
        initComboBoxElectrode(selectedWeldPattern);
        initComboBoxWeldWire(selectedWeldPattern);
        initComboBoxWeldGas(selectedWeldPattern);
        initMenuButtonWeldPosition(selectedWeldPattern);
        initTextFieldWeldPosition(selectedWeldPattern);
        initTitlePaneWeldPatternTest(selectedWeldPattern);
    }

    private void initTitlePaneWeldPatternTest(WeldPatternUI selectedWeldPattern){
        initWeldPatternTestPane();
        if(selectedWeldPattern.getRadiationTest()!=null){
            if (selectedWeldPattern.getRadiationTest().getId()>0) {
                fillRadiationTestPane(selectedWeldPattern.getRadiationTest());
                chkWeldPatternRT.setSelected(true);
                setDisabledRTPane(false);
            }
        }
        if(selectedWeldPattern.getVisualTest()!=null){
            if(selectedWeldPattern.getVisualTest().getId()>0) {
                fillVisualTestPane(selectedWeldPattern.getVisualTest());
                chkWeldPatternVT.setSelected(true);
                setDisabledVTPane(false);
            }
        }
        if(selectedWeldPattern.getMechanicalTest()!=null){
            if(selectedWeldPattern.getMechanicalTest().getId()>0) {
                fillMechanicalTestPane(selectedWeldPattern.getMechanicalTest());
                chkWeldPatternMech.setSelected(true);
                setDisabledMechPane(false);
            }
        }
    }

    private void fillMechanicalTestPane(MechanicalTestUI mechanicalTestUI){
        txfWeldPatternMechNumber.setText(mechanicalTestUI.getNumber());
        dpWeldPatternMechDate.setValue(DateUtil.getLocalDate(mechanicalTestUI.getDate()));
        txfWeldPatternMechAngle.setText(mechanicalTestUI.getAngle()+"");
        String evaluation = mechanicalTestUI.getEvaluation().getType();
        for (String e : cbWeldPatternMechEvaluation.getItems()){
            if(e.equals(evaluation)){
                cbWeldPatternMechEvaluation.getSelectionModel().select(e);
                break;
            }
        }
    }

    private void fillVisualTestPane(VisualTestUI visualTestUI){
        txfWeldPatternVTNumber.setText(visualTestUI.getNumber());
        dpWeldPatternVTDate.setValue(DateUtil.getLocalDate(visualTestUI.getDate()));
        textAreaWeldPatternVTDefects.setText(visualTestUI.getDefects());
        String evaluation = visualTestUI.getEvaluation().getType();
        for (String e : cbWeldPatternVTEvaluation.getItems()){
            if(e.equals(evaluation)){
                cbWeldPatternVTEvaluation.getSelectionModel().select(e);
                break;
            }
        }
    }

    private void fillRadiationTestPane(RadiationTestUI radiationTestUI){
        txfWeldPatternRTNumber.setText(radiationTestUI.getNumber());
        dpWeldPatternRTDate.setValue(DateUtil.getLocalDate(radiationTestUI.getDate()));
        for(Double sensitivity : cbWeldPatternRTSensitivity.getItems() ){
            if(radiationTestUI.getSensitivity().equals(sensitivity.toString())){
                cbWeldPatternRTSensitivity.getSelectionModel().select(sensitivity);
                break;
            }
        }
        textAreaWeldPatternRTDefects.setText(radiationTestUI.getDefects());
        String evaluation = radiationTestUI.getEvaluation().getType();
        for (String e : cbWeldPatternRTEvaluation.getItems()){
            if(e.equals(evaluation)){
                cbWeldPatternRTEvaluation.getSelectionModel().select(e);
                break;
            }
        }
    }

    private void initTextFieldWeldPosition(WeldPatternUI selectedWeldPattern){
        initTextFieldWeldPosition();
    }

    private void initMenuButtonWeldPosition(WeldPatternUI selectedWeldPattern){
        initMenuButtonWeldPosition();
        ObservableList<WeldPositionUI> weldPositions = selectedWeldPattern.getWeldPositions();
        for (MenuItem mi : menuButtonWeldPosition.getItems()){
            CheckMenuItem chkItem = (CheckMenuItem)mi;
            for (WeldPositionUI wp : weldPositions){
                if(mi.getText().equals(wp.getCode())){
                    chkItem.setSelected(true);
                }
            }
        }
    }

    private void initComboBoxWeldGas(WeldPatternUI selectedWeldPattern){
       initComboBoxWeldGas();
        if (null == selectedWeldPattern.getWeldGas())
            return;
        String weldGas = selectedWeldPattern.getWeldGas().getType();
        for (String wg : cbWeldPatternWeldGas.getItems()){
            if(wg.equals(weldGas)){
                chkWeldPatternWeldGas.setSelected(true);
                cbWeldPatternWeldGas.setDisable(false);
                cbWeldPatternWeldGas.getSelectionModel().select(wg);
            }
        }
    }

    private void initComboBoxWeldWire(WeldPatternUI selectedWeldPattern){
        initComboBoxWeldWire();
        if (null == selectedWeldPattern.getWeldWire())
            return;
        String weldWire = selectedWeldPattern.getWeldWire().getType();
        for(String ww : cbWeldPatternWeldWire.getItems()){
            if(ww.equals(weldWire)){
                chkWeldPatternWeldWire.setSelected(true);
                cbWeldPatternWeldWire.setDisable(false);
                cbWeldPatternWeldWire.getSelectionModel().select(ww);
                break;
            }
        }
    }

    private void initComboBoxElectrode(WeldPatternUI selectedWeldPattern){
        initComboBoxElectrode();
        if (null == selectedWeldPattern.getElectrode())
            return;
        String electrode = selectedWeldPattern.getElectrode().getType();
        for (String e : cbWeldPatternElectrode.getItems()){
            if (e.equals(electrode)){
                chkWeldPatternElectrode.setSelected(true);
                cbWeldPatternElectrode.setDisable(false);
                cbWeldPatternElectrode.getSelectionModel().select(e);
                break;
            }
        }
    }

    private void initComboBoxWeldMethod(WeldPatternUI selectedWeldPattern){
        initComboBoxWeldMethod();
        if(null == selectedWeldPattern.getWeldMethod())
            return;
        String weldMethodNameCode = selectedWeldPattern.getWeldMethod().getNameCode();
        for(String wm : cbWeldPatternWeldMethod.getItems()){
            if(wm.equals(weldMethodNameCode)){
                cbWeldPatternWeldMethod.getSelectionModel().select(wm);
                break;
            }
        }
    }

    private void initComboBoxSteelType(WeldPatternUI selectedWeldPattern){
        initComboBoxSteelType();
        if (null == selectedWeldPattern.getSteelType())
            return;
        String steelType = selectedWeldPattern.getSteelType().getType();
        for(String st : cbWeldPatternSteelType.getItems()){
            if(st.equals(steelType)){
                cbWeldPatternSteelType.getSelectionModel().select(st);
                break;
            }
        }
    }

    private void initComboBoxThickness(WeldPatternUI selectedWeldPattern){
        initComboBoxThickness();
        Double thickness = selectedWeldPattern.getThickness();
        if(thickness == null)
            return;
        for (Double t : cbWeldPatternThickness.getItems()){
            if(t.equals(thickness)){
                cbWeldPatternThickness.getSelectionModel().select(t);
                break;
            }
        }
    }

    private void initComboBoxDiameter(WeldPatternUI selectedWeldPattern){
        initComboBoxDiameter();
        Double patternDiameter = selectedWeldPattern.getDiameter();
        if(patternDiameter==null)
            return;

        for(Double d : cbWeldPatternDiameter.getItems()){
            if(d.equals(patternDiameter)){
                cbWeldPatternDiameter.getSelectionModel().select(d);
                break;
            }
        }
    }

    private void initComboBoxDetailType(WeldPatternUI selectedWeldPattern){
        initComboBoxDetailType();
        if(null == selectedWeldPattern.getWeldDetail())
            return;
        String detailTypeCode = selectedWeldPattern.getWeldDetail().getDetailTypeCode();
        for(String types: cbWeldPatternDetail.getItems()){
            if(types.equals(detailTypeCode)){
                cbWeldPatternDetail.getSelectionModel().select(types);
                break;
            }
        }
    }


    private  void initPersProtocolResolutionCert(PersonalProtocolUI selectedPersProtocol){
        ResolutionCertificationUI resolution = selectedPersProtocol.getResolutionCertification();
        if (resolution != null) {
            textAreaResolutionCert.setText(resolution.getTextResolution());
        }

    }

    private void initPersProtocolNTDdocs(PersonalProtocolUI selectedPersProtocol){
        ObservableList<NDTDocumentUI> ntdDocs = selectedPersProtocol.getNdtDocuments();
        currentNTDdocs.clear();
        initAllNDTdocs();
        for(NDTDocumentUI documentUI: ntdDocs){
            currentNTDdocs.add(documentUI.getName());
        }
        allNTDdocs.removeAll(currentNTDdocs);
        checkEmptiesNTDdocsLists();

        listViewAttestDocs.setItems(currentNTDdocs);
    }

    private void checkEmptiesNTDdocsLists(){
        if(allNTDdocs.isEmpty()){
            btAddAllDocsToProtocol.setDisable(true);
            btAddSingleDocToProtocol.setDisable(true);
        }else {
            btAddAllDocsToProtocol.setDisable(false);
            btAddSingleDocToProtocol.setDisable(false);
        }
        if(currentNTDdocs.isEmpty()){
            btRemoveAllDocs.setDisable(true);
            btRemoveSingleDoc.setDisable(true);
        }
        else{
            btRemoveAllDocs.setDisable(false);
            btRemoveSingleDoc.setDisable(false);
        }

    }

    private void initPersProtocolWeldPatterns(PersonalProtocolUI selectedPersProtocol ){
        weldPatternsUIinPersProtocol.clear();
        //If current pers protocol exist in DB than load weld patterns from db
//        PersonalProtocol pp = personalProtocolService.get(selectedPersProtocol.getId());
//        if(pp!=null){
//            List<WeldPattern> weldPatterns = pp.getWeldPatterns();
//            for (WeldPattern wp : weldPatterns){
//                weldPatternsUIinPersProtocol.add(new WeldPatternUI(wp));
//            }
//        }else {
//            weldPatternsUIinPersProtocol.addAll(selectedPersProtocol.getWeldPatterns());
//        }
        weldPatternsUIinPersProtocol.addAll(selectedPersProtocol.getWeldPatterns());
        LOGGER.debug("INIT PERS PROTOCOL WELD PATTERNS: weldPatternUIs list: "+weldPatternsUIinPersProtocol);
        tcolWeldPatternID.setCellValueFactory(new PropertyValueFactory<WeldPatternUI, Long>("id"));
        tcolWeldPatternType.setCellValueFactory(new PropertyValueFactory<WeldPatternUI, String>("typeName"));
        tcolWeldPatternSize.setCellValueFactory(new PropertyValueFactory<WeldPatternUI, String>("size"));
        tcolWeldPatternWeldMethod.setCellValueFactory(new PropertyValueFactory<WeldPatternUI, String>("weldMethodName"));
        tableViewWeldPatterns.setItems(weldPatternsUIinPersProtocol);
        tableViewWeldPatterns.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

    }

    private void initTitlePanePersProtocolTheoryTest(PersonalProtocolUI selectedPersProtocol){
        TheoryTestUI theoryTestUI = selectedPersProtocol.getTheoryTest();

        txfTheoryTestTicketNumber.setText(theoryTestUI.getTicketNumber());
        initComboBoxTheoryTestRating(theoryTestUI);

    }

    private void initComboBoxTheoryTestRating(TheoryTestUI theoryTestUI){

        for (String rating: cbTheoryTestRating.getItems()){
            if(theoryTestUI.getRating().equals(rating)){
                cbTheoryTestRating.getSelectionModel().select(rating);
                break;
            }
        }
    }

    private PersonalProtocolUI getPersProtocolByItemName(TotalProtocolUI totalProtocolUI, String itemName){
        PersonalProtocolUI result = null;
        String fullName = "";
        if(itemName.contains(PERSONAL_PROTOCOL_DB_SUFFIX_NAME) && itemName.contains(PERSONAL_PROTOCOL_PREFIX_NAME)){
            fullName =
                    itemName.substring(PERSONAL_PROTOCOL_PREFIX_NAME.length(),itemName.indexOf(PERSONAL_PROTOCOL_DB_SUFFIX_NAME));
            LOGGER.debug("GET PERSONAL PROTOCOL BY ITEM NAME: fullName is: "+fullName);
            return personalProtocolServiceUI.getPersonalProtocolUIFromDB(totalProtocolUI, fullName);
        }else{
            if(itemName.contains(PERSONAL_PROTOCOL_PREFIX_NAME) && !itemName.contains(PERSONAL_PROTOCOL_DB_SUFFIX_NAME)) {
                fullName = itemName.substring(PERSONAL_PROTOCOL_PREFIX_NAME.length());
                LOGGER.debug("GET PERSONAL PROTOCOL BY ITEM NAME: fullName is: "+fullName);
                return personalProtocolServiceUI.getPersonalProtocolUI(totalProtocolUI, fullName);
            }
        }
        LOGGER.debug("GET PERSONAL PROTOCOL BY ITEM NAME: fullName is: "+fullName);

        return result;
    }

    private boolean isSelectedWeldPatternChanged(WeldPatternUI selectedWeldPattern) {
        chagedFields.clear();

        if (cbWeldPatternDetail.getValue()!=null) {
            if (!cbWeldPatternDetail.getValue().equals(selectedWeldPattern.getWeldDetail().getDetailTypeCode())) {
                chagedFields.add("cbWeldPatternDetail is changed ");
                return true;
            }
        }

        if (cbWeldPatternDiameter.getValue()!=null) {
            if (!cbWeldPatternDiameter.getValue().equals(selectedWeldPattern.getDiameter())) {
                chagedFields.add("cbWeldPatternDiameter is changed ");
                return true;
            }
        }

        if(cbWeldPatternThickness.getValue()!=null) {
            if (!cbWeldPatternThickness.getValue().equals(selectedWeldPattern.getThickness())) {
                chagedFields.add("cbWeldPatternThickness is changed ");
                return true;
            }
        }

        if (!txfWeldPatternMark.getText().trim().equals(selectedWeldPattern.getMark())) {
            chagedFields.add("txfWeldPatternMark is changed ");
            return true;
        }
        if (chkWeldPatternHeating.isSelected()!=selectedWeldPattern.getIsHeating()) {
            chagedFields.add("chkWeldPatternHeating is changed ");
            return true;
        }
        if (chkWeldPatternHeatTreatment.isSelected()!=selectedWeldPattern.getIsHeatTreatment()) {
            chagedFields.add("chkWeldPatternHeatTreatment is changed ");
            return true;
        }

        if (cbWeldPatternSteelType.getValue()!=null) {
            if (!cbWeldPatternSteelType.getValue().equals(selectedWeldPattern.getSteelType().getType())) {
                chagedFields.add("cbWeldPatternSteelType is changed ");
                return true;
            }
        }
        ObservableList<String> checkedWeldPositions = FXCollections.observableArrayList();
        for(MenuItem mi : menuButtonWeldPosition.getItems()){
            CheckMenuItem chkItem = (CheckMenuItem)mi;
            if(chkItem.isSelected()){
                checkedWeldPositions.add(chkItem.getText());
            }
        }
        if (checkedWeldPositions.size()==selectedWeldPattern.getWeldPositions().size()){
            for (WeldPositionUI wp : selectedWeldPattern.getWeldPositions()){
                if(!checkedWeldPositions.contains(wp.getCode())){
                    chagedFields.add("txfWeldPatternWeldPosition is changed ");
                    return true;
                }
            }
        }else{
            return true;
        }

        if (cbWeldPatternWeldMethod.getValue()!=null) {
            if (!cbWeldPatternWeldMethod.getValue().equals(selectedWeldPattern.getWeldMethod().getNameCode())) {
                chagedFields.add("cbWeldPatternWeldMethod is changed ");
                return true;
            }
        }

        if (chkWeldPatternElectrode.isSelected()){
            if(selectedWeldPattern.getElectrode().getId()==0) {
                chagedFields.add("chkWeldPatternElectrode is changed ");
                return true;
            }
            if(cbWeldPatternElectrode.getValue()!=null) {
                if (!cbWeldPatternElectrode.getValue().equals(selectedWeldPattern.getElectrode().getType())) {
                    chagedFields.add("cbWeldPatternElectrode is changed ");
                    return true;
                }
            }
        }else {
            if (selectedWeldPattern.getElectrode().getId()>0) {
                chagedFields.add("chkWeldPatternElectrode is changed ");
                return true;
            }
        }

        if (chkWeldPatternWeldWire.isSelected()){
            if(selectedWeldPattern.getWeldWire().getId()==0) {
                chagedFields.add("chkWeldPatternWeldWire is changed ");
                return true;
            }
            if (cbWeldPatternWeldWire.getValue()!=null) {
                if (!cbWeldPatternWeldWire.getValue().equals(selectedWeldPattern.getWeldWire().getType())) {
                    chagedFields.add("cbWeldPatternWeldWire is changed ");
                    return true;
                }
            }
        }else {
            if (selectedWeldPattern.getWeldWire().getId()>0) {
                chagedFields.add("chkWeldPatternWeldWire is changed ");
                return true;
            }
        }

        if (chkWeldPatternWeldGas.isSelected()){
            if(selectedWeldPattern.getWeldGas().getId()==0) {
                chagedFields.add("chkWeldPatternWeldGas is changed ");
                return true;
            }
            if (cbWeldPatternWeldGas.getValue()!=null) {
                if (!cbWeldPatternWeldGas.getValue().equals(selectedWeldPattern.getWeldGas().getType())) {
                    chagedFields.add("cbWeldPatternWeldGas is changed ");
                    return true;
                }
            }
        }else {
            if (selectedWeldPattern.getWeldGas().getId()>0) {
                chagedFields.add("chkWeldPatternWeldGas is changed ");
                return true;
            }
        }

        if (isWeldPatternRtTestChanged(selectedWeldPattern)) {
            chagedFields.add("WeldPatternRTtest is changed ");
            return true;
        }

        if (isWeldPatternVtTestChanged(selectedWeldPattern)) {
            chagedFields.add("WeldPatternVTtest is changed ");
            return true;
        }

        if (isWeldPatternMechTestChanged(selectedWeldPattern)) {
            chagedFields.add("WeldPatternMechtest is changed ");
            return true;
        }
        return false;
    }

    private boolean isWeldPatternMechTestChanged(WeldPatternUI selectedWeldPattern){
        MechanicalTestUI selectedMechTest = selectedWeldPattern.getMechanicalTest();
        if (chkWeldPatternMech.isSelected()){
            if(selectedMechTest.getId()==0)
                return true;
            if (!txfWeldPatternMechNumber.getText().trim().equals(selectedMechTest.getNumber()))
                return true;
            if (dpWeldPatternMechDate.getValue()!=null) {
                if (!DateUtil.format(dpWeldPatternMechDate.getValue()).equals(
                        DateUtil.format(selectedMechTest.getDate())))
                    return true;
            }
            if(!txfWeldPatternMechAngle.getText().trim().equals(
                    String.valueOf(selectedMechTest.getAngle())))
                return true;
            if (cbWeldPatternMechEvaluation.getValue()!=null) {
                if (!cbWeldPatternMechEvaluation.getValue().equals(
                        selectedMechTest.getEvaluation().getType()))
                    return true;
            }
        }else {
            if (selectedMechTest.getId()>0)
                return true;
        }
        return false;
    }

    private boolean isWeldPatternVtTestChanged(WeldPatternUI selectedWeldPattern){
        VisualTestUI selectedVT = selectedWeldPattern.getVisualTest();

        if (chkWeldPatternVT.isSelected()){
            if(selectedVT.getId()==0)
                return true;
            if (!txfWeldPatternVTNumber.getText().trim().equals(selectedVT.getNumber()))
                return true;
            if (dpWeldPatternVTDate.getValue()!=null) {
                if (!DateUtil.format(dpWeldPatternVTDate.getValue()).equals(
                        DateUtil.format(selectedVT.getDate())))
                    return true;
            }
            if(!textAreaWeldPatternVTDefects.getText().equals(selectedVT.getDefects()))
                return true;
            if (cbWeldPatternVTEvaluation.getValue()!=null) {
                if (!cbWeldPatternVTEvaluation.getValue().equals(
                        selectedVT.getEvaluation().getType()))
                    return true;
            }
        }else {
            if (selectedVT.getId()>0)
                return true;
        }
        return false;
    }

    private boolean isWeldPatternRtTestChanged(WeldPatternUI selectedWeldPattern){
        RadiationTestUI selectedRT = selectedWeldPattern.getRadiationTest();
        if (chkWeldPatternRT.isSelected()){
            if(selectedRT.getId()==0)
                return true;
            if (!txfWeldPatternRTNumber.getText().trim().equals(selectedRT.getNumber()))
                return true;
            if(dpWeldPatternRTDate.getValue()!=null) {
                if (!DateUtil.format(dpWeldPatternRTDate.getValue()).equals(
                        DateUtil.format(selectedRT.getDate())))
                    return true;
            }
            if (cbWeldPatternRTSensitivity.getValue()!=null) {
                if (!cbWeldPatternRTSensitivity.getValue().toString().equals(
                        selectedRT.getSensitivity()))
                    return true;
            }
            if(!textAreaWeldPatternRTDefects.getText().equals(selectedRT.getDefects()))
                return true;
            if (cbWeldPatternRTEvaluation.getValue()!=null) {
                if (!cbWeldPatternRTEvaluation.getValue().equals(
                        selectedRT.getEvaluation().getType()))
                    return true;
            }
        }else {
            if (selectedRT.getId()>0)
                return true;
        }
        return false;
    }

    private void updateSelectedWeldPatternFromFields(WeldPatternUI selectedWeldPattern){

        if (cbWeldPatternDetail.getValue()!=null) {
            selectedWeldPattern.setWeldDetail(getWeldDetailFromComboBox(cbWeldPatternDetail));
        }

        if (cbWeldPatternDiameter.getValue()!=null) {
            selectedWeldPattern.setDiameter(cbWeldPatternDiameter.getValue());
        }

        if(cbWeldPatternThickness.getValue()!=null) {
            selectedWeldPattern.setThickness(cbWeldPatternThickness.getValue());
        }

        if (!txfWeldPatternMark.getText().isEmpty()) {
            selectedWeldPattern.setMark(txfWeldPatternMark.getText().trim());
        }
           selectedWeldPattern.setIsHeating(chkWeldPatternHeating.isSelected());
           selectedWeldPattern.setIsHeatTreatment(chkWeldPatternHeatTreatment.isSelected());

        if (cbWeldPatternSteelType.getValue()!=null) {
            selectedWeldPattern.setSteelType(getSteelTypeFromComboBox(cbWeldPatternSteelType));
        }


        selectedWeldPattern.setWeldPositions(getWeldPositionsFromMenuButton(menuButtonWeldPosition));

        if (cbWeldPatternWeldMethod.getValue()!=null) {
            selectedWeldPattern.setWeldMethod(getWeldMethodFromComboBox(cbWeldPatternWeldMethod));
        }

        if (chkWeldPatternElectrode.isSelected()){
            selectedWeldPattern.setElectrode(getElectrodeFromComboBox(cbWeldPatternElectrode));
        }else {
            selectedWeldPattern.setElectrode(null);
        }

        if (chkWeldPatternWeldWire.isSelected()){
            selectedWeldPattern.setWeldWire(getWeldWireFromComboBox(cbWeldPatternWeldWire));
        }else {
            selectedWeldPattern.setWeldWire(null);
        }

        if (chkWeldPatternWeldGas.isSelected()){
            selectedWeldPattern.setWeldGas(getWeldGasFromComboBox(cbWeldPatternWeldGas));
        }else {
            selectedWeldPattern.setWeldGas(null);
        }
        //TODO finish getters
        if (chkWeldPatternRT.isSelected()) {
            selectedWeldPattern.setRadiationTest(getRTFromPane());
        }else {
            selectedWeldPattern.setRadiationTest(null);
        }
//
//        if (chkWeldPatternVT.isSelected()){
//            selectedWeldPattern.setVisualTest(getVTFromPane());
//        }else {
//            selectedWeldPattern.setVisualTest(null);
//        }
//
//        if (chkWeldPatternMech.isSelected()){
//            selectedWeldPattern.setMechanicalTest(getMechTestFromPane());
//        }
    }

    private void saveRadiationTestInDB(RadiationTestUI radiationTestUI){
        RadiationTest rt = radiationTestServiceUI.getRadiationTestFromUI(radiationTestUI);
            if(rt!=null){
               if(rt.getRadiationTestId()!=null){
                   radiationTestService.update(rt);
               }else {
                   radiationTestService.insert(rt);
               }
            }

    }



    private RadiationTestUI getRTFromPane(){
        RadiationTestUI radiationTestUI = new RadiationTestUI();
        radiationTestUI.setNumber(txfWeldPatternRTNumber.getText().trim());
        if(dpWeldPatternRTDate.getValue()!=null) {
            radiationTestUI.setDate(DateUtil.getDate(dpWeldPatternRTDate.getValue()));
            radiationTestUI.setDateFormat(DateUtil.format(radiationTestUI.getDate()));
        }
        if(cbWeldPatternRTSensitivity.getValue()!=null) {
            radiationTestUI.setSensitivity(cbWeldPatternRTSensitivity.getValue().toString());
        }
        if (!textAreaWeldPatternRTDefects.getText().trim().isEmpty()){
            radiationTestUI.setDefects(textAreaWeldPatternRTDefects.getText().trim());
        }
        if(cbWeldPatternRTEvaluation.getValue()!=null){
            radiationTestUI.setEvaluation(getEvaluationFromComboBox(cbWeldPatternRTEvaluation));
        }
        return radiationTestUI;
    }

    private EvaluationUI getEvaluationFromComboBox(ComboBox<String> cbEvaluation){
        EvaluationUI evaluationUI = null;
        if(cbEvaluation.getValue()==null)
            return null;
        for (Evaluation e : evaluationService.getAll()){
            evaluationUI = new EvaluationUI(e);
            if(evaluationUI.getType().equals(cbEvaluation.getValue()))
                return evaluationUI;
        }
        return evaluationUI;
    }

    private WeldGasUI getWeldGasFromComboBox(ComboBox<String>  cbWeldGas){
        WeldGasUI weldGasUI = null;
        for (WeldGas wg: weldGasService.getAll()){
            weldGasUI = new WeldGasUI(wg);
            if (weldGasUI.getType().equals(cbWeldGas.getValue())){
                return weldGasUI;
            }
        }
        return weldGasUI;
    }

    private WeldWireUI getWeldWireFromComboBox(ComboBox<String>  cbWeldWire){
        WeldWireUI weldWireUI = null;
        for (WeldWire ww: weldWireService.getAll()){
            weldWireUI = new WeldWireUI(ww);
            if (weldWireUI.getType().equals(cbWeldWire.getValue())){
                return weldWireUI;
            }
        }
        return weldWireUI;
    }

    private ElectrodeUI getElectrodeFromComboBox(ComboBox<String>  cbElectrode){
        ElectrodeUI electrodeUI = null;
        for (Electrode e: electrodeService.getAll()){
            electrodeUI = new ElectrodeUI(e);
            if (electrodeUI.getType().equals(cbElectrode.getValue())){
                return electrodeUI;
            }
        }
        return electrodeUI;
    }

    private WeldMethodUI getWeldMethodFromComboBox(ComboBox<String> cbWeldMethod){
        WeldMethodUI weldMethodUI = null;
        for (WeldMethod wm : weldMethodService.getAll()){
            weldMethodUI = new WeldMethodUI(wm);
            if (weldMethodUI.getNameCode().equals(cbWeldMethod.getValue())){
                return weldMethodUI;
            }
        }
        return weldMethodUI;
    }

    private ObservableList<WeldPositionUI> getWeldPositionsFromMenuButton(MenuButton mbtWeldPosition){
        ObservableList<WeldPositionUI> weldPositionUIList = FXCollections.observableArrayList();
        ObservableList<String> checkedWeldPositions = FXCollections.observableArrayList();
        for(MenuItem mi : mbtWeldPosition.getItems()){
            CheckMenuItem chkItem = (CheckMenuItem)mi;
            if(chkItem.isSelected()){
                checkedWeldPositions.add(chkItem.getText());
            }
        }
        for (WeldPosition wp : weldPositionService.getAll()){
            WeldPositionUI weldPositionUI = new WeldPositionUI(wp);
            if (checkedWeldPositions.contains(weldPositionUI.getCode())){
                weldPositionUIList.add(weldPositionUI);
            }
        }
        return weldPositionUIList;
    }

    private SteelTypeUI getSteelTypeFromComboBox(ComboBox<String> cbSteelType){
        SteelTypeUI steelTypeUI = null;
        for (SteelType st : steelTypeService.getAll()){
            steelTypeUI = new SteelTypeUI(st);
            if (steelTypeUI.getType().equals(cbSteelType.getValue())){
                return steelTypeUI;
            }
        }
        return steelTypeUI;
    }

    private WeldDetailUI getWeldDetailFromComboBox(ComboBox<String> cbWeldDetail){
        WeldDetailUI weldDetailUI = null;
        for (WeldDetail wd : weldDetailService.getAll()){
            weldDetailUI = new WeldDetailUI(wd);
            if (weldDetailUI.getDetailTypeCode().equals(cbWeldDetail.getValue())){
                return weldDetailUI;
            }
        }
        return weldDetailUI;
    }


    @FXML
    private void saveSelectedWeldPattern(){
        if(selectedWeldPatternUI==null) {
            LOGGER.warn("SAVE SELECTED WELD PATTERN: SelectedWeldPattern is null");
            return;
        }
        if(selectedWeldPatternUI.getId()==0){
            updateSelectedWeldPatternFromFields(selectedWeldPatternUI);
        }
        LOGGER.debug("SAVE SELECTED WELD PATTERN: isSelectedWeldPatternChanged(): "
                +isSelectedWeldPatternChanged(selectedWeldPatternUI)+
        " changed in: "+chagedFields);
    }


    @FXML
    private void activeComboBoxElectrode(){
        if(cbWeldPatternElectrode.isDisable()){
            cbWeldPatternElectrode.setDisable(false);
        }else {
            cbWeldPatternElectrode.setDisable(true);
        }
    }

    @FXML
    private void activeComboBoxWeldWire(){
        if(cbWeldPatternWeldWire.isDisable()){
            cbWeldPatternWeldWire.setDisable(false);
        }else {
            cbWeldPatternWeldWire.setDisable(true);
        }
    }

    @FXML
    private void activeComboBoxWeldGas(){
        if(cbWeldPatternWeldGas.isDisable()){
            cbWeldPatternWeldGas.setDisable(false);
        }else {
            cbWeldPatternWeldGas.setDisable(true);
        }
    }

    @FXML
    private void addNewWeldPatternToPersProtocol(){
        tabWeldPattern.setDisable(false);
        tabWeldPattern.getTabPane().getSelectionModel().select(tabWeldPattern);
        accordionWeldPatternPane.setExpandedPane(titlePaneWeldPatternOption);
        selectedWeldPatternUI = new WeldPatternUI(selectedPersonalProtocolUI);
        showSelectedWeldPattern(selectedWeldPatternUI);
    }

    @FXML
    private void editWeldPattern(){
        if(selectedPersonalProtocolUI==null)
            return;

        selectedWeldPatternUI = tableViewWeldPatterns.getSelectionModel().getSelectedItem();
        if(selectedWeldPatternUI==null)
            return;

        tabWeldPattern.setDisable(false);
        tabWeldPattern.getTabPane().getSelectionModel().select(tabWeldPattern);
        accordionWeldPatternPane.setExpandedPane(titlePaneWeldPatternOption);
        showSelectedWeldPattern(selectedWeldPatternUI);
    }
    @FXML
    private void discardSaveWeldPattern(){
        tabWeldPattern.setDisable(true);
        tabWeldPattern.getTabPane().getSelectionModel().select(tabPersonalProtocol);
        titlePanePersProtocolWeldPattern.requestFocus();
    }

    @FXML
    private void weldPatternTabSelectionChanged(){
        if (!tabWeldPattern.isSelected()){
            tabWeldPattern.setDisable(true);
        }

    }

    @FXML
    private void cleanResolutionText(){
        textAreaResolutionCert.clear();
    }

    @FXML
    private void closeProtocolPane(){
        if(mainProtocolPane.isVisible()){
            mainProtocolPane.setVisible(false);
            getControllerManager().getMainFrameController().showLogo();
        }
    }

    @FXML
    private void addAllNDTdocsToCurrentProtocol(){
        currentNTDdocs.clear();
        currentNTDdocs.addAll(allNTDdocs);
        allNTDdocs.clear();
        checkEmptiesNTDdocsLists();
    }

    @FXML
    private void addSingleNDTdocToCurrentProtocol(){
        String selectedDoc = listViewAllDocs.getSelectionModel().getSelectedItem();
        if(null != selectedDoc){
            currentNTDdocs.add(selectedDoc);
            allNTDdocs.remove(selectedDoc);
        }
       checkEmptiesNTDdocsLists();
    }
    @FXML
    private void removeAllNDTdocsFromCurrentProtocol(){
        allNTDdocs.addAll(currentNTDdocs);
        currentNTDdocs.clear();
        checkEmptiesNTDdocsLists();
    }

    @FXML
    private void removeSingleNDTdocFromCurrentProtocol(){
        String selectedDoc = listViewAttestDocs.getSelectionModel().getSelectedItem();
        if(null != selectedDoc){
            allNTDdocs.add(selectedDoc);
            currentNTDdocs.remove(selectedDoc);
        }
        checkEmptiesNTDdocsLists();
    }

    @FXML
    private void refreshProtocolsTreeView(){
        initProtocolsTreeView();
    }

    @FXML
    private void gotoSelectedJournal(){
        if(selectedTotalProtocolUI!=null){
            JournalUI journalUI = selectedTotalProtocolUI.getJournal();
            closeProtocolPane();
            getControllerManager().showJournalPane();
            getControllerManager().getJournalController().journalTableView.getSelectionModel().clearSelection();
            getControllerManager().getJournalController().journalTableView.getSelectionModel().select(journalUI);
            getControllerManager().getJournalController().journalTableView.requestFocus();
        }
    }

    private class ComboBoxCommissionHandler implements InvalidationListener{

        @Override
        public void invalidated(Observable observable) {
            LOGGER.debug("COMBO BOX COMMISSION HANDLER: invalidated");
            Long selectedID = cbIDCommissionCert.getSelectionModel().getSelectedItem();
            if(selectedID !=null){
                for(CommissionCertificationUI cc : commissionCertificationUIList){
                    if(selectedID == cc.getId()){
                        selectedTotalProtocolUI.setCommissionCertification(cc);
                        initCommissionTitlePane(selectedTotalProtocolUI);
                        return;
                    }
                }
            }else {
                clearCommissionTitlePane();
            }
        }

    }

    private class CheckMenuItemHandler implements EventHandler<ActionEvent>{
        @Override
        public void handle(ActionEvent event) {
            fillTextFieldWeldPosition();
        }
    }

    private class CheckBoxWeldPatternTestHandler implements EventHandler<MouseEvent>{
        @Override
        public void handle(MouseEvent event) {
            if(event.getSource().getClass().equals(CheckBox.class)){
                CheckBox checkBox = (CheckBox)event.getSource();
                if(checkBox.equals(chkWeldPatternVT)){
                    setDisabledVTPane(!checkBox.isSelected());
                    return;
                }
                if(checkBox.equals(chkWeldPatternRT)){
                    setDisabledRTPane(!checkBox.isSelected());
                    return;
                }
                if(checkBox.equals(chkWeldPatternMech)){
                    setDisabledMechPane(!checkBox.isSelected());
                    return;
                }

            }
        }
    }

    private class ListViewHandler implements EventHandler<Event> {

        private void doSelectProtocol(){

            TreeItem<String> protocolItem = protocolsTreeView.getSelectionModel().getSelectedItem();
            if (protocolItem==null){
                LOGGER.debug("TREE LIST VIEW HANDLER: No one tree item is selected");
                return;
            }
            if(protocolItem.getValue().equals(TREE_ROOT_ITEM_NAME)){
                selectedTotalProtocolUI = null;
                LOGGER.debug("TREE LIST VIEW HANDLER: No one protocol is selected");
                return;
            }
            if(protocolItem.getValue().contains(PERSONAL_PROTOCOL_PREFIX_NAME)){
                TreeItem<String> totalProtocolItem = protocolItem.getParent();
                selectedTotalProtocolUI =
                        totalProtocolServiceUI.getTotalProtocolUIByToStringMethod(totalProtocolItem.getValue(),cachedTotalProtocols);
                selectedPersonalProtocolUI = getPersProtocolByItemName(selectedTotalProtocolUI,protocolItem.getValue());
                showSelectedTotalProtocol(selectedTotalProtocolUI);
                showSelectedPersProtocol(selectedPersonalProtocolUI);

                LOGGER.debug("TREE LIST VIEW HANDLER: The personal protocol is selected: "+
                    selectedPersonalProtocolUI);
                return;
            }
            selectedTotalProtocolUI =
                    totalProtocolServiceUI.getTotalProtocolUIByToStringMethod(protocolItem.getValue(), cachedTotalProtocols);
            if(selectedTotalProtocolUI != null){
                LOGGER.debug("TREE LIST VIEW HANDLER: Selected protocol is: \n"+"id = "+selectedTotalProtocolUI.getId()+
                        "; "+selectedTotalProtocolUI+"\n");
                showSelectedTotalProtocol(selectedTotalProtocolUI);
                //if(!totalProtocolBorderPane.isDisabled())
                //    totalProtocolBorderPane.setDisable(true);
            }
        }

        @Override
        public void handle(Event event) {
            if (event.getClass().equals(KeyEvent.class)){
                KeyEvent keyEvent = (KeyEvent)event;
                if(keyEvent.getCode().equals(KeyCode.DOWN) || keyEvent.getCode().equals(KeyCode.UP)){
                    LOGGER.debug("TREE LIST VIEW HANDLER: Key typed on protocols list");
                    doSelectProtocol();
                    return;
                }
                return;
            }
            LOGGER.debug("TREE LIST VIEW HANDLER: Mouse clicked on protocols list");
            doSelectProtocol();



        }
    }
}
