package sarafServlet;

import dbconnection.dbconnections;
import sarafdao.orderDao;
import saraftutorial.Order;
import saraftutorial.cart;
import saraftutorial.user;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@WebServlet(name = "CheckOutServlet", value = "/cart-check-out")
public class CheckOutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            try{
                SimpleDateFormat formatter=new SimpleDateFormat("yyyy-mm-dd");
                Date date=new Date();
                ArrayList<cart> cart_list=(ArrayList<cart>)request.getSession().getAttribute("cart-list");
                user auth=(user)request.getSession().getAttribute("auth");
                if(cart_list !=null && auth !=null){
                    for(cart c:cart_list){
                        Order od=new Order();
                        od.setId(c.getId());
                        od.setuId(auth.getId());
                        od.setResolution(c.getResolution());
                        od.setDate(formatter.format(date));

                        orderDao odao=new orderDao(dbconnections.getconnection());
                       boolean result= odao.insertOrder(od);
                        if(!result)break;
                    }
                    cart_list.clear();
                    response.sendRedirect("cart.jsp");
                }else {
                    if(auth ==null){
                        response.sendRedirect("login.jsp");
                    }
                    response.sendRedirect("cart.jsp");
                }
            }catch (Exception e){
                e.printStackTrace();
            }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
