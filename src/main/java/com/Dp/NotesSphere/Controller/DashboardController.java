package com.Dp.NotesSphere.Controller;

import com.Dp.NotesSphere.Service.DashboardService;
import com.Dp.NotesSphere.api.response.DashboardDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {

    private final DashboardService dashboardService;

    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping("/personal")
    public DashboardDto getPersonalDashboard(@RequestHeader("X-User-Name") String username) {
        return dashboardService.getPersonalDashboard(username);
    }
}