package com.lvg.weldercenter.se.ui.tasks.organization

import com.lvg.weldercenter.se.models.Organization
import com.lvg.weldercenter.se.services.OrganizationService
import com.lvg.weldercenter.se.ui.dto.OrganizationDTO
import com.lvg.weldercenter.se.ui.tasks.TaskConstants
import javafx.application.Platform
import javafx.beans.property.ReadOnlyObjectProperty
import javafx.beans.property.ReadOnlyObjectWrapper
import javafx.collections.FXCollections
import javafx.collections.ObservableList
import javafx.concurrent.Task
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component

@Component
@Scope('prototype')
class OrganizationTask extends Task<ObservableList<OrganizationDTO>>{

    private ReadOnlyObjectWrapper<ObservableList<OrganizationDTO>> partialResults =
            new ReadOnlyObjectWrapper<>(this, "partialResults",
                    FXCollections.observableArrayList(new ArrayList<OrganizationDTO>()))

    final ObservableList<OrganizationDTO> getPartialResults(){
        return partialResults.get()
    }
    final ReadOnlyObjectProperty<ObservableList<OrganizationDTO>> partialResultsProperty(){
        partialResults.getReadOnlyProperty()
    }

    @Autowired
    OrganizationService organizationService



    @Override
    protected ObservableList<OrganizationDTO> call() throws Exception {
        updateTitle(TaskConstants.ALL_ORGANIZATIONS_TASK_TITLE_MESSAGE)

        long count = organizationService.count()
        long counter = 0

        for(Organization organization : organizationService.getAll()){
            if (this.isCancelled()) break
            final OrganizationDTO organizationDTO = new OrganizationDTO(organization)
            Platform.runLater({getPartialResults().add(organizationDTO)})
            counter++
            updateProgress(counter, count)
        }
        return partialResults.get()
    }
}
