<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<title>회원 등급 상세</title>

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
				<li><a href="/userreport.do">신고회원관리</a></li>
				<li><a href="/usergrade.do">회원등급관리</a></li>
				<li><a href="/selectGrade.do">회원등급상세</a></li>
				<li><a href="/memberList.do">회원목록</a></li>
			</ul>
			</aside>
	<div class="container_box">
		<h1> 회 원 등 급 </h1>
		<hr>

		<h3>V.I.P 회원</h3>

		<table class="table table-striped table-sm">
			<tr>
				<th style="color: white" bgcolor="black" width="150">아이디</th>
				<th style="color: white" bgcolor="black" width="100">이름</th>
				<th style="color: white" bgcolor="black" width="100">연락처</th>
				<th style="color: white" bgcolor="black" width="200">나이</th>
				<th style="color: white" bgcolor="black" width="200">주소</th>
				<th style="color: white" bgcolor="black" width="150">회원등급</th>
				<th colspan="2" style="color: white" bgcolor="black" width="150">회원관리</th>
			</tr>
			<c:forEach items="${gradelist3 }" var="grade3">
				<tr>
					<td>${grade3.id}</td>
					<td>${grade3.name}</td>
					<td>${grade3.phone}</td>
					<td>${grade3.age}</td>
					<td>${grade3.roadAddress}</td>
					<td>${grade3.grade}</td>
					<td colspan="2">
					<button type="button" style="width: 60pt; height: 30pt;" class="btn btn-danger"
					onclick="location.href='updateGold1.do?id=${grade3.id}'">강등</button>
					</td>
				</tr>
			</c:forEach>
		</table>
		<hr>
		<h3>골드 회원</h3>

		<table class="table table-striped table-sm">
			<tr>
				<th style="color: white" bgcolor="black" width="150">아이디</th>
				<th style="color: white" bgcolor="black" width="100">이름</th>
				<th style="color: white" bgcolor="black" width="100">연락처</th>
				<th style="color: white" bgcolor="black" width="200">나이</th>
				<th style="color: white" bgcolor="black" width="200">주소</th>
				<th style="color: white" bgcolor="black" width="150">회원등급</th>
				<th colspan="2" style="color: white" bgcolor="black" width="150">회원관리</th>
			</tr>
			<c:forEach items="${gradelist2 }" var="grade2">
				<tr>
					<td>${grade2.id}</td>
					<td>${grade2.name}</td>
					<td>${grade2.phone}</td>
					<td>${grade2.age}</td>					
					<td>${grade2.roadAddress}</td>
					<td>${grade2.grade}</td>
					<td>
					<button type="button" style="width: 50pt; height:30pt;" class="btn btn-success"
						onclick="location.href='updateVIP1.do?id=${grade2.id}'">등업</button>
					</td>
					<td>
					<button type="button" style="width: 50pt; height: 30pt;" class="btn btn-danger"
						onclick="location.href='updateSilver1.do?id=${grade2.id}'">강등</button>
					</td>

				</tr>
			</c:forEach>
		</table>
		<hr>
		<h3>실버 회원</h3>

		<table class="table table-striped table-sm">
			<tr>
				<th style="color: white" bgcolor="black" width="150">아이디</th>
				<th style="color: white" bgcolor="black" width="100">이름</th>
				<th style="color: white" bgcolor="black" width="100">연락처</th>
				<th style="color: white" bgcolor="black" width="200">나이</th>
				<th style="color: white" bgcolor="black" width="200">주소</th>
				<th style="color: white" bgcolor="black" width="150">회원등급</th>
				<th colspan="2" style="color: white" bgcolor="black" width="150">회원관리</th>
			</tr>
			<c:forEach items="${gradelist1 }" var="grade1">
				<tr>
					<td>${grade1.id}</td>
					<td>${grade1.name}</td>
					<td>${grade1.phone}</td>
					<td>${grade1.age}</td>
					<td>${grade1.roadAddress}</td>
					<td>${grade1.grade}</td>
					<td>
					<button type="button" style="width: 50pt; height: 30pt;" class="btn btn-success"
					onclick="location.href='updateGold1.do?id=${grade1.id}'">등업</button>
					</td>
					<td>
					<button type="button" style="width: 50pt; height: 30pt;" class="btn btn-danger"
					onclick="location.href='updateNormal1.do?id=${grade1.id}'">강등</button>
					</td>
				</tr>
			</c:forEach>
		</table>
		<hr>
		<h3>일반 회원</h3>

		<table class="table table-striped table-sm">
			<tr>
				<th style="color: white" bgcolor="black" width="150">아이디</th>
				<th style="color: white" bgcolor="black" width="100">이름</th>
				<th style="color: white" bgcolor="black" width="100">연락처</th>
				<th style="color: white" bgcolor="black" width="200">나이</th>
				<th style="color: white" bgcolor="black" width="200">주소</th>
				<th style="color: white" bgcolor="black" width="150">회원등급</th>
				<th colspan="2" style="color: white" bgcolor="black" width="150">회원관리</th>
			</tr>
			<c:forEach items="${gradelist0 }" var="grade0">
				<tr>
					<td>${grade0.id}</td>
					<td>${grade0.name}</td>
					<td>${grade0.phone}</td>
					<td>${grade0.age}</td>
					<td>${grade0.roadAddress}</td>
					<td>${grade0.grade}</td>
					<td colspan="2">
					<button type="button" style="width: 60pt; height: 30pt;" class="btn btn-success"
					onclick="location.href='updateSilver1.do?id=${grade0.id}'">등업</button>
					</td>
				</tr>
			</c:forEach>
		</table>
		<hr>
		</div>
	</div>
</div>
	
	<div class="footer">
		<%@ include file="../inc/footer.jsp" %>
	</div>
	
</body>
</html>