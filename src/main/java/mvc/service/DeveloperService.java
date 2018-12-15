package mvc.service;

import mvc.dao.DeveloperRepo;
import mvc.model.Developer;

import java.util.List;

public class DeveloperService {
    private DeveloperRepo developerRepo = new DeveloperRepo();

    public void saveToDeveloperRepo(Developer developer) {
        developerRepo.save(developer);
    }

    public Developer getByIdFromDeveloperRepo(Integer id) {
        Developer developer = developerRepo.getById(id);
        return developer;
    }

    public void updateToDeveloperRepo(Developer developer) {
        developerRepo.update(developer);
    }

    public List<Developer> getAllFromDeveloperRepo() {
        List<Developer> developers = developerRepo.getAll();
        return developers;
    }

    public void deleteFromDeveloperRepo(Integer id) {
        developerRepo.delete(id);
    }
}
