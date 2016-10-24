<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 
 <script>
	$(function(){
		 $("#table").DataTable();
	})	
</script>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1252" />
<link rel="stylesheet" type="text/css" href="static/style.css" />
<script type="text/javascript" src="static/js/boxOver.js"></script>
</head>

<div id="main_container">
	<div id="main_content">
		<div id="menu_tab">

    	  <div class="left_menu_corner"></div>

	      <ul class="menu">

    	    <li><a href="" class="nav1"> Home</a></li>

	        <li class="divider"></li>

        	<li><a href="" class="nav2">Products</a></li>

    	    <li class="divider"></li>

	        <li><a href="" class="nav4">My account</a></li>

        	<li class="divider"></li>
        
    	    <div class="user_login">
				<c:choose>
					<c:when test="${LoggedIn.loggedIn}">
						Welcome ${LoggedIn.loginId} (<a href="logout">Sign Out</a>)
					</c:when>
					<c:otherwise>
						<a href="login">Hello, Sign In</a>
					</c:otherwise>
				</c:choose>
			</div>
	        </ul>
      		<div class="right_menu_corner"></div>
		</div>
	</div>
</div>

    
<!--
<div class="header-container">
 
    <div class="site-name">E-Bazaar</div>
 
    <div class="header-bar">
    	<div style="position:top; margin-top: 4px; margin-bottom: 4px;">
		<ul class="nav navbar-top-links">
		    <a href="">Home</a> 
		    
			<li class="dropdown">
				<a class="dropdown-toggle" data-toggle="dropdown" href="#"><i class="fa fa-wrench fa-fw"></i>Products <i class="fa fa-caret-down"></i>
				</a>
				<ul class="dropdown-menu">
	                <c:forEach items="${catalogs}" var="ct">
	                 <li>
	                     <a href="catalog/${ct.id}"><i class="fa fa-fw"></i> ${ct.name}</a>
	                 </li>
	                 </c:forEach>
				</ul>
			</li>

			<li class="dropdown">
				<a class="dropdown-toggle" data-toggle="dropdown" href="#"><i class="fa fa-user fa-fw"></i>Customer <i class="fa fa-caret-down"></i>
				</a>
				<ul class="dropdown-menu">
					<li><a href="shoppingcart">Shopping Cart</a></li>
					<c:choose>
						<c:when test="${LoggedIn.loggedIn}">
							<li><a href="myshoppingcart">Retrieve Saved Cart</a></li>
							<li><a href="myorder">Review Orders</a></li>
						</c:when>
					</c:choose>
				</ul>
			</li>

            <c:if test="${LoggedIn.admin}">
			<li class="dropdown">
				<a class="dropdown-toggle" data-toggle="dropdown" href="#"><i class="fa fa-gear fa-fw"></i>Administrator <i class="fa fa-caret-down"></i>
				</a>
				<ul class="dropdown-menu">
	                <li><a href="admin/product">New Product</a></li>
	                <li><a href="admin/products">View Products</a></li>
	                <li><a href="admin/catalog">Maintain Catalogs</a></li>
				</ul>
			</li>
            </c:if>			
		</ul>
	</div>
        <div class="userLogin">
			<c:choose>
				<c:when test="${LoggedIn.loggedIn}">
					Welcome ${LoggedIn.loginId} (<a href="logout">Sign Out</a>)
				</c:when>
				<c:otherwise>
					<a href="login">Hello, Sign In</a>
				</c:otherwise>
			</c:choose>
		</div>
    </div>
</div>
-->