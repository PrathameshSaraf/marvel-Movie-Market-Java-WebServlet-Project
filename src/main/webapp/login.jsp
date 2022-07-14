<%@ page import="saraftutorial.user" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%
    user auth = (user) request.getSession().getAttribute("auth");
    if(auth!=null){
        request.setAttribute("auth",auth);
        response.sendRedirect("index.jsp");
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>saraf.com</title>
    <%@include file="includes/head.jsp"%>
</head>
<body>
<%@include file="includes/navbar.jsp"%>
<div class="container">
    <div class="card w-50 mx-auto my-5">
        <div class="card-header text-center">User login </div>
    <div class="card-body">
        <form action="user-login" method="post">
            <div class="form-group">
                <label>Email Address</label>
                <input type="email" class="form-control" name="Login-email" placeholder="Enter your mail" required>
            </div>
            <div class="form-group">
                <label>Password</label>
                <input type="password" class="form-control" name="Login-password" placeholder="**********" required>
            </div>
            <div class="text-center">
                <button type="submit" class="btn btn-primary">login</button>
            </div>

        </form>
    </div>

</div>


<%@include file="includes/foot.jsp"%>
</body>
</html>