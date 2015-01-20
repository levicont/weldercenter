package com.lvg.weldercenter.ui.control;

import com.lvg.weldercenter.models.Curriculum;
import com.lvg.weldercenter.models.Journal;
import com.lvg.weldercenter.models.Teacher;
import com.lvg.weldercenter.models.Welder;
import com.lvg.weldercenter.services.CurriculumService;
import com.lvg.weldercenter.services.JournalService;
import com.lvg.weldercenter.services.TeacherService;
import com.lvg.weldercenter.services.WelderService;
import com.lvg.weldercenter.spring.factories.ServiceFactory;
import com.lvg.weldercenter.ui.entities.JournalUI;
import com.lvg.weldercenter.ui.entities.TeacherUI;
import com.lvg.weldercenter.ui.entities.TopicUI;
import com.lvg.weldercenter.ui.entities.WelderUI;
import com.lvg.weldercenter.ui.util.DateUtil;
import com.lvg.weldercenter.ui.util.TableUtil;
import com.lvg.weldercenter.ui.util.TimeTableUtil;
import com.lvg.weldercenter.ui.util.managers.TableViewManager;
import com.lvg.weldercenter.ui.util.managers.TimeTableUtilManager;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
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
 * Created by Victor Levchenko LVG Corp. on 12.12.14.
 */
public class JournalController extends GenericController{
    private static final Logger LOGGER = Logger.getLogger(JournalController.class);

    private ControllerManager controllerManager;
    private JournalService journalService = ServiceFactory.getJournalService();
    private CurriculumService curriculumService = ServiceFactory.getCurriculumService();
    private TeacherService teacherService = ServiceFactory.getTeacherService();
    private WelderService welderService = ServiceFactory.getWelderService();
    private TimeTableUtil timeTableUtil = new TimeTableUtilManager();
    private TableUtil<JournalUI> tableUtil = new TableViewManager();

    @FXML
    BorderPane mainJournalPane;

    @FXML
    TableView<JournalUI> journalTableView;
    @FXML
    TableColumn<JournalUI, Long> id;
    @FXML
    TableColumn<JournalUI, String> number;
    @FXML
    TableColumn<JournalUI, String> dateBeginFormat;
    @FXML
    TableColumn<JournalUI, String> dateEndFormat;
    @FXML
    TableColumn<JournalUI, String> weldersCount;
    @FXML
    TableColumn<JournalUI, String> curriculum;

    //Search bar
    @FXML
    ToolBar toolBarSearch;
    @FXML
    ToggleButton btShowSearchToolBar;
    @FXML
    VBox vboxToolBars;
    @FXML
    TextField txfSearch;

    //Journal Detail Pane
    @FXML
    GridPane journalDetailsPane;
    @FXML
    TextField tfxJournalNumber;
    @FXML
    DatePicker dpDateBegin;
    @FXML
    DatePicker dpDateEnd;
    @FXML
    ComboBox<String> cbCurriculum;

    //Teachers
    @FXML
    ListView<String> teachersListView;
    @FXML
    MenuButton teachersMenuButton;

    //Welder TableView
    @FXML
    TableView<WelderUI> weldersTableView;
    @FXML
    TableColumn<WelderUI, Long> weldersTableID;
    @FXML
    TableColumn<WelderUI, String> weldersTableSurname;
    @FXML
    TableColumn<WelderUI, String> weldersTableName;
    @FXML
    TableColumn<WelderUI, String> weldersTableSecname;
    @FXML
    TableColumn<WelderUI, ObservableList<String>> weldersTableWeldMethods;
    @FXML
    Button btShowWelderSelectorPane;

    //Welder selector pane VBOX
    @FXML
    VBox selectWelderPane;
    @FXML
    TextField txfSearchWelder;
    @FXML
    Button btAddWelderToJournal;
    @FXML
    ListView<String> weldersListView;

    //Topic TableView
    @FXML
    TableView<TopicUI> topicTableView;
    @FXML
    TableColumn<TopicUI, String> timeTableDateFormat;
    @FXML
    TableColumn<TopicUI, Double> timeTableHours;
    @FXML
    TableColumn<TopicUI, String> timeTableTopicTitle;

