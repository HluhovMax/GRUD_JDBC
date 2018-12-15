package mvc.dao;

import mvc.model.Company;
import mvc.util.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CompanyRepo implements GenericRepository<Company, Integer>{
    private ConnectionUtil connectionUtil = new ConnectionUtil();
    private String SQL = "";
    private Connection connection = connectionUtil.getConnection();

    @Override
    public void save(Company company) throws SQLException {
        SQL = "INSERT INTO company(company) VALUES(?) ";
        PreparedStatement preparedStatement = connection.prepareStatement(SQL);
        preparedStatement.setString(1, company.getCompany());
        preparedStatement.execute();
        connectionUtil.closeConnection(connection);
    }

    @Override
    public Company getById(Integer id) throws SQLException {
        SQL = "SELECT * FROM company WHERE id = ?";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
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
        }finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (resultSet != null) {
                resultSet.close();
            }
            connectionUtil.closeConnection(connection);
        }
        return null;
    }

    @Override
    public void update(Company company) throws SQLException {

    }

    @Override
    public List<Company> getAll() throws SQLException {
        return null;
    }

    @Override
    public void delete(Integer id) throws SQLException {

    }
}
