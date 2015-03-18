package com.lvg.weldercenter.ui.control;

import com.lvg.weldercenter.models.*;
import com.lvg.weldercenter.services.*;
import com.lvg.weldercenter.spring.factories.ServiceFactory;
import com.lvg.weldercenter.spring.factories.ServiceUIFactory;
import com.lvg.weldercenter.ui.entities.*;
import com.lvg.weldercenter.ui.servicesui.PersonalProtocolServiceUI;
import com.lvg.weldercenter.ui.servicesui.TotalProtocolServiceUI;
import com.lvg.weldercenter.ui.util.DateUtil;
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
import org.apache.log4j.Logger;

import java.net.URL;
import java.util.ArrayList;
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

    private TotalProtocolServiceUI totalProtocolServiceUI = ServiceUIFactory.getTotalProtocolServiceUI();
    private PersonalProtocolServiceUI personalProtocolServiceUI = ServiceUIFactory.getPersonalProtocolServiceUI();

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
    ChoiceBox<String> choiceBoxWeldPatternWeldPosition;
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
    private ObservableList<Double> weldPatternDiameterList = FXCollections.observableArrayList();
    private ObservableList<Double> weldPatternThicknessList = FXCollections.observableArrayList();
    private ObservableList<String> weldPatternSteelTypeList = FXCollections.observableArrayList();
    private ObservableList<String> weldPatternElectrodeList = FXCollections.observableArrayList();
    private ObservableList<String> weldPatternWeldWireList = FXCollections.observableArrayList();
    private ObservableList<String> weldPatternWeldGasList = FXCollections.observableArrayList();
    private ObservableList<String> weldPatternAllWeldPosition = FXCollections.observableArrayList();
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
        theoryTestRatingsList.addAll(TheoryTestUI.POSITIVE_RATING_VALUE,TheoryTestUI.NEGATIVE_RATING_VALUE);
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
        initChoiseBoxWeldPosition();
        initWeldPatternTestPane();

    }

    private void initWeldPatternTestPane(){
        chkWeldPatternVT.addEventHandler(MouseEvent.MOUSE_CLICKED, new CheckBoxWeldPatternTestHandler());
        chkWeldPatternRT.addEventHandler(MouseEvent.MOUSE_CLICKED, new CheckBoxWeldPatternTestHandler());
        chkWeldPatternMech.addEventHandler(MouseEvent.MOUSE_CLICKED, new CheckBoxWeldPatternTestHandler());
        setDisabledRTPane(true);
        setDisabledVTPane(true);
        setDisabledMechPane(true);
        initWeldPatternEvaluationList();
        cbWeldPatternRTEvaluation.setItems(weldPatternEvaluationList);
        cbWeldPatternMechEvaluation.setItems(weldPatternEvaluationList);
        cbWeldPatternVTEvaluation.setItems(weldPatternEvaluationList);
        initComboBoxRTSensitivity();
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

    private void initChoiseBoxWeldPosition(){
        weldPatternAllWeldPosition.clear();
        for(WeldPosition wp: weldPositionService.getAll()){
            weldPatternAllWeldPosition.add(wp.getCode());
        }
        choiceBoxWeldPatternWeldPosition.setItems(weldPatternAllWeldPosition);

    }

    private void initComboBoxWeldGas(){
        weldPatternWeldGasList.clear();
        weldPatternWeldGasList.add(EMPTY_STRING_ITEM);
        for(WeldGas wg: weldGasService.getAll()){
            weldPatternWeldGasList.add(wg.getType());
        }
        cbWeldPatternWeldGas.setItems(weldPatternWeldGasList);
        cbWeldPatternWeldGas.setDisable(true);
    }

    private void initComboBoxWeldWire(){
        weldPatternWeldWireList.clear();
        for(WeldWire ww: weldWireService.getAll()){
            weldPatternWeldWireList.add(ww.getType());
        }
        cbWeldPatternWeldWire.setItems(weldPatternWeldWireList);
        cbWeldPatternWeldWire.setDisable(true);
    }

    private void initComboBoxElectrode(){
        weldPatternElectrodeList.clear();
        for (Electrode e : electrodeService.getAll()){
            weldPatternElectrodeList.add(e.getType());
        }
        cbWeldPatternElectrode.setItems(weldPatternElectrodeList);
        cbWeldPatternElectrode.setDisable(true);
    }

    private void initComboBoxWeldMethod(){
        ObservableList<String> weldMethodList = FXCollections.observableArrayList();
        for(WeldMethod wm : weldMethodService.getAll() ){
            WeldMethodUI weldMethodUI = new WeldMethodUI(wm);
            weldMethodList.add(weldMethodUI.getNameCode());
        }
        cbWeldPatternWeldMethod.setItems(weldMethodList);
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
        initPersProtocolWeldPatterns(selectedPersProtocol);
        initTitlePanePersProtocolTheoryTest(selectedPersProtocol);
        initPersProtocolNTDdocs(selectedPersProtocol);
        initPersProtocolResolutionCert(selectedPersProtocol);


    }

    private void showSelectedWeldPattern(WeldPatternUI selectedWeldPattern){

//        initComboBoxDetailType(selectedWeldPattern);
//        initComboBoxDiameter(selectedWeldPattern);
//        initComboBoxThickness(selectedWeldPattern);
//        initComboBoxSteelType(selectedWeldPattern);
//        initComboBoxWeldMethod(selectedWeldPattern);
//        initComboBoxElectrode(selectedWeldPattern);
//        initComboBoxWeldWire(selectedWeldPattern);
//        initComboBoxWeldGas(selectedWeldPattern);
//        initChoiseBoxWeldPosition(selectedWeldPattern);
    }

    private void initChoiseBoxWeldPosition(WeldPatternUI selectedWeldPattern){
        weldPatternAllWeldPosition.clear();
        for(WeldPosition wp: weldPositionService.getAll()){
            weldPatternAllWeldPosition.add(wp.getCode());
        }
        choiceBoxWeldPatternWeldPosition.setItems(weldPatternAllWeldPosition);

    }

    private void initComboBoxWeldGas(WeldPatternUI selectedWeldPattern){
        weldPatternWeldGasList.clear();

        for(WeldGas wg: weldGasService.getAll()){
            weldPatternWeldGasList.add(wg.getType());
        }
        cbWeldPatternWeldGas.setItems(weldPatternWeldGasList);
        cbWeldPatternWeldGas.setDisable(true);
    }

    private void initComboBoxWeldWire(WeldPatternUI selectedWeldPattern){
        weldPatternWeldWireList.clear();
        for(WeldWire ww: weldWireService.getAll()){
            weldPatternWeldWireList.add(ww.getType());
        }
        cbWeldPatternWeldWire.setItems(weldPatternWeldWireList);
        cbWeldPatternWeldWire.setDisable(true);
    }

    private void initComboBoxElectrode(WeldPatternUI selectedWeldPattern){
        weldPatternElectrodeList.clear();
        for (Electrode e : electrodeService.getAll()){
            weldPatternElectrodeList.add(e.getType());
        }
        cbWeldPatternElectrode.setItems(weldPatternElectrodeList);
        cbWeldPatternElectrode.setDisable(true);
    }

    private void initComboBoxWeldMethod(WeldPatternUI selectedWeldPattern){
        ObservableList<String> weldMethodList = FXCollections.observableArrayList();
        for(WeldMethod wm : weldMethodService.getAll() ){
            WeldMethodUI weldMethodUI = new WeldMethodUI(wm);
            weldMethodList.add(weldMethodUI.getNameCode());
        }
        cbWeldPatternWeldMethod.setItems(weldMethodList);
    }

    private void initComboBoxSteelType(WeldPatternUI selectedWeldPattern){
        weldPatternSteelTypeList.clear();
        for (SteelType st: steelTypeService.getAll()){
            SteelTypeUI steelTypeUI = new SteelTypeUI(st);
            weldPatternSteelTypeList.add(steelTypeUI.getType());
        }
        cbWeldPatternSteelType.setItems(weldPatternSteelTypeList);
    }

    private void initComboBoxThickness(WeldPatternUI selectedWeldPattern){
        weldPatternThicknessList.clear();
        for (PatternThickness pt : patternThicknessService.getAll()){
            PatternThicknessUI patternThicknessUI = new PatternThicknessUI(pt);
            weldPatternThicknessList.addAll(patternThicknessUI.getThickness());
        }
        cbWeldPatternThickness.setItems(weldPatternThicknessList);
    }

    private void initComboBoxDiameter(WeldPatternUI selectedWeldPattern){
        weldPatternDiameterList.clear();
        for(PatternDiameter pd: patternDiameterService.getAll()){
            PatternDiameterUI patternDiameterUI = new PatternDiameterUI(pd);
            weldPatternDiameterList.add(patternDiameterUI.getDiameter());
        }
        cbWeldPatternDiameter.setItems(weldPatternDiameterList);
    }

    private void initComboBoxDetailType(WeldPatternUI selectedWeldPattern){
        weldDetailList.clear();
        for (WeldDetail wd : weldDetailService.getAll()){
            WeldDetailUI weldDetailUI = new WeldDetailUI(wd);
            weldDetailList.addAll(weldDetailUI.getDetailTypeCode());
        }
        cbWeldPatternDetail.setItems(weldDetailList);
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
        showSelectedWeldPattern(new WeldPatternUI(selectedPersonalProtocolUI));
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
