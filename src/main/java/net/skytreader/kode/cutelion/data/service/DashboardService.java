package net.skytreader.kode.cutelion.data.service;

import net.skytreader.kode.cutelion.data.entity.Project;
import net.skytreader.kode.cutelion.data.repository.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DashboardService {
    private final ProjectRepository projectRepository;
    private List<Project> projects;

    public DashboardService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
        this.projects = projectRepository.fetchDashboard();
    }

    public boolean hasProjects() {
        return this.projects.size() > 0;
    }
    public List<Project> getProjects() {
        return this.projects;
    }
}
