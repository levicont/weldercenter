package com.lvg.weldercenter.se.ui.tasks.organization


import com.lvg.weldercenter.se.models.OrganizationEmbedded
import com.lvg.weldercenter.se.services.WelderService
import com.lvg.weldercenter.se.ui.tasks.TaskConstants
import javafx.application.Platform
import javafx.beans.property.ReadOnlyObjectWrapper
import javafx.collections.FXCollections
import javafx.collections.ObservableSet
import javafx.concurrent.Task
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component

/**
 * Created by Victor Levchenko LVG Corp. on 15.12.2019.
 */
@Component
@Scope('prototype')
class OrganizationEmbeddedTask extends Task<ObservableSet<OrganizationEmbedded>> implements TaskConstants{

    @Autowired
    WelderService welderService

    private ReadOnlyObjectWrapper<ObservableSet<OrganizationEmbedded>> partialResult =
            new ReadOnlyObjectWrapper<>(this, 'partialResult', FXCollections.observableSet())

    final ObservableSet<OrganizationEmbedded> getPartialResult(){
        return partialResult.get()
    }


    @Override
    protected ObservableSet<OrganizationEmbedded> call() throws Exception {
        updateTitle(ALL_ORGANIZATIONS_TASK_TITLE_MESSAGE)

        welderService.getAllOrganization().forEach({org ->
            if (this.isCancelled()) return
            Platform.runLater({getPartialResult().add(org)})
        })

        return partialResult.get()
    }
}
