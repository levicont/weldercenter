package com.lvg.weldercenter.se.test.ui.gui

import javafx.scene.layout.BorderPane
import org.junit.Test

import static org.testfx.api.FxAssert.verifyThat

class MainFrameGUITest extends GenericGUITest {

    @Test
    void btShowWeldersPaneTest(){
        clickOn(MAIN_PANE_WELDERS_BUTTON_ID)
        verifyThat(WELDER_PANE_ID, { BorderPane pane ->
            return pane.isVisible()
        })
        clickOn(WELDER_PANE_CLOSE_BUTTON_ID)
        verifyThat(MAIN_PANE_LOGO_PANE_ID, {BorderPane pane ->
            return pane.isVisible()
        })
        verifyThat(WELDER_PANE_ID, {BorderPane pane ->
            return pane==null
        })

    }

    @Test
    void miShowWeldersPaneTest(){
        clickOn(MAIN_MENU_DATA_TEXT)

        clickOn(MAIN_MENU_DATA_WELDERS_TEXT)

        verifyThat(WELDER_PANE_ID, {BorderPane pane ->
            return pane.isVisible()
        })
        clickOn(WELDER_PANE_CLOSE_BUTTON_ID)
        verifyThat(MAIN_PANE_LOGO_PANE_ID, {BorderPane pane ->
            return pane.isVisible()
        })
        verifyThat(WELDER_PANE_ID, {BorderPane pane ->
            return pane==null
        })

    }

    @Test
    void exitTest(){
        clickOn(MAIN_MENU_DATA_TEXT)
        clickOn(MAIN_MENU_EXIT_TEXT)
        clickOn(DIALOG_CANCEL_BUTTON_TEXT)
    }

}
