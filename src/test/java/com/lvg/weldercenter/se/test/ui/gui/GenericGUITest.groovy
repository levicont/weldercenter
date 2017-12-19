package com.lvg.weldercenter.se.test.ui.gui

import com.lvg.weldercenter.se.ApplicationFX
import javafx.scene.Node
import javafx.scene.input.KeyCode
import javafx.scene.input.MouseButton
import javafx.stage.Stage
import org.junit.After
import org.junit.BeforeClass
import org.testfx.api.FxToolkit
import org.testfx.framework.junit.ApplicationTest

abstract class GenericGUITest extends ApplicationTest{

    @BeforeClass
    static void setupClass(){
            System.setProperty("testfx.robot", "glass")
            System.setProperty('testfx.setup.timeout', '100000')
            System.setProperty("java.awt.headless", "true")
        launch(ApplicationFX.class)
    }

    @Override
    void start(Stage stage) throws Exception {
        stage.show()
    }

    @After
    void afterEachTest(){
        FxToolkit.hideStage()
        KeyCode[] keyCodes = []
        MouseButton[] mouseButtons = []
        release(keyCodes)
        release(mouseButtons)
    }



    //Helper method
    protected  <T extends Node> T find(final String query){
        return (T) (++lookup(query).queryAll().iterator())
    }
}
