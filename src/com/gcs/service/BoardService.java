package com.gcs.service;

import java.io.IOException;

import java.util.ArrayList;

import java.io.UnsupportedEncodingException;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gcs.DAO.BoardDAO;


import com.gcs.DAO.MemberDAO;


import com.gcs.DTO.BoardDTO;



import com.google.gson.Gson;



public class BoardService  {
	
	HttpServletRequest req = null;
	HttpServletResponse resp = null;

	public BoardService(HttpServletRequest req, HttpServletResponse resp) {
		this.req = req;
		this.resp = resp;
	}


	public void list() {
		
	}


	public void comread() throws ServletException, IOException {
		BoardDAO dao = new BoardDAO();
		ArrayList<BoardDTO> list = dao.commentlist();
		req.setAttribute("list", list);
		RequestDispatcher dis = req.getRequestDispatcher("mngcomment.jsp");

		dis.forward(req, resp);	
	}


	public void write() throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String id =(String) req.getSession().getAttribute("id");
		String mboard_no = req.getParameter("mboard_no");
		String subject = req.getParameter("subject");
		String content = req.getParameter("content");
		String msg = "글 작성에 실패했습니다.";
		
		BoardDAO dao = new BoardDAO();
		if(dao.write(mboard_no, id, subject, content)) {
			msg = "글이 작성되었습니다.";

			req.setAttribute("msg", msg);
			RequestDispatcher dis = req.getRequestDispatcher("write.jsp");
			dis.forward(req, resp);
		}else {
			req.setAttribute("msg", msg);
			RequestDispatcher dis = req.getRequestDispatcher("write.jsp");
			dis.forward(req, resp);

		}		
	}

	public void delmngcomment() throws ServletException, IOException{
		String idx = req.getParameter("comment_no");
		System.out.println("comment_no : "+idx);
		//DB가 필요한가?
		BoardDAO dao = new BoardDAO();
		
		String page = "/mngcomment";
		String msg = "수정에 실패했습니다.";
		
		if(dao.delmngcomment(idx)) {
			page = "/mngcomment";
			msg = "수정에 성공 했습니다.";

		}

		req.setAttribute("msg", msg);
		RequestDispatcher dis = req.getRequestDispatcher(page);
		dis.forward(req, resp);
	  	}
		

	

	public void boardlist(String mBoard_no) throws IOException {
		
		HashMap<String, Object> map = new HashMap<String, Object>();
	     	 Gson gson = new Gson();	  
       		  BoardDAO dao = new BoardDAO();
        		 ArrayList<BoardDTO> list = null;
        		  try {
			list= dao.boardlist(mBoard_no);
		} catch (SQLException e) {
			e.printStackTrace();
		}
          dao.resClose();
	      map.put("list",list);
	      String obj = gson.toJson(map);
	      System.out.println(obj);
	      resp.setContentType("text/html; charset=UTF-8");
	      resp.getWriter().println(obj);      
		/*

		req.setAttribute("msg", msg);
		RequestDispatcher dis = req.getRequestDispatcher("write.jsp");
		dis.forward(req, resp);
*/		
	}

	public void boardList() throws IOException {
		 String mboard_no = req.getParameter("mboard_no");
		String page = req.getParameter("page");
		int startPage =  (Integer.parseInt(page)*5)-4;
		int endPage = Integer.parseInt(page)*5;
		ArrayList<BoardDTO> list = null;
		
		System.out.println(mboard_no+"/"+page);
		BoardDAO dao = new BoardDAO();
		
		
		
		 try {
			list = dao.boardList(mboard_no, startPage, endPage); 
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dao.resClose();
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("list", list);
			Gson gson = new Gson();
			String obj = gson.toJson(map);
			System.out.println("jsp로 보내지는 success 값 : "+obj);
			resp.setContentType("text/html; charset=UTF-8");
			resp.getWriter().println(obj);
		}


	}



}
