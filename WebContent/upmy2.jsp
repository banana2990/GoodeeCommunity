<%@ page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
        <title>.html</title>
        <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>

    <style>
      .upmy1 {
        position: fixed;
        top: 80px;
        left: 350px;
        width: 795px;
        height: 80%;
        display: flex;
        justify-content: center;
        align-items: center;
        border: 1px solid black;
        background-color: #cad0d1;
        border-radius: 5%;
      }
      .upmy1 .upfn {
        position: absolute;
        width: 100px;
        height: 50px;
        left: 440px;
        top: 230px;
        border-radius: 5%;
        border: 1px solid black;
      }
      .upmy1 .upcant {
        position: absolute;
        width: 100px;
        height: 50px;
        left: 585px;
        top: 230px;
        border-radius: 5%;
        border: 1px solid black;
      }
      .upmy1 .out{
        bottom: 10px;
        right: 10px;
        position: absolute;
      }

      .upmy2{
        position: absolute;
        width: 795px;
        height: 330px;
        top: 0px;
        
      }

      .upmy3-1 {
        position: relative;
        width: 300px;
        height: 300px;
        border-radius: 12px;
        top: 100px;
        left: 20px;
        border: 1px solid black;
        
      }

      .upmy3_2{
        position: relative;
        width: 352px;
        height: 200px;
        left: 389px;
        top: 20px;
        border: 1px solid black;
        border-radius: 3%;
      }

      .upmy3_2 .u3_2{
        left: 20px;
        position: relative;
        top: 20px;
      }

      .upmy3_1 .upimge{
        width: 100px;
        height: 100px;
        left: 50px;
        top: 20px;
        position: absolute;
      }
      .upmy3_1 .imge_1{
        border-radius: 50%;
      }
      .upmy3_1 .u{
        top: 20px;
        left: 70px;
        position: relative;
      }
      

      .upmy2_1 {
        
        position: absolute;
        width: 795px;
        height: 232px;
        
        top: 332px;
        }
    
      .upmy2_1 > ul {
          font-size:0;
          }
      .upmy2_1 > ul > li {
          display:inline-block;
          vertical-align:top;
          width:24%;
          border-radius: 20%;
          left: 30px;
          top: 10px;
          position: relative;
          }
          
      .upmy2_1 > ul > li > a {
          display:block;
          width:auto;
          margin:55px;
          
          }


          
      
      

      td{
          width: 40px;
          height: 20px;
        }
      .dap{
          position: absolute;
          top: 416px;
          left: 58px;
        }

      .paging{
        position: relative;
        top: 230px;
      }

      .cl button {
      padding: 10px 20px;
      font-size: 15px;
      border: 1px solid rgba(255, 230, 207, 0.28);
      border-radius: 4px;
      position: absolute;
      right: 24px;
      bottom: 15px;
      }
      .screen button{
        padding: 10px 20px;
        font-size: 15px;
        border: 1px solid rgba(255, 230, 207, 0.28);
        border-radius: 4px;
        position: absolute;
        right: 65px;
        bottom: 15px;
      }

    </style>
  </head>
  <body>
     

    <div class="overlay"></div>
    
    <div class="upmy1">    
      <div class="upmy2">      
        <div class="upmy3_1">          
          <div class="upimge">
            <a href="#"><img class="imge_1" src="image/냥.jpg"/></a>
            <div class="u">
              <a href="photoUp"><button>등록</button></a> <!-- 처음 가입시에 무조건 사진을 등록하고, 수정으로 변경?-->
              <a href="photoDel"><button>삭제</button></a>  <!-- 삭제되면 처음 가입사진으로 수정됨 -->
            </div>
          </div>          
        </div>
        
        <form action="myUpdate" method="post">
	        <div class="upmy3_2">         
	          <div class="u3_2">
	            <div><b>닉네임 : </b><input type="text" value="${mylist.nickName }" name="nickName"><input type="button" id="nickChck" value="중복확인"/></div></br>
	          </div>
	          <div class="u3_2">
	            <div><b>이름 : </b><input type="text" name="name" value="${mylist.name}" ></div></br>
	          </div>
	          <div class="u3_2">
	            <div><b>비밀번호 : </b><input type="password" name="pw" id="myUserPw"/></div></br>
	          </div>
	          <div class="u3_2">
	            <div><b>비밀번호 확인 : </b><input type="password" id="myUserPwChk"/></div></br>
	            <font id="chkNotice" size="2"></font>            
	          </div>        
	        </div>
	        
	        <div class="upfn">
	          <button id="memUpdate" value="수정" style="width: 100px; height: 50px;">수정</button>
	        </div>
        </form>
        
        <div class="out">
         <input type="button" id="memOut" value="회원 탈퇴" onclick="location.href='memberout'" style="width: 100px; height: 50px;" />
        </div>
      
      </div>
      <div class="upmy2_1">
          <ul>
              <li>
                  <a href="">
                      <div class="screen">
                        <button>작성한 글</button>
                      </div>
                  </a>
              </li>
              <li>
                  <a href="">
                      <div class="screen">
                        <button>댓글 단 글</button>
                      </div>
                  </a>
              </li>
              <li>
                  <a href="">
                      <div class="screen">
                        <button>좋아요 한 글</button>
                      </div>
                  </a>
              </li>
              <li>
                <a href="">
                    <div class="screen">
                      <button>작성한 학습사항</button>
                    </div>
                </a>
            </li>
          </ul>
      </div>
      <div class="dap">
        <table style="border-collapse: collapse; margin: 0; padding: 0;">
          <tr style="height: 25px;">
            <td style="width: 600px;">[자유게시판] 헬스장 회원별 유형...txt   </td>
            <td rowspan="2" style="width: 70px;"></td>
          </tr>
          <tr style="height: 25px;">
            <td style="width: 600px;">3대500정준호     11:44             50     ♡  10</td>
            <td></td>
          </tr>
        </table>
      </div>
      <div class="paging">
        <table>
          <tr>
            <td><button><<</button></td>
            <td><button><</button></td>
            <td><button>1</button></td>
            <td><button>></button></td>
            <td><button>>></button></td>
          </tr>
        </table>
      </div>
      <div class="overlay"></div>
      <div class="cl">
        <a href="main"><button>Close</button></a>
      </div>


        </div>
      </div>

      </div>
     </div>
    </div>
  </body>
  <script>
  
  var msg = "${msg}";
  if(msg != ""){
  	alert(msg);
  }
  
		/*닉네임 중복 확인*/
	  $("#nickChck").click(function(){
			if($("input[name='nickName']").val() == ""){
				alert("닉네임을 입력해주세요.");
			}else{
				var nickname = $("input[name='nickName']").val(); // id값 가져오기
				console.log(nickname); // 잘 들어오는 지 확인: 1차
				
				$.ajax({
					type: "get",
					url: "overlaynick",
					data: {"nickname":nickname},
					dataType: "JSON",
					success: function(data){		
						if(data.overlay){
							alert("이미 사용중인 닉네임입니다.");
							$("input[name='nickName']").val("");
						}else{
							alert("사용 가능한 닉네임입니다");
							overChknic = true;
						}
					},
					error: function(e){
						console.log(e);
					}			
				});
			}
		});
		
		
		//회원탈퇴 알림
		$("#memOut").click(function(){
			alert("정말 탈퇴 하시겠습니까?");
		});
		
		
		//비밀번호 확인
		$(function(){
			$("#myUserPw").keyup(function(){
				$('#chkNotice').innerhtml("")
      	});
      
	      $('#myUserPwChk').keyup(function(){
	
	        if($("#myUserPw").val() != $("#myUserPwChk").val()){
	          $("#chkNotice").html("비밀번호가 일치하지 않습니다.<br>");
	          $("#chkNotice").attr("color","red");
	        }else{
	          $("#chkNotice").html("비밀번호가 일치합니다.<br>");
	          $("#chkNotice").attr("color","green");
	        }

             });
		});
		
		//수정 확인
		
		function memUpdate(){
			if(confirm("정말 수정하시겠습니까 ?") == true){
		        location.href="myUpdate";
		    }else{
		        return ;
		    }
		}
		
		



	  
  
  
  </script>
</html>
