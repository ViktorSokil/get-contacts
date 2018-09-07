package com.sokil.service;

import com.sokil.GetContactsApp;
import com.sokil.domain.Contact;
import com.sokil.repository.ContactRepository;
import com.sokil.service.impl.ContactServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = GetContactsApp.class)
public class ContactServiceImplTest {

    @MockBean
    private ContactRepository contactRepository;

    @Autowired
    private ContactServiceImpl contactService;

    List<Contact> contacts = new ArrayList<>();

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);

        Contact contact = new Contact();
        contact.setName("Jasmin");
        Contact contact2 = new Contact();
        contact2.setName("Antonio");
        contacts.add(contact);
        contacts.add(contact2);
    }

    @Test
    public void findAllNotMatchWithRegExTest(){
        when(contactRepository.streamAll()).thenReturn(contacts.stream());
        List<Contact> contactList = contactService.findAllNotMatchWithRegEx("^A.*$");
        verify(contactRepository).streamAll();
        assertEquals(1, contactList.size());
        assertEquals("Jasmin", contactList.get(0).getName());
    }

}
