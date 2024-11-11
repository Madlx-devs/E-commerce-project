<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<% String name =(String)request.getAttribute("title");
 String price =(String)request.getAttribute("price");
 String url =(String)request.getAttribute("image");%>
 
 
 <div id ="prodct-box">
 <img src="<%= url %>" alt="<%=name%>">
        <h2><%=name%></h2>
        <p>A brief description of the product goes here. It should give customers enough information to make a purchase decision.</p>
        <p class="price"><%=price %></p>
        <a href="#" class="btn-buy">buy now</a>
 </div>
</body>
</html>