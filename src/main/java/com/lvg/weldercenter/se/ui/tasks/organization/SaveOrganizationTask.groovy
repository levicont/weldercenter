package com.lvg.weldercenter.se.ui.tasks.organization

import com.lvg.weldercenter.se.exceptions.WelderCenterException
import com.lvg.weldercenter.se.models.Organization
import com.lvg.weldercenter.se.services.OrganizationService
import com.lvg.weldercenter.se.ui.dto.OrganizationDTO
import com.lvg.weldercenter.se.ui.tasks.TaskConstants
import javafx.concurrent.Task
import org.apache.log4j.Logger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component

@Component
@Scope('prototype')
class SaveOrganizationTask extends Task<OrganizationDTO> implements TaskConstants{
    private static final Logger LOGGER = Logger.getLogger(SaveOrganizationTask.class)

    @Autowired
    OrganizationService organizationService

    SaveOrganizationTask(OrganizationDTO organizationDTO){
        super()
        this.organizationDTO = organizationDTO
    }

    private OrganizationDTO organizationDTO


    @Override
    protected OrganizationDTO call() throws Exception {
        try {
            updateTitle(SAVE_ORGANIZATION_DTO_TASK_TITLE_MESSAGE)
            Organization organization  = organizationService.save(organizationDTO.getOrganization())
            updateMessage("Organization with id: ${organization.id} has saved in DB")
            OrganizationDTO result = new OrganizationDTO(organization)
            updateValue(result)
            return result
        }catch(Exception ex){
            LOGGER.warn("There is exception during saving organization: ${organizationDTO} ex: ${ex.getMessage()}" )
            throw new WelderCenterException(ex.getMessage())
        }


    }

    @Override
    protected void succeeded() {
        super.succeeded()
        updateMessage(TASK_SUCCESS_MESSAGE)
    }

    @Override
    protected void cancelled() {
        super.cancelled()
        updateMessage(TASK_CANCELED_MESSAGE)
    }

    @Override
    protected void failed() {
        super.failed()
        updateMessage(TASK_FAIL_MESSAGE)
    }
}
