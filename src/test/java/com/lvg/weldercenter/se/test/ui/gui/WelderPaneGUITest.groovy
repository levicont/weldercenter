package com.lvg.weldercenter.se.test.ui.gui

import com.lvg.weldercenter.se.ui.dto.WelderTableViewDTO
import javafx.scene.control.Button
import javafx.scene.control.DatePicker
import javafx.scene.control.TableView
import javafx.scene.control.TextField
import org.junit.Test

import java.time.LocalDate
import java.util.concurrent.TimeUnit


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
        DatePicker dpDateBegin = (DatePicker)find(WELDER_PANE_DP_WELDER__DATE_BEGIN_ID)


        assert txfName.text == 'Иван'
        assert txfSurname.text == 'Иванов'
        assert txfSecname.text == 'Иванович'
        assert txfAddress.text == 'Харьков'
        assert txfDocNumber.text == '17-005'
        assert dpBirthday.value == LocalDate.of(2000,1,1)
        assert dpDateBegin.value == LocalDate.of(2000,1,1)

        Button btSave = (Button)find(WELDER_PANE_BUTTON_SAVE_ID)
        testSaveButtonBehavior(btSave, txfName, txfSurname, txfSecname, txfDocNumber, txfAddress)

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

}
