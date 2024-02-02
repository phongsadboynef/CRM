package helloservlet.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import helloservlet.entity.RoleEntity;
import helloservlet.service.RoleService;

@WebServlet(name = "RoleController", urlPatterns = { "/role","/role-add","/role-delete","/role-update" })
public class RoleController extends HttpServlet {
	private RoleService roleService = new RoleService();
	int idEdit;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		String path = req.getServletPath();
		System.out.println("kiem tra " + path);
		if (path.equals("/role-add")) {
			doGetRoleAdd(req, resp);
		} else if (path.equals("/role")) {
			doGetRoleTable(req, resp);
		}else if (path.equals("/role-delete")){
			doGetRoleDelete(req, resp);
		}else if (path.equals("/role-update")) {
			doGetRoleUpdate(req, resp);
		}
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		System.out.println("Kiem tra post " + path);
		if (path.equals("/role-add")) {
			doPostRoleAdd(req, resp);
		} else if (path.equals("/role-update")) {
			doPostRoleUpdate(req, resp);
		}
	}
	private void doGetRoleAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("role-add.jsp").forward(req, resp);
	}

	private void doGetRoleTable(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<RoleEntity> list = roleService.getAllRole();
		req.setAttribute("listRole", list);
		req.getRequestDispatcher("role-table.jsp").forward(req, resp);
	}

	private void doPostRoleAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String roleName = req.getParameter("role-name");
		String roleDesc = req.getParameter("role-desc");

		boolean isSuccess = roleService.insertRole(roleName, roleDesc); 
		// cái đối tượng roleService này sẽ thông qua hàm insertRole ở bên class Servicee để lấy 2 cái giá trị người dùng nhập vào 
		// để qua bên class Servie nó sẽ thông qua cũng tên là insert để nó chèn 2 dữ liệu vô class Repository 
		// class Repository thao tác trực tiếp với Database để set dữ liệu vào bảng
																	
//		req.getRequestDispatcher("role-add.jsp").forward(req, resp);
		// Hiển thị ra giao diện ở file jsp
		resp.sendRedirect(req.getContextPath()+ "/role");
	}

	
	private void doGetRoleDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt( req.getParameter("id"));
		roleService.deleteRole(id);
		resp.sendRedirect(req.getContextPath()+ "/role");
	}
	private void doGetRoleUpdate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String nameEdit = req.getParameter("name"); // Lấy name
	    String descEdit = req.getParameter("desc");// Lấy mô tả
	    idEdit = Integer.parseInt(req.getParameter("id"));
	    System.out.println("id" + idEdit);
		req.setAttribute("nameUpdate", nameEdit);
		req.setAttribute("descUpdate", descEdit);
		req.getRequestDispatcher("role-update.jsp").forward(req, resp);
	    }
	private void doPostRoleUpdate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
	    req.setCharacterEncoding("UTF-8");
		String newName = req.getParameter("newNameUpdate");
		String newDesc = req.getParameter("newDescUpdate");
	
		boolean testEdit = roleService.updateRole(newName, newDesc, idEdit);
		System.out.println("ket qua edit " + testEdit);
		resp.sendRedirect(req.getContextPath()+"/role");
	}
	
}
