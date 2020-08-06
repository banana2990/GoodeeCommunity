package com.gcs.controller;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gcs.service.MemberService;

@WebServlet({"/login", "/join","/overlay","/overlaynick","/findid"})
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
					
					//파라미터로 받아올 값
					String email = req.getParameter("u_email");					
					
					// 해킹하면 안돼요ㅎㅎ.
					String host = "smtp.gmail.com";
					String user = "kjy3309@gmail.com";
					String password = "wndduf!2";
					
					// smtp 값 설정
					Properties prop = new Properties();
					prop.put("mail.smtp.host", host);
					prop.put("mail.smtp.port", 465);
					prop.put("mail.smtp.auth", "true");
					prop.put("mail.smtp.ssl.enable", "true"); 
					prop.put("mail.smtp.ssl.trust", host);
					
					Session session = Session.getDefaultInstance(prop, new javax.mail.Authenticator() {
						protected PasswordAuthentication getPasswordAuthentication() {
							return new PasswordAuthentication(user, password);
						}
					});
					
					
					try {
						MimeMessage message = new MimeMessage(session);
						message.setFrom(new InternetAddress(user));
						
						//수신자 메일 주소 파라메터로 가져오자 아작스로?
						message.addRecipient(Message.RecipientType.TO, new InternetAddress("zoo3309@naver.com"));
						
						//제목과 내용
						message.setSubject("구디커뮤니티 인증번호 전달");
						message.setText("인증번호 ");
						
						///보내기
						Transport transport = session.getTransport("smtp");
						transport.connect(host, user, password);
						transport.sendMessage(message, message.getAllRecipients());
					} catch (MessagingException e) {
						e.printStackTrace();
					}
					
					break;
		
				case "/findid":
					System.out.println("이메일로 아이디찾기");
					email = req.getParameter("email");
					service.findid(email);
					break;
					
			}
	}
}
