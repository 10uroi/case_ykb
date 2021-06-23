package com.ykb.vacation.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String surname;

    private Date createDate = new Date();
    private Date startWorkDate = new Date();

    @OneToMany(cascade = CascadeType.ALL)
    private List<Vacation> vacations = new ArrayList<>();
}
