package com.example.phonebook.dtos;


import lombok.Data;

@Data
public class ContactResponse {

    private String firstname;
    private String lastname;
    private String homephone;
    private String businessphone;
    private String email;
    private Boolean photo;



}
