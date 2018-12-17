package mvc.repository;

import mvc.model.Customer;
import mvc.model.Project;
import mvc.repository.jdbc.CompanyRepository;
import mvc.model.Company;
import mvc.util.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CompanyRepoImpl implements CompanyRepository {
    private Connection connection = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    @Override
    public void save(Company company) {
        String SQL = "INSERT INTO company(company) VALUES(?) ";
        insertCustomerToCompany(company);
        insertProjectToCompany(company);
        ProjectRepoImpl projectRepo = new ProjectRepoImpl();
        CustomerRepoImpl customerRepo = new CustomerRepoImpl();
        List<Project> projects = company.getProjects();
        for (Project project : projects
        ) {
            projectRepo.save(project);
        }
        List<Customer> customers = company.getCustomers();
        for (Customer customer : customers
        ) {
            customerRepo.save(customer);
        }
        try {
            connection = ConnectionUtil.getConnection();
            preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setString(1, company.getCompany());
            preparedStatement.execute();
            insertProjectToCompany(company);
            insertCustomerToCompany(company);
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
    public Company getById(Integer id) {
        String COMPANY_PROJECT_SQL = "SELECT *\n" +
                "FROM (\n" +
                "            SELECT company.id, company.company, cp.project_id, p.project\n" +
                "  FROM company\n" +
                "  LEFT JOIN company_project cp on cp.company_id = company.id\n" +
                "  LEFT JOIN project p on cp.project_id = p.id\n" +
                "     ) result\n" +
                "WHERE result.id = ?";
        String COMPANY_CUSTOMER_SQL = "SELECT *\n" +
                "FROM\n" +
                "(\n" +
                "SELECT company.id, company.company, cc.customer_id, c.customer\n" +
                "FROM company\n" +
                "LEFT JOIN company_customer cc on cc.company_id = company.id\n" +
                "LEFT JOIN customer c on cc.customer_id = c.id) result\n" +
                "WHERE result.id = ?";
        try {
            connection = ConnectionUtil.getConnection();
            preparedStatement = connection.prepareStatement(COMPANY_PROJECT_SQL);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            List<Project> projects = new ArrayList<>();
            while (resultSet.next()) {
                Project project = new Project();
                project.setId(resultSet.getInt("project_id"));
                project.setProject(resultSet.getString("project"));
                Company company = new Company();
                company.setId(resultSet.getInt("id"));
                company.setCompany(resultSet.getString("company"));
                project.setCompany(company);
                DeveloperRepoImpl developerRepo = new DeveloperRepoImpl();
                project.setDevelopers(developerRepo.getAll());
                projects.add(project);
            }
            Connection conn = ConnectionUtil.getConnection();
            PreparedStatement statement = conn.prepareStatement(COMPANY_CUSTOMER_SQL);
            statement.setInt(1, id);
            ResultSet set = statement.executeQuery();
            List<Customer> customers = new ArrayList<>();
            while (set.next()) {
                Customer customer = new Customer();
                customer.setId(set.getInt("customer_id"));
                customer.setCustomer(set.getString("customer"));
                ProjectRepoImpl projectRepo = new ProjectRepoImpl();
                customer.setProjects(projectRepo.getAll());
                customers.add(customer);
            }
            Company company = new Company();
            while (resultSet.first()) {
                company.setId(resultSet.getInt("id"));
                company.setCompany(resultSet.getString("company"));
                company.setProjects(projects);
                company.setCustomers(customers);
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
            ConnectionUtil.closeConnection(connection);
        }
        return null;
    }

    @Override
    public void update(Company company) {
        String SQL = "UPDATE company SET company = ? WHERE id = ?";
        try {
            connection = ConnectionUtil.getConnection();
            preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setString(1, company.getCompany());
            preparedStatement.setInt(2, company.getId());
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
    public List<Company> getAll() {
        String SQL = "SELECT * FROM company";
        try {
            connection = ConnectionUtil.getConnection();
            preparedStatement = connection.prepareStatement(SQL);
            resultSet = preparedStatement.executeQuery();
            List<Company> companies = new ArrayList<>();
            while (resultSet.next()) {
                Company company = new Company();
                company.setId(resultSet.getInt("id"));
                company.setCompany(resultSet.getString("company"));
                companies.add(company);
            }
            return companies;
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
        String SQL = "DELETE FROM company WHERE id = ?";
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

    private void insertCustomerToCompany(Company company) {
        String COMPANY_CUSTOMER = "INSERT INTO company_customer(company_id, customer_id)" +
                "VALUES (?, ?)";
        try {
            List<Customer> customers = company.getCustomers();
            for (Customer customer : customers
            ) {
                connection = ConnectionUtil.getConnection();
                preparedStatement = connection.prepareStatement(COMPANY_CUSTOMER);
                preparedStatement.setInt(1, company.getId());
                preparedStatement.setInt(2, customer.getId());
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

    private void insertProjectToCompany(Company company) {
        String COMPANY_PROJECT = "INSERT INTO company_project (company_id, project_id)" +
                "VALUES (?, ?)";
        try {
            List<Project> projects = company.getProjects();
            for (Project project : projects
            ) {
                connection = ConnectionUtil.getConnection();
                preparedStatement = connection.prepareStatement(COMPANY_PROJECT);
                preparedStatement.setInt(1, company.getId());
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
