<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix ="c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix ="fn" uri = "http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>구디 커뮤니티</title>
	<script src = "https://code.jquery.com/jquery-3.5.1.min.js"> </script>
	<link rel="stylesheet" href="style.css">
	<script src="index.js" defer></script>
	<script src="https://kit.fontawesome.com/fbff03f786.js" crossorigin="anonymous"></script>
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
							<input type="button" id="join" value="회원가입" onclick="location.href='joinform.jsp'"/>
						</td>										
					</tr>			
				
				</table>
			</form>
		</div>

    <div class="helpIcon">
        <i class="far fa-comment-dots"></i>   
    </div>
  
    <div class="helpIcon__content">
        <div class="helpIcon__title">
            <br><br>
            <p>무엇을 도와드릴까요?</p>
            <p>문의 주신 내용은 확인 후 답변 드리겠습니다.</p>
        </div>
        <div class="helpIcon__input">
            <form action="contactWrite" method="post">
            <br><br>
            <input type="text" name="writer" placeholder="   작성자"> 
            <input type="text" name="subject" placeholder="   제목">
            <input type="text" name="c_email" placeholder="   이메일">
            <textarea type="text" name="content" placeholder="     문의 내용"></textarea>
            <br><br>
            <button id="ct_send">보내기</button>
            </form>
        </div>
    </div>


</body>	

<script>
//문의사항 보내기
$("#ct_send").click(function(){
	
	var $writer = $("input[name='writer']");
	var $subject = $("input[name='subject']");
	var $c_email = $("input[name='c_email']");
	var $content = $("input[name='content']");
	
	console.log($write,$subject,$c_email,$content);
	
	var param = {};
	
	param.writer = $("input[name='writer']").val();
	param.subject = $("input[name='subject']").val();
	param.c_email = $("input[name='c_email']").val();
	param.content = $("input[name='content']").val();
	
	$.ajax({
        type: "post",
        url: "contactWrite",
        data: param,
        dataType: "JSON",
        success: function(data){
        	console.log(data.contactmsg);
        	alert("msg");
        },
        error: function(error){
           console.log(error);
        }
     }); // 쓰기는 되는데 왜 원래 화면으로 안돌아오는 걸까?

});
</script>
</html>