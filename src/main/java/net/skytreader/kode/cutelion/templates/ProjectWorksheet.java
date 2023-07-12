package net.skytreader.kode.cutelion.templates;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.littemplate.LitTemplate;
import net.skytreader.kode.cutelion.data.entity.Project;
import net.skytreader.kode.cutelion.data.repository.ProjectRepository;

@Tag("project-worksheet")
@JsModule("./src/project-worksheet.ts")
public class ProjectWorksheet extends LitTemplate {
    private Project project;
    private ProjectRepository projectRepository;

    public ProjectWorksheet(ProjectRepository projectRepository){
        this.projectRepository = projectRepository;
    }
}