    //Control buttons
    @FXML
    Button btEdit;
    @FXML
    Button btSave;
    @FXML
    Button btAdd;
    @FXML
    Button btDelete;


    private ObservableList<JournalUI> journals = FXCollections.observableArrayList(new ArrayList<JournalUI>());
    private ObservableList<WelderUI> welders = FXCollections.observableArrayList(new ArrayList<WelderUI>());
    private ObservableList<TopicUI> topics = FXCollections.observableArrayList(new ArrayList<TopicUI>());
    private ObservableList<TeacherUI> teachers = FXCollections.observableArrayList(new ArrayList<TeacherUI>());
    private ObservableList<String> teachersNames = FXCollections.observableArrayList(new ArrayList<String>());
    private ObservableList<CheckMenuItem> teachersCheckItems = FXCollections.observableArrayList(new ArrayList<CheckMenuItem>());
    private ObservableList<String> curriculums = FXCollections.observableArrayList(new ArrayList<String>());

    public JournalController(){
        getTopics().addListener(new TopicsListChangeListener());
    }

    public ControllerManager getControllerManager() {
        return controllerManager;
    }

    public void setControllerManager(ControllerManager controllerManager) {
        this.controllerManager = controllerManager;
    }

    public ObservableList<TopicUI> getTopics() {
        return topics;
    }

    public void setTopics(ObservableList<TopicUI> topics) {
        this.topics = topics;
    }

    public ObservableList<CheckMenuItem> getTeachersCheckItems() {
        return teachersCheckItems;
    }

    public void setTeachersCheckItems(ObservableList<CheckMenuItem> teachersCheckItems) {
        this.teachersCheckItems = teachersCheckItems;
    }

    public ObservableList<WelderUI> getWelders() {
        return welders;
    }

    public void setWelders(ObservableList<WelderUI> welders) {
        this.welders = welders;
    }

    public ObservableList<JournalUI> getJournals() {
        return journals;
    }

    public void setJournals(ObservableList<JournalUI> journals) {
        this.journals = journals;
    }

    public ObservableList<TeacherUI> getTeachers() {
        return teachers;
    }

    public void setTeachers(ObservableList<TeacherUI> teachers) {
        this.teachers = teachers;
    }

    public ObservableList<String> getTeachersNames() {
        return teachersNames;
    }

    public void setTeachersNames(ObservableList<String> teachersNames) {
        this.teachersNames = teachersNames;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LOGGER.debug("INITIALIZING JournalPane");
        initJournals();
        initJournalTable();
        fillComboBoxes();
        initJournalDetailsPane();
        initControlButtons();
    }

    private void initJournals(){
        List<Journal> journalList = journalService.getAll();
        LOGGER.debug("\nINIT JOURNALS: journalList: "+journalList+"\n");
        setJournals(FXCollections.observableArrayList(getJournalUIList(journalService.getAll())));
    }
    private void initJournalTable(){
        id.setCellValueFactory(new PropertyValueFactory<JournalUI, Long>("id"));
        number.setCellValueFactory(new PropertyValueFactory<JournalUI, String>("number"));
        dateBeginFormat.setCellValueFactory(new PropertyValueFactory<JournalUI, String>("dateBeginFormat"));
        dateEndFormat.setCellValueFactory(new PropertyValueFactory<JournalUI, String>("dateEndFormat"));
        weldersCount.setCellValueFactory(new PropertyValueFactory<JournalUI, String>("weldersCount"));
        curriculum.setCellValueFactory(new PropertyValueFactory<JournalUI, String>("curriculum"));

        setJournalTableItem(journalTableView, getJournals());
        journalTableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        journalTableView.addEventHandler(MouseEvent.MOUSE_CLICKED,new TableViewHandler());
        hideSearchBar();
    }

