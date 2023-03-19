package com.tdd;

import java.util.List;

public interface LoanRepository {
    void saveLoan(Loan loan);

    boolean areBookAvailable(List<String> bookReference);
}
