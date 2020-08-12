package com.gcs.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.gcs.DTO.BoardDTO;

public class BoardDAO {
	
	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	public BoardDAO() {
		try {
			Context ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/Oracle");
			conn = ds.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	public void resClose() {
		try {
			if(rs!=null) {rs.close();}
			if(ps!=null) {ps.close();}
			if(conn!=null) {conn.close();}
		}catch(Exception e) {
			e.printStackTrace();
		}		
	}

	public ArrayList<BoardDTO> commentlist() {
		String sql = "SELECT comment_no, board_no, id, co_content, co_reg_date FROM commentary ORDER BY comment_no DESC";
		ArrayList<BoardDTO> list = new ArrayList<BoardDTO>();
		try {		
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()){
				BoardDTO dto = new BoardDTO();
				dto.setComment_no(rs.getInt("comment_no"));
				dto.setBoard_no(rs.getInt("board_no"));
				dto.setId(rs.getString("id"));
				dto.setCo_content(rs.getString("co_content"));
				dto.setCo_reg_date(rs.getDate("co_reg_date"));
				//게시판을 가져와야함! 게시판을 가져오는 건... 조인...
				list.add(dto);  
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			resClose();
		}
		return list;
	}

	public boolean write(String mboard_no, String id, String subject, String content) {
		String sql = "INSERT INTO board (board_no, mBoard_no, id, bo_subject, bo_content, bo_bHit) VALUES (seq_board.NEXTVAL,?,?,?,?,0)";
		boolean result = false;
		try {
			conn.setAutoCommit(false);
			
			ps = conn.prepareStatement(sql);
			ps.setString(1, mboard_no);
			ps.setString(2, id);
			ps.setString(3, subject);
			ps.setString(4, content);
			
			int success = ps.executeUpdate();
			System.out.println(success);
			if(success>0) {
				result = true;
				conn.commit();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return result;	
	}

	public boolean delmngcomment(String comment_no) {
		String sql = "UPDATE commentary SET co_content = '관리자에 의해 삭제된 댓글입니다.' WHERE  comment_no=?";
		boolean result = false;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, comment_no);
			
			int success = ps.executeUpdate();
			if(success > 0) {
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			resClose();
		}
		return result;
	}


	public ArrayList<BoardDTO> boardlist(String mBoard_no) throws SQLException {
	      ArrayList<BoardDTO> list = new ArrayList<BoardDTO>();
	      String sql ="SELECT * FROM board WHERE mBoard_no=? ORDER BY board_no DESC";
	      ps = conn.prepareStatement(sql);
	      ps.setString(1, mBoard_no);
	      rs = ps.executeQuery();
	      
	      while(rs.next()) {
	         BoardDTO dto = new BoardDTO();
	         dto.setBoard_no(rs.getInt("board_no"));
	         dto.setId(rs.getString("Id"));
	         dto.setBo_subject(rs.getString("bo_subject"));
	         dto.setBo_content(rs.getNString("bo_content"));
	         dto.setBo_reg_date(rs.getDate("bo_reg_date"));
	         dto.setBo_bHit(rs.getInt("bo_bHit"));
	         list.add(dto);	         
	      }
	      return list;
	}

	public ArrayList<BoardDTO> boardList(String mboard_no, int startPage, int endPage) throws SQLException {
		ArrayList<BoardDTO> list = new ArrayList<BoardDTO>();
		
		String sql = "SELECT r.rnum, r.board_no, r.mboard_no, r.id, r.bo_subject, r.bo_content, r.bo_reg_date, r.bo_bhit, m.boardname, r.nickname " + 
				"FROM (SELECT ROW_NUMBER() OVER(ORDER BY board_no DESC) AS rnum, board_no, mboard_no, id, bo_subject, bo_content, bo_reg_date, bo_bhit, nickname " + 
				"FROM (SELECT b.board_no, b.mboard_no, b.id, b.bo_subject, b.bo_content, b.bo_reg_date, b.bo_bhit, m.nickname " + 
				"FROM board b, member m WHERE m.id = b.id) WHERE mboard_no=?) r, mboard m WHERE r.mboard_no = m.mboard_no AND rnum BETWEEN ? AND ?";
		
		ps = conn.prepareStatement(sql);
		ps.setString(1, mboard_no);
		ps.setInt(2, startPage);
		ps.setInt(3, endPage);
		
		rs = ps.executeQuery();		
		
		while(rs.next()) {
			BoardDTO dto = new BoardDTO();
			dto.setBoard_no(rs.getInt("board_no"));
			dto.setMboard_no(rs.getInt("mboard_no"));
			dto.setId(rs.getString("id"));
			dto.setBo_subject(rs.getString("bo_subject"));
			dto.setBo_content(rs.getString("bo_content"));
			dto.setBo_reg_date(rs.getDate("bo_reg_date"));
			dto.setBo_bHit(rs.getInt("bo_bHit"));
			dto.setBoardname(rs.getString("boardName"));
			dto.setNickName(rs.getString("nickName"));	
			
			list.add(dto);
		}
		
		return list;	
	}

	public int listCnt(String mboard_no) throws SQLException {
		
		String sql = "SELECT COUNT(*) AS num FROM board WHERE mboard_no=?";
		int cnt = 0;
		ps = conn.prepareStatement(sql);
		ps.setString(1, mboard_no);
		
		rs = ps.executeQuery();
		if(rs.next()) {
			cnt = rs.getInt("num");
		}
		
		return cnt;
	}


	public BoardDTO boardDetail(String board_no) throws SQLException {
		BoardDTO dto = new BoardDTO();
		
		String sql = "SELECT * FROM (SELECT b.board_no, b.mboard_no, b.id, b.bo_subject, b.bo_content, b.bo_reg_date, b.bo_bhit, b.boardname, m.nickname " + 
				"FROM (SELECT b.board_no, b.mboard_no, b.id, b.bo_subject, b.bo_content, b.bo_reg_date, b.bo_bhit, m.boardname " + 
				"FROM board b, mboard m WHERE b.mboard_no = m.mboard_no) b, member m " + 
				"WHERE b.id= m.id) WHERE board_no=?";
		
		ps = conn.prepareStatement(sql);
		ps.setString(1, board_no);
		rs = ps.executeQuery();
		
		if(rs.next()) {
			dto.setBoard_no(rs.getInt("board_no"));
			dto.setMboard_no(rs.getInt("mboard_no"));
			dto.setId(rs.getString("id"));
			dto.setBo_subject(rs.getString("bo_subject"));
			dto.setBo_content(rs.getString("bo_content"));
			dto.setBo_reg_date(rs.getDate("bo_reg_date"));			
			dto.setNickName(rs.getString("nickname"));
			dto.setBoardname(rs.getString("boardname"));
			upHit(rs.getInt("board_no"));
			dto.setBo_bHit(rs.getInt("bo_bHit"));
		}
		
	
		return dto;
	}

	public void recomment(String comment_no, String id, String reco_content){
		String sql ="INSERT INTO recomment (recomment_no, comment_no, id, reco_content) VALUES (SEQ_RECOMMENT.nextval, ?, ?, ?)";
		boolean success = false;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, comment_no);
			ps.setString(2, id);
			ps.setString(3, reco_content);
			if(ps.executeUpdate()>0) {
				success = true;
			}
			System.out.println(success);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			resClose();
		}
		
	}



	private void upHit(int board_no) {
		String sql = "UPDATE board SET bo_bHit = bo_bHit+1 WHERE board_no = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, board_no);
			int success = ps.executeUpdate();
			System.out.println("조회수 올리기 성공 : "+success);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	public ArrayList<BoardDTO> blikeCnt(ArrayList<BoardDTO> list) {
		
		ArrayList<BoardDTO> blikeCnt = new ArrayList<BoardDTO>();
		
		for (int i = 0; i < list.size(); i++) {
			
			String sql = "SELECT COUNT(board_no) FROM blike WHERE board_no=?";

				try {
					ps = conn.prepareStatement(sql);
					ps.setInt(1, list.get(i).getBoard_no());
					rs = ps.executeQuery();
					
					if(rs.next()) {
						BoardDTO dto = new BoardDTO();
						dto.setBlike_cnt(rs.getString("COUNT(board_no)"));
						blikeCnt.add(dto);
						ps.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				} 

		}
		
		return blikeCnt;
	}

	public ArrayList<BoardDTO> commentCnt(ArrayList<BoardDTO> list) {
		
		ArrayList<BoardDTO> commentCnt = new ArrayList<BoardDTO>();
		
		for (int i = 0; i < list.size(); i++) {
			String sql = "SELECT COUNT(*) FROM commentary c, recomment r WHERE board_no=?";
			try {
				ps = conn.prepareStatement(sql);
				ps.setInt(1, list.get(i).getBoard_no());
				rs = ps.executeQuery();
				
				if(rs.next()) {
					BoardDTO dto = new BoardDTO();
					dto.setCommentCnt(rs.getString("COUNT(*)"));
					commentCnt.add(dto);
					ps.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
		return commentCnt;
		
	}


	public ArrayList<BoardDTO> allBoard(int startPage, int endPage) throws SQLException {
		ArrayList<BoardDTO> list = new ArrayList<BoardDTO>();
		
		String sql = "SELECT r.rnum, r.board_no, r.mboard_no, r.id, r.bo_subject, r.bo_content, r.bo_reg_date, r.bo_bhit, m.boardname, r.nickname " + 
				"FROM (SELECT ROW_NUMBER() OVER(ORDER BY board_no DESC) AS rnum, board_no, mboard_no, id, bo_subject, bo_content, bo_reg_date, bo_bhit, nickname " + 
				"FROM (SELECT b.board_no, b.mboard_no, b.id, b.bo_subject, b.bo_content, b.bo_reg_date, b.bo_bhit, m.nickname " + 
				"FROM board b, member m WHERE m.id = b.id)) r, mboard m WHERE r.mboard_no = m.mboard_no AND rnum BETWEEN ? AND ? ORDER BY BOARD_NO DESC";
		
		ps = conn.prepareStatement(sql);
		ps.setInt(1, startPage);
		ps.setInt(2, endPage);
		
		rs = ps.executeQuery();		
		
		while(rs.next()) {
			BoardDTO dto = new BoardDTO();
			dto.setBoard_no(rs.getInt("board_no"));
			dto.setMboard_no(rs.getInt("mboard_no"));
			dto.setId(rs.getString("id"));
			dto.setBo_subject(rs.getString("bo_subject"));
			dto.setBo_content(rs.getString("bo_content"));
			dto.setBo_reg_date(rs.getDate("bo_reg_date"));
			dto.setBo_bHit(rs.getInt("bo_bHit"));
			dto.setBoardname(rs.getString("boardName"));
			dto.setNickName(rs.getString("nickName"));	
			
			list.add(dto);
		}
		
		return list;	
		
	}

	public int AllListCnt() throws SQLException {
		String sql = "SELECT COUNT(*) AS num FROM board";
		int cnt = 0;
		ps = conn.prepareStatement(sql);
		
		rs = ps.executeQuery();
		if(rs.next()) {
			cnt = rs.getInt("num");
		}
		
		return cnt;
	}



	public boolean update(String mboard_no, String board_no, String bo_subject, String bo_content) {
		boolean result = false;
		String sql = "UPDATE board SET mboard_no=?, bo_subject=?, bo_content=? WHERE board_no=?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, mboard_no);
			ps.setString(2, bo_subject);
			ps.setString(3, bo_content);
			ps.setString(4, board_no);
			
			if(ps.executeUpdate()>0) {
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			resClose();
		}
		return result;
	}

	public boolean del(String board_no) {
		String sql ="DELETE FROM board WHERE board_no=?";
		boolean result = false;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, board_no);
			
			if(ps.executeUpdate()>0) {
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			resClose();
		}
		return result;
	}
}

