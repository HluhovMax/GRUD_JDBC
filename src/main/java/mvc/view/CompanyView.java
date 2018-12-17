package mvc.view;

import mvc.controller.CompanyController;
import mvc.model.Company;
import mvc.model.Customer;
import mvc.model.Project;


import java.util.List;
import java.util.Scanner;

public class CompanyView {
    private CompanyController companyController = new CompanyController();
    private Scanner intScanner = new Scanner(System.in);
    private Scanner stringScanner = new Scanner(System.in);
    private Company company = new Company();

    public void save() {
        System.out.println("enter company name:");
        company.setCompany(stringScanner.nextLine());
        if (company != null) {
            companyController.save(company);
        }
    }

    public Company getById() {
        System.out.println("enter company id:");
        company = companyController.getById(intScanner.nextInt());
        if (company != null) {
            System.out.println(company);
            return company;
        }
        return null;
    }

    public void update() {
        System.out.println("enter id, company");
        company.setId(intScanner.nextInt());
        company.setCompany(stringScanner.nextLine());
        if (company != null) {
            companyController.update(company);
        }
    }

    public List<Company> getAll() {
        List<Company> companies = companyController.getAll();
        if (companies != null) {
            for (Company company:companies
                 ) {
                System.out.println(company);
            }
            return companies;
        }
        return null;
    }

    public void delete() {
        System.out.println("enter id:");
        companyController.delete(intScanner.nextInt());
    }

    public void insert() {
        System.out.println("enter company_id, customer_id, project_id");
        company.setId(intScanner.nextInt());
        Customer customer = new Customer();
        customer.setId(intScanner.nextInt());
        Project project = new Project();
        project.setId(intScanner.nextInt());
        company.setCustomer(customer);
        company.setProject(project);
        if (company != null) {
            companyController.insert(company);
        }
    }
}
