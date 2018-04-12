package com.lvg.weldercenter.se.ui.services

import com.lvg.weldercenter.se.ui.dto.WelderDTO
import com.lvg.weldercenter.se.ui.tasks.welders.GetWelderByIdTask
import javafx.concurrent.Service
import javafx.concurrent.Task
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.stereotype.Component

@Component
class LoadingWelderByIdService extends Service<WelderDTO>implements OnceStartedService{

    @Autowired
    ConfigurableApplicationContext ctx

    private boolean startedOnce = false



    @Override
    protected Task<WelderDTO> createTask() {
        return ctx.getBean(GetWelderByIdTask.class)
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
