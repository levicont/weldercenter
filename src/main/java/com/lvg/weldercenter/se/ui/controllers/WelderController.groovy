package com.lvg.weldercenter.se.ui.controllers

import com.lvg.weldercenter.se.models.OrganizationEmbedded
import com.lvg.weldercenter.se.ui.converters.OrganizationEmbeddedStringConverter
import com.lvg.weldercenter.se.ui.dto.DTOConstants
import com.lvg.weldercenter.se.ui.dto.WelderDTO
import com.lvg.weldercenter.se.ui.dto.WelderTableViewDTO
import com.lvg.weldercenter.se.ui.repositories.EducationDTORepository
import com.lvg.weldercenter.se.ui.repositories.JobDTORepository
import com.lvg.weldercenter.se.ui.repositories.QualificationDTORepository
import com.lvg.weldercenter.se.ui.repositories.WelderDTORepository
import com.lvg.weldercenter.se.ui.services.LoadingWelderByIdService
import com.lvg.weldercenter.se.ui.services.SaveWelderDTOService
import com.lvg.weldercenter.se.ui.utils.ControlFXUtils
import com.lvg.weldercenter.se.ui.utils.Printer
import javafx.beans.property.*
import javafx.beans.value.ChangeListener
import javafx.beans.value.ObservableValue
import javafx.concurrent.Worker
import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.control.*
import javafx.scene.layout.BorderPane
import javafx.scene.layout.GridPane
import javafx.util.Callback
import org.apache.log4j.Logger
import org.controlsfx.control.textfield.AutoCompletionBinding
import org.controlsfx.control.textfield.TextFields
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

import java.time.LocalDate
import java.time.format.DateTimeFormatter

import static javafx.application.Platform.runLater

@Component
class WelderController implements Initializable {
    private static final Logger LOGGER = Logger.getLogger(WelderController.class)
    @Autowired
    MainFrameController mainFrameController
    //TODO I don't remember why this statement here
    /*
    @Autowired
    FXMLLoaderProvider fxmlLoaderProvider

     */

    @Autowired
    OrganizationEmbeddedStringConverter organizationEmbeddedStringConverter

    @Autowired
    WelderDTORepository welderDTORepository
    @Autowired
    WelderTableController welderTableController
    @Autowired
    EducationDTORepository educationDTORepository
    @Autowired
    QualificationDTORepository qualificationRepository
    @Autowired
    JobDTORepository jobDTORepository
    @Autowired
    SaveWelderDTOService saveWelderDTOService
    @Autowired
    LoadingWelderByIdService loadingWelderByIdService

    private WelderDTO welderDTO
    private final ObjectProperty<WelderDTO> welderDTOProperty = new SimpleObjectProperty<>(welderDTO)



    @FXML
    private TextField txfSurname
    @FXML
    private TextField txfName
    @FXML
    private TextField txfSecname
    @FXML
    private DatePicker dpBirthday
    @FXML
    private TextField txfDocNumber
    @FXML
    private DatePicker dpDateBegin
    @FXML
    private ComboBox<String> cbEducation
    @FXML
    private ComboBox<String> cbQualification
    @FXML
    private ComboBox<String> cbJob
    @FXML
    private TextField txfAddress
    @FXML
    private TextField txfOrganizationName
    @FXML
    private TextField txfOrganizationAddress
    @FXML
    private TextField txfOrganizationPhone


    @FXML
    private Button btSave
    @FXML
    private Button btAdd
    @FXML
    private Button btDelete

    @FXML
    BorderPane mainWelderPane
    @FXML
    private GridPane welderDetailsPane

    private TextField[] allTextFields
    private DatePicker[] allDatePickers
    private ObjectProperty<WelderDTO> bufferedWelderDTOProperty = new SimpleObjectProperty<>()
    private BooleanProperty isWelderChangedProperty = new SimpleBooleanProperty(false)

    @Override
    void initialize(URL location, ResourceBundle resources) {
        init()
    }

    private void init() {
        initCbEducation()
        initCbQualification()
        initCbJob()
        initCRUDButtons()
        initTxfOrganizationName()
        allTextFields = [txfName, txfSurname, txfSecname, txfDocNumber, txfAddress,
                         txfOrganizationName, txfOrganizationAddress, txfOrganizationPhone]
        allDatePickers = [dpDateBegin, dpBirthday]
        welderDetailsPane.disableProperty().bind(welderDTOProperty.isNull())

    }

