package com.example.phonebook.controller;


import com.example.phonebook.dtos.ContactRequest;
import com.example.phonebook.dtos.ContactResponse;
import com.example.phonebook.repository.ContactRepository;
import com.example.phonebook.repository.entity.Contact;
import com.example.phonebook.service.ContactService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@AllArgsConstructor
public class ContactController {

    private final ContactRepository contactRepository;
    private final ContactService contactService;
    private final ModelMapper modelMapper;


    @GetMapping("/contacts")
    public List<ContactResponse> getAllContacts() {
        return contactService.getAllContacts();
    }


    @PostMapping("/contact")
    public void addContact(@Valid @RequestBody ContactRequest contact) {
        Contact newContact = modelMapper.map(contact, Contact.class);
        //return contactService.addAddress(contact.getFirstname(),contact.getLastname(),contact.getEmail(),contact.getBusinessphone(),contact.getHomephone(),contact.getPhoto());
        contactRepository.save(newContact);
    }

    @GetMapping("/contact/lastname/{lastname}/businessphone/{businessphone}")
    public Optional<Contact> getContactByLastnameandBuisnessnumber(@PathVariable String lastname, @PathVariable String businessphone) {
        return contactService.getContactbyLastnameAndBuisnessPhone(lastname, businessphone);
    }


    @DeleteMapping("/contact/{id}")
    public ResponseEntity<?> deleteContact(@PathVariable Long id) {
        if (contactRepository.findById(id).isPresent()) {
            contactRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @PatchMapping("/contact/{id}")
    public ResponseEntity<?> updateContact(@PathVariable Long id, @RequestBody Contact contact) {
        if (contactService.updateContact(id, contact)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
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

}






