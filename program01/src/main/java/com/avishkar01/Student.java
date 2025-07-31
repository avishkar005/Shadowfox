package com.avishkar01;

import javafx.beans.property.SimpleStringProperty;

public class Student {
    private final SimpleStringProperty rollno;
    private final SimpleStringProperty name;
    private final SimpleStringProperty course;
    private final SimpleStringProperty email;

    public Student(String rollno, String name, String course, String email) {
        this.rollno = new SimpleStringProperty(rollno);
        this.name = new SimpleStringProperty(name);
        this.course = new SimpleStringProperty(course);
        this.email = new SimpleStringProperty(email);

    }

    public String getRollNo() {
        return rollno.get() != null ? rollno.get() : "";
    }

    public void setRollNo(String rollno) {
        this.rollno.set(rollno);
    }

    public String getName() {
        return name.get() != null ? name.get() : "";
    }

    public void setName(String name) {
        this.name.set(name);

    }

    public String getCourse() {
        return course.get() != null ? course.get() : "";
    }

    public void setCourse(String course) {
        this.course.set(course);

    }

    public String getEmail() {
        return email.get() != null ? email.get() : "";
    }

    public void setEmail(String email) {
        this.email.set(email);

    }

}