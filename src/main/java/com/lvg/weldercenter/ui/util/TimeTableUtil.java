package com.lvg.weldercenter.ui.util;

import com.lvg.weldercenter.models.Journal;
import com.lvg.weldercenter.ui.entities.TopicUI;

import java.util.List;

/**
 * Created by Victor Levchenko LVG Corp. on 17.12.14.
 */
public interface TimeTableUtil {
    public final Integer HOURS_IN_WORK_DAY = 8;

    public List<TopicUI> getTimeTable(Journal journal);
}
