package contactservice;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ContactTest {
	@Test
	void testContactClass() {
		Contact contact = new Contact("12345", "Emilio", "Crocco", "1234562514", "102 Makebelieve Place");
		assertTrue(contact.getId().equals("12345"));
		assertTrue(contact.getFirstName().equals("Emilio"));
		assertTrue(contact.getLastName().equals("Crocco"));
		assertTrue(contact.getPhoneNumber().equals("1234562514"));
		assertTrue(contact.getAddress().equals("102 Makebelieve Place"));
	}

	@Test
	void testContactClassNoId() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Contact(null, "Emilio", "Crocco", "1234562514", "102 Makebelieve Place");
		});
	}
	
	@Test
	void testContactClassTooLongId() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Contact("12345678910", "Emilio", "Crocco", "1234562514", "102 Makebelieve Place");
		});
	}
	
	@Test
	void testContactClassBoundaryMaxIdLength() {
	    String id10 = "1234567890"; // 10 characters
	    Contact contact = new Contact(id10, "Emilio", "Crocco", "1234567890", "123 Main Street");
	    assertTrue(contact.getId().equals(id10));
	}
	
	@Test
	void testContactClassNoFirstName() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Contact("12345", null, "Crocco", "1234562514", "102 Makebelieve Place");
		});
	}
	
	@Test
	void testContactClassTooLongFirstName() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Contact("12345", "Emjiliocrosd", "Crocco", "1234562514", "102 Makebelieve Place");
		});
	}
	
	@Test
	void testContactClassBoundaryMaxFirstNameLength() {
	    String firstName10 = "ABCDEFGHIJ"; // 10 characters
	    Contact contact = new Contact("12345", firstName10, "Crocco", "1234567890", "123 Main Street");
	    assertTrue(contact.getFirstName().equals(firstName10));
	}
	
	@Test
	void testContactClassNoLastName() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Contact("12345", "Emilio", null, "1234562514", "102 Makebelieve Place");
		});
	}
	
	@Test
	void testContactClassTooLongLastName() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Contact("12345", "Emilio", "CroccoCrocco", "1234562514", "102 Makebelieve Place");
		});
	}
	
	@Test
	void testContactClassBoundaryMaxLastNameLength() {
	    String lastName10 = "ABCDEFGHIJ"; // 10 characters
	    Contact contact = new Contact("12345", "Emilio", lastName10, "1234567890", "123 Main Street");
	    assertTrue(contact.getLastName().equals(lastName10));
	}
	
	@Test
	void testContactClassNoPhoneNumber() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Contact("12345", "Emilio", "Crocco", null, "102 Makebelieve Place");
		});
	}
	
	@Test
	void testContactClassTooLongPhoneNumber() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Contact("12345", "Emilio", "Crocco", "1234562514064254", "102 Makebelieve Place");
		});
	}
	
	@Test
	void testContactClassTooShortPhoneNumber() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Contact("12345", "Emilio", "Crocco", "4254", "102 Makebelieve Place");
		});
	}
	
	@Test
	void testContactClassBoundaryPhoneNumberExact10Digits() {
	    String phone10 = "0123456789"; // exactly 10 digits
	    Contact contact = new Contact("12345", "Emilio", "Crocco", phone10, "123 Main Street");
	    assertTrue(contact.getPhoneNumber().equals(phone10));
	}
	
	@Test
	void testContactClassNoAddress() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Contact("12345", "Emilio", "Crocco", "1234562514", null);
		});
	}
	
	@Test
	void testContactClassTooLongAddress() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Contact("12345", "Emilio", "Crocco", "1234562514", "102 Makebelieve Place102 Makebelieve Place102 Makebelieve Place");
		});
	}
	
	@Test
	void testContactClassBoundaryMaxAddressLength() {
	    String address30 = "123456789012345678901234567890"; // 30 chars
	    Contact contact = new Contact("12345", "Emilio", "Crocco", "1234567890", address30);
	    assertTrue(contact.getAddress().equals(address30));
	}
}
