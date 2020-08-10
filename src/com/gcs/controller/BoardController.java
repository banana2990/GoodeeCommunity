package com.gcs.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gcs.service.BoardService;;

@WebServlet({"/boardList","/mngboard","/mngcomment","/write","/delmngcomment"})
public class BoardController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		proc(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		proc(req, resp);
	}

	private void proc(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException{
		
		String uri = req.getRequestURI();
		String ctx = req.getContextPath();
		String reqAddr = uri.substring(ctx.length());
		
		BoardService boardService = new BoardService(req, resp);
		
		switch(reqAddr) {
		case "/boardList": // 이거 주소..확인 필요
			System.out.println("게시판 리스트 띄우기");
			boardService.boardList();
			break;
			
		case "/write":
			boardService.write();
			System.out.println("글쓰기 요청");
			break;
		
		case "/mngboard": // 관리자 게시판별 게시글 목록
			String mBoard_no = req.getParameter("mBoard_no");
			System.out.println(mBoard_no);
			boardService.boardlist(mBoard_no);
			break;
			
		case "/mngcomment": // 관리자 댓글 전체 목록
			System.out.println("댓글 목록 호출");
			boardService.comread();			
			break;

		
		case "/deletecom":
			break;
			
		case "/delmngcomment"://관리자 댓글 삭제
			System.out.println("관리자 댓글 삭제 ");
			boardService.delmngcomment();
			break;
		}
	}
}
