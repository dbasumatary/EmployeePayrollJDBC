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
            String query = "select * from employeePayroll";
            Statement statement = con.createStatement();
            ResultSet set = statement.executeQuery(query);
            while(set.next()) {
                int id = set.getInt("ID");
                String name = set.getString("Name");
                String gender = set.getString("Gender");
                double basicPay = set.getDouble("Basic_Pay");
                double deductions = set.getDouble("Deductions");
                double taxablePay = set.getDouble("Taxable_Pay");
                double incomeTax = set.getDouble("Income_Tax");
                double netPay = set.getDouble("Net_Pay");
                Date startDate = set.getDate("Start");
                String phone = set.getString("phone_number");
                String address = set.getString("Address");

                System.out.println("ID : "+id);
                System.out.println("Name : "+name);
                System.out.println("Gender : "+gender);
                System.out.println("Basic Pay : "+basicPay);
                System.out.println("Deductions : "+deductions);
                System.out.println("Taxable Pay : "+taxablePay);
                System.out.println("Income Tax : "+incomeTax);
                System.out.println("Net Pay : "+netPay);
                System.out.println("Start Date : "+startDate);
                System.out.println("Phone number : "+phone);
                System.out.println("Address : "+address);
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
            String query = "UPDATE employeePayroll SET Basic_Pay = ? WHERE Name = ?";

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

    //UC5 - Retrieve employees in the given date range
    public void getEmployeesByDateRange(Date startDate, Date endDate) {
        try {
            Connection con = this.getConnection();
            String query = "SELECT * FROM employeePayroll WHERE Start BETWEEN ? AND ?";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setDate(1, startDate);
            statement.setDate(2, endDate);
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                int id = set.getInt("ID");
                String name = set.getString("Name");
                String gender = set.getString("Gender");
                double basicPay = set.getDouble("Basic_Pay");
                double deductions = set.getDouble("Deductions");
                double taxablePay = set.getDouble("Taxable_Pay");
                double incomeTax = set.getDouble("Income_Tax");
                double netPay = set.getDouble("Net_Pay");
                Date joinDate = set.getDate("Start");
                String phone = set.getString("phone_number");
                String address = set.getString("Address");

                System.out.println("ID : " + id);
                System.out.println("Name : " + name);
                System.out.println("Gender : "+gender);
                System.out.println("Basic Pay : " + basicPay);
                System.out.println("Deductions : " + deductions);
                System.out.println("Taxable Pay : " + taxablePay);
                System.out.println("Income Tax : " + incomeTax);
                System.out.println("Net Pay : " + netPay);
                System.out.println("Join Date : " + joinDate);
                System.out.println("Phone number : "+phone);
                System.out.println("Address : "+address);
                System.out.println("*****************************************");
            }
            statement.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void getSumSalaryByGender(String gender) throws SQLException {
        Connection con = this.getConnection();
        int sum = 0;
        String query = "SELECT SUM(Basic_Pay) FROM employeePayroll WHERE Gender = ? GROUP BY Gender";
        PreparedStatement preparedStatement = con.prepareStatement(query);
        preparedStatement.setString(1, gender);
        ResultSet set = preparedStatement.executeQuery();
        if (set.next()) {
            sum = set.getInt(1);
        }
        set.close();
        preparedStatement.close();
        System.out.println("The sum of basic pay by gender " + gender + " is : " + sum);
    }

    public void getAverageSalaryByGender(String gender) throws SQLException {
        Connection con = this.getConnection();
        double average = 0;
        String query = "SELECT AVG(Basic_Pay) FROM employeePayroll WHERE Gender = ? GROUP BY Gender";
        PreparedStatement preparedStatement = con.prepareStatement(query);
        preparedStatement.setString(1, gender);
        ResultSet rs = preparedStatement.executeQuery();
        if (rs.next()) {
            average = rs.getDouble(1);
        }
        rs.close();
        preparedStatement.close();
        System.out.println("The average basic pay by gender " + gender + " is : " + average);
    }

    public void getMinSalaryByGender(String gender) throws SQLException {
        Connection con = this.getConnection();
        int min = 0;
        String query = "SELECT MIN(Basic_Pay) FROM employeePayroll WHERE Gender = ? GROUP BY Gender";
        PreparedStatement preparedStatement = con.prepareStatement(query);
        preparedStatement.setString(1, gender);
        ResultSet rs = preparedStatement.executeQuery();
        if (rs.next()) {
            min = rs.getInt(1);
        }
        rs.close();
        preparedStatement.close();
        System.out.println("The min basic pay by gender " + gender + " is : " + min);
    }

    public void getMaxSalaryByGender(String gender) throws SQLException {
        Connection con = this.getConnection();
        int max = 0;
        String query = "SELECT MAX(Basic_Pay) FROM employeePayroll WHERE Gender = ? GROUP BY Gender";
        PreparedStatement preparedStatement = con.prepareStatement(query);
        preparedStatement.setString(1, gender);
        ResultSet rs = preparedStatement.executeQuery();
        if (rs.next()) {
            max = rs.getInt(1);
        }
        rs.close();
        preparedStatement.close();
        System.out.println("The max basic pay by gender " + gender + " is : " + max);
    }

    public void getCountByGender(String gender) throws SQLException {
        Connection con = this.getConnection();
        int count = 0;
        String query = "SELECT COUNT(*) FROM employeePayroll WHERE Gender = ? GROUP BY Gender";
        PreparedStatement preparedStatement = con.prepareStatement(query);
        preparedStatement.setString(1, gender);
        ResultSet rs = preparedStatement.executeQuery();
        if (rs.next()) {
            count = rs.getInt(1);
        }
        rs.close();
        preparedStatement.close();
        System.out.println("The count of basic pay by gender " + gender + " is : " + count);
    }

    public static void main(String[] args) throws Exception{
        DatabaseOperations databaseOperations = new DatabaseOperations();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int flag = -1;
        while (flag != 0){
            System.out.println("*****************************************");
            System.out.println("Enter your choice:");
            System.out.println("\n1. Display employee details\n2. Update basic pay\n3. Display employee in date range\n4. Database Operations\n5. Exit");
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
                case 3: System.out.print("Enter the start date in format YYYY-MM-DD : ");
                        Date startDate = Date.valueOf(bufferedReader.readLine());
                        System.out.print("Enter the end date in format YYYY-MM-DD : ");
                        Date endDate = Date.valueOf(bufferedReader.readLine());
                        databaseOperations.getEmployeesByDateRange(startDate,endDate);
                        break;
                case 4: int choice = -1;
                        while (choice !=0){
                            System.out.println("\nEnter the operation :");
                            System.out.println("\n1. Sum of salary by gender\n2. Average of salary by gender\n3. Min salary by gender\n4. Max salary by gender\n" +
                                    "5. Count people by gender\n6. Exit");
                            choice = Integer.parseInt(bufferedReader.readLine());
                            switch (choice){
                                case 1: System.out.print("Enter the gender as M or F: ");
                                        databaseOperations.getSumSalaryByGender(bufferedReader.readLine());
                                        break;
                                case 2: System.out.print("Enter the gender as M or F: ");
                                        databaseOperations.getAverageSalaryByGender(bufferedReader.readLine());
                                        break;
                                case 3: System.out.print("Enter the gender as M or F: ");
                                        databaseOperations.getMinSalaryByGender(bufferedReader.readLine());
                                        break;
                                case 4: System.out.print("Enter the gender as M or F: ");
                                        databaseOperations.getMaxSalaryByGender(bufferedReader.readLine());
                                        break;
                                case 5: System.out.print("Enter the gender as M or F: ");
                                        databaseOperations.getCountByGender(bufferedReader.readLine());
                                        break;
                                case 6: choice = 0;
                                        break;
                            }
                        }
                        break;
                case 5: flag = 0;
                        break;
            }
        }
    }
}
