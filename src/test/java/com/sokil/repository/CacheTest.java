package com.sokil.repository;

import com.sokil.SpringBootApp;
import com.sokil.domain.Contact;
import net.sf.ehcache.CacheManager;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.number.OrderingComparison.greaterThan;
import static org.junit.Assert.assertThat;

/**
 * Created by Viktor on 21.09.2018.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootApp.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CacheTest {

    @Autowired
    private ContactRepository contactRepository;
    Contact contact = new Contact();

    @Before
    public void setUp(){
        contact.setName("Jasmin");
    }

    @Test
    public void testCache(){
        contactRepository.save(contact);
        contactRepository.findAll();
        int size = CacheManager.ALL_CACHE_MANAGERS.get(0)
                .getCache("com.sokil.domain.Contact").getSize();
        assertThat(size, greaterThan(0));
    }
}
