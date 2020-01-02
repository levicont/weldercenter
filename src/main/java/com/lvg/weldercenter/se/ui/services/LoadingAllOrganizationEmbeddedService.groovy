package com.lvg.weldercenter.se.ui.services

import com.lvg.weldercenter.se.models.OrganizationEmbedded
import com.lvg.weldercenter.se.ui.tasks.organization.OrganizationEmbeddedTask
import javafx.collections.ObservableSet
import javafx.concurrent.Task
import org.springframework.stereotype.Component

/**
 * Created by Victor Levchenko LVG Corp. on 15.12.2019.
 */

@Component
class LoadingAllOrganizationEmbeddedService extends UIService<ObservableSet<OrganizationEmbedded>>{

    @Override
    protected Task<ObservableSet<OrganizationEmbedded>> getTask() {
        return ctx.getBean(OrganizationEmbeddedTask.class)
    }
}