    private void initJournalDetailsPane(){
        tfxJournalNumber.textProperty().addListener(new ChangeJournalPropertiesListener());
        dpDateBegin.getEditor().textProperty().addListener(new ChangeJournalPropertiesListener());
        dpDateEnd.getEditor().textProperty().addListener(new ChangeJournalPropertiesListener());

        selectWelderPane.setVisible(false);
        txfSearchWelder.textProperty().addListener(new SearchWelderInvalidateListener());

        journalDetailsPane.setDisable(true);
    }

    private void clearJournalDetailsPane(){
        tfxJournalNumber.setText("");
        dpDateBegin.setValue(DateUtil.DEFAULT_DATE);
        dpDateEnd.setValue(DateUtil.DEFAULT_DATE);
        getTopics().clear();
        weldersTableView.getItems().clear();
        teachersListView.getItems().clear();
        for (MenuItem mi : teachersMenuButton.getItems()){
            CheckMenuItem cmi = (CheckMenuItem)mi;
            cmi.setSelected(false);
        }

    }

    private void initControlButtons(){
        btEdit.addEventHandler(MouseEvent.MOUSE_CLICKED, new ActiveEditHandler());
        btEdit.setDisable(true);
        btSave.setDisable(true);
    }

    private void fillComboBoxes(){
        initCurriculumComboBox();
        initTeachersMenuButton();
    }

    private void initCurriculumComboBox(){
        List<String> currNames = new ArrayList<String>();
        List<Curriculum> curriculums = curriculumService.getAll();
        for(Curriculum cur : curriculums){
            currNames.add(cur.getTitle());
        }
        cbCurriculum.setItems(FXCollections.observableArrayList(currNames));
    }

    private void initTeachersMenuButton(){
        initTeacherCheckItems();
        teachersMenuButton.getItems().clear();
        teachersMenuButton.getItems().addAll(getTeachersCheckItems());
    }

    private void initTeacherCheckItems(){
        List<Teacher> teachersDB = teacherService.getAll();
        getTeachersCheckItems().clear();
        for (Teacher t : teachersDB){
            TeacherUI teacherUI = new TeacherUI(t);
            CheckMenuItem teacherItem = new CheckMenuItem(teacherUI.getFullTeacherName());
            teacherItem.addEventHandler(ActionEvent.ACTION, new TeacherCheckMenuItemHandler());
            for (TeacherUI selectedJournalTeacher : getTeachers()){
                if(selectedJournalTeacher.getFullTeacherName().equals(teacherUI.getFullTeacherName()))
                    teacherItem.setSelected(true);
            }
            getTeachersCheckItems().add(teacherItem);
        }
    }

    private void initTopicTableView(JournalUI journal){
        initTopics(journal);
        timeTableDateFormat.setCellValueFactory(new PropertyValueFactory<TopicUI, String>("dateFormat"));
        timeTableHours.setCellValueFactory(new PropertyValueFactory<TopicUI, Double>("timeLong"));
        timeTableTopicTitle.setCellValueFactory(new PropertyValueFactory<TopicUI, String>("title"));

        setTableViewItems(topicTableView, getTopics());
        topicTableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        topicTableView.setEditable(false);
        LOGGER.debug("INIT TOPIC TABLE VIEW: Selected journal's topics is: "+getTopics());
    }

    private void initWeldersTableView(JournalUI journal){
        initWelders(journal);
        weldersTableID.setCellValueFactory(new PropertyValueFactory<WelderUI, Long>("id"));
        weldersTableName.setCellValueFactory(new PropertyValueFactory<WelderUI, String>("name"));
        weldersTableSurname.setCellValueFactory(new PropertyValueFactory<WelderUI, String>("surname"));
        weldersTableSecname.setCellValueFactory(new PropertyValueFactory<WelderUI, String>("secname"));
        weldersTableWeldMethods.setCellValueFactory(new PropertyValueFactory<WelderUI, ObservableList<String>>("weldMethods"));

        setTableViewItems(weldersTableView, getWelders());
        weldersTableView.getItems().addListener(new ChangeJournalPropertiesListener());
        weldersTableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        weldersTableView.setEditable(false);
        LOGGER.debug("INIT WELDER TABLE VIEW: Selected journal's welders is: "+getWelders());
    }


