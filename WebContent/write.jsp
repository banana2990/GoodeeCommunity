<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

	<c:choose>	
		<c:when test="${sessionScope.id eq null}">
			<jsp:forward page="/login.jsp"/>
		</c:when>
	</c:choose>
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://fonts.googleapis.com/css2?family=Roboto:wght@900&family=Source+Sans+Pro:wght@600&display=swap" rel="stylesheet">
<link rel="stylesheet" href="style.css">
<link rel="stylesheet" href="write&update.css">
<script
src="https://kit.fontawesome.com/fbff03f786.js" crossorigin="anonymous">
</script>

<title>구디 커뮤니티</title>
</head>
<body>
	<div id="container">
        <!-- Main Navbar -->
        <nav class="navbar" id="navbar">
            <div class="navbar__logo">
                <a href="main">
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
            
            <button class="write">
                글쓰기
            </button>
    
            <ul class="navbar__menu">
                <h2>전체 게시판</h2>
                <li>
                    <a href="boardList.jsp">
                        <span>자유 게시판</span>
                    </a>
                </li>
                <li>
                    <a href="boardList.jsp">
                        <span>학습 게시판</span>
                    </a>
                </li>
                <li>
                    <a href="boardList.jsp">
                        <span>익명 게시판</span>
                    </a>
                </li>
                <li>
                    <a href="boardList.jsp">
                        <span>오늘 점심 뭐먹지?</span>
                    </a>
                </li>
            </ul>
            
            <div class="top-util">
                <div class="inner">
                    <button type="button" class="profile">
                        <div class="profile-img"></div>
                    </button>
                    <button class="login">로그인</button>
                </div>
            </div>
        </nav>
        
        <!-- Main Contents -->
        <div id="contents">
            <div class="section-top">
                <h1>글쓰기</h1>	
                	<div class="select-box">                        
                            <form class="searchbar" action="write" method="post">
                                <select style="width: 300px" name="mboard_no">
                                    <option value="1">자유게시판</option>
                                    <option value="2">학습게시판</option>
                                    <option value="3">익명게시판</option>
                            		<!--  세션 스코프 확인했을 때 관리자면 공지사항 option 추가 -->
                                </select>
                                <input type="text" name="subject" class="tit-input" placeholder="제목" maxlength="50" >			
				                <input type="text" class="link-input" placeholder="링크" maxlength="50">			
				                <div class="note-editing-area">
				                       <textarea name="content" class="textarea"></textarea>
				                </div>  
				                <div class="btn-box">					
					                <button class="register">등록</button>	           							                  
					                <input type="button" class="cancle" onclick="cancle()" value="취소">             		
            					</div> 
            				</form>                                                     
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

        	alert("contactmsg");
        },
        error: function(error){
           console.log(error);
        }
     }); // 쓰기는 되는데 왜 원래 화면으로 안돌아오는 걸까?

});


	var loginId = "${sessionScope.id}";
	if(loginId == null){
		alert("로그인이 필요합니다. 로그인 페이지로 이동하시겠습니까? ");
		location.href("login.jsp");
	}
	
	/*
	if(loginId="admin"){
		$('select[name="mboard_no"]').append("<option value='4'>공지</option>");
	}
	*/
	
	function cancle(){
		if(confirm("글 작성을 취소하시겠습니까 ?") == true){
			window.history.back();
		} else{
			return;
		}
	}
		
	var msg = "${msg}";
	if(msg == "글이 작성되었습니다."){
		alert(msg);
		history.go(-2);
	}
	
	if(msg == "글 작성에 실패했습니다."){
			alert(msg);
	}

	
</script>
</html>