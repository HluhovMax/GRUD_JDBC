package mvc.repository.jdbc;

import mvc.model.Project;

import java.util.List;

public interface ProjectRepository extends GenericRepository<Project, Integer> {

    @Override
    void save(Project project);

    @Override
    Project getById(Integer integer);

    @Override
    void update(Project project);

    @Override
    List<Project> getAll();

    @Override
    void delete(Integer integer);
}
