package com.gcs.DAO;

import java.io.IOException;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;



public class ReplyDAO {
	
	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	public ReplyDAO() {
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

	public boolean reply(String board_no, String id, String content) {
		String sql = "";
		boolean result = false;
		try { // id로 이 게시글에 작성한 댓글 있는 지 확인
			sql = "SELECT id FROM commentary WHERE board_no=?";
			
				ps=conn.prepareStatement(sql);
				ps.setString(1, board_no);
				rs = ps.executeQuery();
				System.out.println("1번 쿼리 작동");
			if(!rs.next()) {			 // 작성 댓글 없으면 추가
			sql= "INSERT INTO commentary (comment_no, board_no, id, co_content) VALUES (seq_comment.NEXTVAL, ?, ?, ?)";
			try {
				ps = conn.prepareStatement(sql);
				ps.setString(1, board_no);
				ps.setString(2, id);
				ps.setString(3, content);
				
				int success = ps.executeUpdate();
				System.out.println("2번 쿼리 작동");
				if(success > 0) {
					result = true;
				}
			} catch (SQLException e) {e.printStackTrace();
			}finally {resClose();}			
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("reply DAO 끝");
		return result;
	}
}
