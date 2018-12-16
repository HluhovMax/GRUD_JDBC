package mvc.service;

import mvc.dao.CustomerRepoImpl;
import mvc.model.Customer;

import java.util.List;

public class CustomerService {
    private CustomerRepoImpl customerRepoImpl = new CustomerRepoImpl();

    public void saveToCustomerRepo(Customer customer) {
        customerRepoImpl.save(customer);
    }

    public Customer getByIdFromCustomerRepo(Integer id) {
        Customer customer = customerRepoImpl.getById(id);
        return customer;
    }

    public void updateToCustomerRepo(Customer customer) {
        customerRepoImpl.update(customer);
    }

    public List<Customer> getAllFromCustomerRepo() {
        List<Customer> customers = customerRepoImpl.getAll();
        return customers;
    }

    public void deleteFromCustomerRepo(Integer id) {
        customerRepoImpl.delete(id);
    }
}
