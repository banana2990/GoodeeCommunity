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
                    <h1>관리자 페이지</h1>
                </a>
            </div>
            <div class="search-input">
                <form class="searchbar" action="search" method="get">
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
                    <a href="contact" id="ask">
                        <span>문의사항 내역</span>
                    </a>
                </li>
            </ul>
            
            <div class="top-util">
                <div class="inner">
                	<button onclick="location.href='logout'"> 로그아웃 </button>
                    <button type="button" class="profile">
                        <div class="profile-img"></div> </button>
                </div>
            </div>
        </nav>
       <div id="contents">
            <div class="section-top">
                <div class="helplist">
                		<div id="menu">
                    <table>
                        <tr>
                            <th>No</th>
                            <th>작성자</th>
                            <th>내용</th>
                            <th>이메일</th>
                            <th>접수일</th>
                            <th>상태</th>
                        </tr>
                        
                        <!-- 문의사항 리스트 -->
                        <c:forEach var="contact" items="${contact}">
						<tr>
							<td><p><c:out value="${contact.contact_no}"/></p></td>
							<td><p><c:out value="${contact.writer}" /></p></td>
							<td><p><c:out value="${contact.subject}" /></p></td>
							<td><p><c:out value="${contact.content}" /></p></td>
							<td><p><c:out value="${contact.c_email}" /></p></td>
							<c:if test="${contact.c_status eq 0}">
								<td><p> <input type="button" class="status" value="접수" onclick="stateSet(${contact.c_status}, ${contact.contact_no})"></p></td>
							</c:if>
							<c:if test="${contact.c_status eq 1}">
								<td><p> <input type="button" class="status" value="완료" onclick="stateSet(${contact.c_status}, ${contact.contact_no})"></p></td>
							</c:if>
							
						</tr>		
						</c:forEach>
                         
                    </table>
                </div>
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
                    <!-- 게시글 영역 리스트박스 치우고 다시 디테일 불러오는 것 붙여넣기 필요-->
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