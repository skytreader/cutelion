package net.skytreader.kode.cutelion.templates;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.html.Input;
import com.vaadin.flow.component.html.NativeButton;
import com.vaadin.flow.component.littemplate.LitTemplate;
import com.vaadin.flow.component.template.Id;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import elemental.json.JsonValue;
import elemental.json.impl.JsonUtil;
import net.skytreader.kode.cutelion.data.entity.Project;
import net.skytreader.kode.cutelion.data.repository.ProjectRepository;
import net.skytreader.kode.cutelion.logic.Utils;

import java.awt.*;
import java.time.ZonedDateTime;

@Tag("project-worksheet")
@JsModule("./src/project-worksheet.ts")
public class ProjectWorksheet extends LitTemplate {
    private Project project;
    private ProjectRepository projectRepository;

    @Id("project-name")
    private TextField projectName;

    @Id("default-language")
    private TextField defaultLanguage;

    @Id("persist-project")
    private Button persistProjectButton;

    public ProjectWorksheet(ProjectRepository projectRepository,
                            Project project){
        this.projectRepository = projectRepository;
        this.project = project;

        Binder<Project> projectBinder = new Binder<>(Project.class);
        projectBinder.forField(projectName)
                .asRequired("project name is required")
                .bind(Project::getName,
                Project::setName);
        projectBinder.forField(defaultLanguage)
                .withValidator(Utils::isValidLocaleString, "does not fit " +
                        "locale string pattern")
                .bind(Project::getDefaultLanguage, Project::setDefaultLanguage);

        if (project != null) {
            projectBinder.setBean(project);
            ObjectMapper om = new ObjectMapper();
            try {
                JsonValue jv = JsonUtil.parse(om.writeValueAsString(project));
                getElement().setPropertyJson("project", jv);
            } catch (JsonProcessingException jpe) {
                jpe.printStackTrace();
            }
            getElement().setProperty("hasProject", true);
            persistProjectButton.addClickListener(event -> {
                this.project.setName(projectName.getValue());
                this.project.setDefaultLanguage(defaultLanguage.getValue());
                this.project.setModifiedAt(ZonedDateTime.now());
                projectRepository.save(this.project);
            });
        } else {
            persistProjectButton.addClickListener(event -> {
                String _projectName = projectName.getValue();
                String _defaultLanguage = defaultLanguage.getValue();
                Project p = new Project(_projectName, _defaultLanguage);
                projectRepository.save(p);

                persistProjectButton.getUI().ifPresent(ui -> ui.navigate("project" +
                        "/edit/" + p.getId()));
            });
        }
    }
}
