<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix ="c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix ="fn" uri = "http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://fonts.googleapis.com/css2?family=Roboto:wght@900&family=Source+Sans+Pro:wght@600&display=swap" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="style.css" />
<script src="index.js" defer></script>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>

<script
src="https://kit.fontawesome.com/fbff03f786.js" crossorigin="anonymous"></script>
<title>구디 커뮤니티</title>
</head>
<body>
	<div id="container">
        <!-- Main Navbar -->
        <nav class="navbar" id="navbar">
            <div class="navbar__logo">
                <a href="index.jsp">
                    <img src="image/logo.PNG" alt="로고">
                    <h1>구디 커뮤니티</h1>
                </a>
            </div>
            <div class="search-input">
                <form class="searchbar" action="#" method="get">
                    <input
                      class="search"
                      type="text"
                      name="search"
                    />
                    <button><i class="fas fa-search"></i></button>
                </form>
            </div>	
            
            <button class="write" onclick="location.href='writeView'">
                글쓰기
            </button>
    
            <ul class="navbar__menu">
                <h2>전체 게시판</h2>
                <li>
                    <a href="boardList?mboard_no=1" id="free" >
                        <span>자유 게시판</span>
                    </a>
                </li>
                <li>
                    <a href="boardList?mboard_no=2" id="edu">
                        <span>학습 게시판</span>
                    </a>
                </li>
                <li>
                    <a href="boardList?mboard_no=3" id="secret">
                        <span>익명 게시판</span>
                    </a>
                </li>
                <li>
                    <a href="D130_오늘점심.html" id="lunch">
                        <span>오늘 점심 뭐먹지?</span>
                    </a>
                </li>
            </ul>
            
            <div class="top-util">
            	<div class="boxx">
                    <jsp:include page="upmy1.jsp"/>
		        </div>
                <div class="inner">
                    <button type="button" class="profile">
                        <div id="profile_img" class="profile-img"></div>
                    </button>
                    <button id="login" class="login" onclick="location.href='login.jsp'">로그인</button>
                </div>
            </div>
        </nav>
        
        <!-- Main Contents -->
        <div id="contents">
            <div class="section-top">
                <div class="img-swipe">
                    <!-- 이미지 필요!!! -->
                    	메인 이미지 영역입니다.
                </div>
                <div class="today-memo">
                	<form action="#" method="post">
	                	<table>
	                	 	<tr>
	                	 		<h1 style= "margin-left:60px;">오늘의 메모</h1>
	                	 	</tr>
							<tr>
								<th>제목</th>
								<td><input type="text" name="" id="" value=""/></td>
							</tr>
							<tr>
								<th>내용</th>
								<td><textarea name="" id="" cols="30" rows="15"></textarea></td>
							</tr>
							<tr>
								<td colspan="2" style= "text-align:center;"><input type="button" value="저장" id="" /></td>
							</tr>
						</table>
					</form>
                </div>
            </div>

            <div class="section-bot">
                <div class="board-swipe">
                    <ul>
                        <li>
                            <button class="key-color">전체 게시판</button>
                        </li>
                        <li>
                            <button>공지사항</button>
                        </li>
                        <li>
                            <button>인기 글</button>
                        </li>
                    </ul>
                </div>
                <div class="list-box">
                    <!-- 게시글 영역 -->
                    
                    <div class="list-paging">
                      <button id="1" class="on">1</button>
                      <button id="2">2</button>
                      <button id="3">3</button>
                      <button id="4">4</button>
                      <button id="5">5</button>
                      <button id="next">next</button>
                    </div>
                </div>
            </div>
        </div>  
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

var writeMsg = "${writeMsg}";
if(writeMsg != ""){
	alert(writeMsg);
	location.href = "login.jsp";
}

var loginId = "${sessionScope.id}";
// 세션에 저장된 경우 로그인버튼을 비활성화하기
console.log(loginId);

if(loginId!="admin"){}else{location.href="admin_temp.jsp";}

var profile_img = $("#profile_img");
var loginbtn = $("#login");

if(loginId==""){
	profile_img.css({"display":"none"});	
}else{    
    loginbtn.css({"display":"none"});
    profile_img.css({"display":"block"});
}



function write(){
	if("${sessionScope.id}" == null ){
		alert("로그인이 필요합니다.")
	}else{
		location.href="write.jsp";
	}
}

$("#ct_send").click(function(){
	
	var $writer = $("input[name='writer']");
	var $subject = $("input[name='writer']");
	var $c_email = $("input[name='writer']");
	var $content = $("input[name='writer']");
	
	
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
        	
        },
        error: function(error){
           console.log(error);
        }
     });
	
})

</script>
</html>