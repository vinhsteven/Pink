
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script>
	$(function(){
		 $("#table").DataTable();
	})	
</script>

<head>
<title>Electronix Store</title>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1252" />
<link rel="stylesheet" type="text/css" href="static/style.css" />
<script type="text/javascript" src="static/js/boxOver.js"></script>
</head>

<body>

<div id="main_container">

  <div id="header">

    <div id="logo"> <a href=""><img src="static/images/logo.png" alt="" border="0" width="237" height="140" /></a> </div>

    <div class="oferte_content">

      <div class="top_divider"><img src="static/images/header_divider.png" alt="" width="1" height="164" /></div>

      <div class="oferta">

        <div class="oferta_content"> <img src="static/images/laptop.png" width="94" height="92" alt="" border="0" class="oferta_img" />

          <div class="oferta_details">

            <div class="oferta_title">Samsung GX 2004 LM</div>

            <div class="oferta_text"> Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco </div>

            <a href="details.html" class="details">details</a> </div>

        </div>

        <div class="oferta_pagination"> <span class="current">1</span> <a href="http://all-free-download.com/free-website-templates/">2</a> <a href="http://all-free-download.com/free-website-templates/">3</a> <a href="http://all-free-download.com/free-website-templates/">4</a> <a href="http://all-free-download.com/free-website-templates/">5</a> </div>

      </div>

      <div class="top_divider"><img src="static/images/header_divider.png" alt="" width="1" height="164" /></div>

    </div>

    <!-- end of oferte_content-->

  </div>

  <div id="main_content">

    <div class="left_content">

      <div class="title_box">Categories</div>

      <ul class="left_menu">
		<c:forEach items="${catalogs}" var="ct" varStatus="myIndex">
			<c:choose>
		    	<c:when test="${myIndex.getCount()%2==0}">
	    			<li class="even"><a href="catalog/${ct.id}"><i class="fa fa-fw"></i> ${ct.name}</a></li>
	    		</c:when>
	    		<c:otherwise>
           			<li class="odd"><a href="catalog/${ct.id}"><i class="fa fa-fw"></i> ${ct.name}</a></li>
         		</c:otherwise>
        	</c:choose>
	    </c:forEach>

      </ul>

      <div class="title_box">Special Products</div>
		
      <div class="border_box">
		<c:forEach items="${products[0]}" var="product" end="0">
        	<div class="product_title"><a href="details.html">${product.name}</a></div>

    	    <div class="product_img"><a href="details.html"><img width="auto" height="94px" src="static/images/${product.catalogid}${product.id}.jpg" alt="" border="0" /></a></div>

	        <div class="prod_price"><span class="reduce">$${product.unitPrice+product.unitPrice*0.2}</span> <span class="price">$${product.unitPrice}</span></div>
		</c:forEach>
      </div>

      <div class="banner_adds"> <a href=""><img src="static/images/bann2.jpg" alt="" border="0" /></a> </div>

    </div>

    <!-- end of left content -->

    <div class="center_content">

      <div class="center_title_bar">Latest Products</div>

	<c:forEach items="${products[0]}" var="product">
    	<div class="prod_box">

        	<div class="top_prod_box"></div>

	        <div class="center_prod_box">
             
	            <div class="product_img">
	            	<a href="product/${product.id}"><img width="auto" height="94px" src="static/images/${product.catalogid}${product.id}.jpg" alt="" border="0"/></a>
	            </div>
	            <div class=product_title><a href="product/${product.id}">${product.name}</a></div>
	            <div class="prod_price"><span class="price">$${product.unitPrice}</span></div>
			
        	</div>

	        <div class="bottom_prod_box"></div>

        <div class="prod_details_tab">
         <a href="" title="header=[Specials] body=[&nbsp;] fade=[on]"><img src="static/images/favs.gif" alt="" border="0" class="left_bt" /></a> 
         <a href="" title="header=[Gifts] body=[&nbsp;] fade=[on]"><img src="static/images/favorites.gif" alt="" border="0" class="left_bt" /></a> 
         <a href="product/${product.id}" class="prod_details">details</a> </div>
      </div>
    </c:forEach>  

      <div class="center_title_bar">Recommended Products</div>

      <c:forEach items="${products[1]}" var="product">
    	<div class="prod_box">

        	<div class="top_prod_box"></div>

	        <div class="center_prod_box">
             
	            <div class="product_img">
	            	<a href="product/${product.id}"><img width="auto" height="94px" src="static/images/${product.catalogid}${product.id}.jpg" alt="" border="0"/></a>
	            </div>
	            <div class=product_title><a href="product/${product.id}">${product.name}</a></div>
	            <div class="prod_price"><span class="price">$${product.unitPrice}</span></div>
			
        	</div>

	        <div class="bottom_prod_box"></div>

        <div class="prod_details_tab">
         <a href="" title="header=[Specials] body=[&nbsp;] fade=[on]"><img src="static/images/favs.gif" alt="" border="0" class="left_bt" /></a> 
         <a href="" title="header=[Gifts] body=[&nbsp;] fade=[on]"><img src="static/images/favorites.gif" alt="" border="0" class="left_bt" /></a> 
         <a href="product/${product.id}" class="prod_details">details</a> </div>

      </div>
    </c:forEach>

    </div>

    <!-- end of center content -->

    <div class="right_content">

      <div class="shopping_cart">

        <div class="cart_title">Shopping cart</div>

        <div class="cart_details"> 3 items <br />

        <span class="border_cart"></span> Total: <span class="price">350$</span> </div>

        <div class="cart_icon"><a href="shoppingcart" title="header=[Checkout] body=[&nbsp;] fade=[on]"><img src="static/images/shoppingcart.png" alt="" width="48" height="48" border="0" /></a></div>

      </div>

      <div class="title_box">What's new</div>

      <div class="border_box">

        <div class="border_box">
		<c:forEach items="${products[1]}" var="product" end="0">
        	<div class="product_title"><a href="details.html">${product.name}</a></div>

    	    <div class="product_img"><a href="details.html"><img width="auto" height="94px" src="static/images/${product.catalogid}${product.id}.jpg" alt="" border="0" /></a></div>

	        <div class="prod_price"><span class="reduce">$${product.unitPrice+product.unitPrice*0.2}</span> <span class="price">$${product.unitPrice}</span></div>
		</c:forEach>
      </div>

      </div>

      <div class="banner_adds"> <a href=""><img src="static/images/bann1.jpg" alt="" border="0" /></a> </div>

    </div>

    <!-- end of right content -->

  </div>

  <!-- end of main content -->

  <div class="footer">

    <div class="left_footer"> <img src="static/images/footer_logo.png" alt="" width="170" height="49"/> </div>

    <div class="center_footer"> Pink Group. All Rights Reserved 2008<br />

      <a href="http://csscreme.com"><img src="static/images/csscreme.jpg" alt="csscreme" border="0" /></a><br />

      <img src="static/images/payment.gif" alt="" /> </div>

  </div>

</div>

<!-- end of main_container -->

</html>

