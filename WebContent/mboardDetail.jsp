<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://fonts.googleapis.com/css2?family=Roboto:wght@900&family=Source+Sans+Pro:wght@600&display=swap" rel="stylesheet">
<link rel="stylesheet" href="style.css">
<link rel="stylesheet" href="D110_style.css">
<script src="index.js" defer></script>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script
src="https://kit.fontawesome.com/fbff03f786.js"
crossorigin="anonymous"
></script>
<title>구디 커뮤니티</title>
</head>
<body>
		<div id="container">
        <!-- Main Navbar -->
        <nav class="navbar" id="navbar">
            <div class="navbar__logo">
                <a href="index.jsp">
                    <img src="image/logo.PNG" alt="로고">
                    <h1>관리자 페이지</h1>
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
                    <a href="mngboard.jsp" id="mngboard">
                        <span>게시글 관리</span>
                    </a>
                </li>
                <li>
                    <a href="mngcomment" id="mngcomment">
                        <span>댓글 관리</span>
                    </a>
                </li>
                <li>
                    <a href="membermanagement.jsp" id="mngmember">
                        <span>회원 관리</span>
                    </a>
                </li>
                <li>
                    <a href="contact.jsp" id="ask">
                        <span>문의사항 내역</span>
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
            <!--게시글 상세보기-->
            <div class="section_top">
                <div class="title_box">
                    <input type="button" value="삭제" onclick="location.href='del?board_no=${boardDetail.board_no}&mboard_no=${boardDetail.mboard_no }'"/>
                    <input type="button" value="수정" onclick="location.href='updateForm?board_no=${boardDetail.board_no}'"/>
                    <h2><a href="#" class="key_color">${boardDetail.boardname }</a></h2>
                    <h3>${boardDetail.bo_subject }</h3>
                    <dl class="writing_info">
                        <dd class="writer">${boardDetail.nickName }</dd>
                        <span class="date">${boardDetail.bo_reg_date }</span>
                        <img src="./image/조회수.PNG" alt class="read">
                        <span class="count">${boardDetail.bo_bHit }</span>
                    </dl>
                </div>
            </div>
            
            <div class="section-bot">
                <div class="view-box">
                    <div class="txt note-editor">
                        ${boardDetail.bo_content }
                    </div>
                    <div class="share">
                        <button type="button" class="btn-like thread-likes">
                            <span class="like-count">0</span>
                        </button>
                    </div>
                </div>

                <div class="reply-box">
                    <div class="reply-area">
                        <p class="total">
                            <span class="message">댓글</span>
                            <span class="key-color">2</span>
                        </p>
                        <div class="reply-input">
                            <div class="reply-div">
                                <form action="reply?board_no=${boardDetail.board_no}" method="post">
										<input type="hidden" name="no" id="no" value="${ content.board_no }"> 
										<input type="hidden" name="id" id="id" value="${ id }">
										<textarea rows="5" cols="50" name="co_content" id="reply_content"></textarea>
										<input type="submit" class="btn-reply" id="reply_btn" value="댓글 등록">
									</form>
									
                            </div>
                        </div>
                    </div>

                    <div class="reply-list">
                        <ul>
                            <li data-commentid="1027890">
                                <div class="reply">
                                    <div class="profile-img"></div>
                                    <div class="txt-area">
                                        <dl class="writing-info">
                                            <dd class="writer">아무개</dd>
                                            <dd>
                                                <span class="date">2020.08.05 11:12</span>
                                            </dd>
                                        </dl>
                                        <div class="txt.box1">
                                            <p>야 이거 겁나 어렵네......</p>
                                            <input type="text" value="" class = "reply-inputbox" style="width : 750px">
                                            <button class="btn-coment-reply" id="recomment">답글</button><button class="btn-coment-reply">취소</button>
                                        </div>
                                        <div class="util">
                                            <button type="button" class="btn-like reply-likes">
                                                <span class="like-count-reply">1</span>
                                            </button>
                                            <button type="button" class="btn-rereply">답글쓰기</button>
                                        </div>
                                    </div>
                                </div>
                            </li>

                            <li data-commentid="102789">
                                <div class="reply">
                                    <div class="profile-img2"></div>
                                    <div class="txt-area2">
                                        <dl class="writing-info">
                                            <dd class="writer">아무개2</dd>
                                            <dd>
                                                <span class="date">2020.08.05 13:21</span>
                                            </dd>
                                        </dl>
                                        <div class="txt.box1">
                                            <p>대댓글 대박이네......</p>
                                            <button class="btn-coment-reply">삭제</button><button class="btn-coment-reply">취소</button>
                                        </div>
                                        <div class="util">
                                            <button type="button" class="btn-like reply-likes">
                                                <span class="like-count-reply">0</span>
                                            </button>
                                            <button type="button" class="btn-rereply">답글쓰기</button>
                                        </div>
                                    </div>
                                </div>
                            </li>
                        </ul>
                    </div>         
                </div>
                <button class="btn-foot" location.href="#">이전</button>
                <button class="btn-foot" location.href="#">다음</button>
                <button class="btn-list" location.href="#">목록으로</button>
            </div>
        </div>
    </div>   
            
</body>
<script>
	var msg = "${msg}";
	if(msg != ""){
		alert(msg);
	}
</script>
</html>