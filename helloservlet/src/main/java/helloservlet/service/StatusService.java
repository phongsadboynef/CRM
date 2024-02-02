package helloservlet.service;

import java.util.List;

import helloservlet.entity.StatusEntity;
import helloservlet.repository.StatusRepository;

public class StatusService {
private StatusRepository statusRepository = new StatusRepository();
public List<StatusEntity> getAllStatus (){
	return statusRepository.getStatuses();
}
}
