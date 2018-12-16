package mvc.service;

import mvc.dao.CompanyRepoImpl;
import mvc.model.Company;

import java.util.List;

public class CompanyService {
    private CompanyRepoImpl companyRepoImpl = new CompanyRepoImpl();

    public void saveToCompanyRepo(Company company) {
        companyRepoImpl.save(company);
    }

    public Company getByIdFromCompanyRepo(Integer id) {
        Company company = companyRepoImpl.getById(id);
        return company;
    }

    public void updateToCompanyRepo(Company company) {
        companyRepoImpl.update(company);
    }

    public List<Company> getAllFromCompanyRepo() {
        List<Company> companies = companyRepoImpl.getAll();
        return companies;
    }

    public void deleteFromCompanyRepo(Integer id) {
        companyRepoImpl.delete(id);
    }
}
