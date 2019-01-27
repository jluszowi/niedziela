package budget;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.math.BigDecimal;
import java.sql.*;
import java.text.NumberFormat;

public class HomeBudget {

    private static final String DB_HOST = "morfeusz.wszib.edu.pl";
    private static final int DB_PORT = 1433;
    private static final String DB_USER = "jluszowi";
    private static final String DB_PASS = "Java2Jo";
    private static final String DB_NAME = "jluszowi";



    private static final String INSERT_ENTRY_SQL = "INSERT INTO budget.BudgetEntries(EntryName, Amount) VALUES (?, ?)";
    private static final String GET_BALANCE_SQL = "select SUM (Amount) as Balance from budget.BudgetEntries";

    public static void main(String[] args) {

        // TODO - requied validation - Konieczne jest dopisanie validacji

        BudgetEntry be = new BudgetEntry();
        be.setEntryName(args[0]);
        be.setAmount(new BigDecimal(args[1]));



        HomeBudget hb = new HomeBudget();
        try (Connection con = hb.connect(DB_USER, DB_PASS, DB_NAME);
             Statement stmt = con.createStatement()) {

            PreparedStatement ps = con.prepareStatement(INSERT_ENTRY_SQL);
            ps.setString(1,be.getEntryName());
            ps.setBigDecimal(2,be.getAmount());
            ps.execute();

            BigDecimal balance;
            ResultSet rs = stmt.executeQuery(GET_BALANCE_SQL);

            if(rs.next()) {
                balance = rs.getBigDecimal("Balance");
            } else {
                balance = new BigDecimal(0);
            }

            System.out.print("Zapisano! Nazwa: " + be.getEntryName());
            System.out.print(", kwota: " + hb.currencyFormat(be.getAmount()));
            System.out.print(", saldo: " + hb.currencyFormat(balance));


        } catch (SQLException e) {
            System.out.println("Wystąpił błąd bazy danych: " + e.getMessage());;
        }


    }

    public Connection connect (String username, String password, String dbName) throws SQLServerException {
        SQLServerDataSource ds = new SQLServerDataSource();
        ds.setServerName(DB_HOST);
        ds.setPortNumber(DB_PORT);
        ds.setUser(username);
        ds.setPassword(password);
        ds.setDatabaseName(dbName);
        return ds.getConnection();

    }

    public String currencyFormat(BigDecimal bd) {
        return NumberFormat.getCurrencyInstance().format(bd);
    }

}


// C:\Users\jluszowi.DYDAKTYKA.002\IdeaProjects\niedziela\out\production\niedziela\budget\BudgetEntry.class

// C:\Users\jluszowi.DYDAKTYKA.002\Downloads\Microsoft JDBC Driver 7.0 for SQL Server\sqljdbc_7.0\enu\mssql-jdbc-7.0.0.jre8.jar