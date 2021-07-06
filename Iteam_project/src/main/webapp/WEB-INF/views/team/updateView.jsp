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
<title>팀원구하기</title>
</head>
	<div id="gnb">
        <%@ include file="../inc/sidemenu.jsp" %>
    </div>
	<script type="text/javascript">
		$(document).ready(function(){
			var formObj = $("form[name='updateForm']");
			
			$(".cancel_btn").on("click", function(){
				event.preventDefault();
				location.href = "/team/readView.do?bno=${update.bno}"
					   + "&page=${scri.page}"
					   + "&perPageNum=${scri.perPageNum}"
					   + "&searchType=${scri.searchType}"
					   + "&keyword=${scri.keyword}";
			})
			
			$(".update_btn").on("click", function(){
				if(fn_valiChk()){
					return false;
				}
				formObj.attr("action", "/team/update.do");
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
			<h1>팀원구하기</h1>
			<hr>

				<form name="updateForm" role="form" method="post" action="/team/update.do">
					<input type="hidden" name="bno" value="${update.bno}" readonly="readonly"/>
				
				
					<div class="mb-3">
						<label for="title">프로젝트명</label>
						<input type="text" id="title" name="title" value="${update.title}" class="form-control" title="프로젝트명을 입력하세요."/>
					</div>	
					
					<div class="mb-3">
						<label for="skill">사용기술</label>
						<input type="text" id="skill" name="skill" value="${update.skill}" class="form-control" title="사용기술을 입력하세요."/>
					</div>	
					
					
					<div class="mb-3">
						<label for="content">세부사항</label>
						<textarea id="content" rows="5" name="content"class="form-control" title="세부사항을 입력하세요."><c:out value="${update.content}" /></textarea>
					</div>
					
					<div class="mb-3">
						<label for="people">정원</label><br>
							<select name="people" class="form-control">
								<option value="3" ${update.people == '3' ? 'selected="selected"' : '' }>3인</option>
								<option value="4" ${update.people == '4' ? 'selected="selected"' : '' }>4인</option>
								<option value="5" ${update.people == '5' ? 'selected="selected"' : '' }>5인</option>
								<option value="6" ${update.people == '6' ? 'selected="selected"' : '' }>6인</option>
								<option value="7" ${update.people == '7' ? 'selected="selected"' : '' }>7인</option>
							</select>
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