    private void initTeacherListView(JournalUI journal){
        initTeachers(journal);
        initTeachersMenuButton();
        initTeachersNames();
        teachersListView.setItems(getTeachersNames());
        teachersListView.getItems().addListener(new ChangeJournalPropertiesListener());
        teachersListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        teachersListView.setEditable(false);

    }

    private void initWeldersListView(){
        ObservableList<String> weldersList = FXCollections.observableArrayList();
        List<Welder> welders = welderService.getAll();
        for (Welder welder : welders){
            WelderUI welderUI = new WelderUI(welder);
            weldersList.add(welderUI.getFullName());
        }
        weldersListView.setItems(weldersList);
        weldersListView.setEditable(false);
        weldersListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }

    private void updateTeacherListView(){
        teachersListView.getItems().clear();
        teachersListView.getItems().addAll(getTeachersNames());
    }

    private <T>void  setTableViewItems(TableView<T> tableView, ObservableList<T> items){
        if(tableView.getColumns().size()==0){
            tableView.setItems(items);
            return;
        }
        tableView.getColumns().get(0).setVisible(false);
        tableView.setItems(items);
        tableView.getColumns().get(0).setVisible(true);

    }

    private void initWelders(JournalUI journal){
        Journal journalDB = journalService.get(journal.getId());
        if (journalDB == null) {
            welders.clear();
            return;
        }
        ObservableList<WelderUI> weldersUI = journal.getWelders();
        getWelders().clear();
        getWelders().addAll(weldersUI);
    }

    private void initTopics(JournalUI journal){
        Journal journalDB = journalService.get(journal.getId());
        if(journalDB==null){
            topics.clear();
            return;
        }
        List<TopicUI> topicsUI = timeTableUtil.getTimeTable(journalDB);
        //setTopics(FXCollections.observableArrayList(topicsUI));
        getTopics().clear();
        getTopics().addAll(FXCollections.observableArrayList(topicsUI));


    }

    private void initTeachers(JournalUI journal){
        Journal journalDB = journalService.get(journal.getId());
        if (journalDB == null) {
            teachers.clear();
            return;
        }
        ObservableList<TeacherUI> teachersUI = journal.getTeachers();
        getTeachers().clear();
        getTeachers().addAll(teachersUI);
    }

    private void initTeachersNames(){
        teachersNames.clear();
        for (MenuItem mi : getTeachersCheckItems()){
            CheckMenuItem teacherNameItem = (CheckMenuItem)mi;
            if(teacherNameItem.isSelected())
                teachersNames.add(teacherNameItem.getText());
        }
    }

    private void fillJournalDetailsPane(JournalUI selectedJournal){
        tfxJournalNumber.setText(selectedJournal.getNumber());
        dpDateBegin.setValue(DateUtil.getLocalDate(selectedJournal.getDateBegin()));
        dpDateEnd.setValue(DateUtil.getLocalDate(selectedJournal.getDateEnd()));
        dpDateEnd.setEditable(false);
        dpDateEnd.setDisable(true);
        if (!selectedJournal.getCurriculum().isEmpty())
            cbCurriculum.getSelectionModel().select(selectedJournal.getCurriculum());
        initTopicTableView(selectedJournal);
        initWeldersTableView(selectedJournal);
        initTeacherListView(selectedJournal);

    }

    private JournalUI getJournalUIFromFields(){
        JournalUI journalUI = new JournalUI();
        journalUI.setId(0);
        if(tfxJournalNumber.getText()==null || tfxJournalNumber.getText().isEmpty()){
            journalUI.setNumber("");
        }else {
            journalUI.setNumber(tfxJournalNumber.getText());
        }
        if(dpDateBegin.getValue()== null)
            journalUI.setDateBegin(DateUtil.getDate(DateUtil.DEFAULT_DATE));
        else
            journalUI.setDateBegin(DateUtil.getDate(dpDateBegin.getValue()));

        journalUI.setDateBeginFormat(DateUtil.format(journalUI.getDateBegin()));

        if(dpDateEnd.getValue()== null)
            journalUI.setDateEnd(DateUtil.getDate(DateUtil.DEFAULT_DATE));
        else
            journalUI.setDateEnd(DateUtil.getDate(dpDateEnd.getValue()));

        journalUI.setDateEndFormat(DateUtil.format(journalUI.getDateEnd()));

        if (cbCurriculum.getValue()==null)
            journalUI.setCurriculum("");
        else
            journalUI.setCurriculum(cbCurriculum.getValue());

        journalUI.setTeachers(getTeachersUIFromListView());
        journalUI.setWelders(weldersTableView.getItems());
        journalUI.setWeldersCount(weldersTableView.getItems().size()+"");

        return journalUI;
    }

