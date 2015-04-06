package com.lvg.weldercenter.ui.servicesui.impl;

import com.lvg.weldercenter.models.ResolutionCertification;
import com.lvg.weldercenter.services.ResolutionCertificationService;
import com.lvg.weldercenter.ui.entities.ResolutionCertificationUI;
import com.lvg.weldercenter.ui.servicesui.ResolutionCertificationServiceUI;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Victor on 06.04.2015.
 */
public class ResolutionCeritificationUIManager implements ResolutionCertificationServiceUI {
    @Autowired
    private ResolutionCertificationService resolutionCertificationService;

    @Override
    public ResolutionCertification getResolutionCertFromUIModel(ResolutionCertificationUI resolutionCertificationUI) {
        if (resolutionCertificationUI == null) {
            return null;
        }
        ResolutionCertification rc = resolutionCertificationService.get(resolutionCertificationUI.getId());
        if(rc != null){
            updateResolutionFromUIModel(rc,resolutionCertificationUI);
        }else {
            rc  = new ResolutionCertification();
            updateResolutionFromUIModel(rc, resolutionCertificationUI);
        }
        return rc;
    }

    private void updateResolutionFromUIModel(ResolutionCertification updResolution, ResolutionCertificationUI modelUI){
        updResolution.setTextResolution(updResolution.getTextResolution());
    }
}
