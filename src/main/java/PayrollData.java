import java.time.LocalDate;

public class PayrollData {
    int id;
    String name;
    LocalDate startDate;
    double basicPay;
    double deductions;
    double taxablePay;
    double incomeTax;
    double netPay;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public LocalDate getStartDate() {
        return startDate;
    }
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }
    public double getBasicPay() {
        return basicPay;
    }
    public void setBasicPay(double basicPay) {
        this.basicPay = basicPay;
    }
    public double getDeductions() {
        return deductions;
    }
    public void setDeductions(double deductions) {
        this.deductions = deductions;
    }
    public double getTaxablePay() {
        return taxablePay;
    }
    public void setTaxablePay(double taxablePay) {
        this.taxablePay = taxablePay;
    }
    public double getIncomeTax() {
        return incomeTax;
    }
    public void setIncomeTax(double incomeTax) {
        this.incomeTax = incomeTax;
    }
    public double getNetPay() {
        return netPay;
    }
    public void setNetPay(double netPay) {
        this.netPay = netPay;
    }
    public PayrollData() {
        super();
    }
    public PayrollData(int id,String name,double basicPay, double deductions, double taxablePay, double incomeTax, double netPay, LocalDate startDate){
        super();
        this.id = id;
        this.name = name;
        this.basicPay = basicPay;
        this.deductions = deductions;
        this.taxablePay = taxablePay;
        this.incomeTax = incomeTax;
        this.netPay = netPay;
        this.startDate = startDate;
    }
    @Override
    public String toString() {
        return "EmployeeDetails [id=" + id + ", name=" + name + ", basicPay=" + basicPay + ", deductions=" + deductions + ", " +
                "taxablePay=" + taxablePay + ", incomeTax=" + incomeTax + ", netPay=" + netPay + ", startDate=" + startDate +"]";
    }
}