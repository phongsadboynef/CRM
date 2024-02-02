package youtube;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// đặt tên cho con servlet qua biến name để phân biệt với những con servlet khác, và đường link thông qua url
@WebServlet(name ="hello" , urlPatterns = {"/xinchao"})
public class hello  extends HttpServlet{
@Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	PrintWriter printwriter =resp.getWriter(); // tại vì resp.getWriter có kiểu dữ liệu là PrintWriter, nên phải tạo 1 đối tượng
	// tên là printwriter 
	printwriter.write("hello phong dz");
	printwriter.close(); // tại vì khi gọi PrintWriter nó sẽ tạo ra 1 cái tag để mình dữ liệu trả về, sau khi ghi xong
	// nhớ phải close
}
}
