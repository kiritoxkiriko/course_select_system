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
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    private College college;
    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    private Professor professor;
    @Column
    @NotNull
    private String name;
    @Column
    @NotNull
    private double score;
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Course> requireCourses=new ArrayList<>();
    @Column
    private int selectNum = 0;
    @NotNull
    @Column
    private int totalNum=10;
    @Column
    @NotNull
    private double price;
    @Column
    @NotNull
    private int beginWeek;
    @Column
    @NotNull
    private int finishWeek;
    @Column
    @NotNull
    //用空格隔开
    private String daysOfWeek;
    @Column
    @NotNull
    private int beginTime;
    @Column
    @NotNull
    private int finishTime;
    @Column
    private String location;

    public Course() {
    }

    public Course(@NotNull College college, @NotNull Professor professor, @NotNull String name, @NotNull double score, List<Course> requireCourses, @NotNull int totalNum, @NotNull double price, @NotNull int beginWeek, @NotNull int finishWeek, @NotNull String daysOfWeek, @NotNull int beginTime, @NotNull int finishTime) {
        this.college = college;
        this.professor = professor;
        this.name = name;
        this.score = score;
        this.requireCourses = requireCourses;
        this.totalNum = totalNum;
        this.price = price;
        this.beginWeek = beginWeek;
        this.finishWeek = finishWeek;
        this.daysOfWeek = daysOfWeek;
        this.beginTime = beginTime;
        this.finishTime = finishTime;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public College getCollege() {
        return college;
    }

    public void setCollege(College college) {
        this.college = college;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public List<Course> getRequireCourses() {
        return requireCourses;
    }

    public void setRequireCourses(List<Course> requireCourses) {
        this.requireCourses = requireCourses;
    }

    public int getSelectNum() {
        return selectNum;
    }

    public void setSelectNum(int selectNum) {
        this.selectNum = selectNum;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getBeginWeek() {
        return beginWeek;
    }

    public void setBeginWeek(int beginWeek) {
        this.beginWeek = beginWeek;
    }

    public int getFinishWeek() {
        return finishWeek;
    }

    public void setFinishWeek(int finishWeek) {
        this.finishWeek = finishWeek;
    }

    public int getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(int beginTime) {
        this.beginTime = beginTime;
    }

    public int getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(int finishTime) {
        this.finishTime = finishTime;
    }


    public String getDaysOfWeek() {
        return daysOfWeek;
    }

    public void setDaysOfWeek(String daysOfWeek) {
        this.daysOfWeek = daysOfWeek;
    }

    public int getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(int totalNum) {
        this.totalNum = totalNum;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Course(String location) {
        this.location = location;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return id == course.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", college=" + college +
                ", professor=" + professor +
                ", name='" + name + '\'' +
                ", score=" + score +
                ", requireCourses=" + requireCourses +
                ", selectNum=" + selectNum +
                ", price=" + price +
                ", beginWeek=" + beginWeek +
                ", finishWeek=" + finishWeek +
                ", daysOfWeek='" + daysOfWeek + '\'' +
                ", beginTime=" + beginTime +
                ", finishTime=" + finishTime +
                '}';
    }
}
