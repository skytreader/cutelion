package net.skytreader.kode.cutelion.templates;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.confirmdialog.ConfirmDialog;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Hr;
import com.vaadin.flow.component.listbox.MultiSelectListBox;
import com.vaadin.flow.component.littemplate.LitTemplate;
import com.vaadin.flow.component.template.Id;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.RouterLink;
import net.skytreader.kode.cutelion.data.service.DashboardService;
import net.skytreader.kode.cutelion.data.transfer.PlainProjectDTO;
import net.skytreader.kode.cutelion.data.transfer.ProjectForDeletion;
import net.skytreader.kode.cutelion.views.ProjectWorksheetView;

import java.util.List;
import java.util.Set;

@Tag("dashboard-page")
@JsModule("./src/dashboard.ts")
public class Dashboard extends LitTemplate {
    private DashboardService dashboardService;

    @Id("start-project")
    private Button startProjectButton;

    @Id("project-list")
    private MultiSelectListBox<PlainProjectDTO> projectList;

    @Id("delete-project")
    private Button deleteProjectButton;

    public Dashboard(DashboardService dashboardService){
        this.dashboardService = dashboardService;
        startProjectButton.addClickListener(event -> {
            startProjectButton.getUI().ifPresent(ui ->
                    ui.navigate("project/edit"));
        });
        deleteProjectButton.addClickListener(event -> {
            Set<PlainProjectDTO> ps = projectList.getSelectedItems();

            StringBuilder nameHTMLList = new StringBuilder();
            nameHTMLList.append("ul");
            String liOpen = "<li>";
            String liEnd = "</li>";
            for (PlainProjectDTO project: ps) {
                nameHTMLList.append(liOpen);
                nameHTMLList.append(project.getName());
                nameHTMLList.append(liEnd);
            }
            nameHTMLList.append("</ul>");

            ConfirmDialog deletionConfirmation = new ConfirmDialog();
            deletionConfirmation.setHeader("Confirm Deletion");
            deletionConfirmation.setText(new DeleteProjectsConfirmation(
                    ps.stream().map(pDto -> new ProjectForDeletion(pDto.getName())).toList()
            ));
            deletionConfirmation.setConfirmText("Delete Projects");
            deletionConfirmation.addConfirmListener(_e -> {
                List<Long> ids = ps.stream().map(pdto -> pdto.getId()).toList();
                this.dashboardService.deleteProjectsById(ids);
            });
            deletionConfirmation.setCancelable(true);
            deletionConfirmation.open();
        });
        List<PlainProjectDTO> projects = dashboardService.getProjects();
        getElement().setPropertyList("projects", projects);
        projectList.setItems(projects);
        projectList.setRenderer(new ComponentRenderer<Component,
                PlainProjectDTO>(plainProjectDTO -> {
                    Button btn = new Button(plainProjectDTO.getName());
                    btn.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
                    btn.addClickListener(event -> {
                       btn.getUI().ifPresent(ui -> ui.navigate("project/edit" +
                               "/" + plainProjectDTO.getId()));
                    });
                    return btn;
        }));

        for (PlainProjectDTO pDto : projects) {
            projectList.addComponents(pDto, new Hr());
        }
    }
}
