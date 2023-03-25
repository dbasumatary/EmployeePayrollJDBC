import static org.junit.Assert.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.junit.Test;

public class DatabaseConnectionTest {

    @Test
    public void testCheckDatabaseConnection() {
        // Database credentials
        String url = "jdbc:mysql://localhost:3306/payroll_service";
        String username = "root";
        String pwd = "DBUnited23@sql";

        try {
            // Try to establish connection
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, username, pwd);

            // Check if connection is not null
            assertTrue(con != null);

            // Close the connection
            con.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
