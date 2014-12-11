package com.lvg.weldercenter.services;

import com.lvg.weldercenter.models.Education;

/**
 * Created by Victor Levchenko (LVG Corp.) on 29.10.2014.
 */
public interface EducationService extends GenericService<Education> {
    Education getByType(String type);
}
