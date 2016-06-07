package com.lvg.weldercenter.ui.util.managers;

import javafx.scene.control.TextFormatter;

import java.util.function.UnaryOperator;

/**
 * Created by Victor on 07.06.2016.
 */
public class FormatterManager {


    public TextFormatter<String> getDateTextFieldFormatter(){
        return new TextFormatter<>(new DateTextFieldFormatter());
    }


    private class DateTextFieldFormatter implements UnaryOperator<TextFormatter.Change>{

            @Override
            public TextFormatter.Change apply(TextFormatter.Change t) {
                TextFormatter.Change change = t;
                if (change.isAdded()){
                    String text = change.getControlText();
                    if(change.getText().matches("^[0-9]{1,2}[.][0-9]{2}[.][0-9]{4}"))
                        return change;
                    if(text.matches("^[0-9]{1,2}[.][0-9]{2}[.][0-9]{4}")){
                        System.out.println("BEFORE Added YEAR"+
                                "\n text: "+ change.getText()+
                                "\n controlText: "+ change.getControlText()+
                                "\n newControlText: "+ change.getControlNewText());
                        change.setText("");
                        change.setCaretPosition(change.getControlText().length());
                        change.setAnchor(change.getControlText().length());
                        System.out.println("AFTER Added YEAR"+
                                "\n text: "+ change.getText()+
                                "\n controlText: "+ change.getControlText()+
                                "\n newControlText: "+ change.getControlNewText());
                        return change;
                    }else if(text.matches("^[0-9]{1,2}[.][0-9]{2}")){
                        System.out.println("BEFORE Added MONTH"+
                                "\n text: "+ change.getText()+
                                "\n controlText: "+ change.getControlText()+
                                "\n newControlText: "+ change.getControlNewText());
                        change.setText("."+change.getText());
                        change.setCaretPosition(change.getControlNewText().length());
                        change.setAnchor(change.getControlNewText().length());
                        System.out.println("AFTER Added MONTH"+
                                "\n text: "+ change.getText()+
                                "\n controlText: "+ change.getControlText()+
                                "\n newControlText: "+ change.getControlNewText());
                        return change;
                    }else if(text.matches("[0-9]{2}")){
                        change.setText("."+change.getText());
                        change.setCaretPosition(change.getControlNewText().length());
                        change.setAnchor(change.getControlNewText().length());
                        System.out.println("Added DAY"+
                                "\n text: "+ text+
                                "\n controlText: "+ change.getControlText()+
                                "\n newControlText: "+ change.getControlNewText());
                        return change;
                    }else if(text.matches("^[0-9]{0,1}") ||
                            text.matches("^[0-9]{1,2}[.][0-9]{0,1}") ||
                            text.matches("^[0-9]{1,2}[.][0-9]{1,2}[.][0-9]{0,4}")){
                        return change;
                    }else{
                        System.out.println("INCORRECT"+
                                "\n text: "+ text+
                                "\n controlText: "+ change.getControlText()+
                                "\n newControlText: "+ change.getControlNewText());
                        change.setText("");
                    }

                }


                return change;
            }


    }
}
