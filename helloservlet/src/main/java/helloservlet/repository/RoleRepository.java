package helloservlet.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.catalina.ant.ReloadTask;

import helloservlet.config.MysqlConfig;
import helloservlet.entity.RoleEntity;
import helloservlet.entity.UserEntity;

public class RoleRepository {
	
	
	public int deleteById(int id) {
		int count =0;
		String query = "DELETE FROM roles r WHERE r.id= ? ";
		try {
			Connection connection = MysqlConfig.getConnection();
			PreparedStatement prepareStatement = connection.prepareStatement(query);
			prepareStatement.setInt(1,id);
			count = prepareStatement.executeUpdate();
		}catch (Exception e) {
			System.out.println("Loi " + e.getLocalizedMessage());
		}
		return count;
	}
	
	public List<RoleEntity> findAll(){
		List<RoleEntity> listRole = new ArrayList<RoleEntity>();
		String query = "SELECT * FROM roles";
		try {
			Connection connection = MysqlConfig.getConnection();
			PreparedStatement prepareStatement = connection.prepareStatement(query);
			ResultSet resultSet = prepareStatement.executeQuery();
			while(resultSet.next()) {
				RoleEntity roleEntity= new RoleEntity();
				roleEntity.setId(resultSet.getInt("id"));
				roleEntity.setName(resultSet.getString("name"));
				roleEntity.setDesc(resultSet.getString("description"));
				
				listRole.add(roleEntity);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return listRole;
		
	}
	
	public int insert (String roleName, String desc) {
		int count =0;
		try {
			String query ="INSERT INTO roles( name, description ) VALUES (?,?);";
			Connection connection =MysqlConfig.getConnection();
			PreparedStatement prepareStatement = connection.prepareStatement(query);
			prepareStatement.setString(1, roleName); 
			prepareStatement.setString(2,desc); 
			count = prepareStatement.executeUpdate();
		} catch (Exception e) {
		System.out.println(" Loi insert " + e.getLocalizedMessage());
		}
		return count;
	}
	
	public int updateRolebyId(String name, String desc, int id) {
		int count = 0;
		String query = "UPDATE roles SET name = ?, description =? WHERE id = ?;";
		Connection connection = MysqlConfig.getConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1,name );
			preparedStatement.setString(2,desc );
			preparedStatement.setInt(3,id);

			count = preparedStatement.executeUpdate();
			

		} catch (Exception e) {

			System.out.println("Lỗi edit" + e.getLocalizedMessage());
		}

		return count;
	}
}
