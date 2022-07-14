package sarafServlet;

import dbconnection.dbconnections;
import sarafdao.userDao;
import saraftutorial.user;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import javax.swing.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "LoginServlet", value = "/user-login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("login.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.setContentType("text/html;charset=UTF-8");
    try(PrintWriter out=response.getWriter()){
       String email=request.getParameter("Login-email");
        String password=request.getParameter("Login-password");


            userDao udao=new userDao(dbconnections.getconnection());
             user us=udao.userLogin(email,password);

             if(us !=null){
                 out.print("login Successfully");
                 request.getSession().setAttribute("auth",us);
                 response.sendRedirect("index.jsp");
             }else{
                 out.print("Login Failed");
             }
    }
    }
}
