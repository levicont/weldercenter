package com.lvg.weldercenter.se.ui.controllers

import com.lvg.weldercenter.se.ui.converters.OrganizationDTOStringConverter
import com.lvg.weldercenter.se.ui.dto.OrganizationDTO
import com.lvg.weldercenter.se.ui.dto.WelderDTO
import com.lvg.weldercenter.se.ui.repositories.EducationDTORepository
import com.lvg.weldercenter.se.ui.repositories.JobDTORepository
import com.lvg.weldercenter.se.ui.repositories.OrganizationDTORepository
import com.lvg.weldercenter.se.ui.repositories.QualificationDTORepository
import com.lvg.weldercenter.se.ui.utils.ControlFXUtils
import javafx.beans.property.*
import javafx.beans.value.ChangeListener
import javafx.collections.FXCollections
import javafx.collections.ObservableList
import javafx.collections.transformation.FilteredList
import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.Parent
import javafx.scene.control.*
import javafx.scene.layout.BorderPane
import javafx.scene.layout.GridPane
import javafx.util.Callback
import org.apache.log4j.Logger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

import java.time.LocalDate

@Component
class WelderController implements Initializable {
    private static final Logger LOGGER = Logger.getLogger(WelderController.class)
    @Autowired
    MainFrameController mainFrameController
    @Autowired
    FXMLLoaderProvider fxmlLoaderProvider

    @Autowired
    OrganizationDTOStringConverter organizationDTOStringConverter

    @Autowired
    WelderTableController welderTableController
    @Autowired
    EducationDTORepository educationDTORepository
    @Autowired
    QualificationDTORepository qualificationRepository
    @Autowired
    JobDTORepository jobDTORepository
    @Autowired
    OrganizationDTORepository organizationDTORepository

    private final ObjectProperty<WelderDTO> welderDTOProperty = new SimpleObjectProperty<>()


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
    private ComboBox<OrganizationDTO> cbOrganization
    @FXML
    private ComboBox<String> cbJob
    @FXML
    private TextField txfAddress

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
        initCbOrganization()
        initCRUDButtons()
        allTextFields = [txfName, txfSurname, txfSecname, txfDocNumber, txfAddress]
        allDatePickers = [dpDateBegin, dpBirthday]
        welderDetailsPane.disableProperty().bind(welderDTOProperty.isNull())

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

    private void initCbOrganization() {
        organizationDTORepository.loadAllDTO()
        cbOrganization.converterProperty().set(organizationDTOStringConverter)
        LOGGER.debug("--- INIT ORGANIZATION DTO COMBO BOX: allOrganization size: ${organizationDTORepository.allDTO.value.size()}")
        cbOrganization.setItems(organizationDTORepository.allDTO)
        //cbOrganization.itemsProperty().bind(organizationDTORepository.allDTO)
    }


    @FXML
    void refresh(ActionEvent event) {
        LOGGER.debug("ActionEvent performed ${event.eventType} on ${event.source.class.name}")
        welderTableController.refreshTable()
        refreshWelderPane()
    }


