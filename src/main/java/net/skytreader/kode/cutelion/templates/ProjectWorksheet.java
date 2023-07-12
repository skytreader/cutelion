package net.skytreader.kode.cutelion.templates;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.html.Input;
import com.vaadin.flow.component.html.NativeButton;
import com.vaadin.flow.component.littemplate.LitTemplate;
import com.vaadin.flow.component.template.Id;
import net.skytreader.kode.cutelion.data.entity.Project;
import net.skytreader.kode.cutelion.data.repository.ProjectRepository;

@Tag("project-worksheet")
@JsModule("./src/project-worksheet.ts")
public class ProjectWorksheet extends LitTemplate {
    private Project project;
    private ProjectRepository projectRepository;

    @Id("project-name")
    private Input projectName;

    @Id("default-language")
    private Input defaultLanguage;

    @Id("create-project")
    private NativeButton createProjectButton;

    public ProjectWorksheet(ProjectRepository projectRepository){
        this.projectRepository = projectRepository;
        createProjectButton.addClickListener(event -> {
            String _projectName = projectName.getValue();
            String _defaultLanguage = defaultLanguage.getValue();
            Project p = new Project(_projectName, _defaultLanguage);
            projectRepository.save(p);
        });
    }
}
