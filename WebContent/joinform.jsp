<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix ="c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix ="fn" uri = "http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>구디 커뮤니티</title>
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
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
	
	.joinbox{
		position: absolute;
		left: 30%;
		top: 30%;
	}
	
	table, td,tr {
	border: 1px solid black;
	border-collapse: collapse;
	text-align: center;
	padding: 5px 10px;
	}
	
	
	</style>
</head>
	<body>
         <div class="navbar__logo">
                <a href="index.jsp">
                    <img src="image/logo.PNG" alt="로고">
                 </a>
            </div>       
            
            <div class="joinbox">
            <h3> 회원 가입 </h3>
	<table>
		<tr>
			<td> 아이디* </td>
			<td> <input type="text" name="id"/> <input type="button" id="idChck" value="ID중복확인"/></td>
		</tr>
		<tr>
			<td> 비밀번호* </td>
			<td> <input type="password" name="pw"/></td>
		</tr>
		<tr>
			<td> 비밀번호확인* </td>
			<td> <input type="password" name="pwChck"/></td>
		</tr>

		<tr>
			<td> 닉네임 </td>
			<td> <input type="text" name="nickName"/> <input type="button" id="nickChck" value="중복확인"/></td>
		</tr>

		<tr>
			<td> 이름 </td>
			<td> <input type="text" name="name"></td>
		</tr>


		<tr>
			<td> 이메일 </td>
			<td> <input type="email" name="u_email"></td>
		</tr>
		<tr>
			<td colspan="2"> <input type = "button" id="join" value="회원가입"> </td>
		</tr>	

	</table>

</div>
	</body>

<script>
	var overChk = false;
	var overChknic = false;
	
	$("#idChck").click(function(){
		if($("input[name='id']").val() == ""){
			alert("아이디를 입력해주세요.");
		}else{
			var id = $("input[name='id']").val();
			$.ajax({
				type: "get",
				url: "overlay",
				data: {"id":id},
				dataType: "JSON",
				success: function(data){
					console.log(data);
					if(data.overlay){
						alert("이미 사용중인 ID 입니다.");
						$("input[name='id']").val("");
					}else{
						alert("사용 가능한 ID 입니다");
						overChk = true;
					}
				},
				error: function(e){
					console.log(e);
				}			
			});
		}
	});
	
	$("#nickChck").click(function(){
		if($("input[name='nickName']").val() == ""){
			alert("닉네임을 입력해주세요.");
		}else{
			var nickname = $("input[name='nickName']").val(); // id값 가져오기
			console.log(nickname); // 잘 들어오는 지 확인: 1차
			
			$.ajax({
				type: "get",
				url: "overlaynick",
				data: {"nickname":nickname},
				dataType: "JSON",
				success: function(data){		
					if(data.overlay){
						alert("이미 사용중인 닉네임입니다.");
						$("input[name='nickName']").val("");
					}else{
						alert("사용 가능한 닉네임입니다");
						overChknic = true;
					}
				},
				error: function(e){
					console.log(e);
				}			
			});
		}
	});
	
	
	$("#join").click(function(){
		var $id = $("input[name='id']");
		var $pw = $("input[name='pw']");
		var $pwChck = $("input[name='pwChck']");
		var $name = $("input[name='name']");
		var $nickName = $("input[name='nickName']");
		var $email = $("input[name='u_email']");
		
		console.log($id);
		console.log($pw);
		console.log($name);
		
		if($pw.val()==$pwChck.val()){}else{
			alert("비밀번호가 일치하지 않습니다.")
			$("input[name='pw]").focus();
		};		
		
		
		if(overChk&&overChknic){			
			if($id.val()==""){
				alert("아이디를 확인해주세요");
				$id.focus();
			}else if($pw.val()==""){
				alert("비밀번호를 입력해주세요");
				$pw.focus();
			}else if($pwChck.val()==""){
				alert("비밀번호를 확인해주세요");
				$pwChck.focus();
			}else if($nickName.val()==""){
				alert("닉네임을 입력해주세요");
			}else if($name.val()==""){
				alert("이름을 입력해주세요");
				$name.focus();
			}else if($email.val()==""){
				alert("이메일을 입력해주세요");
				$email.focus();
			}else{// 모든 항목을 입력했을 경우
				console.log("서버로 전송");
				
				var param = {};
				param.id = $id.val();
				param.pw = $pw.val();
				param.name = $name.val();
				param.nickName = $nickName.val();
				param.email = $email.val();
				
				console.log('param:' , param); // 콘솔에 +찍으면 문자열로 바뀜
				
				$.ajax({
					type: "post",
					url: "join",
					data: param,
					dataType: "JSON",
					success: function(data){
						console.log(data.join);

						if(data.join){
							alert("회원가입에 성공하였습니다.");
							location.href="index.jsp";
						}else{
							alert("회원가입에 실패하였습니다.");
							location.href="joinform.jsp";
						}
					},
					error: function(error){
						console.log(error);
					}
				});
			}			
			
		}else{
			alert("중복체크를 확인해 주세요");
			$("input[name='id']").focus();
		}
	});
	
	
</script>
</html>