package appointmentservice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Calendar;
import java.util.Date;

import org.junit.jupiter.api.Test;

public class AppointmentTest {

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
    public void AppointmentValidCreationTest() {
        Date futureDate = getFutureDate();
        Appointment appointment = new Appointment("1234567890", futureDate, "Regular checkup");

        assertEquals("1234567890", appointment.getAppointmentId());
        assertEquals(futureDate, appointment.getAppointmentDate());
        assertEquals("Regular checkup", appointment.getDescription());
    }

    @Test
    public void AppointmentDescriptionBoundaryMaxLengthTest() {
        Date futureDate = getFutureDate();
        String desc50 = "12345678901234567890123456789012345678901234567890"; // 50 characters

        Appointment appointment = new Appointment("123", futureDate, desc50);

        assertEquals(desc50, appointment.getDescription());
    }

    @Test
    public void AppointmentIdCannotBeNullTest() {
        Date futureDate = getFutureDate();
        assertThrows(IllegalArgumentException.class, () -> {
            new Appointment(null, futureDate, "Description");
        });
    }

    @Test
    public void AppointmentIdTooLongTest() {
        Date futureDate = getFutureDate();
        assertThrows(IllegalArgumentException.class, () -> {
            new Appointment("12345678901", futureDate, "Description"); // 11 chars
        });
    }

    @Test
    public void AppointmentDateCannotBeNullTest() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Appointment("123", null, "Description");
        });
    }

    @Test
    public void AppointmentDateCannotBeInThePastTest() {
        Date pastDate = getPastDate();
        assertThrows(IllegalArgumentException.class, () -> {
            new Appointment("123", pastDate, "Description");
        });
    }

    @Test
    public void AppointmentDescriptionCannotBeNullTest() {
        Date futureDate = getFutureDate();
        assertThrows(IllegalArgumentException.class, () -> {
            new Appointment("123", futureDate, null);
        });
    }

    @Test
    public void AppointmentDescriptionTooLongTest() {
        Date futureDate = getFutureDate();
        String longDescription = "This description is definitely longer than fifty characters long.";
        assertThrows(IllegalArgumentException.class, () -> {
            new Appointment("123", futureDate, longDescription);
        });
    }

    @Test
    public void AppointmentSetValidDateTest() {
        Date futureDate = getFutureDate();
        Appointment appointment = new Appointment("123", futureDate, "Description");

        Date newFutureDate = getFutureDate();
        appointment.setAppointmentDate(newFutureDate);

        assertEquals(newFutureDate, appointment.getAppointmentDate());
    }

    @Test
    public void AppointmentSetPastDateThrowsExceptionTest() {
        Date futureDate = getFutureDate();
        Appointment appointment = new Appointment("123", futureDate, "Description");

        Date pastDate = getPastDate();
        assertThrows(IllegalArgumentException.class, () -> {
            appointment.setAppointmentDate(pastDate);
        });
    }

    @Test
    public void AppointmentSetNullDateThrowsExceptionTest() {
        Date futureDate = getFutureDate();
        Appointment appointment = new Appointment("123", futureDate, "Description");

        assertThrows(IllegalArgumentException.class, () -> {
            appointment.setAppointmentDate(null);
        });
    }

    @Test
    public void AppointmentSetValidDescriptionTest() {
        Date futureDate = getFutureDate();
        Appointment appointment = new Appointment("123", futureDate, "Old description");

        appointment.setDescription("New description");

        assertEquals("New description", appointment.getDescription());
    }

    @Test
    public void AppointmentSetTooLongDescriptionThrowsExceptionTest() {
        Date futureDate = getFutureDate();
        Appointment appointment = new Appointment("123", futureDate, "Desc");

        String longDescription = "This description is definitely longer than fifty characters long.";
        assertThrows(IllegalArgumentException.class, () -> {
            appointment.setDescription(longDescription);
        });
    }

    @Test
    public void AppointmentSetNullDescriptionThrowsExceptionTest() {
        Date futureDate = getFutureDate();
        Appointment appointment = new Appointment("123", futureDate, "Desc");

        assertThrows(IllegalArgumentException.class, () -> {
            appointment.setDescription(null);
        });
    }
}
