package com.waled.TaskFlow.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "User")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @Column(name="username")
    private String username;

    @Column(name="passsword")
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;



}
