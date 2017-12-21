package com.lvg.weldercenter.se.test.ui.gui

import com.lvg.weldercenter.se.models.Organization
import com.lvg.weldercenter.se.ui.dto.OrganizationDTO
import com.lvg.weldercenter.se.ui.dto.WelderTableViewDTO
import javafx.scene.control.Button
import javafx.scene.control.ComboBox
import javafx.scene.control.DatePicker
import javafx.scene.control.TableView
import javafx.scene.control.TextField
import org.junit.Test

import java.time.LocalDate

class WelderPaneGUITest extends GenericGUITest {


    @Test
    void selectWelderTest(){
        clickOn(MAIN_MENU_DATA_TEXT)
        clickOn(MAIN_MENU_DATA_WELDERS_TEXT)
        TableView<WelderTableViewDTO> tableView = (TableView<WelderTableViewDTO>)find(WELDER_PANE_TABLE_VIEW_ID)

        assert tableView != null
        assert tableView instanceof  TableView<WelderTableViewDTO>
        assert tableView.items.size() == 4
        clickOn('1')
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
        assert cbJob.value == 'сварщик'
        assert cbOrganization.value == new OrganizationDTO(new Organization(id: 100,
                                                            name: 'IBM',
                                                            address: 'Michigan city',
                                                            phone: '100-123-456'))

        Button btSave = (Button)find(WELDER_PANE_BUTTON_SAVE_ID)
        testSaveButtonBehavior(btSave, txfName, txfSurname, txfSecname, txfDocNumber, txfAddress)
        testSaveButtonBehaviorDatePickers(btSave, dpBirthday, dpDateBegin)
        testSaveButtonBehaviorComboBoxes(btSave, cbJob, cbQualification, cbEducation)
        testSaveButtonBehaviorComboBoxes(btSave, cbOrganization)
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
        T changedData = (T)(T.class == OrganizationDTO.class ? new OrganizationDTO(new Organization()): "Changed")
        comboBoxes.each {ComboBox<T> comboBox ->
            def unchanged = comboBox.value
            assert saveButton != null
            assert saveButton.isDisabled()
            comboBox.value = changedData
            assert !saveButton.isDisabled()
            comboBox.value = unchanged
            assert saveButton.isDisabled()
        }
    }

}
