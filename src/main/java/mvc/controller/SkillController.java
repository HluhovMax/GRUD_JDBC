package mvc.controller;

import mvc.model.Skill;
import mvc.service.SkillService;

import java.util.List;

public class SkillController {
    private SkillService skillService = new SkillService();

    public void save(Skill skill) {
        skillService.saveToSkillRepo(skill);
    }

    public Skill getById(Integer id){
        Skill skill = skillService.getByIdFromSkillRepo(id);
        return skill;
    }

    public void update(Skill skill) {
        skillService.updateToSkillRepo(skill);
    }

    public List<Skill> getAll() {
        List<Skill> skills = skillService.getAllFromSkillRepo();
        return skills;
    }

    public void delete(Integer id){
        skillService.deleteFromSkillRepo(id);
    }
}
