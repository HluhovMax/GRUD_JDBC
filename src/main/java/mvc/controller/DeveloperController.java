package mvc.controller;

import mvc.model.Developer;
import mvc.service.DeveloperService;

import java.util.List;

public class DeveloperController {
    private DeveloperService developerService = new DeveloperService();

    public void save(Developer developer) {
        developerService.saveToDeveloperRepo(developer);
    }

    public Developer getById(Integer id){
        Developer developer = developerService.getByIdFromDeveloperRepo(id);
        return developer;
    }

    public void update(Developer developer) {
        developerService.updateToDeveloperRepo(developer);
    }

    public List<Developer> getAll() {
        List<Developer> developers = developerService.getAllFromDeveloperRepo();
        return developers;
    }

    public void delete(Integer id) {
        developerService.deleteFromDeveloperRepo(id);
    }

    public void insert(Developer developer) {
        developerService.insertIntoDeveloperRepo(developer);
    }
}
