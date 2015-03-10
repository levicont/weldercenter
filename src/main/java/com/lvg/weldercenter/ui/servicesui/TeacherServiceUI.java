package com.lvg.weldercenter.ui.servicesui;

import com.lvg.weldercenter.models.Teacher;
import com.lvg.weldercenter.ui.entities.TeacherUI;

/**
 * Created by Victor Levchenko LVG Corp. on 19.02.15.
 */
public interface TeacherServiceUI {

    public Teacher getTeacherFromTeacherUI(TeacherUI teacherUI);
}
