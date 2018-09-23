package com.sokil.web.rest;


import com.sokil.domain.Contact;
import com.sokil.domain.ListContactsWrapper;
import com.sokil.service.IContactService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ContactResource {

    private static final Logger log = LoggerFactory.getLogger(ContactResource.class);

    private final IContactService contactService;

    public ContactResource(IContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping(value = "/hello/contacts")
    public ListContactsWrapper getAllContactsNotMatchedWithFilter(@RequestParam("nameFilter")String nameFilter) {
        List<Contact> contacts = contactService.findAllNotMatchWithRegEx(nameFilter);
        log.info("Contacts: ", contacts);
        return new ListContactsWrapper(contacts);
    }
}
