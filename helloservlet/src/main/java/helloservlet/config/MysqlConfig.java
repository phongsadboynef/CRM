package helloservlet.config;

import java.net.ConnectException;
import java.sql.Connection;
import java.sql.DriverManager;

/*
 * @author binhcc
 * Class dùng để khai báo thông tin cấu hình tạo kết nối tới CSDL
 * */
public class MysqlConfig {
	public static Connection getConnection() {
		
		// khai báo Driver sử dụng cho JDBC (từ khóa tên driver class.forname)
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			// khai báo thông tin cơ sở dữ liệu mà JDBC sẽ kết nối tới
			 return DriverManager.getConnection("jdbc:mysql://localhost:3307/crm_app", "root", "admin123");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Lỗi kết nối CSDL " + e.getLocalizedMessage());
		}
		return null;
		
	}
}
