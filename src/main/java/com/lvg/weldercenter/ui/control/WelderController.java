package com.lvg.weldercenter.ui.control;

import com.lvg.weldercenter.models.*;
import com.lvg.weldercenter.services.*;
import com.lvg.weldercenter.spring.factories.ServiceFactory;
import com.lvg.weldercenter.spring.factories.ServiceUIFactory;
import com.lvg.weldercenter.ui.entities.*;
import com.lvg.weldercenter.ui.servicesui.OrganizationServiceUI;
import com.lvg.weldercenter.ui.util.ControlFXUtils;
import com.lvg.weldercenter.ui.util.DateUtil;
import com.lvg.weldercenter.ui.util.TableUtil;
import com.lvg.weldercenter.ui.util.managers.TableViewManager;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import org.apache.log4j.Logger;
import org.controlsfx.control.action.Action;
import org.controlsfx.dialog.Dialog;
import org.controlsfx.dialog.Dialogs;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by Victor Levchenko LVG Corp. on 14.11.14.
 */
public class WelderController extends GenericController {
    private static final Logger LOGGER = Logger.getLogger(WelderController.class);

    private WelderService welderService = ServiceFactory.getWelderService();
    private WeldMethodService weldMethodService = ServiceFactory.getWeldMethodService();
    private EducationService educationService = ServiceFactory.getEducationService();
    private OrganizationService organizationService = ServiceFactory.getOrganizationService();
    private QualificationService qualificationService = ServiceFactory.getQualificationService();
    private JobService jobService = ServiceFactory.getJobService();
    private TableUtil<WelderUI> tableUtil = new TableViewManager();

    private OrganizationServiceUI organizationServiceUI = ServiceUIFactory.getOrganizationServiceUI();
    @FXML
    private BorderPane mainWelderPane;

    @FXML
    private TableView<WelderUI> welderTableView;

    @FXML
    private ToolBar toolBarSearch;
    @FXML
    private VBox vboxToolBars;
    @FXML
    private ToggleButton btShowSearchToolBar;
    @FXML
    private TextField txfSearch;

    @FXML
    private Button btUpdate;

    @FXML
    private TableColumn<WelderUI,Long> id;
    @FXML
    private TableColumn<WelderUI, String> name;
    @FXML
    private TableColumn<WelderUI, String> surname;
    @FXML
    private TableColumn<WelderUI, String> secname;
    @FXML
    private TableColumn<WelderUI, String> birthdayFormat;
    @FXML
    private TableColumn<WelderUI, String> job;
    @FXML
    private TableColumn<WelderUI, ObservableList<String>> weldMethods;

    // Welder Details Pane componets from fxml file

    @FXML
    private GridPane welderDetailsPane;
    @FXML
    private TextField txfSurname;
    @FXML
    private TextField txfName;
    @FXML
    private TextField txfSecname;
    @FXML
    private DatePicker dpBirthday;
    @FXML
    private TextField txfDocNumber;
    @FXML
    private DatePicker dpDateBegin;
    @FXML
    private TextField txfAddress;
    @FXML
    private ComboBox<EducationUI> cbEducation;
    @FXML
    private ComboBox<QualificationUI> cbQualification;
    @FXML
    private ComboBox<OrganizationUI> cbOrganization;
    @FXML
    private ComboBox<JobUI> cbJob;

    @FXML
    private MenuButton mbtWeldMethod;
    @FXML
    private TextField txfWeldMethod;

    // Controller VBOX components
    @FXML
    private Button btEdit;
    @FXML
    private Button btAdd;
    @FXML
    private Button btDelete;
    @FXML
    private Button btSave;



    private ObservableList<String> weldMethodsList = FXCollections.observableArrayList();
    private ObservableList<String> txfWeldMethodList = FXCollections.observableArrayList();
    private ObservableList<WelderUI> allWelders = FXCollections.observableArrayList();
    private ObservableList<EducationUI> allEducations = FXCollections.observableArrayList();
    private ObservableList<OrganizationUI> allOrganizations = FXCollections.observableArrayList();
    private ObservableList<QualificationUI> allQualifications = FXCollections.observableArrayList();
    private ObservableList<JobUI> allJobs = FXCollections.observableArrayList();

