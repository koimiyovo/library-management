package com.tdd;

import com.tdd.application.SearchBook;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SearchBookTest {
	@Test
	public void nonExistingBook() {
		assertEquals(List.of(), SearchBook.execute("my book"));
	}

	@Test
	public void existingBook() {
		assertEquals(List.of("les miserables 1"), SearchBook.execute("les miserables 1"));
	}

	@Test
	public void existingBook_OtherCase() {
		assertEquals(List.of("toto"), SearchBook.execute("toto"));
	}

	@Test
	public void shouldReturnAllBooksMatchingSearch() {
		assertEquals(List.of("les miserables 1", "les miserables 2"), SearchBook.execute("les miserables"));
	}
}
