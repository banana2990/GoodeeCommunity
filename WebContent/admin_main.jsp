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
                	<c:forEach items="${list }" var="bbs" varStatus="status">
	                	<ul>              	
							<li>
								<a href="boardDetail?board_no=${bbs.board_no}">
									<h3>
										<strong class="key-color">[${bbs.boardname}]</strong> ${bbs.bo_subject }</h3>
										<i class="icon-new"></i>
										<dl class="writing-info"> 
											<dt class="blind"></dt>
											<c:if test="${bbs.mboard_no eq 3}" >
												<dd class="writer">익명</dd>
											</c:if>
											<c:if test="${bbs.mboard_no ne 3}">
												<dd class="writer">${bbs.nickName }</dd> 
											</c:if>
											<dt class="blind"></dt>
										<dd>
											<span class="date">${bbs.bo_reg_date }</span>
											<span class="count-read">${bbs.bo_bHit }</span>
											<span class="count-likes">${blikeCnt[status.index].blike_cnt }</span>
											<span class="count-comment">${commentCnt[status.index] }</span>
										</dd>
									</dl>
								</a>
							</li>				
	                    </ul>
                    </c:forEach>                         
                </div>

                <div class="list-paging">  
                	<c:if test="${page.curPage ne 1}">
                        <button onClick="fn_paging('${page.prevPage }')">prev</button> 
                    </c:if>             
                     <c:forEach var="pageNum" begin="${page.startPage }" end="${page.endPage }">
	                     <c:choose>
		                        <c:when test="${pageNum eq  page.curPage}">
		                            <button class="on" onClick="fn_paging('${pageNum }')">${pageNum }</button> 
		                        </c:when>
		                        <c:otherwise>
		                            <button onClick="fn_paging('${pageNum }')">${pageNum }</button> 
		                        </c:otherwise>
	                    </c:choose>
	                </c:forEach>
	                <c:if test="${page.curPage ne page.pageCnt && page.pageCnt > 0}">
                        	<button onClick="fn_paging('${page.nextPage }')">next</button> 
                    </c:if>
                </div>
                
            </div>
            </div>
        </div>  
        
	</body>
	
	<script>
	function fn_paging(curPage) {
		location.href = "main?curPage="+curPage;
	}		
	</script>
</html>