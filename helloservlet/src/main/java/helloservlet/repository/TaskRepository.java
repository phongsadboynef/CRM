package helloservlet.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import helloservlet.config.MysqlConfig;
import helloservlet.entity.JobsEntity;
import helloservlet.entity.StatusEntity;
import helloservlet.entity.TaskEntity;
import helloservlet.entity.UserEntity;

public class TaskRepository {
public List<TaskEntity> getTasks (){
	List<TaskEntity> listTask = new ArrayList<TaskEntity>();

	try {
		Connection connection = MysqlConfig.getConnection();
		String query = "select  t.id, t.name as task_name, j.name as job_name,u.First_Name , u.Last_Name , t.start_date, t.end_date, s.name as status_name\r\n"
				+ "from tasks t inner join users u on t.user_id = u.id\r\n"
				+ "inner join status s on t.status_id = s.id \r\n"
				+ "inner join jobs j on t.job_id = j.id";
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		ResultSet resultSet = preparedStatement.executeQuery();
		// Trường hợp chúng ta chỉ select câu query nên sẽ là executeQuery
		while(resultSet.next()) {
			TaskEntity task = new TaskEntity();
			task.setId(resultSet.getInt("id"));
			task.setName(resultSet.getString("task_name"));
			task.setStartDate(resultSet.getString("start_date"));
			task.setEndDate(resultSet.getString("end_date"));
			
			JobsEntity jobsEntity = new JobsEntity();
			jobsEntity.setName("job_name"); 
			task.setIdJob(jobsEntity);
			
			UserEntity userEntity = new UserEntity();
			userEntity.setFullname(resultSet.getString("First_Name")+" "+ resultSet.getString("Last_Name"));
			task.setIdUser(userEntity);
			
			StatusEntity statusEntity = new StatusEntity();
			statusEntity.setName("status_name");
			task.setIdStatus(statusEntity);
			
			listTask.add(task);
		}
	}catch(Exception e){
		System.out.println(" kiem tra lay listTask " + e.getLocalizedMessage());
	}
	return listTask;
}
public int insertTask (String name, String starDay, String endDay, int idUser, int idJob) {
	int count = 0;
	String query = "insert into tasks (name, start_date, end_date, user_id, job_id, status_id)\n"
			+ "values (?, ?, ?, ?, ?, 1)"; // cái này do k có câu nào thêm bên bảng status nên mình tự điền mặc định là 1 chứ k được để nó null
	Connection connection = MysqlConfig.getConnection();
	try {
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setString(1, name);
		preparedStatement.setString(2, starDay);
		preparedStatement.setString(3, endDay);
		preparedStatement.setInt(4, idUser);
		preparedStatement.setInt(5, idJob);
		
		count= preparedStatement.executeUpdate();
		connection.close();
		
	} catch (Exception e) {
	System.out.println("loi insert Task " + e.getLocalizedMessage());
	}return count;
}
public int deleteById (int id) {
	int count = 0;
	String query = "delete from tasks t where t.id = ?";
	Connection connection = MysqlConfig.getConnection();
	try {
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setInt(1, id);
		
		count = preparedStatement.executeUpdate();
		connection.close();
	} catch (Exception e) {
		System.out.println("loi delete Task " + e.getLocalizedMessage());
	}return count;
}
public int updateTask(String name, String starDay, String endDay, int idUser, int idJob, int idStatus, int id) {
	int count = 0;
	Connection connection = MysqlConfig.getConnection();
	String query = "update tasks set name=?, start_date=?, end_date=?, user_id=?, job_id=?, status_id=? where id=?";
	
	try {
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setString(1, name);
		preparedStatement.setString(2, starDay);
		preparedStatement.setString(3, endDay);
		preparedStatement.setInt(4, idUser);
		preparedStatement.setInt(5, idJob);
		preparedStatement.setInt(6, idStatus);
		preparedStatement.setInt(7, id);
		
		count = preparedStatement.executeUpdate();

		connection.close();
	} catch (Exception e) {
		System.out.println("Loi  update task " + e.getLocalizedMessage());
	}
	return count;
}

public List<TaskEntity> findTaskById(int id) {
	List<TaskEntity> tasks = new ArrayList<>();
	String query ="SELECT *\r\n"
			+ "FROM tasks t \r\n"
			+ "WHERE id = ?";
	try {
		Connection connection = MysqlConfig.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setInt(1, id);
		ResultSet resultSet = preparedStatement.executeQuery();

		while (resultSet.next()) {
			TaskEntity task = new TaskEntity();
			task.setId(resultSet.getInt("id"));
			task.setName(resultSet.getString("name"));
			task.setStartDate(resultSet.getString("start_date"));
			task.setEndDate(resultSet.getString("end_date"));
			
			UserEntity userEntity = new UserEntity();
			userEntity.setFullname(resultSet.getString("First_Name")+" "+ resultSet.getString("Last_Name"));
			task.setIdUser(userEntity);
			
			JobsEntity jobsEntity = new JobsEntity();
			jobsEntity.setName("job_name"); 
			task.setIdJob(jobsEntity);
			
			StatusEntity statusEntity = new StatusEntity();
			statusEntity.setName("status_name");
			task.setIdStatus(statusEntity);		
		}
		connection.close();

	} catch (Exception e) {
		System.out.println("loi findTaskByUserId " + e.getMessage());
	}
	return tasks;
}
}
