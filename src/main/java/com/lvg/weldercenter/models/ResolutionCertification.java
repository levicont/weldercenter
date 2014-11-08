package com.lvg.weldercenter.models;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Victor Levchenko LVG Corp. on 23.10.14.
 */
@Entity
@Table(name = "resolution_certification")
public class ResolutionCertification implements Serializable{


    private static final long serialVersionUID = 8867333287125073724L;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ResolutionCertification that = (ResolutionCertification) o;

        if (!resolutionCertificationId.equals(that.resolutionCertificationId)) return false;
        if (textResolution != null ? !textResolution.equals(that.textResolution) : that.textResolution != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = resolutionCertificationId.hashCode();
        result = 31 * result + (textResolution != null ? textResolution.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ResolutionCertification{" +
                "resolutionCertificationId=" + resolutionCertificationId +
                ", textResolution='" + textResolution + '\'' +
                '}';
    }
}
