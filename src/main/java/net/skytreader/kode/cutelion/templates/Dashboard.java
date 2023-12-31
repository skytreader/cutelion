package net.skytreader.kode.cutelion.templates;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.confirmdialog.ConfirmDialog;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.listbox.MultiSelectListBox;
import com.vaadin.flow.component.littemplate.LitTemplate;
import com.vaadin.flow.component.template.Id;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import net.skytreader.kode.cutelion.data.service.DashboardService;
import net.skytreader.kode.cutelion.data.transfer.PlainProjectDTO;
import net.skytreader.kode.cutelion.data.transfer.ProjectForDeletion;

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

    private List<PlainProjectDTO> projects;

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
                List<Long> ids =
                        ps.stream().map(pdto -> {
                            Long id = pdto.getId();
                            projects.remove(pdto);
                            return id;
                        }).toList();
                this.dashboardService.deleteProjectsById(ids);
                projectList.getListDataView().refreshAll();
            });
            deletionConfirmation.setCancelable(true);
            deletionConfirmation.open();
        });
        projects = dashboardService.getProjects();
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
        deleteProjectButton.setEnabled(projectList.getSelectedItems().size() != 0);
        projectList.addSelectionListener(event -> {
            deleteProjectButton.setEnabled(projectList.getSelectedItems().size() != 0);
        });
    }
}
