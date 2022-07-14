package sarafServlet;

import saraftutorial.cart;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(name = "incdecServlet", value = "/inc-dec-Servlet")
public class incdecServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        try(PrintWriter out=response.getWriter();){
            String action=request.getParameter("action");
            int id=Integer.parseInt(request.getParameter("id"));

            ArrayList<cart> cart_list=(ArrayList<cart>) request.getSession().getAttribute("cart-list");

            if(action != null && id>=1){
                if(action.equals("inc")){
                    for(cart c:cart_list){
                        if(c.getId()==id) {
                            int resolution = c.getResolution();

                            int[] a={0,120, 240, 360, 480, 720, 1080};

                            for(int i=0;i<7;i++){
                                if(resolution==a[i]) {
                                    c.setResolution(a[i+1]);

                                    response.sendRedirect("cart.jsp");

                                }
                                if(resolution==1080) i=0;
                            }

                        }
                    }
                }
                if(action.equals("dec")){
                    for(cart c:cart_list){
                        if(c.getId()==id) {
                            int resolution = c.getResolution();

                            int[] a={120, 240, 360, 480, 720, 1080};

                            for(int i=5;i>=0;i--){

                                if(resolution==a[i]) {
                                    c.setResolution(a[i-1]);

                                    response.sendRedirect("cart.jsp");
                                }
                                if(resolution==120) i=5;
                            }

                        }
                    }
                }
            }
        }
    }


}
