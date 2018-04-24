package com.lvg.weldercenter.se.ui.controllers

import com.lvg.weldercenter.se.ui.dto.DTOConstants
import com.lvg.weldercenter.se.ui.dto.WelderDTO
import com.lvg.weldercenter.se.ui.dto.WelderTableViewDTO
import com.lvg.weldercenter.se.ui.factories.LineNumbersCellFactory
import com.lvg.weldercenter.se.ui.listeners.welderspane.LoadWelderByIdChangeStateListener
import com.lvg.weldercenter.se.ui.repositories.WelderDTORepository
import com.lvg.weldercenter.se.ui.services.LoadingWelderByIdService
import com.lvg.weldercenter.se.ui.services.LoadingWeldersForTableViewService
import com.lvg.weldercenter.se.ui.utils.ControlFXUtils
import com.lvg.weldercenter.se.ui.utils.Printer
import com.lvg.weldercenter.se.ui.utils.ServiceUtils
import javafx.beans.value.ChangeListener
import javafx.beans.value.ObservableValue
import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.control.*
import javafx.scene.control.cell.PropertyValueFactory
import javafx.scene.layout.VBox
import org.apache.log4j.Logger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

import static javafx.concurrent.Worker.State

@Component
class WelderTableController implements Initializable {

    private static final Logger LOGGER = Logger.getLogger(WelderTableController.class)

    @Autowired
    WelderDTORepository weldersRepository
    @Autowired
    LoadingWeldersForTableViewService loadingWeldersForTableViewService
    @Autowired
    LoadingWelderByIdService loadingWelderByIdService
    @Autowired
    LoadWelderByIdChangeStateListener loadWelderByIdChangeStateListener
    @Autowired
    WelderController welderController

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

    private void init() {
        initTable()
        initTxfSearch()
    }

    private void initTable() {
        LOGGER.debug("INITIALIZING WelderPane")
        id.setCellValueFactory(new PropertyValueFactory<WelderTableViewDTO, Long>('id'))
        lineNumber.setCellFactory(new LineNumbersCellFactory<WelderTableViewDTO, WelderTableViewDTO>())
        name.setCellValueFactory(new PropertyValueFactory<WelderTableViewDTO, String>('name'))
        surname.setCellValueFactory(new PropertyValueFactory<WelderTableViewDTO, String>('surname'))
        secondName.setCellValueFactory(new PropertyValueFactory<WelderTableViewDTO, String>('secondName'))
        birthday.setCellValueFactory(new PropertyValueFactory<WelderTableViewDTO, String>('birthday'))
        organizationName.setCellValueFactory(new PropertyValueFactory<WelderTableViewDTO, String>('organization'))
        weldersRepository.reloadWelders()
        welderTableView.placeholderProperty().set(new Label(ControlFXUtils.EMPTY_TABLE_PLACEHOLDER))
        welderTableView.setItems(weldersRepository.welderTableViewDTOListProperty())
        welderTableView.selectionModel.selectedItemProperty().addListener(new ChangeListener<WelderTableViewDTO>() {
            @Override
            void changed(ObservableValue<? extends WelderTableViewDTO> observable, WelderTableViewDTO oldValue, WelderTableViewDTO newValue) {
                doLoadSelectedWelderFromDB()
            }
        })

    }

    private void initTxfSearch() {
        txfSearch.textProperty().addListener(new ChangeListener<String>() {
            @Override
            void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                weldersRepository.filter(newValue)
                selectFirst()
                txfSearch.requestFocus()
                txfSearch.selectEnd()
            }
        })
    }

    void refreshTable() {
        weldersRepository.reloadWelders()
    }

    TableView<WelderTableViewDTO> getWeldersTableView() {
        return welderTableView
    }

    @FXML
    void selectFirst() {
        LOGGER.debug("SELECT FIRST RECORD in weldersTableView")
        ControlFXUtils.selectFirstTableRecord(welderTableView)

    }

    @FXML
    void selectLast() {
        LOGGER.debug("SELECT LAST RECORD in weldersTableView")
        ControlFXUtils.selectLastTableRecord(welderTableView)
    }

    @FXML
    void selectPrevious() {
        LOGGER.debug("SELECT PREVIOUS RECORD in weldersTableView")
        ControlFXUtils.selectPrevTableRecord(welderTableView)
    }

    @FXML
    void selectNext() {
        LOGGER.debug("SELECT NEXT RECORD in weldersTableView")
        ControlFXUtils.selectNextTableRecord(welderTableView)
    }

    @FXML
    void addNew() {
        weldersRepository.getNewWelderDTO()
        selectLast()
    }

    @FXML
    void addCopy() {

    }

    void selectWelderById(Long id) {
        LOGGER.debug("  ${id}")
        loadingWeldersForTableViewService.stateProperty().addListener(new ChangeListener<State>() {
            @Override
            void changed(ObservableValue<? extends State> observable, State oldValue, State newValue) {
                if (newValue == State.SUCCEEDED) {
                    WelderTableViewDTO welderTableViewDTO = findWelderTableViewDTOById(id)
                    if (welderTableViewDTO != null) {
                        LOGGER.debug("selectWelderById: selecting welderTableViewDTO: ${welderTableViewDTO}")
                        welderTableView.selectionModel.select(welderTableViewDTO)
                    }
                }
                if (newValue == State.SUCCEEDED || newValue == State.FAILED)
                    loadingWeldersForTableViewService.stateProperty().removeListener(this)
            }
        })

    }

    private WelderTableViewDTO findWelderTableViewDTOById(Long id) {
        WelderTableViewDTO welderTableViewDTO = null
        welderTableView.items.stream().forEach(
                { welder ->
                    if (welder.getIdProperty().get() == id) {
                        LOGGER.debug("selectWelderById: welderTableViewDTO has found: ${welder}")
                        welderTableViewDTO = welder
                    }
                })
        return welderTableViewDTO
    }

    private void doLoadSelectedWelderFromDB() {
        LOGGER.debug("--- BEGIN doLoadSelectedWelderFromDB ---")
        WelderTableViewDTO selectedWelder = getWeldersTableView()
                .selectionModel.getSelectedItem()
        if (selectedWelder == null) {
            welderController.welderDTOProperty().set(null)
            LOGGER.debug("selectedWelder is null")
            return
        }

        if (selectedWelder.id == DTOConstants.NULL_ID_FIELD_DEFAULT)  {
            welderController.loadWelder(WelderDTO.defaultWelderDTO())
            LOGGER.debug("selectedWelder is unsaved it has id: ${selectedWelder.id}")
            Printer.logDTO(WelderTableViewDTO.class, selectedWelder)
            return
        }
        //If we have an empty welder then we are deleting it from TableView
        welderController.welderDTORepository.removeUnsavedItems()
        LOGGER.debug("selectedWelder is presented in DB ")
        Printer.logDTO(WelderTableViewDTO.class, selectedWelder)
        LOGGER.debug("Try to start loadingWelderByIdService")
        loadingWelderByIdService.setId(selectedWelder.id)
        loadingWelderByIdService.stateProperty().addListener(loadWelderByIdChangeStateListener)
        ServiceUtils.startService(loadingWelderByIdService)

    }
}
