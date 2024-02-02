package helloservlet.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import helloservlet.config.MysqlConfig;
import helloservlet.entity.UserEntity;
import helloservlet.service.LoginService;
@WebServlet(name="loginController", urlPatterns = {"/login"})
public class LoginController extends HttpServlet {
private LoginService loginService = new LoginService();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	// TODO Auto-generated method stub
	req.getRequestDispatcher("login.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		String remember = req.getParameter("remember");
		
		boolean isSuccess= loginService.checkLogin(email, password, remember, resp);
		System.out.println("kiem tra login " + isSuccess);
		req.getRequestDispatcher("login.jsp").forward(req, resp);
}
}
