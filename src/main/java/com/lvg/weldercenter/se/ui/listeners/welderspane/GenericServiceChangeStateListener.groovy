package com.lvg.weldercenter.se.ui.listeners.welderspane

import com.lvg.weldercenter.se.ui.controllers.MainFrameController
import com.lvg.weldercenter.se.ui.repositories.WelderDTORepository
import javafx.beans.value.ChangeListener
import javafx.concurrent.Worker
import javafx.stage.Stage
import org.springframework.beans.factory.annotation.Autowired

abstract class GenericServiceChangeStateListener implements ChangeListener<Worker.State>{
    @Autowired
    protected WelderDTORepository weldersRepository

    @Autowired
    protected MainFrameController mainFrameController

    protected Stage loadingView

    protected static boolean isShowingState(Worker.State state){
        switch(state) {
            case Worker.State.SUCCEEDED: return false
            case Worker.State.RUNNING: return true
            case Worker.State.SCHEDULED: return false
            case Worker.State.CANCELLED: return false
            case Worker.State.FAILED: return false
        }
        return false
    }
}
