package mvc.dao.repository;

import mvc.model.Skill;

import java.util.List;

public interface SkillRepository extends GenericRepository<Skill, Integer> {

    @Override
    void save(Skill skill);

    @Override
    Skill getById(Integer integer);

    @Override
    void update(Skill skill);

    @Override
    List<Skill> getAll();

    @Override
    void delete(Integer integer);
}
