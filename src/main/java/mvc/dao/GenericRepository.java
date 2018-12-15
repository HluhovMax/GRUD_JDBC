package mvc.dao;

import java.sql.SQLException;
import java.util.List;

public interface GenericRepository<T, ID> {
    void save(T t) throws SQLException;

    T getById(ID id) throws SQLException;

    void update(T t) throws SQLException;

    List<T> getAll() throws SQLException;

    void delete(ID id) throws SQLException;
}
