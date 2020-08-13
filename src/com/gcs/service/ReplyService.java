package com.gcs.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
		String board_no = req.getParameter("board_no"); //???? 이걸 어떻게 갖고오지?
		String id = (String) req.getSession().getAttribute("id");
		String co_content = req.getParameter("co_content");
		String msg = "댓글 작성에 실패했습니다.";
		System.out.println(board_no+id+co_content);
		
		ReplyDAO dao = new ReplyDAO();
		if(dao.reply(board_no, id, co_content)) {
			msg = "댓글이 작성되었습니다.";

			req.setAttribute("msg", msg);
			RequestDispatcher dis = req.getRequestDispatcher("boardDetail?board_no="+board_no);
			dis.forward(req, resp);
		}else {
			req.setAttribute("msg", msg);
			RequestDispatcher dis = req.getRequestDispatcher("boardDetail?board_no="+board_no);
			dis.forward(req, resp);
			System.out.println("reply service 끝");
		}		
	}


	}
	
	
