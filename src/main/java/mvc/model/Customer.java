package mvc.model;

public class Customer {
    private int id;
    private String customer;

    public Customer() {
    }

    public Customer(int id, String customer) {
        this.id = id;
        this.customer = customer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", customer='" + customer + '\'' +
                '}';
    }
}
