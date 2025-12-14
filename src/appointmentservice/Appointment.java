package appointmentservice;

import java.util.Date;

public class Appointment {
	private String appointmentId;
	private Date appointmentDate;
	private String description;
	
	public String getAppointmentId() {
		return this.appointmentId;
	}
	
	public Date getAppointmentDate() {
		return this.appointmentDate;
	}
	
	public String getDescription() {
		return this.description;
	}
	
	// ID is only set in the constructor and never updated
	private void setAppointmentId(String appointmentId) {
		if (appointmentId == null || appointmentId.length() > 10) {
			throw new IllegalArgumentException("Invalid appointment ID");
		}
		this.appointmentId = appointmentId;
	}
	
	public void setAppointmentDate(Date appointmentDate) {
		if (appointmentDate == null) {
			throw new IllegalArgumentException("Invalid appointment date: null");
		}
		// Date cannot be in the past
		if (appointmentDate.before(new Date())) {
			throw new IllegalArgumentException("Invalid appointment date: in the past");
		}
		this.appointmentDate = appointmentDate;
	}
	
	public void setDescription(String description) {
		if (description == null || description.length() > 50) {
			throw new IllegalArgumentException("Invalid description");
		}
		this.description = description;
	}
	
	public Appointment(String appointmentId, Date appointmentDate, String description) {
		this.setAppointmentId(appointmentId);
		this.setAppointmentDate(appointmentDate);
		this.setDescription(description);
	}
}
