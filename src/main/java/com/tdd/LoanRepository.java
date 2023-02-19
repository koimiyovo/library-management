package com.tdd;

public interface LoanRepository {
    void saveLoan(Loan loan);

    boolean isBookAvailable(String bookReference);
}
