package main.src.test.java;

import main.src.main.java.Loan;
import main.src.main.java.LoanABook;
import main.src.main.java.InMemoryLoanRepository;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class LoanBookTest {
    @Test
    public void loanABook() {
        InMemoryLoanRepository loanRepository = new InMemoryLoanRepository();
        Loan expectedLoan = new Loan("loaner@gmail.com", "REF001", new Date(2023, 1, 25));
        LoanABook loanABook = new LoanABook(() -> new Date(2023, 1, 25), loanRepository);
        loanABook.execute("REF001", "loaner@gmail.com");

        Loan actualLoan = loanRepository.lastLoan();

        assertEquals(expectedLoan.bookReference, actualLoan.bookReference);
        assertEquals(expectedLoan.loaner, actualLoan.loaner);
        assertEquals(expectedLoan.loanDate, actualLoan.loanDate);
    }

    @Test
    public void loanAnotherBook() {
        InMemoryLoanRepository loanRepository = new InMemoryLoanRepository();
        Loan expectedLoan = new Loan("loaner@gmail.com", "REF002", new Date(2023, 1, 25));
        LoanABook loanABook = new LoanABook(() -> new Date(2023, 1, 25), loanRepository);
        loanABook.execute("REF002", "loaner@gmail.com");

        Loan actualLoan = loanRepository.lastLoan();

        assertEquals(expectedLoan.bookReference, actualLoan.bookReference);
        assertEquals(expectedLoan.loaner, actualLoan.loaner);
        assertEquals(expectedLoan.loanDate, actualLoan.loanDate);
    }

}
