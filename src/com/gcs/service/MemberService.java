package com.gcs.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Properties;
import java.util.Random;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gcs.DAO.MemberDAO;
import com.google.gson.Gson;

public class MemberService {
	HttpServletRequest req = null;
	HttpServletResponse resp = null;
	
	public MemberService(HttpServletRequest req, HttpServletResponse resp) {
		this.req = req;
		this.resp = resp;
	}

	public boolean login(String id, String pw) throws SQLException {
		MemberDAO dao = new MemberDAO();
		boolean result = dao.login(id,pw);
		return result;
	}

	public void overlay() throws IOException {
		String id = req.getParameter("id"); // 값 받아오기
		boolean success = false;
		System.out.println("id: "+id); // 3차 확인
		
		MemberDAO dao = new MemberDAO();
		try {
			success = dao.overlay(id);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dao.resClose();
			HashMap<String,Object> map =  new HashMap<String, Object>();
			map.put("overlay",success);
			Gson json = new Gson();
			String obj = json.toJson(map);
			System.out.println("result :"+obj);
			resp.getWriter().println(obj);
		}
		
	}
	
	public void overlaynick() throws IOException {
		String nickname = req.getParameter("nickname"); // 값 받아오기
		boolean success = false;
		System.out.println("nickname: "+nickname); // 3차 확인
		
		MemberDAO dao = new MemberDAO();
		try {
			success = dao.overlaynick(nickname);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dao.resClose();
			HashMap<String,Object> map =  new HashMap<String, Object>();
			map.put("overlay",success);
			Gson json = new Gson();
			String obj = json.toJson(map);
			System.out.println("result :"+obj);
			resp.getWriter().println(obj);
		}
		
	}

	public void join() throws IOException {
		String id = req.getParameter("id");
		String pw = req.getParameter("pw");
		String name = req.getParameter("name");
		String nickName = req.getParameter("nickName");	
		String email = req.getParameter("email");
		String emailChk = req.getParameter("emailChk");
		System.out.println("입력 값 :"+ id + pw + name + nickName + email + emailChk);
		boolean success = false;
		
		MemberDAO dao = new MemberDAO();
		try {
			success = dao.join(id,pw,name,nickName,email,emailChk);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dao.resClose();
			HashMap<String,Object> map =  new HashMap<String, Object>();
			map.put("join",success);
			Gson gson = new Gson();
			String obj = gson.toJson(map);
			System.out.println("result:"+obj);
			resp.getWriter().println(obj);
		}		
	}

	public void findid(String email) throws IOException {
		email = req.getParameter("email"); // 값 받아오기
		String id = "";
		System.out.println("id: "+email); // 3차 확인
		
		MemberDAO dao = new MemberDAO();
		try {
			id = dao.findid(email);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dao.resClose();
			HashMap<String,Object> map =  new HashMap<String, Object>();
			map.put("findid",id);
			Gson json = new Gson();
			String obj = json.toJson(map);
			System.out.println("찾은 id :"+obj);
			resp.getWriter().println(obj);
		}
		
		
	}

	public void sendMail() throws IOException {
		// 파라미터로 받아올 값
		String email = req.getParameter("email");					
		
		// 해킹하면 안돼요ㅜ.
		String host = "smtp.gmail.com";
		final String user = "kjy3309@gmail.com";
		final String password = "wndduf!2";
		
		// smtp 값 설정
		Properties prop = new Properties();
		prop.put("mail.smtp.host", host);
		prop.put("mail.smtp.port", 465);
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.ssl.enable", "true"); 
		prop.put("mail.smtp.ssl.trust", host);
		
		// 난수
		Random r = new Random();
        int dice = r.nextInt(999999) + 100000;
        System.out.println(dice);
		
		Session session = Session.getDefaultInstance(prop, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(user, password);
			}
		});
		
		
		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(user));
			
			//수신자 메일 주소 파라메터로 가져오자 아작스로?
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
			
			//제목과 내용
			message.setSubject("구디커뮤니티 인증번호 전달");
			message.setText("인증번호는 ["+dice+"] 입니다.");
			
			///보내기
			Transport transport = session.getTransport("smtp");
			transport.connect(host, user, password);
			transport.sendMessage(message, message.getAllRecipients());
			
		} catch (MessagingException e) {
			e.printStackTrace();
		} finally {			
			HashMap<String,Object> map =  new HashMap<String, Object>();
			map.put("dice",dice);
			Gson gson = new Gson();
			String obj = gson.toJson(map);
			System.out.println("result:"+obj);
			resp.getWriter().println(obj);
		}
		
	}

	
	
	
	
	
	
	
	
	
}
