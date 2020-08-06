package com.gcs.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

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
		System.out.println("입력 값 :"+ id + pw + name + nickName + email);
		boolean success = false;
		
		MemberDAO dao = new MemberDAO();
		try {
			success = dao.join(id,pw,name,nickName,email);
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

	
	
	
	
	
	
	
	
	
}
