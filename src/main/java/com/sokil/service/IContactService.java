package com.sokil.service;


import com.sokil.domain.Contact;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface IContactService {
    List<Contact> findAllNotMatchWithRegEx(String regEx);

    List<Contact> findAllNotMatchWithRegEx(String regEx, Integer minId, Integer maxId);

    List<Contact> findAllNotMatchWithRegEx(String regEx, Pageable pageable);
}
