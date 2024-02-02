<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
 <%-- 
 <%!%> :khai báo biến
 <% %> : thẻ xử lý logic code
 <%= %> : Xuất giá trị của biến
 --%>

<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<%! int count = 0; %>

<%count ++; %>

<body>
	Hello Servlet Phong 
	<% if(count%2 ==0) { %>
	<h1 style="color: red; "> <%= count %></h1>
	<%}else { %>
	<h1 style="color: blue"><%=count %></h1> <%} %>
</body>
</html>