package com.lvg.weldercenter.se.ui.services

import com.lvg.weldercenter.se.ui.dto.OrganizationDTO
import com.lvg.weldercenter.se.ui.tasks.organization.SaveOrganizationTask
import javafx.concurrent.Task
import org.springframework.stereotype.Component

@Component
class SaveOrganizationDTOService extends ParameterUIService<OrganizationDTO, OrganizationDTO>{

    @Override
    protected Task<OrganizationDTO> getTask() {
        return ctx.getBean(SaveOrganizationTask.class, parameter)
    }
}
