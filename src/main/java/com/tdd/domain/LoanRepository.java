package com.tdd.domain;

import com.tdd.domain.Loan;

import java.util.List;

public interface LoanRepository {
    void saveLoan(Loan loan);

    boolean areBookAvailable(List<String> bookReference);
}
