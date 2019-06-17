package com.wxm.pt.entity;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author Alex Wang
 * @date 2019/06/16
 */
@Entity
public class CourseInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    private Course course;
    @ManyToOne
    private Student student;
    @Column
    private double score;

    public CourseInfo() {
    }

    public CourseInfo(Course course, Student student, double score) {
        this.course = course;
        this.student = student;
        this.score = score;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CourseInfo that = (CourseInfo) o;
        return Objects.equals(course, that.course) &&
                Objects.equals(student, that.student);
    }

    @Override
    public int hashCode() {
        return Objects.hash(course, student);
    }
}
