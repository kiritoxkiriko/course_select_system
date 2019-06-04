package com.wxm.exc4.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * @author Alex Wang
 * @date 2019/05/11
 */
@Entity
public class Student {
    @Id
    private long id;
    @NotNull
    private String name;
    @Column
    @NotNull
    private String password;
    @ManyToOne(fetch = FetchType.LAZY)
    private College college;

    public Student() {
    }

    public Student(long id, @NotNull String name, @NotNull String password, College college) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.college = college;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public College getCollege() {
        return college;
    }

    public void setCollege(College college) {
        this.college = college;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return id == student.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", college=" + college +
                '}';
    }
}
