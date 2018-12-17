package mvc.repository;

import mvc.model.Developer;
import mvc.repository.jdbc.ProjectRepository;
import mvc.model.Project;
import mvc.util.ConnectionUtil;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProjectRepoImpl implements ProjectRepository {
    private Connection connection = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    @Override
    public void save(Project project) {
        String SQL = "INSERT INTO project(id, project) VALUES(?, ?) ";
        insertDeveloperToProject(project);
        DeveloperRepoImpl developerRepo = new DeveloperRepoImpl();
        List<Developer> developers = project.getDevelopers();
        for (Developer developer : developers
        ) {
            developerRepo.save(developer);
        }
        try {
            connection = ConnectionUtil.getConnection();
            preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setInt(1, project.getId());
            preparedStatement.setString(2, project.getProject());
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
    public Project getById(Integer id) {
        String SQL = "SELECT *\n" +
                "FROM (\n" +
                "       SELECT project.id, project.project, pd.developer_id, d.name, d.specialty, d.experience, d.salary\n" +
                "       FROM project\n" +
                "              LEFT JOIN project_developer pd on pd.project_id = project.id\n" +
                "  LEFT JOIN developer d on pd.developer_id = d.id) result\n" +
                "WHERE result.id = ?";
        try {
            connection = ConnectionUtil.getConnection();
            preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            List<Developer> developers = new ArrayList<>();
            while (resultSet.next()) {
                Developer developer = new Developer();
                developer.setId(resultSet.getInt("developer_id"));
                developer.setName(resultSet.getString("name"));
                developer.setSpecialty(resultSet.getString("specialty"));
                developer.setExperience(resultSet.getInt("experience"));
                developer.setSalary(resultSet.getInt("salary"));
                developers.add(developer);
            }
            Project project = new Project();
            while (resultSet.first()) {
                project.setId(resultSet.getInt("id"));
                project.setProject(resultSet.getString("project"));
                project.setDevelopers(developers);
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
            ConnectionUtil.closeConnection(connection);
        }
        return null;
    }

    @Override
    public void update(Project project) {
        String SQL = "UPDATE project SET project = ?, cost = ? WHERE id = ?";
        try {
            connection = ConnectionUtil.getConnection();
            preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setString(1, project.getProject());
            //preparedStatement.setInt(2, project.getCost());
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
            ConnectionUtil.closeConnection(connection);
        }
    }

    @Override
    public List<Project> getAll() {
        String SQL = "SELECT * FROM customer";
        try {
            connection = ConnectionUtil.getConnection();
            preparedStatement = connection.prepareStatement(SQL);
            resultSet = preparedStatement.executeQuery();
            List<Project> projects= new ArrayList<>();
            while (resultSet.next()) {
                Project project = new Project();
                project.setId(resultSet.getInt("id"));
                project.setProject(resultSet.getString("project"));
               // project.setCost(resultSet.getInt("cost"));
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
            ConnectionUtil.closeConnection(connection);
        }
        return null;
    }

    @Override
    public void delete(Integer id) {
        String SQL = "DELETE FROM project WHERE id = ?";
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

    private void insertDeveloperToProject(Project project) {
        String PROJECT_DEVELOPER_SQL = "INSERT INTO project_developer(project_id, developer_id) VALUES (?,?)";
        try {
            List<Developer> developers = project.getDevelopers();
            for (Developer developer : developers
            ) {
                connection = ConnectionUtil.getConnection();
                preparedStatement = connection.prepareStatement(PROJECT_DEVELOPER_SQL);
                preparedStatement.setInt(1, project.getId());
                preparedStatement.setInt(2, developer.getId());
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