    public WelderController(){
        weldMethodsList = FXCollections.observableArrayList(getWeldMethods(weldMethodService.getAll()));
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        LOGGER.debug("INITIALIZING WelderPane");
        fillMenuButton(mbtWeldMethod, getWeldMethods(weldMethodService.getAll()));
        initTableViewWelders();
        fillComboBoxes();
        initWelderDetailsPane();
        initControlButtons();


    }

    public ObservableList<WelderUI> getAllWelders() {
        return allWelders;
    }


    private void initTableViewWelders(){
        initWeldersList();
        id.setCellValueFactory(new PropertyValueFactory<WelderUI, Long>("id"));
        name.setCellValueFactory(new PropertyValueFactory<WelderUI, String>("name"));
        surname.setCellValueFactory(new PropertyValueFactory<WelderUI, String>("surname"));
        secname.setCellValueFactory(new PropertyValueFactory<WelderUI, String>("secname"));
        birthdayFormat.setCellValueFactory(new PropertyValueFactory<WelderUI, String>("birthdayFormat"));
        job.setCellValueFactory(new PropertyValueFactory<WelderUI, String>("job"));
        weldMethods.setCellValueFactory(new PropertyValueFactory<WelderUI, ObservableList<String>>("weldMethods"));
        welderTableView.setItems(allWelders);
        welderTableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        welderTableView.addEventHandler(Event.ANY,new TableViewHandler());
        hideSearchBar();
    }

    private void initControlButtons(){
        btEdit.addEventHandler(MouseEvent.MOUSE_CLICKED, new ActiveEditWelderHandler());
        btEdit.setDisable(true);
        btSave.setDisable(true);
    }

    private void initWelderDetailsPane(){
        InvalidationListener invalidationListener = new TextFieldValidateListener();
        txfSurname.textProperty().addListener(invalidationListener);
        txfName.textProperty().addListener(invalidationListener);
        txfSecname.textProperty().addListener(invalidationListener);
        txfDocNumber.textProperty().addListener(invalidationListener);
        txfAddress.textProperty().addListener(invalidationListener);
        dpBirthday.getEditor().textProperty().addListener(invalidationListener);
        dpDateBegin.getEditor().textProperty().addListener(invalidationListener);
        ChangeListener changeListener = new OrganizationFilterListener();
        cbOrganization.getEditor().textProperty().addListener(changeListener);

        clearWelderDetailsPane();
        setDisableWelderDetailsPane(true);
    }

    private void resetWelderDetailPane(){
        WelderUI selectedWelder = welderTableView.getSelectionModel().getSelectedItem();
        btEdit.setDisable(false);
        btSave.setDisable(true);
        setDisableWelderDetailsPane(true);
        fillWelderDetailPane(selectedWelder);
    }

    private void fillWelderDetailPane(WelderUI selectedWelder) {
        txfSurname.setText(selectedWelder.getSurname());
        txfName.setText(selectedWelder.getName());
        txfSecname.setText(selectedWelder.getSecname());
        dpBirthday.setValue(DateUtil.getLocalDate(selectedWelder.getBirthday()));
        txfDocNumber.setText(selectedWelder.getDocNumber());
        dpDateBegin.setValue(DateUtil.getLocalDate(selectedWelder.getDateBegin()));
        txfAddress.setText(selectedWelder.getAddress());
        selectWeldMethodItems(selectedWelder);
        selectComboBoxItems(selectedWelder);

    }

    private void selectWeldMethodItems(WelderUI selectedWelder){
        ObservableList<MenuItem> menuItems = mbtWeldMethod.getItems();
        List<String> weldMethods = selectedWelder.getWeldMethods();
        for(MenuItem mi : menuItems){
            CheckMenuItem cmi = (CheckMenuItem)mi;
            String name = mi.getText().substring(0,3).trim();
            if(weldMethods.contains(name)){
                cmi.setSelected(true);
            }else {
                cmi.setSelected(false);
            }
        }
        updateTextFieldWeldMethods();

    }

    private void selectComboBoxItems(WelderUI selectedWelder){
        for (EducationUI educationUI: cbEducation.getItems()){
            if(educationUI.toString().equals(selectedWelder.getEducation())){
                cbEducation.getSelectionModel().select(educationUI);
                break;
            }
        }

        for (QualificationUI qualifName : cbQualification.getItems()){
            if(qualifName.toString().equals(selectedWelder.getQualification())){
                cbQualification.getSelectionModel().select(qualifName);
                break;
            }
        }

        for (OrganizationUI orgName: cbOrganization.getItems()){
            if(orgName.toString().equals(selectedWelder.getOrganization())){
                cbOrganization.getSelectionModel().select(orgName);
                break;
            }
        }

        for (JobUI jobName : cbJob.getItems()){
            if(jobName.toString().equals(selectedWelder.getJob())){
                cbJob.getSelectionModel().select(jobName);
                break;

            }
        }

    }

