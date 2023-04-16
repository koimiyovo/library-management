package com.tdd.integration;

import com.tdd.domain.Loan;
import com.tdd.infrastructure.DbLoanRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;

public class DbLoanRepositoryTest {

    @BeforeEach
    public void setUp() {
        String url = "jdbc:postgresql://127.0.0.1:5432/library_management?user=postgres&password=admin&ssl=false";
        try {
            Connection connection = DriverManager.getConnection(url);
            PreparedStatement st = connection.prepareStatement("TRUNCATE TABLE LOANS;");
            st.execute();
            System.out.println(connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

     @Test
    public void testSaveLoan() {
         DbLoanRepository repository = new DbLoanRepository();
         Loan loan = new Loan("loaner@gmail.com", List.of("REF001"), currentDate(), Duration.ofDays(2));
         repository.saveLoan(loan);

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
