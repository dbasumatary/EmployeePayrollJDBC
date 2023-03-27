import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.*;

public class DatabaseOperations {
    public Connection getConnection() throws SQLException{
        String jdbcURL = "jdbc:mysql://localhost:3306/payroll_service?allowPublicKeyRetrieval=true&useSSL=false";
        String userName = "root";
        String password = "DBUnited23@sql";
        Connection connection;
        System.out.println("Connecting to database...");
        connection = DriverManager.getConnection(jdbcURL,userName,password);
        System.out.println("Connection is Successful to connection "+ connection);
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
                System.out.println("*****************************************");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //UC4 - Updating the salary and sync it with database using JDBC Prepared Statement
    public void updateBasicPay(String name, double newBasicPay){
        try {
            Connection connection = this.getConnection();

            //The query updates the Basic_Pay column of the employee_payroll table for the employee with specified Name.
            String query = "UPDATE employee_payroll SET Basic_Pay = ? WHERE Name = ?";

            //The PreparedStatement object is used to set the new value of basicPay and name
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setDouble(1, newBasicPay);
            statement.setString(2, name);

            //the executeUpdate() method returns the number of rows affected by the update.
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Basic Pay updated successfully for employee with name " + name);
            } else {
                System.out.println("No employee found with name " + name);
            }
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception{
        DatabaseOperations databaseOperations = new DatabaseOperations();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int flag = -1;
        while (flag != 0){
            System.out.println("*****************************************");
            System.out.println("Enter your choice:");
            System.out.println("\n1. Display employee details\n2. Update basic pay\n3. Exit");
            flag = Integer.parseInt(bufferedReader.readLine());
            switch (flag){
                case 1: databaseOperations.displayDetails();
                        break;
                case 2: System.out.print("Enter the name: ");
                        String name = bufferedReader.readLine();
                        System.out.print("Enter the updated basic pay for " + name + ": ");
                        Double newPay = Double.parseDouble(bufferedReader.readLine());
                        databaseOperations.updateBasicPay(name,newPay);
                        break;
                case 3: flag = 0;
                        break;
            }
        }
    }
}
