package mvc.controller;

import mvc.model.Company;
import mvc.service.CompanyService;

import java.util.List;

public class CompanyController {
    private CompanyService companyService = new CompanyService();

    public void save(Company company) {
        companyService.saveToCompanyRepo(company);
    }

    public Company getById(Integer id) {
        Company company = companyService.getByIdFromCompanyRepo(id);
        return company;
    }

    public void update(Company company) {
        companyService.updateToCompanyRepo(company);
    }

    public List<Company> getAll() {
        List<Company> companies = companyService.getAllFromCompanyRepo();
        return companies;
    }

    public void delete(Integer id) {
        companyService.deleteFromCompanyRepo(id);
    }
}
