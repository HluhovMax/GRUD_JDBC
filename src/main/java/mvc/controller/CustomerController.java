package mvc.controller;

import mvc.model.Customer;
import mvc.service.CustomerService;

import java.util.List;

public class CustomerController {
    private CustomerService customerService = new CustomerService();

    public void save(Customer customer) {
        customerService.saveToCustomerRepo(customer);
    }

    public Customer getById(Integer id) {
        Customer customer = customerService.getByIdFromCustomerRepo(id);
        return customer;
    }

    public void update(Customer customer) {
        customerService.updateToCustomerRepo(customer);
    }

    public List<Customer> getAll() {
        List<Customer> customers = customerService.getAllFromCustomerRepo();
        return customers;
    }

    public void delete(Integer id) {
        customerService.deleteFromCustomerRepo(id);
    }

}
