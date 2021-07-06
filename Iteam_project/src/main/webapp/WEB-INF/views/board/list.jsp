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

<title>자유게시판</title>
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
			<h1>자유게시판</h1>
				<hr>
					<div id="nav">
        				<%@ include file="../inc/nav.jsp" %>
    				</div>
				<hr>
	
				<form role="form" method="get">
				<button type="button" class="btn btn-default" style="float: right;" onclick="location.href='/board/writeView.do'">글작성</button>
					<table class="table table-hover">
						<thead>
							<tr><th>번호</th><th>제목</th><th>작성자</th><th>등록일</th><th>조회수</th><th>추천수</th></tr>
						</thead>
						
						<c:forEach items="${list}" var = "list">
							<tr>
								<td>${list.bno}</td>
								<td>
									<a style="color: black;" href="/board/readView.do?bno=${list.bno}&page=${scri.page}&perPageNum=${scri.perPageNum}&searchType=${scri.searchType}&keyword=${scri.keyword}">
									<c:out value="${list.title}"/>
										<c:if test="${list.reply_count != 0}">
											<small><b style="color: red;">[&nbsp;<c:out value="${list.reply_count}"/>&nbsp;]</b></small>
										</c:if>
									</a>
								</td>
								<td><c:out value="${list.writer}" /></td>
								<td><fmt:formatDate value="${list.regdate}" pattern="yyyy-MM-dd"/></td>
								<td><c:out value="${list.cnt}" /></td>
								<td><c:out value="${list.recommend}" /></td>
							</tr>
						</c:forEach>
						
					</table>
					<div class="search row">
						<div class="col-xs-2 col-sm-2">
							<select name="searchType" class="form-control">
								<option value="n"<c:out value="${scri.searchType == null ? 'selected' : ''}"/>>-----</option>
								<option value="t"<c:out value="${scri.searchType eq 't' ? 'selected' : ''}"/>>제목</option>
								<option value="c"<c:out value="${scri.searchType eq 'c' ? 'selected' : ''}"/>>내용</option>
								<option value="w"<c:out value="${scri.searchType eq 'w' ? 'selected' : ''}"/>>작성자</option>
								<option value="tc"<c:out value="${scri.searchType eq 'tc' ? 'selected' : ''}"/>>제목+내용</option>
							</select>
						</div>
						 
						<div class="col-xs-10 col-sm-10">
							<div class="input-group">
								<input type="text" name="keyword" id="keywordInput" value="${scri.keyword}" class="form-control"/>
								<span class="input-group-btn">
									<button id="searchBtn" type="button" class="btn btn-default">검색</button> 	
								</span>
							</div>
						</div>
						 
						<script>
							 $(function(){
								 $('#searchBtn').click(function() {
									 self.location = "/board/list.do" + '${pageMaker.makeQuery(1)}' + "&searchType=" + $("select option:selected").val() + "&keyword=" + encodeURIComponent($('#keywordInput').val());
								 });
							 });   
						</script>
					</div>
					<div class="col-md-offset-3">
						<ul class="pagination">
							<c:if test="${pageMaker.prev}">
								<li><a href="/board/list.do${pageMaker.makeSearch(pageMaker.startPage - 1)}">이전</a></li>
							</c:if> 
							
							<c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}" var="idx">
								<li <c:out value="${pageMaker.cri.page == idx ? 'class=info' : ''}" />>
								<a href="/board/list.do${pageMaker.makeSearch(idx)}">${idx}</a></li>
							</c:forEach>
							
							<c:if test="${pageMaker.next && pageMaker.endPage > 0}">
								<li><a href="/board/list.do${pageMaker.makeSearch(pageMaker.endPage + 1)}">다음</a></li>
							</c:if> 
						</ul>
					</div>
				</form>	
			</div>
		</div>	
		
	<div class="footer">
		<%@ include file="../inc/footer.jsp" %>
	</div>	
	
</body>
</html>