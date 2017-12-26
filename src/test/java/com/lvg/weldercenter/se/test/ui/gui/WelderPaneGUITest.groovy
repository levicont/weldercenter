package com.lvg.weldercenter.se.test.ui.gui

import com.lvg.weldercenter.se.models.Organization
import com.lvg.weldercenter.se.ui.dto.OrganizationDTO
import com.lvg.weldercenter.se.ui.dto.WelderTableViewDTO
import com.lvg.weldercenter.se.ui.utils.ControlFXUtils
import javafx.scene.control.*
import javafx.scene.layout.GridPane
import org.apache.log4j.Logger
import org.junit.Test

import java.time.LocalDate

class WelderPaneGUITest extends GenericGUITest {
    private static final Logger LOGGER = Logger.getLogger(WelderPaneGUITest.class)
    private static final String FIRST_ROW_IN_TABLE_NUMBER = '1'

    @Test
    void selectWelderTest(){
        clickOn(MAIN_MENU_DATA_TEXT)
        clickOn(MAIN_MENU_DATA_WELDERS_TEXT)
        TableView<WelderTableViewDTO> tableView = (TableView<WelderTableViewDTO>)find(WELDER_PANE_TABLE_VIEW_ID)

        assert tableView != null
        assert tableView instanceof  TableView<WelderTableViewDTO>
        assert tableView.items.size() == 4
        clickOn(FIRST_ROW_IN_TABLE_NUMBER)
        TextField txfName = (TextField)find(WELDER_PANE_TXF_WELDER_NAME_ID)
        TextField txfSurname = (TextField)find(WELDER_PANE_TXF_WELDER_SURNAME_ID)
        TextField txfSecname = (TextField)find(WELDER_PANE_TXF_WELDER_SECOND_NAME_ID)
        TextField txfAddress = (TextField)find(WELDER_PANE_TXF_WELDER_ADDRESS_ID)
        TextField txfDocNumber = (TextField)find(WELDER_PANE_TXF_WELDER_DOCUMENT_NUMBER_ID)
        DatePicker dpBirthday = (DatePicker)find(WELDER_PANE_DP_WELDER_BIRTHDAY_ID)
        DatePicker dpDateBegin = (DatePicker)find(WELDER_PANE_DP_WELDER_DATE_BEGIN_ID)
        ComboBox<String> cbJob = (ComboBox<String>)find(WELDER_PANE_CB_JOB_ID)
        ComboBox<String> cbEducation = (ComboBox<String>)find(WELDER_PANE_CB_EDUCATION_ID)
        ComboBox<String> cbQualification = (ComboBox<String>)find(WELDER_PANE_CB_QUALIFICATION_ID)
        ComboBox<OrganizationDTO> cbOrganization = (ComboBox<OrganizationDTO>)find(WELDER_PANE_CB_ORGANIZATION_ID)


        assert txfName.text == 'Иван'
        assert txfSurname.text == 'Иванов'
        assert txfSecname.text == 'Иванович'
        assert txfAddress.text == 'Харьков'
        assert txfDocNumber.text == '17-005'
        assert dpBirthday.value == LocalDate.of(1984,12,20)
        assert dpDateBegin.value == LocalDate.of(2000,1,1)
        assert cbEducation.value == 'среднее-специальное'
        assert cbQualification.value == 'электросварщик'
        assert cbJob.value == 'электросварщик'
        assert cbOrganization.value == getTestOrganizationDTO()

        Button btSave = (Button)find(WELDER_PANE_BUTTON_SAVE_ID)
        testSaveButtonBehavior(btSave, txfName, txfSurname, txfSecname, txfDocNumber, txfAddress)
        testSaveButtonBehaviorDatePickers(btSave, dpBirthday, dpDateBegin)
        testSaveButtonBehaviorComboBoxes(btSave, cbJob,  cbEducation, cbQualification)
        testSaveButtonBehaviorComboBoxes(btSave, cbOrganization)
   }

