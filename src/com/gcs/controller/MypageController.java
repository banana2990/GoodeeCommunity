package com.gcs.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gcs.service.MypageService;

@WebServlet({"/myboard","/mycomment"})
public class MypageController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		process(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		process(req, resp);
	}

	private void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		String uri = req.getRequestURI();
		String ctx = req.getContextPath();
		String reqAddr = uri.substring(ctx.length());
		
		MypageService service = new MypageService(req, resp);
		
		switch(reqAddr) {
		case "/myboard":
			System.out.println("마이페이지 게시글 목록 불러오기");
			service.boardList();
			break;
			
		case "/mycomment":
			break;
		}
		
		
		
		
		
		
		
	}

}
