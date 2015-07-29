package com.lvg.weldercenter.ui.control;

import com.lvg.weldercenter.models.*;
import com.lvg.weldercenter.services.*;
import com.lvg.weldercenter.spring.factories.ServiceFactory;
import com.lvg.weldercenter.spring.factories.ServiceUIFactory;
import com.lvg.weldercenter.ui.entities.*;
import com.lvg.weldercenter.ui.servicesui.*;
import com.lvg.weldercenter.ui.util.DateUtil;
import com.lvg.weldercenter.ui.util.EventFXUtil;
import com.lvg.weldercenter.ui.util.Printer;
import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
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
import org.controlsfx.control.action.Action;
import org.controlsfx.dialog.Dialogs;

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
    private Integer taskProgress = 0;


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
    private WeldJoinTypeService weldJoinTypeService = ServiceFactory.getWeldJoinTypeService();
    private ElectrodeService electrodeService = ServiceFactory.getElectrodeService();
    private WeldWireService weldWireService = ServiceFactory.getWeldWireService();
    private WeldGasService weldGasService = ServiceFactory.getWeldGasService();
    private EvaluationService evaluationService = ServiceFactory.getEvaluationService();
    private RadiationTestService radiationTestService = ServiceFactory.getRadiationTestService();
    private VisualTestService visualTestService = ServiceFactory.getVisualTestService();
    private MechanicalTestService mechanicalTestService = ServiceFactory.getMechanicalTestService();
    private WeldPatternService weldPatternService = ServiceFactory.getWeldPatternService();

    private TotalProtocolServiceUI totalProtocolServiceUI = ServiceUIFactory.getTotalProtocolServiceUI();
    private PersonalProtocolServiceUI personalProtocolServiceUI = ServiceUIFactory.getPersonalProtocolServiceUI();
    private EvaluationServiceUI evaluationServiceUI = ServiceUIFactory.getEvaluationServiceUI();
    private RadiationTestServiceUI radiationTestServiceUI = ServiceUIFactory.getRadiationTestServiceUI();
    private VisualTestServiceUI visualTestServiceUI = ServiceUIFactory.getVisualTestServiceUI();
    private MechanicalTestServiceUI mechanicalTestServiceUI = ServiceUIFactory.getMechanicalTestServiceUI();
    private WeldPatternServiceUI weldPatternServiceUI = ServiceUIFactory.getWeldPatternServiceUI();

    @FXML
    BorderPane mainProtocolPane;
    @FXML
    TabPane tabPaneAllProtocols;

    @FXML
    TreeView<String> protocolsTreeView;
    @FXML
    TextField txfSearch;

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
    @FXML
    Button btCancelSaveTotalProtocol;
    @FXML
    Button btSaveTotalProtocol;

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
    ComboBox<String> cbAttestType;
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
    @FXML
    Button btCancelSavePersonalProtocol;
    @FXML
    Button btSavePersonalProtocol;

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
    TextField txfWeldPatternWeldJoinType;
    @FXML
    MenuButton menuButtonWeldJoinType;
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
    TextField txfWeldPatternMechClearance;
    @FXML
    ComboBox<String> cbWeldPatternMechEvaluation;
    @FXML
    Button btWeldPatternSave;
    @FXML
    Button btWeldPatternCancel;

    @FXML
    ProgressBar prgsBarUpdater;

    private boolean isWeldPatternSaved = false;



    private TotalProtocolUI selectedTotalProtocolUI = null;
    private PersonalProtocolUI selectedPersonalProtocolUI = null;
    private WeldPatternUI selectedWeldPatternUI = null;
    private List<String> chagedFields = new ArrayList<String>();
    private List<String> attestTypes = new ArrayList<String>(){{
       add("Первичная");
       add("Дополнительная");
       add("Периодическая");
       add("Внеочередная");
    }};

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
    private ObservableList<CheckMenuItem> weldPatternAllWeldJoinTypes = FXCollections.observableArrayList();
    private ObservableList<String> weldPatternSelectedWeldJoinTypes = FXCollections.observableArrayList();
    private ObservableList<String> weldPatternEvaluationList = FXCollections.observableArrayList();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LOGGER.debug("INITIALIZING ProtocolPane");
        init();
    }

    private void init(){
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
        tabPersonalProtocol.setDisable(true);
        initComboBoxAttestType();

    }

    private void initComboBoxAttestType(){
        cbAttestType.getItems().clear();
        cbAttestType.getItems().addAll(attestTypes);
    }

    private void initComboBoxAttestType(PersonalProtocolUI pp){
        initComboBoxAttestType();
        if(cbAttestType.getItems().contains(pp.getAttestType()))
            cbAttestType.getSelectionModel().select(pp.getAttestType());
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
        prgsBarUpdater.setVisible(true);
        prgsBarUpdater.progressProperty().unbind();
        Task updator = createProtocolsUpdater();
        prgsBarUpdater.progressProperty().bind(updator.progressProperty());
        Thread t = new Thread(updator);
        t.setName("INIT ProtocolsTreeView Thread");
        t.start();
    }

    private Task<Void> createProtocolsUpdater(){
        return new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                updateProgress(5, MAX_TASK_PROGRESS_VALUE);
                totalProtocols.clear();
                cachedTotalProtocols.clear();
                List<Journal> journalsDb = journalService.getAll();
                updateProgress(20, MAX_TASK_PROGRESS_VALUE);
                List<PersonalProtocol> personalProtocolsDB = personalProtocolService.getAll();
                updateProgress(30, MAX_TASK_PROGRESS_VALUE);
                List<TotalProtocol> totalProtocolList  = totalProtocolService.getAll();
                updateProgress(40, MAX_TASK_PROGRESS_VALUE);
                List<JournalUI> journalUIList = new ArrayList<JournalUI>();
                List<PersonalProtocolUI> protocolUIFromDB = new ArrayList<PersonalProtocolUI>();

                for (PersonalProtocol pp : personalProtocolsDB){
                    if(pp.getJournal()!=null){
                        journalUIList.add(new JournalUI(pp.getJournal()));
                    }
                }
                updateProgress(70, MAX_TASK_PROGRESS_VALUE);
                double currProgress = 70;
                double stepProgress = 30 / totalProtocolList.size();
                for (TotalProtocol tp : totalProtocolList){
                    currProgress+=stepProgress;
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
                    updateProgress(currProgress, MAX_TASK_PROGRESS_VALUE);
                }
                updateProgress(100, MAX_TASK_PROGRESS_VALUE);

                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        prgsBarUpdater.setVisible(false);
                        TreeItem<String> rootItem = new TreeItem<String>(TREE_ROOT_ITEM_NAME);
                        rootItem.getChildren().clear();
                        rootItem.getChildren().addAll(totalProtocols);
                        protocolsTreeView.setRoot(rootItem);
                        protocolsTreeView.getRoot().setExpanded(true);
                        protocolsTreeView.addEventHandler(MouseEvent.MOUSE_CLICKED, new ListViewHandler());
                        protocolsTreeView.addEventHandler(KeyEvent.KEY_RELEASED, new ListViewHandler());
                        txfSearch.textProperty().addListener(new SearchInvalidateHandler());
                        protocolsTreeView.getSelectionModel().select(protocolsTreeView.getRoot());
                        protocolsTreeView.fireEvent(EventFXUtil.getMouseClickEvent());
                    }
                });


                return null;
            }
        };
    }

    private void initTotalProtocolTab(){
        accordionTotalProtocol.setExpandedPane(titlePaneTotalProtocolWelders);
        initTotalProtocolWeldersTableView();
        initCommissionComboBox();
        tabTotalProtocol.setDisable(true);
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
        if (currentCommission == null){
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
        initMenuButtonWeldJoinType();
        initTextFieldWeldPosition();
        initWeldPatternTestPane();
        initTextFieldWeldJoinType();

    }

    private void initMenuButtonWeldJoinType(){
        weldPatternAllWeldJoinTypes.clear();
        for(WeldJoinType wjt: weldJoinTypeService.getAll()){
            CheckMenuItem item = new CheckMenuItem(wjt.getType());
            weldPatternAllWeldJoinTypes.add(item);
            item.addEventHandler(ActionEvent.ACTION, new CheckMenuItemHandler());
        }
        menuButtonWeldJoinType.getItems().clear();
        menuButtonWeldJoinType.getItems().addAll(weldPatternAllWeldJoinTypes);
    }

    private void initTextFieldWeldJoinType(){ fillTextFieldWeldJoinType();}

    private void fillTextFieldWeldJoinType(){
        txfWeldPatternWeldJoinType.clear();
        if(menuButtonWeldJoinType.getItems().isEmpty())
            return;
        StringBuilder text = new StringBuilder();
        for(MenuItem mi : menuButtonWeldJoinType.getItems()){
            CheckMenuItem chkItem = (CheckMenuItem)mi;
            if (chkItem.isSelected()){
                text.append(chkItem.getText());
                if (menuButtonWeldJoinType.getItems().iterator().hasNext()){
                    text.append(" ");
                }
            }
        }
        txfWeldPatternWeldJoinType.setText(text.toString());
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
       txfWeldPatternMechClearance.setDisable(isDisabled);
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
        txfWeldPatternMechClearance.clear();
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
        if(cbWeldPatternDetail.getValue()!=null && !cbWeldPatternDetail.getValue().contains("Труба")){
            cbWeldPatternDiameter.setDisable(true);
        }else{
            cbWeldPatternDiameter.setDisable(false);
        }


    }

    private void initComboBoxDetailType(){
        weldDetailList.clear();
        for (WeldDetail wd : weldDetailService.getAll()){
            WeldDetailUI weldDetailUI = new WeldDetailUI(wd);
            weldDetailList.addAll(weldDetailUI.getDetailTypeCode());
        }
        cbWeldPatternDetail.setItems(weldDetailList);
        cbWeldPatternDetail.valueProperty().addListener(new ComboBoxWeldDetailHandler());
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
        tabPersonalProtocol.setDisable(true);
        tabTotalProtocol.setDisable(false);


    }

    private void showSelectedPersProtocol(PersonalProtocolUI selectedPersProtocol){
        txfPersonalProtocolNumber.setText(selectedPersProtocol.getNumber());
        dpPersonalProtocolDate.setValue(DateUtil.getLocalDate(selectedPersProtocol.getDatePeriodicalCert()));
        lbWelderFullName.setText(selectedPersProtocol.toString());
        initComboBoxAttestType(selectedPersProtocol);
        tabPersonalProtocol.getTabPane().getSelectionModel().select(tabPersonalProtocol);
        titlePanePersProtocolWeldPattern.setExpanded(true);
        initPersProtocolWeldPatterns(selectedPersProtocol);
        initTitlePanePersProtocolTheoryTest(selectedPersProtocol);
        initPersProtocolNTDdocs(selectedPersProtocol);
        initPersProtocolResolutionCert(selectedPersProtocol);
        tabPersonalProtocol.setDisable(false);
        tabWeldPattern.setDisable(true);
        tableViewWeldPatterns.setDisable(false);


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
        initMenuButtonWeldJoinType(selectedWeldPattern);
        initTextFieldWeldPosition(selectedWeldPattern);
        initTextFieldWeldJoinType(selectedWeldPattern);
        initTitlePaneWeldPatternTest(selectedWeldPattern);
        isWeldPatternSaved = false;
    }

    private void setDisabledAllTabs(){
        for (Tab t : tabPaneAllProtocols.getTabs()){
            t.setDisable(true);
        }
    }

    private void initMenuButtonWeldJoinType(WeldPatternUI selectedWeldPatternUI) {
        initMenuButtonWeldJoinType();
        ObservableList<WeldJoinTypeUI> weldJoinTypes = selectedWeldPatternUI.getWeldJoinTypes();
        for (MenuItem mi : menuButtonWeldJoinType.getItems()) {
            CheckMenuItem chkItem = (CheckMenuItem) mi;
            for (WeldJoinTypeUI wjt : weldJoinTypes) {
                if (mi.getText().equals(wjt.getType())) {
                    chkItem.setSelected(true);
                }
            }
        }
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
        txfWeldPatternMechAngle.setText(mechanicalTestUI.getAngle() + "");
        txfWeldPatternMechClearance.setText(mechanicalTestUI.getClearance()+"");
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

    private void initTextFieldWeldJoinType(WeldPatternUI selectedWeldPattern){
        initTextFieldWeldJoinType();
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
        }else{
            textAreaResolutionCert.clear();
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
        if (theoryTestUI != null) {
            txfTheoryTestTicketNumber.setText(theoryTestUI.getTicketNumber());
            initComboBoxTheoryTestRating(theoryTestUI);
        }else {
            txfTheoryTestTicketNumber.clear();
            cbTheoryTestRating.getSelectionModel().clearSelection();
        }

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
            if (selectedWeldPattern.getElectrode() != null) {
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
            if (selectedWeldPattern.getWeldWire() != null) {
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
            if (selectedWeldPattern.getWeldGas() != null) {
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
            if(!txfWeldPatternMechClearance.getText().trim().equals(
                    String.valueOf(selectedMechTest.getClearance())))
                return true;
            if (cbWeldPatternMechEvaluation.getValue()!=null) {
                if (!cbWeldPatternMechEvaluation.getValue().equals(
                        selectedMechTest.getEvaluation().getType()))
                    return true;
            }
        }else {
            if (selectedMechTest != null)
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
            if (selectedVT != null)
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
            if (selectedRT != null)
                return true;
        }
        return false;
    }

    private void updateSelectedWeldPatternFromFields(WeldPatternUI selectedWeldPattern){

        if (cbWeldPatternDetail.getValue()!=null) {
            selectedWeldPattern.setWeldDetail(getWeldDetailFromComboBox(cbWeldPatternDetail));
        }else {
            selectedWeldPattern.setWeldDetail(null);
        }

        if (cbWeldPatternDiameter.getValue()!=null) {
            if (cbWeldPatternDiameter.isDisable())
                selectedWeldPattern.setDiameter(0);
            else
                selectedWeldPattern.setDiameter(cbWeldPatternDiameter.getValue());
        }

        if(cbWeldPatternThickness.getValue()!=null) {
            selectedWeldPattern.setThickness(cbWeldPatternThickness.getValue());
        }else {
            selectedWeldPattern.setThickness(0);
        }

        if (!txfWeldPatternMark.getText().isEmpty()) {
            selectedWeldPattern.setMark(txfWeldPatternMark.getText().trim());
        }
           selectedWeldPattern.setIsHeating(chkWeldPatternHeating.isSelected());
           selectedWeldPattern.setIsHeatTreatment(chkWeldPatternHeatTreatment.isSelected());

        if (cbWeldPatternSteelType.getValue()!=null) {
            selectedWeldPattern.setSteelType(getSteelTypeFromComboBox(cbWeldPatternSteelType));
        }else {
            selectedWeldPattern.setSteelType(null);
        }


        selectedWeldPattern.setWeldPositions(getWeldPositionsFromMenuButton(menuButtonWeldPosition));
        selectedWeldPattern.setWeldJoinTypes(getWeldJoinTypesFromMenuButton(menuButtonWeldJoinType));

        if (cbWeldPatternWeldMethod.getValue()!=null) {
            selectedWeldPattern.setWeldMethod(getWeldMethodFromComboBox(cbWeldPatternWeldMethod));
        }else {
            selectedWeldPattern.setWeldMethod(null);
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

        if (chkWeldPatternRT.isSelected()) {
            if (selectedWeldPattern.getRadiationTest()!=null){
                deleteRadiationTestFromDB(selectedWeldPatternUI.getRadiationTest());
            }
            selectedWeldPattern.setRadiationTest(getRTFromPane());
            //saveRadiationTestInDB(selectedWeldPatternUI.getRadiationTest());
        }else {
            if (selectedWeldPattern.getRadiationTest()!=null){
                deleteRadiationTestFromDB(selectedWeldPatternUI.getRadiationTest());
            }
            selectedWeldPattern.setRadiationTest(null);
        }

        if (chkWeldPatternVT.isSelected()){
            if (selectedWeldPattern.getVisualTest()!=null){
                deleteVisualTestFromDB(selectedWeldPatternUI.getVisualTest());

            }
            selectedWeldPattern.setVisualTest(getVTFromPane());
           // saveVisualTestInDB(selectedWeldPatternUI.getVisualTest());
        }else {
            if (selectedWeldPattern.getVisualTest()!=null){
                deleteVisualTestFromDB(selectedWeldPatternUI.getVisualTest());
            }
            selectedWeldPattern.setVisualTest(null);
        }

        if (chkWeldPatternMech.isSelected()){
            if (selectedWeldPatternUI.getMechanicalTest() != null){
                deleteMechTestUIFromDB(selectedWeldPatternUI.getMechanicalTest());
            }
            selectedWeldPattern.setMechanicalTest(getMechTestFromPane());
            //saveMechTestUIInDB(selectedWeldPatternUI.getMechanicalTest());
        }else {
            if (selectedWeldPatternUI.getMechanicalTest() != null){
                deleteMechTestUIFromDB(selectedWeldPatternUI.getMechanicalTest());
            }
            selectedWeldPatternUI.setMechanicalTest(null);
        }
    }

    private void deleteMechTestUIFromDB(MechanicalTestUI mechanicalTestUI){
        if (mechanicalTestUI.getId() > 0){
            MechanicalTest mechTest = mechanicalTestService.get(mechanicalTestUI.getId());
            if (mechTest != null){
                mechanicalTestService.delete(mechTest);
            }
        }
    }

    private void saveMechTestUIInDB(MechanicalTestUI mechTestUI){
        MechanicalTest mt = mechanicalTestServiceUI.getMechTestFromMechTestUI(mechTestUI);
        if(mt!=null){
            if(mt.getMechanicalTestId()!=null){
                mechanicalTestService.update(mt);
            }else {
                mechanicalTestService.insert(mt);
            }
        }
    }

    private MechanicalTestUI getMechTestFromPane(){
        MechanicalTestUI mechanicalTestUI = new MechanicalTestUI();
        mechanicalTestUI.setNumber(txfWeldPatternMechNumber.getText().trim());
        if (dpWeldPatternMechDate.getValue()!=null){
            mechanicalTestUI.setDate(DateUtil.getDate(dpWeldPatternMechDate.getValue()));
            mechanicalTestUI.setDateFormat(DateUtil.format(mechanicalTestUI.getDate()));
        }
        Double clearance = 0.0;
        Double angle = 0.0;
        try{
            String angleText = txfWeldPatternMechAngle.getText().trim();
            if (!angleText.isEmpty())
                angle = Double.valueOf(angleText);

        }catch (NumberFormatException ex){
            LOGGER.warn("GET MECH TEST FROM PANE: not valid angle in text field :"+txfWeldPatternMechAngle.getText(),ex);
        }
        try{
            String clearanceText = txfWeldPatternMechClearance.getText().trim();
            if (!clearanceText.isEmpty())
                clearance = Double.valueOf(clearanceText);

        }catch (NumberFormatException ex){
            LOGGER.warn("GET MECH TEST FROM PANE: not valid clearance in text field :"
                    +txfWeldPatternMechClearance.getText(),ex);
        }
        mechanicalTestUI.setClearance(clearance);
        mechanicalTestUI.setAngle(angle);
        if (cbWeldPatternMechEvaluation.getValue() != null){
            mechanicalTestUI.setEvaluation(getEvaluationFromComboBox(cbWeldPatternMechEvaluation));
        }else{
            mechanicalTestUI.setEvaluation(null);
        }
        return mechanicalTestUI;
    }

    private void saveVisualTestInDB(VisualTestUI visualTestUI){
        VisualTest vt = visualTestServiceUI.getVisualTestFromVisualTestUI(visualTestUI);
        if(vt!=null){
            if(vt.getVisuaLTestId()!=null){
                visualTestService.update(vt);
            }else {
                visualTestService.insert(vt);
            }
        }
    }

    private void deleteVisualTestFromDB(VisualTestUI visualTestUI){
        if (visualTestUI.getId()>0){
            VisualTest vt = visualTestService.get(visualTestUI.getId());
            if(vt!=null)
                visualTestService.delete(vt);
        }
    }

    private VisualTestUI getVTFromPane(){
        VisualTestUI visualTestUI = new VisualTestUI();
        visualTestUI.setNumber(txfWeldPatternVTNumber.getText().trim());
        if(dpWeldPatternVTDate.getValue()!=null){
            visualTestUI.setDate(DateUtil.getDate(dpWeldPatternVTDate.getValue()));
            visualTestUI.setDateFormat(DateUtil.format(visualTestUI.getDate()));
        }
        visualTestUI.setDefects(textAreaWeldPatternVTDefects.getText().trim());
        if (cbWeldPatternVTEvaluation.getValue()!=null){
            visualTestUI.setEvaluation(getEvaluationFromComboBox(cbWeldPatternVTEvaluation));
        }else {
            visualTestUI.setEvaluation(null);
        }
        return visualTestUI;
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

    private void deleteRadiationTestFromDB(RadiationTestUI radiationTestUI){
        if (radiationTestUI.getId()>0){
            RadiationTest rt = radiationTestService.get(radiationTestUI.getId());
            if (rt!=null)
                radiationTestService.delete(rt);
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
        }else {
            radiationTestUI.setEvaluation(null);
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
            String nameCode = weldMethodUI.getNameCode();
            if (nameCode.equals(cbWeldMethod.getValue())){
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

    private ObservableList<WeldJoinTypeUI> getWeldJoinTypesFromMenuButton(MenuButton mbtWeldJoinType){
        ObservableList<WeldJoinTypeUI> weldJoinTypeList = FXCollections.observableArrayList();
        ObservableList<String> checkedWeldJoinTypes = FXCollections.observableArrayList();
        for (MenuItem mi : mbtWeldJoinType.getItems()){
            CheckMenuItem chkItem = (CheckMenuItem)mi;
            if (chkItem.isSelected()){
                checkedWeldJoinTypes.add(chkItem.getText());
            }
        }
        for (WeldJoinType wjt : weldJoinTypeService.getAll()){
            WeldJoinTypeUI weldJoinTypeUI = new WeldJoinTypeUI(wjt);
            if (checkedWeldJoinTypes.contains(weldJoinTypeUI.getType())){
                weldJoinTypeList.addAll(weldJoinTypeUI);
            }
        }
        return weldJoinTypeList;
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

    private void selectParentTotalProtocol(){
        TreeItem<String> selectedItem = protocolsTreeView.getSelectionModel().getSelectedItem();
        if (selectedItem == null)
            return;
            if (selectedItem.getValue().contains(PERSONAL_PROTOCOL_PREFIX_NAME)){
            protocolsTreeView.getSelectionModel().select(selectedItem.getParent());
            protocolsTreeView.requestFocus();
        }
    }

    private void updateSelectedPersonalProtocolFromFields(PersonalProtocolUI selectedPersProtocol){
        if (selectedPersProtocol == null)
            return;

        if (txfPersonalProtocolNumber.getText().isEmpty()){
            selectedPersProtocol.setNumber("");
        }else {
            selectedPersProtocol.setNumber(txfPersonalProtocolNumber.getText().trim());
        }

        if (dpPersonalProtocolDate.getValue()!=null){
            selectedPersProtocol.setDatePeriodicalCert(DateUtil.getDate(dpPersonalProtocolDate.getValue()));
            selectedPersProtocol.setDatePeriodicalCertFormat(
                    DateUtil.format(selectedPersProtocol.getDatePeriodicalCert()));
        }else {
            selectedPersProtocol.setDatePeriodicalCert(null);
            selectedPersProtocol.setDatePeriodicalCertFormat("");
        }

        if (cbAttestType.getValue()!=null){
            selectedPersProtocol.setAttestType(cbAttestType.getValue());
        }else {
            selectedPersProtocol.setAttestType("");
        }

        ObservableList<WeldPatternUI> weldPatternUIs = tableViewWeldPatterns.getItems();
        if (weldPatternUIs != null && !weldPatternUIs.isEmpty()) {
            selectedPersProtocol.setWeldPatterns(weldPatternUIs);
        }else {
            selectedPersProtocol.getWeldPatterns().clear();
        }

        ObservableList<NDTDocumentUI> ndtDocumentUIs = getNDTDocumentsUIFromListView(listViewAttestDocs);
        selectedPersProtocol.setNdtDocuments(ndtDocumentUIs);
        selectedPersProtocol.setTheoryTest(getTheoryTestUIFromPane());
        selectedPersProtocol.setResolutionCertification(getResolutionCertUIFromPane());
    }

    private  ResolutionCertificationUI getResolutionCertUIFromPane(){
        ResolutionCertificationUI rsUI = new ResolutionCertificationUI();
        rsUI.setTextResolution(textAreaResolutionCert.getText());
        return rsUI;
    }

    private TheoryTestUI getTheoryTestUIFromPane(){
        TheoryTestUI theoryTestUI = new TheoryTestUI();
        if (txfTheoryTestTicketNumber.getText().trim().isEmpty()){
            theoryTestUI.setTicketNumber("");
        }else {
            theoryTestUI.setTicketNumber(txfTheoryTestTicketNumber.getText().trim());
        }
        if (cbTheoryTestRating.getValue()!=null){
            theoryTestUI.setRating(cbTheoryTestRating.getValue());
        }else {
            theoryTestUI.setRating("");
        }
        return theoryTestUI;
    }

    private ObservableList<NDTDocumentUI> getNDTDocumentsUIFromListView(ListView<String> listView){
        ObservableList<NDTDocumentUI> ndtList = FXCollections.observableArrayList();
        if (listView == null)
            return ndtList;

        ObservableList<String> names = listView.getItems();
        for (NDTDocument ndtDocument : ndtDocumentService.getAll()){
            NDTDocumentUI ndtDocumentUI = new NDTDocumentUI(ndtDocument);
            if (names.contains(ndtDocument.getName())){
                ndtList.add(ndtDocumentUI);
            }
        }
        return ndtList;
    }
    private void updateSelectedTotalProtocolFromFields(TotalProtocolUI totalProtocolUI){
        if (totalProtocolUI==null)
            return;
        if (cbIDCommissionCert.getValue()!=null){
            totalProtocolUI.setCommissionCertification(getCommissionCertUIbyID(cbIDCommissionCert.getValue()));
        }else {
            totalProtocolUI.setCommissionCertification(null);
        }
    }

    private CommissionCertificationUI getCommissionCertUIbyID(Long id){
        if (id == null) {
            return null;
        }
        CommissionCertification commissionCertification = commissionCertificationService.get(id);
        if (commissionCertification != null){
            return new CommissionCertificationUI(commissionCertification);
        }
        return null;
    }

    @FXML
    private void saveTotalProtocol(){
        btSaveTotalProtocol.setDisable(true);
        prgsBarUpdater.setVisible(true);
        prgsBarUpdater.progressProperty().unbind();
        prgsBarUpdater.setProgress(ProgressIndicator.INDETERMINATE_PROGRESS);
        setDisabledAllTabs();
        Task<Void> saver = createTotalProtocolSaver();
        Thread t = new Thread(saver);
        t.setName("TotalProtocol SAVER Thread");
        t.start();


    }

    private Task<Void> createTotalProtocolSaver(){
        return new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                updateSelectedTotalProtocolFromFields(selectedTotalProtocolUI);
                totalProtocolServiceUI.saveTotalProtocolUIinDB(selectedTotalProtocolUI);
                Platform.runLater(new Runnable(){
                    @Override
                    public void run() {
                        //initProtocolsTreeView();
                        protocolsTreeView.getSelectionModel().select(getTreeItemByStringName(
                                protocolsTreeView.getRoot(), selectedTotalProtocolUI.toString()));

                        Printer.printTotalProtocolUI(selectedTotalProtocolUI);
                        LOGGER.debug("SAVE_TOTAL_PROTOCOL: total_protocol is updated");
                        btSaveTotalProtocol.setDisable(false);
                        prgsBarUpdater.setVisible(false);
                        tabTotalProtocol.setDisable(false);
                    }
                });
                return null;
            }
        };
    }

    private TreeItem<String> getTreeItemByStringName(TreeItem<String> rootTreeitem,String nameItem){
      for (TreeItem<String> ti: rootTreeitem.getChildren()){
          if (ti.getValue().equals(nameItem))
              return ti;
      }
        return rootTreeitem;
    }

    @FXML
    private void savePersonalProtocol(){
        btSavePersonalProtocol.setDisable(true);
        //tabPaneAllProtocols.setDisable(true);
        setDisabledAllTabs();
        prgsBarUpdater.setVisible(true);
        prgsBarUpdater.progressProperty().unbind();
        prgsBarUpdater.setProgress(ProgressIndicator.INDETERMINATE_PROGRESS);
        Task<Void> saver = createPersonalProtocolSaver();
        Thread t = new Thread(saver);
        t.setName("PersonalProtocol SAVER Thread");
        t.start();
    }

    private Task<Void> createPersonalProtocolSaver(){
        return new Task<Void>() {
            @Override
            protected Void call() throws Exception {

                updateSelectedPersonalProtocolFromFields(selectedPersonalProtocolUI);
                updateProgress(35, MAX_TASK_PROGRESS_VALUE);
                personalProtocolServiceUI.savePersonalProtocolUIinDB(selectedPersonalProtocolUI);
                updateProgress(90, MAX_TASK_PROGRESS_VALUE);
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        prgsBarUpdater.setVisible(false);
                        btSavePersonalProtocol.setDisable(false);
                        //tabPaneAllProtocols.setDisable(false);
                        tabTotalProtocol.setDisable(false);
                        tabPersonalProtocol.setDisable(false);
                        Printer.printPersonalProtocolUI(selectedPersonalProtocolUI);
                    }
                });
                return null;
            }
        };
    }

    @FXML
    private void cancelSavePersonalProtocol(){
        TreeItem<String> selectedItem = protocolsTreeView.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            protocolsTreeView.getSelectionModel().select(selectedItem.getParent());
            protocolsTreeView.requestFocus();
            protocolsTreeView.fireEvent(EventFXUtil.getMouseClickEvent());
        }
    }

    @FXML
    private void cancelSaveTotalProtocol(){
        TreeItem<String> rootItem = protocolsTreeView.getRoot();
        protocolsTreeView.getSelectionModel().select(rootItem);
        protocolsTreeView.requestFocus();
        protocolsTreeView.fireEvent(EventFXUtil.getMouseClickEvent());
    }


    @FXML
    private void saveSelectedWeldPattern(){
        if(selectedWeldPatternUI==null) {
            LOGGER.warn("SAVE SELECTED WELD PATTERN: SelectedWeldPattern is null");
            return;
        }
        setDisabledAllTabs();
        prgsBarUpdater.setVisible(true);
        Task<Void> saver = createWeldPatternSaver();
        Thread t = new Thread(saver);
        t.setName("WeldPattern SAVER Thread");
        prgsBarUpdater.progressProperty().unbind();
        prgsBarUpdater.progressProperty().bind(saver.progressProperty());
        t.start();
    }

    private Task<Void> createWeldPatternSaver(){
        return new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                updateSelectedWeldPatternFromFields(selectedWeldPatternUI);
                updateProgress(20, MAX_TASK_PROGRESS_VALUE);
                WeldPattern weldPattern = weldPatternServiceUI.getWeldPatternFromWeldPatternUI(selectedWeldPatternUI);
                weldPattern.setPersonalProtocol(personalProtocolServiceUI.getPersonalProtocolFromUIModel(selectedPersonalProtocolUI));
                updateProgress(45, MAX_TASK_PROGRESS_VALUE);
                if(selectedWeldPatternUI.getId()==0){
                    selectedWeldPatternUI.setPersonalProtocol(selectedPersonalProtocolUI);
                    Long id = weldPatternService.insert(weldPattern);
                    isWeldPatternSaved = true;
                    LOGGER.debug("SAVE SELECTED WELD PATTERN: selectedWeldPattern is inserted.");
                    selectedWeldPatternUI = new WeldPatternUI(weldPatternService.get(id));
                    selectedWeldPatternUI.setPersonalProtocol(selectedPersonalProtocolUI);

                }else{
                    weldPatternService.update(weldPattern);
                    isWeldPatternSaved = true;
                    selectedWeldPatternUI = new WeldPatternUI(weldPatternService.get(weldPattern.getWeldPatternId()));
                    selectedWeldPatternUI.setPersonalProtocol(selectedPersonalProtocolUI);
                    LOGGER.debug("SAVE SELECTED WELD PATTERN: selectedWeldPattern is updated.");

                }
                updateProgress(90, MAX_TASK_PROGRESS_VALUE);
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        protocolsTreeView.requestFocus();
                        tabPersonalProtocol.setDisable(false);
                        tabTotalProtocol.setDisable(false);
                        doSelectProtocol();
                        prgsBarUpdater.setVisible(false);
                        Printer.printWeldPatternUI(selectedWeldPatternUI);
                        //Printer.printWeldPattern(weldPattern);
                    }
                });

                return null;
            }
        };
    }
    private void refreshWeldPatternTableView(TableView<WeldPatternUI> tableView){
        if (tableView.getColumns().get(0)!= null) {
            tableView.getColumns().get(0).setVisible(false);
            tableView.getColumns().get(0).setVisible(true);
        }
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
    private void deleteWeldPattern(){
        if (selectedPersonalProtocolUI == null)
            return;
        Action response = Dialogs.create().owner(mainProtocolPane.getScene().getWindow())
                .title("Удаление записей")
                .masthead("Сделан выбор записей для удаления")
                .message("Удалить образец из данного протокола?")
                .actions(org.controlsfx.dialog.Dialog.Actions.OK, org.controlsfx.dialog.Dialog.Actions.CANCEL)
                .showConfirm();
        if (response == org.controlsfx.dialog.Dialog.Actions.OK) {
            WeldPatternUI delWeldPattern = tableViewWeldPatterns.getSelectionModel().getSelectedItem();
            if (delWeldPattern == null)
                return;
            if (delWeldPattern.getId() > 0) {
                WeldPattern wp = weldPatternService.get(delWeldPattern.getId());
                if (wp != null) {
                    weldPatternService.delete(wp);
                    tableViewWeldPatterns.getItems().remove(delWeldPattern);
                }
            }
        }
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
            if (isSelectedWeldPatternChanged(selectedWeldPatternUI)&& !isWeldPatternSaved){
                Action response = Dialogs.create().owner(mainProtocolPane.getScene().getWindow())
                        .title("Сохранение записей")
                        .masthead("Выбранный образец был изменен.")
                        .message("Сохранить изменения в образце?")
                        .actions(org.controlsfx.dialog.Dialog.Actions.OK, org.controlsfx.dialog.Dialog.Actions.CANCEL)
                        .showConfirm();
                if (response == org.controlsfx.dialog.Dialog.Actions.OK){
                    saveSelectedWeldPattern();
                    tabWeldPattern.setDisable(true);
                    LOGGER.debug("WELD_PATTERN_TAB_SELECTION_CHANGED: response OK");
                    return;
                }
            }
            tabWeldPattern.setDisable(true);
        }

    }

    @FXML
    private void personalProtocolTabSelectionChanged(){
        Tab selTab = tabPaneAllProtocols.getSelectionModel().getSelectedItem();
        if (selTab.equals(tabPersonalProtocol)) {
            LOGGER.debug("PERSONAL_PROTOCOL_TAB_SELECTION_CHANGED: selected tab is tabPersonalProtocol");
            return;
        }
        if (selTab.equals(tabTotalProtocol)) {
            LOGGER.debug("PERSONAL_PROTOCOL_TAB_SELECTION_CHANGED: selected tab is tabTotalProtocol");
            return;
        }
        if (selTab.equals(tabWeldPattern)){
            LOGGER.debug("PERSONAL_PROTOCOL_TAB_SELECTION_CHANGED: selected tab is tabWeldPattern");
            return;
        }
        LOGGER.debug("PERSONAL_PROTOCOL_TAB_SELECTION_CHANGED: no one tab is selected");

    }

    @FXML
    private void mainTabPaneOnMouseClicked(){
        Tab selTab = tabPaneAllProtocols.getSelectionModel().getSelectedItem();
        if (selTab.equals(tabPersonalProtocol)) {
            LOGGER.debug("MAIN_TAB_PANE_ON_MOUSE_CLICKED: selected tab is tabPersonalProtocol");
            return;
        }
        if (selTab.equals(tabTotalProtocol)) {
            selectParentTotalProtocol();
            tabPersonalProtocol.setDisable(true);
            LOGGER.debug("MAIN_TAB_PANE_ON_MOUSE_CLICKED: selected tab is tabTotalProtocol");
            return;
        }
        if (selTab.equals(tabWeldPattern)){
            LOGGER.debug("MAIN_TAB_PANE_ON_MOUSE_CLICKED: selected tab is tabWeldPattern");
            return;
        }
        LOGGER.debug("MAIN_TAB_PANE_ON_MOUSE_CLICKED: no one tab is selected");
    }

    @FXML
    private void totalProtocolTabSelectionChanged(){
        Tab selTab = tabPaneAllProtocols.getSelectionModel().getSelectedItem();
        if (selTab.equals(tabPersonalProtocol)) {
            LOGGER.debug("TOTAL_PROTOCOL_TAB_SELECTION_CHANGED: selected tab is tabPersonalProtocol");
            return;
        }
        if (selTab.equals(tabTotalProtocol)) {
            LOGGER.debug("TOTAL_PROTOCOL_TAB_SELECTION_CHANGED: selected tab is tabTotalProtocol");
            return;
        }
        if (selTab.equals(tabWeldPattern)){
            LOGGER.debug("TOTAL_PROTOCOL_TAB_SELECTION_CHANGED: selected tab is tabWeldPattern");
            return;
        }
        LOGGER.debug("TOTAL_PROTOCOL_TAB_SELECTION_CHANGED: no one tab is selected");
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
            getControllerManager().getJournalController().journalTableView.fireEvent(EventFXUtil.getMouseClickEvent());
            getControllerManager().getJournalController().journalTableView.requestFocus();
        }
    }
    @FXML
    private void showTotalProtocolReport(){
        getControllerManager().showReportViewPane();
        getControllerManager().getReportViewController().showTotalProtocolReport(selectedTotalProtocolUI);

    }

    @FXML
    private void showPersonalProtocolReport(){
        getControllerManager().showReportViewPane();
        getControllerManager().getReportViewController().showPersonalProtocolReport(selectedPersonalProtocolUI,selectedTotalProtocolUI);
    }

    @FXML
    private void showJournalReport(){
        getControllerManager().showReportViewPane();
        getControllerManager().getReportViewController().showJournalReport(selectedTotalProtocolUI.getJournal());

    }

    @FXML
    private void showTheoryProtocolReport(){
        getControllerManager().showReportViewPane();
        getControllerManager().getReportViewController().showTheoryProtocolReport(selectedTotalProtocolUI);
    }

    @FXML
    private void showVisualTestProtocolReport(){
        getControllerManager().showReportViewPane();
        getControllerManager().getReportViewController().showVisualTestProtocolReport(selectedTotalProtocolUI);
    }

    @FXML
    private void showRadiationTestProtocolReport(){
        getControllerManager().showReportViewPane();
        getControllerManager().getReportViewController().showRadiationTestProtocolReport(selectedTotalProtocolUI);
    }

    @FXML
    private void showMechanicalTestProtocolReport(){
        getControllerManager().showReportViewPane();
        getControllerManager().getReportViewController().showMechanicalTestProtocolReport(selectedTotalProtocolUI);
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
    private class ComboBoxWeldDetailHandler implements InvalidationListener{
        @Override
        public void invalidated(Observable observable) {
            LOGGER.debug("COMBO BOX WELD DETAIL HANDLER: invalideted");
            String detail = cbWeldPatternDetail.getSelectionModel().getSelectedItem();
            if (detail != null){
                if (!detail.contains("Труба")){
                    cbWeldPatternDiameter.setDisable(true);
                    return;
                }

            }
            cbWeldPatternDiameter.setDisable(false);
        }
    }

    private class CheckMenuItemHandler implements EventHandler<ActionEvent>{
        @Override
        public void handle(ActionEvent event) {

            if (event.getSource().getClass().equals(CheckMenuItem.class)) {
                CheckMenuItem source = (CheckMenuItem) event.getSource();
                if (menuButtonWeldPosition.getItems().contains(source))
                    fillTextFieldWeldPosition();
                if (menuButtonWeldJoinType.getItems().contains(source))
                    fillTextFieldWeldJoinType();
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
    private void doSelectProtocol(){

        TreeItem<String> protocolItem = protocolsTreeView.getSelectionModel().getSelectedItem();
        if (protocolItem==null){
            LOGGER.debug("TREE LIST VIEW HANDLER: No one tree item is selected");
            return;
        }
        if(protocolItem.getValue().equals(TREE_ROOT_ITEM_NAME)){
            selectedTotalProtocolUI = null;
            tabPaneAllProtocols.getSelectionModel().select(tabTotalProtocol);
            setDisabledAllTabs();
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

            LOGGER.debug("TREE LIST VIEW HANDLER: The personal protocol is selected: " +
                    selectedPersonalProtocolUI);
            return;
        }
        selectedTotalProtocolUI =
                totalProtocolServiceUI.getTotalProtocolUIByToStringMethod(protocolItem.getValue(), cachedTotalProtocols);
        if(selectedTotalProtocolUI != null){
            LOGGER.debug("TREE LIST VIEW HANDLER: Selected protocol is: \n"+"id = "+selectedTotalProtocolUI.getId()+
                    "; "+selectedTotalProtocolUI+"\n");
            showSelectedTotalProtocol(selectedTotalProtocolUI);
        }
    }



    private class ListViewHandler implements EventHandler<Event> {


        @Override
        public void handle(Event event) {
            if (event.getClass().equals(KeyEvent.class)){
                KeyEvent keyEvent = (KeyEvent)event;
                if(keyEvent.getCode().equals(KeyCode.DOWN) || keyEvent.getCode().equals(KeyCode.UP)||
                        ((KeyEvent) event).getCode().equals(KeyCode.PAGE_UP) ||
                        ((KeyEvent) event).getCode().equals(KeyCode.PAGE_DOWN)){
                    LOGGER.debug("TREE LIST VIEW HANDLER: Key typed on protocols list");
                    doSelectProtocol();
                    return;
                }
                return;
            }
            if (event.getClass().equals(MouseEvent.class)){
                if (((MouseEvent)event).getEventType().equals(MouseEvent.MOUSE_CLICKED)){
                    LOGGER.debug("TREE LIST VIEW HANDLER: Mouse clicked on protocols list");
                    doSelectProtocol();
                    return;
                }
            }
        }
    }

    private class SearchInvalidateHandler implements InvalidationListener{

        @Override
        public void invalidated(Observable observable) {
            if (txfSearch.getText().isEmpty()) {
                protocolsTreeView.getRoot().getChildren().clear();
                protocolsTreeView.getRoot().getChildren().addAll(totalProtocols);
                return;
            }
            ObservableList<TreeItem<String>> temporyChildrens = FXCollections.observableArrayList();

            for (TreeItem<String> item : totalProtocols){
                if (item.getValue().contains(txfSearch.getText())){
                    temporyChildrens.add(item);
                }
            }
            protocolsTreeView.getRoot().getChildren().clear();
            protocolsTreeView.getRoot().getChildren().addAll(temporyChildrens);
            protocolsTreeView.getSelectionModel().select(protocolsTreeView.getRoot());
            protocolsTreeView.fireEvent(EventFXUtil.getMouseClickEvent());
            txfSearch.requestFocus();
        }
    }
}
