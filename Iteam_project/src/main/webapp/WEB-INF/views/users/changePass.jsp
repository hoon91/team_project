<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
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
<title>비밀번호변경</title>
</head>
<script>
$(document).ready(function() {
	$("#submit").on("click",function(){

		var pw=$("#password").val();
		var num = pw.search(/[0-9]/g);
		var eng = pw.search(/[a-z]/ig);
		
		if(pw.length<8 || pw.length>20){
			alert("비밀번호는 8자리~20자리 이내로 입력해주세요.");
			return false;
		}else if(pw.search(/\s/)!=-1){
			alert("비밀번호는 공백 없이 입력해주세요.");
			return false;
		}else if(num <0 || eng <0){
			alert("비밀번호는 영문, 숫자, 특수무자를 혼합하여 입력해주세요.");
			return false;
		}
		var pw2=$("#password2").val();
		if(pw!=pw2){
			alert("비밀번호를 다릅니다.")
			$("#password2").focus();
			return false;
		}
		alert("비밀번호가 변경되었습니다. 다시 로그인 해주세요");
	
	})
		
})
</script>
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
				<li><a href="/changePass.do">비밀번호변경</a></li>
				<li><a href="/deleteUser.do?id=${user.id}">회원탈퇴</a></li>
			</ul>
			</aside>	
	<div class="container_box">
		<hr>
         <h2>비밀번호 변경</h2>            
		<hr>
		<form action="updatePass.do" method="post">
			<table class="type03">
  				<tr>
    				<th scope="row">아이디</th>
    				<td><input type="text" name="id" id="id" value="${user.id}" readonly="readonly"/>
  				</tr>
  				<tr>
    				<th scope="row">비밀번호</th>
    				<td><input type="password" name="password" id="password"/></td>
  				</tr>
  				<tr>
  				  	<th scope="row">비밀번호 확인</th>
    				<td><input type="password" name="password2" id="password2" /></td>
  				</tr>
			</table>
			<br>
	
				<div align="center"> 
					<button type="submit" class="btn btn-primary" id="submit">변경하기</button>
					<button type="reset" class="btn btn-danger">다시입력</button>	
				</div>
		</form>
		<hr>
	</div>
</div>
</div>
</body>
</html>