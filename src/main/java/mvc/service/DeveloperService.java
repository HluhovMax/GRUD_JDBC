package mvc.service;

import mvc.dao.DeveloperRepoImpl;
import mvc.model.Developer;

import java.util.List;

public class DeveloperService {
    private DeveloperRepoImpl developerRepoImpl = new DeveloperRepoImpl();

    public void saveToDeveloperRepo(Developer developer) {
        developerRepoImpl.save(developer);
    }

    public Developer getByIdFromDeveloperRepo(Integer id) {
        Developer developer = developerRepoImpl.getById(id);
        return developer;
    }

    public void updateToDeveloperRepo(Developer developer) {
        developerRepoImpl.update(developer);
    }

    public List<Developer> getAllFromDeveloperRepo() {
        List<Developer> developers = developerRepoImpl.getAll();
        return developers;
    }

    public void deleteFromDeveloperRepo(Integer id) {
        developerRepoImpl.delete(id);
    }
}
