package taskservice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TaskServiceTest {
	TaskService taskService = new TaskService();

	@Test
	void TaskServiceInitTest() {
		assertNotNull(taskService);
	}

	@Test
	void TaskServiceGetUnknownTaskReturnsNullTest() {
		Task task = taskService.getTask("does-not-exist");
		assertNull(task);
	}
	
	@Test
	void TaskServiceAddTaskTest() {
		taskService.addTask("12345", "testTask1", "this is test task");
		Task task = taskService.getTask("12345");
		assertNotNull(task);
		assertEquals("testTask1", task.getName());
	}
	
	@Test
	void TaskServiceAddDuplicateTaskTest() {
		taskService.addTask("12345", "testTask1", "this is test task");
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			taskService.addTask("12345", "testTask1", "this is test task");
		});
	}

	@Test
	void TaskServiceAddTaskWithBoundaryValuesTest() {
		String id10 = "1234567890"; // 10 characters
		String name20 = "ABCDEFGHIJKLMNOPQRST"; // 20 characters
		String desc50 = "12345678901234567890123456789012345678901234567890"; // 50 characters

		taskService.addTask(id10, name20, desc50);
		Task task = taskService.getTask(id10);

		assertNotNull(task);
		assertEquals(id10, task.getId());
		assertEquals(name20, task.getName());
		assertEquals(desc50, task.getDescription());
	}
	
	@Test
	void TaskServiceDeleteTaskIdTest() {
		taskService.addTask("12345", "testTask1", "this is test task");
		Task task = taskService.getTask("12345");
		assertNotNull(task);
		
		taskService.deleteTask(task.getId());
		
		Task taskAfterDelete = taskService.getTask("12345");
		assertNull(taskAfterDelete);
	}
	
	@Test
	void TaskServiceDeleteTaskUnknownIdTest() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			taskService.deleteTask("12345");
		});
	}
	
	@Test
	void TaskServiceUpdateTaskNameTest() {
		taskService.addTask("12345", "testTask1", "this is test task");
		Task task = taskService.getTask("12345");
		assertEquals("testTask1", task.getName());
		
		taskService.updateName(task.getId(), "testMask9");
		
		Task taskAfterUpdate = taskService.getTask("12345");
		assertEquals("testMask9", taskAfterUpdate.getName());
	}

	@Test
	void TaskServiceUpdateTaskNameTooLongThrowsTest() {
		taskService.addTask("12345", "testTask1", "this is test task");

		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			taskService.updateName("12345", "this name is over twenty characters");
		});
	}
	
	@Test
	void TaskServiceUpdateTaskNameUnknownIdTest() {
		Task task = taskService.getTask("12345");
		assertNull(task);
		
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			taskService.updateName("12345", "testMask9");			
		});
	}
	
	@Test
	void TaskServiceUpdateTaskDescriptionTest() {
		taskService.addTask("12345", "testTask1", "this is test task");
		Task task = taskService.getTask("12345");
		assertEquals("this is test task", task.getDescription());
		
		taskService.updateDescription(task.getId(), "this is also a test task");
		
		Task taskAfterUpdate = taskService.getTask("12345");
		assertEquals("this is also a test task", taskAfterUpdate.getDescription());
	}

	@Test
	void TaskServiceUpdateTaskDescriptionTooLongThrowsTest() {
		taskService.addTask("12345", "testTask1", "this is test task");

		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			taskService.updateDescription(
				"12345",
				"I need to be well over fifty characters so this description is long."
			);
		});
	}
	
	@Test
	void TaskServiceUpdateTaskDescriptionUnknownIdTest() {
		Task task = taskService.getTask("12345");
		assertNull(task);
		
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			taskService.updateDescription("12345", "this is also a test task");			
		});
	}
}
