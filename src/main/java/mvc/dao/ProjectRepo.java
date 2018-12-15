package mvc.dao;

import mvc.model.Project;
import mvc.util.ConnectionUtil;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProjectRepo implements GenericRepository<Project, Integer>{
    private ConnectionUtil connectionUtil = new ConnectionUtil();
    private String SQL = "";
    private Connection connection = connectionUtil.getConnection();
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    @Override
    public void save(Project project) {
        SQL = "INSERT INTO project(project, cost) VALUES(?, ?) ";
        try {
            preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setString(1, project.getProject());
            preparedStatement.setInt(2, project.getCost());
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
    public Project getById(Integer id) {
        SQL = "SELECT * FROM project WHERE id = ?";
        try {
            preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            Project project = new Project();
            while (resultSet.next()) {
                project.setId(resultSet.getInt("id"));
                project.setProject(resultSet.getString("project"));
                project.setCost(resultSet.getInt("cost"));
                return project;
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
    public void update(Project project) {
        SQL = "UPDATE project SET project = ?, cost = ? WHERE id = ?";
        try {
            preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setString(1, project.getProject());
            preparedStatement.setInt(2, project.getCost());
            preparedStatement.setInt(3, project.getId());
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
    public List<Project> getAll() {
        SQL = "SELECT * FROM customer";
        try {
            preparedStatement = connection.prepareStatement(SQL);
            resultSet = preparedStatement.executeQuery();
            List<Project> projects= new ArrayList<>();
            while (resultSet.next()) {
                Project project = new Project();
                project.setId(resultSet.getInt("id"));
                project.setProject(resultSet.getString("project"));
                project.setCost(resultSet.getInt("cost"));
                projects.add(project);
            }
            return projects;
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
        SQL = "DELETE FROM project WHERE id = ?";
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
