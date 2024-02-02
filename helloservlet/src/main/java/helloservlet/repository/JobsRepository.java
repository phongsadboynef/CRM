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

public class JobsRepository {
	public List<JobsEntity> getJobs(){
		List<JobsEntity> listJobs= new ArrayList<JobsEntity>();
		String query = "select * from jobs"; // chuan bi cau query truoc ben database
		try {
			Connection connection = MysqlConfig.getConnection();
			PreparedStatement prepareStatement = connection.prepareStatement(query);
			ResultSet resultSet = prepareStatement.executeQuery(); 
			while(resultSet.next()) {
				JobsEntity jobs = new JobsEntity();
				jobs.setId(resultSet.getInt("id"));
				jobs.setName(resultSet.getString("name"));
				jobs.setStartDate(resultSet.getString("start_date"));
				jobs.setEndDate(resultSet.getString("end_date"));
				listJobs.add(jobs);
			}
		}catch(Exception e) {
			System.out.println("Loi getjobs: " + e.getLocalizedMessage());
		}
		return listJobs;
}
public int insertJobs (String name, String starDate, String endDate) {
	String query = "insert into jobs (name, start_date, end_date) values (?, ?, ?);";
	int count =0;
	Connection connection = MysqlConfig.getConnection();
	try {
		PreparedStatement prepareStatement = connection.prepareStatement(query);
		prepareStatement.setString(1, name);
		prepareStatement.setString(2, starDate);
		prepareStatement.setString(3, endDate);
		
		count = prepareStatement.executeUpdate();
		connection.close();
	}catch(Exception e ) {
		System.out.println("loi insertJob " +e.getLocalizedMessage());
	}
	return count;
}
public int deleteJobById(int id) {
    int count = 0;
    try {
        Connection connection = MysqlConfig.getConnection();
        String query = "delete from jobs j where j.id = ?";
        PreparedStatement prepareStatemen = connection.prepareStatement(query);
        prepareStatemen.setInt(1, id);
        count = prepareStatemen.executeUpdate();

        connection.close();
    } catch (Exception e) {
        System.out.println("loi delete job " + e.getMessage());
    }
    return count;
}
public int UpdateJob (String name, String startDate, String endDate, int id ) {
	int count = 0;
	String query = "update jobs set name=?, start_date=?, end_date=? where id=?";
			try {
				Connection connection = MysqlConfig.getConnection();
				PreparedStatement prepareStatemen = connection.prepareStatement(query);
				prepareStatemen.setString(1, name);
				prepareStatemen.setString(2, startDate);
				prepareStatemen.setString(3, endDate);
				prepareStatemen.setInt(4, id);
				
				count=prepareStatemen.executeUpdate();
				connection.close();
			}catch(Exception e ) {
				System.out.println("loi update jobs " + e.getLocalizedMessage());
			}
			return count;
}
public List<UserEntity> getUserByJobId(int jobId) {
    List<UserEntity> users = new ArrayList<>();
    try {
        Connection connection = MysqlConfig.getConnection();
        String query = "select distinct user_id, First_Name, Last_Name\n" +
                "from users u inner join tasks t\n" +
                "on u.id = t.user_id and t.job_id = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, jobId);
        ResultSet resultSet = preparedStatement.executeQuery();

        while(resultSet.next()) {
            UserEntity user = new UserEntity();
            user.setId(resultSet.getInt("user_id"));
            user.setFirstname(resultSet.getString("First_Name"));
            user.setLastname(resultSet.getString("Last_Name"));
            users.add(user);
        }

        connection.close();
    } catch (Exception e) {
        System.out.println("loi get users by jobId " + e.getMessage());
    }
    return users;
}


public List<TaskEntity> getTaskByJobIdAndUserId(int jobId, int userId) {
    List<TaskEntity> tasks = new ArrayList<>();
    try {
        Connection connection = MysqlConfig.getConnection();
        String query = "select t.id , t.name , t.start_date , t.end_date , t.user_id , t.job_id , t.status_id , s.name as status_name\r\n"
        		+ "from tasks t\r\n"
        		+ "inner join status s on t.status_id = s.id \r\n"
        		+ "where t.user_id =?  and t.job_id =?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, userId);
        preparedStatement.setInt(2, jobId);
        ResultSet resultSet = preparedStatement.executeQuery();

        while(resultSet.next()) {
            TaskEntity task = new TaskEntity();
            task.setId(resultSet.getInt("id"));
            task.setName(resultSet.getString("name"));
            task.setStartDate(resultSet.getString("start_date"));
            task.setEndDate(resultSet.getString("end_date"));
            
            StatusEntity statusEntity = new StatusEntity();
			statusEntity.setName(resultSet.getString("status_name"));
			task.setIdStatus(statusEntity);
            tasks.add(task);
        }
        connection.close();
    } catch (Exception e) {
        System.out.println("Loi get tasks by jobId and userId " + e.getMessage());
    }
    return tasks;
}
}