    private void initWelderTable(){
        id.setCellValueFactory(new PropertyValueFactory<WelderUI, Long>("id"));
        name.setCellValueFactory(new PropertyValueFactory<WelderUI, String>("name"));
        surname.setCellValueFactory(new PropertyValueFactory<WelderUI, String>("surname"));
        secname.setCellValueFactory(new PropertyValueFactory<WelderUI, String>("secname"));
        birthdayFormat.setCellValueFactory(new PropertyValueFactory<WelderUI, String>("birthdayFormat"));
        job.setCellValueFactory(new PropertyValueFactory<WelderUI, String>("job"));
        weldMethods.setCellValueFactory(new PropertyValueFactory<WelderUI, ObservableList<String>>("weldMethods"));
        setWelderTableItem(welderTableView, getAllWelders());
        welderTableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        welderTableView.addEventHandler(Event.ANY,new TableViewHandler());
        hideSearchBar();
    }

    private void initWeldersList(){
        allWelders.clear();
        for (Welder welder : welderService.getAll()){
            WelderUI welderUI = new WelderUI(welder);
            allWelders.add(welderUI);
        }
    }

    private void updateTextFieldWeldMethodList(){
        txfWeldMethodList.clear();
        for(MenuItem item : mbtWeldMethod.getItems()){
            CheckMenuItem chkItem = (CheckMenuItem)item;
            if(chkItem.isSelected()){
                txfWeldMethodList.add(chkItem.getText());
            }
        }
    }

    private void updateTextFieldWeldMethods(){
        updateTextFieldWeldMethodList();
        StringBuilder text = new StringBuilder();
        for (int i = 0; i<txfWeldMethodList.size(); i++){
            String sufix;
            if(i == (txfWeldMethodList.size()-1)){
                sufix="";
            }else {
                sufix="; ";
            }
            text.append(txfWeldMethodList.get(i)+sufix);

        }
        txfWeldMethod.setText(text.toString());
    }

    private List<WelderUI> getWeldersUIList(List<Welder> welderList){
        List<WelderUI> list = new ArrayList<WelderUI>();
        for (Welder w : welderList){
            WelderUI welder  = new WelderUI(w);
            list.add(welder);
        }
        return list;
    }

    private List<String> getWeldMethods(List<WeldMethod> weldMethodList){
        List<String> list = new ArrayList<String>();
        for(WeldMethod wm : weldMethodList){
            list.add(wm.getName()+" ("+wm.getCode()+")");
        }
        return list;
    }

    private void fillMenuButton(MenuButton mbt, List<String> list){
        mbt.getItems().clear();
        for (String title : list){
            CheckMenuItem checkMenuItem = new CheckMenuItem(title);
            mbt.getItems().add(checkMenuItem);
            checkMenuItem.addEventHandler(ActionEvent.ACTION, new TextFieldListUpdater());

        }

    }

    private void fillComboBoxes(){
        initEducationComboBox();
        initOrganizationComboBox();
        //TODO add correct listener

        initQualificationComboBox();
        initJobComboBox();
    }

    private void initEducationComboBox(){
        initAllEducationList();
        cbEducation.setItems(allEducations);
    }

    private void initAllEducationList(){
        allEducations.clear();
        for (Education edu : educationService.getAll()){
            EducationUI educationUI = new EducationUI(edu);
            allEducations.add(educationUI);
        }
    }

    private void initOrganizationComboBox(){
        initAllOrganizationList();
        cbOrganization.setItems(allOrganizations);

    }

    private void initAllOrganizationList(){
        allOrganizations.clear();
        for (Organization org: organizationService.getAll()){
            OrganizationUI orgUI = new OrganizationUI(org);
            allOrganizations.add(orgUI);
        }
    }

    private void initQualificationComboBox(){
        initAllQualificationList();
        cbQualification.setItems(allQualifications);
    }

    private void initAllQualificationList(){
        allQualifications.clear();
        for (Qualification q : qualificationService.getAll()){
            QualificationUI qUI = new QualificationUI(q);
            allQualifications.add(qUI);
        }
    }

