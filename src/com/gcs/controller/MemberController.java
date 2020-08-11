package com.gcs.controller;

import java.io.IOException;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gcs.service.MemberService;

@WebServlet({"/login", "/join","/overlay","/overlaynick","/findid","/findpw","/mail"})
public class MemberController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		process(req,resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		process(req,resp);
	}

	private void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
			
			String uri = req.getRequestURI();
			String ctx = req.getContextPath();
			String reqAddr = uri.substring(ctx.length());
			req.setCharacterEncoding("UTF-8");
			RequestDispatcher dis = null;			
			MemberService service = new MemberService(req, resp);
			
			switch(reqAddr) {
			
				case "/login":
					String id = req.getParameter("id");
					String pw = req.getParameter("pw");
					System.out.println("login: "+id+ " Password: "+pw);
					String msg =  "로그인 실패하였습니다.";
					String page = "index.jsp";
					try {
					if(service.login(id,pw)) {
						msg = "환영합니다 "+id+"님!";
						page = "index.jsp";
						req.getSession().setAttribute("id", id);
					}}catch(Exception e) {}
					finally {
						req.setAttribute("msg", msg);
						dis = req.getRequestDispatcher(page);
						dis.forward(req, resp);}
					break;
					
				case "/join":
					System.out.println("회원가입 요청");
					service.join();
					break;
				
				case "/overlay":
					System.out.println("아이디 중복 체크 요청"); //2차확인
					service.overlay();
					break;
					
				case "/overlaynick":
					System.out.println("닉네임 중복 체크 요청"); //2차확인
					service.overlaynick();
					break;	
					
				
				case "/logout" :
					req.getSession().removeAttribute("id");
					resp.sendRedirect("index.jsp");
					break;	
				
				case "/loginCheck":
					break;
					
				case "/mail":
					System.out.println("인증메일 보내기");
					service.sendMail();											
					break;
		
				case "/findid":
					System.out.println("이메일로 아이디찾기");
					String email = req.getParameter("email");
					service.findid(email);
					break;
				
				case "/findpw":
					System.out.println("이메일로 비번찾기");
					service.findpw();
					break;
			}
	}
}
