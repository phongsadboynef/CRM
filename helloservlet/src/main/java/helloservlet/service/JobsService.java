package helloservlet.service;

import java.util.ArrayList;
import java.util.List;


import helloservlet.entity.JobsDetailEntity;
import helloservlet.entity.JobsEntity;
import helloservlet.entity.TaskEntity;
import helloservlet.entity.UserEntity;
import helloservlet.repository.JobsRepository;

public class JobsService {
	private JobsRepository jobsRepository = new JobsRepository();
public List<JobsEntity> getAllJobs(){
	return jobsRepository.getJobs();
}
public boolean insert (String name, String starDate, String endDate) {
	int count = jobsRepository.insertJobs(name, starDate, endDate);
	return count >0;	
}
public  boolean delete (int id) {
	int count = jobsRepository.deleteJobById(id);
	return count >0;	
}
public boolean update(String name, String startDate, String endDate, int id) {
	int count= jobsRepository.UpdateJob(name, startDate, endDate, id);
	return count>0;
}

public List<JobsDetailEntity> getDetailsByJobId(int jobId) {
    List<JobsDetailEntity> jobDetails = new ArrayList<>();
    List<UserEntity> users = jobsRepository.getUserByJobId(jobId);
    for (UserEntity user: users) {
      JobsDetailEntity jobDetail = new JobsDetailEntity();
        jobDetail.setIdUser(user.getId());
        jobDetail.setUserName(user.getFirstname() + " " + user.getLastname());
        List<TaskEntity> tasks = jobsRepository.getTaskByJobIdAndUserId(jobId, user.getId());
        jobDetail.setTaskEntities(tasks);

        jobDetails.add(jobDetail);
    }
    return jobDetails;
}



}
