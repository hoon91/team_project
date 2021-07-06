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

<title>회원 등급 관리</title>

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
		<h2>회 원 등 급</h2>
		<hr>
			<table class="table table-hover">
				<tr>
					<th style="color: white" bgcolor="black" width="100">아이디</th>
					<th style="color: white" bgcolor="black" width="100">이름</th>
					<th style="color: white" bgcolor="black" width="100">연락처</th>
					<th style="color: white" bgcolor="black" width="100">나이</th>
					<th style="color: white" bgcolor="black" width="300">도로명주소</th>
					<th style="color: white" bgcolor="black" width="300">지번주소</th>
					<th style="color: white" bgcolor="black" width="150">회원등급</th>
					<th style="color: white" bgcolor="black" width="100">등업</th>
					<th style="color: white" bgcolor="black" width="100">강등</th>

				</tr>
				<c:forEach items="${gradelist }" var="gr">
					<tr>
						<td>${gr.id}</td>
						<td>${gr.name}</td>
						<td>${gr.phone}</td>
						<td>${gr.age}</td>
						<td>${gr.roadAddress}</td>
						<td>${gr.jubunAddress}</td>
						<td>${gr.grade}</td>
						<td>
						<c:choose>
						<c:when test="${gr.grade eq 'VIP'}"><input type="button" style="width: 80pt; height: 30pt;"
							value="VIP" class="btn btn-success"
							onclick="location.href='updateVIP.do?id=${gr.id}'"> </c:when>
						<c:when test="${gr.grade eq 'gold'}"><input type="button" style="width: 80pt; height: 30pt;"
							value="VIP로 등업" class="btn btn-success"
							onclick="location.href='updateVIP.do?id=${gr.id}'"> </c:when>
						<c:when test="${gr.grade eq 'silver'}"><input type="button" style="width: 80pt; height: 30pt;"
							value="골드로 등업" class="btn btn-success"
							onclick="location.href='updateGold.do?id=${gr.id}'"> </c:when>
						<c:when test="${gr.grade eq 'normal'}"><input type="button" style="width: 80pt; height: 30pt;"
							value="실버로 등업" class="btn btn-success"
							onclick="location.href='updateSilver.do?id=${gr.id}'"> </c:when>
						</c:choose>
						</td>
						<td>
						<c:choose>
						<c:when test="${gr.grade eq 'VIP'}"><input type="button" style="width: 80pt; height: 30pt;"
							value="골드로 강등" class="btn btn-danger"
							onclick="location.href='updateGold.do?id=${gr.id}'"> </c:when>
						<c:when test="${gr.grade eq 'gold'}"><input type="button" style="width: 80pt; height: 30pt;"
							value="실버로 강등" class="btn btn-danger"
							onclick="location.href='updateSilver.do?id=${gr.id}'"> </c:when>
						<c:when test="${gr.grade eq 'silver'}"><input type="button" style="width: 80pt; height: 30pt;"
							value="일반으로 강등" class="btn btn-danger"
							onclick="location.href='updateNormal.do?id=${gr.id}'"> </c:when>
						<c:when test="${gr.grade eq 'normal'}"><input type="button" style="width: 80pt; height: 30pt;"
							value=".." class="btn btn-default"
							> </c:when>
						</c:choose>
						</td>
					</tr>
				</c:forEach>
			</table>
			
	<div class="col-md-offset-3">
		<ul class="pagination">
			<c:if test="${startPage != 1 }">
				<li><a href="/userGradeList.do?pageNum=${startPage - 1 }&grade=${grade}">&lt;</a>
			</c:if>
			<c:forEach var="i" begin="${startPage}" end="${endPage}">
				<li><a href="javascript:go('${i}','${grade}')">${i}</a></li>
				<%--   <a href="getBoardList.do?pageNum=${i}&searchCondition=${searchCondition}&searchKeyword=${searchKeyword}">${i}</a>  --%>
			</c:forEach>
			<c:if test="${endPage != lastPage}">
				<li><a href="/userGradeList.do?pageNum=${endPage+1 }&grade=${grade}">&gt;</a></li>
			</c:if>
			</ul>
			</div>
			
			<br>
			<div align="center">
				<input type="button" class="btn btn-default" onclick="location.href='admin.do'"style="width: 90pt; height: 30pt;" value="목록으로"  >
				<input type="button" class="btn btn-default" onclick="location.href='login.do'"style="width: 90pt; height: 30pt;" value="로그인"  >
			</div>
		</div>
	</div>
</div>

	<div class="footer">
		<%@ include file="../inc/footer.jsp" %>
	</div>


<script>
function go(pageNum,grade) {
	location.href = 'userGradeList.do?pageNum='+ pageNum+'&grade='+grade;
}
</script>
</body>
</html>