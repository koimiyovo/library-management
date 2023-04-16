package com.tdd.infrastructure;

import com.tdd.domain.Loan;
import com.tdd.domain.LoanRepository;
import org.json.simple.JSONArray;
import org.postgresql.util.PGInterval;
import org.postgresql.util.PGobject;


import java.sql.*;
import java.util.List;


public class DbLoanRepository implements LoanRepository {
    private String url;

    public DbLoanRepository() {
        this.url = "jdbc:postgresql://127.0.0.1:5432/library_management?user=postgres&password=admin&ssl=false";
    }

    @Override
    public void saveLoan(Loan loan) {
        try {
            Connection connection = DriverManager.getConnection(url);
            PreparedStatement st = connection.prepareStatement("INSERT INTO LOANS  (loaner, book_references, loaned_at, duration) VALUES(?, ?, ?, ?)");
            st.setString(1, loan.loaner);

            PGobject jsonObject = new PGobject();
            jsonObject.setType("json");
            JSONArray jsArray = new JSONArray();
            jsArray.addAll(loan.bookReferences);
            jsonObject.setValue(jsArray.toJSONString());

            st.setObject(2, jsonObject);
            java.sql.Date d = new Date(loan.loanDate.getYear(), loan.loanDate.getMonth(), loan.loanDate.getDate());
            st.setDate(3, d);

            PGInterval interval = new PGInterval(loan.loanDuration.toDays() + " days");
            st.setObject(4, interval);
            st.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean areBookAvailable(List<String> bookReference) {
        return false;
    }
}
