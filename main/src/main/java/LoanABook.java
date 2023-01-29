package main.src.main.java;

import java.util.Date;
import java.util.function.Supplier;

public class LoanABook {
    private Supplier<Date> loanDateProvider;
    private LoanRepository loanRepository;

    public LoanABook(Supplier<Date> loanDateProvider, LoanRepository loanRepository) {
        this.loanDateProvider = loanDateProvider;
        this.loanRepository = loanRepository;
    }

    public void execute(String bookReference, String loanerEmail) {
        Loan loanToSave = new Loan(loanerEmail, bookReference, loanDateProvider.get());
        loanRepository.saveLoan(loanToSave);
    }
}
