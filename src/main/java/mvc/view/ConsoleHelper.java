package mvc.view;

import java.util.Scanner;

public class ConsoleHelper {
    public static void run() {
        System.out.println("enter the name of the entity you want to work with:");
        Scanner scanner = new Scanner(System.in);
        String choice = scanner.nextLine();
        switch (choice) {
            case "company":
                CompanyView companyView = new CompanyView();
                System.out.println("enter the actions you want to do");
                choice = scanner.nextLine();
                switch (choice) {
                    case "save":
                        companyView.save();
                        break;
                    case "get by id":
                        companyView.getById();
                        break;
                    case "update":
                        companyView.update();
                        break;
                    case "get All":
                        companyView.getAll();
                        break;
                    case "delete":
                        companyView.delete();
                        break;
                    case "insert":
                        companyView.insert();
                }
                break;
            case "customer":
                CustomerView customerView = new CustomerView();
                System.out.println("enter the actions you want to do");
                choice = scanner.nextLine();
                switch (choice) {
                    case "save":
                        customerView.save();
                        break;
                    case "get by id":
                        customerView.getById();
                        break;
                    case "update":
                        customerView.update();
                        break;
                    case "get All":
                        customerView.getAll();
                        break;
                    case "delete":
                        customerView.delete();
                        break;
                    case "insert":
                        customerView.insert();
                }
                break;
            case "developer":
                DeveloperView developerView = new DeveloperView();
                System.out.println("enter the actions you want to do");
                choice = scanner.nextLine();
                switch (choice) {
                    case "save":
                        developerView.save();
                        break;
                    case "get by id":
                        developerView.getById();
                        break;
                    case "update":
                        developerView.update();
                        break;
                    case "get All":
                        developerView.getAll();
                        break;
                    case "delete":
                        developerView.delete();
                        break;
                    case "insert":
                        developerView.insert();
                }
                break;
            case "project":
                ProjectView projectView = new ProjectView();
                System.out.println("enter the actions you want to do");
                choice = scanner.nextLine();
                switch (choice) {
                    case "save":
                        projectView.save();
                        break;
                    case "get by id":
                        projectView.getById();
                        break;
                    case "update":
                        projectView.update();
                        break;
                    case "get All":
                        projectView.getAll();
                        break;
                    case "delete":
                        projectView.delete();
                        break;
                    case "insert":
                        projectView.insert();
                }
                break;
            case "skill":
                SkillView skillView = new SkillView();
                System.out.println("enter the actions you want to do");
                choice = scanner.nextLine();
                switch (choice) {
                    case "save":
                        skillView.save();
                        break;
                    case "get by id":
                        skillView.getById();
                        break;
                    case "update":
                        skillView.update();
                        break;
                    case "get All":
                        skillView.getAll();
                        break;
                    case "delete":
                        skillView.delete();
                        break;
                }
                break;
        }
    }
}
