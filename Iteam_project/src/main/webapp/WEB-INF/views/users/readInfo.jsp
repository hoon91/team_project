<%@page import="java.util.Calendar"%>
<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<title>회원정보보기</title>
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
			<h3>${user.id}님의 정보</h3>
			<hr>
		<form action="updateInfo.do">
		<table class="type03">
  				<tr>
    				<th scope="row">아이디</th>
    				<td><input name="id" value="${userInfo.id}" readonly/></td>
  				</tr>
  				<tr>
    				<th scope="row">이름</th>
    				<td><input name="name" value="${userInfo.name}" readonly/></td>
  				</tr>
  				<tr>
    				<th scope="row">연락처</th>
    				<td><input id="phone" name="phone" value="${userInfo.phone}" readonly></td>
  				</tr>
  				<tr>
    				<th scope="row">사는지역</th>
    				<td><input name="city" id="city" value="${userInfo.city}" readonly></td>
  				</tr>
 				<tr>
    				<th scope="row">나이</th>
    				<%
						Calendar cal = Calendar.getInstance();
						int bornyear = cal.get(Calendar.YEAR);
					%>
					<c:set var="borny" value='<%=bornyear %>'/>
    				<td><input name="age" id="age" value="${borny-userInfo.age+1 }" readonly></td>
  				</tr>
  				<tr>
    				<th scope="row">우편번호</th>
    				<td><input id="postcode" name="postcode" value="${userInfo.postcode}" readonly>
  				</tr>
  				<tr>
    				<th scope="row">도로명주소</th>
    				<td><input id="roadAddress" name="roadAddress" value="${userInfo.roadAddress}" readonly></td>
  				</tr>
  				<tr>
    				<th scope="row">지번주소</th>
    				<td><input id="jibunAddress" name="jubunAddress" value="${userInfo.jubunAddress}" readonly></td>
  				</tr>
  				<tr>
    				<th scope="row">상세주소</th>
    				<td><input  id="detailAddress" name="detailAddress" value="${userInfo.detailAddress}" readonly></td>
  				</tr>
  				<tr>
    				<th scope="row">참고항목</th>
    				<td><input id="extraAddress" name="extraAddress" value="${userInfo.extraAddress}" readonly></td>
  				</tr>
				<tr>
					<td colspan="2" align="center">
					<button class="btn btn-default" id="update_userInfo">회원정보수정</button>
					<button type="button" class="btn btn-default" onclick="location.href='/'">메인으로</button>
					</td>
				</tr>
			</table>
		</form>
	</div>
</div>
</div>

	<div class="footer">
		<%@ include file="../inc/footer.jsp" %>
	</div>
	
<script>

$("#update_userInfo").click(function() {
	var id = $("#inputId").val();
	location.assign("/updateInfo.do?" + id);
});

</script>	
	
</body>
</html>