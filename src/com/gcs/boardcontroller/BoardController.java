package com.gcs.boardcontroller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gcs.boardservice.BoardService;;

@WebServlet({"/boardList"})
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
		case "/list":
			boardService.list();
			break;
			
		}
		
	}
	
}