    private ObservableList<TeacherUI> getTeachersUIFromListView(){
        ObservableList<String> teachersNames = teachersListView.getItems();
        ObservableList<TeacherUI> result = FXCollections.observableArrayList();
        List<Teacher> teachersFromDB = teacherService.getAll();
        for(Teacher t : teachersFromDB){
            TeacherUI teacherUI = new TeacherUI(t);
            if(teachersNames.contains(teacherUI.getFullTeacherName()))
                result.add(teacherUI);
        }
        return result;
    }

    private void saveJournalInDB(JournalUI journalUI){
        Journal journal = getJournalFromJournalUI(journalUI);
        if (journal.getJournalId()==0){
            journal.setJournalId(null);
            Long id = journalService.insert(journal);
            journal.setJournalId(id);
            JournalUI newJournal = new JournalUI(journal);
            journalTableView.getItems().remove(journalUI);
            journalTableView.getSelectionModel().clearSelection();
            journalTableView.getItems().add(newJournal);
            journalTableView.getSelectionModel().select(newJournal);
            LOGGER.debug("SAVE JOURNAL IN DB: New journal was added to data base with id="+id);
            return;
        }
        if(journalService.get(journal.getJournalId())!=null){
            JournalUI updJournal = new JournalUI(journal);
            journalService.update(journal);
            LOGGER.debug("SAVE JOURNAL IN DB: New journal was updated in data base");
            return;
        }
        LOGGER.debug("SAVE JOURNAL IN DB: Journal was not saved in data base");
    }

    private Journal getJournalFromJournalUI(JournalUI journalUI){
        Journal journal = new Journal();

        journal.setJournalId(journalUI.getId());
        journal.setNumber(journalUI.getNumber());
        journal.setDateBegin(journalUI.getDateBegin());
        journal.setDateEnd(journalUI.getDateEnd());

        List<Curriculum> curriculums = curriculumService.getAll();
        for (Curriculum cur: curriculums){
            if(journalUI.getCurriculum().equals(cur.getTitle())){
                journal.setCurriculum(cur);
                break;
            }
        }

        List<Teacher> teacherList = new ArrayList<Teacher>();
        for (TeacherUI teacherUI : journalUI.getTeachers()){
            teacherList.add(getTeacherFromTeacherUI(teacherUI));
        }
        journal.setTeachers(teacherList);

        List<Welder> welderList = new ArrayList<Welder>();
        for(WelderUI welderUI: journalUI.getWelders()){
            welderList.add(getWelderFromWelderUI(welderUI));
        }
        journal.setWelders(welderList);

        return journal;
    }

    private Teacher getTeacherFromTeacherUI(TeacherUI teacherUI){
        if(teacherUI.getId()==0)
            return new Teacher();
        return teacherService.get(teacherUI.getId());
    }

    private Welder getWelderFromWelderUI(WelderUI welderUI){
        if(welderUI.getId()==0)
            return new Welder();
        return welderService.get(welderUI.getId());
    }
    private boolean isJournalUIChanged(JournalUI selectedJournal){
        boolean result = false;
        JournalUI updJournal = getJournalUIFromFields();
        selectedJournal = new JournalUI(journalService.get(selectedJournal.getId()));

        if(!selectedJournal.getNumber().equals(updJournal.getNumber()))
            return true;
        if(!selectedJournal.getDateBeginFormat().equals(updJournal.getDateBeginFormat()))
            return true;
        if(!selectedJournal.getDateEndFormat().equals(updJournal.getDateEndFormat()))
            return true;
        if(!selectedJournal.getCurriculum().equals(updJournal.getCurriculum()))
            return true;
        if(!selectedJournal.getWelders().equals(updJournal.getWelders()))
            return true;
        if(!selectedJournal.getTeachers().equals(updJournal.getTeachers()))
            return true;
        return result;
    }

