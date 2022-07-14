<%@ page import="dbconnection.dbconnections" %>
<%@ page import="saraftutorial.user" %>
<%@ page import="saraftutorial.Product" %>
<%@ page import="sarafdao.ProductDao" %>
<%@ page import="java.util.List" %>
<%@ page import="saraftutorial.cart" %>
<%@ page import="java.util.*" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%
    user auth = (user) request.getSession().getAttribute("auth");
    if (auth != null) {
        request.setAttribute("auth", auth);

    }
    ProductDao pd = new ProductDao(dbconnections.getconnection());
    List<Product> products = pd.getAllProducts();

    ArrayList<cart> cart_list=(ArrayList<cart>) session.getAttribute("cart-list");
    List<cart> cartProducts=null;
    if(cart_list !=null){
        ProductDao pDao=new ProductDao(dbconnections.getconnection());
        cartProducts=pDao.getCartProducts(cart_list);
        request.setAttribute("cart_list",cart_list);
    }
%>

<!DOCTYPE html>
<html>
<head>
    <title>Saraf.com</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet">
    <%@include file="includes/head.jsp" %>
</head>
<body>
<%@include file="includes/navbar.jsp" %>

<div class="container">
    <div class="card-header  my-3"style="color: #01019a"><h3>All Movies</h3>
    </div>
    <div class="row">
        <%if(!products.isEmpty()){
                for(Product p:products){%>
        <div class="col-md-3 my-3">
            <div class="card w-100" style="width:10rem;">
                <img class="card-img-top" src="images/<%= p.getImage()%>" alt="Card image cap" height="300"  >
                <div class="card-body">
                    <h6 class="card-title"><b>Movie: <%=p.getMovieName()%></b></h6>
                    <h7 class="card-title">Release:<%=p.getReleaseDate()%></h7><br>
                    <h7 class="card-title">Price: <%= p.getPrice()%></h7>
                    <div class="mt-3 d-flex justify-content-between">
                        <a href="add-to-cart?id=<%= p.getId()%>" class="btn btn-dark">Add to Card</a>
                        <a href="order-now?resolution=720&id=<%=p.getId()%>" class="btn btn-primary">Purchase</a>
                    </div>

                </div>
            </div>
        </div>
               <% }
            }
        %>




    </div>

</div>

<%@include file="includes/foot.jsp" %>
</body>
</html>