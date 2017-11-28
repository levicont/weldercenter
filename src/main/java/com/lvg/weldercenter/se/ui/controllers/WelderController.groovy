package com.lvg.weldercenter.se.ui.controllers

import com.lvg.weldercenter.se.models.Education
import com.lvg.weldercenter.se.ui.converters.EducationStringConverter
import com.lvg.weldercenter.se.ui.dto.EducationDTO
import com.lvg.weldercenter.se.ui.dto.WelderDTO
import com.lvg.weldercenter.se.ui.repositories.EducationDTORepository
import com.lvg.weldercenter.se.ui.utils.ControlFXUtils
import javafx.beans.property.ObjectProperty
import javafx.beans.property.SimpleObjectProperty
import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.control.ComboBox
import javafx.scene.control.DatePicker
import javafx.scene.control.TextField
import org.apache.log4j.Logger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class WelderController implements Initializable{
    private static final Logger LOGGER = Logger.getLogger(WelderController.class)
    @Autowired
    WelderTableController welderTableController
    @Autowired
    EducationDTORepository educationDTORepository
    @Autowired
    EducationStringConverter educationStringConverter

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
    private ComboBox<EducationDTO> cbEducation
    @FXML
    private ComboBox<String> cbQualification
    @FXML
    private ComboBox<String> cbOrganization
    @FXML
    private ComboBox<String> cbJob
    @FXML
    private TextField txfAddress

    @Override
    void initialize(URL location, ResourceBundle resources) {
        init()
    }

    private void init(){
        initCbEducation()
    }

    private void initCbEducation(){
        cbEducation.itemsProperty().bind(educationDTORepository.allEducationProperty)
        cbEducation.setConverter(educationStringConverter)
    }

    @FXML
    void refresh(ActionEvent event) {
        welderTableController.refreshTable()
        refreshWelderPane()
    }



    void loadWelder(WelderDTO welderUI){
        if (welderUI == null) {
            LOGGER.warn('Cannot load null welderUI')
            return
        }
        welderDTOProperty.set(welderUI)
        txfName.textProperty().bindBidirectional(welderUI.name)
        txfName.textProperty().set(welderUI.name)
        txfSecname.textProperty().set(welderUI.secondName)
        txfSurname.textProperty().set(welderUI.surname)
        dpBirthday.valueProperty().set(welderUI.birthday)
        txfDocNumber.textProperty().set(welderUI.documentNumber)
        dpDateBegin.valueProperty().set(welderUI.dateBegin)
        selectEducation(welderUI.education)


        txfAddress.textProperty().set(welderUI.address)

    }

    private void selectEducation(String education){
        if(education == null || education.isEmpty())
            return
        EducationDTO selectedEducation = null

        cbEducation.items.each {edu ->
            if(edu.education.education == education){
                selectedEducation = edu
                return
            }
        }
        if (selectedEducation == null){
            selectedEducation = new EducationDTO(new Education(education: education))
        }
        cbEducation.selectionModel.select(selectedEducation)


    }


    private void refreshWelderPane(){
        init()
        ControlFXUtils.clearTextFields(txfName, txfSurname, txfSecname, txfAddress, txfDocNumber)
        dpBirthday.valueProperty().set(null)
        dpDateBegin.valueProperty().set(null)
        cbEducation.valueProperty().set(null)

    }



}
