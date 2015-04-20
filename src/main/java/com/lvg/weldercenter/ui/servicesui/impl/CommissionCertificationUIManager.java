package com.lvg.weldercenter.ui.servicesui.impl;

import com.lvg.weldercenter.models.CommissionCertification;
import com.lvg.weldercenter.services.CommissionCertificationService;
import com.lvg.weldercenter.ui.entities.CommissionCertificationUI;
import com.lvg.weldercenter.ui.servicesui.CommissionCertificationServiceUI;
import com.lvg.weldercenter.ui.servicesui.TeacherServiceUI;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Victor on 20.04.2015.
 */
public class CommissionCertificationUIManager implements CommissionCertificationServiceUI {

    @Autowired
    CommissionCertificationService commissionCertificationService;
    @Autowired
    TeacherServiceUI teacherServiceUI;

    @Override
    public CommissionCertification getCommissionCertificationFromUIModel(CommissionCertificationUI commissionCertificationUI) {
        if (commissionCertificationUI == null)
            return null;
        CommissionCertification cc  = commissionCertificationService.get(commissionCertificationUI.getId());
        if (cc != null){
            updateCommissionCertificatioFromUIModel(cc,commissionCertificationUI);
        }else {
            cc = new CommissionCertification();
            updateCommissionCertificatioFromUIModel(cc,commissionCertificationUI);
        }
        return cc;
    }

    private void updateCommissionCertificatioFromUIModel(CommissionCertification updCommission,
                                                         CommissionCertificationUI modelUI){
        updCommission.setHead(teacherServiceUI.getTeacherFromTeacherUI(modelUI.getHead()));
        updCommission.setNdtSpecialist(teacherServiceUI.getTeacherFromTeacherUI(modelUI.getNdtSpecialist()));
        updCommission.setWeldSpecialist(teacherServiceUI.getTeacherFromTeacherUI(modelUI.getWeldSpecialist()));
        updCommission.setSafetySpecialist(teacherServiceUI.getTeacherFromTeacherUI(modelUI.getSafetySpecialist()));
    }
}
