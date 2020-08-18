<%@ page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix ="c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix ="fn" uri = "http://java.sun.com/jsp/jstl/functions" %>


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
            <a href="#"><img class="imge_1" id="userphoto" src="image/냥.jpg"/></a>
            <div class="u">
              <a href="photoUp"><button>등록</button></a> 
              <a href="photoDel"><button>삭제</button></a>
            </div>
          </div>          
        </div>
        
        <form action="myUpdate" method="post">
	        <div class="upmy3_2">         
	          <div class="u3_2">
	            <div><b>닉네임 : </b><input type="text" value="${mylist.nickName }" name="nickName"><input type="button" id="nickChck" value="중복확인"/></div></br>
	          </div>
	          <div class="u3_2">
	            <div><b>이름 : </b><input type="text" name="name" value="${mylist.name}" ></div><br/>
	          </div>
	          <div class="u3_2">
	            <div><b>비밀번호 : </b><input type="password" name="pw" id="myUserPw"/></div><br/>
	          </div>
	          <div class="u3_2">
	            <div><b>비밀번호 확인 : </b><input type="password" id="myUserPwChk"/></div><br/>
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
			<!-- 내가쓴 것들의 목록 -->
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
											<span class="count-comment">${commentCnt[status.index]}</span>
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

      <div class="overlay"></div>
      <div class="cl">
        <a href="main"><button>Close</button></a>
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
		
		function fn_paging(curPage) {
			if(mboard_no.length > 2){
				mboard_no = window.location.search.substring(21);
			}
			location.href = "boardList?curPage="+curPage+"&mboard_no="+mboard_no;
		}	
	  
  
  
  </script>
</html>
