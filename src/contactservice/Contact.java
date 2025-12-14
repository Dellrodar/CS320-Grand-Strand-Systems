package contactservice;

public class Contact {
    private String id;
	private String firstName;
	private String lastName;
	private String number;
	private String address;
    
    public String getId() {
        return this.id;
    }
    
    public String getFirstName() {
    	return this.firstName;
    }
    
    public String getLastName() {
    	return this.lastName;
    }
    
    public String getPhoneNumber() {
    	return this.number;
    }
    
    public String getAddress() {
    	return this.address;
    }
    
    public void setFirstName(String firstName) {
        if (firstName == null || firstName.length() > 10) {
            throw new IllegalArgumentException("Invalid firstName");
        }
        this.firstName = firstName;
    }
    
    public void setLastName(String lastName) {
        if (lastName == null || lastName.length() > 10) {
            throw new IllegalArgumentException("Invalid lastName");
        }
        this.lastName = lastName;
    }
    
    public void setNumber(String number) {
        if (number == null || number.length() != 10) {
            throw new IllegalArgumentException("Invalid phone");
        }
        this.number = number;
    }
    
    public void setAddress(String address) {
        if (address == null || address.length() > 30) {
            throw new IllegalArgumentException("Invalid address");
        }
        this.address = address;
    }
    
    public Contact(String id, String firstName, String lastName, String number, String address) {
        if (id == null || id.length() > 10) {
            throw new IllegalArgumentException("Invalid ID");
        }

        this.id = id;                
        this.setFirstName(firstName); 
        this.setLastName(lastName);
        this.setNumber(number);     
        this.setAddress(address);  
    }
    
}
