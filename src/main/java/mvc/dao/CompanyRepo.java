package mvc.dao;

import mvc.model.Company;
import mvc.util.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CompanyRepo implements GenericRepository<Company, Integer>{
    private ConnectionUtil connectionUtil = new ConnectionUtil();
    private String SQL = "";
    private Connection connection = connectionUtil.getConnection();
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    @Override
    public void save(Company company) {
        SQL = "INSERT INTO company(company) VALUES(?) ";
        try {
            preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setString(1, company.getCompany());
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
    public Company getById(Integer id) {
        SQL = "SELECT * FROM company WHERE id = ?";
        try {
            preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            Company company = new Company();
            while (resultSet.next()) {
                company.setId(resultSet.getInt("id"));
                company.setCompany(resultSet.getString("company"));
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
    public void update(Company company) {
        SQL = "UPDATE company SET company = ? WHERE id = ?";
        try {
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
            connectionUtil.closeConnection(connection);
        }
    }

    @Override
    public List<Company> getAll() {
        SQL = "SELECT * FROM company";
        try {
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
            connectionUtil.closeConnection(connection);
        }
        return null;
    }

    @Override
    public void delete(Integer id) {
        SQL = "DELETE FROM company WHERE id = ?";
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
