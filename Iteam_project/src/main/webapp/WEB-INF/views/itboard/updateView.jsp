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
<title>IT자료실</title>
</head>
	<div id="gnb">
        <%@ include file="../inc/sidemenu.jsp" %>
    </div>
	<script type="text/javascript">
		$(document).ready(function(){
			var formObj = $("form[name='updateForm']");
			
			$(document).on("click","#fileDel", function(){
				$(this).parent().remove();
			})
			fn_addFile();
			
			$(".cancel_btn").on("click", function(){
				event.preventDefault();
				location.href = "/itboard/readView.do?bno=${update.bno}"
					   + "&page=${scri.page}"
					   + "&perPageNum=${scri.perPageNum}"
					   + "&searchType=${scri.searchType}"
					   + "&keyword=${scri.keyword}";
			})
			
			$(".update_btn").on("click", function(){
				if(fn_valiChk()){
					return false;
				}
				formObj.attr("action", "/itboard/update.do");
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
		function fn_addFile(){
			var fileIndex = 1;
			//$("#fileIndex").append("<div><input type='file' style='float:left;' name='file_"+(fileIndex++)+"'>"+"<button type='button' style='float:right;' id='fileAddBtn'>"+"추가"+"</button></div>");
			$(".fileAdd_btn").on("click", function(){
				$("#fileIndex").append("<div><input type='file' style='float:left;' name='file_"+(fileIndex++)+"'>"+"</button>"+"<button type='button' style='float:right;' id='fileDelBtn'>"+"삭제"+"</button></div>");
			});
			$(document).on("click","#fileDelBtn", function(){
				$(this).parent().remove();
			});
		}
 		var fileNoArry = new Array();
 		var fileNameArry = new Array();
 		function fn_del(value, name){
 			fileNoArry.push(value);
 			fileNameArry.push(name);
 			$("#fileNoDel").attr("value", fileNoArry);
 			$("#fileNameDel").attr("value", fileNameArry);
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
			<h1>IT자료실</h1>
			<hr>
				<form name="updateForm" role="form" method="post" action="/itboard/update.do" enctype="multipart/form-data">
					<input type="hidden" name="bno" value="${update.bno}" readonly="readonly"/>
					<input type="hidden" id="page" name="page" value="${scri.page}"> 
					<input type="hidden" id="perPageNum" name="perPageNum" value="${scri.perPageNum}"> 
					<input type="hidden" id="searchType" name="searchType" value="${scri.searchType}"> 
					<input type="hidden" id="keyword" name="keyword" value="${scri.keyword}"> 
					<input type="hidden" id="fileNoDel" name="fileNoDel[]" value=""> 
					<input type="hidden" id="fileNameDel" name="fileNameDel[]" value=""> 
					
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
					<div id="fileIndex">
						<c:forEach var="file" items="${file}" varStatus="var">
							<div>
								<input type="hidden" id="FILE_NO" name="FILE_NO_${var.index}" value="${file.FILE_NO }">
								<input type="hidden" id="FILE_NAME" name="FILE_NAME" value="FILE_NO_${var.index}">
								<a href="#" id="fileName" onclick="return false;">${file.ORG_FILE_NAME}</a>(${file.FILE_SIZE}kb)
								<button id="fileDel" onclick="fn_del('${file.FILE_NO}','FILE_NO_${var.index}');" type="button">삭제</button><br>
							</div>
						</c:forEach>
					</div>
				
					<div>
						<button type="button" class="update_btn btn btn-primary">저장</button>
						<button type="button" class="cancel_btn btn btn-danger">취소</button>
						<button type="button" class="fileAdd_btn btn btn-warning">파일추가</button>
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