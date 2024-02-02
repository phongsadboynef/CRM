package helloservlet.api;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import helloservlet.service.RoleService;
@WebServlet(name = "apiRoleController", urlPatterns = {"/api/role"})
public class ApiRoleController extends HttpServlet{

	private RoleService roleService = new RoleService();
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		//boolean isSucess= roleService.de
		System.out.println("kiem tra delete "+ id);
	}
}
