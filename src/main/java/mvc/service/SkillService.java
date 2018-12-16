package mvc.service;

import mvc.dao.SkillRepoImpl;
import mvc.model.Skill;

import java.util.List;

public class SkillService {
    private SkillRepoImpl skillRepoImpl = new SkillRepoImpl();

    public void saveToSkillRepo(Skill skill) {
        skillRepoImpl.save(skill);
    }

    public Skill getByIdFromSkillRepo(Integer id) {
        Skill skill = skillRepoImpl.getById(id);
        return skill;
    }

    public void updateToSkillRepo(Skill skill) {
        skillRepoImpl.update(skill);
    }

    public List<Skill> getAllFromSkillRepo() {
        List<Skill> skills = skillRepoImpl.getAll();
        return skills;
    }

    public void deleteFromSkillRepo(Integer id) {
        skillRepoImpl.delete(id);
    }
}
