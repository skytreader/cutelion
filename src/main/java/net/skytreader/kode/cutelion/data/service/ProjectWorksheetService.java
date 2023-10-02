package net.skytreader.kode.cutelion.data.service;

import net.skytreader.kode.cutelion.data.entity.Project;
import net.skytreader.kode.cutelion.data.entity.Translation;
import net.skytreader.kode.cutelion.data.repository.ProjectRepository;
import net.skytreader.kode.cutelion.data.repository.TranslationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectWorksheetService {
    private final ProjectRepository projectRepository;
    private final TranslationRepository translationRepository;

    public ProjectWorksheetService(ProjectRepository projectRepository,
                                   TranslationRepository translationRepository){
        this.projectRepository = projectRepository;
        this.translationRepository = translationRepository;
    }

    public void saveProject(Project p){
        this.projectRepository.save(p);
    }

    public void saveTranslation(Translation t) {
        this.translationRepository.save(t);
    }

    public Project findProject(Long projectId) {
        return this.projectRepository.findById(projectId).get();
    }

    public List<Translation> findTranslationsByLocaleAndProject(String locale,
                                                            Project p) {
        return this.translationRepository.findByLocaleAndProject(locale, p);
    }
}
