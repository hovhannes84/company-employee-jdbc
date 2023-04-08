import model.Company;
import model.Employee;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CompanyEmployeeMain {
    private static Scanner scanner = new Scanner(System.in);
    private static Connection connection = DBConectionProvider.getInstance().getConnection();
    public static void main(String[] args) {
        System.out.println("Please input employee name,surname,email,company_Id");
        String companyStr = scanner.nextLine();
        String[] employeeData = companyStr.split(",");
        Employee employee = new Employee();
        employee.setName(employeeData[0]);
        employee.setSurname(employeeData[1]);
        employee.setEmail(employeeData[2]);
        employee.setCompany_id(Integer.parseInt(employeeData[3]));
        saveEmployeeToDB(employee);


//        List<Company> companyList = getAllCompaniesFromDB();
//        for (Company company : companyList) {
//            System.out.println(company);
//        }


    }

    private static void saveEmployeeToDB(Employee employee) {
        try {
            Statement statement1 = connection.createStatement();
            statement1.executeUpdate("insert into employee (name,surname,email,company_id) values ('"+ employee.getName()+"','"+ employee.getSurname()+"','" + employee.getEmail()+ "','" + employee.getCompany_id()+ "');");
            System.out.println("Employee inserted into DB");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private static List<Company> getAllCompaniesFromDB() {
        List<Company> companyList = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from company");

            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String name  = resultSet.getString("name");
                String country  = resultSet.getString("country");
                Company company = new Company();
                company.setId(id);
                company.setName(name);
                company.setCountry(country);
                companyList.add(company);


            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return companyList;
    }

    private static void saveCompanyToDB(Company company) {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("insert into company(name,country) values ('" + company.getName()+"','" + company.getCountry()+"');");
            System.out.println("Company inserted into DB");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
