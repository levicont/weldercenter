package com.lvg.weldercenter.models;

import javax.persistence.*;

/**
 * Created by Victor Levchenko LVG Corp. on 23.10.14.
 */
@Entity
@Table(name = "resolution_certification")
public class ResolutionCertification {

    private Long resolutionCertificationId;
    private String textResolution;

    //Getters and setters

    @Id
    @GeneratedValue
    @Column(name = "id_resolution_certification")
    public Long getResolutionCertificationId() {
        return resolutionCertificationId;
    }

    public void setResolutionCertificationId(Long resolutionCertificationId) {
        this.resolutionCertificationId = resolutionCertificationId;
    }
    @Column(name = "text_resolution", columnDefinition = "TEXT")
    public String getTextResolution() {
        return textResolution;
    }

    public void setTextResolution(String textResolution) {
        this.textResolution = textResolution;
    }
}
