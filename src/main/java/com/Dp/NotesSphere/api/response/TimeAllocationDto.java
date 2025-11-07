package com.Dp.NotesSphere.api.response;

public class TimeAllocationDto {
    private String projectName;
    private double hours;

    public TimeAllocationDto() {}

    public TimeAllocationDto(String projectName, double hours) {
        this.projectName = projectName;
        this.hours = hours;
    }

    public String getProjectName() { return projectName; }
    public void setProjectName(String projectName) { this.projectName = projectName; }

    public double getHours() { return hours; }
    public void setHours(double hours) { this.hours = hours; }
}
