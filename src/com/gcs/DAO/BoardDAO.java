package com.gcs.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
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

	public ArrayList<BoardDTO> boardList(String mboard_no, int startPage, int endPage) throws SQLException {
		
		ArrayList<BoardDTO> list = new ArrayList<BoardDTO>();
		
		String sql = "SELECT r.rnum, r.board_no, r.mboard_no, r.id, r.bo_subject, r.bo_content, r.bo_reg_date, r.bo_bhit, m.boardname " + 
				"FROM (SELECT ROW_NUMBER() OVER(ORDER BY board_no DESC) AS rnum, board_no, mboard_no, id, bo_subject, bo_content, bo_reg_date, bo_bhit FROM board WHERE mboard_no=?) r, mboard m " + 
				"WHERE r.mboard_no = m.mboard_no AND rnum BETWEEN ? AND ?";
		
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
			dto.setBo_bHit(rs.getInt("bo_bhit"));
			dto.setBoardname(rs.getString("boardname"));
			
			list.add(dto);
		}
		
		System.out.println(list);
		return list;
	}
}
