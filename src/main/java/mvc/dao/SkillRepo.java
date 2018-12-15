package mvc.dao;


import mvc.model.Skill;
import mvc.util.ConnectionUtil;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SkillRepo implements GenericRepository<Skill, Integer>{
    private ConnectionUtil connectionUtil = new ConnectionUtil();
    private String SQL = "";
    private Connection connection = connectionUtil.getConnection();
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    @Override
    public void save(Skill skill) {
        SQL = "INSERT INTO skills(skill) VALUES(?) ";
        try {
            preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setString(1, skill.getSkill());
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
    public Skill getById(Integer id) {
        SQL = "SELECT * FROM skills WHERE id = ?";
        try {
            preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            Skill skill = new Skill();
            while (resultSet.next()) {
                skill.setId(resultSet.getInt("id"));
                skill.setSkill(resultSet.getString("skill"));
                return skill;
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
    public void update(Skill skill) {
        SQL = "UPDATE skills SET skill = ? WHERE id = ?";
        try {
            preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setString(1, skill.getSkill());
            preparedStatement.setInt(2, skill.getId());
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
    public List<Skill> getAll() {
        SQL = "SELECT * FROM skills";
        try {
            preparedStatement = connection.prepareStatement(SQL);
            resultSet = preparedStatement.executeQuery();
            List<Skill> skills = new ArrayList<>();
            while (resultSet.next()) {
                Skill skill = new Skill();
                skill.setId(resultSet.getInt("id"));
                skill.setSkill(resultSet.getString("skill"));
                skills.add(skill);
            }
            return skills;
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
        SQL = "DELETE FROM skills WHERE id = ?";
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
