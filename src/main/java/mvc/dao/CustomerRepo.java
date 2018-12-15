package mvc.dao;

import mvc.model.Customer;
import mvc.util.ConnectionUtil;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerRepo implements GenericRepository<Customer, Integer>{
    private ConnectionUtil connectionUtil = new ConnectionUtil();
    private String SQL = "";
    private Connection connection = connectionUtil.getConnection();
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    @Override
    public void save(Customer customer) {
        SQL = "INSERT INTO customer(customer) VALUES(?) ";
        try {
            preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setString(1, customer.getCustomer());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            connectionUtil.closeConnection(connection);
        }
    }

    @Override
    public Customer getById(Integer id) {
        SQL = "SELECT * FROM customer WHERE id = ?";
        try {
            preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            Customer company = new Customer();
            while (resultSet.next()) {
                company.setId(resultSet.getInt("id"));
                company.setCustomer(resultSet.getString("customer"));
                return company;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            connectionUtil.closeConnection(connection);
        }
        return null;
    }

    @Override
    public void update(Customer customer) {
        SQL = "UPDATE customer SET customer = ? WHERE id = ?";
        try {
            preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setString(1, customer.getCustomer());
            preparedStatement.setInt(2, customer.getId());
            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            connectionUtil.closeConnection(connection);
        }
    }

    @Override
    public List<Customer> getAll() {
        SQL = "SELECT * FROM customer";
        try {
            preparedStatement = connection.prepareStatement(SQL);
            resultSet = preparedStatement.executeQuery();
            List<Customer> customers = new ArrayList<>();
            while (resultSet.next()) {
                Customer customer = new Customer();
                customer.setId(resultSet.getInt("id"));
                customer.setCustomer(resultSet.getString("customer"));
                customers.add(customer);
            }
            return customers;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            connectionUtil.closeConnection(connection);
        }
        return null;
    }

    @Override
    public void delete(Integer id) {
        SQL = "DELETE FROM customer WHERE id = ?";
        try {
            preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            connectionUtil.closeConnection(connection);
        }
    }
}
