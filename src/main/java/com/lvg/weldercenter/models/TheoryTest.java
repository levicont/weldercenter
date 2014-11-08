package com.lvg.weldercenter.models;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Victor Levchenko LVG Corp. on 23.10.14.
 */
@Entity
@Table(name = "theory_test")
public class TheoryTest implements Serializable{

    private static final long serialVersionUID = 79062375086076292L;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TheoryTest that = (TheoryTest) o;

        if (!rating.equals(that.rating)) return false;
        if (!theoryTestId.equals(that.theoryTestId)) return false;
        if (!ticketNumber.equals(that.ticketNumber)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = theoryTestId.hashCode();
        result = 31 * result + ticketNumber.hashCode();
        result = 31 * result + rating.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "TheoryTest{" +
                "theoryTestId=" + theoryTestId +
                ", ticketNumber='" + ticketNumber + '\'' +
                ", rating='" + rating + '\'' +
                '}';
    }
}
