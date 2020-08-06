package com.gcs.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class MemberDAO {
	
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	public MemberDAO(){
		try {
			Context ctxt = new InitialContext();
			DataSource ds = (DataSource) ctxt.lookup("java:comp/env/jdbc/Oracle");
			con = ds.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	public void resClose() {
		try{
			if(con!=null) {con.close();}
			if(ps!=null) {ps.close();}
			if(rs!=null) {rs.close();}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public boolean login(String id, String pw) throws SQLException {
		String sql = "SELECT id from member WHERE ID=? and PW=?";
		
		boolean result = false;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, id);
			ps.setString(2, pw);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				result = true;
			}	
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {resClose(); System.out.println(result);}		
		return result;
	}

	public boolean overlay(String id) throws SQLException {
		boolean success = false; // id가 있으면 true값이 나옴
		String sql = "SELECT id FROM member WHERE id=?";
		ps = con.prepareStatement(sql);
		ps.setString(1,id);
		rs = ps.executeQuery();
		success = rs.next();
		System.out.println("success: "+success); // 4차 확인
		return success;	
	}

	public boolean overlaynick(String nickname) throws SQLException{
		boolean success = false; // id가 있으면 true값이 나옴
		String sql = "SELECT nickname FROM member WHERE nickname=?";
		ps = con.prepareStatement(sql);
		ps.setString(1,nickname);
		rs = ps.executeQuery();
		success = rs.next();
		System.out.println("success: "+success); // 4차 확인
		return success;	
	} 

	public boolean join(String id, String pw, String name, String nickName, String email) throws SQLException{
		boolean success = false;
		String sql = "INSERT INTO member (id, pw, name, nickName, u_email, u_email_checked) VALUES (?,?,?,?,?,0)";
		ps = con.prepareStatement(sql);
		ps.setString(1, id);
		ps.setString(2,pw);
		ps.setString(3,name);
		ps.setString(4,nickName);
		ps.setString(5,email);		
		int result = ps.executeUpdate();
		if(result>0) {
			success=true;
		}		
		return success;	
	}

	public String findid(String email) throws SQLException {
		String id = "";
		String sql = "SELECT id FROM member WHERE u_email=?";
		ps = con.prepareStatement(sql);
		ps.setString(1,email);
		rs = ps.executeQuery();
		if(rs.next()){
			id= rs.getString("id");
		System.out.println("찾은 id: "+id); // 4차 확인
		}else {
			id="";
		}
		return id;	
	}
	
	
	
	
	
}
