<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix ="c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix ="fn" uri = "http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://fonts.googleapis.com/css2?family=Roboto:wght@900&family=Source+Sans+Pro:wght@600&display=swap" rel="stylesheet">
<link rel="stylesheet" href="style.css">
<script
src="https://kit.fontawesome.com/fbff03f786.js"
crossorigin="anonymous"
></script>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
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
            
            <button class="write" onclick="location.href='write.jsp'">
                글쓰기
            </button>
    
            <ul class="navbar__menu">
                <h2>전체 게시판</h2>
                <li>
                    <a href="boardList.jsp?mboard_no=1" id="free">
                        <span>자유 게시판</span>
                    </a>
                </li>
                <li>
                    <a href="boardList.jsp?mboard_no=2" id="edu">
                        <span>학습 게시판</span>
                    </a>
                </li>
                <li>
                    <a href="boardList.jsp?mboard_no=3" id="secret">
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
                <div class="inner">
                    <button type="button" class="profile">
                        <div class="profile-img"></div>
                    </button>
                    <button class="login" onclick="location.href='login.jsp'">로그인</button>
                </div>
            </div>
        </nav>

        <!-- Main Contents -->
        <div id="contents">
            <div class="section-bot">
                <div class="board-swipe">
                    <ul>
                        <li>
                            <button class="key-color" style="top : -35px">전체 게시판</button>
                        </li>
                    </ul>
                <!-- 게시글 검색바 -->
                <div class="board_search-input">
	                <form class="searchbar" action="#" method="get" >
	                    <select>
							<option>제목</option>
							<option>작성자</option>
							<option>제목+내용</option>
						</select>
	                    <input
	                      class="search"
	                      type="text"
	                      name="search"
	                    />
	                    <button style="left : 100px"><i class="fas fa-search"></i></button>
	                </form>
            	</div>
                </div>
		
                <div class="list-box">
                    <!-- 게시글 영역 -->                    
                    <ul>                   	
							<li>
								<!-- 게시글 상세보기 링크  -->
								<a href="board_view?thread=395071">
									<h3>
										<strong class="key-color">[]</strong>
									</h3>
									<i class="icon-new"></i>
									<!-- 게시글 상세보기 클래스 변경해야됨 -->
									<dl class="writing-info">
										<dt class="blind">writer</dt>
										<dd class="writer"></dd>
										<dt class="blind">etc</dt>
										<dd>
											<span class="date"></span>
											<span class="count-read"></span>
											<span class="count-likes">미구현</span>
											<span class="count-comment">미구현</span>
										</dd>
									</dl>
								</a>
							</li>					
                    </ul>                   
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
	
	
	
	var mboard_no = window.location.search.substring(11);
	var page = $('.list-paging button.on');

	var param = {};
	param.mboard_no = mboard_no;
	param.page = page[0].id;
	
	console.log(param);
	
	listCall();
	
	function listCall(){
		$.ajax({
			type:"post"
			,url:"boardList"
			,data:param
			,dataType:"JSON"
			,success:function(data){
				console.log(data.list);
			}
			,error:function(e){
				console.log(e);
			}
		});
	};	
	
	
	
	
</script>
</html>