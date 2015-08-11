package com.lvg.weldercenter.exceptions;

/**
 * Created by Victor on 10.08.2015.
 */
public class WelderException extends Exception {

    public WelderException(){
        super();
    }

    public WelderException(String msg, Exception ex){
        super(msg, ex);
    }

    public WelderException(String msg){
        super(msg);
    }
}