    private void initJobComboBox(){
        initAllJobsList();
        cbJob.setItems(allJobs);

    }

    private void initAllJobsList(){
        allJobs.clear();
        for (Job j: jobService.getAll()){
            JobUI jobUI = new JobUI(j);
            allJobs.add(jobUI);
        }
    }

    private void clearWelderDetailsPane(){
        txfSurname.clear();
        txfName.clear();
        txfSecname.clear();
        txfDocNumber.clear();
        txfAddress.clear();
        dpBirthday.setValue(DateUtil.DEFAULT_DATE);
        dpDateBegin.setValue(DateUtil.DEFAULT_DATE);
        for(MenuItem mi : mbtWeldMethod.getItems()){
            CheckMenuItem checkMI = (CheckMenuItem)mi;
            checkMI.setSelected(false);
            mi.fire();
        }
        initControlButtons();



    }

    private boolean isWelderChanged(WelderUI selectedWelder){
        WelderUI newWelder = getWelderUIFromFields();
        if(!newWelder.getSurname().equals(selectedWelder.getSurname())){
            LOGGER.debug("The Surname of welder is changed");
            return true;
        }
        if (!newWelder.getName().equals(selectedWelder.getName())){
            LOGGER.debug("The Name of welder is changed");
            return true;
        }
        if (!newWelder.getSecname().equals(selectedWelder.getSecname())){
            LOGGER.debug("The Secname of welder is changed");
            return true;
        }
        if (!newWelder.getBirthday().equals(selectedWelder.getBirthday())){
            LOGGER.debug("The Birthday of welder is changed");
            return true;
        }
        if (!newWelder.getDocNumber().equals(selectedWelder.getDocNumber())){
            LOGGER.debug("The DocNumber of welder is changed");
            return true;
        }
        if (!newWelder.getDateBegin().equals(selectedWelder.getDateBegin())){
            LOGGER.debug("The DateBegin of welder is changed");
            return true;
        }
        if (!newWelder.getEducation().equals(selectedWelder.getEducation())){
            LOGGER.debug("The Education of welder is changed");
            return true;
        }
        if (!newWelder.getQualification().equals(selectedWelder.getQualification())){
            LOGGER.debug("The Qualification of welder is changed");
            return true;
        }
        if (!newWelder.getOrganization().equals(selectedWelder.getOrganization())){
            LOGGER.debug("The Organization of welder is changed");
            return true;
        }
        if (!newWelder.getJob().equals(selectedWelder.getJob())){
            LOGGER.debug("The Job of welder is changed");
            return true;
        }
        if (!newWelder.getAddress().equals(selectedWelder.getAddress())){
            LOGGER.debug("The Address of welder is changed");
            return true;
        }
        if (!newWelder.getWeldMethods().equals(selectedWelder.getWeldMethods())){
            LOGGER.debug("The Weld methods of welder is changed");
            return true;
        }
        LOGGER.debug("No one of welder's fields is changed");
        return false;

    }

    private WelderUI getWelderUIFromFields(){
        WelderUI newWelder = new WelderUI();
        newWelder.setSurname(txfSurname.getCharacters().toString());
        newWelder.setName(txfName.getText());
        newWelder.setSecname(txfSecname.getText());
        newWelder.setBirthday(DateUtil.getDate(dpBirthday.getValue()));
        newWelder.setBirthdayFormat(DateUtil.getDate(dpBirthday.getValue()));
        newWelder.setDocNumber(txfDocNumber.getText());
        newWelder.setDateBegin(DateUtil.getDate(dpDateBegin.getValue()));
        newWelder.setAddress(txfAddress.getText());
        fillWelderUIDataFromComboBoxes(newWelder);
        List<String> weldMethods = new ArrayList<String>();
        for (MenuItem mi  : mbtWeldMethod.getItems()){
            CheckMenuItem chkMi = (CheckMenuItem)mi;
            if (chkMi.isSelected()){
                weldMethods.add(chkMi.getText().substring(0,3).trim());
            }
        }
        newWelder.setWeldMethods(FXCollections.observableArrayList(weldMethods));
        LOGGER.debug("Welder from text fields: "+newWelder);
        return newWelder;
    }

