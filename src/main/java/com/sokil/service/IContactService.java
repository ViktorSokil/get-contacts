package com.sokil.service;


import com.sokil.domain.Contact;

import java.util.List;


public interface IContactService {
    List<Contact> findAllNotMatchWithRegEx(String regEx);
}
