package net.skytreader.kode.cutelion.templates;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.littemplate.LitTemplate;
import net.skytreader.kode.cutelion.data.transfer.ProjectForDeletion;

import java.util.List;

@Tag("delete-projects-confirmation")
@JsModule("./src/snippets/delete-projects-confirmation.ts")
public class DeleteProjectsConfirmation extends LitTemplate {
    public DeleteProjectsConfirmation(List<ProjectForDeletion> projectForDeletions){
        getElement().setPropertyList("projects", projectForDeletions);
    }
}
