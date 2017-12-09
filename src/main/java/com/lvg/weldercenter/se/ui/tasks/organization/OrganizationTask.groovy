package com.lvg.weldercenter.se.ui.tasks.organization

import com.lvg.weldercenter.se.models.Organization
import com.lvg.weldercenter.se.services.OrganizationService
import com.lvg.weldercenter.se.ui.dto.OrganizationDTO
import com.lvg.weldercenter.se.ui.tasks.TaskConstants
import javafx.collections.FXCollections
import javafx.collections.ObservableList
import javafx.concurrent.Task
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component

@Component
@Scope('prototype')
class OrganizationTask extends Task<ObservableList<OrganizationDTO>>{

    @Autowired
    OrganizationService organizationService



    @Override
    protected ObservableList<OrganizationDTO> call() throws Exception {
        ObservableList<OrganizationDTO> results = FXCollections.observableArrayList()
        updateTitle(TaskConstants.ALL_ORGANIZATIONS_TASK_TITLE_MESSAGE)

        long count = organizationService.count()
        long counter = 0

        for(Organization organization : organizationService.getAll()){
            if (this.isCancelled()){
                break
            }
            results.add(new OrganizationDTO(organization))
            counter++
            updateMessage("Added $counter organizations to the list")
            updateValue(FXCollections.unmodifiableObservableList(results))
            updateProgress(counter, count)
        }
        return results
    }
}
