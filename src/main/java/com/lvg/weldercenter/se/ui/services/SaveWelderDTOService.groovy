package com.lvg.weldercenter.se.ui.services

import com.lvg.weldercenter.se.ui.dto.WelderDTO
import com.lvg.weldercenter.se.ui.tasks.welders.SaveWelderTask
import javafx.concurrent.Service
import javafx.concurrent.Task
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.stereotype.Component

@Component
class SaveWelderDTOService extends Service<WelderDTO> implements OnceStartedService{

    @Autowired
    ConfigurableApplicationContext ctx

    private boolean startedOnce = false
    private WelderDTO welderDTO

    void setWelderDTO(WelderDTO welderDTO){
        this.welderDTO = welderDTO
    }



    @Override
    protected Task<WelderDTO> createTask() {
        if (welderDTO == null){
            throw new NullPointerException("WelderDTO cannot be null during saving")
        }
        return ctx.getBean(SaveWelderTask.class, welderDTO)
    }

    @Override
    boolean isStartedOnce() {
        return startedOnce
    }

    @Override
    void setStartedOnceFlag(boolean startedOnce) {
        this.startedOnce = startedOnce
    }
}
