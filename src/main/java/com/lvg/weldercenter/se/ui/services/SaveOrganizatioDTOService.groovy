package com.lvg.weldercenter.se.ui.services

import com.lvg.weldercenter.se.ui.dto.OrganizationDTO
import com.lvg.weldercenter.se.ui.tasks.organization.SaveOrganizationTask
import javafx.concurrent.Service
import javafx.concurrent.Task
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.stereotype.Component

@Component
class SaveOrganizatioDTOService extends Service<OrganizationDTO> implements OnceStartedService{

    @Autowired
    ConfigurableApplicationContext ctx

    private boolean startedOnce = false
    private OrganizationDTO organizationDTO

    void setOrganizationDTO(OrganizationDTO organizationDTO){
        this.organizationDTO = organizationDTO
    }



    @Override
    protected Task<OrganizationDTO> createTask() {
        if (organizationDTO == null){
            throw new NullPointerException("OrganizationDTO cannot be null during saving")
        }
        return ctx.getBean(SaveOrganizationTask.class, organizationDTO)
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
