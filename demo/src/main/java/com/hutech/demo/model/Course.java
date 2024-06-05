package com.hutech.demo.model;

import java.time.LocalDate;

public class Course {
    private int id;
    private String lectureName;
    private String place;
    private LocalDate startDate;

    public Course() {
    }

    public String getLectureName() {
        return lectureName;
    }

    public String getPlace() {
        return place;
    }

    public LocalDate getStartDate() {
        return startDate;
    }
}
