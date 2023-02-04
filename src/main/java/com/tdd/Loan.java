package com.tdd;

import java.util.Date;

public class Loan {
    public Loan(String loaner, String bookReference, Date loaneDate) {
        this.loaner = loaner;
        this.bookReference = bookReference;
        this.loanDate = loaneDate;
    }

    public String loaner;
    public String bookReference;
    public Date loanDate;
}
