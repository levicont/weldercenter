package com.lvg.weldercenter.se.ui.controllers

import com.lvg.weldercenter.se.ui.dto.WelderTableViewDTO
import com.lvg.weldercenter.se.ui.factories.LineNumbersCellFactory
import com.lvg.weldercenter.se.ui.listeners.welderspane.WeldersTableViewEventHandler
import com.lvg.weldercenter.se.ui.repositories.WelderDTORepository
import javafx.event.Event
import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.control.Button
import javafx.scene.control.TableColumn
import javafx.scene.control.TableView
import javafx.scene.control.TextField
import javafx.scene.control.ToggleButton
import javafx.scene.control.ToolBar
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
    private ToggleButton btShowSearchToolBar

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
        initTable()
    }

    void initTable(){
        LOGGER.debug("INITIALIZING WelderPane")
        id.setCellValueFactory(new PropertyValueFactory<WelderTableViewDTO,Long>('id'))
        lineNumber.setCellFactory(new LineNumbersCellFactory<WelderTableViewDTO, WelderTableViewDTO>())
        name.setCellValueFactory(new PropertyValueFactory<WelderTableViewDTO,String>('name'))
        surname.setCellValueFactory(new PropertyValueFactory<WelderTableViewDTO,String>('surname'))
        secondName.setCellValueFactory(new PropertyValueFactory<WelderTableViewDTO,String>('secondName'))
        birthday.setCellValueFactory(new PropertyValueFactory<WelderTableViewDTO,String>('birthday'))
        organizationName.setCellValueFactory(new PropertyValueFactory<WelderTableViewDTO,String>('organization'))
        welderTableView.setItems(weldersRepository.allWeldersForTableView.get())
        welderTableView.addEventHandler(Event.ANY, weldersTableViewEventHandler)

    }

    void refreshTable(){
        initTable()
        weldersRepository.reloadWelders()
    }

    TableView<WelderTableViewDTO> getWeldersTableView(){
        return welderTableView
    }
}