    private void fillWelderUIDataFromComboBoxes(WelderUI welderUI){
        if(null == cbEducation.getValue()){
            welderUI.setEducation("");
        }else
            welderUI.setEducation(cbEducation.getValue().toString());
        if(null == cbQualification.getValue()){
            welderUI.setQualification("");
        }else
            welderUI.setQualification(cbQualification.getValue().toString());
        if(null == cbJob.getValue()){
            welderUI.setJob("");
        }else
            welderUI.setJob(cbJob.getValue().toString());
        if (null == cbOrganization.getValue()){
            welderUI.setOrganization("");
        }else {
            saveNewOrganization();
            welderUI.setOrganization(cbOrganization.getValue()+"");
        }
    }

    private void hideSearchBar(){
        if (toolBarSearch.isVisible()){
            toolBarSearch.setVisible(false);
            btShowSearchToolBar.setSelected(false);
            vboxToolBars.setMargin(toolBarSearch, new Insets(0, 0, -40, 0));
            txfSearch.clear();
        }
    }
    //Action handlers
    @FXML
    private void onChangeWelderComponents(ActionEvent event){
        readyToSave();
        LOGGER.debug("The value of Welder Components was changed.");
    }

    @FXML
    private void saveWelder(){
        WelderUI updatedWelderUI = welderTableView.getSelectionModel().getSelectedItem();
        Welder welder = welderService.get(updatedWelderUI.getId());
        if(welder==null){
            addWelderInDB();
            refreshWelderTable();
            requestFocusAtTable();
            return;
        }
        updateWelderFromFields(welder);
        welderService.update(welder);
        if (getAllWelders().equals(welderTableView.getItems())) {
            welderTableView.getItems().set(welderTableView.getItems().indexOf(updatedWelderUI),
                    new WelderUI(welder));
            lightRefreshTable();
        }
        else {
            welderTableView.getItems().set(welderTableView.getItems().indexOf(updatedWelderUI),
                    new WelderUI(welder));
            getAllWelders().remove(welderTableView.getItems().indexOf(updatedWelderUI));
        }
        requestFocusAtTable();
        LOGGER.debug("After save selected welder is: "+welderTableView.getSelectionModel().getSelectedItem());

    }

    private void addWelderInDB(){
        Welder welder = getNewWelderFromFields();
        welderService.insert(welder);
    }

    private void setWelderTableItem(TableView<WelderUI> welderTableView,ObservableList<WelderUI> items){
        if(welderTableView.getColumns().size()==0){
            welderTableView.setItems(items);
            return;
        }
        welderTableView.getColumns().get(0).setVisible(false);
        welderTableView.setItems(items);
        welderTableView.getColumns().get(0).setVisible(true);

    }

    private void setDisableWelderDetailsPane(boolean disabled){
        ControlFXUtils.setDisabledTextFields(disabled,txfSurname, txfName,
                txfSecname, txfDocNumber, txfAddress, txfWeldMethod);
        ControlFXUtils.setDisabledComboBoxes(disabled, cbEducation, cbQualification, cbOrganization, cbJob);
        ControlFXUtils.setDisabledDatePickers(disabled, dpBirthday, dpDateBegin);
        mbtWeldMethod.setDisable(disabled);
    }

    @FXML
    private void addNewWelder(){
        clearWelderDetailsPane();
        WelderUI newWelder = new WelderUI();
        welderTableView.getItems().add(newWelder);
        welderTableView.getSelectionModel().clearSelection();
        welderTableView.getSelectionModel().select(newWelder);
        setDisableWelderDetailsPane(false);
        //welderDetailsPane.setDisable(false);
        btSave.setDisable(false);
    }

    @FXML
    private void initSearch(){
        txfSearch.textProperty().addListener(new SearchValidateListener());
        if (toolBarSearch.isVisible()) {
            toolBarSearch.setVisible(false);
            vboxToolBars.setMargin(toolBarSearch, new Insets(0, 0, -40, 0));
            btShowSearchToolBar.setSelected(false);
        }
        else {
            toolBarSearch.setVisible(true);
            vboxToolBars.setMargin(toolBarSearch, new Insets(0, 0, 0, 0));
            btShowSearchToolBar.setSelected(true);
            txfSearch.requestFocus();
        }

    }

    @FXML
    private void selectFirst(){
        tableUtil.selectFirstItem(welderTableView);
    }

    @FXML
    private void selectLast(){
        tableUtil.selectLastItem(welderTableView);
    }

    @FXML
    private void selectPrev(){
       tableUtil.selectPrevItem(welderTableView);
    }

    @FXML
    private void selectNext(){
        tableUtil.selectNextItem(welderTableView);
    }
    private void lightRefreshTable(){
        fillComboBoxes();
        initWelderTable();
        initWelderDetailsPane();
        initControlButtons();
    }

