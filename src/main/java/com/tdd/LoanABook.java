package com.tdd;

import java.time.Duration;
import java.util.Date;
import java.util.function.Supplier;

public class LoanABook {
	private final Supplier<Date> loanDateProvider;

	private final LoanRepository loanRepository;

	public LoanABook(Supplier<Date> loanDateProvider, LoanRepository loanRepository) {
		this.loanDateProvider = loanDateProvider;
		this.loanRepository = loanRepository;
	}

	public void execute(String bookReference, String loanerEmail, Duration loanDuration) {
		if (!loanRepository.isBookAvailable(bookReference)) return;
		Loan loanToSave = new Loan(loanerEmail, bookReference, loanDateProvider.get(), loanDuration);
		loanRepository.saveLoan(loanToSave);
	}
}
