<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
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
<title>회원가입</title>
</head>
<script type="text/javascript">
var idck =0;
$(document).ready(function() {
	
	$("#submit").on("click",function(){
		if($("#id").val()==""){
			alert("아이디를 입력해주세요.");
			$("#id").focus();
			return false;
		}
		var pw=$("#password").val();
		var num = pw.search(/[0-9]/g);
		var eng = pw.search(/[a-z]/ig);
		
		if(pw.length<8 || pw.length>20){
			alert("비밀번호는 8자리~20자리 이내로 입력해주세요.");
			return false;
		}else if(pw.search(/\s/)!=-1){
			alert("비밀번호는 공백 없이 입력해주세요.");
			return false;
		}else if(num <0 || eng <0){
			alert("비밀번호는 영문, 숫자를 혼합하여 입력해주세요.");
			return false;
		}
		var pw2=$("#password2").val();
		if(pw!=pw2){
			alert("비밀번호가 다릅니다.")
			$("#password2").focus();
			return false;
		}

		if($("#name").val()==""){
			alert("이름을 입력해주세요.");
			$("#name").focus();
			return false;
		}
		if($("#phone").val()==""){
			alert("핸드폰번호를 입력해주세요.");
			$("#phone").focus();
			return false;
		}
		if($("#city").val()==""){
			alert("사는지역을 입력해주세요.");
			$("#city").focus();
			return false;
		}
		if($("#age").val()==""){
			alert("출생년도를 입력해주세요.");
			$("#age").focus();
			return false;
		}
		alert("ITEAM 회원가입에 완료되셨습니다.");
	})
	
	$("#idck").on("click",function(){
		
		var id = $("#id").val();
		
		$.ajax({
            async: true,
            type : 'POST',
            data : id,
            url : "idChk.do",
            dataType : "json",
            contentType: "application/json; charset=UTF-8",
            success : function(data){
            	console.log(JSON.stringify(data));
            	if(data.cnt>0){
            		alert("아이디가 존재합니다. 다른 아이디를 입력해주세요.");
            		$("#id").focus();
            	}else{
            		alert("사용가능한 아이디입니다.");
            		idck = 1;
            	}
            },
            error : function(error){
            	console.log(JSON.stringify(error));
            	alert("error :"+error);
            }
		})
		
	})
})

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
         <h1>회원가입</h1>            
		<hr>
		<form action="insertMember.do" method="post">
			<table class="type03">
  				<tr>
    				<th scope="row">아이디</th>
    				<td><input type="text" name="id" id="id"/>
        			<input type="button" value="중복 확인" id="idck"><br></td>
  				</tr>
  				<tr>
    				<th scope="row">비밀번호</th>
    				<td><input type="password" name="password" id="password" placeholder="영문,숫자 혼합 8자이상 12자 이하" /></td>
  				</tr>
  				<tr>
  				  	<th scope="row">비밀번호 확인</th>
    				<td><input type="password" name="password2" id="password2" placeholder="영문,숫자 혼합 8자이상 12자 이하" /></td>
  				</tr>
  				<tr>
    				<th scope="row">이름</th>
    				<td><input type="text" name="name" id="name" /></td>
  				</tr>
  				<tr>
    				<th scope="row">연락처</th>
    				<td><input type="text" name="phone" id="phone" placeholder="특수문자를 제외하고 숫자만 입력하시오."/></td>
  				</tr>
  				<tr>
    				<th scope="row">사는지역</th>
    				<td><input type="text" name="city" id="city" placeholder="시까지만 입력하시오."/></td>
  				</tr>
 				<tr>
    				<th scope="row">출생년도</th>
    				<td><input type="number" name="age" id="age" placeholder="출생년도 4자리를 입력해주세요." /></td>
  				</tr>
  				<tr>
    				<th scope="row">우편번호</th>
    				<td><input id="postcode" name="postcode" placeholder="우편번호">
					<input type="button" onclick="execDaumPostcode()" value="우편번호 찾기"><br></td>
  				</tr>
  				<tr>
    				<th scope="row">도로명주소</th>
    				<td><input id="roadAddress" name="roadAddress" placeholder="도로명주소"></td>
  				</tr>
  				<tr>
    				<th scope="row">지번주소</th>
    				<td><input id="jibunAddress" name="jubunAddress" placeholder="지번주소"></td>
  				</tr>
  				<tr>
    				<th scope="row">상세주소</th>
    				<td><input id="detailAddress" name="detailAddress" placeholder="상세주소"></td>
  				</tr>
  				<tr>
    				<th scope="row">참고항목</th>
    				<td><input id="extraAddress" name="extraAddress" placeholder="참고항목"></td>
  				</tr>
			</table>
			<br>
	
				<div align="center"> 
					<button type="submit" class="btn btn-primary" id="submit">가입하기</button>
					<button type="reset" class="btn btn-danger">다시입력</button>	
				</div>
		</form>
		<hr>
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