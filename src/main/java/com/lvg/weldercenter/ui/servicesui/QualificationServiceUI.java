package com.lvg.weldercenter.ui.servicesui;

import com.lvg.weldercenter.models.Qualification;
import com.lvg.weldercenter.ui.entities.QualificationUI;

/**
 * Created by Victor Levchenko LVG Corp. on 04.08.15.
 */
public interface QualificationServiceUI {
    public Qualification getQualificationFromUIModel(QualificationUI qualificationUI);
}
