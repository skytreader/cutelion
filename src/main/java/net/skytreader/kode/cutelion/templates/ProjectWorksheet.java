package net.skytreader.kode.cutelion.templates;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.littemplate.LitTemplate;
import com.vaadin.flow.component.template.Id;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.BinderValidationStatus;
import elemental.json.JsonValue;
import elemental.json.impl.JsonUtil;
import net.skytreader.kode.cutelion.data.entity.Project;
import net.skytreader.kode.cutelion.data.entity.Translation;
import net.skytreader.kode.cutelion.data.service.ProjectWorksheetService;
import net.skytreader.kode.cutelion.logic.Utils;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

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

    @Id("translation-grid")
    private Grid<Translation> translationGrid;

    @Id("add-translation")
    private Button addTranslationButton;

    private Binder<Translation> translationBinder;
    private List<Translation> shownTranslations;
    private ProjectWorksheetService projectWorksheetService;

    public ProjectWorksheet(ProjectWorksheetService projectWorksheetService,
                            Project project){
        this.projectWorksheetService = projectWorksheetService;
        this.project = project;

        Binder<Project> projectBinder = this.createProjectBinder();

        if (project != null) {
            translationLocale.setItems(project.getLocales().stream().sorted().toList());
            this.translationBinder = this.createTranslationBinder();
            this.createNewTranslationBean();
            projectBinder.setBean(project);
            ObjectMapper om = new ObjectMapper();
            try {
                JsonValue jv = JsonUtil.parse(om.writeValueAsString(project));
                getElement().setPropertyJson("project", jv);
            } catch (JsonProcessingException jpe) {
                jpe.printStackTrace();
            }
            configureTranslationLocale();
            initGrid();
            this.shownTranslations =
                    projectWorksheetService.findTranslationsByLocaleAndProject(this.project.getDefaultLanguage(), this.project);
            this.shownTranslations.sort(
                    Comparator.comparing(Translation::getKey)
            );
            translationGrid.setItems(this.shownTranslations);

            persistProjectButton.addClickListener(event -> {
                this.project.setName(projectName.getValue());
                this.project.setDefaultLanguage(defaultLanguage.getValue());
                projectWorksheetService.saveProject(this.project);
            });
            addTranslationButton.addClickListener(event -> {
                BinderValidationStatus validationStatus =
                        translationBinder.validate();
                if (validationStatus.isOk()) {
                    Translation t = translationBinder.getBean();
                    projectWorksheetService.saveTranslation(t);
                    this.shownTranslations.add(t);
                    this.translationGrid.getDataProvider().refreshAll();
                    translationKey.clear();
                    translationValue.clear();
                    this.createNewTranslationBean();
                }
            });
        } else {
            persistProjectButton.addClickListener(event -> {
                BinderValidationStatus validationStatus =
                        projectBinder.validate();
                if (validationStatus.isOk()) {
                    String _projectName = projectName.getValue();
                    String _defaultLanguage = defaultLanguage.getValue();
                    Project p = new Project(_projectName, _defaultLanguage,
                            Arrays.asList(_defaultLanguage));
                    projectWorksheetService.saveProject(p);

                    persistProjectButton.getUI().ifPresent(ui -> ui.navigate("project" +
                            "/edit/" + p.getId()));
                }
            });
        }
    }

    private void initGrid() {
        this.translationGrid.addColumn(Translation::getKey).setHeader("Key");
        this.translationGrid.addColumn(Translation::getValue).setHeader(
                "Value");
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
        translationLocale.setValue(this.project.getDefaultLanguage());
        translationLocale.addValueChangeListener(event -> {
            this.shownTranslations =
                    this.projectWorksheetService.findTranslationsByLocaleAndProject(event.getValue(), this.project);
            this.translationGrid.setItems(this.shownTranslations);
            this.translationGrid.getDataProvider().refreshAll();
        });
    }

    private void createNewTranslationBean() {
        Translation t = new Translation();
        t.setProject(this.project);
        if (this.translationBinder.getBean() == null) {
            t.setLocale(this.project.getDefaultLanguage());
        } else {
            t.setLocale(this.translationBinder.getBean().getLocale());
        }
        this.translationBinder.setBean(t);
    }
}
