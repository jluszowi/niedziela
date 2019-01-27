import java.sql.*;

public class Lab61 {

    public static void main(String[] args) throws SQLException {

        String connectionUrl =
                "jdbc:sqlserver://morfeusz.wszib.edu.pl:1433;databaseName=AdventureWorks;user=jluszowi;password=Java2Jo";

        Connection con = DriverManager.getConnection(connectionUrl);
        Statement stmt = con.createStatement();

        ResultSet rs = stmt.executeQuery("select top 10 LastName, FirstName From Person.Contact where LastName = 'Anderson'");

        while (rs.next()) {
            System.out.println(rs.getString("LastName") + " " + rs.getString("FirstName"));
        }

        ResultSet rs1 = stmt.executeQuery("select Distinct Title From HumanResources.Employee");

        while (rs1.next()) {
            System.out.println(rs1.getString("Title"));


        }
        rs.close();
        stmt.close();
        con.close();

    }
}
