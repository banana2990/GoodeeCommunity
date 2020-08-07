package com.gcs.service;

import java.io.IOException;
<<<<<<< HEAD
import java.util.ArrayList;
=======
import java.io.UnsupportedEncodingException;
>>>>>>> 42324a82bb65d4f36607f647b3c8260449993487

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gcs.DAO.BoardDAO;
<<<<<<< HEAD
import com.gcs.DTO.BoardDTO;
=======
>>>>>>> 42324a82bb65d4f36607f647b3c8260449993487


public class BoardService  {
	
	HttpServletRequest req = null;
	HttpServletResponse resp = null;

	public BoardService(HttpServletRequest req, HttpServletResponse resp) {
		this.req = req;
		this.resp = resp;
	}

	public void list() {
		
	}

<<<<<<< HEAD
	public void comread() throws ServletException, IOException {
		BoardDAO dao = new BoardDAO();
		ArrayList<BoardDTO> list = dao.list();
		req.setAttribute("list", list);
		RequestDispatcher dis = req.getRequestDispatcher("mngcomment.jsp");
		dis.forward(req, resp);		
=======
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
		
>>>>>>> 42324a82bb65d4f36607f647b3c8260449993487
	}
}
