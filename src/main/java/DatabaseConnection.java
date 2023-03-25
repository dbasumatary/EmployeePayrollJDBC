import java.sql.*;

public class DatabaseConnection {

    public static void checkDatabaseConnection() {
        // Database credentials
        String url = "jdbc:mysql://localhost:3306/payroll_service";
        String username = "root";
        String pwd = "DBUnited23@sql";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, username, pwd);
            System.out.println("Connection successful to " + url);

            //query to be run for checking connection
            String query = "SELECT * FROM employee_payroll";
            Statement stmt = con.createStatement();

            //query is executed
            ResultSet resultSet = stmt.executeQuery(query);
            resultSet.next();

            //retrieve Name from database
            String name = resultSet.getString("Name");
            System.out.println(name);

            //close the statement and connection
            stmt.close();
            con.close();
            System.out.println("Connection closed");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        checkDatabaseConnection();
    }
}

