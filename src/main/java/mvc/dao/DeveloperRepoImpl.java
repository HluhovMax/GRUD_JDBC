package mvc.dao;

import mvc.dao.repository.DeveloperRepository;
import mvc.model.Developer;
import mvc.model.Skill;
import mvc.util.ConnectionUtil;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DeveloperRepoImpl implements DeveloperRepository {
    private Connection connection = ConnectionUtil.getConnection();
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    @Override
    public void save(Developer developer) {
        String SQL = "INSERT INTO developer(name, specialty, experience, salary) VALUES(?, ?, ?, ?) ";
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
            ConnectionUtil.closeConnection(connection);
        }
    }

    @Override
    public Developer getById(Integer id) {
        String SQL = "SELECT * FROM developer, skills" +
                " LEFT JOIN developer_skill ds on developer.id = ?";
        try {
            preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            Developer developer = new Developer();
            Skill skill = new Skill();
            while (resultSet.next()) {
                developer.setId(resultSet.getInt("id"));
                developer.setName(resultSet.getString("name"));
                developer.setSpecialty(resultSet.getString("specialty"));
                developer.setExperience(resultSet.getInt("experience"));
                developer.setSalary(resultSet.getInt("salary"));
                skill.setSkill(resultSet.getString("skill"));
                developer.setSkill(skill);
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
            ConnectionUtil.closeConnection(connection);
        }
        return null;
    }

    @Override
    public void update(Developer developer) {
        String SQL = "UPDATE developer SET name = ?, specialty = ?, experience = ?, salary = ? WHERE id = ?";
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
            ConnectionUtil.closeConnection(connection);
        }
    }

    @Override
    public List<Developer> getAll() {
        String SQL = "SELECT * FROM developer";
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
            ConnectionUtil.closeConnection(connection);
        }
        return null;
    }

    @Override
    public void delete(Integer id) {
        String SQL = "DELETE FROM developer WHERE id = ?";
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
            ConnectionUtil.closeConnection(connection);
        }
    }

    public void insertSkillsForDeveloper(Developer developer) {
        String DEVELOPER_SKILLS = "INSERT INTO developer_skill(developer_id, skill_id)" +
                "VALUES (?, ?) ";
        try {
            preparedStatement = connection.prepareStatement(DEVELOPER_SKILLS);
            preparedStatement.setInt(1, developer.getId());
            preparedStatement.setInt(2, developer.getSkill().getId());
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
}
