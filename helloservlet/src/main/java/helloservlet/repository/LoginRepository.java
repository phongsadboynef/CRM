package helloservlet.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import helloservlet.config.MysqlConfig;
import helloservlet.entity.LoginEntity;
import helloservlet.entity.UserEntity;

/**
 * Chứa tất cả câu query liên quan tới bảng User
 * 
 * select: đại diện cho chữ find where: đại diện bởi chữ by
 * 
 */

public class LoginRepository {
	 public List<LoginEntity> findbyEmailandPassword(String email, String password) {
		 
		 List<LoginEntity> listUser = new ArrayList<LoginEntity>();
	
		String query = "SELECT *\r\n" + "FROM users  u\r\n" + "WHERE u.email = ? AND u.password = ?";
		

		Connection connection = MysqlConfig.getConnection();
		try {
			PreparedStatement prepareStatement = connection.prepareStatement(query);
			
			prepareStatement.setNString(1, email); 
			prepareStatement.setNString(2, password); 
			ResultSet resultSet = prepareStatement.executeQuery();

			while (resultSet.next()) {
				LoginEntity entity = new LoginEntity();
				entity.setId(resultSet.getInt("id")); 
				entity.setFullname(resultSet.getString("fullname")); // chỗ này bên database đã được sửa lại
				listUser.add(entity);
			}

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Lỗi findbyEmailAndPassword" + e.getMessage());
		}
		return listUser;
	}
}
