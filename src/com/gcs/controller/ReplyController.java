package com.gcs.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.gcs.service.BoardService;
import com.gcs.service.ReplyService;
 

 
@WebServlet("/reply")
public class ReplyController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		process(req,resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		process(req,resp);
	}

	private void process(HttpServletRequest req, HttpServletResponse resp) {
		
		String uri = req.getRequestURI();
		String ctx = req.getContextPath();
		String reqAddr = uri.substring(ctx.length());
		
		BoardService boardService = new BoardService(req, resp);
		
		switch(reqAddr) {
		
		case "/reply":
			
			System.out.println("댓글쓰기");
			break;
		
		} 
		
	}
 
	
	

    /*
    //댓글리스트를 호출할때 맵핑되는 메소드
    @RequestMapping("list.do")
    public ModelAndView list(int bno, ModelAndView mav) {
        List<ReplyDTO> list=replyService.list(bno); //댓글 목록
        mav.setViewName("board/reply_list"); //뷰의 이름
        mav.addObject("list", list); //뷰에 전달할 데이터 저장
        return mav; //뷰로 이동
    }
    
    //댓글 목록을 ArrayList로 리턴
    @RequestMapping("list_json.do")
    public List<ReplyDTO> list_json(int bno){
        return replyService.list(bno);
    }
    
    @RequestMapping("insert.do") //세부적인 url pattern
    public void insert(ReplyDTO dto, HttpSession session) {
        
        //댓글 작성자 아이디
        //현재 접속중인 사용자 아이디
        String userid=(String)session.getAttribute("userid");
        dto.setReplyer(userid);
        
        //댓글이 테이블에 저장됨
        replyService.create(dto);
        //jsp 페이지로 가거나 데이터를 리턴하지 않음
    }
    */
}

