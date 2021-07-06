<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style>
*{
	margin:0;
	padding:0;
	box-sizing:border-box;
	
}
.lab2 {
	display: flex;
	align-items: center;
	position: relative;
	top: 50px;
	left: 0px;
	transition: .5s;
}

.button-in-label {
	font-size: 3rem;
	margin-right: 10px;
}

.sidemenu-button {
	border: none;
	outline: none;
	background-color: transparent;
	font-size: 15px;
}
section{
	background-color:#34495e;
	color:#fff;
	width:250px;
	padding-top:30px;
	padding-left: 30px;
	position: absolute;
	top: 0;
	left:-250px;
	bottom: 0;
	transition: 1s ease;
	z-index:6;
	
}
.sidelist{
	padding-left:45px;
}
.slist{
	margin:30px 0;
}
.slist:nth-child(1){
	magin-top: 50px;
}

</style>

<script>
	
</script>
</head>
<body>
	<main>
	<c:if test="${user!=null}">
	<div class = "lab2" style="position:fixed;"><span class="button-in-label"></span> 
		<button class="sidemenu-button"><img src="../resources/images/myproject.png" alt="" height="52px" width="180px"></button>
	</div>
	</c:if>
	<section>
		<h1>M Y 페 이 지</h1>
		<br><br><br><br><br>
		<c:if test="${user.pno!= 0 }"><h3>현재 진행 중인</h3><h3>프로젝트가</h3><h3> 있습니다.</h3></c:if>
		<c:if test="${user.pno eq 0 }"><h3>현재 진행 중인</h3><h3>프로젝트가</h3><h3> 없습니다.</h3></c:if>
		<br>
		<br>
		<br>
		<c:if test="${user.pno!= 0 }"><a href="/team/teamProject.do?pno=${user.pno}" style="color:#81BEF7; font-size: 1.2em;
font-weight: bold;">진행 중인 프로젝트로 이동</a></c:if>
	</section>
	</main>
	<script>
		const sidebtn = document.querySelector('.sidemenu-button');
		const section = document.querySelector('section');
		const label = document.querySelector('lab2');
		
		sidebtn.addEventListener('click',function(){
			section.style.left =0;
			label.style.opacity =0;
			
		})
		
		sidebtn.addEventListener('blur',function(){
			section.style.left ='-250px';
			label.style.opacity =1;
			
		})
	</script>
</body>
</html>