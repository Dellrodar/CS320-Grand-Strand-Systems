package contactservice;

import java.util.ArrayList;
import java.util.List;

public class ContactService {

    private List<Contact> contacts = new ArrayList<>();

    private Contact getContactById(String id) {
        for (Contact contact : contacts) {
            if (contact.getId().equals(id)) {
                return contact;
            }
        }
        return null;
    }
    
    public Contact getContact(String id) {
        return getContactById(id);
    }

    public void addContact(String id, String firstName, String lastName, String number, String address) {
        if (getContactById(id) != null) {
            throw new IllegalArgumentException("Contact ID must be unique");
        }

        Contact newContact = new Contact(id, firstName, lastName, number, address);
        contacts.add(newContact);
    }

    public void deleteContact(String id) {
        Contact contact = getContactById(id);
        if (contact == null) {
            throw new IllegalArgumentException("Contact ID not found");
        }
        contacts.remove(contact);
    }

    public void updateFirstName(String id, String firstName) {
        Contact contact = getContactById(id);
        if (contact == null) {
            throw new IllegalArgumentException("Contact ID not found");
        }
        contact.setFirstName(firstName);
    }

    public void updateLastName(String id, String lastName) {
        Contact contact = getContactById(id);
        if (contact == null) {
            throw new IllegalArgumentException("Contact ID not found");
        }
        contact.setLastName(lastName);
    }

    public void updateNumber(String id, String number) {
        Contact contact = getContactById(id);
        if (contact == null) {
            throw new IllegalArgumentException("Contact ID not found");
        }
        contact.setNumber(number);
    }

    public void updateAddress(String id, String address) {
        Contact contact = getContactById(id);
        if (contact == null) {
            throw new IllegalArgumentException("Contact ID not found");
        }
        contact.setAddress(address);
    }
}
