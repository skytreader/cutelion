package net.skytreader.kode.cutelion.templates;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.html.Input;
import com.vaadin.flow.component.html.NativeButton;
import com.vaadin.flow.component.littemplate.LitTemplate;
import com.vaadin.flow.component.template.Id;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import elemental.json.JsonValue;
import elemental.json.impl.JsonUtil;
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

    public ProjectWorksheet(ProjectRepository projectRepository,
                            Project project){
        this.projectRepository = projectRepository;
        this.project = project;
        if (project != null) {
            ObjectMapper om = new ObjectMapper();
            try {
                JsonValue jv = JsonUtil.parse(om.writeValueAsString(project));
                getElement().setPropertyJson("project", jv);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        createProjectButton.addClickListener(event -> {
            String _projectName = projectName.getValue();
            String _defaultLanguage = defaultLanguage.getValue();
            Project p = new Project(_projectName, _defaultLanguage);
            projectRepository.save(p);

            createProjectButton.getUI().ifPresent(ui -> ui.navigate("project" +
                    "/edit/" + p.getId()));
        });
    }
}
