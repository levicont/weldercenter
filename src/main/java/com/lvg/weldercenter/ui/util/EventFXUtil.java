package com.lvg.weldercenter.ui.util;

import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

/**
 * Created by Victor Levchenko LVG Corp. on 26.02.15.
 */
public class EventFXUtil {
    private static MouseEvent mouseClickEvent = new MouseEvent(MouseEvent.MOUSE_CLICKED,
            0,0, 0, 0, MouseButton.PRIMARY, 1,
            true, true, true, true,true, true, true, true, true, true, null);

    public static MouseEvent getMouseClickEvent(){
        return mouseClickEvent;
    }
}
