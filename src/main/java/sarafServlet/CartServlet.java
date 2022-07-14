package sarafServlet;

import saraftutorial.cart;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(name = "CartServlet", value = "/add-to-cart")
public class CartServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try(PrintWriter out=response.getWriter()){
            ArrayList<cart> cartlist=new ArrayList<>();
            int id=Integer.parseInt(request.getParameter("id"));
            cart cm=new cart();
            cm.setId(id);
            cm.setResolution(120);

            HttpSession session=request.getSession();
            session.getAttribute("cart-list");
            ArrayList<cart> cart_list=(ArrayList<cart>) session.getAttribute("cart-list");

            if(cart_list==null){
                cartlist.add(cm);
                session.setAttribute("cart-list",cartlist);
                response.sendRedirect("index.jsp");
            }
            else {
                cartlist = cart_list;
                boolean exist = false;
                for (cart c : cartlist) {
                    if (c.getId() == id) {
                        exist = true;
                        out.print("<h3 style='color:crimson; text-align:center'>Item Already Exist in cart. <a href='cart.jsp'>go to Cart page</a><h3> ");
                    }
                }
                if(!exist){
                    cartlist.add(cm);
                    response.sendRedirect("index.jsp");
                }
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
}
