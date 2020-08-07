package com.gcs.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
<<<<<<< HEAD
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.gcs.DTO.BoardDTO;

=======

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

>>>>>>> 42324a82bb65d4f36607f647b3c8260449993487
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

<<<<<<< HEAD
	public ArrayList<BoardDTO> list() {
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
=======
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
		
>>>>>>> 42324a82bb65d4f36607f647b3c8260449993487
	}
}
