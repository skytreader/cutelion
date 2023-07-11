package net.skytreader.kode.cutelion.templates;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.littemplate.LitTemplate;
import elemental.json.Json;
import elemental.json.JsonValue;
import net.skytreader.kode.cutelion.data.service.DashboardService;

@Tag("dashboard-page")
@JsModule("./src/dashboard.ts")
public class Dashboard extends LitTemplate {
    private DashboardService dashboardService;

    public Dashboard(DashboardService dashboardService){
        this.dashboardService = dashboardService;
        getElement().setPropertyList("projects", dashboardService.getProjects());
    }
}
