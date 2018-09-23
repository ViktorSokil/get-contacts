package com.sokil.domain;


import java.util.List;

public class ListContactsWrapper {
    List<Contact> contacts;

    public ListContactsWrapper() {
    }

    public ListContactsWrapper(List<Contact> contacts) {
        this.contacts = contacts;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }
}
