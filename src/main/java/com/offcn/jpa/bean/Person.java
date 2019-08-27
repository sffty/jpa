package com.offcn.jpa.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Person {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "name", nullable = true, length = 20)
    private String name;

    @Column(name = "age", nullable = true, length = 4)
    private int age;
    @Column(name = "password", nullable = true, length = 20)
    private String password;

    @OneToMany(mappedBy = "personId", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Dog> dogs;

}
