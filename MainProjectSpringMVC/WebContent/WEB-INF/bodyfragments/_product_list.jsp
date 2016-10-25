<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<head>
<link rel="stylesheet" type="text/css" href="static/style.css" />
<script type="text/javascript" src="static/js/boxOver.js"></script>
</head>

<br/>
<br/>
    <!-- Page Content -->
    <div class="container-fluid">

        <div class="row-fluid">

            <div class="col-md-12">          

                <div class="row">

					<c:forEach items="${products}" var="product">
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

            </div>

        </div>

    </div>
    <!-- /.container -->