    //TODO correct update method must be update weld methods

    @FXML
    private void refreshWelderTable(){
        initWeldersList();
        fillComboBoxes();
        fillMenuButton(mbtWeldMethod, getWeldMethods(weldMethodService.getAll()));
        initWelderTable();
        initWelderDetailsPane();
        initControlButtons();
    }

    @FXML
    private void submitData(ActionEvent event){
        if(event.getSource().equals(txfSurname)){
            LOGGER.debug("ActionEvent has been generated in surname field");
            txfName.requestFocus();
        }
        if(event.getSource().equals(txfName)){
            LOGGER.debug("ActionEvent has been generated in name field");
            txfSecname.requestFocus();
        }
        if(event.getSource().equals(txfSecname)){
            LOGGER.debug("ActionEvent has been generated in secname field");
            dpBirthday.getEditor().requestFocus();

        }
        if(event.getSource().equals(txfDocNumber)){
            LOGGER.debug("ActionEvent has been generated in docnumber field");
            dpDateBegin.getEditor().requestFocus();
        }
        if(event.getSource().equals(txfAddress)){
            LOGGER.debug("ActionEvent has been generated in address field");
            saveWelder();
        }
    }

    @FXML
    private void copyWelder(){
        WelderUI sourceWelder = welderTableView.getSelectionModel().getSelectedItem();
        if(null==sourceWelder)
            return;
        addNewWelder();
        fillWelderDetailPane(sourceWelder);
    }

    @FXML
    private void deleteWelders(){
        LOGGER.debug("DELETE button is pressed");
        ObservableList<WelderUI> delWelders = welderTableView.getSelectionModel().getSelectedItems();
        ObservableList<WelderUI> allWelders = welderTableView.getItems();
        if(!delWelders.isEmpty()){
            LOGGER.debug("start DELETE dialog...");
            Action response = Dialogs.create().owner(mainWelderPane.getScene().getWindow())
                    .title("Удаление записей")
                    .masthead("Сделан выбор записей для удаления")
                    .message("Удалить выбранные записи? ("+delWelders.size()+"шт.)")
                    .actions(Dialog.Actions.OK, Dialog.Actions.CANCEL)
                    .showConfirm();
            if(response == Dialog.Actions.OK){
                for(WelderUI welder : delWelders){
                    Welder welderDB = welderService.get(welder.getId());
                    if(null!=welderDB)
                        welderService.delete(welderDB);
                    else
                        LOGGER.warn("Not possible to delete welder: "+welder);
                    //allWelders.remove(welder);
                }
                welderTableView.getSelectionModel().clearSelection();
                welderTableView.getSelectionModel().selectFirst();
                clearWelderDetailsPane();
                refreshWelderTable();

                LOGGER.debug("Selected welders has been deleted");
            }

        }
    }

    private void updateWelderUIFromDB(WelderUI updatedWelder, Welder dbWelder){
        updatedWelder.setId(dbWelder.getWelderId());
        updatedWelder.setSurname(dbWelder.getSurname());
        updatedWelder.setName(dbWelder.getName());
        updatedWelder.setSecname(dbWelder.getSecname());
        updatedWelder.setBirthday(dbWelder.getBirthday());
        updatedWelder.setBirthdayFormat(dbWelder.getBirthday());
        updatedWelder.setDocNumber(dbWelder.getDocNumber());
        updatedWelder.setDateBegin(dbWelder.getDateBegin());
        updatedWelder.setAddress(dbWelder.getAddress());
        if(null!=dbWelder.getEducation())
            updatedWelder.setEducation(dbWelder.getEducation().getType());
        else
            updatedWelder.setEducation("");

        if(null!=dbWelder.getQualification())
            updatedWelder.setQualification(dbWelder.getQualification().getType());
        else
            updatedWelder.setQualification("");
        if (null!=dbWelder.getOrganization())
            updatedWelder.setOrganization(dbWelder.getOrganization().getName());
        else
            updatedWelder.setOrganization("");

        if (null!=dbWelder.getJob())
            updatedWelder.setJob(dbWelder.getJob().getName());
        else
            updatedWelder.setJob("");

        ObservableList<String> weldMethodList = updatedWelder.getWeldMethods();
        weldMethodList.clear();
        for (WeldMethod weldMethod : dbWelder.getWeldMethods()){
            weldMethodList.add(weldMethod.getName());
        }
        //updatedWelder.setWeldMethods(weldMethodList);
    }

