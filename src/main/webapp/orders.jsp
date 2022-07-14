<%@ page import="saraftutorial.user" %>
<%@ page import="sarafdao.ProductDao" %>
<%@ page import="dbconnection.dbconnections" %>
<%@ page import="saraftutorial.cart" %>
<%@ page import="java.util.*" %>
<%@ page import="sarafdao.*" %>
<%@ page import="saraftutorial.*" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    user auth = (user) request.getSession().getAttribute("auth");
    List<Order> orders=null;
    if(auth!=null){
        request.setAttribute("auth",auth);
        orders=new orderDao(dbconnections.getconnection()).userOrders(auth.getId());
    }else{
       response.sendRedirect("login.jsp");
    }
    ArrayList<cart> cart_list=(ArrayList<cart>) session.getAttribute("cart-list");
    List<cart> cartProducts=null;
    if(cart_list !=null){
        //ProductDao pDao=new ProductDao(dbconnections.getconnection());
        //cartProducts=pDao.getCartProducts(cart_list);
        request.setAttribute("cart_list",cart_list);
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>Saraf.com</title>
    <%@include file="includes/head.jsp"%>
</head>
<body>
<%@include file="includes/navbar.jsp"%>
<div class="container">
    <div class="card-header my-3" style="color: #0505b6"><h3>All Orders</h3></div>
    <table class="table table-light">
        <thead>
        <tr>
            <th scope="col">Date</th>
            <th scope="col">Movie Name</th>
            <th scope="col">Release Date</th>
            <th scope="col">Resolution</th>
            <th scope="col">Price</th>

            <th scope="col">Cancel</th>
        </tr>
        </thead>
        <tbody>
            <% if(orders !=null){
                for(Order o:orders){%>
            <tr>
            <td><%= o.getDate()%></td>
            <td><%= o.getMovieName()%></td>
            <td><%= o.getReleaseDate()%></td>
            <td><%= o.getResolution()%></td>
            <td><%= o.getPrice()%></td>
            <td><a class="btn btn-sm btn-danger" href="Cancel-Order?id=<%= o.getOrderId()%>">Cancel</a></td>
            </tr>

                <%}
            }%>
        </tbody>
    </table>

</div>

</div>


<%@include file="includes/foot.jsp"%>
</body>
</html>