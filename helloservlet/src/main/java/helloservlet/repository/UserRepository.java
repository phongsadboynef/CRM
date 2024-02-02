package helloservlet.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;




import helloservlet.config.MysqlConfig;
import helloservlet.entity.RoleEntity;
import helloservlet.entity.UserEntity;

public class UserRepository {
	public List<UserEntity> getUser(){
		List<UserEntity> listUsers = new ArrayList<UserEntity>();
		String query = "SELECT u.id ,u.First_Name, u.Last_Name ,u.email ,r.name \r\n"
				+ "FROM users u\r\n"
				+ "join roles r ON u.role_id = r.id";
		// bên database sẽ test trước
		
		try {
			Connection connection = MysqlConfig.getConnection();
			PreparedStatement prepareStatement = connection.prepareStatement(query);
			ResultSet resultSet = prepareStatement.executeQuery();
			//Đối với câu lấy giá trị : Select => excuteQuery
			while(resultSet.next()) {
				UserEntity User= new UserEntity();
				User.setId(resultSet.getInt("id"));
				User.setFirstname(resultSet.getString("First_Name"));
				User.setLastname(resultSet.getString("Last_Name"));
				User.setEmail(resultSet.getString("email"));
				
				// tạo ra 1 cái role để add cái của nó với kiểu RoleEntity nhé
				RoleEntity role = new RoleEntity();
				role.setName(resultSet.getString("name"));

				User.setIdRole(role);
				listUsers.add(User);
			}
		} catch (Exception e) {
			System.out.println("Loi getUser" + e.getLocalizedMessage());
		}
		return listUsers;
	}
	
	public int insertUser (String firstname, String lastname, String email,String password, int PhoneNo, int roleId ) {
		String query = "INSERT INTO users ( First_Name ,Last_Name , email, password ,Phone_No,role_id  )\r\n"
				+ "VALUES (?,?,?,?,?,?);";
		int count = 0;
		Connection connection = MysqlConfig.getConnection();
		try {
			PreparedStatement prepareStatement = connection.prepareStatement(query);
			prepareStatement.setString(1, firstname);
			prepareStatement.setString(2, lastname);
			prepareStatement.setString(3, email);
			prepareStatement.setString(4, password);
			prepareStatement.setInt(5, PhoneNo);
			prepareStatement.setInt(6, roleId);
			count = prepareStatement.executeUpdate();
			
		}catch(Exception e) {
			System.out.println("Loi insertUser " + e.getLocalizedMessage());
		}
		return count;
	}
	public int deleteById(int id) {
		int count = 0;
		String query = "DELETE FROM users u WHERE u.id= ?";
		Connection connection = MysqlConfig.getConnection();
		try {
			PreparedStatement prepareStatement = connection.prepareStatement(query);
			prepareStatement.setInt(1, id);
			count= prepareStatement.executeUpdate();
			
			connection.close();
		}catch(Exception e) {
			System.out.println("Loi deleteUserc "+ e.getLocalizedMessage());
		}
		return count;
	}
	
	public int updateUser(String email, String password, int roleId , String firstname, String lastname, int id ) {
		String query = "Update users \r\n"
        		+ "SET email =?, password =?, role_id =?, First_Name =?, Last_Name =?\r\n"
        		+ "WHERE id = ?";
		int count = 0;
		Connection connection = MysqlConfig.getConnection();
		try {
			PreparedStatement preparedStatement= connection.prepareStatement(query); // thực hiện câu query
			preparedStatement.setString(1, email);
			preparedStatement.setString(2, password);
			preparedStatement.setInt(3, roleId);
			preparedStatement.setString(4, firstname);
			preparedStatement.setString(5, lastname);
			preparedStatement.setInt(6, id);
			
			count= preparedStatement.executeUpdate();
			
			connection.close();
		}catch(Exception e ) {
			System.out.println("loi updateUser " + e.getLocalizedMessage());
		}
		return count;

	}
	public UserEntity findUserById(int id) {
        UserEntity user = new UserEntity();
        try{
            String query = "select * from users where id = ?";
            Connection connection = MysqlConfig.getConnection();
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1,id);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                user.setId(rs.getInt("id"));
                user.setFirstname(rs.getString("firstname"));
                user.setLastname(rs.getString("lastname"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                RoleEntity role = new RoleEntity();
				role.setName(rs.getString("roleId"));

				user.setIdRole(role);
            }

            connection.close();

        }catch (Exception e){
            System.out.println("Lỗi findUserById " + e.getMessage());
        }

        return user;
    }

}


