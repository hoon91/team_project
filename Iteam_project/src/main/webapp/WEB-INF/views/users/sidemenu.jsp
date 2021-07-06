<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style>
*{
	margin:0;
	padding:0;
	box-sizing:border-box;
	
}
.lab2 {
	display: flex;
	align-items: center;
	position: relative;
	top: 30px;
	left: 30px;
	transition: .5s;
}

.button-in-label {
	font-size: 3rem;
	margin-right: 10px;
}

.sidemenu-button {
	border: none;
	outline: none;
	background-color: transparent;
	font-size: 3rem;
}
section{
	background-color:#34495e;
	color:#fff;
	width:250px;
	padding-top:30px;
	padding-left: 30px;
	position: absolute;
	top: 0;
	left:-250px;
	bottom: 0;
	transition: 1s ease;
	
}
.sidelist{
	padding-left:45px;
}
.slist{
	margin:30px 0;
}
.slist:nth-child(1){
	magin-top: 50px;
}

</style>

<script>
	
</script>
</head>
<body>
	<main>
	<div class = "lab2"><span class="button-in-label">menu</span> <input
		type="button" value="sidemenubutton" class="sidemenu-button">
	</div>
	<section>
		<h1>마이페이지</h1>
		<ul class="sidelist">
			<li class="slist"><a href="https://www.naver.com">네이버</a><br></li>

		</ul>
	</section>
	</main>
	<script>
		const sidebtn = document.querySelector('.sidemenu-button');
		const section = document.querySelector('section');
		const label = document.querySelector('lab2');
		
		sidebtn.addEventListener('click',function(){
			section.style.left =0;
			label.style.opacity =0;
			
		})
		
		sidebtn.addEventListener('blur',function(){
			section.style.left ='-250px';
			label.style.opacity =1;
			
		})
	</script>
</body>
</html>