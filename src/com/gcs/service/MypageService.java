package com.gcs.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gcs.DAO.MypageDAO;
import com.gcs.DTO.BoardDTO;

public class MypageService {
	HttpServletRequest req = null;
	HttpServletResponse resp = null;
	
	public MypageService(HttpServletRequest req, HttpServletResponse resp) {
		this.req = req;
		this.resp = resp;
	}

	public void boardList() throws ServletException, IOException {
		String id = (String) req.getSession().getAttribute("id");
		String pageParam = req.getParameter("curPage");
		
		System.out.println("전달받은 curPage의 값 = "+pageParam);
		int curPage = 1; // 첫 페이지 1 설정
		int listCnt = 0;
		if(pageParam != null) {
			curPage = Integer.parseInt(pageParam);			
		}
	
		int startPage =  (curPage)*5-4;
		int endPage = (curPage)*5;
		
		System.out.println(id+"/"+curPage);
	
		ArrayList<BoardDTO> list = null;		
		ArrayList<BoardDTO> blikeCnt = null;	
		ArrayList<BoardDTO> commentCnt = null;	
		System.out.println(id+" 아이디 /  curPage"+curPage);
		
		MypageDAO dao = new MypageDAO();
		String location = "myboardList_test.jsp"; // 리로드할 곳 up
			
		try {		
				list = dao.boardList(id, startPage, endPage);
				listCnt = dao.listCnt(id); // 총 게시물의 개수			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		// 얘내는 자체적으로 prepareStatement 종료시켜줌
		blikeCnt = dao.blikeCnt(list);
		commentCnt = dao.commentCnt(list);						
		
		dao.resClose();
		
		Pagination page = new Pagination(listCnt, curPage);
		
		req.setAttribute("list", list);
		req.setAttribute("blikeCnt", blikeCnt);
		req.setAttribute("commentCnt", commentCnt);
		req.setAttribute("page", page);
		
		RequestDispatcher dis = req.getRequestDispatcher(location);
		dis.forward(req, resp);

		
	}

	
	
	
}
