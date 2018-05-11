package com.lvg.weldercenter.se.ui.services

import com.lvg.weldercenter.se.ui.dto.OrganizationDTO
import com.lvg.weldercenter.se.ui.tasks.organization.OrganizationTask
import javafx.collections.ObservableList
import javafx.concurrent.Task
import org.springframework.stereotype.Component

@Component
class LoadingAllOrganizationsService extends UIService<ObservableList<OrganizationDTO>>{

    @Override
    protected Task<ObservableList<OrganizationDTO>> getTask() {
        return ctx.getBean(OrganizationTask.class)
    }
}
