package contactservice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ContactServiceTest {
	ContactService service = new ContactService();
	
	@Test
	void ContactServiceInitTest() {
		assertNotNull(service);
	}
	
	@Test
	void ContactServiceGetUnknownContactReturnsNullTest() {
	    Contact user = service.getContact("does-not-exist");
	    assertNull(user);
	}
	
	@Test
	void ContactServiceAddContactTest() {
		service.addContact("12345", "Emilio", "Crocco", "1234567891", "102 Makebelieve Place");
		Contact user = service.getContact("12345");
		assertNotNull(user);
		assertEquals("Emilio", user.getFirstName());
	}
	
	@Test
	void ContactServiceAddDuplicateContactIdTest() {
		service.addContact("12345", "Emilio", "Crocco", "1234567891", "102 Makebelieve Place");
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			service.addContact("12345", "Emilio", "Crocco", "1234567891", "102 Makebelieve Place");	
		});		
	}
	
	@Test
	void ContactServiceAddContactWithBoundaryValuesTest() {
	    String id10 = "1234567890";
	    String firstName10 = "ABCDEFGHIJ";
	    String lastName10 = "KLMNOPQRST";
	    String phone10 = "0123456789";
	    String address30 = "123456789012345678901234567890";

	    service.addContact(id10, firstName10, lastName10, phone10, address30);
	    Contact user = service.getContact(id10);

	    assertNotNull(user);
	    assertEquals(firstName10, user.getFirstName());
	    assertEquals(lastName10, user.getLastName());
	    assertEquals(phone10, user.getPhoneNumber());
	    assertEquals(address30, user.getAddress());
	}

	// New negative tests for addContact to show robust error handling through the service API
	@Test
	void ContactServiceAddContactNullFirstNameThrowsTest() {
	    Assertions.assertThrows(IllegalArgumentException.class, () -> {
	        service.addContact("12345", null, "Crocco", "1234567890", "102 Makebelieve Place");
	    });
	}

	@Test
	void ContactServiceAddContactPhoneTooShortThrowsTest() {
	    Assertions.assertThrows(IllegalArgumentException.class, () -> {
	        service.addContact("12345", "Emilio", "Crocco", "1234", "102 Makebelieve Place");
	    });
	}

	@Test
	void ContactServiceAddContactAddressTooLongThrowsTest() {
	    String longAddress = "123456789012345678901234567890XXX"; // > 30
	    Assertions.assertThrows(IllegalArgumentException.class, () -> {
	        service.addContact("12345", "Emilio", "Crocco", "1234567890", longAddress);
	    });
	}
	
	@Test
	void ContactServiceDeleteContactIdTest() {
		service.addContact("12345", "Emilio", "Crocco", "1234567891", "102 Makebelieve Place");
		Contact user = service.getContact("12345");
		assertNotNull(user);
		
		service.deleteContact(user.getId());
		
		Contact userAfterDelete = service.getContact("12345");
		assertNull(userAfterDelete);
	}
	
	@Test
	void ContactServiceDeleteUnknownContactIdTest() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			service.deleteContact("12345");	
		});	
	}
	
	@Test
	void ContactServiceUpdateContactFirstNameTest() {
		service.addContact("12345", "Emilio", "Crocco", "1234567891", "102 Makebelieve Place");
		Contact user = service.getContact("12345");
		assertEquals(user.getFirstName(), "Emilio");
		
		service.updateFirstName("12345", "Tensei");
		assertEquals(user.getFirstName(), "Tensei");
	}
	
	@Test
	void ContactServiceUpdateFirstNameTooLongThrowsTest() {
	    service.addContact("12345", "Emilio", "Crocco", "1234567890", "102 Makebelieve Place");
	    
	    Assertions.assertThrows(IllegalArgumentException.class, () -> {
	        service.updateFirstName("12345", "NameIsWayTooLong");
	    });
	}
	
	@Test
	void ContactServiceUpdateFirstNameUnknownIdTest() {
		Contact user = service.getContact("12345");
		assertNull(user);
		
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			service.updateFirstName("12345", "Tensei");
		});	
	}

	@Test
	void ContactServiceUpdateContactLastNameTest() {
		service.addContact("12345", "Emilio", "Crocco", "1234567891", "102 Makebelieve Place");
		Contact user = service.getContact("12345");
		assertEquals(user.getLastName(), "Crocco");
		
		service.updateLastName("12345", "Lefty");
		assertEquals(user.getLastName(), "Lefty");
	}
	
	@Test
	void ContactServiceUpdateLastNameTooLongThrowsTest() {
	    service.addContact("12345", "Emilio", "Crocco", "1234567890", "102 Makebelieve Place");
	    
	    Assertions.assertThrows(IllegalArgumentException.class, () -> {
	        service.updateLastName("12345", "NameIsWayTooLong");
	    });
	}
	
	@Test
	void ContactServiceUpdateLastNameUnknownIdTest() {
		Contact user = service.getContact("12345");
		assertNull(user);
		
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			service.updateLastName("12345", "Lefty");
		});	
	}
	
	@Test
	void ContactServiceUpdateContactPhoneNumberTest() {
		service.addContact("12345", "Emilio", "Crocco", "1234567891", "102 Makebelieve Place");
		Contact user = service.getContact("12345");
		assertEquals(user.getPhoneNumber(), "1234567891");
		
		service.updateNumber("12345", "9876543210");
		assertEquals(user.getPhoneNumber(), "9876543210");
	}
	
	@Test
	void ContactServiceUpdateNumberTooShortThrowsTest() {
	    service.addContact("12345", "Emilio", "Crocco", "1234567890", "102 Makebelieve Place");
	    
	    Assertions.assertThrows(IllegalArgumentException.class, () -> {
	        service.updateNumber("12345", "1234");
	    });
	}
	
	@Test
	void ContactServiceUpdateNumberTooLongThrowsTest() {
		service.addContact("12345", "Emilio", "Crocco", "1234567890", "102 Makebelieve Place");
		
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			service.updateNumber("12345", "1234562514064254");
		});
	}
	
	@Test
	void ContactServiceUpdatePhoneUnknownIdTest() {
		Contact user = service.getContact("12345");
		assertNull(user);
		
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			service.updateNumber("12345", "9876543210");
		});	
	}
	
	@Test
	void ContactServiceUpdateContactAddressTest() {
		service.addContact("12345", "Emilio", "Crocco", "1234567891", "102 Makebelieve Place");
		Contact user = service.getContact("12345");
		assertEquals(user.getAddress(), "102 Makebelieve Place");
		
		service.updateAddress("12345", "191 Dream Blvd");
		assertEquals(user.getAddress(), "191 Dream Blvd");
	}
	
	@Test
	void ContactServiceUpdateAddressTooLongThrowsTest() {
	    service.addContact("12345", "Emilio", "Crocco", "1234567890", "102 Makebelieve Place");
	    
	    String longAddress = "123456789012345678901234567890XXX"; // > 30
	    Assertions.assertThrows(IllegalArgumentException.class, () -> {
	        service.updateAddress("12345", longAddress);
	    });
	}
	
	@Test
	void ContactServiceUpdateAddressUnknownIdTest() {
		Contact user = service.getContact("12345");
		assertNull(user);
		
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			service.updateAddress("12345", "191 Dream Blvd");
		});	
	}
}
