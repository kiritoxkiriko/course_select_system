package com.wxm.pt.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * @author Alex Wang
 * @date 2019/05/11
 */
@Entity
public class Degree {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    @NotNull
    private String name;
    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    private College college;

    public Degree() {
    }

    public Degree(@NotNull String name, @NotNull College college) {
        this.name = name;
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
        Degree degree = (Degree) o;
        return id == degree.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Degree{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", college=" + college +
                '}';
    }
}
