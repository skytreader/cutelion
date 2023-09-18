package net.skytreader.kode.cutelion.templates;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.littemplate.LitTemplate;
import com.vaadin.flow.component.template.Id;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import elemental.json.JsonValue;
import elemental.json.impl.JsonUtil;
import net.skytreader.kode.cutelion.data.entity.Project;
import net.skytreader.kode.cutelion.data.entity.Translation;
import net.skytreader.kode.cutelion.data.service.ProjectWorksheetService;
import net.skytreader.kode.cutelion.logic.Utils;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

@Tag("project-worksheet")
@JsModule("./src/project-worksheet.ts")
@Transactional
public class ProjectWorksheet extends LitTemplate {
    private Project project;

    @Id("project-name")
    private TextField projectName;

    @Id("default-language")
    private TextField defaultLanguage;

    @Id("persist-project")
    private Button persistProjectButton;

    @Id("translation-key")
    private TextField translationKey;

    @Id("translation-value")
    private TextField translationValue;

    @Id("translation-locale")
    private ComboBox<String> translationLocale;

    @Id("add-translation")
    private Button addTranslationButton;

    private String enteredLocale;

    Binder<Translation> translationBinder;

    public ProjectWorksheet(ProjectWorksheetService projectWorksheetService,
                            Project project){
        this.project = project;

        Binder<Project> projectBinder = this.createProjectBinder();

        if (project != null) {
            translationLocale.setItems(project.getLocales());
            Translation translationBean = new Translation();
            translationBean.setProject(project);
            this.translationBinder = this.createTranslationBinder();
            this.translationBinder.setBean(translationBean);
            this.enteredLocale = project.getDefaultLanguage();
            projectBinder.setBean(project);
            ObjectMapper om = new ObjectMapper();
            try {
                JsonValue jv = JsonUtil.parse(om.writeValueAsString(project));
                getElement().setPropertyJson("project", jv);
            } catch (JsonProcessingException jpe) {
                jpe.printStackTrace();
            }
            configureTranslationLocale();
            persistProjectButton.addClickListener(event -> {
                this.project.setName(projectName.getValue());
                this.project.setDefaultLanguage(defaultLanguage.getValue());
                projectWorksheetService.saveProject(this.project);
            });
            addTranslationButton.addClickListener(event -> {
                Translation t = translationBinder.getBean();
                projectWorksheetService.saveTranslation(t);
                translationKey.clear();
                translationValue.clear();
                getElement().callJsFunction("addTranslation", t.getKey(),
                        t.getValue(), t.getLocale());
            });
        } else {
            persistProjectButton.addClickListener(event -> {
                String _projectName = projectName.getValue();
                String _defaultLanguage = defaultLanguage.getValue();
                Project p = new Project(_projectName, _defaultLanguage,
                        Arrays.asList(_defaultLanguage));
                projectWorksheetService.saveProject(p);

                persistProjectButton.getUI().ifPresent(ui -> ui.navigate("project" +
                        "/edit/" + p.getId()));
            });
        }
    }

    private Binder<Translation> createTranslationBinder() {
        Binder<Translation> translationBinder = new Binder<>(Translation.class);
        translationBinder.forField(translationKey)
                .asRequired()
                .bind(Translation::getKey, Translation::setKey);
        translationBinder.forField(translationValue)
                .asRequired()
                .bind(Translation::getValue, Translation::setValue);
        translationBinder.forField(translationLocale)
                .withValidator(Utils::isValidLocaleString,
                        "does not match locale string pattern")
                .bind(Translation::getLocale, Translation::setLocale);
        return translationBinder;
    }

    private Binder<Project> createProjectBinder() {
        Binder<Project> projectBinder = new Binder<>(Project.class);
        projectBinder.forField(projectName)
                .asRequired("project name is required")
                .bind(Project::getName,
                        Project::setName);
        projectBinder.forField(defaultLanguage)
                .withValidator(Utils::isValidLocaleString, "does not match " +
                        "locale string pattern")
                .bind(Project::getDefaultLanguage, Project::setDefaultLanguage);
        return projectBinder;
    }

    private void configureTranslationLocale(){
        translationLocale.addValueChangeListener(event -> {
            this.enteredLocale = event.getValue();
        });
    }
}
