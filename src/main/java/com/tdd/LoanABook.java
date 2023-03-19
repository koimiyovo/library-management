package com.tdd;

import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.function.Supplier;

public class LoanABook {
	private final Supplier<Date> loanDateProvider;

	private final LoanRepository loanRepository;

	public LoanABook(Supplier<Date> loanDateProvider, LoanRepository loanRepository) {
		this.loanDateProvider = loanDateProvider;
		this.loanRepository = loanRepository;
	}

	public void execute(List<String> bookReferences, String loanerEmail, Duration loanDuration) {
		if (!loanRepository.areBookAvailable(bookReferences)) return;
		Loan loanToSave = new Loan(loanerEmail, bookReferences, loanDateProvider.get(), loanDuration);
		loanRepository.saveLoan(loanToSave);
	}
}
