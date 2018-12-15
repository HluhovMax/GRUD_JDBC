package mvc.dao;

import mvc.model.Customer;
import mvc.util.ConnectionUtil;

import java.sql.SQLException;
import java.util.List;

public class CustomerRepo implements GenericRepository<Customer, Integer>{
    private ConnectionUtil connectionUtil = new ConnectionUtil();

    @Override
    public void save(Customer customer) throws SQLException {

    }

    @Override
    public Customer getById(Integer integer) throws SQLException {
        return null;
    }

    @Override
    public void update(Customer customer) throws SQLException {

    }

    @Override
    public List<Customer> getAll() throws SQLException {
        return null;
    }

    @Override
    public void delete(Integer integer) throws SQLException {

    }
}
