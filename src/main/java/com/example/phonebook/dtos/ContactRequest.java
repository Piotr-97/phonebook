package com.example.phonebook.dtos;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class ContactRequest {



    @NotBlank(message = "firstname is mandatory")
    @Size(max = 100,message = "max 100")
    public String firstname;

    @NotBlank(message = "lastname is mandatory")
    @Size(max = 100,message = "max 100")
    private String lastname;

    @NotBlank(message = "home phone is mandatory")
    @Size(max = 20,message = "homephone should have 20 digits max")
    @Pattern(regexp = "\\d{9,}",message = "homephone should have at least 9 digits")
    private String homephone;

    @NotBlank(message = "business phone is mandatory")
    @Pattern(regexp = "\\d{9,}",message = "homephone should have at least 9 digits")
    private String businessphone;

    @Size(max = 100,message = "max 100")
    @Pattern(regexp = "\\S{3,}\\@{1}\\S{1,}\\.\\S{1,}",message = "email is not matching")
    @NotBlank(message = "email is mandatory")
    private String email;

    private Boolean photo;


}
