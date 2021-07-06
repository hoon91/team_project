<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
		var formObj = $("form[name='writeForm']");
		$(".write_btn").on("click", function(){
			if(fn_valiChk()){
				return false;
			}
			formObj.attr("action", "/team/write.do");
			formObj.attr("method", "post");
			formObj.submit();
		});
	})
	function fn_valiChk(){
		var regForm = $("form[name='writeForm'] .form-control").length;
		for(var i = 0; i<regForm; i++){
			if($(".form-control").eq(i).val() == "" || $(".form-control").eq(i).val() == null){
				alert($(".form-control").eq(i).attr("title"));
				return true;
			}
		}
	}
		function fn_valiChk(){
			var regForm = $("form[name='writeForm'] .form-control").length;
			for(var i = 0; i<regForm; i++){
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
				
				<form name="writeForm" method="post" action="/team/write.do">
					<table>
						<tbody>
							<c:if test="${user.id != null}">
								<div class="mb-3">
								<label for="title">프로젝트명</label>
								<input type="text" id="title" name="title" class="form-control" title="프로젝트명을 입력하세요." placeholder="ex) Spring을 이용한 웹제작, 안드로이드를 이용한 앱제작"/>
								</div>
								<div class="mb-3">
								<label for="skill">사용기술</label>
								<input type="text" id="skill" name="skill" class="form-control" title="사용기술을 입력하세요." placeholder="사용하고자 하는 사용언어, front기술, Back기술을 명시해주십시오"/>
								</div>
								<div class="mb-3">
									<label for="content">세부사항</label><br>
									<textarea id="content" rows="5" name="content" class="form-control" title="세부사항을 입력하세요."></textarea>
								</div>
								<div class="mb-3">
								<label for="people">정원</label><br>
								<select name="people" class="form-control">
									<option value="">인원을 선택해주세요</option>
									<option value="3" >3인</option>
									<option value="4">4인</option>
									<option value="5">5인</option>
									<option value="6">6인</option>
									<option value="7">7인</option>
								</select>
								</div>
																	
								<div class="mb-3">
								<label for="writer">작성자</label>
								<input type="text" id="writer" name="writer" class="form-control" title="작성자를 입력하세요." value="${user.id}" readonly="readonly" />
								</div>
	
								<div>	
								<input type="button" class="write_btn btn btn-primary" value="작성">					
								<input type="button" class="btn btn-warning" value="취소" onclick= "location.href = '/team/list.do'">
								</div>
								
								
							</c:if>
							<c:if test="${user.id == null}">
								<p>로그인 후에 작성하실 수 있습니다.</p>
							</c:if>
						</tbody>			
					</table>
				</form>
			<hr/>
		</div>
	</div>
		
	<div class="footer">
		<%@ include file="../inc/footer.jsp" %>
	</div>
	
</body>
</html>