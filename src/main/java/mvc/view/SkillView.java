package mvc.view;

import mvc.controller.SkillController;
import mvc.model.Skill;
import mvc.service.SkillService;

import java.util.List;
import java.util.Scanner;

public class SkillView {
    private SkillController skillController = new SkillController();
    private Scanner intScanner = new Scanner(System.in);
    private Scanner stringScanner = new Scanner(System.in);
    private Skill skill = new Skill();

    public void save() {
        System.out.println("enter skill name:");
        skill.setSkill(stringScanner.nextLine());
        if (skill != null) {
            skillController.save(skill);
        }
    }

    public Skill getById() {
        System.out.println("enter id:");
        skill = skillController.getById(intScanner.nextInt());
        if (skill != null) {
            System.out.println(skill);
            return skill;
        }
        return null;
    }

    public void update() {
        System.out.println("enter id, customer");
        skill.setId(intScanner.nextInt());
        skill.setSkill(stringScanner.nextLine());
        if (skill != null) {
            skillController.update(skill);
        }
    }

    public List<Skill> getAll() {
        List<Skill> skills = skillController.getAll();
        if (skills != null) {
            for (Skill skill : skills
            ) {
                System.out.println(skill);
            }
            return skills;
        }
        return null;
    }

    public void delete() {
        System.out.println("enter id:");
        skillController.delete(intScanner.nextInt());
    }
}
