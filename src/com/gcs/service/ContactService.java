package com.gcs.service;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gcs.DAO.ContactDAO;
import com.gcs.DTO.ContactDTO;

public class ContactService {
	
	HttpServletRequest req = null;
	HttpServletResponse resp = null;

	public ContactService(HttpServletRequest req, HttpServletResponse resp) {
		this.req = req;
		this.resp = resp;
	}

	public void contact() throws ServletException, IOException {
		ContactDAO dao = new ContactDAO();
		ArrayList<ContactDTO> contact = dao.contact();
		req.setAttribute("contact", contact);
		RequestDispatcher dis = req.getRequestDispatcher("contact.jsp");
		dis.forward(req, resp);	
		
	}

	public void ctwrite() throws ServletException, IOException {
		String writer = req.getParameter("writer");
		String subject = req.getParameter("subject");
		String c_email = req.getParameter("c_email");
		String content = req.getParameter("content");
		String msg = "문의사항 보내기가 실패했습니다.";
		
		ContactDAO dao = new ContactDAO();
		
		dao.ctwrite(writer, subject, c_email, content);
		
		
	}
}
