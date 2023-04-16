package com.tdd.infrastructure;

import com.tdd.domain.Loan;
import com.tdd.domain.LoanRepository;

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
	public boolean areBookAvailable(List<String> bookReferences) {
		return bookReferences
				.stream()
				.allMatch(reference -> this.loans.stream().noneMatch(loan -> loan.bookReferences.contains(reference)));
	}
}
