package com.lvg.weldercenter.ui.control;

import com.lvg.weldercenter.models.*;
import com.lvg.weldercenter.services.CurriculumService;
import com.lvg.weldercenter.services.JournalService;
import com.lvg.weldercenter.spring.factories.ServiceFactory;
import com.lvg.weldercenter.ui.entities.*;
import com.lvg.weldercenter.ui.util.DateUtil;
import com.lvg.weldercenter.ui.util.TimeTableUtil;
import com.lvg.weldercenter.ui.util.managers.TimeTableUtilManager;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
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
    private TimeTableUtil timeTableUtil = new TimeTableUtilManager();

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
    ChoiceBox<TeacherUI> teacherChoiceBox;

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
    private ObservableList<String> curriculums = FXCollections.observableArrayList(new ArrayList<String>());

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
        tfxJournalNumber.textProperty().addListener(new TextFieldsValidateListener());
        dpDateBegin.getEditor().textProperty().addListener(new TextFieldsValidateListener());
        dpDateEnd.getEditor().textProperty().addListener(new TextFieldsValidateListener());

       // journalDetailsPane.setDisable(true);
    }

    private void initControlButtons(){
        btEdit.addEventHandler(MouseEvent.MOUSE_CLICKED, new ActiveEditHandler());
        btEdit.setDisable(true);
        btSave.setDisable(true);
    }

    private void fillComboBoxes(){
        initCurriculumComboBox();
    }

    private void initCurriculumComboBox(){
        List<String> currNames = new ArrayList<String>();
        List<Curriculum> curriculums = curriculumService.getAll();
        for(Curriculum cur : curriculums){
            currNames.add(cur.getTitle());
        }
        cbCurriculum.setItems(FXCollections.observableArrayList(currNames));
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
        weldersTableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        weldersTableView.setEditable(false);
        LOGGER.debug("INIT WELDER TABLE VIEW: Selected journal's welders is: "+getWelders());
    }

    private void initTeacherListView(JournalUI journal){
        initTeachers(journal);
        teachersListView.setItems(getTeachersNames());
        teachersListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        teachersListView.setEditable(false);

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
        setWelders(weldersUI);
    }

    private void initTopics(JournalUI journal){
        Journal journalDB = journalService.get(journal.getId());
        if(journalDB==null){
            topics.clear();
            return;
        }
        List<TopicUI> topicsUI = timeTableUtil.getTimeTable(journalDB);
        setTopics(FXCollections.observableArrayList(topicsUI));
    }

    private void initTeachers(JournalUI journal){
        Journal journalDB = journalService.get(journal.getId());
        if (journalDB == null) {
            teachers.clear();
            teachersNames.clear();
            return;
        }
        ObservableList<TeacherUI> teachersUI = journal.getTeachers();
        setTeachers(teachersUI);
        initTeachersNames(teachersUI);
    }

    private void initTeachersNames(ObservableList<TeacherUI> teachers){
        teachersNames.clear();
        for (TeacherUI teacherUI : teachers){
            teachersNames.add(teacherUI.getSurname()+" "+teacherUI.getName()+" "+teacherUI.getSecname());
        }
    }

    private void fillJournalDetailsPane(JournalUI selectedJournal){
        tfxJournalNumber.setText(selectedJournal.getNumber());
        dpDateBegin.setValue(DateUtil.getLocalDate(selectedJournal.getDateBegin()));
        dpDateEnd.setValue(DateUtil.getLocalDate(selectedJournal.getDateEnd()));
        if (!selectedJournal.getCurriculum().isEmpty())
            cbCurriculum.getSelectionModel().select(selectedJournal.getCurriculum());
        initTopicTableView(selectedJournal);
        initWeldersTableView(selectedJournal);
        initTeacherListView(selectedJournal);

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

    @FXML
    private void closeJournalPane(){
        if(mainJournalPane.isVisible()){
            mainJournalPane.setVisible(false);
            controllerManager.getMainFrameController().showLogo();
        }

    }

    private class TableViewHandler implements EventHandler<MouseEvent>{
        @Override
        public void handle(MouseEvent event) {
            LOGGER.debug("TABLE VIEW HANDLER: Mouse clicked on journal table");
            JournalUI selectedJournal = journalTableView.getSelectionModel().getSelectedItem();
            if(selectedJournal != null){
                LOGGER.debug("TABLE VIEW HANDLER: Selected journal is: \n"+selectedJournal+"\n");
                fillJournalDetailsPane(selectedJournal);
            }

        }
    }

    private class TextFieldsValidateListener implements InvalidationListener{
        @Override
        public void invalidated(Observable observable) {
            LOGGER.debug("TEXT FIELD INVALIDATE: invalidated TextField on JournalDetailPane");

        }
    }

    private class ActiveEditHandler implements EventHandler<MouseEvent>{
        @Override
        public void handle(MouseEvent event) {
            LOGGER.debug("ACTIVE EDIT HANDLER: click on btEdit");
        }
    }


}
