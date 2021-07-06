<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<style type="text/css">
	li {list-style: none; display:inline; padding: 6px;}
	p {text-align: center; font-size:20px; color:#369;}
</style>

<ul>
	<li>
		<c:if test="${user != null}">
			<p>${user.id}님 안녕하세요. 당신의 등급은 `${user.grade}` 입니다. </p>
		</c:if>
		<c:if test="${user.isreport != null}">
			<p>${user.id}님은 현재 신고 접수가 되어 진행중입니다.</p>
		</c:if>
	</li>
</ul>