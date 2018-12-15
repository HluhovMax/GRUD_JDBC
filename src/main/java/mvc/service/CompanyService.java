package mvc.service;

import mvc.dao.CompanyRepo;
import mvc.model.Company;

import java.util.List;

public class CompanyService {
    private CompanyRepo companyRepo = new CompanyRepo();

    public void saveToCompanyRepo(Company company) {
        companyRepo.save(company);
    }

    public Company getByIdFromCompanyRepo(Integer id) {
        Company company = companyRepo.getById(id);
        return company;
    }

    public void updateToCompanyRepo(Company company) {
        companyRepo.update(company);
    }

    public List<Company> getAllFromCompanyRepo() {
        List<Company> companies = companyRepo.getAll();
        return companies;
    }

    public void deleteFromCompanyRepo(Integer id) {
        companyRepo.delete(id);
    }
}
