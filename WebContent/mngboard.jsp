<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix ="c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix ="fn" uri = "http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<link href="https://fonts.googleapis.com/css2?family=Roboto:wght@900&family=Source+Sans+Pro:wght@600&display=swap" rel="stylesheet">
	<link rel="stylesheet" type="text/css" href="style.css" />
	<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
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
                <div class="inner">
                    <button type="button" class="profile">
                        <div class="profile-img"></div>
                    </button>
                </div>
            </div>
        </nav>
        
        <div id="contents">
	    	<div class="select box">
		        <select name="mngboard" onchange="reload()">
					<option value="1">자유게시판</option>
					<option value="2">학습게시판</option>
					<option value="3">익명게시판</option>
					<option value="4">공지</option>
				</select>
			</div>
			
	        <div id="mngboardlist">       
	         
	               <table>
    			     <tr>
					<th>no.</th> 
					<th>제목</th> 
					<th>작성자</th> 
					<th>조회수</th> 
					<th>등록일</th>
					<th>삭제</th>
				         </tr> 
				     </table>
	        </div>
	      
         </div>
        
	</body>
	
	<script>

	
	function reload(){		
		var val = $("select[name='mngboard']").val();
		console.log(val);
		$.ajax({
	        type: "get",
	        url: "mngboard",
	        data: {"mBoard_no": val},
	        dataType: "JSON",
	        success: function(data) {
	            console.log(data.list);
	            //drawTable(data.list);
	        },
		    error: function() {
		        console.log(data);
		    }
	    });
	}
	
/* 	function drawTable(list){
		var content = "";
		   $("tbody").empty();   
		   list.forEach(function(item,num){
		      //console.log(num,item);
		      content = "<tr><td>"+item.board_no+"</td>"
		         +"<td><a href='detail?board_no="+item.board_no+"'>"
		        +item.bo_subject+"</td></a><td>"+item.user_name
		         +"</td><td>"+item.id+"</td><td>"+item.bo_bhit+"</td><td>"
		         +item.bo_bhit  +"</td><td>"+item.bo_reg_date
		         +"</td><td><a href='delboard?board_no="+item.board_no+"'> 삭제 </a></td></tr>";
		         $("tbody").append(content);
		   }); */
		  			   
		


	</script>
</html>