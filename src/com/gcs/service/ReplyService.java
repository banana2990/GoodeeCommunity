package com.gcs.service;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gcs.DAO.ReplyDAO;

public class ReplyService {

	HttpServletRequest req = null;
	HttpServletResponse resp = null;

	public ReplyService(HttpServletRequest req, HttpServletResponse resp) {
		this.req = req;
		this.resp = resp;
	}

	public  void reply() throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String id =(String) req.getSession().getAttribute("id");
		String mboard_no = req.getParameter("mboard_no");
		String subject = req.getParameter("subject");
		String content = req.getParameter("content");
		String msg = "댓글 작성에 실패했습니다.";
		
		ReplyDAO dao = new ReplyDAO();
		if(dao.reply(mboard_no, id, subject, content)) {
			msg = "댓글이 작성되었습니다.";


			req.setAttribute("msg", msg);
			RequestDispatcher dis = req.getRequestDispatcher("view.jsp");
			dis.forward(req, resp);
		}else {
			req.setAttribute("msg", msg);
			RequestDispatcher dis = req.getRequestDispatcher("view.jsp");
			dis.forward(req, resp);

		}		
	}
	
	
}
