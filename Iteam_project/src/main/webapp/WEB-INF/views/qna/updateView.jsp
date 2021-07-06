<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<link rel="icon" href="data:;base64,iVBORw0KGgo=">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<link rel="stylesheet" href="../resources/css/iteam_main.css">
<title>QNA</title>
</head>
	<div id="gnb">
        <%@ include file="../inc/sidemenu.jsp" %>
    </div>
	<script type="text/javascript">
		$(document).ready(function(){
			var formObj = $("form[name='updateForm']");
			
			$(".cancel_btn").on("click", function(){
				event.preventDefault();
				location.href = "/qna/readView.do?bno=${update.bno}"
					   + "&page=${scri.page}"
					   + "&perPageNum=${scri.perPageNum}"
					   + "&searchType=${scri.searchType}"
					   + "&keyword=${scri.keyword}";
			})
			
			$(".update_btn").on("click", function(){
				if(fn_valiChk()){
					return false;
				}
				formObj.attr("action", "/qna/update.do");
				formObj.attr("method", "post");
				formObj.submit();
			})
		})
			
		function fn_valiChk(){
			var updateForm = $("form[name='updateForm'] .form-control").length;
			for(var i = 0; i<updateForm; i++){
				if($(".form-control").eq(i).val() == "" || $(".form-control").eq(i).val() == null){
					alert($(".form-control").eq(i).attr("title"));
					return true;
				}
			}
		}
		
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
			<h1>QNA</h1>
			<hr>

				<form name="updateForm" role="form" method="post" action="/qna/update.do">
					<input type="hidden" name="bno" value="${update.bno}" readonly="readonly"/>
				
				
					<div class="mb-3">
						<label for="title">제목</label>
						<input type="text" id="title" name="title" value="${update.title}" class="form-control" title="제목을 입력하세요."/>
					</div>	
					
					<div class="mb-3">
						<label for="content">내용</label>
						<textarea id="content" name="content"class="form-control" title="내용을 입력하세요."><c:out value="${update.content}" /></textarea>
					</div>
					
					<div class="mb-3">
						<label for="writer">작성자</label>
						<input type="text" id="writer" name="writer" class="form-control" value="${update.writer}" readonly="readonly"/>
					</div>
					
					<div class="mb-3">
						<label for="regdate">작성날짜</label>
						<fmt:formatDate value="${update.regdate}" pattern="yyyy-MM-dd"/>					
					</div>				
					
					<div>
						<button type="submit" class="update_btn btn btn-primary">저장</button>
						<button type="submit" class="cancel_btn btn btn-warning">취소</button>
					</div>
				<hr/>
			</form>
		</div>
	</div>
	
	<div class="footer">
		<%@ include file="../inc/footer.jsp" %>
	</div>	

</body>
</html>