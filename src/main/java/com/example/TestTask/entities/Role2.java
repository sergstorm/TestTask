package com.example.TestTask.entities;



import javax.persistence.*;
import javax.transaction.Transactional;

@Entity
@Table(name = "roles")
public class Role2 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Role2{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
