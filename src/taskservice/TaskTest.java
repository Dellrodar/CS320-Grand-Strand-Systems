package taskservice;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TaskTest {

	@Test
	void testTaskClass() {
		Task task = new Task("12345", "testTask1", "this is test task");
		assertTrue(task.getId().equals("12345"));
		assertTrue(task.getName().equals("testTask1"));
		assertTrue(task.getDescription().equals("this is test task"));
	}
	
	@Test
	void testTaskClassNoId() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Task(null, "testTask1", "this is test task");
		});
	}
	
	@Test
	void testTaskClassTooLongId() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Task("123456789106", "testTask1", "this is test task");
		});
	}

	@Test
	void testTaskClassBoundaryMaxIdLength() {
		String id10 = "1234567890"; // 10 characters
		Task task = new Task(id10, "testTask1", "this is test task");
		assertTrue(task.getId().equals(id10));
	}
	
	@Test
	void testTaskClassNoName() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Task("12345", null, "this is test task");
		});
	}
	
	@Test
	void testTaskClassNameTooLong() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Task("12345", "im way over the twenty char limit", "this is test task");
		});
	}

	@Test
	void testTaskClassBoundaryMaxNameLength() {
		String name20 = "ABCDEFGHIJKLMNOPQRST"; // 20 characters
		Task task = new Task("12345", name20, "this is test task");
		assertTrue(task.getName().equals(name20));
	}
	
	@Test
	void testTaskClassNoDescription() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Task("12345", "testTask1", null);
		});
	}
	
	@Test
	void testTaskClassDescriptionTooLong() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Task(
				"12345",
				"testTask1",
				"I need to be well over 50 characters. Unfortunately that means this message needs to be very long."
			);
		});
	}

	@Test
	void testTaskClassBoundaryMaxDescriptionLength() {
		String desc50 = "12345678901234567890123456789012345678901234567890"; // 50 characters
		Task task = new Task("12345", "testTask1", desc50);
		assertTrue(task.getDescription().equals(desc50));
	}
}
