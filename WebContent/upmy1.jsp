<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
	    <meta charset="utf-8">
	    	<link rel="stylesheet" type="text/css" href="style.css" />
        <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
	<style>
	</style>
</head>
<body>
	<div id="layer">
	    <div class="pic">
	        <a class="pic_1" href="#"><img class="pf" src="image/냥.jpg"  ></a>
	    </div>
	</div>
	<div id="btn">
	    <div class="imge">
	        <a href="#"><img class="imge_1" src="image/냥.jpg"/></a>
	        <div class="nic">나는 ui 전문가</div>
	    </div>
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
	        <a href="upmy2.jsp">내정보</a>
	    </div>
	    <div class="logout">
	        <a href="logout">로그아웃</a>
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
</script>
</html>
