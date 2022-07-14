
<nav class="navbar navbar-expand-lg bg-light">
  <div class="container-fluid" style="background-color: #c5fcc7">
      <a class="navbar-brand" ><h2>Marvel Movie Market</h2></a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav ml-auto mb-2 mb-lg-0">
        <li class="nav-item"><a class="nav-link active" aria-current="page" href="index.jsp">Home</a></li>
        <li class="nav-item"><a class="nav-link" href="cart.jsp">cart<span class="badge badge-danger">${cart_list.size()}</span></a></li>

       <% if(auth !=null){ %>

           <li class="nav-item"><a class="nav-link" href="orders.jsp">order</a></li>
          <li class="nav-item"><a class="nav-link" href="log-out">log Out </a></li>

       <% }else{%>
           <li class="nav-item"><a class="nav-link" href="login.jsp">log in</a></li>
       <% }
        %>

      </ul>

    </div>
  </div>
</nav>