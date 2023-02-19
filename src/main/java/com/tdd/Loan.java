package com.tdd;

import java.time.Duration;
import java.util.Date;
import java.util.Objects;

public class Loan {
	public Loan(String loaner, String bookReference, Date loanDate, Duration loanDuration) {
		this.loaner = loaner;
		this.bookReference = bookReference;
		this.loanDate = loanDate;
		this.loanDuration = loanDuration;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Loan loan = (Loan) o;
		return Objects.equals(loaner, loan.loaner) && Objects.equals(bookReference, loan.bookReference) && Objects.equals(loanDate, loan.loanDate);
	}

	@Override
	public int hashCode() {
		return Objects.hash(loaner, bookReference, loanDate);
	}

	public String loaner;

	public String bookReference;

	public Date loanDate;

	public Duration loanDuration;
}
