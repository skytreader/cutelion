package net.skytreader.kode.cutelion.views;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import net.skytreader.kode.cutelion.data.service.DashboardService;

@PageTitle("Your Projects - CuteL10N")
@Route(value="")
public class DashboardView extends VerticalLayout {
    private final DashboardService dashboardService;

    public DashboardView(DashboardService dashboardService) {
        this.dashboardService = dashboardService;

        H1 appHeader = new H1("CuteL10n");
        add(appHeader);
        H2 viewHeader = new H2("Projects Dashboard");
        add(viewHeader);

        if (this.dashboardService.hasProjects()){

        } else {
            add(new Paragraph("No projects found. Start one?"));
        }
    }
}
