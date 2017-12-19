package com.lvg.weldercenter.se.test.ui.gui

import javafx.scene.layout.BorderPane
import org.junit.Test

import static org.testfx.api.FxAssert.verifyThat

class MainFrameGUITest extends GenericGUITest{

    private static final String MAIN_MENU_DATA_TEXT = 'Данные'
    private static final String MAIN_MENU_DATA_WELDERS_TEXT = 'Сварщики'
    private static final String MAIN_MENU_EXIT_TEXT = 'Выход'



    @Test
    void btShowWeldersPaneTest(){
        clickOn('#btShowWelders')
        verifyThat('#mainWelderPane', { BorderPane pane ->
            return pane.isVisible()
        })
        clickOn('#btCloseWelderPane')
        verifyThat('#logoPane', {BorderPane pane ->
            return pane.isVisible()
        })
        verifyThat('#mainWelderPane', {BorderPane pane ->
            return pane==null
        })

    }

    @Test
    void miShowWeldersPaneTest(){
        clickOn(MAIN_MENU_DATA_TEXT)
        clickOn(MAIN_MENU_DATA_WELDERS_TEXT)
        verifyThat('#mainWelderPane', {BorderPane pane ->
            return pane.isVisible()
        })
        clickOn('#btCloseWelderPane')
        verifyThat('#logoPane', {BorderPane pane ->
            return pane.isVisible()
        })
        verifyThat('#mainWelderPane', {BorderPane pane ->
            return pane==null
        })

    }

    @Test
    void exitTest(){
        clickOn(MAIN_MENU_DATA_TEXT)
        clickOn(MAIN_MENU_EXIT_TEXT)
        clickOn('Cancel')
    }

}
