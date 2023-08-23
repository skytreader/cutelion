package net.skytreader.kode.cutelion.data.service;

import jakarta.transaction.Transactional;
import net.skytreader.kode.cutelion.data.entity.Project;
import net.skytreader.kode.cutelion.data.repository.ProjectRepository;
import net.skytreader.kode.cutelion.data.transfer.PlainProjectDTO;
import org.modelmapper.module.jsr310.Jsr310Module;
import org.modelmapper.module.jsr310.Jsr310ModuleConfig;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class DashboardService {
    private final ProjectRepository projectRepository;

    public DashboardService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public boolean hasProjects() {
        return this.projectRepository.existsAny();
    }
    public List<PlainProjectDTO> getProjects() {
        ModelMapper mm = new ModelMapper();
        Jsr310ModuleConfig config = Jsr310ModuleConfig.builder().build();
        mm.registerModule(new Jsr310Module(config));
        List<Project> projects = this.projectRepository.fetchDashboard();
        return projects.stream().map(p -> mm.map(p,
                PlainProjectDTO.class)).collect(Collectors.toList());
    }

    public void deleteProjectsById(List<Long> ids) {
        this.projectRepository.deleteAllByIdInBatch(ids);
    }
}
