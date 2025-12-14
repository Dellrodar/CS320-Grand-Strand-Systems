package appointmentservice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.Calendar;
import java.util.Date;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AppointmentServiceTest {

    AppointmentService service = new AppointmentService();

    private Date getFutureDate() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_YEAR, 1); // tomorrow
        return cal.getTime();
    }

    private Date getPastDate() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_YEAR, -1); // yesterday
        return cal.getTime();
    }

    @Test
    void AppointmentServiceInitTest() {
        assertNotNull(service);
    }

    @Test
    void AppointmentServiceGetUnknownAppointmentReturnsNullTest() {
        Appointment appt = service.getAppointment("does-not-exist");
        assertNull(appt);
    }

    @Test
    void AppointmentServiceAddAppointmentTest() {
        Date futureDate = getFutureDate();
        service.addAppointment("A1", futureDate, "Dentist");

        Appointment stored = service.getAppointment("A1");
        assertNotNull(stored);
        assertEquals("A1", stored.getAppointmentId());
        assertEquals("Dentist", stored.getDescription());
    }

    @Test
    void AppointmentServiceAddAppointmentWithBoundaryValuesTest() {
        Date futureDate = getFutureDate();
        String id10 = "1234567890"; // 10 characters
        String desc50 = "12345678901234567890123456789012345678901234567890"; // 50 characters

        service.addAppointment(id10, futureDate, desc50);

        Appointment stored = service.getAppointment(id10);
        assertNotNull(stored);
        assertEquals(id10, stored.getAppointmentId());
        assertEquals(desc50, stored.getDescription());
    }

    @Test
    void AppointmentServiceAddDuplicateAppointmentIdTest() {
        Date futureDate = getFutureDate();
        service.addAppointment("A1", futureDate, "Dentist");

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            service.addAppointment("A1", futureDate, "Doctor");
        });
    }

    @Test
    void AppointmentServiceAddAppointmentWithNullDescriptionThrowsTest() {
        Date futureDate = getFutureDate();

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            service.addAppointment("A3", futureDate, null);
        });
    }

    @Test
    void AppointmentServiceAddAppointmentWithTooLongDescriptionThrowsTest() {
        Date futureDate = getFutureDate();
        String longDescription = "This description is definitely longer than fifty characters long.";

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            service.addAppointment("A4", futureDate, longDescription);
        });
    }

    @Test
    void AppointmentServiceAddAppointmentWithPastDateThrowsTest() {
        Date pastDate = getPastDate();

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            service.addAppointment("A5", pastDate, "Past appointment");
        });
    }

    @Test
    void AppointmentServiceDeleteAppointmentIdTest() {
        Date futureDate = getFutureDate();
        service.addAppointment("A2", futureDate, "Checkup");

        Appointment appt = service.getAppointment("A2");
        assertNotNull(appt);

        service.deleteAppointment("A2");
        Appointment apptAfterDelete = service.getAppointment("A2");
        assertNull(apptAfterDelete);
    }

    @Test
    void AppointmentServiceDeleteUnknownAppointmentIdTest() {
        // No appointment with ID "A3" exists
        Appointment appt = service.getAppointment("A3");
        assertNull(appt);

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            service.deleteAppointment("A3");
        });
    }
}
