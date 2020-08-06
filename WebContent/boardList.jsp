<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
</head>
<body>
   <div id="container">
        <!-- Main Navbar -->
        <nav class="navbar" id="navbar">
            <div class="navbar__logo">
                <a href="#">
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
                    <a href="#" id="free" onClick="location.href='boardList.jsp'">
                        <span>자유 게시판</span>
                    </a>
                </li>
                <li>
                    <a href="#" id="edu" onClick="location.href='boardList.jsp'">
                        <span>학습 게시판</span>
                    </a>
                </li>
                <li>
                    <a href="#" id="secret" onClick="location.href='boardList.jsp'">
                        <span>익명 게시판</span>
                    </a>
                </li>
                <li>
                    <a href="#" id="lunch" onClick="location.href='boardList.jsp'">
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
									<strong class="key-color">[자유게시판]</strong>
									내가 만약.........
								</h3>
								<i class="icon-new"></i>
								<!-- 게시글 상세보기 클래스 변경해야됨 -->
								<dl class="writing-info">
									<dt class="blind">writer</dt>
									<dd class="writer">나는 ui전문가</dd>
									<dt class="blind">etc</dt>
									<dd>
										<span class="date">19:21</span>
										<span class="count-read">112</span>
										<span class="count-likes">21</span>
										<span class="count-comment">2</span>
									</dd>
								</dl>
							</a>
						</li>
						<li>
							<!-- 게시글 상세보기 링크  -->
							<a href="board_view?thread=395071">
								<h3>
									<strong class="key-color">[인기게시판]</strong>
									바람의나라에서 사기당한 썰 푼다.
								</h3>
								<i class="icon-new"></i>
								<!-- 게시글 상세보기 클래스 변경해야됨 -->
								<dl class="writing-info">
									<dt class="blind">writer</dt>
									<dd class="writer">우유빛깔김중열</dd>
									<dt class="blind">etc</dt>
									<dd>
										<span class="date">12:19</span>
										<span class="count-read">152</span>
										<span class="count-likes">22</span>
										<span class="count-comment">25</span>
									</dd>
								</dl>
							</a>
						</li>
						<li>
							<!-- 게시글 상세보기 링크  -->
							<a href="board_view?thread=395071">
								<h3>
									<strong class="key-color">[자유게시판]</strong>
									헬스장 회원별 유형....txt
								</h3>
								<i class="icon-new"></i>
								<!-- 게시글 상세보기 클래스 변경해야됨 -->
								<dl class="writing-info">
									<dt class="blind">writer</dt>
									<dd class="writer">3대500정준호</dd>
									<dt class="blind">etc</dt>
									<dd>
										<span class="date">11:14</span>
										<span class="count-read">50</span>
										<span class="count-likes">10</span>
										<span class="count-comment">20</span>
									</dd>
								</dl>
							</a>
						</li>
						<li>
							<!-- 게시글 상세보기 링크  -->
							<a href="board_view?thread=395071">
								<h3>
									<strong class="key-color">[자유게시판]</strong>
									금요일 금요일~
								</h3>
								<i class="icon-new"></i>
								<!-- 게시글 상세보기 클래스 변경해야됨 -->
								<dl class="writing-info">
									<dt class="blind">writer</dt>
									<dd class="writer">오늘 어디가는상진</dd>
									<dt class="blind">etc</dt>
									<dd>
										<span class="date">11:07</span>
										<span class="count-read">23</span>
										<span class="count-likes">12</span>
										<span class="count-comment">2</span>
									</dd>
								</dl>
							</a>
						</li>
						<li>
							<!-- 게시글 상세보기 링크  -->
							<a href="board_view?thread=395071">
								<h3>
									<strong class="key-color">[인기게시판]</strong>
									정준호 바디프로필 유출본
								</h3>
								<i class="icon-new"></i>
								<!-- 게시글 상세보기 클래스 변경해야됨 -->
								<dl class="writing-info">
									<dt class="blind">writer</dt>
									<dd class="writer">구스패치</dd>
									<dt class="blind">etc</dt>
									<dd>
										<span class="date">10:34</span>
										<span class="count-read">142</span>
										<span class="count-likes">23</span>
										<span class="count-comment">45</span>
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
</html>