    void loadWelder(WelderDTO welderUI) {
        removeListeners()
        if (welderUI == null) {
            LOGGER.warn('Cannot load null welderUI')
            return
        }
        welderDTOProperty.set(welderUI)
        bufferedWelderDTOProperty.set(welderUI)
        addListeners()
        txfName.textProperty().bindBidirectional(welderUI.nameProperty)
        isWelderChangedProperty.set(false)
        txfSecname.textProperty().bindBidirectional(welderUI.secondNameProperty)
        txfSurname.textProperty().bindBidirectional(welderUI.surnameProperty)
        dpBirthday.valueProperty().bindBidirectional(welderUI.birthdayProperty)
        txfDocNumber.textProperty().bindBidirectional(welderUI.documentNumberProperty)
        dpDateBegin.valueProperty().bindBidirectional(welderUI.dateBeginProperty)
        ControlFXUtils.selectItemInCombo(welderUI.education, cbEducation)
        cbEducation.valueProperty().bindBidirectional(welderUI.educationProperty)
        ControlFXUtils.selectItemInCombo(welderUI.qualification, cbQualification)
        cbQualification.valueProperty().bindBidirectional(welderUI.qualificationProperty)
        ControlFXUtils.selectItemInCombo(welderUI.job, cbJob)
        cbJob.valueProperty().bindBidirectional(welderUI.jobProperty)
        ControlFXUtils.selectItemInCombo(welderUI.organizationDTO, cbOrganization)
        //TODO is it really has to be bound bidirectional
        cbOrganization.valueProperty().bindBidirectional(welderUI.organizationProperty)
        txfAddress.textProperty().bindBidirectional(welderUI.addressProperty)


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
        cbOrganization.valueProperty().set(null)
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
    void saveOrganization(ActionEvent event) {
        //Show organization dialog
        OrganizationDialog dialog = new OrganizationDialog(cbOrganization.valueProperty().getValue())
        Optional<ButtonType> optional = dialog.showAndWait()
        //TODO correct adding organization to combo box when changed another
        //TODO field instead name Save button not enabled;
        switch (optional.get().buttonData) {
            case ButtonBar.ButtonData.OK_DONE:
                LOGGER.debug(" --- saveOrganization option: SAVE chosen \n" +
                        "\t Organization: ${dialog.organizationDTOObjectProperty().getValue()}\n")

                OrganizationDTO organizationDTO = dialog.organizationDTOObjectProperty().getValue()
                LOGGER.debug("Received organizationDTO: ${organizationDTO} with id: ${organizationDTO.getId()} " +
                        "hash:${organizationDTO.hashCode()}\n" +
                        "old organizationDTO: ${welderDTOProperty.get().organizationDTO} " +
                        "hash: ${welderDTOProperty.get().organizationDTO.hashCode()}")

                cbOrganization.editor.text = organizationDTO.name
                welderDTOProperty.value.organizationProperty().set(organizationDTO)

                LOGGER.debug("OrganizationDTO added to combo box. value: ${cbOrganization.value} " +
                        "id:${cbOrganization.value.getId()}")
                break
            case ButtonBar.ButtonData.CANCEL_CLOSE:
                LOGGER.debug("--- saveOrganization option: CANCEL chosen")
                break
        }
    }

    private void addListeners() {
        ControlFXUtils.addChangeListenerToTextFields((ChangeListener<String>) textFieldChangeListener,
                allTextFields)
        ControlFXUtils.addChangeListenerToDatePickers((ChangeListener<LocalDate>) datePickerChangeListener,
                allDatePickers)
        ControlFXUtils.addChangeListenerToComboBoxes((ChangeListener<String>) comboBoxChangeStringListener,
                cbJob, cbQualification, cbEducation)
        ControlFXUtils.addChangeListenerToComboBoxes((ChangeListener<OrganizationDTO>) comboBoxChangeOrganizationListener,
                cbOrganization)
        ControlFXUtils.addChangeListenerToTextFields((ChangeListener<String>) comboBoxChangeEditorOrganizationListener,
                cbOrganization.editor)
    }

    private void removeListeners() {
        ControlFXUtils.removeChangeListenerFromTextFields((ChangeListener<String>) textFieldChangeListener,
                allTextFields)
        ControlFXUtils.removeChangeListenerFromDatePickers((ChangeListener<LocalDate>) datePickerChangeListener,
                allDatePickers)
        ControlFXUtils.removeChangeListenerFromComboBoxes((ChangeListener<String>) comboBoxChangeStringListener,
                cbJob, cbQualification, cbEducation)
        ControlFXUtils.removeChangeListenerFromComboBoxes((ChangeListener<OrganizationDTO>) comboBoxChangeOrganizationListener,
                cbOrganization)
        ControlFXUtils.removeChangeListenerFromTextFields((ChangeListener<String>) comboBoxChangeEditorOrganizationListener,
                cbOrganization.editor)
    }

    //Listeners
    private final textFieldChangeListener = { StringProperty stringProperty, String oldValue, String newValue ->
        LOGGER.debug("ChangeListener source: ${stringProperty.class.simpleName} oldValue: ${oldValue} newValue: ${newValue}")
        if (welderDTOProperty.getValue() == null) return
        String trimmedNewValue = newValue.trim()
        if (stringProperty == txfName.textProperty()) {
            isWelderChangedProperty.set(trimmedNewValue != welderDTOProperty.get().originalWelderProperty().get().name)
            return
        }
        if (stringProperty == txfSurname.textProperty()) {
            isWelderChangedProperty.set(trimmedNewValue != welderDTOProperty.get().originalWelderProperty().get().surname)
            return
        }
        if (stringProperty == txfSecname.textProperty()) {
            isWelderChangedProperty.set(trimmedNewValue != welderDTOProperty.get().originalWelderProperty().get().secondName)
            return
        }
        if (stringProperty == txfDocNumber.textProperty()) {
            isWelderChangedProperty.set(trimmedNewValue != welderDTOProperty.get().originalWelderProperty().get().documentNumber)
            return
        }
        if (stringProperty == txfAddress.textProperty()) {
            isWelderChangedProperty.set(trimmedNewValue != welderDTOProperty.get().originalWelderProperty().get().address)
            return
        }
    }

    private final datePickerChangeListener = { ObjectProperty<LocalDate> dateObjectProperty, oldValue, newValue ->
        LOGGER.debug("ChangeListener source: ${dateObjectProperty.class.simpleName} oldValue: ${oldValue}" +
                " newValue: ${newValue}")
        if (welderDTOProperty.getValue() == null) return

        if (dateObjectProperty == dpBirthday.valueProperty()) {
            isWelderChangedProperty.set(newValue != welderDTOProperty.get().originalWelderProperty().get().birthday)
            return
        }
        if (dateObjectProperty == dpDateBegin.valueProperty()) {
            isWelderChangedProperty.set(newValue != welderDTOProperty.get().originalWelderProperty().get().dateBegin)
            return
        }
    }

    private final comboBoxChangeStringListener = { ObjectProperty<String> stringObjectProperty,
                                                   String oldValue, String newValue ->
        LOGGER.debug("ChangeListener source: ${stringObjectProperty.class.simpleName} oldValue: ${oldValue} newValue: ${newValue}")
        if (welderDTOProperty.getValue() == null) return

        if (stringObjectProperty == cbEducation.valueProperty()) {
            isWelderChangedProperty.set(newValue != welderDTOProperty.get().originalWelderProperty().get().education)
            return
        }
        if (stringObjectProperty == cbQualification.valueProperty()) {
            isWelderChangedProperty.set(newValue != welderDTOProperty.get().originalWelderProperty().get().qualification)
            return
        }
        if (stringObjectProperty == cbJob.valueProperty()) {
            isWelderChangedProperty.set(newValue != welderDTOProperty.get().originalWelderProperty().get().job)
            return
        }
    }

    private final comboBoxChangeOrganizationListener = { ObjectProperty<OrganizationDTO> orgDTOObjectProperty,
                                                         OrganizationDTO oldValue, OrganizationDTO newValue ->
        LOGGER.debug("ChangeListener source: ${orgDTOObjectProperty.class.simpleName} oldValue: ${oldValue} newValue: ${newValue}")
        if (welderDTOProperty.getValue() == null) return
        if (newValue == null) return

        if (orgDTOObjectProperty == cbOrganization.valueProperty()) {
            isWelderChangedProperty.set(newValue.organization != welderDTOProperty.get().originalWelderProperty().get().organization)
        }


    }
    private final comboBoxChangeEditorOrganizationListener = { StringProperty textProperty,
                                                               String oldValue, String newValue ->
        if (!cbOrganization.isFocused()) return
        LOGGER.debug("ChangeListener source: ${textProperty.class.simpleName} oldValue: ${oldValue} newValue: ${newValue}")

        if (newValue == null || newValue.isEmpty() ) {
            organizationDTORepository.setFilterPredicate({e -> true})
            return
        }

        organizationDTORepository.setFilterPredicate({org ->
            if (org == null || org.name.isEmpty())
                return true
            String lowerCaseName = newValue.toLowerCase()
            if (org.name.toLowerCase() == lowerCaseName )
                return true
            if (org.name.toLowerCase().contains(lowerCaseName))
                return true

            return false
        })
        LOGGER.debug(" --comboBoxChangeEditorOrganizationListener filteredList: ${organizationDTORepository.allDTO}")

        if (!cbOrganization.isShowing()) {
            cbOrganization.show()
        }

    }


    private class OrganizationDialog extends Dialog<ButtonType> {
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


}
