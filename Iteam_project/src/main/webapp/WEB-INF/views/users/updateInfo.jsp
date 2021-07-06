<%@page import="java.util.Calendar"%>
<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<title>회원정보변경</title>
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
				<li><a href="/readInfo.do?id=${user.id}">회원정보수정</a></li>
				<li><a href="changePass.do?id=${user.id}">비밀번호변경</a></li>
				<li><a href="/deleteUser.do?id=${user.id}">회원탈퇴</a></li>
			</ul>
			</aside>	
	<div class="container_box">
			<hr>
			<h3>${user.id}님의 정보</h3>
			<hr>
		<form action="/updateInfo.do" name="updateInfoForm" method="post">
		<table class="type03">
  				<tr>
    				<th scope="row">아이디</th>
    				<td><input name="id" value="${userInfo.id}" readonly></td>
  				</tr>
  				<tr>
    				<th scope="row">이름</th>
    				<td><input name="name" value="${userInfo.name}"></td>
  				</tr>
  				<tr>
    				<th scope="row">연락처</th>
    				<td><input id="phone" name="phone" value="${userInfo.phone}"></td>
  				</tr>
  				<tr>
    				<th scope="row">사는지역</th>
    				<td><input name="city" id="city" value="${userInfo.city}"></td>
  				</tr>
 				<tr>
 					<th scope="row">출생년도</th>
    				<%
						Calendar cal = Calendar.getInstance();
						int bornyear = cal.get(Calendar.YEAR);
					%>
					<c:set var="borny" value='<%=bornyear %>'/>
    				<td><input name="age" id="age" value="${borny-userInfo.age+1 }"></td>
  				</tr>
  				<tr>
    				<th scope="row">우편번호</th>
    				<td><input id="postcode" name="postcode" value="${userInfo.postcode}">
    				<input type="button" onclick="execDaumPostcode()" value="우편번호 찾기"><br></td>
  				</tr>
  				<tr>
    				<th scope="row">도로명주소</th>
    				<td><input id="roadAddress" name="roadAddress" value="${userInfo.roadAddress}"></td>
  				</tr>
  				<tr>
    				<th scope="row">지번주소</th>
    				<td><input id="jibunAddress" name="jubunAddress" value="${userInfo.jubunAddress}"></td>
  				</tr>
  				<tr>
    				<th scope="row">상세주소</th>
    				<td><input  id="detailAddress" name="detailAddress" value="${userInfo.detailAddress}"></td>
  				</tr>
  				<tr>
    				<th scope="row">참고항목</th>
    				<td><input id="extraAddress" name="extraAddress" value="${userInfo.extraAddress}"></td>
  				</tr>
				<tr>
					<td colspan="2" align="center">
					<button type="submit" class="btn btn-default" onclick="location.href='/readInfo.do?id=${user.id}'">수정완료</button>
					<button type="button" class="btn btn-default" onclick="location.href='/readInfo.do?id=${user.id}'">마이페이지로</button>
					</td>
				</tr>
			</table>
		</form>
	</div>
</div>
</div>	      
		      

<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>

    //본 예제에서는 도로명 주소 표기 방식에 대한 법령에 따라, 내려오는 데이터를 조합하여 올바른 주소를 구성하는 방법을 설명합니다.
    function execDaumPostcode() {
        new daum.Postcode({
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 도로명 주소의 노출 규칙에 따라 주소를 표시한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var roadAddr = data.roadAddress; // 도로명 주소 변수
                var extraRoadAddr = ''; // 참고 항목 변수

                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                    extraRoadAddr += data.bname;
                }
                // 건물명이 있고, 공동주택일 경우 추가한다.
                if(data.buildingName !== '' && data.apartment === 'Y'){
                   extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
                // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                if(extraRoadAddr !== ''){
                    extraRoadAddr = ' (' + extraRoadAddr + ')';
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('postcode').value = data.zonecode;
                document.getElementById("roadAddress").value = roadAddr;
                document.getElementById("jibunAddress").value = data.jibunAddress;
                
                // 참고항목 문자열이 있을 경우 해당 필드에 넣는다.
                if(roadAddr !== ''){
                    document.getElementById("extraAddress").value = extraRoadAddr;
                } else {
                    document.getElementById("extraAddress").value = '';
                }

                var guideTextBox = document.getElementById("guide");
                // 사용자가 '선택 안함'을 클릭한 경우, 예상 주소라는 표시를 해준다.
                if(data.autoRoadAddress) {
                    var expRoadAddr = data.autoRoadAddress + extraRoadAddr;
                    guideTextBox.innerHTML = '(예상 도로명 주소 : ' + expRoadAddr + ')';
                    guideTextBox.style.display = 'block';

                } else if(data.autoJibunAddress) {
                    var expJibunAddr = data.autoJibunAddress;
                    guideTextBox.innerHTML = '(예상 지번 주소 : ' + expJibunAddr + ')';
                    guideTextBox.style.display = 'block';
                } else {
                    guideTextBox.innerHTML = '';
                    guideTextBox.style.display = 'none';
                }
            }
        }).open();
    }
</script>	
	
</body>
</html>