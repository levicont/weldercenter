package com.lvg.weldercenter.se.ui.controllers

import com.lvg.weldercenter.se.ui.models.WelderUI
import com.lvg.weldercenter.se.ui.repositories.WeldersRepository
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
    WeldersRepository weldersRepository

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
    private TableView<WelderUI> welderTableView

    @FXML
    private TableColumn<WelderUI, Long> id

    @FXML
    private TableColumn<WelderUI, String> organizationName

    @FXML
    private TableColumn<WelderUI, String> surname

    @FXML
    private TableColumn<WelderUI, String> name

    @FXML
    private TableColumn<WelderUI, String> job

    @FXML
    private TableColumn<WelderUI, String> birthdayFormat

    @FXML
    private TableColumn<WelderUI, String> secname

    @FXML
    private Button btFirst

    @Override
    void initialize(URL location, ResourceBundle resources) {
        initTable()
    }

    void initTable(){
        LOGGER.debug("INITIALIZING WelderPane")
        id.setCellValueFactory(new PropertyValueFactory<WelderUI,Long>('id'))
        name.setCellValueFactory(new PropertyValueFactory<WelderUI,String>('name'))
        surname.setCellValueFactory(new PropertyValueFactory<WelderUI,String>('surname'))
        secname.setCellValueFactory(new PropertyValueFactory<WelderUI,String>('secondName'))
        job.setCellValueFactory(new PropertyValueFactory<WelderUI,String>('job'))
        birthdayFormat.setCellValueFactory(new PropertyValueFactory<WelderUI,String>('birthdayFormat'))
        organizationName.setCellValueFactory(new PropertyValueFactory<WelderUI,String>('organizationName'))
        welderTableView.setItems(weldersRepository.allWelders.get())
        welderTableView.itemsProperty().bindBidirectional(weldersRepository.allWelders)

    }
}
