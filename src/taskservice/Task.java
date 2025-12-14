package taskservice;

public class Task {
	private String id;
	private String name;
	private String description;
	
	public String getId() {
		return this.id;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getDescription() {
		return this.description;
	}
	
	private void setId(String id) {
		if (id == null || id.length() > 10) {
            throw new IllegalArgumentException("Invalid ID");
        }
		
		this.id = id;
	}
	
	public void setName(String name) {
		if (name == null || name.length() > 20) {
			throw new IllegalArgumentException("Invalid name");
		}
		
		this.name = name;
	}
	
	public void setDescription(String description) {
		if (description == null || description.length() > 50) {
			throw new IllegalArgumentException("Invalid description");
		}
		
		this.description = description;
	}
	
	public Task(String id, String name, String description) {
		this.setId(id);
		this.setName(name);
		this.setDescription(description);
    }

}
