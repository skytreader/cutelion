package net.skytreader.kode.cutelion.views;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.*;
import net.skytreader.kode.cutelion.data.entity.Project;
import net.skytreader.kode.cutelion.data.repository.ProjectRepository;
import net.skytreader.kode.cutelion.templates.ProjectWorksheet;
import org.springframework.transaction.annotation.Transactional;

import java.util.logging.Logger;

@PageTitle("Edit Project - CuteL10N")
@Route(value="project/edit")
public class ProjectWorksheetView extends VerticalLayout implements HasUrlParameter<Long> {
    private final ProjectRepository projectRepository;
    private final static Logger logger =
            Logger.getLogger(ProjectWorksheetView.class.getName());
    private Project project;

    public ProjectWorksheetView(ProjectRepository projectRepository){
        logger.info("constructor");
        this.projectRepository = projectRepository;
    }

    @Override
    @Transactional
    public void setParameter(BeforeEvent be,
                             @OptionalParameter Long projectId){
        logger.info("setParameter called");
        if (projectId != null) {
            this.project = this.projectRepository.findById(projectId).get();
            logger.info("got project " + this.project.getId());
        }
        add(new ProjectWorksheet(this.projectRepository, this.project));
    }
}
