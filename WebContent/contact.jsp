<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://fonts.googleapis.com/css2?family=Roboto:wght@900&family=Source+Sans+Pro:wght@600&display=swap" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="style.css" />
<link rel="stylesheet" type="text/css" href="M05_관리자문의.css" />
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="index.js" defer></script>
<script src="https://kit.fontawesome.com/fbff03f786.js" crossorigin="anonymous"></script>
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
            
            <button class="write" location.href="#">
                글쓰기
            </button>
    
            <ul class="navbar__menu">
                <h2>전체 게시판</h2>
                <li>
                    <a href="#">
                        <span>자유 게시판</span>
                    </a>
                </li>
                <li>
                    <a href="#">
                        <span>학습 게시판</span>
                    </a>
                </li>
                <li>
                    <a href="#">
                        <span>익명 게시판</span>
                    </a>
                </li>
                <li>
                    <a href="#">
                        <span>오늘 점심 뭐먹지?</span>
                    </a>
                </li>
            </ul>
            
            <div class="top-util">
                <div class="top-board">문의사항 내역</div>
                <div class="inner">
                    <button type="button" class="profile">
                        <div class="profile-img-manager"></div>
                    </button>
                    <button class="login" onclick="location.href='login.jsp'">로그인</button>
                </div>
            </div>
        </nav>
        
        <!-- Main Contents -->
        <div id="contents">
            <div class="section-top">
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
                        <c:forEach items="${contact}}" var="contact">
						<tr>
							<td> ${contact.contact_no} </td>
							<td> ${contact.writer}</td>
							<td> ${contact.co_content}</td>
							<td> ${contact.c_email} </td>
							<td> ${contact.co_reg_date} </td>							
							<td> <a href="#=${contact.c_status}">상태 </a></td>
						</tr>		
					</c:forEach>
                        <tr>
                            <th>No</th>
                            <th>작성자</th>
                            <th>내용</th>
                            <th>이메일</th>
                            <th>접수일</th>
                            <th>상태</th>
                        </tr>
                        <tr>
                            <td>1</td>
                            <td>정준호</td>
                            <td><div><span>누가 저 사칭...</span>
                                <p class="arrow_box">누가 저 사칭했습니다. 지금 몸자랑 하고 다니는 사람은 제가 아닙니다 조치 부탁드려요</p>
                            </div></td>
                            <td>jjh@gmail.com</td>
                            <td>20.08.03</td>
                            <td><button class="btn-table">접수</button></td>
                        </tr>
                        <tr>
                            <td>2</td>
                            <td>문의좀</td>
                            <td><div><span>프로필 등록이...</span>
                                <p class="arrow_box">프로필 등록이 안되요. 제 프로필을 모두에게 보여주고 싶은데, 그게 안되니깐 너무 답답하고 화가 납니다. 조치 부탁드려요</p>
                            </div></td>
                            <td>local@naver.com</td>
                            <td>20.08.01</td>
                            <td><button class="btn-table">접수</button></td>
                        </tr>
                        <tr>
                            <td>3</td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                        </tr>
                        <tr>
                            <td>4</td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                        </tr>
                        <tr>
                            <td>5</td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                        </tr>
                        <tr>
                            <td>6</td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                        </tr>
                        <tr>
                            <td>7</td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                        </tr>
                        <tr>
                            <td>8</td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                        </tr>
                        <tr>
                            <td>9</td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                        </tr>
                        <tr>
                            <td>10</td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                        </tr>
                    </table>
                </div>
            <div class="section-bot">
            <!--페이징-->
                <div class="list-box">   
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
    //접수 / 완료 버튼
    $(function() {
        $('.btn-table').click( function() {
            if( $(this).html() == '접수' ) {
                $(this).html('완료');
            }
            else {
                $(this).html('접수');
            }
        });
    });
</script>
</html>