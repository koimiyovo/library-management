package com.tdd;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class LoanBookTest {
    private InMemoryLoanRepository loanRepository;

    private LoanABook loanABook;

    @BeforeEach
    public void setUp() {
        this.loanRepository = new InMemoryLoanRepository();
        this.loanABook = new LoanABook(this::currentDate, loanRepository);
    }

    @Test
    @DisplayName("Loan a book")
    public void loanABook() {
        Loan expectedLoan = new Loan("loaner@gmail.com", List.of("REF001"), currentDate(), Duration.ofDays(2));

        loanABook.execute(List.of("REF001"), "loaner@gmail.com", Duration.ofDays(2));

        Loan actualLoan = loanRepository.lastLoan();
        expectBookLoaned(expectedLoan, actualLoan);
    }

    @Test
    @DisplayName("Loan another book")
    public void loanAnotherBook() {
        Loan expectedLoan = new Loan("loaner@gmail.com", List.of("REF002"), currentDate(), Duration.ofDays(2));
        loanABook.execute(List.of("REF002"), "loaner@gmail.com", Duration.ofDays(2));

        Loan actualLoan = loanRepository.lastLoan();
        expectBookLoaned(expectedLoan, actualLoan);
    }

    @Test
    @DisplayName("Should not loan unavailable book")
    public void shouldNotLoanUnavailableBook() {
        loanRepository.feedWith(new Loan("loaner@gmail.com", List.of("UNVLB-REF"), currentDate(), Duration.ofDays(2)));

        loanABook.execute(List.of("UNVLB-REF"), "loaner@gmail.com", Duration.ofDays(2));

        assertEquals(List.of(new Loan("loaner@gmail.com", List.of("UNVLB-REF"), currentDate(), Duration.ofDays(2))), loanRepository.loans);
    }

    @Test
    @DisplayName("Should not loan another unavailable book - Other case")
    public void shouldNotLoanUnavailableBookOtherCase() {
        loanRepository.feedWith(new Loan("loaner@gmail.com", List.of("ANTHR-UNVLB-REF"), currentDate(), Duration.ofDays(2)));
        loanABook.execute(List.of("ANTHR-UNVLB-REF"), "loaner@gmail.com", Duration.ofDays(2));
        assertEquals(List.of(new Loan("loaner@gmail.com", List.of("ANTHR-UNVLB-REF"), currentDate(), Duration.ofDays(2))), loanRepository.loans);
    }

    @Test
    public void shouldLoanTwoBooks() {
        Loan expectedLoan = new Loan("loaner@gmail.com", List.of("REF001", "REF002"), currentDate(), Duration.ofDays(2));

        loanABook.execute(List.of("REF001", "REF002"), "loaner@gmail.com", Duration.ofDays(2));

        Loan actualLoan = loanRepository.lastLoan();
        expectBookLoaned(expectedLoan, actualLoan);
    }

    @Test
    public void shouldNotLoanBooksWhenOneIsUnavailable() {
        loanRepository.feedWith(new Loan("loaner@gmail.com", List.of("REF001"), currentDate(), Duration.ofDays(2)));

        loanABook.execute(List.of("REF001", "REF002"), "loaner@gmail.com", Duration.ofDays(2));

        assertEquals(List.of(new Loan("loaner@gmail.com", List.of("REF001"), currentDate(), Duration.ofDays(2))), loanRepository.loans);
    }

    private Date currentDate() {
        DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        try {
            return sdf.parse("25/02/1994 00:00");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }


    private void expectBookLoaned(Loan expectedLoan, Loan actualLoan) {
        assertEquals(expectedLoan, actualLoan);
        assertEquals(Duration.ofDays(2), expectedLoan.loanDuration);
    }
}
