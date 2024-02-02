package helloservlet.filter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import helloservlet.config.MysqlConfig;
import helloservlet.entity.UserEntity;

@WebFilter (filterName = "authenFilter", urlPatterns = {})
public class AuthenticationFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		System.out.println("Da kich hoat filter");
		
	
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		
		
		String email= "";
		String password = "";
		String contextPath = req.getContextPath();
		//Lấy danh sách cookie người dùng gửi qua thông qua request
		try {
			Cookie[] cookies = req.getCookies();
			
			 for (Cookie cookie : cookies) {
				if(cookie.getName().equals("email")) {
				email = cookie.getValue();
				}
				if(cookie.getName().equals("password")) {
					password = cookie.getValue();
					
					System.out.println(email + password);
			}
		} 
			
		}
			 catch (Exception e) {
			// TODO: handle exception
				
			System.out.println("loi" + e.getMessage());
		}
		if(email.trim().length()>0 && password.trim().length()>0) {
			// Đã đăng nhập rồi
			// lấy email và password để truy vấn database để chứng thực tài khoản tồn tại
			// trong database hay không, nếu tồn tại thì cho đi vào trang
			
			
			// cho phép người đi vào link mà người dùng đang gọi mà kích hoạt filter
			chain.doFilter(req, resp);
		}else {
			//chưa đăng nhập thì mình đá nó về trang login
			resp.sendRedirect(contextPath+ "/login");
		}
		
	}
	}