    @Test
    void updateOrganizationTest(){
        clickOn(MAIN_MENU_DATA_TEXT)
        clickOn(MAIN_MENU_DATA_WELDERS_TEXT)
        TableView<WelderTableViewDTO> tableView = (TableView<WelderTableViewDTO>)find(WELDER_PANE_TABLE_VIEW_ID)

        assert tableView != null
        assert tableView instanceof  TableView<WelderTableViewDTO>
        assert tableView.items.size() == 4
        clickOn(FIRST_ROW_IN_TABLE_NUMBER)
        ComboBox<OrganizationDTO> cbOrganization = (ComboBox<OrganizationDTO>)find(WELDER_PANE_CB_ORGANIZATION_ID)
        assert cbOrganization != null
        assert cbOrganization.value == getTestOrganizationDTO()
        clickOn(WELDER_PANE_BT_SAVE_ORGANIZATION_ID)
        GridPane orgDialogPane = (GridPane)find(ORGANIZATION_DIALOG_PANE_ID)
        TextField txfName = (TextField)find(ORGANIZATION_DIALOG_TXF_NAME_ID)
        TextField txfAddress = (TextField)find(ORGANIZATION_DIALOG_TXF_ADDRESS_ID)
        TextField txfPhone = (TextField)find(ORGANIZATION_DIALOG_TXF_PHONE_ID)

        assert orgDialogPane != null
        assert orgDialogPane.isVisible()
        assert txfName != null
        assert txfName.text == cbOrganization.value.name
        assert txfAddress != null
        assert txfAddress.text == cbOrganization.value.address
        assert txfPhone != null
        assert txfPhone.text == cbOrganization.value.phone
        clickOn(ControlFXUtils.CANCEL_DIALOG_BUTTON_TEXT)
        assert !orgDialogPane.isVisible()



    }

    private static void testSaveButtonBehavior(Button saveButton, TextField... textFields){
        def changedText = 'Changed'
        textFields.each {TextField txf ->
            def unchanged = txf.text
            assert saveButton != null
            assert saveButton.isDisabled()
            txf.text = changedText
            assert !saveButton.isDisabled()
            txf.text = unchanged
            assert saveButton.isDisabled()
        }
    }

    private static void testSaveButtonBehaviorDatePickers(Button saveButton, DatePicker... datePickers){
        def changedDate = LocalDate.now()
        datePickers.each {DatePicker datePicker ->
            def unchanged = datePicker.value
            assert saveButton != null
            assert saveButton.isDisabled()
            datePicker.value = changedDate
            assert !saveButton.isDisabled()
            datePicker.value = unchanged
            assert saveButton.isDisabled()
        }
    }

    private static <T>void testSaveButtonBehaviorComboBoxes(Button saveButton, ComboBox<T>... comboBoxes){
        comboBoxes.each {ComboBox<T> comboBox ->
            switch(T.class){
                case String.class :
                    testSaveButtonBehaviorStringComboBox(saveButton,(ComboBox<String>)comboBox)
                    break
                case OrganizationDTO.class :
                    testSaveButtonBehaviorOrganizationDTOComboBox(saveButton, (ComboBox<OrganizationDTO>) comboBox)
                    break
            }

        }
    }

    private static void testSaveButtonBehaviorStringComboBox(Button saveButton, ComboBox<String> comboBox){
        String changedData = 'Changed'
        def unchanged = comboBox.value
        LOGGER.debug("---- Testing combo box id: ${comboBox.id} ----")
        assert saveButton != null
        assert saveButton.isDisabled()
        comboBox.value = changedData
        assert !saveButton.isDisabled()
        comboBox.value = unchanged
        assert saveButton.isDisabled()
    }

    private static void testSaveButtonBehaviorOrganizationDTOComboBox(Button saveButton, ComboBox<OrganizationDTO> comboBox){
        OrganizationDTO  changedData = getTestOrganizationDTO().setName('Changed')
        def unchanged = comboBox.value
        LOGGER.debug("---- Testing combo box id: ${comboBox.id} ----")
        assert saveButton != null
        assert saveButton.isDisabled()
        comboBox.value = changedData
        assert !saveButton.isDisabled()
        comboBox.value = unchanged
        assert saveButton.isDisabled()
    }


    private static OrganizationDTO getTestOrganizationDTO(){
        return new OrganizationDTO(new Organization(id: 100,
                name: 'IBM',
                address: 'Michigan city',
                phone: '100-123-456'))
    }

}