    private void setJournalTableItem(TableView<JournalUI> tableView, ObservableList<JournalUI> items){
        if(tableView.getColumns().size()==0){
            tableView.setItems(items);
            return;
        }
        tableView.getColumns().get(0).setVisible(false);
        tableView.setItems(items);
        tableView.getColumns().get(0).setVisible(true);

    }

    private void hideSearchBar(){
        if (toolBarSearch.isVisible()){
            toolBarSearch.setVisible(false);
            btShowSearchToolBar.setSelected(false);
            vboxToolBars.setMargin(toolBarSearch, new Insets(0, 0, -40, 0));
            txfSearch.clear();
        }
    }

    private List<JournalUI> getJournalUIList(List<Journal> journalList){
        List<JournalUI> result = new ArrayList<JournalUI>();
        for (Journal journal : journalList){
            JournalUI journalUI = new JournalUI(journal);
            result.add(journalUI);
        }
        return result;
    }

    private JournalUI getJournalUICopy(JournalUI srcJournal){
        JournalUI result = new JournalUI(getJournalFromJournalUI(srcJournal));
        return result;
    }

    private void lightRefreshJournalTable(){
        journalTableView.getColumns().get(0).setVisible(false);
        journalTableView.getColumns().get(0).setVisible(true);
    }

    @FXML
    private void refreshJournalTableView(){
        journalTableView.getItems().clear();
        journalTableView.getItems().addAll(FXCollections.observableArrayList(getJournalUIList(journalService.getAll())));
        journalTableView.getSelectionModel().selectFirst();
        fillJournalDetailsPane(journalTableView.getSelectionModel().getSelectedItem());
    }

    @FXML
    private void selectFirst(){
        tableUtil.selectFirstItem(journalTableView);
    }

    @FXML
    private void selectLast(){
        tableUtil.selectLastItem(journalTableView);
    }

    @FXML
    private void selectPrev(){
        tableUtil.selectPrevItem(journalTableView);
    }

    @FXML
    private void selectNext(){
        tableUtil.selectNextItem(journalTableView);
    }

