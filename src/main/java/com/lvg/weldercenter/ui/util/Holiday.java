package com.lvg.weldercenter.ui.util;

/**
 * Created by Victor Levchenko (LVG Corp.) on 26.12.2014.
 */
public class Holiday {



        private int mounth;
        private int day;

        public Holiday(int day, int mounth){
            this.day = day;
            this.mounth = mounth;
        }


        public int getMounth() {
            return mounth;
        }

        public void setMounth(int mounth) {
            this.mounth = mounth;
        }

        public int getDay() {
            return day;
        }

        public void setDay(int day) {
            this.day = day;
        }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Holiday holiday = (Holiday) o;

        if (day != holiday.day) return false;
        if (mounth != holiday.mounth) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = mounth;
        result = 31 * result + day;
        return result;
    }
}
