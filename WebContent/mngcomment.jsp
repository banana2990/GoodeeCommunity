<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix ="c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix ="fn" uri = "http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<link href="https://fonts.googleapis.com/css2?family=Roboto:wght@900&family=Source+Sans+Pro:wght@600&display=swap" rel="stylesheet">
	<link rel="stylesheet" type="text/css" href="style.css" />
	<link rel="stylesheet" type="text/css" href="manage.css" />
	
	<script
	src="https://kit.fontawesome.com/fbff03f786.js" crossorigin="anonymous"></script>
		<title>관리자 카테고리별 댓글 가져오기</title>
		<style>
				table, th, td {
					border: 1px solid olive;
					border-collapse : collapse;
					padding : 5px 10px;		
					}
		</style>
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
            
            <button class="write" location.href="#">
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
                    <a href="mngcomment.jsp" id="mngcomment">
                        <span>댓글 관리</span>
                    </a>
                </li>
                <li>
                    <a href="#" id="mngmember">
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
             <h2>전체 댓글 관리</h2>
                <div class="inner">
                    <button type="button" class="profile">
                        <div class="profile-img"></div>
                    </button>
                </div>
            </div>
        </nav>
        
        <div id="contents">
	        <div class="contents_table">
				 <table>
						<tr> 
							<th style="width : 10px " >no.</th> 
							<th>게시판</th> 
							<th style="width : 400px;">내용</th> 
							<th style="width : 130px">작성자</th> 
							<th>등록일</th>
							<th>삭제</th>
						</tr>
										
					<c:forEach items="${list}" var="bbs">
					<tr>
					<td> ${bbs.comment_no} </td>
					<td> mboard_no </td>
					<td> <a href="detail?board_no=${bbs.board_no}"> ${bbs.co_content} </a></td>
					<td> ${bbs.id} </td>
					<td> ${bbs.co_reg_date} </td>							
					<td> <button onclick = "location.href='delmngcomment?comment_no=${bbs.comment_no}'">삭제 </button></td>								
					</tr>		
					</c:forEach>
					
						
	
				</table>            
        </div>
	</body>
	
	<script>
	
	</script>
</html>