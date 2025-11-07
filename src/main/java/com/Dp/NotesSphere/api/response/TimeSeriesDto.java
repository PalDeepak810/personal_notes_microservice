package com.Dp.NotesSphere.api.response;

public class TimeSeriesDto {
    private String date;
    private long created;
    private long completed;

    public TimeSeriesDto() {}

    public TimeSeriesDto(String date, long created, long completed) {
        this.date = date;
        this.created = created;
        this.completed = completed;
    }

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }

    public long getCreated() { return created; }
    public void setCreated(long created) { this.created = created; }

    public long getCompleted() { return completed; }
    public void setCompleted(long completed) { this.completed = completed; }
}