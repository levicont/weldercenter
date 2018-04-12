package com.lvg.weldercenter.se.ui.services

import com.lvg.weldercenter.se.ui.dto.OrganizationDTO
import com.lvg.weldercenter.se.ui.tasks.organization.OrganizationTask
import javafx.collections.ObservableList
import javafx.concurrent.Service
import javafx.concurrent.Task
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.stereotype.Component

@Component
class LoadingAllOrganizationsService extends Service<ObservableList<OrganizationDTO>> implements OnceStartedService{

    @Autowired
    ConfigurableApplicationContext ctx


    private boolean isStartedOnce = false


    @Override
    boolean isStartedOnce() {
        return isStartedOnce
    }

    @Override
    void setStartedOnceFlag(boolean startedOnce) {
        this.isStartedOnce = startedOnce
    }

    @Override
    protected Task<ObservableList<OrganizationDTO>> createTask() {
        return ctx.getBean(OrganizationTask.class)
    }
}
