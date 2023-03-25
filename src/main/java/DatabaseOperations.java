import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.*;

public class DatabaseOperations {
    public Connection getConnection() throws SQLException{
        String jdbcURL = "jdbc:mysql://localhost:3306/payroll_service?useSSL=false";
        String userName = "root";
        String password = "DBUnited23@sql";
        Connection connection;
        System.out.println("Connecting to database ");
        connection = DriverManager.getConnection(jdbcURL,userName,password);
        System.out.println("Connection is Successful "+ connection);
        System.out.println();
        return connection;
    }

    public void displayDetails() {
        try {
            Connection con = this.getConnection();
            String query = "select * from employee_payroll";
            Statement statement = con.createStatement();
            ResultSet set = statement.executeQuery(query);
            while(set.next()) {
                int id = set.getInt("ID");
                String name = set.getString("Name");
                double basicPay = set.getDouble("Basic_Pay");
                double deductions = set.getDouble("Deductions");
                double taxablePay = set.getDouble("Taxable_Pay");
                double incomeTax = set.getDouble("Income_Tax");
                double netPay = set.getDouble("Net_Pay");
                Date startDate = set.getDate("Start");

                System.out.println("ID : "+id);
                System.out.println("Name : "+name);
                System.out.println("Basic Pay : "+basicPay);
                System.out.println("Deductions : "+deductions);
                System.out.println("Taxable Pay : "+taxablePay);
                System.out.println("Income Tax : "+incomeTax);
                System.out.println("Net Pay : "+netPay);
                System.out.println("Start Date : "+startDate);
                System.out.println("*************************************");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int flag = -1;
        while (flag != 0){
            System.out.println("Enter your choice:");
            System.out.println("\n1. Display employee details\n2. Exit");
            flag = Integer.parseInt(bufferedReader.readLine());
            switch (flag){
                case 1: DatabaseOperations databaseOperations = new DatabaseOperations();
                        databaseOperations.displayDetails();
                        break;
                case 2: flag = 0;
                        break;
            }
        }
    }
}
