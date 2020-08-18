<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
	    <meta charset="UTF-8">
	    	<link rel="stylesheet" type="text/css" href="style.css"/>
        	<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
	<style>
	#layer{
	width: 40px;
	height: 40px;
	}
	#layer .pf{
	width: 40px;
	height: 40px;
	}
	</style>
</head>
<body>
	<div id="layer">
	    <div class="pic">
	        <a class="pic_1" href="#"><img class="pf" id="userphoto" src="image/냥.jpg"  ></a>
	    </div>
	</div>
	<div id="btn">
	    <div class="imge">
	        <a href="#"><img class="imge_1" id="userphoto" src="image/냥.jpg"/></a>
	        <div class="nic">${sessionScope.id} 님 반가워요.</div>
	    </div>
	    <div>
		    <div class="alert">		    	
		    	<a class="alert_alert">알림</a>
		    	    	
		        <ul>
		        </br>
		        <a href="#"><li>첫번째 알림~!!!</li></a>
		        </br>
		        <a href="#"><li>두번째 알림~!!!</li></a>
		        </br>
		        <a href="#"><li>세번째 알림~!!!</li></a>
		        </ul>
		    </div>
		    
		    <div class="dmy">
		        <a href="myPageList">내정보</a>
		        <a href="logout">로그아웃</a>
		    </div>
		    
	    </div>
	</div>
</body>
<script>
  $(document).ready(function(){
      btn = $('#layer'); 
      layer = $('#btn');
      btn.click(function(){
         layer.toggle(
           function(){layer.addClass('show')},
           function(){layer.addClass('hide')} 
         );
       });
  });
  
/*
      // 사용자별 이미지 불러오기
  	$.ajax({
  	    type: "post",
        url: "userphoto",
        dataType: "JSON",
        success: function(data){
        	console.log(data.userphoto);
			var userphoto = "";
        	$("#userphoto").attr('src',"받아온 값");
        },
        error: function(error){
           console.log(error);
        }
  	  	});
*/
</script>
</html>
