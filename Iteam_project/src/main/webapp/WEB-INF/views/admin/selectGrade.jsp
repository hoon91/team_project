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
<title>회원 등급 목록</title>
</head>
<body>
<script type="text/javascript">
		
		$(document).ready(function(){
			var formObj = $("form[name='readForm']");
			
			// 수정 
			$(".update_btn").on("click", function(){
				formObj.attr("action", "/updateView.do");
				formObj.attr("method", "get");
				formObj.submit();				
			})
			
			// 삭제
			$(".delete_btn").on("click", function(){
				
				var deleteYN = confirm("삭제하시겠습니까?");
				if(deleteYN == true){
					
				formObj.attr("action", "/delete.do");
				formObj.attr("method", "post");
				formObj.submit();
					
				}
			})
			
			// 목록
			$(".vip_btn").on("click", function(){
				
				location.href = "/userGradeList.do?grade=${vip.grade}"
			})
			
			$(".gold_btn").on("click", function(){
				
				location.href = "/userGradeList.do?grade=${gold.grade}"
			})
			
			$(".silver_btn").on("click", function(){
				
				location.href = "/userGradeList.do?grade=${silver.grade}"
			})
			
			$(".normal_btn").on("click", function(){
				
				location.href = "/userGradeList.do?grade=${normal.grade}"
			})
		
		})
</script>

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
		<h2> 회원 등급 목록 </h2>
		<hr>
		<h3>열람하실 회원을 선택해주십시오.</h3>
			<hr>
				<div align="center">
					<button type="button" class="vip_btn btn btn-danger">V.I.P 회 원</button><hr>
					<button type="button" class="gold_btn btn btn-warning">골 드 회 원</button><hr>
					<button type="button" class="silver_btn btn btn-primary">실 버 회 원</button><hr>	
					<button type="button" class="normal_btn btn btn-info">일 반 회 원</button><hr>	
				</div>
			</div>
		</div>
	</div>
	
	<div class="footer">
		<%@ include file="../inc/footer.jsp" %>
	</div>
	

</body>
</html>