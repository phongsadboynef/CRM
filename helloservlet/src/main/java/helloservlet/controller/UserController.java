package helloservlet.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import helloservlet.entity.RoleEntity;
import helloservlet.entity.UserEntity;
import helloservlet.service.RoleService;
import helloservlet.service.UserService;

@WebServlet(name = "UserPageController", urlPatterns = { "/user", "/user-add", "/user-delete","/user-update" })

public class UserController extends HttpServlet {
	  private UserService userService = new UserService();
	  private RoleService roleService = new RoleService();
	  
	  int idEdit;
	 @Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 System.out.println("sam main");
		 String path = req.getServletPath();
		 System.out.println(" kiem tra " + path);
		 if (path.equals("/user-add")) {
			doGetUserAdd(req, resp);
			} else if (path.equals("/user")) {
				doGetUserTable(req, resp);
			}else if (path.equals("/user-delete")){
				doGetUserDelete(req, resp);
			}
			else if (path.equals("/user-update")){
				doGetUserUpdate(req, resp);
				}
	}
	 @Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 String path = req.getServletPath();
			if (path.equals("/user-add")) {
				System.out.println("kiểm tra post useradd");
				doPostUserAdd(req, resp);
			}else if(path.equals("/user-update")) {
				System.out.println("kiểm tra post userupdate");
				doPostUserUpdate(req, resp);
				}
			}
	
	 private void doGetUserAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 	System.out.println("sam doget");
		 	req.setAttribute("listRole", roleService.getAllRole()); // để có được cái select chọn các role
		 	// ta phải lấy danh sách role ở roleController có
		 	// cái setAttribute: là mình muốn cái nào thì phải Set nó và file jsp để đưa ra màn hình và file jsp
			req.getRequestDispatcher("user-add.jsp").forward(req, resp);
			
		}  
	 private void doGetUserTable(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 System.out.println("Kiem tra getallUser");
		 req.setCharacterEncoding("UTF-8");
		 List<UserEntity> list = userService.getAllUser();
		 req.setAttribute("listUser", list); // set 1 cai biến listUser để bên jsp gọi list ra
		 req.getRequestDispatcher("user-table.jsp").forward(req, resp);
        
} 
	 private void doPostUserAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 	System.out.println("sam doPost");
		 	String firstname = req.getParameter("first-name");
			String lastname = req.getParameter("last-name");
			String email = req.getParameter("Email");
			String password = req.getParameter("pass-word");
			int phoneNo = Integer.parseInt(req.getParameter("phone")); //tại vì getParameter là kiểu String nên ép kiểu về Integer.
			int roleId = Integer.parseInt(req.getParameter("roleId"));
			boolean isSuccess = userService.insert(firstname, lastname, email, password, phoneNo, roleId);
			resp.sendRedirect(req.getContextPath()+"/user");
		}
	 private void doGetUserDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 	int id = Integer.parseInt(req.getParameter("id")); // taọ 1 biến id để nhập request từ người dùng, 
		 	//chuyển về kiểu int do getParameter là kiểu String.
		 	userService.delete(id);
		 	resp.sendRedirect(req.getContextPath()+ "/user");
		} 

	 private void doGetUserUpdate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 	
		 	String emailEdit = req.getParameter("email"); // Lấy name
			String passwordEdit = req.getParameter("password");// Lấy mô tả
			String firstnameEdit = req.getParameter("firstname");
			String lastnameEdit = req.getParameter("lastname");
			idEdit = Integer.parseInt(req.getParameter("id"));
			System.out.println("id :" + idEdit );
			req.setAttribute("emailUpdate", emailEdit);
			req.setAttribute("passwordUpdate", passwordEdit);
			req.setAttribute("listRole", roleService.getAllRole());
			req.setAttribute("firstnameUpdate", firstnameEdit);
			req.setAttribute("lastnameUpdate", lastnameEdit);
			
			req.getRequestDispatcher("user-update.jsp").forward(req, resp);
		} 
	 private void doPostUserUpdate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 	
		 	req.setCharacterEncoding("UTF-8");
		 	String firstname = req.getParameter("user-firstname");
		 	String lastname = req.getParameter("user-lastname");
		 	String email = req.getParameter("user-email");
		 	String password = req.getParameter("user-password");
		 	int roleId = Integer.parseInt(req.getParameter("roleId"));
		 	boolean isSucess = userService.update(email, password, roleId, firstname, lastname, idEdit);
		 	System.out.println("test " + isSucess);
		 	
		 	resp.sendRedirect(req.getContextPath()+"/user");
        
		}
} 
	

