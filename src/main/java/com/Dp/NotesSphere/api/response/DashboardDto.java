package com.Dp.NotesSphere.api.response;

import java.util.List;

public class DashboardDto {
    private int totalProjects;
    private int completedTasks;
    private int pendingDeadlines;
    private int timeSpentHours;
    private List<ProjectStatusDto> projectStatus;
    private List<TimeSeriesDto> tasksOverTime;
    private List<TimeAllocationDto> timeAllocation;

    public DashboardDto() {}

    // Getters and setters
    public int getTotalProjects() { return totalProjects; }
    public void setTotalProjects(int totalProjects) { this.totalProjects = totalProjects; }

    public int getCompletedTasks() { return completedTasks; }
    public void setCompletedTasks(int completedTasks) { this.completedTasks = completedTasks; }

    public int getPendingDeadlines() { return pendingDeadlines; }
    public void setPendingDeadlines(int pendingDeadlines) { this.pendingDeadlines = pendingDeadlines; }

    public int getTimeSpentHours() { return timeSpentHours; }
    public void setTimeSpentHours(int timeSpentHours) { this.timeSpentHours = timeSpentHours; }

    public List<ProjectStatusDto> getProjectStatus() { return projectStatus; }
    public void setProjectStatus(List<ProjectStatusDto> projectStatus) { this.projectStatus = projectStatus; }

    public List<TimeSeriesDto> getTasksOverTime() { return tasksOverTime; }
    public void setTasksOverTime(List<TimeSeriesDto> tasksOverTime) { this.tasksOverTime = tasksOverTime; }

    public List<TimeAllocationDto> getTimeAllocation() { return timeAllocation; }
    public void setTimeAllocation(List<TimeAllocationDto> timeAllocation) { this.timeAllocation = timeAllocation; }
}

