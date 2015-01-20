package com.lvg.weldercenter.ui.util;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Victor Levchenko (LVG Corp.) on 26.12.2014.
 */
public interface Holidays {
    public static final Holiday NEW_YEAR = new Holiday(1,1);
    public static final Holiday CHRISTMAS = new Holiday(7,1);
    public static final Holiday CATHOLICITY = new Holiday(22,1);
    public static final Holiday WOMAN_DAY  = new Holiday(8,3);
    public static final Holiday FIRST_MAY_DAY = new Holiday(1,5);
    public static final Holiday SECOND_MAY_DAY = new Holiday(2,5);
    public static final Holiday GLORY_DAY = new Holiday(9,5);
    public static final Holiday CONSTITUTION_DAY = new Holiday(28,6);
    public static final Holiday INDEPENDENT_DAY = new Holiday(24,8);
    public static final DayOfWeek SUNDAY = DayOfWeek.SUNDAY;
    public static final DayOfWeek SATURDAY = DayOfWeek.SATURDAY;

    public static final List<Holiday> ALL_HOLIDAYS = new ArrayList<Holiday>(){{
       add(NEW_YEAR);
       add(CHRISTMAS);
       add(CATHOLICITY);
       add(WOMAN_DAY);
       add(FIRST_MAY_DAY);
       add(SECOND_MAY_DAY);
       add(GLORY_DAY);
       add(CONSTITUTION_DAY);
       add(INDEPENDENT_DAY);
    }};
}
