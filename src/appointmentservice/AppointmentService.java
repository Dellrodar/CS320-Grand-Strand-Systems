package appointmentservice;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class AppointmentService {

    private Map<String, Appointment> appointments = new HashMap<>();

    public AppointmentService() {}

    public void addAppointment(String appointmentId, Date appointmentDate, String description) {
        if (appointments.containsKey(appointmentId)) {
            throw new IllegalArgumentException("Appointment ID already exists.");
        }
        Appointment appt = new Appointment(appointmentId, appointmentDate, description);
        appointments.put(appointmentId, appt);
    }

    public void deleteAppointment(String appointmentId) {
        if (!appointments.containsKey(appointmentId)) {
            throw new IllegalArgumentException("Appointment ID does not exist.");
        }
        appointments.remove(appointmentId);
    }

    public Appointment getAppointment(String appointmentId) {
        return appointments.get(appointmentId);
    }
}
