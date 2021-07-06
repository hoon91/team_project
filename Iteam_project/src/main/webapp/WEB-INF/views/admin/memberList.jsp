<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<link rel="icon" href="data:;base64,iVBORw0KGgo=">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<link rel="stylesheet" href="../resources/css/iteam_main.css">
<title>회원 목록</title>
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
		<aside>
			<ul>
				<li><a href="/userreport.do">신고회원관리</a></li>
				<li><a href="/usergrade.do">회원등급관리</a></li>
				<li><a href="/selectGrade.do">회원등급상세</a></li>
				<li><a href="/memberList.do">회원목록</a></li>
			</ul>
			</aside>
			
	<div class="container_box">
		<hr>
		<h3>회원 목록</h3>
		<hr>
	<form>
	<table class="table table-hover">
		<tr>
			<th style="color: white" bgcolor="black" width="150">아이디</th>
			<th style="color: white" bgcolor="black" width="100">이름</th>
			<th style="color: white" bgcolor="black" width="150">연락처</th>
			<th style="color: white" bgcolor="black" width="100">나이</th>
			<th style="color: white" bgcolor="black" width="100">도시명</th>
			<th style="color: white" bgcolor="black" width="200">도로명주소</th>
			<th style="color: white" bgcolor="black" width="200">지번주소</th>
			<th style="color: white" bgcolor="black" width="100">등급</th>
		</tr>
		<c:forEach items="${list}" var="list">
			<tr>
				<td>${list.id}</td>
				<td>${list.name}</td>
				<td>${list.phone}</td>
				<td>${list.age}</td>
				<td>${list.city}</td>
				<td>${list.roadAddress}</td>
				<td>${list.jubunAddress}</td>
				<td>${list.grade}</td>
			</tr>
		</c:forEach>
	</table>

		<div class="col-md-offset-3">
				<ul class="pagination">
			<c:if test="${startPage != 1 }">
				<li><a href="/memberList.do?pageNum=${startPage - 1 }">&lt;</a></li>
			</c:if>
			<c:forEach var="i" begin="${startPage}" end="${endPage}">
				<li><a href="javascript:go('${i}')">${i}</a></li>
			</c:forEach>
			<c:if test="${endPage != lastPage}">
				<li><a href="/memberList.do?pageNum=${endPage+1 }">&gt;</a></li>
			</c:if>
			</ul>
			</div>
			
		</form>	
			<div align="center">
			<button type="button" class="btn btn-default" onclick="location.href='admin.do'"style="width: 90pt; height: 30pt;">목록으로</button>
			</div>
		</div>
	</div>
</div>

	<div class="footer">
		<%@ include file="../inc/footer.jsp" %>
	</div>


<script>
	function go(pageNum) {
	location.href = 'memberList.do?pageNum=' + pageNum;
}
</script>
</body>
</html>