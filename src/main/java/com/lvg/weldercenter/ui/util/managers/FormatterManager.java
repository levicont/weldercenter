package com.lvg.weldercenter.ui.util.managers;

import com.lvg.weldercenter.ui.util.DateUtil;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import org.apache.log4j.Logger;

import java.time.format.DateTimeParseException;
import java.util.function.UnaryOperator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Victor on 07.06.2016.
 */
public class FormatterManager {
    private static Logger LOGGER = Logger.getLogger(FormatterManager.class);

    public TextFormatter<String> getDateTextFieldFormatter(){
        return new TextFormatter<>(new DateTextFieldFormatter());
    }

    public DateCheckListener getCheckListener(Node source){
        return new DateCheckListener(source);
    }

    private class DateTextFieldFormatter implements UnaryOperator<TextFormatter.Change>{
        private final String DATE_DAY_REGEX = "^[0-9]{2}";
        private final String DATE_MONTH_REGEX = "^[0-9]{2}[.][0-9]{2}";
        private final String DATE_YEAR_REGEX = "^[0-9]{2}[.][0-9]{2}[.][0-9]{4}";
        private final String DATE_EDITABLE_DAY_REGEX = "^[0-9]{0,2}";
        private final String DATE_EDITABLE_MONTH_REGEX = "^[0-9]{0,2}[.][0-9]{0,2}";
        private final String DATE_EDITABLE_YEAR_REGEX = "^[0-9]{0,2}[.][0-9]{0,2}[.][0-9]{0,4}";

        @Override
        public TextFormatter.Change apply(TextFormatter.Change t) {
            TextFormatter.Change change = t;
            if (change.isReplaced())
                return change;
            if (change.isAdded()){
                String text = change.getControlText();
                if(isDateYearDigits(change.getText()))
                    return change;

                if(isDateYearDigits(text)){
                    LOGGER.debug("BEFORE Added YEAR" +
                            "\n change: " + change.toString());
                    change.setText("");
                    change.setCaretPosition(change.getControlText().length());
                    change.setAnchor(change.getControlText().length());
                    LOGGER.debug("AFTER Added YEAR" +
                            "\n change: " + change.toString());
                    return change;
                }else if(isDateMounthDigits(text)){
                    LOGGER.debug("BEFORE Added MONTH" +
                            "\n change: " + change.toString());
                    change.setText("."+change.getText());
                    change.setCaretPosition(change.getControlNewText().length());
                    change.setAnchor(change.getControlNewText().length());
                    LOGGER.debug("AFTER Added MONTH" +
                            "\n change: " + change.toString());
                    return change;
                }else if(isDateDaysDigits(text)){
                    change.setText("."+change.getText());
                    change.setCaretPosition(change.getControlNewText().length());
                    change.setAnchor(change.getControlNewText().length());
                    LOGGER.debug("Added DAY" +
                            "\n change: " + change.toString());
                    return change;
                }else if(isEditableDate(text)){
                    return change;
                }else{
                    LOGGER.debug("INCORRECT"+
                            "\n change: " + change.toString());
                    change.setText("");
                }

            }

            return change;
        }

        private boolean isDateDaysDigits(String text){
            return text.matches(DATE_DAY_REGEX);
        }

        private boolean isDateMounthDigits(String text){
            return text.matches(DATE_MONTH_REGEX);
        }

        private boolean isDateYearDigits(String text){
            return text.matches(DATE_YEAR_REGEX);
        }

        private boolean isEditableDate(String text){
            return text.matches(DATE_EDITABLE_DAY_REGEX) || text.matches(DATE_EDITABLE_MONTH_REGEX)
                    || text.matches(DATE_EDITABLE_YEAR_REGEX);
        }

    }

    private class DateCheckListener implements ChangeListener<String>{
        private final String COMMON_DATE_PATTERN_REGEX = "^[0-9]{1,2}[.][0-9]{1,2}[.][0-9]{2,4}";
        private final Pattern COMMON_DATE_PATTERN = Pattern.compile(COMMON_DATE_PATTERN_REGEX);
        private final Node source;

        public DateCheckListener(Node source){
            this.source = source;
        }


        @Override
        public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

            Matcher matcher = COMMON_DATE_PATTERN.matcher(newValue);
            TextField editor = getEditor(source);
            int textLength = editor.getText().length();
            if ( matcher.matches() || textLength >= 10 ){
                checkDate(editor);
            }
        }

        private TextField getEditor(Node source){
            if (source instanceof TextField)
                return (TextField)source;
            if (source instanceof DatePicker)
                return ((DatePicker)source).getEditor();
            throw new IllegalArgumentException("Component has not editor");
        }

        private void checkDate(TextField editor){
            String dateStr = editor.getText();
            try {
                DateUtil.parse(editor.getText());
            }catch (DateTimeParseException ex){
                LOGGER.debug("NOT CORRECT DATE: "+dateStr);
                editor.setStyle("-fx-text-inner-color:red");
                return;
            }
            editor.setStyle("-fx-text-inner-color:black");


        }
    }
}
