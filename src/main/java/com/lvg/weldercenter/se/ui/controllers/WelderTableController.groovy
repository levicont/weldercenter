package com.lvg.weldercenter.se.ui.controllers

import com.lvg.weldercenter.se.ui.dto.WelderTableViewDTO
import com.lvg.weldercenter.se.ui.factories.LineNumbersCellFactory
import com.lvg.weldercenter.se.ui.listeners.welderspane.WeldersTableViewEventHandler
import com.lvg.weldercenter.se.ui.repositories.WelderDTORepository
import com.lvg.weldercenter.se.ui.utils.ControlFXUtils
import javafx.beans.value.ChangeListener
import javafx.beans.value.ObservableValue
import javafx.event.Event
import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.control.*
import javafx.scene.control.cell.PropertyValueFactory
import javafx.scene.layout.VBox
import org.apache.log4j.Logger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class WelderTableController implements Initializable{

    private static final Logger LOGGER = Logger.getLogger(WelderTableController.class)

    @Autowired
    WelderDTORepository weldersRepository
    @Autowired
    WeldersTableViewEventHandler weldersTableViewEventHandler

    @FXML
    private TextField txfSearch

    @FXML
    private VBox vboxToolBars

    @FXML
    private ToolBar toolBarSearch

    @FXML
    private Button btPrev

    @FXML
    private Button btNext

    @FXML
    private Button btLast

    @FXML
    private Button btNew


    @FXML
    private TableView<WelderTableViewDTO> welderTableView

    @FXML
    private TableColumn<WelderTableViewDTO, Long> id

    @FXML
    private TableColumn<WelderTableViewDTO, WelderTableViewDTO> lineNumber

    @FXML
    private TableColumn<WelderTableViewDTO, String> organizationName

    @FXML
    private TableColumn<WelderTableViewDTO, String> surname

    @FXML
    private TableColumn<WelderTableViewDTO, String> name

    @FXML
    private TableColumn<WelderTableViewDTO, String> birthday

    @FXML
    private TableColumn<WelderTableViewDTO, String> secondName

    @FXML
    private Button btFirst

    @Override
    void initialize(URL location, ResourceBundle resources) {
        init()
    }

    private void init(){
        initTable()
        initTxfSearch()
    }

    private void initTable(){
        LOGGER.debug("INITIALIZING WelderPane")
        id.setCellValueFactory(new PropertyValueFactory<WelderTableViewDTO,Long>('id'))
        lineNumber.setCellFactory(new LineNumbersCellFactory<WelderTableViewDTO, WelderTableViewDTO>())
        name.setCellValueFactory(new PropertyValueFactory<WelderTableViewDTO,String>('name'))
        surname.setCellValueFactory(new PropertyValueFactory<WelderTableViewDTO,String>('surname'))
        secondName.setCellValueFactory(new PropertyValueFactory<WelderTableViewDTO,String>('secondName'))
        birthday.setCellValueFactory(new PropertyValueFactory<WelderTableViewDTO,String>('birthday'))
        organizationName.setCellValueFactory(new PropertyValueFactory<WelderTableViewDTO,String>('organization'))
        weldersRepository.reloadWelders()
        welderTableView.placeholderProperty().set(new Label(ControlFXUtils.EMPTY_TABLE_PLACEHOLDER))
        welderTableView.setItems(weldersRepository.welderTableViewDTOListProperty())
        welderTableView.addEventHandler(Event.ANY, weldersTableViewEventHandler)

    }

    private void initTxfSearch(){
        txfSearch.textProperty().addListener(new ChangeListener<String>() {
            @Override
            void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                weldersRepository.filter(newValue)
            }
        })
    }

    void refreshTable(){
        weldersRepository.reloadWelders()
    }

    TableView<WelderTableViewDTO> getWeldersTableView(){
        return welderTableView
    }

    @FXML
    void selectFirst(){
        LOGGER.debug("SELECT FIRST RECORD in weldersTableView")
        ControlFXUtils.selectFirstTableRecord(welderTableView)

    }

    @FXML
    void selectLast(){
        LOGGER.debug("SELECT LAST RECORD in weldersTableView")
        ControlFXUtils.selectLastTableRecord(welderTableView)
    }

    @FXML
    void selectPrevious(){
        LOGGER.debug("SELECT PREVIOUS RECORD in weldersTableView")
        ControlFXUtils.selectPrevTableRecord(welderTableView)
    }

    @FXML
    void selectNext(){
        LOGGER.debug("SELECT NEXT RECORD in weldersTableView")
        ControlFXUtils.selectNextTableRecord(welderTableView)
    }

    @FXML
    void addNew(){
        weldersRepository.getNewWelderDTO()
        selectLast()
    }

    @FXML
    void addCopy(){

    }
}
