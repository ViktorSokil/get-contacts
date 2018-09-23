package com.sokil.service.impl;


import com.sokil.repository.ContactRepository;
import com.sokil.service.IContactService;
import com.sokil.domain.Contact;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class ContactServiceImpl implements IContactService {
    private final ContactRepository contactRepository;

    public ContactServiceImpl(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Contact> findAllNotMatchWithRegEx(String regEx) {
        Pattern pattern = Pattern.compile(regEx);
        Matcher m = pattern.matcher("");
        return contactRepository.findAll().stream()
            .filter(contact -> !m.reset(contact.getName()).matches())
            .collect(Collectors.toList());
    }

}
