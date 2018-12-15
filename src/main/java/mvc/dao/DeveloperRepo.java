package mvc.dao;

import mvc.model.Developer;
import mvc.util.ConnectionUtil;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DeveloperRepo implements GenericRepository<Developer, Integer>{
    private ConnectionUtil connectionUtil = new ConnectionUtil();
    private String SQL = "";
    private Connection connection = connectionUtil.getConnection();
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    @Override
    public void save(Developer developer) {
        SQL = "INSERT INTO developer(name, specialty, experience, salary) VALUES(?, ?, ?, ?) ";
        try {
            preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setString(1, developer.getName());
            preparedStatement.setString(2, developer.getSpecialty());
            preparedStatement.setInt(3, developer.getExperience());
            preparedStatement.setInt(4, developer.getSalary());
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
    public Developer getById(Integer id) {
        SQL = "SELECT * FROM developer WHERE id = ?";
        try {
            preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            Developer developer = new Developer();
            while (resultSet.next()) {
                developer.setId(resultSet.getInt("id"));
                developer.setName(resultSet.getString("name"));
                developer.setSpecialty(resultSet.getString("specialty"));
                developer.setExperience(resultSet.getInt("experience"));
                developer.setSalary(resultSet.getInt("salary"));
                return developer;
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
    public void update(Developer developer) {
        SQL = "UPDATE developer SET name = ?, specialty = ?, experience = ?, salary = ? WHERE id = ?";
        try {
            preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setString(1, developer.getName());
            preparedStatement.setString(2, developer.getSpecialty());
            preparedStatement.setInt(3, developer.getExperience());
            preparedStatement.setInt(4, developer.getSalary());
            preparedStatement.setInt(5, developer.getId());
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
    public List<Developer> getAll() {
        SQL = "SELECT * FROM developer";
        try {
            preparedStatement = connection.prepareStatement(SQL);
            resultSet = preparedStatement.executeQuery();
            List<Developer> developers = new ArrayList<>();
            while (resultSet.next()) {
                Developer developer = new Developer();
                developer.setId(resultSet.getInt("id"));
                developer.setName(resultSet.getString("name"));
                developer.setSpecialty(resultSet.getString("specialty"));
                developer.setExperience(resultSet.getInt("experience"));
                developer.setSalary(resultSet.getInt("salary"));
                developers.add(developer);
            }
            return developers;
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
        SQL = "DELETE FROM developer WHERE id = ?";
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