    //Initialized organization name text field as autocomplete field
    private void initTxfOrganizationName(){
        TextFields.bindAutoCompletion(txfOrganizationName,
                new Callback<AutoCompletionBinding.ISuggestionRequest, Collection<OrganizationEmbedded>>() {
            @Override
            Collection<OrganizationEmbedded> call(AutoCompletionBinding.ISuggestionRequest param) {

                return welderDTORepository.allOrganizations.findAll {org ->
                    org.name.contains(param.userText)
                }
            }
        }, organizationEmbeddedStringConverter)

    }

    private void initCRUDButtons() {
        btSave.disableProperty().bind((isWelderChangedProperty.not()) | welderDTOProperty.isNull())
        btDelete.disableProperty().bind(welderDTOProperty.isNull())
    }

    private void initCbEducation() {
        educationDTORepository.loadEducations()
        cbEducation.itemsProperty().bind(educationDTORepository.allEducationProperty)
    }

    private void initCbQualification() {
        qualificationRepository.loadQualifications()
        cbQualification.itemsProperty().bind(qualificationRepository.qualificationsProperty())
    }

    private void initCbJob() {
        jobDTORepository.loadAllDTO()
        cbJob.itemsProperty().bind(jobDTORepository.jobsNameListProperty())
    }



    @FXML
    void refresh(ActionEvent event) {
        LOGGER.debug("ActionEvent performed ${event.eventType} on ${event.source.class.name}")
        welderTableController.refreshTable()
        refreshWelderPane()
    }


    void loadWelder() {
        WelderDTO welderDTO = loadingWelderByIdService.getValue()
        loadWelderOnPane(welderDTO)
    }

    void loadNewWelder(){
        loadWelderOnPane(WelderDTO.defaultWelderDTO())
    }

    private void loadWelderOnPane(WelderDTO welderDTO){
        LOGGER.debug("--- BEGIN loadWelder ---")
        removeListeners()
        if (welderDTO == null) {
            LOGGER.warn('Cannot load null welderDTO')
            clearWelderPane()
            return
        }
        // welderTableController.selectWelderById(welderDTO.id)
        LOGGER.debug("welderDTO has already loaded")
        Printer.logDTO(WelderDTO.class, welderDTO)
        welderDTOProperty.set(welderDTO)
        bufferedWelderDTOProperty.set(welderDTO)
        addListeners()
        bindWelderDTO()
    }

    void clearWelderPane(){
        init()
    }

    private void bindWelderDTO(){
        LOGGER.debug("Trying to bind welderDTO to UI")
        WelderDTO welderDTO = welderDTOProperty.get()
        isWelderChangedProperty.bind(welderDTO.isWelderDTOChangedProperty())
        txfName.textProperty().bindBidirectional(welderDTO.nameProperty)
        txfSecname.textProperty().bindBidirectional(welderDTO.secondNameProperty)
        txfSurname.textProperty().bindBidirectional(welderDTO.surnameProperty)
        dpBirthday.valueProperty().bindBidirectional(welderDTO.birthdayProperty)
        txfDocNumber.textProperty().bindBidirectional(welderDTO.documentNumberProperty)
        dpDateBegin.valueProperty().bindBidirectional(welderDTO.dateBeginProperty)
        ControlFXUtils.selectItemInCombo(welderDTO.education, cbEducation)
        cbEducation.valueProperty().bindBidirectional(welderDTO.educationProperty)
        ControlFXUtils.selectItemInCombo(welderDTO.qualification, cbQualification)
        cbQualification.valueProperty().bindBidirectional(welderDTO.qualificationProperty)
        ControlFXUtils.selectItemInCombo(welderDTO.job, cbJob)
        cbJob.valueProperty().bindBidirectional(welderDTO.jobProperty)
        txfOrganizationName.textProperty().bindBidirectional(welderDTO.organizationNameProperty)
        txfOrganizationAddress.textProperty().bindBidirectional(welderDTO.organizationAddressProperty)
        txfOrganizationPhone.textProperty().bindBidirectional(welderDTO.organizationPhoneProperty)

        //TODO is it really has to be bound bidirectional
        txfAddress.textProperty().bindBidirectional(welderDTO.addressProperty)
        LOGGER.debug("Trying to bind welderDTO to WelderTableViewDTO")

    }


