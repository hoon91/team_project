<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ITEAM</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<link rel="icon" href="data:;base64,iVBORw0KGgo=">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<link rel="stylesheet" href="../resources/css/iteam_main.css">
<style>

</style>
</head>
<body>
	
	<div id="gnb">
        <%@ include file="inc/gnb.jsp" %>
        <%@ include file="inc/sidemenu.jsp" %>
    </div>

	<div class="logo">
		<h1>
		<a href="/"><img src="../resources/images/iteam.png" alt="logo" width="200px"/></a>
		</h1>
	</div>

	<div class="main-menu">
		<%@ include file="inc/main-menu.jsp" %>
    </div>


	<div class="container">
		<div class="content">
  	<div id="myCarousel" class="carousel silde" data-ride="carousel">
      <ol class="carousel-indicators">
       <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
       <li data-target="#myCarousel" data-slide-to="1"></li>
       <li data-target="#myCarousel" data-slide-to="2"></li>
    </ol>
   <div class="carousel-inner">
   		 <div class="item active">
     		<img src="../resources/images/iteamimg1.png">
    	</div>
    	<div class="item">
     		<img src="../resources/images/iteamimg2.png">
    	</div>
    	<div class="item">
     		<img src="../resources/images/iteamimg3.png">
    	</div>
    </div>
    <a class="left control-control" href="#myCarousel" data-slide="prev">
     	<span class="giyphicon giyphicon-chevron-left"></span>
    </a>
    <a class="carousel-control-next" href="#myCarousel" role="button" data-slide="next">
    	 <span class="giyphicon giyphicon-chevron-right"></span>
    </a>
   </div>
   <div align="center">
   		<h4>S  U  P  P  O  R  T</h4>
   		<img src="../resources/images/android.png"  width="280">
    	<img src="../resources/images/java.jpg" width="280">
    	<img src="../resources/images/oracle.png" width="280">
    	<img src="../resources/images/python.jpg" width="280">
    	<img src="../resources/images/react.jpg" width="280">
   		<img src="../resources/images/mysql.jpg" width="280">
    	<img src="../resources/images/js.png" width="280">
    	<img src="../resources/images/jsp.png" width="280">
  	</div>
  </div>
</div>

	<div class="footer">
		<%@ include file="inc/footer.jsp" %>
	</div>

</body>
</html>