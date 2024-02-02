package helloservlet.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import helloservlet.config.MysqlConfig;
import helloservlet.entity.RoleEntity;
import helloservlet.entity.StatusEntity;
import helloservlet.entity.UserEntity;

public class StatusRepository {
public List<StatusEntity> getStatuses() {
	 List<StatusEntity> statuses = new ArrayList<StatusEntity>();
	try {
		Connection connection = MysqlConfig.getConnection(); // bên config đã có setup cái connection rồi
		String query = "select * from status"; // viết câu query ben database truoc khi dua vo nay.
		PreparedStatement prepareStatement = connection.prepareStatement(query);
		ResultSet resultSet = prepareStatement.executeQuery();
		//Đối với câu lấy giá trị : Select => excuteQuery
		while(resultSet.next()) {
			StatusEntity status = new StatusEntity();
			status.setId(resultSet.getInt("id")); // bên data base sẽ lay cot id
			status.setName(resultSet.getString("name")); // bên data base sẽ lay cot name
			statuses.add(status); 
		}
		connection.close();
	}catch(Exception e ) {
		System.out.println("Lỗi "+ e.getLocalizedMessage());
	}return statuses;
}
}
