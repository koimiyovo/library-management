package com.tdd;


import org.junit.jupiter.api.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class LoanBookTest {
	@Test
	public void loanABook() {
		InMemoryLoanRepository loanRepository = new InMemoryLoanRepository();
		Loan expectedLoan = new Loan("loaner@gmail.com", "REF001", currentDate());

		LoanABook loanABook = new LoanABook(this::currentDate, loanRepository);
		loanABook.execute("REF001", "loaner@gmail.com");

		Loan actualLoan = loanRepository.lastLoan();

		assertEquals(expectedLoan.bookReference, actualLoan.bookReference);
		assertEquals(expectedLoan.loaner, actualLoan.loaner);
		assertEquals(expectedLoan.loanDate.compareTo(actualLoan.loanDate), 0);
	}

	@Test
	public void loanAnotherBook() {
		InMemoryLoanRepository loanRepository = new InMemoryLoanRepository();
		Loan expectedLoan = new Loan("loaner@gmail.com", "REF002", currentDate());
		LoanABook loanABook = new LoanABook(this::currentDate, loanRepository);
		loanABook.execute("REF002", "loaner@gmail.com");

		Loan actualLoan = loanRepository.lastLoan();

		assertEquals(expectedLoan.bookReference, actualLoan.bookReference);
		assertEquals(expectedLoan.loaner, actualLoan.loaner);
		assertEquals(expectedLoan.loanDate.compareTo(actualLoan.loanDate), 0);
	}

	private Date currentDate() {
		DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		try {
			return sdf.parse("25/02/1994 00:00");
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}

}
