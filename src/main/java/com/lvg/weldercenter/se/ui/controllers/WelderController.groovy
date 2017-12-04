package com.lvg.weldercenter.se.ui.controllers

import com.lvg.weldercenter.se.ui.dto.WelderDTO
import com.lvg.weldercenter.se.ui.repositories.EducationDTORepository
import com.lvg.weldercenter.se.ui.repositories.JobDTORepository
import com.lvg.weldercenter.se.ui.repositories.QualificationDTORepository
import com.lvg.weldercenter.se.ui.utils.ControlFXUtils
import javafx.beans.property.ObjectProperty
import javafx.beans.property.SimpleObjectProperty
import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.control.ComboBox
import javafx.scene.control.DatePicker
import javafx.scene.control.TextField
import javafx.scene.layout.BorderPane
import javafx.scene.layout.GridPane
import org.apache.log4j.Logger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class WelderController implements Initializable{
    private static final Logger LOGGER = Logger.getLogger(WelderController.class)
    @Autowired
    MainFrameController mainFrameController

    @Autowired
    WelderTableController welderTableController
    @Autowired
    EducationDTORepository educationDTORepository
    @Autowired
    QualificationDTORepository qualificationRepository
    @Autowired
    JobDTORepository jobDTORepository

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
    private ComboBox<String> cbOrganization
    @FXML
    private ComboBox<String> cbJob
    @FXML
    private TextField txfAddress

    @FXML
    BorderPane mainWelderPane
    @FXML
    private GridPane welderDetailsPane

    private WelderDTO bufferedWelderDTO

    @Override
    void initialize(URL location, ResourceBundle resources) {
        init()
    }

    private void init(){
        initCbEducation()
        initCbQualification()
        initCbJob()
        welderDetailsPane.disableProperty().bind(welderDTOProperty.isNull())

    }

    private void initCbEducation(){
        educationDTORepository.loadEducations()
        cbEducation.itemsProperty().bind(educationDTORepository.allEducationProperty)
    }

    private void initCbQualification(){
        qualificationRepository.loadQualifications()
        cbQualification.itemsProperty().bind(qualificationRepository.qualificationsProperty())
    }

    private void initCbJob(){
        jobDTORepository.loadAllDTO()
        cbJob.itemsProperty().bind(jobDTORepository.jobsNameListProperty())
    }

    @FXML
    void refresh(ActionEvent event) {
        LOGGER.debug("ActionEvent performed ${event.eventType} on ${event.source.class.name}")
        welderTableController.refreshTable()
        refreshWelderPane()
    }



    void loadWelder(WelderDTO welderUI){
        if (welderUI == null) {
            LOGGER.warn('Cannot load null welderUI')
            return
        }
        welderDTOProperty.set(welderUI)
        txfName.textProperty().bindBidirectional(welderUI.nameProperty)
        txfSecname.textProperty().bindBidirectional(welderUI.secondNameProperty)
        txfSurname.textProperty().bindBidirectional(welderUI.surnameProperty)
        dpBirthday.valueProperty().bindBidirectional(welderUI.birthdayProperty)
        txfDocNumber.textProperty().bindBidirectional(welderUI.documentNumberProperty)
        dpDateBegin.valueProperty().bindBidirectional(welderUI.dateBeginProperty)
        selectItemInCombo(welderUI.education, cbEducation)
        cbEducation.valueProperty().bindBidirectional(welderUI.educationProperty)
        selectItemInCombo(welderUI.qualification, cbQualification)
        cbQualification.valueProperty().bindBidirectional(welderUI.qualificationProperty)
        selectItemInCombo(welderUI.job, cbJob)
        cbJob.valueProperty().bindBidirectional(welderUI.jobProperty)
        txfAddress.textProperty().bindBidirectional(welderUI.addressProperty)


    }

    private <T> void selectItemInCombo(T item, ComboBox<T> comboBox){
        if (item == null)
            return
        def selectedItem = null
        comboBox.items.each {i ->
            if(i == item){
                selectedItem = i
                return
            }
        }
        if (selectedItem == null){
            selectedItem = item
        }
        comboBox.selectionModel.select(selectedItem)
    }


    private void refreshWelderPane(){
        init()
        welderDTOProperty.set(null)
        ControlFXUtils.clearTextFields(txfName, txfSurname, txfSecname, txfAddress, txfDocNumber)
        dpBirthday.valueProperty().set(null)
        dpDateBegin.valueProperty().set(null)
        cbEducation.valueProperty().set(null)
        cbQualification.valueProperty().set(null)
        cbJob.valueProperty().set(null)

    }

    ObjectProperty<WelderDTO> welderDTOProperty(){
        return welderDTOProperty
    }

    @FXML
    void closeWelderPane(ActionEvent event) {
        LOGGER.debug("ActionEvent performed ${event.eventType} on ${event.source.class.name}")
        mainFrameController.closePane(PaneType.WELDER_PANE)
    }



}
