package com.example.phonebook.repository.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



import javax.persistence.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "contact")
public class Contact {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "firstname",nullable = false)
    public String firstname;

    @Column(name = "lastname",nullable = false)
    private String lastname;

    @Column(name = "homephone",nullable = false)
    private String homephone;

    @Column(name = "businessphone",nullable = false)//
    private String businessphone;

    @Column(name = "email",nullable = false)
    private String email;

    @Column(name = "photo")
    private Boolean  photo;



}
