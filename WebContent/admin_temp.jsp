<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix ="c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix ="fn" uri = "http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<link href="https://fonts.googleapis.com/css2?family=Roboto:wght@900&family=Source+Sans+Pro:wght@600&display=swap" rel="stylesheet">
	<link rel="stylesheet" type="text/css" href="style.css" />
	
	<script
	src="https://kit.fontawesome.com/fbff03f786.js" crossorigin="anonymous"></script>
		<title>관리자 카테고리별 글 가져오기</title>		
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
                    <a href="#" id="ask">
                        <span>문의사항 내역</span>
                    </a>
                </li>
            </ul>
            
            <div class="top-util">
                <div class="inner">
                    <button type="button" class="profile">
                        <div class="profile-img"></div>
                    </button>
                    <button class="login" onclick="location.href='login.jsp'">로그인</button>
                    <button onclick="location.href='photo_test.jsp'">포토임시확인용</button>
                </div>
            </div>
        </nav>
       <div id="contents">
            <div class="section-top">
                <div class="img-swipe">
                    <!-- 이미지 필요!!! -->
                    	메인 이미지 영역입니다.
                </div>
                <div class="helplist">
                		문의사항 목록 영역입니다.
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
                    게시글 영역입니다.<br>
                    ul
                     li ....
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
        
	</body>
	
	<script>
	
	</script>
</html>