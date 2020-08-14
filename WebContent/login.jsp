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
	
	.loginbox{
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
                <a href="main">
                    <img src="image/logo.PNG" alt="로고">
                 </a>
            </div>            


	<div class= "loginbox">
	<h2> 로그인 해주세요</h2>
			<form action="login" method="post">
				<table>
					<tr>
						<td>ID</td>
						<td><input type="text" name="id"/></td>				
						<td rowspan="3"><input type="submit" value="로그인"/></td>						
					</tr>
					<tr>
						<td>PW</td>
						<td><input type="password" name="pw"/></td>										
					</tr>	
					<tr>
						<td colspan="3">
							<a href="searchidpass.jsp">ID/비밀번호 찾기</a>
							<input type="button" id="join" value="회원가입" onclick="location.href='joinform.jsp'"/> </td>			
						</td>										
					</tr>			
				
				</table>
			</form>
		</div>

</body>	

<script>
	
</script>
</html>