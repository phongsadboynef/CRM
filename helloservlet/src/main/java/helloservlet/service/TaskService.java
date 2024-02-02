package helloservlet.service;

import java.util.List;

import helloservlet.entity.TaskEntity;
import helloservlet.repository.JobsRepository;
import helloservlet.repository.RoleRepository;
import helloservlet.repository.TaskRepository;
import helloservlet.repository.UserRepository;

public class TaskService {
private UserRepository userRepository = new UserRepository();
private RoleRepository roleRepository = new RoleRepository();
private JobsRepository jobsRepository = new JobsRepository();
private TaskRepository taskRepository = new TaskRepository();

public List<TaskEntity> getAllTask(){
	return taskRepository.getTasks();
}
public boolean insert(String name, String starDay, String endDay, int idUser, int idJob) {
	int count = taskRepository.insertTask(name, starDay, endDay, idUser, idJob);
	return count > 0;
}
public boolean deleteTask (int id) {
	int count = taskRepository.deleteById(id);
	return count>0;
}
public boolean updateTask(String name, String starDay, String endDay, int idUser, int idJob, int idStatus, int id) {
	int count = taskRepository.updateTask(name, starDay, endDay, idUser, idJob, idStatus, id);
	return  count>0;
}
public List<TaskEntity> findTasksId(int id) {
	return taskRepository.findTaskById(id);
}	
}