    @FXML
    private void closeWelderPane(){
        if(mainWelderPane.isVisible()){
            mainWelderPane.setVisible(false);
            controllerManager.getMainFrameController().showLogo();
        }
    }

    @FXML
    private void saveNewOrganization(){
        String orgName = cbOrganization.getEditor().getText();
        if (orgName.isEmpty())
            return;
        for (OrganizationUI org: allOrganizations){
            if (org.toString().toLowerCase().equals(orgName.toLowerCase()))
                return;
        }
        Action saveResponse = ControlFXUtils.getResponseSaveRecordDialog("Сохранить новую организацию?",
                mainWelderPane.getScene().getWindow());
        if (saveResponse == Dialog.Actions.OK){
            OrganizationUI newOrg = new OrganizationUI();
            newOrg.setName(orgName);
            organizationServiceUI.saveOrganizationUIinDB(newOrg);
            initOrganizationComboBox();
            for (OrganizationUI org : allOrganizations){
                if (org.getName().equals(orgName)){
                    cbOrganization.getSelectionModel().select(org);
                }
            }
        }else {
            initOrganizationComboBox();
        }


    }

    private void updateWelderFromFields(Welder welder){
        WelderUI updatedWelder = getWelderUIFromFields();
        updatedWelder.setId(welder.getWelderId());

        welder.setSurname(updatedWelder.getSurname());
        welder.setName(updatedWelder.getName());
        welder.setSecname(updatedWelder.getSecname());
        welder.setBirthday(updatedWelder.getBirthday());
        welder.setDocNumber(updatedWelder.getDocNumber());
        welder.setDateBegin(updatedWelder.getDateBegin());
        welder.setAddress(updatedWelder.getAddress());
        welder.setEducation(educationService.getByType(updatedWelder.getEducation()));
        welder.setQualification(qualificationService.getByType(updatedWelder.getQualification()));
        welder.setOrganization(organizationService.getByName(updatedWelder.getOrganization()));
        welder.setJob(jobService.getByName(updatedWelder.getJob()));
        List<WeldMethod> weldMethodList = new ArrayList<WeldMethod>();
        for (String weldMethodName : updatedWelder.getWeldMethods()){
            weldMethodList.add(weldMethodService.getByName(weldMethodName.trim()));
        }
        welder.setWeldMethods(weldMethodList);
    }

    private  Welder getNewWelderFromFields() {
        Welder welder = new Welder();
        WelderUI updatedWelder = getWelderUIFromFields();

        welder.setSurname(updatedWelder.getSurname());
        welder.setName(updatedWelder.getName());
        welder.setSecname(updatedWelder.getSecname());
        welder.setBirthday(updatedWelder.getBirthday());
        welder.setDocNumber(updatedWelder.getDocNumber());
        welder.setDateBegin(updatedWelder.getDateBegin());
        welder.setAddress(updatedWelder.getAddress());
        LOGGER.debug("Updated welder education is: " + updatedWelder.getEducation());

        welder.setEducation(educationService.getByType(updatedWelder.getEducation()));
        welder.setQualification(qualificationService.getByType(updatedWelder.getQualification()));
        welder.setOrganization(organizationService.getByName(updatedWelder.getOrganization()));
        welder.setJob(jobService.getByName(updatedWelder.getJob()));

        List<WeldMethod> weldMethodList = new ArrayList<WeldMethod>();
        for (String weldMethodName : updatedWelder.getWeldMethods()){
            weldMethodList.add(weldMethodService.getByName(weldMethodName.trim()));
        }
        welder.setWeldMethods(weldMethodList);
        return welder;
    }

    private void requestFocusAtTable(){
        welderTableView.requestFocus();
        Event.fireEvent(welderTableView, new MouseEvent(MouseEvent.MOUSE_CLICKED,
                0,0, 0, 0, MouseButton.PRIMARY, 1,
                true, true, true, true,true, true, true, true, true, true, null));

    }

    private void showWarningDialog(String warnMessage){
        Dialogs.create()
                .owner(mainWelderPane.getScene().getWindow())
                .title("Ошибка ввода данных")
                .masthead("Произошла ошибка при заполнении полей")
                .message(warnMessage)
                .showError();
    }

