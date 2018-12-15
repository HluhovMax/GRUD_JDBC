package mvc;

import mvc.dao.CompanyRepo;
import mvc.dao.CustomerRepo;
import mvc.model.Company;
import mvc.model.Customer;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        CustomerRepo customerRepo = new CustomerRepo();

        customerRepo.delete(1);
    }
}
