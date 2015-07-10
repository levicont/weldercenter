package com.lvg.weldercenter.ui.entities.report;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * Created by Victor on 20.05.2015.
 */
@Entity
public class TheoryReportEntity extends GenericReportEntity{

    @Column(name = "welder")
    private String welder;
    private String weldMethods;
    private String ndtDocs;
    private String numberTickets;
    private String rating;
    private String notes;

    public TheoryReportEntity(){
        welder = "none";
        weldMethods = "none";
        ndtDocs = "none";
        numberTickets = "none";
        rating = "none";
        notes = "";
    }

    public String getWelder() {
        return welder;
    }

    public void setWelder(String welder) {
        this.welder = welder;
    }

    public String getWeldMethods() {
        return weldMethods;
    }

    public void setWeldMethods(String weldMethods) {
        this.weldMethods = weldMethods;
    }

    public String getNdtDocs() {
        return ndtDocs;
    }

    public void setNdtDocs(String ndtDocs) {
        this.ndtDocs = ndtDocs;
    }

    public String getNumberTickets() {
        return numberTickets;
    }

    public void setNumberTickets(String numberTickets) {
        this.numberTickets = numberTickets;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
}
