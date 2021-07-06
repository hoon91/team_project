<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<link rel="icon" href="data:;base64,iVBORw0KGgo=">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<link rel="stylesheet" href="../resources/css/iteam_main.css">

<title>로그인</title>

</head>
<body>


   <div id="gnb">
        <%@ include file="../inc/gnb.jsp" %>
         <%@ include file="../inc/sidemenu.jsp" %>
    </div>

	<div class="logo">
		<h1>
		<a href="/"><img src="../resources/images/iteam.png" alt="logo" width="200px"/></a>
		</h1>
	</div>

	<div class="main-menu">
		<%@ include file="../inc/main-menu.jsp" %>
    </div>
    
	<div class="container">
		<div class="content">	
		<h3>L O G I N</h3>
		<hr>
	
		<form action="login.do" method="post" style="align-content: center">
			 <table class="type03">
 			 		<tr>
    					<th scope="row">I     D</th>
    					<td><input type="text" name="id" value="${user.id}" /></td>
  					</tr>
  					<tr>
    					<th scope="row">PASSWORD</th>
    					<td><input type="password" name="password" value="${user.password}" /></td>
  					</tr>
			 </table>
			<br>	
			<div align="center">    
			      <input type="submit" value="로  그  인" style="height:40px; width:100px" class="btn btn-default" />
			      <input type="button" onclick="javascript:location.href='membership.do'" value="회원가입" style="height:40px; width:100px" class="btn btn-default"/>
			</div>		
		</form>
		<hr>
	  </div>
	</div>


</body>
</html>