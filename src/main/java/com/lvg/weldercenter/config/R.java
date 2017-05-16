package com.lvg.weldercenter.config;

/**
 * Created by Victor on 19.08.2015.
 */
public class R {
    public static class UI{
        public static class Entities {
            public static class Reports {

                //Generic Reports properties
                public static final String GENERIC_SIZE_SEPARATOR = " x ";
                public static final String GENERIC_NULL_FIELD = "NULL";
                public static final String GENERIC_SPACE_SEPARATOR = " ";
                public static final String GENERIC_SLASH_SEPARATOR = " / ";
                public static final String GENERIC_SEMICOLON_SEPARATOR = "; ";
                public static final String GENERIC_COLON_SEPARATOR = ": ";
                public static final String GENERIC_YES_STRING = "есть";
                public static final String GENERIC_NO_STRING = "нет";
                public static final String GENERIC_DATE_SUFFIX = " г.";
                public static final String GENERIC_FLOAT_DIGIT_SUFFIX = ".0";
                public static final String GENERIC_MILLIMETERS_SUFFIX = " мм";
                public static final String GENERIC_DEGREES_SUFFIX = " град.";

                //NDT Laboratory properties
                public static final String GENERIC_HEAD_OF_NDT_LABORATORY = "В.Г. Левченко";
                public static final String GENERIC_RT_CERT_OF_HEAD_LABORATORY = "(II уровень RT, квалификационное удостоверение №30212)";
                public static final String GENERIC_RT_NDT_SPEC = "Д.А. Крапива";
                public static final String GENERIC_RT_CERT_OF_NDT_SPEC = "(III уровень RT, квалификационное удостоверение №30298)";
                public static final String GENERIC_MECH_TEST_SPEC = "Д.А. Крапива";

                //Journal properies
                public static  final String JOURNAL_KEY_ID = "ID";
                public static  final String JOURNAL_KEY_NUMBER = "NUMBER";
                public static  final String JOURNAL_KEY_DATE_BEGIN = "DATE_BEGIN";
                public static  final String JOURNAL_KEY_DATE_BEGIN_FORMAT = "DATE_BEGIN_FORMAT";
                public static  final String JOURNAL_KEY_DATE_BEGIN_FORMAT_YEAR = "DATE_BEGIN_FORMAT_YEAR";
                public static  final String JOURNAL_KEY_DATE_END = "DATE_END";
                public static  final String JOURNAL_KEY_DATE_END_FORMAT = "DATE_END_FORMAT";
                public static  final String JOURNAL_KEY_CURRICULUM_TITLE = "CURRICULUM_TITLE";
                public static  final String JOURNAL_KEY_WELDERS_COUNT = "WELDERS_COUNT";
                public static  final String JOURNAL_KEY_TEACHERS = "TEACHERS";

                //Weld pattern properties
                public static final String WELD_PATT_STANDART_LENGHT = "300";
                public static final String WELD_PATT_STANDART_WIDTH = "250";
                public static final String WELD_PATT_VT_DEFAULT_DEFECTS ="Все сварные соединения не имеют " +
                        "отступлений по размерам и форме шва. \n" +
                        "Отсутствуют: \n" +
                        "а) поверхностные трещины;\n" +
                        "б) наплывы, прожоги, поверхностные поры или шлаковые включения;\n" +
                        "в) непровары, подрезы, смещения кромок, излом или неперпендикулярность соединяемых элементов," +
                        " превышающие допустимые";

            }
        }

        public static class Control{

            //Generic controller
            public static final String GENERIC_STYLE_NOT_EDITABLE_BACKGROUND = "-fx-background-color: #deefff";
            public static final String GENERIC_STYLE_NOT_EDIT_COMBOBOX =
                    "-fx-opacity: 1;" +
                    "-fx-background-color: #deefff;" +
                    "-fx-text-fill: #000";
            public static final String GENERIC_STYLE_NOT_EDIT_TABLE_VIEW =
                "-fx-background-color : #deefff;" +
                        "-fx-opacity: 0.7";


            public static final String GENERIC_STYLE_NOT_EDIT_DATE_PICKER =
                    "-fx-opacity: 1;" +
                    "-fx-background-color: #deefff;" +
                    "-fx-text-fill: #000";
            public static final String GENERIC_STYLE_TAB_BACKGROUND = "-fx-background-color: linear-gradient(to TOP, #f9eee0, #f9e5cc)";
            public static final String GENERIC_ROOT_CURRICULUM_TITLE = "Программы подготовки";
            public static final Integer GENERIC_MAX_TASK_PROGRESS_VALUE = 100;
        }
    }
}
