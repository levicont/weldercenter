package com.lvg.weldercenter.ui.servicesui.impl;

import com.lvg.weldercenter.models.Organization;
import com.lvg.weldercenter.services.OrganizationService;
import com.lvg.weldercenter.ui.entities.OrganizationUI;
import com.lvg.weldercenter.ui.servicesui.OrganizationServiceUI;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Victor on 29.07.2015.
 */
public class OrganizatioUIManager implements OrganizationServiceUI {
    @Autowired
    OrganizationService organizationService;

    @Override
    public Organization getOrganizationFromOrganizationUI(OrganizationUI organizationUI) {
        if (organizationUI == null)
            return null;
        Organization org = organizationService.get(organizationUI.getId());
        if (org!=null){
            updateOrganizationFromUIModel(org, organizationUI);
        }else {
            org = new Organization();
            updateOrganizationFromUIModel(org, organizationUI);
        }
        return org;
    }

    private void updateOrganizationFromUIModel(Organization updOrganization,
                                                         OrganizationUI modelUI){
        updOrganization.setName(modelUI.getName());
        updOrganization.setAdress(modelUI.getAdress());
        updOrganization.setPhone(modelUI.getPhone());
    }
}
