package helloservlet.service;

import java.util.List;

import helloservlet.entity.RoleEntity;
import helloservlet.repository.RoleRepository;

public class RoleService {
	private RoleRepository roleRepository = new RoleRepository();
public boolean insertRole(String roleName, String roleDesc) {
	int count = roleRepository.insert(roleName, roleDesc);
	return count>0;
}
public List<RoleEntity>getAllRole(){
	return roleRepository.findAll();
}
public boolean deleteRole (int id) {
	return roleRepository.deleteById(id) > 0;
}
public boolean updateRole (String name, String desc, int id) {
	int count = roleRepository.updateRolebyId(name, desc, id);
	return count>0;
}
}
