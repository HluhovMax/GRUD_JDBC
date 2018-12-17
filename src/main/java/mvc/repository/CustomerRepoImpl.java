package mvc.repository;

import mvc.model.Project;
import mvc.repository.jdbc.CustomerRepository;
import mvc.model.Customer;
import mvc.util.ConnectionUtil;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerRepoImpl implements CustomerRepository {
    private Connection connection = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    @Override
    public void save(Customer customer) {
        String SQL = "INSERT INTO customer(id, customer) VALUES(?, ?) ";
        insertProjectToCustomer(customer);
        ProjectRepoImpl projectRepo = new ProjectRepoImpl();
        List<Project> projects = customer.getProjects();
        for (Project project : projects
        ) {
            projectRepo.save(project);
        }
        try {
            connection = ConnectionUtil.getConnection();
            preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setInt(1, customer.getId());
            preparedStatement.setString(2, customer.getCustomer());
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
            ConnectionUtil.closeConnection(connection);
        }
    }

    @Override
    public Customer getById(Integer id) {
        String SQL = "SELECT *\n" +
                "FROM (\n" +
                "  SELECT customer.id, customer.customer, cp.project_id, p.project\n" +
                "       FROM customer\n" +
                "  LEFT JOIN customer_project cp on cp.customer_id = customer.id\n" +
                "  LEFT JOIN project p on cp.project_id = p.id) result\n" +
                "WHERE result.id = ?";
        try {
            connection = ConnectionUtil.getConnection();
            preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            List<Project> projects = new ArrayList<>();
            while (resultSet.next()) {
                Project project = new Project();
                project.setId(resultSet.getInt("project_id"));
                project.setProject(resultSet.getString("project"));
                projects.add(project);
            }
            Customer customer = new Customer();
            while (resultSet.first()) {
                customer.setId(resultSet.getInt("id"));
                customer.setCustomer(resultSet.getString("customer"));
                customer.setProjects(projects);
                return customer;
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
            ConnectionUtil.closeConnection(connection);
        }
        return null;
    }

    @Override
    public void update(Customer customer) {
        String SQL = "UPDATE customer SET customer = ? WHERE id = ?";
        try {
            connection = ConnectionUtil.getConnection();
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
            ConnectionUtil.closeConnection(connection);
        }
    }

    @Override
    public List<Customer> getAll() {
        String SQL = "SELECT * FROM customer";
        try {
            connection = ConnectionUtil.getConnection();
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
            ConnectionUtil.closeConnection(connection);
        }
        return null;
    }

    @Override
    public void delete(Integer id) {
        String SQL = "DELETE FROM customer WHERE id = ?";
        try {
            connection = ConnectionUtil.getConnection();
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
            ConnectionUtil.closeConnection(connection);
        }
    }

    private void insertProjectToCustomer(Customer customer) {
        String CUSTOMER_PROJECT = "INSERT INTO customer_project (customer_id, project_id)" +
                "VALUES (?, ?)";
        try {
            List<Project> projects = customer.getProjects();
            for (Project project : projects
            ) {
                connection = ConnectionUtil.getConnection();
                preparedStatement = connection.prepareStatement(CUSTOMER_PROJECT);
                preparedStatement.setInt(1, customer.getId());
                preparedStatement.setInt(2, project.getId());
                preparedStatement.execute();
            }
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
            ConnectionUtil.closeConnection(connection);
        }
    }
}
