package mvc.view;

import mvc.controller.DeveloperController;
import mvc.model.Developer;
import mvc.model.Skill;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DeveloperView {
    private DeveloperController developerController ;
    private Scanner intScanner ;
    private Scanner stringScanner;
    private Developer developer;

    public DeveloperView() {
        developerController = new DeveloperController();
        intScanner = new Scanner(System.in);
        stringScanner = new Scanner(System.in);
        developer = new Developer();
    }

    public void save() {
        System.out.println("enter id, name, specialty, experience, salary");
        developer.setId(intScanner.nextInt());
        developer.setName(stringScanner.nextLine());
        developer.setSpecialty(stringScanner.nextLine());
        developer.setExperience(intScanner.nextInt());
        developer.setSalary(intScanner.nextInt());
        System.out.println("how many skills do you want to add:");
        int x = intScanner.nextInt();
        List<Skill> skillList = new ArrayList<>();
        for (int i = 0; i < x; i++) {
            System.out.println("enter id, skill:");
            Skill skill = new Skill();
            skill.setId(intScanner.nextInt());
            skill.setSkill(stringScanner.nextLine());
            skillList.add(skill);
        }
        developer.setSkills(skillList);
        if (developer != null) {
            developerController.save(developer);
        }
    }

    public Developer getById() {
        System.out.println("enter id:");
        developer = developerController.getById(intScanner.nextInt());
        if (developer != null) {
            System.out.println(developer);
            return developer;
        }
        return null;
    }

    public void update() {
        System.out.println("enter id, name, specialty, experience, salary");
        developer.setId(intScanner.nextInt());
        developer.setName(stringScanner.nextLine());
        developer.setSpecialty(stringScanner.nextLine());
        developer.setExperience(intScanner.nextInt());
        developer.setSalary(intScanner.nextInt());
        if (developer != null) {
            developerController.update(developer);
        }
    }

    public List<Developer> getAll() {
        List<Developer> developers = developerController.getAll();
        if (developers != null) {
            for (Developer developer : developers
            ) {
                System.out.println(developer);
            }
            return developers;
        }
        return null;
    }

    public void delete() {
        System.out.println("enter id:");
        developerController.delete(intScanner.nextInt());
    }

}
