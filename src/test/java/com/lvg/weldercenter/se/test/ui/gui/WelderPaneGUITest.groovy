package com.lvg.weldercenter.se.test.ui.gui

import com.lvg.weldercenter.se.ui.dto.WelderTableViewDTO
import javafx.scene.control.Button
import javafx.scene.control.TableView
import javafx.scene.control.TextField
import org.junit.Test



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

        assert txfName.text == 'Иван'
        assert txfSurname.text == 'Иванов'
        assert txfSecname.text == 'Иванович'
        assert txfAddress.text == 'Харьков'
        assert txfDocNumber.text == '17-005'

        Button btSave = (Button)find(WELDER_PANE_BUTTON_SAVE_ID)
        assert btSave.isDisabled()
        txfName.text = 'Ива'
        assert !btSave.isDisabled()
        txfName.text = 'Иван'
        assert btSave.isDisabled()







    }


}
