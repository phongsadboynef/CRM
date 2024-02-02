package helloservlet.service;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import helloservlet.entity.LoginEntity;
import helloservlet.entity.UserEntity;
import helloservlet.repository.LoginRepository;

public class LoginService{
private LoginRepository loginRepository = new LoginRepository();
public boolean checkLogin(String email, String password, String remember, HttpServletResponse resp) {
	List<LoginEntity> list = loginRepository.findbyEmailandPassword(email, password);
	boolean isSuccess = list.size()>0;
	// Nếu khác null tức là người dùng có check vào lưu tài khoản
	if (isSuccess && remember != null) {
		Cookie ckEmail= new Cookie("email", email);
		Cookie ckPassword = new Cookie ("password", password) ;
		resp.addCookie(ckEmail);
		resp.addCookie(ckPassword);
	}

	return isSuccess;

}
}
