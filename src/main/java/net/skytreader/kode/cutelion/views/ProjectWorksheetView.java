package net.skytreader.kode.cutelion.views;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.*;
import net.skytreader.kode.cutelion.data.entity.Project;
import net.skytreader.kode.cutelion.data.repository.ProjectRepository;
import net.skytreader.kode.cutelion.data.repository.TranslationRepository;
import net.skytreader.kode.cutelion.templates.ProjectWorksheet;
import org.springframework.transaction.annotation.Transactional;

@Route(value="project/edit")
public class ProjectWorksheetView extends VerticalLayout implements HasUrlParameter<Long>, HasDynamicTitle {
    private final ProjectRepository projectRepository;
    private final TranslationRepository translationRepository;
    private Project project;

    public ProjectWorksheetView(ProjectRepository projectRepository,
                                TranslationRepository translationRepository){
        this.projectRepository = projectRepository;
        this.translationRepository = translationRepository;
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

    @Override
    @Transactional
    public void setParameter(BeforeEvent be,
                             @OptionalParameter Long projectId){
        if (projectId != null) {
            this.project = this.projectRepository.findById(projectId).get();
        }
        add(new ProjectWorksheet(this.projectRepository,
                this.translationRepository, this.project));
    }
}
