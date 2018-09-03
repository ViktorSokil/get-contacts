package com.sokil.web.rest;

import com.sokil.domain.Contact;
import com.sokil.domain.ListContactsWrapper;
import com.sokil.service.IContactService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

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
        log.info("Named filter: ", nameFilter);
        List<Contact> contacts = contactService.findAllNotMatchWithRegEx(nameFilter);
        log.info("Contacts: ", contacts);
        return new ListContactsWrapper(contacts);
    }


    @GetMapping(value = "/hello/contacts/{minId}/{maxId}")
    public ListContactsWrapper getContactsNotMatchedWithFilterBetweenId(@RequestParam("nameFilter") String nameFilter, @PathVariable Integer minId, @PathVariable Integer maxId) {
        log.info("Named filter: ", nameFilter);

        List<Contact> contacts = contactService.findAllNotMatchWithRegEx(nameFilter, minId, maxId);
        return new ListContactsWrapper(contacts);
    }

}
