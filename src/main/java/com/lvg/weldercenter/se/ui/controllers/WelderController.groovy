package com.lvg.weldercenter.se.ui.controllers

import com.lvg.weldercenter.se.ui.converters.EducationStringConverter
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
import javafx.scene.layout.BorderPane
import javafx.scene.layout.GridPane
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
        welderDetailsPane.disableProperty().bind(welderDTOProperty.isNull())

    }

    private void initCbEducation(){
        educationDTORepository.loadEducations()
        cbEducation.itemsProperty().bind(educationDTORepository.allEducationProperty)
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
        txfName.textProperty().bindBidirectional(welderUI.nameProperty)
        txfSecname.textProperty().bindBidirectional(welderUI.secondNameProperty)
        txfSurname.textProperty().bindBidirectional(welderUI.surnameProperty)
        dpBirthday.valueProperty().bindBidirectional(welderUI.birthdayProperty)
        txfDocNumber.textProperty().bindBidirectional(welderUI.documentNumberProperty)
        dpDateBegin.valueProperty().bindBidirectional(welderUI.dateBeginProperty)
        selectEducation(welderUI.education)
        cbEducation.valueProperty().bindBidirectional(welderUI.educationProperty)

        txfAddress.textProperty().bindBidirectional(welderUI.addressProperty)


    }

    private void selectEducation(String education){
        if(education == null || education.isEmpty())
            return
        String selectedEducation = null

        cbEducation.items.each {edu ->
            if(edu == education){
                selectedEducation = edu
                return
            }
        }
        if (selectedEducation == null){
            selectedEducation = education
        }
        cbEducation.selectionModel.select(selectedEducation)


    }


    private void refreshWelderPane(){
        init()
        welderDTOProperty.set(null)
        ControlFXUtils.clearTextFields(txfName, txfSurname, txfSecname, txfAddress, txfDocNumber)
        dpBirthday.valueProperty().set(null)
        dpDateBegin.valueProperty().set(null)
        cbEducation.valueProperty().set(null)

    }

    ObjectProperty<WelderDTO> welderDTOProperty(){
        return welderDTOProperty
    }

    @FXML
    void closeWelderPane(ActionEvent event) {
        mainWelderPane.setVisible(false)
    }



}
