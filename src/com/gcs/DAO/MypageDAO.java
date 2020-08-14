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

public class MypageDAO {

	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	public MypageDAO() {
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

	
	public ArrayList<BoardDTO> boardList(String id, int startPage, int endPage) throws SQLException {
		ArrayList<BoardDTO> list = new ArrayList<BoardDTO>();
		
		String sql = "SELECT ta.rnum, ta.board_no, ta.mBoard_no, ta.id, ta.bo_subject, ta.bo_reg_date, "
				+ "ta.bo_bHit, tb.boardname FROM (SELECT ROW_NUMBER()OVER(ORDER BY board_no DESC) AS rnum, "
				+ "board_no, mBoard_no, id, bo_subject, bo_reg_date, bo_bHit FROM board WHERE id = ?) ta "
				+ "INNER JOIN mBoard tb ON ta.mBoard_no = tb.mboard_no WHERE rnum BETWEEN ? AND ?";

		ps = conn.prepareStatement(sql);
		ps.setString(1, id);
		ps.setInt(2, startPage);
		ps.setInt(3, endPage);
		
		rs = ps.executeQuery();		
		
		while(rs.next()) {
			BoardDTO dto = new BoardDTO();
			dto.setBoard_no(rs.getInt("board_no"));
			dto.setMboard_no(rs.getInt("mboard_no"));
			dto.setId(rs.getString("id"));
			dto.setBo_subject(rs.getString("bo_subject"));
			dto.setBo_reg_date(rs.getDate("bo_reg_date"));
			dto.setBo_bHit(rs.getInt("bo_bHit"));
			dto.setBoardname(rs.getString("boardName"));
			
			list.add(dto);}
			
		return list;
	}

	public int listCnt(String id) throws SQLException {
		String sql = "SELECT COUNT(*) AS num FROM board WHERE id=?";
		int cnt = 0;
		ps = conn.prepareStatement(sql);
		ps.setString(1, id);
		
		rs = ps.executeQuery();
		if(rs.next()) {
			cnt = rs.getInt("num");
		}
		
		return cnt;
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
					dto.setCommentCnt(rs.getInt("COUNT(*)"));
					commentCnt.add(dto);
					ps.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
		return commentCnt;
	}

	
}
