package net.skytreader.kode.cutelion.views;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.*;
import net.skytreader.kode.cutelion.data.entity.Project;
import net.skytreader.kode.cutelion.data.repository.ProjectRepository;
import net.skytreader.kode.cutelion.templates.ProjectWorksheet;

@PageTitle("Edit Project - CuteL10N")
@Route(value="project/edit")
public class ProjectWorksheetView extends VerticalLayout implements HasUrlParameter<Integer> {
    private final ProjectRepository projectRepository;
    private Project project;

    public ProjectWorksheetView(ProjectRepository projectRepository){
        this.projectRepository = projectRepository;

        add(new ProjectWorksheet(projectRepository));
    }

    @Override
    public void setParameter(BeforeEvent be,
                             @OptionalParameter Integer projectId){
        if (projectId != null) {
            this.project = this.projectRepository.fetchProject(projectId);
        }
    }
}
