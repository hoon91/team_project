<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
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
<title>회원탈퇴</title>
</head>
<body>	    


	<div id="gnb">
        <%@ include file="../inc/gnb.jsp" %>
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
			<aside>
			<ul>
				<li><a href="/readInfo.do?id=${user.id}">회원정보수정</a></li>
				<li><a href="changePass.do?id=${user.id}">비밀번호변경</a></li>
				<li><a href="/deleteUser.do?id=${user.id}">회원탈퇴</a></li>
			</ul>
			</aside>
			
		<div class="container_box">
		<hr>
		<h3>회원탈퇴</h3>
		<hr>
		<form action="deleteUser.do" method="post">
			<table class="type03">
				<tr>
    				<th scope="row">I     D</th>
    				<td><input type="text" name="id" value="${user.id}" readonly="readonly"/></td>
  				</tr>
  				<tr>
    				<th scope="row">PASSWORD</th>
    				<td><input type="password" name="password"/></td>
  				</tr>
				<tr>
					<td colspan="2" align="center">
					<input class="btn btn-default" type="submit" value="탈퇴"  onclick="return check()"/>
				</td>
				</tr>
			</table>
	
		</form>
		<hr>
	    </div>
    </div>
</div>

	<div class="footer">
		<%@ include file="../inc/footer.jsp" %>
	</div>

<script>
function check(){
	if(!confirm("정말 탈퇴하시겠습니까?")) return false
	else true;
}

</script>	
</body>
</html>