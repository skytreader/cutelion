package net.skytreader.kode.cutelion.templates;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.html.Hr;
import com.vaadin.flow.component.listbox.MultiSelectListBox;
import com.vaadin.flow.component.littemplate.LitTemplate;
import com.vaadin.flow.component.template.Id;
import net.skytreader.kode.cutelion.data.service.DashboardService;
import net.skytreader.kode.cutelion.data.transfer.PlainProjectDTO;

import java.util.List;
import java.util.Set;

@Tag("dashboard-page")
@JsModule("./src/dashboard.ts")
public class Dashboard extends LitTemplate {
    private DashboardService dashboardService;

    @Id("start-project")
    private Button startProjectButton;

    @Id("project-list")
    private MultiSelectListBox<String> projectList;

    @Id("delete-project")
    private Button deleteProjectButton;

    public Dashboard(DashboardService dashboardService){
        this.dashboardService = dashboardService;
        startProjectButton.addClickListener(event -> {
            startProjectButton.getUI().ifPresent(ui ->
                    ui.navigate("project/edit"));
        });
        deleteProjectButton.addClickListener(event -> {
            Set<String> p = projectList.getSelectedItems();
        });
        List<PlainProjectDTO> projects = dashboardService.getProjects();
        getElement().setPropertyList("projects", projects);
        String[] projectNames =
                dashboardService.getProjects().stream().map(p -> p.getName()).toArray(String[]::new);
        projectList.setItems(projectNames);

        for (int i = 0; i < projectNames.length; i++) {
            int itemIndex = 2 * i;
            projectList.addComponents(projectNames[i], new Hr());
        }
    }
}
