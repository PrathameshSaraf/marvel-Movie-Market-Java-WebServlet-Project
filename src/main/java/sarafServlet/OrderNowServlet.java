package sarafServlet;
import java.sql.*;
import dbconnection.dbconnections;
import sarafdao.orderDao;
import saraftutorial.Order;
import saraftutorial.cart;
import saraftutorial.user;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@WebServlet(name = "OrderNowServlet", value = "/order-now")
public class OrderNowServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try(PrintWriter out=response.getWriter()){

            SimpleDateFormat formatter=new SimpleDateFormat("yyyy-mm-dd");
            Date date=new Date();
            user auth=(user)request.getSession().getAttribute("auth");
            if(auth !=null){

                String productId =request.getParameter("id");
                Order ordermodel=new Order();
                ordermodel.setId(Integer.parseInt(productId));
                ordermodel.setuId(auth.getId());
                ordermodel.setResolution(ordermodel.getResolution());
                ordermodel.setDate(formatter.format(date));

                orderDao od=new orderDao(dbconnections.getconnection());
                boolean result=od.insertOrder(ordermodel);
                if(result){
                    ArrayList<cart> cart_list=(ArrayList<cart>)request.getSession().getAttribute("cart-list");
                    if(cart_list !=null){
                        for(cart c:cart_list){
                            if(c.getId()==Integer.parseInt(productId)){
                                cart_list.remove(cart_list.indexOf(c));
                                break;
                            }
                        }

                    }
                    response.sendRedirect("orders.jsp");
                }else{
                    out.println("order failed");
                }

            }else{
                response.sendRedirect("login.jsp");
            }

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
