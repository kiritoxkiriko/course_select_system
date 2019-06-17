package com.wxm.pt.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;

/**
 * @author Alex Wang
 * @date 2019/05/11
 */
@Entity
public class StudyProgram {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne(fetch = FetchType.LAZY)
    private Student student;
    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    private CourseOffering courseOffering;
    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Course> primaryCourses=new ConcurrentSkipListSet<>();
    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Course> secondaryCourses=new ConcurrentSkipListSet<>();
    @NotNull
    private boolean submit=false;

    public StudyProgram() {
    }

    public StudyProgram(Student student, @NotNull CourseOffering courseOffering) {
        this.student = student;
        this.courseOffering = courseOffering;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public CourseOffering getCourseOffering() {
        return courseOffering;
    }

    public void setCourseOffering(CourseOffering courseOffering) {
        this.courseOffering = courseOffering;
    }

    public Set<Course> getPrimaryCourses() {
        return primaryCourses;
    }

    public void setPrimaryCourses(Set<Course> primaryCourses) {
        this.primaryCourses = primaryCourses;
    }

    public Set<Course> getSecondaryCourses() {
        return secondaryCourses;
    }

    public void setSecondaryCourses(Set<Course> secondaryCourses) {
        this.secondaryCourses = secondaryCourses;
    }

    public boolean isSubmit() {
        return submit;
    }

    public void setSubmit(boolean submit) {
        this.submit = submit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudyProgram that = (StudyProgram) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
