import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Db  {
    public static void main(String[] args) {
        try {
            // Load the JDBC driver
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
// Set the path to the sqljdbc_auth.dll library
// System.setProperty("java.library.path", "C:\\Program Files (x86)\\Microsoft JDBC DRIVER 12.4 for SQL Server\\auth\\x64\\mssql-jdbc_auth-12.4.2.x64.dll");
            // Configure JDBC connection URL
            String jdbcUrl = "jdbc:sqlserver://DESKTOP-IVJF0DT\\SQLEXPRESS;databaseName=java;intergratedSecurity=false;encrypt=false";
            String username = "usr";
            String password = "1234";

            // Establish JDBC connection
            Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
            

            // Use the connection to perform database operations
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM hall");

            resultSet.next();

            System.out.println(resultSet.getString("name"));
            // Process the result set or perform other operationss
            // Close the connection when done
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
            // System.out.println(e);
        }
    }
}
