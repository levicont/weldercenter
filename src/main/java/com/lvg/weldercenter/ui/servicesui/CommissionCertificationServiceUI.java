package com.lvg.weldercenter.ui.servicesui;

import com.lvg.weldercenter.models.CommissionCertification;
import com.lvg.weldercenter.ui.entities.CommissionCertificationUI;

/**
 * Created by Victor on 20.04.2015.
 */
public interface CommissionCertificationServiceUI {
    public CommissionCertification getCommissionCertificationFromUIModel(CommissionCertificationUI commissionCertificationUI);
}
