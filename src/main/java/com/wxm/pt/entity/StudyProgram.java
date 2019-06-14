package com.wxm.pt.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
    private List<Course> primaryCourses=new ArrayList<>();
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Course> secondaryCourses=new ArrayList<>();
    @Column
    @NotNull
    private boolean submit=false;

    public StudyProgram(Student student, @NotNull CourseOffering courseOffering) {
        this.student = student;
        this.courseOffering = courseOffering;
    }

    public StudyProgram() {
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

    public List<Course> getPrimaryCourses() {
        return primaryCourses;
    }

    public void setPrimaryCourses(List<Course> primaryCourses) {
        this.primaryCourses = primaryCourses;
    }

    public List<Course> getSecondaryCourses() {
        return secondaryCourses;
    }

    public void setSecondaryCourses(List<Course> secondaryCourses) {
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

    @Override
    public String toString() {
        return "StudyProgram{" +
                "id=" + id +
                ", student=" + student +
                ", courseOffering=" + courseOffering +
                ", primaryCourses=" + primaryCourses +
                ", secondaryCourses=" + secondaryCourses +
                ", submit=" + submit +
                '}';
    }
}
