package mvc.repository.jdbc;


import java.util.List;

public interface GenericRepository<T, ID> {
    void save(T t);

    T getById(ID id);

    void update(T t);

    List<T> getAll();

    void delete(ID id);
}
