package helloservlet.controller;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import helloservlet.entity.JobsEntity;
import helloservlet.repository.JobsRepository;
import helloservlet.service.JobsService;
@WebServlet(name = "JobController", urlPatterns = { "/groupwork", "/groupwork-add", "/groupwork-delete","/groupwork-update", "/groupwork-details" })
public class JobsController extends HttpServlet {
	private JobsRepository jobsRepository = new JobsRepository();
	private JobsService jobsService= new JobsService();
int idEdit;
@Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	System.out.println("phong MAIN JOB");
	String path = req.getServletPath();
	if (path.equals("/groupwork")) {
		doGetJobTable(req, resp);
	} else if (path.equals("/groupwork-add")) {
		System.out.println("da vao doget JOB add");
		doGetJobAdd(req, resp);
	} else if (path.equals("/groupwork-delete")) {
		System.out.println("da vao doget JOB DEELE");
		doGetJobDelete(req, resp);
	}else if(path.equals("/groupwork-update")) {
		System.out.println("da vao doget JOB UPDATE");
		doGetJobUpdate(req, resp);			
	}else if (path.equals("/groupwork-details")) {
		doGetDetailsByJobId(req, resp);

	}
}

@Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	String path = req.getServletPath();
	if (path.equals("/groupwork-add")) {
		System.out.println("kiểm tra post JOBadd");
		doPostJobAdd(req, resp);
	} else if (path.equals("/groupwork-update")) {
		System.out.println("kiểm tra post JOB UPDATE");
		doPostJobUpdate(req, resp);
	}
}

private void doGetJobTable(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	List<JobsEntity> list =jobsService.getAllJobs();
	req.setAttribute("listJob", list);
	req.getRequestDispatcher("groupwork.jsp").forward(req, resp);

}

private void doGetJobAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	System.out.println("sam DO GET JOB");
	req.getRequestDispatcher("groupwork-add.jsp").forward(req, resp);
}
private void doGetJobDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	int id = Integer.parseInt(req.getParameter("id"));
	jobsService.delete(id);
	resp.sendRedirect(req.getContextPath()+ "/groupwork");
}

private void doPostJobAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	System.out.println("sam DO POST JOB");
	String name = req.getParameter("job-name");
	String starDate = req.getParameter("job-starDate");
	String endDate = req.getParameter("job-endDate");

	name = setUTF8(name);
	boolean isSuccess =jobsService.insert(name, starDate, endDate);
	
	resp.sendRedirect(req.getContextPath()+"/groupwork");
	
}
private String setUTF8(String string) {
	byte[] bytes = string.getBytes(StandardCharsets.ISO_8859_1);
	string = new String(bytes, StandardCharsets.UTF_8);

	return string;
}
private void doGetJobUpdate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	System.out.println("phong UPDATE GET JOB");
	idEdit = Integer.parseInt(req.getParameter("id"));
	System.out.println("id: " + idEdit);
	req.getRequestDispatcher("groupwork-update.jsp").forward(req, resp);
}
private void doPostJobUpdate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	System.out.println("sam UPDATE POST JOB");
	String name = req.getParameter("job-name");
	String starDate = req.getParameter("job-starDate");
	String endDate = req.getParameter("job-endDate");
	name = setUTF8(name);
	
	boolean isSuccess = jobsService.update(name, starDate, endDate, idEdit);
	System.out.println("Test" + isSuccess);
	
	resp.sendRedirect(req.getContextPath()+"/groupwork");
}
private void doGetDetailsByJobId(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
	 System.out.println("phong da vao doget Jobdetail");
	 int id = Integer.parseInt(req.getParameter("id"));
     req.setAttribute("jobDetails", jobsService.getDetailsByJobId(id));
     System.out.println("phong da vao doget Jobdetail");
     req.getRequestDispatcher("/groupwork-details.jsp").forward(req, resp);
     System.out.println("phong da vao xong doget Jobdetail !");
}
}