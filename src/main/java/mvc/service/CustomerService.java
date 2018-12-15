package mvc.service;

import mvc.dao.CustomerRepo;
import mvc.model.Customer;

import java.util.List;

public class CustomerService {
    private CustomerRepo customerRepo = new CustomerRepo();

    public void saveToCustomerRepo(Customer customer) {
        customerRepo.save(customer);
    }

    public Customer getByIdFromCustomerRepo(Integer id) {
        Customer customer = customerRepo.getById(id);
        return customer;
    }

    public void updateToCustomerRepo(Customer customer) {
        customerRepo.update(customer);
    }

    public List<Customer> getAllFromCustomerRepo() {
        List<Customer> customers = customerRepo.getAll();
        return customers;
    }

    public void deleteFromCustomerRepo(Integer id) {
        customerRepo.delete(id);
    }
}
