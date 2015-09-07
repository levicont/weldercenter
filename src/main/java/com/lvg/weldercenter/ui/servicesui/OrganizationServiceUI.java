package com.lvg.weldercenter.ui.servicesui;

import com.lvg.weldercenter.models.Organization;
import com.lvg.weldercenter.ui.entities.OrganizationUI;

/**
 * Created by Victor on 29.07.2015.
 */
public interface OrganizationServiceUI {

    public Organization getOrganizationFromOrganizationUI(OrganizationUI organizationUI);
    public void saveOrganizationUIinDB(OrganizationUI organizationUI);
}
