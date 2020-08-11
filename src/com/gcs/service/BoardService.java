package com.gcs.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.sql.SQLException;
import java.util.HashMap;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.gcs.DAO.BoardDAO;
import com.gcs.DTO.BoardDTO;
import com.google.gson.Gson;


public class BoardService  {
	
	HttpServletRequest req = null;
	HttpServletResponse resp = null;

	public BoardService(HttpServletRequest req, HttpServletResponse resp) {
		this.req = req;
		this.resp = resp;
	}

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
	}

	public void comread() throws ServletException, IOException {
		BoardDAO dao = new BoardDAO();
		ArrayList<BoardDTO> list = dao.commentlist();
		req.setAttribute("list", list);
		RequestDispatcher dis = req.getRequestDispatcher("mngcomment.jsp");
		dis.forward(req, resp);	
	}

	public void delmngcomment() throws ServletException, IOException{
		String idx = req.getParameter("comment_no");
		System.out.println("comment_no : "+idx);
		//DB가 필요한가?
		BoardDAO dao = new BoardDAO();
		String page = "/mngcomment";
		String msg = "수정에 실패했습니다.";
		
		if(dao.delmngcomment(idx)) {
			page = "/mngcomment";
			msg = "수정에 성공 했습니다.";
		}
		req.setAttribute("msg", msg);
		RequestDispatcher dis = req.getRequestDispatcher(page);
		dis.forward(req, resp);
	  	}
		
	public void boardlist(String mBoard_no) throws IOException {
		HashMap<String, Object> map = new HashMap<String, Object>();
	     	 Gson gson = new Gson();	  
       		  BoardDAO dao = new BoardDAO();
        		 ArrayList<BoardDTO> list = null;
        		  try {
			list= dao.boardlist(mBoard_no);
		} catch (SQLException e) {
			e.printStackTrace();
		}
          dao.resClose();
	      map.put("list",list);
	      String obj = gson.toJson(map);
	      System.out.println(obj);
	      resp.setContentType("text/html; charset=UTF-8");
	      resp.getWriter().println(obj);      
		/*
		req.setAttribute("msg", msg);
		RequestDispatcher dis = req.getRequestDispatcher("write.jsp");
		dis.forward(req, resp);
		 */		
	}

	public void boardList() throws IOException, ServletException {
		String mboard_no = req.getParameter("mboard_no");

		String pageParam = req.getParameter("curPage");
		
		System.out.println("전달받은 curPage의 값 = "+pageParam);
		int curPage = 1; // 첫 페이지 1 설정
		int listCnt = 0;
		if(pageParam != null) {
			curPage = Integer.parseInt(pageParam);			
		}
	
		int startPage =  (curPage)*5-4;
		int endPage = (curPage)*5;
		
		System.out.println(mboard_no+"/"+curPage);
	
		ArrayList<BoardDTO> list = null;		
		System.out.println(mboard_no+"게시판번호 /  curPage"+curPage);
		
		BoardDAO dao = new BoardDAO();
		 try {
			 
			listCnt = dao.listCnt(mboard_no); // 총 게시물의 개수
			list = dao.boardList(mboard_no, startPage, endPage); 
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dao.resClose();
			
			Pagination page = new Pagination(listCnt, curPage);
			
			req.setAttribute("list", list);
			req.setAttribute("page", page);
			
			RequestDispatcher dis = req.getRequestDispatcher("boardList.jsp");
			dis.forward(req, resp);
		}

	}
	
	//updateForm
	public void updateForm() throws ServletException, IOException {
		BoardDAO dao = new BoardDAO();
		String idx = req.getParameter("idx");//idx는 req에서 뽑아낼 수 있다
		System.out.println("수정IDX : "+idx);
		BoardDTO dto = dao.detail(idx);//detail에서 그냥 가져옴, board dto를 반환함
		req.setAttribute("bbs", dto);
		RequestDispatcher dis = req.getRequestDispatcher("updateForm.jsp");
		dis.forward(req, resp);
	}

	public void recomment() throws UnsupportedEncodingException, SQLException {
		req.setCharacterEncoding("UTF-8");
		String comment_no = req.getParameter("comment_no");
		String reco_content = req.getParameter("recomment");	
		String id =(String) req.getSession().getAttribute("id");
		System.out.println(comment_no+ reco_content+ id);
		BoardDAO dao = new BoardDAO();
		dao.recomment(comment_no, id, reco_content);
		
	}

	public void boardDetail() throws ServletException, IOException {
		BoardDTO dto = null;
		String board_no = req.getParameter("board_no");
		BoardDAO dao = new BoardDAO();
		
		try {
			dto = dao.boardDetail(board_no);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dao.resClose();
			req.setAttribute("boardDetail", dto);
			
			RequestDispatcher dis = req.getRequestDispatcher("boardDetail.jsp");
			dis.forward(req, resp);
		}
		
		
	}

}