    @FXML
    private void makeJournalCopy(){
        JournalUI srcJournal = journalTableView.getSelectionModel().getSelectedItem();
        if(null==srcJournal)
            return;
        JournalUI copyJournal = getJournalUICopy(srcJournal);
        copyJournal.setId(0);
        saveJournalInDB(copyJournal);
        journalDetailsPane.setDisable(false);
        btEdit.setDisable(true);
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
    private void saveJournal(){
        JournalUI selectedJournal = journalTableView.getSelectionModel().getSelectedItem();
        if(selectedJournal.getId()==0){
            saveJournalInDB(getJournalUIFromFields());
            btSave.setDisable(true);
            journalDetailsPane.setDisable(true);
            return;
        }
        if(isJournalUIChanged(selectedJournal)){
            JournalUI updJournal = getJournalUIFromFields();
            Long id = selectedJournal.getId();
            updJournal.setId(id);
            saveJournalInDB(updJournal);
            journalTableView.getItems().remove(selectedJournal);

            updJournal = new JournalUI(journalService.get(id));
            journalTableView.getSelectionModel().clearSelection();
            journalTableView.getItems().add(updJournal);
            lightRefreshJournalTable();
            journalTableView.getSelectionModel().select(updJournal);
            fillJournalDetailsPane(updJournal);

            btSave.setDisable(true);
            journalDetailsPane.setDisable(true);
            return;

        }
        LOGGER.debug("SAVE JOURNAL: Journal was not saved");
        btSave.setDisable(true);
        journalDetailsPane.setDisable(true);

    }

    @FXML
    private void removeJournals(){
        ObservableList<JournalUI> deletingJournals = journalTableView.getSelectionModel().getSelectedItems();
        if(!deletingJournals.isEmpty()){
            LOGGER.debug("REMOVE JOURNALS: start DELETE dialog...");
            Action response = Dialogs.create().owner(mainJournalPane.getScene().getWindow())
                    .title("Удаление записей")
                    .masthead("Сделан выбор записей для удаления")
                    .message("Удалить выбранные записи? ("+deletingJournals.size()+"шт.)")
                    .actions(Dialog.Actions.OK, Dialog.Actions.CANCEL)
                    .showConfirm();
            if(response == Dialog.Actions.OK){
                for(JournalUI journalUI : deletingJournals){
                    Journal journalDB = journalService.get(journalUI.getId());
                    if(null!=journalDB) {
                        journalService.delete(journalDB);
                        journalTableView.getItems().remove(journalUI);
                    }
                    else
                        LOGGER.warn("REMOVE JOURNALS: Not possible to delete journal: "+journalUI);
                                   }
                journalTableView.getSelectionModel().clearSelection();
                journalTableView.getSelectionModel().selectFirst();
                fillJournalDetailsPane(journalTableView.getSelectionModel().getSelectedItem());

                LOGGER.debug("REMOVE JOURNALS: Selected journals has been deleted");
            }

        }
    }

    @FXML
    private void addNewJournal(){
        JournalUI newJournal = new JournalUI();
        getJournals().add(newJournal);
        journalTableView.getSelectionModel().clearSelection();
        journalTableView.getSelectionModel().select(newJournal);

        fillJournalDetailsPane(newJournal);
        journalDetailsPane.setDisable(false);
        btEdit.setDisable(true);

    }

    @FXML
    private void closeJournalPane(){
        if(mainJournalPane.isVisible()){
            mainJournalPane.setVisible(false);
            controllerManager.getMainFrameController().showLogo();
        }

    }

    @FXML
    private void showWelderSelectorPane(){
        if(selectWelderPane.isVisible()){
            selectWelderPane.setVisible(false);
        }else {
            selectWelderPane.setVisible(true);
            txfSearchWelder.requestFocus();
            if (weldersListView.getItems().isEmpty()){
                initWeldersListView();
            }
        }
    }

    @FXML
    private void addWelderToJournal(){
        String selectedWelder = weldersListView.getSelectionModel().getSelectedItem();
        if(selectedWelder == null || selectedWelder.isEmpty()){
            LOGGER.debug("ADD WELDER TO JOURNAL: not possible to add welder to journal (null or empty)");
            return;
        }
        try {
            LOGGER.debug("ADD WELDER TO JOURNAL: selectedWelder = "+selectedWelder);
            String strId = selectedWelder.substring(0, selectedWelder.indexOf("\t"));
            Long id = Long.parseLong(strId);
            LOGGER.debug("ADD WELDER TO JOURNAL: id = "+id);
            if(id==null){
                return;
            }
            WelderUI addedWelder = new WelderUI(welderService.get(id));
            if (!getWelders().contains(addedWelder)) {
                getWelders().add(addedWelder);
            }
        }catch (NumberFormatException ex){
            LOGGER.debug("ADD WELDER TO JOURNAL: not possible to add welder to journal: "+ex.getMessage());
            return;
        }

    }

    @FXML
    private void removeWelderFromJournal(){
        WelderUI selectedWelder = weldersTableView.getSelectionModel().getSelectedItem();
        if (selectedWelder == null) {
            return;
        }
        Action response = Dialogs.create().owner(mainJournalPane.getScene().getWindow())
                .title("Удаление записей")
                .masthead("Сделан выбор записей для удаления")
                .message("Удалить сварщика(ов) из данного журнала?")
                .actions(Dialog.Actions.OK, Dialog.Actions.CANCEL)
                .showConfirm();
        if(response == Dialog.Actions.OK){
            getWelders().remove(selectedWelder);
        }

    }

    @FXML
    private void calculateCurriculum(){
        String curriculumTitle = cbCurriculum.getSelectionModel().getSelectedItem();
        if(null == curriculumTitle || curriculumTitle.isEmpty())
            return;
        List<TopicUI> topics = timeTableUtil.getTimeTable(curriculumTitle, dpDateBegin.getValue());
        getTopics().clear();
        getTopics().addAll(topics);
    }

    private class TableViewHandler implements EventHandler<MouseEvent>{
        @Override
        public void handle(MouseEvent event) {
            LOGGER.debug("TABLE VIEW HANDLER: Mouse clicked on journal table");
            JournalUI selectedJournal = journalTableView.getSelectionModel().getSelectedItem();
            if(selectedJournal != null){
                LOGGER.debug("TABLE VIEW HANDLER: Selected journal is: \n"+selectedJournal+"\n");
                if(!journalDetailsPane.isDisabled())
                    journalDetailsPane.setDisable(true);
                btEdit.setDisable(false);
                btSave.setDisable(true);
                fillJournalDetailsPane(selectedJournal);
            }else{
                btEdit.setDisable(true);
                clearJournalDetailsPane();
            }

        }
    }
    private class TeacherCheckMenuItemHandler implements EventHandler<ActionEvent>{
        @Override
        public void handle(ActionEvent event) {
            JournalUI selectedJournal = journalTableView.getSelectionModel().getSelectedItem();
            if (selectedJournal == null)
                return;
            initTeachersNames();
        }
    }


    private class TopicsListChangeListener implements ListChangeListener<TopicUI>{
        @Override
        public void onChanged(Change<? extends TopicUI> c) {
            LOGGER.debug("TOPICS LIST CHANGE: topics was changed");
            if(!getTopics().isEmpty())
                dpDateEnd.setValue(DateUtil.getLocalDate(getTopics().get(getTopics().size()-1).getDate()));

        }
    }

    private class ActiveEditHandler implements EventHandler<MouseEvent>{
        @Override
        public void handle(MouseEvent event) {
            LOGGER.debug("ACTIVE EDIT HANDLER: click on btEdit");
            if(journalDetailsPane.isDisabled()) {
                journalDetailsPane.setDisable(false);
                btEdit.setDisable(true);
            }
        }
    }
    private class ChangeJournalPropertiesListener implements InvalidationListener{
        @Override
        public void invalidated(Observable observable) {
            LOGGER.debug("CHANGE JOURNAL PROPERTIES: journal properties has been changed.");
            if(!journalDetailsPane.isDisabled()) {
                    btSave.setDisable(false);
            }
        }
    }
    private class SearchWelderInvalidateListener implements InvalidationListener{
        @Override
        public void invalidated(Observable observable) {
            if(txfSearchWelder.getText().trim().isEmpty()){
                initWeldersListView();
                return;
            }
            ObservableList<String> filteredList = FXCollections.observableArrayList();
            ObservableList<String> welderList = weldersListView.getItems();
            for (String welderName : welderList ){

                if(welderName.toLowerCase().contains(txfSearchWelder.getText().toLowerCase())){
                    filteredList.add(welderName);
                }
            }
            weldersListView.setItems(filteredList);
            weldersListView.getSelectionModel().selectFirst();
        }
    }

    private class SearchValidateListener implements InvalidationListener{
        @Override
        public void invalidated(Observable observable) {
            if (txfSearch.textProperty().get().isEmpty()){
                refreshJournalTableView();
                return;
            }

            ObservableList<JournalUI> filteredList = FXCollections.observableArrayList();
            ObservableList<TableColumn<JournalUI,?>> columns = journalTableView.getColumns();

            for (int i = 0; i<getJournals().size(); i++){

                for (int j = 0; j<columns.size(); j++){
                    TableColumn<JournalUI,?> column = columns.get(j);
                    String cellValue = column.getCellData(getJournals().get(i)).toString();
                    cellValue = cellValue.toLowerCase();
                    if (cellValue.contains(txfSearch.textProperty().get().toLowerCase())){
                        filteredList.add(getJournals().get(i));
                        break;
                    }
                }
            }
            journalTableView.setItems(filteredList);
            if (!filteredList.isEmpty()){
                journalTableView.getSelectionModel().selectFirst();
            }
        }

    }


}
