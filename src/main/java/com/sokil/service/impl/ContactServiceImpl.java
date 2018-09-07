package com.sokil.service.impl;

import com.sokil.domain.Contact;
import com.sokil.repository.ContactRepository;
import com.sokil.service.IContactService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContactServiceImpl implements IContactService{
    private final ContactRepository contactRepository;

    public ContactServiceImpl(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Contact> findAllNotMatchWithRegEx(String regEx) {
        return contactRepository
            .streamAll()
            .filter(contact ->
                !contact.getName().matches(regEx))
            .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<Contact> findAllNotMatchWithRegEx(String regEx, Integer minId, Integer maxId) {
        return contactRepository
            .findAllByBetweenId(minId, maxId)
            .filter(contact ->
                !contact.getName().matches(regEx))
            .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<Contact> findAllNotMatchWithRegEx(String regEx, Pageable pageable) {
        return contactRepository.streamAllPaged(pageable)
            .filter(contact ->
            !contact.getName().matches(regEx))
            .collect(Collectors.toList());
    }

}
