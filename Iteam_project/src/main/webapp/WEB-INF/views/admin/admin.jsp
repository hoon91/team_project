<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<link rel="icon" href="data:;base64,iVBORw0KGgo=">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<link rel="stylesheet" href="../resources/css/iteam_main.css">
<title>관리자페이지</title>
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
				<h1>관리자 페이지 입니다</h1>
				<hr>
			</div>	
    	</div>
	</div>
			
	<div class="footer">
		<%@ include file="../inc/footer.jsp" %>
	</div>
	
</body>
</html>