    private void refreshWelderPane() {
        init()
        welderDTOProperty.set(null)
        ControlFXUtils.clearTextFields(allTextFields)
        dpBirthday.valueProperty().set(null)
        dpDateBegin.valueProperty().set(null)
        cbEducation.valueProperty().set(null)
        cbQualification.valueProperty().set(null)
        cbJob.valueProperty().set(null)
    }


    ObjectProperty<WelderDTO> welderDTOProperty() {
        return welderDTOProperty
    }

    @FXML
    void closeWelderPane(ActionEvent event) {
        LOGGER.debug("ActionEvent performed ${event.eventType} on ${event.source.class.name}")
        mainFrameController.closePane(PaneType.WELDER_PANE)
    }

    @FXML
    void addNewWelder(){
       welderTableController.addNew()
    }

    @FXML
    void saveWelder(){
        LOGGER.debug("saveWelder: --- BEGIN SAVE WELDER ---")
        WelderDTO savingWelderDTO = welderDTOProperty().get()
        LOGGER.debug("saveWelder: start checking OrganizationDTO")
        //TODO process saving organization with check
        welderDTORepository.saveWelderDTO(savingWelderDTO)
        runLater(welderSavePostProcessing)
        Printer.logDTO(WelderDTO.class, welderDTOProperty.get())
        LOGGER.debug("saveWelder: --- END SAVE WELDER ---")
    }


    private void addListeners() {
        ControlFXUtils.addChangeListenerToTextFields((ChangeListener<String>) textFieldChangeListener,
                allTextFields)
        ControlFXUtils.addChangeListenerToDatePickers((ChangeListener<LocalDate>) datePickerChangeListener,
                allDatePickers)
    }

    private void removeListeners() {
        ControlFXUtils.removeChangeListenerFromTextFields((ChangeListener<String>) textFieldChangeListener,
                allTextFields)
        ControlFXUtils.removeChangeListenerFromDatePickers((ChangeListener<LocalDate>) datePickerChangeListener,
                allDatePickers)
    }

    //Listeners
    private final textFieldChangeListener = { StringProperty stringProperty, String oldValue, String newValue ->
        LOGGER.debug("ChangeListener source: ${stringProperty.class.simpleName} oldValue: ${oldValue} newValue: ${newValue}")
        if (welderDTOProperty.getValue() == null) return
        TableView<WelderTableViewDTO> table = welderTableController.getWeldersTableView()
        ControlFXUtils.refreshTable(table)

        if (stringProperty == txfName.textProperty()) {
            changeStringPropertyOfSelectedItem(table, "name", newValue)
            return
        }
        if (stringProperty == txfSurname.textProperty()) {
            changeStringPropertyOfSelectedItem(table, "surname", newValue)
            return
        }
        if (stringProperty == txfSecname.textProperty()) {
            changeStringPropertyOfSelectedItem(table, "secondName", newValue)
            return
        }
        if (stringProperty == txfOrganizationName.textProperty()){
            onOrganizationSubmit()
            return
        }
    }

    private static void changeStringPropertyOfSelectedItem(TableView<WelderTableViewDTO> tableView, String propertyName, String value){
        if (tableView == null) {
            LOGGER.debug("changeStringPropertyOfSelectedItem: TableView is ${tableView}")
            return}
        if (propertyName == null ) {
            LOGGER.debug("changeStringPropertyOfSelectedItem: propertyName is ${propertyName}")
            return
        }
        WelderTableViewDTO selectedItem = tableView.getSelectionModel().getSelectedItem()
        if (selectedItem == null) {
            LOGGER.debug("changeStringPropertyOfSelectedItem: selectedItem is ${selectedItem}")
            return
        }
        StringProperty property = selectedItem.getStringProperty(propertyName)
        LOGGER.debug("changeStringPropertyOfSelectedItem: property is ${property}; property class is ${property.class}")
        property.set(value)
        LOGGER.debug("changeStringPropertyOfSelectedItem: StringPropetry has changed by value: ${value}. It is ${property}")
        ControlFXUtils.refreshTable(tableView)
    }

