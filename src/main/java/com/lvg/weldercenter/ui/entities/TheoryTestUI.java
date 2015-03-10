package com.lvg.weldercenter.ui.entities;

import com.lvg.weldercenter.models.TheoryTest;
import javafx.beans.property.SimpleStringProperty;

/**
 * Created by Victor Levchenko LVG Corp. on 23.01.15.
 */
public class TheoryTestUI extends GenericEntityUI {
    private SimpleStringProperty ticketNumber = new SimpleStringProperty();
    private SimpleStringProperty rating = new SimpleStringProperty();

    public TheoryTestUI(){
        this.id.set(0);
        this.ticketNumber.set("");
        this.rating.set("");
    }

    public TheoryTestUI(TheoryTest theoryTest){
        this.id.set(theoryTest.getTheoryTestId());
        this.ticketNumber.set(theoryTest.getTicketNumber());
        this.rating.set(theoryTest.getRating());
    }

    //Getters and setters

    public String getTicketNumber() {
        return ticketNumber.get();
    }

    public SimpleStringProperty ticketNumberProperty() {
        return ticketNumber;
    }

    public void setTicketNumber(String ticketNumber) {
        this.ticketNumber.set(ticketNumber);
    }

    public String getRating() {
        return rating.get();
    }

    public SimpleStringProperty ratingProperty() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating.set(rating);
    }
}
