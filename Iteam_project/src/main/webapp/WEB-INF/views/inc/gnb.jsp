<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="text-right" >
<a id="create-kakao-link-btn" href="javascript:;" style="position:fixed; top:100px; left:6px; width:80;">
  <img src="https://developers.kakao.com/assets/img/about/logos/kakaolink/kakaolink_btn_medium.png"/>
</a>
         <c:choose>
            <c:when test="${user != null}"> 
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
               <a style="color: black;" onclick="location.href='/mypage.do?id=${user.id}'">내정보보기</a>
               <a style="color: black;" onclick="location.href='/logout.do'">로그아웃</a>
               <span class="login_log" style="border-bottom: 1px solid black; color:black;">${user.id} 님, 환영합니다.</span>
               
            </c:when>
            <c:when test="${admin.id eq 'admin'}">
               <a onclick="location.href='/admin.do'">관리자페이지</a>
               <a onclick="location.href='/logout.do'">로그아웃</a>
               &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
               <span class="login_log" style="border-bottom: 1px solid black; color:black;">관리자님, 환영합니다.</span>
            </c:when>
            <c:otherwise>
               <a onclick="location.href='/login.do'">로그인</a>
               <a onclick="location.href='/membership.do'">회원가입</a>
            </c:otherwise>
         </c:choose>         
      </div>

<script src="https://developers.kakao.com/sdk/js/kakao.js"></script>
<script type="text/javascript">
  //
  Kakao.init('f8b33c4b6f179337fc7d3050e3156251');
  Kakao.Link.createDefaultButton({
    container: '#create-kakao-link-btn',
    objectType: 'feed',
    content: {
      title: '프로젝트 팀원',
      description: '#프로젝트 #IT #Java #Spring #Jsp #Python',
      imageUrl:
        'https://postfiles.pstatic.net/MjAyMTA2MjFfMTI1/MDAxNjI0MjYzMDU2MjE1.-D9-dKw0Hr6YOzT1FDjKuv3_C2K-P2CR8o9d6-7zVncg.QUxpLc6vMPtvN2GIv7SjQa9xlqnBayqsGP50TzS1H-Mg.PNG.alcodsha/KakaoTalk_20210621_171012583.png?type=w773',
      link: {
        mobileWebUrl: 'https://okky.kr/article/970247',
        webUrl: 'https://okky.kr/article/970247',
      },
    },
    social: {
      likeCount: 286,
      commentCount: 45,
      sharedCount: 845,
    },
    buttons: [
      {
        title: '웹으로 보기',
        link: {
          mobileWebUrl: 'https://okky.kr/article/970247',
          webUrl: 'https://okky.kr/article/970247',
        },
      },
      {
        title: '앱으로 보기',
        link: {
          mobileWebUrl: 'https://okky.kr/article/970247',
          webUrl: 'https://okky.kr/article/970247',
        },
      },
    ],
  })
</script>