package taskservice;

import java.util.ArrayList;
import java.util.List;

public class TaskService {
	
	private List<Task> tasks = new ArrayList<>();
	
	private Task getTaskById(String id) {
        for (Task task : tasks) {
            if (task.getId().equals(id)) {
                return task;
            }
        }
        return null;
    }
	
	public Task getTask(String id) {
        return getTaskById(id);
    }
	
	public void addTask(String id, String name, String description) {
		if (getTask(id) != null) {
			throw new IllegalArgumentException("Task ID must be unique");
		}
		
		Task newTask = new Task(id, name, description);
		tasks.add(newTask);
	}
	
	public void deleteTask(String id) {
		Task task = getTask(id);
		if (task == null) {
			throw new IllegalArgumentException("Task ID not found");
		}
		
		tasks.remove(task);
	}

	public void updateName(String id, String name) {
		Task task = getTask(id);
		if (task == null) {
			throw new IllegalArgumentException("Task ID not found");
		}
		
		task.setName(name);
	}
	
	public void updateDescription(String id, String description) {
		Task task = getTask(id);
		if (task == null) {
			throw new IllegalArgumentException("Task ID not found");
		}
		
		task.setDescription(description);
	}
}
