package sarafServlet;

import saraftutorial.cart;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(name = "RemoveFromServlet", value = "/remove-from-servlet")
public class RemoveFromServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try(PrintWriter out=response.getWriter()){
            String id=request.getParameter("id");
            if(id!=null){
                ArrayList<cart> cart_list=(ArrayList<cart>)request.getSession().getAttribute("cart-list");
                if(cart_list !=null){
                    for(cart c:cart_list){
                        if(c.getId()==Integer.parseInt(id)){
                            cart_list.remove(cart_list.indexOf(c));
                            break;
                        }
                    }
                    response.sendRedirect("cart.jsp");
                }
            }else{
                response.sendRedirect("cart.jsp");
            }
        }
    }


}
