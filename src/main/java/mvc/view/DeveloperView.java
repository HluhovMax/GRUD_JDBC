package mvc.view;

import mvc.controller.DeveloperController;
import mvc.model.Developer;


import java.util.List;
import java.util.Scanner;

public class DeveloperView {
    private DeveloperController developerController = new DeveloperController();
    private Scanner intScanner = new Scanner(System.in);
    private Scanner stringScanner = new Scanner(System.in);
    private Developer developer = new Developer();

    public void save() {
        System.out.println("enter name, specialty, experience, salary");
        developer.setName(stringScanner.nextLine());
        developer.setSpecialty(stringScanner.nextLine());
        developer.setExperience(intScanner.nextInt());
        developer.setSalary(intScanner.nextInt());
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
