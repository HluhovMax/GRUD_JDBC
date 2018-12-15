package mvc;

import mvc.dao.CompanyRepo;
import mvc.model.Company;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        CompanyRepo companyRepo = new CompanyRepo();

//        try {
//            companyRepo.save(new Company(2, "Apple"));
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

        try {
            System.out.println(companyRepo.getById(1));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
