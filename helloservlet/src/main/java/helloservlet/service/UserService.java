package helloservlet.service;

import java.util.ArrayList;
import java.util.List;


import helloservlet.entity.UserEntity;
import helloservlet.repository.UserRepository;

public class UserService {
private UserRepository userRepository = new UserRepository();

public List<UserEntity> getAllUser(){
	return userRepository.getUser();
}
//public List<String> getFristNameList(){
//	List<String > fristNameList = new ArrayList<String>();
//	List<UserEntity> userList = this.getAllUser(); 
//	if(userList.size()>0) {
//		for (UserEntity userEntity : userList) {
//			String fullname= userEntity.getFullname();
//			String fristName = fullname;
//			int fristSpaceIndex = fullname.indexOf(" ");
//			if(fristSpaceIndex !=-1) {
//				fristName = fullname.substring(0, fristSpaceIndex);
//			}
//			fristNameList.add(fristName);
//		}
//	}
//	return fristNameList;
//}
//public List<String> getLastNameList(){
//	List<String > lastNameList = new ArrayList<String>();
//	List<UserEntity> userList = this.getAllUser(); 
//	if(userList.size()>0) {
//		for (UserEntity userEntity : userList) {
//			String fullname= userEntity.getFullname();
//			String lastName= fullname;
//			int lastSpaceIndex = fullname.indexOf(" ");
//			if(lastSpaceIndex !=-1) {
//				lastName = fullname.substring(lastSpaceIndex);
//			}
//				lastNameList.add(lastName);
//		}
//	}
//	return lastNameList;
//}
public boolean insert(String firstname, String lastname, String email, String password, int PhoneNo, int roleId) {
	int count = userRepository.insertUser(firstname, lastname, email, password, PhoneNo, roleId);
	return count>0;
}
public boolean delete (int id) {
	int count = userRepository.deleteById(id);
	return count>0;
}
public boolean update(String email, String password ,int roleId , String firstname, String lastname, int id ) {
	int count = userRepository.updateUser(email, password, roleId, firstname, lastname, id);
	return count >0;
}
public UserEntity findUserById(int id) {
    return  userRepository.findUserById(id);
}
}
