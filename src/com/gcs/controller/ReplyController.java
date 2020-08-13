package com.gcs.controller;

import java.io.IOException;



import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gcs.service.ReplyService;

@WebServlet("/reply")
public class ReplyController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private void doProcess(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uri = req.getRequestURI();
		String ctx = req.getContextPath();
		String reqAddr = uri.substring(ctx.length());
		
		ReplyService replyService = new ReplyService(req, resp);
		
		
		switch(reqAddr) {
		case "/reply":
			replyService.reply();
			break;
		
		}
	}
		
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("get");
		doProcess(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)	throws ServletException, IOException {
		System.out.println("post");
		doProcess(req, resp);
	

	}
}


