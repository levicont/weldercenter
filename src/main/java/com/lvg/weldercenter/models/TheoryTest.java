package com.lvg.weldercenter.models;

import javax.persistence.*;

/**
 * Created by Victor Levchenko LVG Corp. on 23.10.14.
 */
@Entity
@Table(name = "theory_test")
public class TheoryTest {

    private Long theoryTestId;
    private String ticketNumber;
    private String rating;


    //Getters and Setters

    @Id
    @GeneratedValue
    @Column(name = "id_theory_test")
    public Long getTheoryTestId() {
        return theoryTestId;
    }

    public void setTheoryTestId(Long theoryTestId) {
        this.theoryTestId = theoryTestId;
    }

    @Column(name = "ticket_number")
    public String getTicketNumber() {
        return ticketNumber;
    }

    public void setTicketNumber(String ticketNumber) {
        this.ticketNumber = ticketNumber;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
}
