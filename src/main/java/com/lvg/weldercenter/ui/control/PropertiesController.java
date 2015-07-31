package com.lvg.weldercenter.ui.control;

import com.lvg.weldercenter.models.Organization;
import com.lvg.weldercenter.models.PatternDiameter;
import com.lvg.weldercenter.models.WeldDetail;
import com.lvg.weldercenter.services.OrganizationService;
import com.lvg.weldercenter.services.PatternDiameterService;
import com.lvg.weldercenter.services.WeldDetailService;
import com.lvg.weldercenter.spring.factories.ServiceFactory;
import com.lvg.weldercenter.spring.factories.ServiceUIFactory;
import com.lvg.weldercenter.ui.entities.*;
import com.lvg.weldercenter.ui.servicesui.OrganizationServiceUI;
import com.lvg.weldercenter.ui.servicesui.PatternDiameterServiceUI;
import com.lvg.weldercenter.ui.servicesui.WeldDetailServiceUI;
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
    private final Logger LOGGER = Logger.getLogger(PropertiesController.class);
    private final String STYLE_NOT_EDITABLE_BACKGROUND = "-fx-background-color: #deefff";

    private OrganizationService organizationService = ServiceFactory.getOrganizationService();
    private WeldDetailService weldDetailService = ServiceFactory.getWeldDetailService();
    private PatternDiameterService patternDiameterService = ServiceFactory.getPatternDiameterService();

    private OrganizationServiceUI organizationServiceUI = ServiceUIFactory.getOrganizationServiceUI();
    private WeldDetailServiceUI weldDetailServiceUI = ServiceUIFactory.getWeldDetailServiceUI();
    private PatternDiameterServiceUI patternDiameterServiceUI = ServiceUIFactory.getPatternDiameterServiceUI();

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
    private TitledPane titlePaneWeldPatternsTypes;
    @FXML
    private ListView<WeldDetailUI> listViewWeldPatternsTypes;
    @FXML
    private TextField txfWeldPatternTypeCode;
    @FXML
    private TextField txfWeldPatternTypeName;
    @FXML
    private Button btAddWeldPatternType;
    @FXML
    private Button btEditWeldPatternType;
    @FXML
    private Button btDeleteWeldPatternType;
    @FXML
    private Button btSaveWeldPatternType;

    @FXML
    private TitledPane titlePaneDiameters;
    @FXML
    private ListView<PatternDiameterUI> listViewDiameters;
    @FXML
    private TextField txfDiameter;
    @FXML
    private Button btAddDiameter;
    @FXML
    private Button btEditDiameter;
    @FXML
    private Button btDeleteDiameter;
    @FXML
    private Button btSaveDiameter;

    @FXML
    private TitledPane titlePaneThickness;
    @FXML
    private ListView<String> listViewThickness;
    @FXML
    private TextField txfThickness;

    @FXML
    private TitledPane titlePaneSteelTypes;
    @FXML
    private ListView<String> listViewSteelTypes;
    @FXML
    private TextField txfSteelType;
    @FXML
    private ComboBox<String> cbSteelTypeGroup;

    @FXML
    private TitledPane titlePaneSteelGroups;
    @FXML
    private ListView<String> listViewSteelGroups;
    @FXML
    private TextField txfSteelGroup;
    @FXML
    private TextArea txtAreaSteelGroupDecription;

    //Weld tab
    @FXML
    private Tab tabWeld;

    @FXML
    private TitledPane titlePaneWeldMethod;
    @FXML
    private ListView<String> listViewWeldMethod;
    @FXML
    private TextField txfWeldMethodCode;
    @FXML
    private TextField txfWeldMethodName;

    @FXML
    private TitledPane titlePaneElectrode;
    @FXML
    private ListView<String> listViewElectrode;
    @FXML
    private TextField txfElectrodeType;

    @FXML
    private TitledPane titlePaneWeldWire;
    @FXML
    private ListView<String> listViewWeldWire;
    @FXML
    private TextField txfWeldWireType;

    @FXML
    private TitledPane titlePaneWeldGas;
    @FXML
    private ListView<String> listViewWeldGas;
    @FXML
    private TextField txfWeldGasType;

    @FXML
    private TitledPane titlePaneWeldPosition;
    @FXML
    private ListView<String> listViewWeldPosition;
    @FXML
    private TextField txfWeldPositionCode;
    @FXML
    private TextArea txtAreaWeldPositionDescription;

    //Education and etc tab
    @FXML
    private Tab tabEducationEtc;

    @FXML
    private TitledPane titlePaneEducation;
    @FXML
    private ListView<String> listViewEducation;
    @FXML
    private TextField txfEducationType;

    @FXML
    private TitledPane titlePaneQualification;
    @FXML
    private ListView<String> listViewQualification;
    @FXML
    private TextField txfQualificationType;

    @FXML
    private TitledPane titlePaneJob;
    @FXML
    private ListView<String> listViewJob;
    @FXML
    private TextField txfJobType;

    //Commission Certification tab
    @FXML
    private Tab tabCommission;
    @FXML
    private TableView<CommissionCertificationUI> tableViewCommissions;
    @FXML
    private TableColumn<CommissionCertificationUI, Long> tblColumnCommissionId;
    @FXML
    private TableColumn<CommissionCertificationUI, String> tblColumnHead;
    @FXML
    private TableColumn<CommissionCertificationUI, String> tblColumnWeldSpec;
    @FXML
    private TableColumn<CommissionCertificationUI, String> tblColumnNDTSpec;
    @FXML
    private TableColumn<CommissionCertificationUI, String> tblColumnSafetySpec;
    @FXML
    private ComboBox<String> cbCommissionHead;
    @FXML
    private ComboBox<String> cbCommissionWeldSpec;
    @FXML
    private ComboBox<String> cbCommissionNDTSpec;
    @FXML
    private ComboBox<String> cbCommissionSafetySpec;

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LOGGER.debug("INITIALIZING Properties Pane");
        init();
    }

    private void init(){
        initOrganizationTab();
        initWeldPatternTab();
        btSave.setDisable(true);
    }

    private void initWeldPatternTab(){
        initTitlePaneWeldPatternTypes();
        initTitlePaneDiameters();
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
        setDisabledTextFields(true,txfDiameter);
        btSaveDiameter.setDisable(true);
        PatternDiameterUI selectedDiameterUI = listViewDiameters.getSelectionModel().getSelectedItem();
        if (selectedDiameterUI == null)
            return;
        updateDiameterFromFialds(selectedDiameterUI);
        PatternDiameter patternDiameter = patternDiameterServiceUI.getPatternDiameterFromUIModel(selectedDiameterUI);
        if (patternDiameter.getPatternDiameterId()==null || patternDiameter.getPatternDiameterId()==0){
            patternDiameterService.insert(patternDiameter);
            LOGGER.debug("SAVE PATTERN DIAMETER: pattern diameter has been inserted in DB");
        }else {
            patternDiameterService.update(patternDiameter);
            LOGGER.debug("SAVE PATTERN DIAMETER: pattern diameter has been updated in DB");
        }
        allDiameters.remove(selectedDiameterUI);
        allDiameters.add(selectedDiameterUI);
        listViewDiameters.getSelectionModel().select(selectedDiameterUI);

    }

    private void updateDiameterFromFialds(PatternDiameterUI diameterUI){
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
        }
    }

    @FXML
    private void saveRecord(){
        Tab activeTab = tabPaneAllProperties.getSelectionModel().getSelectedItem();
        if (activeTab == null)
            return;
        if (activeTab.equals(tabOrganizations)){
            saveOrganization();
        }
    }

    @FXML
    private void addRecord(){
        Tab activeTab = tabPaneAllProperties.getSelectionModel().getSelectedItem();
        if (activeTab == null)
            return;
        if (activeTab.equals(tabOrganizations)){
            addOrganization();
        }
    }

    @FXML
    private void deleteRecord(){
        Tab activeTab = tabPaneAllProperties.getSelectionModel().getSelectedItem();
        if (activeTab == null)
            return;
        if (activeTab.equals(tabOrganizations)){
            deleteOrganization();
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
    }

    @FXML
    private void refreshTableView(){
        Tab activeTab = tabPaneAllProperties.getSelectionModel().getSelectedItem();
        if (activeTab == null)
            return;
        if (activeTab.equals(tabOrganizations)){
            initAllOrganizationsList();
            selectFirstRecord();
        }
    }

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

    //Utilities -------------------------------------------------------------------

    private Action getResponseDeleteDialog(int countOfDeletingRecords){
        Action response = Dialogs.create().owner(mainPropertiesPane.getScene().getWindow())
                .title("Удаление записей")
                .masthead("Сделан выбор записей для удаления")
                .message("Удалить выбранные записи? ("+countOfDeletingRecords+"шт.)")
                .actions(org.controlsfx.dialog.Dialog.Actions.OK, org.controlsfx.dialog.Dialog.Actions.CANCEL)
                .showConfirm();
        return response;
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

            }

        }
        private void doSelectDiameter(){
            PatternDiameterUI selectedDiameter = listViewDiameters.getSelectionModel().getSelectedItem();
            if (selectedDiameter == null)
                return;
            setDisabledTextFields(true);
            txfDiameter.setText(selectedDiameter.getDiameter()+"");
        }

        private void doSelectWeldPatternType(){
            WeldDetailUI selectedWeldDetail = listViewWeldPatternsTypes.getSelectionModel().getSelectedItem();
            if (selectedWeldDetail==null)
                return;
            setDisabledWeldPatternTypesFields(true);
            txfWeldPatternTypeName.setText(selectedWeldDetail.getType());
            txfWeldPatternTypeCode.setText(selectedWeldDetail.getCode());
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
            if (selectedTab.equals(tabWeldPatterns)) {
                if (txfWeldPatternTypeCode.isEditable()) {
                    btSaveWeldPatternType.setDisable(false);
                    return;
                }

                if (txfDiameter.isEditable()){
                    btSaveDiameter.setDisable(false);
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
