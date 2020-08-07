<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix ="c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix ="fn" uri = "http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>구디 커뮤니티</title>
	<script src = "https://code.jquery.com/jquery-3.5.1.min.js"> </script>
	<style>
	.navbar__logo {
		position: absolute;
		left: 45px;
		top: 50px;
	}
	
	.navbar__logo img {
	    width: 150px;
	    height: 150px;
	}
	
	.searchbox{
		position: absolute;
		left: 30%;
		top: 30%;
	}
	
	table, tr, td{
		text-align:center;
		}
	
	</style>
</head>

<body>
    <div class="navbar__logo">
            <a href="index.jsp">
                <img src="image/logo.PNG" alt="로고">
             </a>
    </div>           

	<div class= "searchbox">
		<h2> ID 찾기</h2>		
			<div>
				이메일을 입력해주세요
				<input type="email" id="email"> <button id="findid" onclick="findid()">ID 찾기</button>			
			</div>
		<h2> 비밀번호 찾기</h2>
			<div>
				<p>이메일을 입력해주세요. 입력하신 이메일로 임시 비밀번호를 발송해드립니다.</p>						
				<p><td>이메일 : <input type="email" name="u_email"> <button id="auth">임시비밀번호 발송</button></td></p>				
			</div>			
	</div>

</body>	

<script>
	$("#auth").click(function(){
		if($("input[name='u_email']").val() == ""){
			alert("이메일을 입력해주세요");
		}else{
			var $u_email = $("input[name='u_email']");
			var param = {};
			param.email = $u_email.val();
			
			$.ajax({
				type: "post",
				url: "findpw",
				data: param,
				dataType: "JSON",
				success: function(data){
					alert(data.msg);
				},
				error: function(error){
					console.log(error);
				}
			});
		}					
	});

	function findid(){
		var email = $("#email").val();
		console.log(email); // 잘 들어오는 지 확인: 1차
		
		$.ajax({
			type: "get",
			url: "findid",
			data: {"email":email},
			dataType: "JSON",
			success: function(data){
				console.log(data.searchedid);
				var id = data.searchedid;
				console.log(id);
				if(id!==""){alert("회원님의 아이디는: "+id+"입니다.");}
				else{alert("입력하신 이메일로 가입된 정보가 없습니다.");}				
			},
			error: function(e){
				console.log(e);
			}			
		});		
	}

	
</script>
</html>