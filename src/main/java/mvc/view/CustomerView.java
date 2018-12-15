package mvc.view;

import mvc.controller.CustomerController;
import mvc.model.Customer;
import mvc.service.CustomerService;

import java.util.List;
import java.util.Scanner;

public class CustomerView {
    private CustomerController customerController = new CustomerController();
    private Scanner intScanner = new Scanner(System.in);
    private Scanner stringScanner = new Scanner(System.in);
    private Customer customer = new Customer();

    public void save() {
        System.out.println("enter customer name:");
        customer.setCustomer(stringScanner.nextLine());
        if (customer != null) {
            customerController.save(customer);
        }
    }

    public Customer getById() {
        System.out.println("enter id:");
        customer = customerController.getById(intScanner.nextInt());
        if (customer != null) {
            System.out.println(customer);
            return customer;
        }
        return null;
    }

    public void update() {
        System.out.println("enter id, customer");
        customer.setId(intScanner.nextInt());
        customer.setCustomer(stringScanner.nextLine());
        if (customer != null) {
            customerController.update(customer);
        }
    }

    public List<Customer> getAll() {
        List<Customer> customers = customerController.getAll();
        if (customers != null) {
            for (Customer customer : customers
            ) {
                System.out.println(customer);
            }
            return customers;
        }
        return null;
    }

    public void delete() {
        System.out.println("enter id:");
        customerController.delete(intScanner.nextInt());
    }
}
