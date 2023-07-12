package net.skytreader.kode.cutelion.views;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import net.skytreader.kode.cutelion.data.repository.ProjectRepository;
import net.skytreader.kode.cutelion.templates.ProjectWorksheet;

@PageTitle("Edit Project - CuteL10N")
@Route(value="project/edit")
public class ProjectWorksheetView extends VerticalLayout {
    private final ProjectRepository projectRepository;

    public ProjectWorksheetView(ProjectRepository projectRepository){
        this.projectRepository = projectRepository;

        add(new ProjectWorksheet(projectRepository));
    }
}
