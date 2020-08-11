package com.gcs.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
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
import com.gcs.DTO.MemberDTO;
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
		System.out.println("email: "+email); // 3차 확인
		
		MemberDAO dao = new MemberDAO();
		try {
			id = dao.findid(email);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dao.resClose();
			HashMap<String,Object> map =  new HashMap<String, Object>();
			map.put("searchedid",id);
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

	public void findpw() throws IOException {
		// 파라미터로 받아올 값
		String email = req.getParameter("email");					
		System.out.println(email);
		// 해킹하면 안돼요ㅜ. 
		//히히
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
		
		// 난수 생성
		Random r = new Random();
		int success = 0;
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
			message.setSubject("구디커뮤니티 비밀번호 전달");
			message.setText("비밀번호는 ["+dice+"] 입니다.");
			
			///보내기
			Transport transport = session.getTransport("smtp");
			transport.connect(host, user, password);
			transport.sendMessage(message, message.getAllRecipients());
			
			// 이메일을 보냈으면 난수로 비밀번호 업데이트
			MemberDAO dao = new MemberDAO();
			success = dao.findpw(email,dice);			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {			
			HashMap<String,Object> map =  new HashMap<String, Object>();
			String msg = "이메일로 가입된 ID를 찾을 수 없습니다.";
			if(success>0) {
				msg ="비밀번호가 전송되었습니다. 이메일을 확인해주세요";
			}
			map.put("msg", msg);
			Gson gson = new Gson();
			String obj = gson.toJson(map);
			System.out.println("result:"+obj);
			resp.setContentType("text/html; charset=UTF-8");
			resp.getWriter().println(obj);
		}
	}
<<<<<<< HEAD

	//관리자 - 회원리스트
	public void list() throws IOException {
		HashMap<String, Object> map = new HashMap<String, Object>();
		Gson gson = new Gson();
		boolean loginChk = false;
		// 1. 세션체크
		if (req.getSession().getAttribute("id") != null) {// 로그인 상태 일 경우
			// 2. 리스트 호출
			MemberDAO dao = new MemberDAO();
			ArrayList<MemberDTO> list = null;
			try {
				list = dao.list();// 한 건이 아니니깐 arraylist로 받아야함
				loginChk = true;
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				dao.resClose();
			}
			// 3. map에 넣기
			map.put("list", list);/// ????????
		}
		map.put("login", loginChk);// ?????
		String obj = gson.toJson(map);// 4. Gson 으로 json 형태로 변환
		System.out.println(obj);
		resp.setContentType("text/html; charset=UTF-8");
		resp.getWriter().println(obj);// 5. response 객체로 보내기

	}

	//관리자 - 회원삭제
	public void delete() throws IOException {
		String[] delList = req.getParameterValues("delList[]");
		System.out.println("length : " + delList.length);
		MemberDAO dao = new MemberDAO();
		boolean success = false;
		try {
			if (dao.delete(delList) == delList.length) {
				success = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dao.resClose();
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("del", success);
			Gson gson = new Gson();
			String obj = gson.toJson(map);
			System.out.println(obj);
			resp.getWriter().println(obj);
		}
		
	}

	
=======
>>>>>>> 16616679d617e1e5a89f4e482e0bb4bae5ba005a
}
