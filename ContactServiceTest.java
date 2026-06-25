package contactservice;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ContactServiceTest {
    private ContactService service;
    private Contact contact;

    @BeforeEach
    void setUp() {
        service = new ContactService();
        contact = new Contact("1", "Angelo", "Salas", "1234567890", "123 Main Street");
    }

    @Test
    void testAddContact() {
        service.addContact(contact);
        assertEquals(contact, service.getContact("1"));
    }

    @Test
    void testAddDuplicateContactId() {
        service.addContact(contact);

        Contact duplicate = new Contact("1", "John", "Smith", "0987654321", "456 Oak Street");

        assertThrows(IllegalArgumentException.class, () -> {
            service.addContact(duplicate);
        });
    }

    @Test
    void testAddNullContact() {
        assertThrows(IllegalArgumentException.class, () -> {
            service.addContact(null);
        });
    }

    @Test
    void testDeleteContact() {
        service.addContact(contact);
        service.deleteContact("1");

        assertNull(service.getContact("1"));
    }

    @Test
    void testDeleteContactNotFound() {
        assertThrows(IllegalArgumentException.class, () -> {
            service.deleteContact("99");
        });
    }

    @Test
    void testUpdateFirstName() {
        service.addContact(contact);
        service.updateFirstName("1", "John");

        assertEquals("John", service.getContact("1").getFirstName());
    }

    @Test
    void testUpdateLastName() {
        service.addContact(contact);
        service.updateLastName("1", "Smith");

        assertEquals("Smith", service.getContact("1").getLastName());
    }

    @Test
    void testUpdatePhone() {
        service.addContact(contact);
        service.updatePhone("1", "0987654321");

        assertEquals("0987654321", service.getContact("1").getPhone());
    }

    @Test
    void testUpdateAddress() {
        service.addContact(contact);
        service.updateAddress("1", "456 Oak Street");

        assertEquals("456 Oak Street", service.getContact("1").getAddress());
    }

    @Test
    void testUpdateContactNotFound() {
        assertThrows(IllegalArgumentException.class, () -> {
            service.updateFirstName("99", "John");
        });
    }

    @Test
    void testUpdateInvalidFirstName() {
        service.addContact(contact);

        assertThrows(IllegalArgumentException.class, () -> {
            service.updateFirstName("1", "TooLongName");
        });
    }

    @Test
    void testUpdateInvalidLastName() {
        service.addContact(contact);

        assertThrows(IllegalArgumentException.class, () -> {
            service.updateLastName("1", "TooLongLast");
        });
    }

    @Test
    void testUpdateInvalidPhone() {
        service.addContact(contact);

        assertThrows(IllegalArgumentException.class, () -> {
            service.updatePhone("1", "12345");
        });
    }

    @Test
    void testUpdateInvalidAddress() {
        service.addContact(contact);

        assertThrows(IllegalArgumentException.class, () -> {
            service.updateAddress("1", "This address is way too long to use");
        });
    }
}