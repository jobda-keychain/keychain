package com.jobda.keychain.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userIdx;

    @Column(name = "id")
    private String id;

    @Column(name = "pw")
    private String pw;

    @Column(name = "service")
    private int service;

    @Column(name = "stage")
    private int stage;

    @Column(name = "des")
    private String des;

}
