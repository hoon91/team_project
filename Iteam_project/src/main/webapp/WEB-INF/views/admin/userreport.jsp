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
<title>신고 회원 관리</title>

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
		<hr>
		<h2>신고 접수 회원 명단</h2>
		<hr>
		<form action="deleteReportUser.do" method="get">
			<table class="table table-hover">
				<tr>
					<th width="150">I  D</th>
					<th width="150">비밀번호</th>
					<th width="100">이름</th>
					<th width="200">연락처</th>
					<th width="150">회원등급</th>
					<th width="100">신고해제</th>
					<th width="100">강제탈퇴</th>

				</tr>
				<c:forEach items="${reportlist }" var="report">
					<tr>
						<td>${report.id }</td>
						<td>${report.password }</td>
						<td>${report.name }</td>
						<td>${report.phone }</td>
						<td>${report.grade }</td>
						<td><button type="button" style="width: 80pt; height: 30pt;" class="btn btn-primary"
						onclick="location.href='returnReportUser.do?id=${report.id}'">신고해제</button></td>
						<td><button type="button" style="width: 80pt; height: 30pt;" class="btn btn-danger"
						onclick="location.href='deleteReportUser.do?id=${report.id}'">강제탈퇴</button></td>
					</tr>
				</c:forEach>
			</table>

	<div class="col-md-offset-3">
		<ul class="pagination">
			<c:if test="${startPage != 1 }">
				<li><a href="/userreport.do?pageNum=${startPage - 1 }">&lt;</a></li>
			</c:if>
			<c:forEach var="i" begin="${startPage}" end="${endPage}">
				<li><a href="javascript:go('${i}')">${i}</a></li>
			</c:forEach>
			<c:if test="${endPage != lastPage}">
				<li><a href="/userreport.do?pageNum=${endPage+1 }">&gt;</a></li>
			</c:if>
			</ul>
			</div>
			
		</form>
			<div align="center">
				<button type="button" class="btn btn-default" onclick="location.href='admin.do'"style="width: 90pt; height: 30pt;">목록으로</button>
				<button type="button" class="btn btn-default" onclick="location.href='login.do'"style="width: 90pt; height: 30pt;">로그인</button>
			</div>
		</div>
	</div>
</div>
	
	<div class="footer">
		<%@ include file="../inc/footer.jsp" %>
	</div>
	



<script>
	function go(pageNum) {
	location.href = 'userreport.do?pageNum=' + pageNum;
}
</script>


</body>
</html>