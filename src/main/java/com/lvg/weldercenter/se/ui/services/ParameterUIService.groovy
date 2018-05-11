package com.lvg.weldercenter.se.ui.services

import javafx.concurrent.Task

abstract class ParameterUIService<T, V> extends UIService<V> {
    protected T parameter


    void initParameter(T parameter) {
        this.parameter = parameter
    }

    protected void checkParameter() {
        if (parameter == null) {
            throw new NullPointerException("ParameterUIService: parameter is null")
        }
    }

    @Override
    protected Task<V> createTask() {
        checkParameter()
        return getTask()
    }
}
