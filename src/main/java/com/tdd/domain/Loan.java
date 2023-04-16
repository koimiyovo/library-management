package com.tdd.domain;

import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Loan {
    public Loan(String loaner, List<String> bookReferences, Date loanDate, Duration loanDuration) {
        this.loaner = loaner;
        this.bookReferences = bookReferences;
        this.loanDate = loanDate;
        this.loanDuration = loanDuration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Loan loan = (Loan) o;
        return loaner.equals(loan.loaner) && bookReferences.equals(loan.bookReferences) && loanDate.equals(loan.loanDate) && loanDuration.equals(loan.loanDuration);
    }

    @Override
    public int hashCode() {
        return Objects.hash(loaner, bookReferences, loanDate, loanDuration);
    }

    public String loaner;

    public List<String> bookReferences;

    public Date loanDate;

    public Duration loanDuration;
}
