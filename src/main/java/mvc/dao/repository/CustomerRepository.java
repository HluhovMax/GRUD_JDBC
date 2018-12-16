package mvc.dao.repository;

import mvc.model.Customer;

import java.util.List;

public interface CustomerRepository extends GenericRepository<Customer, Integer> {

    @Override
    void save(Customer customer);

    @Override
    Customer getById(Integer integer);

    @Override
    void update(Customer customer);

    @Override
    List<Customer> getAll();

    @Override
    void delete(Integer integer);
}
