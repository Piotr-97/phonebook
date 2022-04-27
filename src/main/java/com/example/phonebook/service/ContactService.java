package com.example.phonebook.service;

import com.example.phonebook.dtos.ContactResponse;
import com.example.phonebook.repository.ContactRepository;
import com.example.phonebook.repository.entity.Contact;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.*;

@Service
@AllArgsConstructor
public class ContactService {


    private final ContactRepository contactRepository;
    private final ModelMapper modelMapper;

    public List<ContactResponse> getAllContacts() {
        List<Contact> contacts = contactRepository.findAll();

        List<ContactResponse> result = new ArrayList<>();
        for (Contact contact :
                contacts) {
            ContactResponse contactResponse = modelMapper.map(contact, ContactResponse.class);
            result.add(contactResponse);
        }
        return result;
    }

    public Optional<Contact> getContactbyLastnameAndBuisnessPhone(String lastname, String businessphone) {
        return contactRepository.findByLastnameAndBusinessphone(lastname, businessphone);


    }

    public Contact addAddress(String firstname, String lastname, String email,String businessphone,String homephone,Boolean photo){
        Contact contact = Contact.builder()
                .firstname(firstname)
                .lastname(lastname)
                .email(email)
                .businessphone(businessphone)
                .homephone(homephone)
                .photo(photo)
                .build();

        return contactRepository.save(contact);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }


    public boolean updateContact(Long id, Contact updatedcontact) {
        Optional<Contact> currentContact = contactRepository.findById(id);
        if (currentContact.isPresent()) {
            Contact newContact = currentContact.get();

            if (updatedcontact.getFirstname() != null) {
                newContact.setFirstname(updatedcontact.getFirstname());
            }
            if (updatedcontact.getLastname() != null) {
                newContact.setLastname(updatedcontact.getLastname());
            }
            if (updatedcontact.getBusinessphone() != null) {
                newContact.setBusinessphone(updatedcontact.getBusinessphone());
            }
            if ((updatedcontact.getHomephone() != null)) {
                newContact.setHomephone(updatedcontact.getHomephone());
            }
            if ((updatedcontact.getHomephone() != null)) {
                newContact.setHomephone(updatedcontact.getHomephone());
            }
            if (updatedcontact.getEmail() != null) {
                newContact.setEmail(updatedcontact.getEmail());
            }
            if (updatedcontact.getPhoto() != null) {
                newContact.setPhoto(updatedcontact.getPhoto());
            }
            contactRepository.save(newContact);
            return true;
        }
        return false;
    }
}
