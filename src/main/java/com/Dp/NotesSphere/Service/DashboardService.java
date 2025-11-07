package com.Dp.NotesSphere.Service;

import com.Dp.NotesSphere.Modals.Note;
import com.Dp.NotesSphere.Repository.NoteRepo;
import com.Dp.NotesSphere.api.response.DashboardDto;
import com.Dp.NotesSphere.api.response.ProjectStatusDto;
import com.Dp.NotesSphere.api.response.TimeAllocationDto;
import com.Dp.NotesSphere.api.response.TimeSeriesDto;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class DashboardService {

    private final NoteRepo noteRepo;

    public DashboardService(NoteRepo noteRepo) {
        this.noteRepo = noteRepo;
    }

    public DashboardDto getPersonalDashboard(String username) {
        // Fetch all notes for this user directly
        List<Note> notes = noteRepo.findByUsername(username);

        int totalProjects = 1;
        int completedTasks = notes.size() / 2;
        int pendingDeadlines = notes.size() - completedTasks;
        double timeSpentHours = notes.size() * 1.5;

        // Project status breakdown
        List<ProjectStatusDto> projectStatus = List.of(
                new ProjectStatusDto("Completed", completedTasks),
                new ProjectStatusDto("Pending", pendingDeadlines)
        );

        // Tasks over time
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        Map<String, Long> createdPerDay = notes.stream()
                .filter(n -> n.getCreatedAt() != null)
                .collect(Collectors.groupingBy(
                        n -> n.getCreatedAt().format(fmt),
                        Collectors.counting()
                ));

        List<TimeSeriesDto> tasksOverTime = createdPerDay.entrySet().stream()
                .map(e -> new TimeSeriesDto(e.getKey(), e.getValue(), 0))
                .sorted(Comparator.comparing(TimeSeriesDto::getDate))
                .collect(Collectors.toList());

        // Time allocation
        List<TimeAllocationDto> timeAllocation = notes.stream()
                .collect(Collectors.groupingBy(Note::getTitle, Collectors.counting()))
                .entrySet().stream()
                .map(e -> new TimeAllocationDto(e.getKey(), e.getValue() * 1.5))
                .collect(Collectors.toList());

        DashboardDto dto = new DashboardDto();
        dto.setTotalProjects(totalProjects);
        dto.setCompletedTasks(completedTasks);
        dto.setPendingDeadlines(pendingDeadlines);
        dto.setTimeSpentHours((int) timeSpentHours);
        dto.setProjectStatus(projectStatus);
        dto.setTasksOverTime(tasksOverTime);
        dto.setTimeAllocation(timeAllocation);
        return dto;
    }
}
