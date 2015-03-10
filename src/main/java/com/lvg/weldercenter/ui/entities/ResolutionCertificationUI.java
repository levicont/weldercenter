package com.lvg.weldercenter.ui.entities;

import com.lvg.weldercenter.models.ResolutionCertification;
import javafx.beans.property.SimpleStringProperty;

/**
 * Created by Victor Levchenko LVG Corp. on 23.01.15.
 */
public class ResolutionCertificationUI extends GenericEntityUI {
    private final String DEFAULT_RESOLUTION = "";
    private final SimpleStringProperty textResolution = new SimpleStringProperty();


    public ResolutionCertificationUI(){
        this.id.set(0);
        this.textResolution.set(DEFAULT_RESOLUTION);
    }

    public ResolutionCertificationUI(ResolutionCertification resolutionCertification){
        this.id.set(resolutionCertification.getResolutionCertificationId());
        this.textResolution.set(resolutionCertification.getTextResolution());
    }

    //Getters and Setters

    public String getTextResolution() {
        return textResolution.get();
    }

    public SimpleStringProperty textResolutionProperty() {
        return textResolution;
    }

    public void setTextResolution(String textResolution) {
        this.textResolution.set(textResolution);
    }
}
