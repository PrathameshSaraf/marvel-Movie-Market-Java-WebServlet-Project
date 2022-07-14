<%@ page import="saraftutorial.user" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="saraftutorial.cart" %>
<%@ page import="java.util.*" %>
<%@ page import="saraftutorial.Product" %>
<%@ page import="sarafdao.ProductDao" %>
<%@ page import="dbconnection.dbconnections" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    user auth = (user) request.getSession().getAttribute("auth");
    if(auth!=null){
        request.setAttribute("auth",auth);
    }
    ArrayList<cart> cart_list=(ArrayList<cart>) session.getAttribute("cart-list");
    List<cart> cartProducts=null;
    if(cart_list !=null){
        ProductDao pDao=new ProductDao(dbconnections.getconnection());
        cartProducts=pDao.getCartProducts(cart_list);
        double total= pDao.getTotalCartPrice(cart_list);
        request.setAttribute("cart_list",cart_list);
        request.setAttribute("total",total);
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>Saraf.com</title>
    <%@include file="includes/head.jsp"%>

    <style type="text/css">
        .table tbody{
            vertical-align: middle;
        }
        .btn-incre,.btn-decre{
            box-shadow: none;
            font-size: 25px;
        }
    </style>
</head>
<body>
<%@include file="includes/navbar.jsp"%>
<div class="container">
    <div class="d-flex py-3"><h3>Total Price :${(total>0)?total:0} RS</h3><a class="mx-3 btn btn-primary" href="cart-check-out">Check Outs</a></div>
    <table class="table table-light">
        <thead>
        <tr>
            <th scope="col">Name</th>
            <th scope="col">Release Date</th>
            <th scope="col">Price</th>
            <th scope="col">Buy Now</th>
            <th scope="col">Cancel</th>
        </tr>
        </thead>
        <tbody>
        <% if(cart_list !=null){
            for(cart c:cartProducts){%>
        <tr>
            <td><%= c.getMovieName()%></td>
            <td><%=c.getReleaseDate()%></td>
            <td><%=c.getPrice()%></td>
            <td>
                <form action="cart-check-out" method="get" class="form-inline">
                    <input type="hidden" name="id" value="<%=c.getId()%>" class="form-input">
                    <div class="form-group d-flex justify-content-between w-50">
                        <a class="btn btn-sm btn-incre" href="inc-dec-Servlet?action=dec&id=<%=c.getId()%>"><i class="fas fa-minus-square"></i></a>
                        <input type="text" name="Resolution" class="form-control w-50" value="<%=c.getResolution()%> P" readonly>
                        <a class="btn btn-sm btn-decre" href="inc-dec-Servlet?action=inc&id=<%=c.getId()%>"><i class="fas fa-plus-square"></i></a>
                    </div>
                    <button type="submit" class="btn btn-primary btn-sm" >Buy</button>
                </form>
            </td>
            <td> <a class="btn btn-sm btn-danger" href="remove-from-servlet?id=<%=c.getId()%>">Remove</a> </td>
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