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
	<h2> ID비밀번호 찾는 섹션</h2>		
		<div>
			이메일을 입력해주세요
			<input type="text" id="email"> <input type="button" id="findid" value="ID 찾기"/>
		</div>	
	</div>

</body>	

<script>

	$("findid").click(function(){
		var email = $("email").val();
		console.log(email); // 잘 들어오는 지 확인: 1차
	});
	/*
		$.ajax({
			type: "get",
			url: "findid",
			data: {"email":email},
			dataType: "JSON",
			success: function(data){
				console.log(data);
				var id = "${id}";
				alert("찾으신아이디는!"+id);
			},
			error: function(e){
				console.log(e);
			}			
		});
	});
	*/
</script>
</html>