package com.wxm.exc4.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @author Alex Wang
 * @date 2019/05/11
 */
@Entity
public class CourseOffering {
    public static final int BEGIN=1;
    public static final int FINISH=2;
    public static final int STOP=0;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @OneToOne
    @NotNull
    private Degree degree;
    @ManyToMany(fetch = FetchType.LAZY)
    @NotNull
    private List<Course> courses=new ArrayList<>();
    @Column
    @NotNull
    private int status=BEGIN;
    @Column
    @NotNull
    private Date expireDate;

    public CourseOffering() {
    }

    public CourseOffering(@NotNull Degree degree, @NotNull List<Course> courses, @NotNull Time expireDate) {
        this.degree = degree;
        this.courses = courses;
        this.expireDate = expireDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Degree getDegree() {
        return degree;
    }

    public void setDegree(Degree degree) {
        this.degree = degree;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CourseOffering that = (CourseOffering) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "CourseOffering{" +
                "id=" + id +
                ", degree=" + degree +
                ", courses=" + courses +
                ", status=" + status +
                ", expireTime=" + expireDate +
                '}';
    }
}
