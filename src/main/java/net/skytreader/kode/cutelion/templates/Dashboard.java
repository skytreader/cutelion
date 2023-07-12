package net.skytreader.kode.cutelion.templates;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.html.NativeButton;
import com.vaadin.flow.component.littemplate.LitTemplate;
import com.vaadin.flow.component.template.Id;
import net.skytreader.kode.cutelion.data.service.DashboardService;

@Tag("dashboard-page")
@JsModule("./src/dashboard.ts")
public class Dashboard extends LitTemplate {
    private DashboardService dashboardService;

    @Id("start-project")
    private NativeButton startProjectButton;

    public Dashboard(DashboardService dashboardService){
        this.dashboardService = dashboardService;
        startProjectButton.addClickListener(event -> {
            startProjectButton.getUI().ifPresent(ui ->
                    ui.navigate("project/edit"));
        });
        getElement().setPropertyList("projects", dashboardService.getProjects());
    }
}
