package com.example.phonebook.repository;

import com.example.phonebook.dtos.ContactResponse;
import com.example.phonebook.repository.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {
    Optional<Contact> findByLastnameAndBusinessphone(String lastname,String businessphone);
}
