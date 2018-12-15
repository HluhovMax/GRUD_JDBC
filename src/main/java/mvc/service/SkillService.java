package mvc.service;

import mvc.dao.SkillRepo;
import mvc.model.Skill;

import java.util.List;

public class SkillService {
    private SkillRepo skillRepo = new SkillRepo();

    public void saveToSkillRepo(Skill skill) {
        skillRepo.save(skill);
    }

    public Skill getByIdFromSkillRepo(Integer id) {
        Skill skill = skillRepo.getById(id);
        return skill;
    }

    public void updateToSkillRepo(Skill skill) {
        skillRepo.update(skill);
    }

    public List<Skill> getAllFromProjectRepo() {
        List<Skill> skills = skillRepo.getAll();
        return skills;
    }

    public void deleteFromSkillRepo(Integer id) {
        skillRepo.delete(id);
    }
}
