package mvc.repository;

import mvc.repository.jdbc.DeveloperRepository;
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
    private Connection connection = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    @Override
    public void save(Developer developer) {
        String SQL = "INSERT INTO developer(id, name, specialty, experience, salary) VALUES(?, ?, ?, ?, ?) ";
        insertSkillsForDeveloper(developer);
        SkillRepoImpl skillRepo = new SkillRepoImpl();
        List<Skill> skills = developer.getSkills();
        for (Skill skill : skills
        ) {
            skillRepo.save(skill);
        }
        try {
            connection = ConnectionUtil.getConnection();
            preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setInt(1, developer.getId());
            preparedStatement.setString(2, developer.getName());
            preparedStatement.setString(3, developer.getSpecialty());
            preparedStatement.setInt(4, developer.getExperience());
            preparedStatement.setInt(5, developer.getSalary());
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
        String SQL = "SELECT *\n" +
                "FROM (\n" +
                "SELECT developer.id, developer.name, developer.specialty, developer.experience, developer.salary, ds.skill_id, s.skill\n" +
                "FROM developer\n" +
                "       LEFT JOIN developer_skill ds on ds.developer_id = developer.id\n" +
                "       LEFT JOIN skills s on ds.skill_id = s.id) result\n" +
                "WHERE result.id = ?";
        try {
            connection = ConnectionUtil.getConnection();
            preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            List<Skill> skills = new ArrayList<>();
            while (resultSet.next()) {
                Skill skill = new Skill();
                skill.setId(resultSet.getInt("skill_id"));
                skill.setSkill(resultSet.getString("skill"));
                skills.add(skill);
            }
            Developer developer = new Developer();
            while (resultSet.first()) {
                developer.setId(resultSet.getInt("id"));
                developer.setName(resultSet.getString("name"));
                developer.setSpecialty(resultSet.getString("specialty"));
                developer.setExperience(resultSet.getInt("experience"));
                developer.setSalary(resultSet.getInt("salary"));
                developer.setSkills(skills);
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
            connection = ConnectionUtil.getConnection();
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
            connection = ConnectionUtil.getConnection();
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

    private void insertSkillsForDeveloper(Developer developer) {
        String DEVELOPER_SKILLS = "INSERT INTO developer_skill(developer_id, skill_id)" +
                "VALUES (?, ?) ";
        try {
            List<Skill> skills = developer.getSkills();
            for (Skill skill : skills) {
                connection = ConnectionUtil.getConnection();
                preparedStatement = connection.prepareStatement(DEVELOPER_SKILLS);
                preparedStatement.setInt(1, developer.getId());
                preparedStatement.setInt(2, skill.getId());
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
