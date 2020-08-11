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

}
