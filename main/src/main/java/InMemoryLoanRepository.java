package main.src.main.java;

import java.util.ArrayList;
import java.util.List;

public class InMemoryLoanRepository implements LoanRepository {
    public List<Loan> loans = new ArrayList<>();

    public Loan lastLoan() {
        return loans.get(loans.size() - 1);
    }

    public void saveLoan(Loan loan) {
        loans.add(loan);
    }
}
