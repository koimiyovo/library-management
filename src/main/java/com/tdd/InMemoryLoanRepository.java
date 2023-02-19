package com.tdd;

import java.util.ArrayList;
import java.util.List;

public class InMemoryLoanRepository implements LoanRepository {
	public List<Loan> loans = new ArrayList<>();

	public Loan lastLoan() {
		if (loans.isEmpty()) {
			return null;
		}
		return loans.get(loans.size() - 1);
	}

	public void saveLoan(Loan loan) {
		loans.add(loan);
	}

	public void feedWith(Loan loan) {
		this.loans.add(loan);
	}

	@Override
	public boolean isBookAvailable(String bookReference) {
		return this.loans.stream().noneMatch(loan -> loan.bookReference.equals(bookReference));
	}
}
