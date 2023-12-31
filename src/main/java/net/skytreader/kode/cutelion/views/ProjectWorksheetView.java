package net.skytreader.kode.cutelion.views;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.*;
import net.skytreader.kode.cutelion.data.entity.Project;
import net.skytreader.kode.cutelion.data.service.ProjectWorksheetService;
import net.skytreader.kode.cutelion.templates.ProjectWorksheet;
import org.springframework.transaction.annotation.Transactional;

@Route(value="project/edit")
public class ProjectWorksheetView extends VerticalLayout implements HasUrlParameter<Long>, HasDynamicTitle {
    private final ProjectWorksheetService projectWorksheetService;
    private Project project;

    public ProjectWorksheetView(ProjectWorksheetService projectWorksheetService){
        this.projectWorksheetService = projectWorksheetService;
    }

    /**
     * For some stupid reason, the annotation won't work. Good thing this
     * module <em>does</em> have a dynamic title.
     * @return
     */
    @Override
    public String getPageTitle() {
        if (this.project == null) {
            return "New Project - CuteL10N";
        } else {
            return "Edit Project - " + this.project.getName() + " - CuteL10N";
        }
    }

    @Transactional
    @Override
    public void setParameter(BeforeEvent be,
                             @OptionalParameter Long projectId){
        if (projectId != null) {
            this.project = this.projectWorksheetService.findProject(projectId);
            // IMPORTANT: Initialize lazy-loaded field.
            this.project.getTranslations();
        }
        add(new ProjectWorksheet(this.projectWorksheetService, this.project));
    }
}
