package sarafServlet;

import dbconnection.dbconnections;
import sarafdao.orderDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "CancelOrderServlet", value = "/Cancel-Order")
public class CancelOrderServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            try(PrintWriter out=response.getWriter()){
                String id=request.getParameter("id");
                if(id!=null){
                    orderDao od=new orderDao(dbconnections.getconnection());
                    od.cancelOrder(Integer.parseInt(id));

                }
                response.sendRedirect("orders.jsp");
            }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