    private final datePickerChangeListener = { ObjectProperty<LocalDate> dateObjectProperty, oldValue, LocalDate newValue ->
        LOGGER.debug("ChangeListener source: ${dateObjectProperty.class.simpleName} oldValue: ${oldValue}" +
                " newValue: ${newValue}")
        if (welderDTOProperty.getValue() == null) return
        ControlFXUtils.refreshTable(welderTableController.getWeldersTableView())
        DateTimeFormatter df = DateTimeFormatter.ofPattern(DTOConstants.DATE_FORMAT_PATTERN)
        if (dateObjectProperty == dpBirthday.valueProperty()) {
            changeStringPropertyOfSelectedItem(welderTableController.getWeldersTableView(),
                    'birthday', newValue.format(df))
            return
        }
    }

    private onOrganizationSubmit(){
        //TODO this may be leak performance
        Set<OrganizationEmbedded> organizationEmbeddedSet = welderDTORepository.allOrganizations
        OrganizationEmbedded selectedOrg = organizationEmbeddedSet.find {org ->
            org.name == txfOrganizationName.textProperty().value
        }
        if (selectedOrg != null){
            txfOrganizationAddress.textProperty().set(selectedOrg.address)
            txfOrganizationPhone.textProperty().set(selectedOrg.phone)
        }
    }

    /*private class OrganizationDialog extends Dialog<ButtonType> {
        private final ButtonType SAVE_BUTTON_TYPE = new ButtonType(ControlFXUtils.OK_DIALOG_BUTTON_TEXT,
                ButtonBar.ButtonData.OK_DONE)
        private final ButtonType CANCEL_BUTTON_TYPE = new ButtonType(ControlFXUtils.CANCEL_DIALOG_BUTTON_TEXT,
                ButtonBar.ButtonData.CANCEL_CLOSE)

        private ObjectProperty<TextField> txfNameProperty = new SimpleObjectProperty<>()
        private ObjectProperty<TextField> txfAddressProperty = new SimpleObjectProperty<>()
        private ObjectProperty<TextField> txfPhoneProperty = new SimpleObjectProperty<>()
        private ObjectProperty<OrganizationDTO> organizationDTOObjectProperty = new SimpleObjectProperty<>()

        OrganizationDialog(OrganizationDTO organizationDTO) {
            Parent parent = fxmlLoaderProvider.loadParent(PaneType.ORGANIZATION_DIALOG_PANE, false)
            final DialogPane dialogPane = getDialogPane()
            dialogPane.contentProperty().set(parent)
            setTitle('Сохранить организацию')
            dialogPane.buttonTypes.addAll(CANCEL_BUTTON_TYPE, SAVE_BUTTON_TYPE)
            GridPane mainGridPane = (GridPane) ((BorderPane) parent).center
            organizationDTOObjectProperty.set(organizationDTO)

            mainGridPane.children.stream().forEach({ node ->
                switch (node.id) {
                    case 'txfName': txfNameProperty.set((TextField) node)
                        break
                    case 'txfAddress': txfAddressProperty.set((TextField) node)
                        break
                    case 'txfPhone': txfPhoneProperty.set((TextField) node)
                        break
                }
            })
            txfNameProperty.getValue().textProperty()
                    .bindBidirectional(organizationDTOObjectProperty.get().nameProperty())
            txfAddressProperty.getValue().textProperty()
                    .bindBidirectional(organizationDTOObjectProperty.get().addressProperty())
            txfPhoneProperty.getValue().textProperty()
                    .bindBidirectional(organizationDTOObjectProperty.get().phoneProperty())

        }

        ObjectProperty<OrganizationDTO> organizationDTOObjectProperty() {
            organizationDTOObjectProperty
        }
    }

     */

    private Runnable welderSavePostProcessing = {
            saveWelderDTOService.stateProperty().addListener(new ChangeListener<Worker.State>() {
                @Override
                void changed(ObservableValue<? extends Worker.State> observable, Worker.State oldValue, Worker.State newValue) {
                    LOGGER.debug("saveWelderDTO Change Listener class: ${getClass()}: State is: ${newValue}")
                    if (newValue == Worker.State.SUCCEEDED)  {
                        LOGGER.debug("welderSavePostProcessing : selecting saved WelderDTO with id: ${saveWelderDTOService.valueProperty().get().id}")
                        welderTableController.selectWelderById(saveWelderDTOService.valueProperty().get().id)
                    }
                    if (newValue == Worker.State.SUCCEEDED || newValue == Worker.State.FAILED)  {
                        saveWelderDTOService.stateProperty().removeListener(this)
                    }
                }
            })
    }


}