    private void readyToSave(){
        if (btSave.isDisabled() && !welderDetailsPane.isDisabled()) {
            btSave.setDisable(false);
        }
    }

    private class TableViewHandler implements EventHandler<Event>{
        @Override
        public void handle(Event event) {
            if(ControlFXUtils.isEventIsSelectedKeyOnList(event) || ControlFXUtils.isEventIsSelectedMouse(event)){
                doSelectWelder();
                return;
            }

        }

        private void doSelectWelder(){
            WelderUI selectedWelder = welderTableView.getSelectionModel().getSelectedItem();
            if (selectedWelder == null) {
                LOGGER.debug("there is no any welder is selected");
                return;
            }
            LOGGER.debug("selected welder is" + selectedWelder);
            btEdit.setDisable(false);
            btSave.setDisable(true);
           setDisableWelderDetailsPane(true);
           // welderDetailsPane.setDisable(true);
            fillWelderDetailPane(selectedWelder);

        }
    }

    private class TextFieldListUpdater implements EventHandler<ActionEvent>{
        @Override
        public void handle(ActionEvent event) {
            LOGGER.debug("CheckMenuItem is clicked");
            LOGGER.debug("WeldMethodList size is: "+txfWeldMethodList.size());
            System.out.println();
            System.out.println();
            updateTextFieldWeldMethods();
            readyToSave();
        }

        @Override
        public String toString() {
            return super.toString();
        }
    }

    private class ActiveEditWelderHandler implements EventHandler<MouseEvent>{

        @Override
        public void handle(MouseEvent event) {
            LOGGER.debug("Active Edit Welder is clicked");
            if (welderTableView.getSelectionModel().getSelectedItem() == null)
                return;
            setDisableWelderDetailsPane(false);
            txfSurname.requestFocus();
        }
    }

    private class ChangeWelderHandler implements EventHandler<KeyEvent>{

        @Override
        public void handle(KeyEvent event) {
            LOGGER.debug("KEY EVENT: Text in text field is changed ");
            WelderUI selectedWelder = welderTableView.getSelectionModel().getSelectedItem();
            LOGGER.debug("Editing welder change status is: "
                    +isWelderChanged(selectedWelder));
            if (isWelderChanged(selectedWelder)){
                readyToSave();
            }
        }
    }

    private class TextFieldValidateListener implements InvalidationListener{
        @Override
        public void invalidated(Observable observable) {
            if (welderDetailsPane.isDisabled())
                return;
            LOGGER.debug("VALIDATE LISTENER: Text in text field is changed ");
            readyToSave();
        }
    }

    private class SearchValidateListener implements InvalidationListener{
        //ObservableList<WelderUI> data = getWelders();
        @Override
        public void invalidated(Observable observable) {
            if (txfSearch.textProperty().get().isEmpty()){
                refreshWelderTable();
                return;
            }

            ObservableList<WelderUI> filteredList = FXCollections.observableArrayList();
            ObservableList<TableColumn<WelderUI,?>> columns = welderTableView.getColumns();

            for (int i = 0; i< getAllWelders().size(); i++){

                for (int j = 0; j<columns.size(); j++){
                    TableColumn<WelderUI,?> column = columns.get(j);
                    String cellValue = column.getCellData(getAllWelders().get(i)).toString();
                    cellValue = cellValue.toLowerCase();
                    if (cellValue.contains(txfSearch.textProperty().get().toLowerCase())){
                        filteredList.add(getAllWelders().get(i));
                        break;
                    }
                }
            }
            welderTableView.setItems(filteredList);
            if (!filteredList.isEmpty()){
                welderTableView.getSelectionModel().selectFirst();
            }
        }


    }

    private class OrganizationFilterListener implements javafx.beans.value.ChangeListener<String>{


        @Override
        public void changed(ObservableValue observable, String oldValue, String newValue) {

            if (!cbOrganization.isFocused()){
                return;
            }
            if (cbOrganization.getEditor().getText().isEmpty()){
                initOrganizationComboBox();
                return;
            }

            ObservableList<OrganizationUI> filteredList = FXCollections.observableArrayList();

            for (OrganizationUI org : allOrganizations){
                if (org.toString().equals(newValue)) {
                    cbOrganization.getSelectionModel().select(org);
                    return;
                }
                if (org.toString().toLowerCase().contains(newValue.toLowerCase())){
                    filteredList.add(org);
                }            }
            cbOrganization.setItems(filteredList);
            cbOrganization.show();

        }

    }


}
