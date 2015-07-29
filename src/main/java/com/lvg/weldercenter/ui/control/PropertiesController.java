package com.lvg.weldercenter.ui.control;

import com.lvg.weldercenter.models.NDTDocument;
import com.lvg.weldercenter.models.Organization;
import com.lvg.weldercenter.services.OrganizationService;
import com.lvg.weldercenter.spring.factories.ServiceFactory;
import com.lvg.weldercenter.spring.factories.ServiceUIFactory;
import com.lvg.weldercenter.ui.entities.CommissionCertificationUI;
import com.lvg.weldercenter.ui.entities.NDTDocumentUI;
import com.lvg.weldercenter.ui.entities.OrganizationUI;
import com.lvg.weldercenter.ui.entities.TeacherUI;
import com.lvg.weldercenter.ui.servicesui.OrganizationServiceUI;
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

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Victor on 28.07.2015.
 */
public class PropertiesController extends GenericController {
    private final Logger LOGGER = Logger.getLogger(PropertiesController.class);
    private final String STYLE_NOT_EDITABLE_BACKGROUND = "-fx-background-color: #deefff";

    private OrganizationService organizationService = ServiceFactory.getOrganizationService();

    private OrganizationServiceUI organizationServiceUI = ServiceUIFactory.getOrganizationServiceUI();


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
    private ListView<String> listViewWeldPatternsTypes;
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
    private TitledPane titlePaneDiameters;
    @FXML
    private ListView<String> listViewDiameters;
    @FXML
    private TextField txfDiameter;
    @FXML
    private Button btAddDiameter;
    @FXML
    private Button btEditDiameter;
    @FXML
    private Button btDeleteDiameter;

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
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LOGGER.debug("INITIALIZING Properties Pane");
        init();
    }

    private void init(){
        initOrganizationTab();
        btSave.setDisable(true);
    }

    private void initOrganizationTab(){
        initTableViewOrganizations();
        setDisabledOrganizationFields(true);
        TextFieldValidateListener validateListener = new TextFieldValidateListener();
        txfOrganizationName.textProperty().addListener(validateListener);
        txfOrganizationAdress.textProperty().addListener(validateListener);
        txfOrganizationPhone.textProperty().addListener(validateListener);
    }

    private void setDisabledOrganizationFields(boolean disabled){
        if (disabled) {
            txfOrganizationName.setEditable(false);
            txfOrganizationName.setStyle(STYLE_NOT_EDITABLE_BACKGROUND);
            txfOrganizationAdress.setEditable(false);
            txfOrganizationAdress.setStyle(STYLE_NOT_EDITABLE_BACKGROUND);
            txfOrganizationPhone.setEditable(false);
            txfOrganizationPhone.setStyle(STYLE_NOT_EDITABLE_BACKGROUND);
        }else {
            txfOrganizationName.setEditable(true);
            txfOrganizationName.setStyle("");
            txfOrganizationAdress.setEditable(true);
            txfOrganizationAdress.setStyle("");
            txfOrganizationPhone.setEditable(true);
            txfOrganizationPhone.setStyle("");
        }
    }

    private void initTableViewOrganizations(){
        initAllOrganizationsList();
        tblColumnOrgId.setCellValueFactory(new PropertyValueFactory<OrganizationUI, Long>("id"));
        tblColumnOrgName.setCellValueFactory(new PropertyValueFactory<OrganizationUI, String>("name"));
        tblColumnOrgAdress.setCellValueFactory(new PropertyValueFactory<OrganizationUI, String>("adress"));
        tblColumnOrgPhone.setCellValueFactory(new PropertyValueFactory<OrganizationUI, String>("phone"));
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
        }

    }

    private void addOrganization(){
        OrganizationUI newOrg = new OrganizationUI();
        allOrganization.add(newOrg);
        tableViewOrganizations.getSelectionModel().select(newOrg);
        tableViewOrganizations.fireEvent(EventFXUtil.getMouseClickEvent());
        editRecord();

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

    private class TableViewEventHandler implements EventHandler<javafx.event.Event>{

        @Override
        public void handle(javafx.event.Event event) {
            TableView source = (TableView) event.getSource();
            //Key events UP or DOWN
            if (event.getClass().equals(KeyEvent.class)) {
                if (((KeyEvent) event).getCode().equals(KeyCode.UP) ||
                        ((KeyEvent) event).getCode().equals(KeyCode.DOWN) ||
                        ((KeyEvent) event).getCode().equals(KeyCode.PAGE_UP) ||
                        ((KeyEvent) event).getCode().equals(KeyCode.PAGE_DOWN)) {

                    if (source.equals(tableViewOrganizations)) {
                        doSelectOrganization();
                        return;
                    }
                }
            }
            //Mouse events
            if (event.getClass().equals(MouseEvent.class)) {
                if (((MouseEvent)event).getEventType().equals(MouseEvent.MOUSE_CLICKED)) {
                    if (source.equals(tableViewOrganizations)) {
                        doSelectOrganization();
                        return;
                    }
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
            if (txfOrganizationName.isEditable())
                btSave.setDisable(false);
        }
    }
}
