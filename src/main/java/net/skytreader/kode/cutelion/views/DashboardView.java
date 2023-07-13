package net.skytreader.kode.cutelion.views;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import net.skytreader.kode.cutelion.data.service.DashboardService;
import net.skytreader.kode.cutelion.templates.Dashboard;

@PageTitle("Your Projects - CuteL10N")
@Route(value="")
public class DashboardView extends VerticalLayout {
    private final DashboardService dashboardService;

    public DashboardView(DashboardService dashboardService) {
        this.dashboardService = dashboardService;

        add(new Dashboard(dashboardService));
    }
}
