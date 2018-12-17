package mvc.repository.jdbc;

import mvc.model.Company;

import java.util.List;

public interface CompanyRepository extends GenericRepository<Company, Integer> {

    @Override
    void save(Company company);

    @Override
    Company getById(Integer integer);

    @Override
    void update(Company company);

    @Override
    List<Company> getAll();

    @Override
    void delete(Integer integer);
}
