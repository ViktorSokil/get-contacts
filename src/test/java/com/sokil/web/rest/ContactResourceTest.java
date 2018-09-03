package com.sokil.web.rest;


import com.sokil.GetContactsApp;
import com.sokil.domain.Contact;
import com.sokil.repository.ContactRepository;
import com.sokil.service.impl.ContactServiceImpl;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = GetContactsApp.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ContactResourceTest {
    private MockMvc restContactMockMvc;

    @Autowired
    private ContactServiceImpl contactService;
    @Autowired
    private ContactResource contactResource;
    @Autowired
    private ContactRepository contactRepository;

    private Contact contact;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.restContactMockMvc = MockMvcBuilders
            .standaloneSetup(contactResource)
            .build();
    }

    private static Contact createContacts() {
        Contact contact = new Contact();
        contact.setName("Jasmin");
        return contact;
    }

    @Before
    public void initTest() {
        contactRepository.deleteAll();
        contact = createContacts();
    }

    @Test
    //@Ignore
    public void getContactsNotMatchedWithRegEx()throws Exception {
        // Initialize the database
        contactRepository.saveAndFlush(contact);
        List<Contact> list = contactRepository.findAll();

        // Get all the companies
        restContactMockMvc.perform(get("/api/hello/contacts?nameFilter=^A.*$"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.contacts.[*].Contact.id").value(hasItem(contact.getId())))
            .andExpect(jsonPath("$.contacts.[*].Contact.name").value(hasItem(contact.getName())));
    }


}
