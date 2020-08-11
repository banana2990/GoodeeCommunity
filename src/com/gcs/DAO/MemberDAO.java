package com.gcs.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.gcs.DTO.PhotoDTO;

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

	public boolean join(String id, String pw, String name, String nickName, String email, String emailChk) throws SQLException{
		boolean success = false;
		String sql = "INSERT INTO member (id, pw, name, nickName, u_email, u_email_checked) VALUES (?,?,?,?,?,?)";
		ps = con.prepareStatement(sql);
		ps.setString(1, id);
		ps.setString(2,pw);
		ps.setString(3,name);
		ps.setString(4,nickName);
		ps.setString(5,email);
		ps.setString(6, emailChk);
		int result = ps.executeUpdate();
		/* => 가입시에 바로 기본 이미지를 한다고 하면!
		long pk = 0;
		rs = ps.getGeneratedKeys();
		if(rs.next()) {
			pk = rs.getLong(1);
			System.out.println("id: "+id);
			if(dto.getOriFileName()!=null) { // photo 테이블에 데이터 추가
				sql="INSERT INTO photo (photo_no, id, oriName, newName) VALUES (seq_photo.NEXTVAL, ?,?,?)";
				ps = con.prepareStatement(sql);
				ps.setLong(1, id);
				ps.setString(2, dto.getOriName());
				ps.setString(3, dto.getNewName());
				ps.executeUpdate();
		*/
		if(result>0) {
			success=true;
		}
		resClose();
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
		resClose();
		return id;	
	}

	public int findpw(String email, int dice) throws SQLException {
		String sql = "UPDATE member SET pw=? WHERE u_email=?";
		ps = con.prepareStatement(sql);
		ps.setInt(1, dice);
		ps.setString(2, email);		
		int success = ps.executeUpdate();
		return success;	

	}

	public void pupload(PhotoDTO pdto) throws SQLException {
		String sql ="";
		String id = pdto.getId();
		System.out.println("ID: "+id);
		if(pdto.getOriName()!=null) { // photo 테이블에 데이터 추가
			sql="INSERT INTO photo (photo_no, id, oriName, newName) VALUES (photo_seq.NEXTVAL, ?,?,?)";
			ps = con.prepareStatement(sql);
			ps.setString(1, id);
			ps.setString(2, pdto.getOriName());
			ps.setString(3, pdto.getNewName());
			ps.executeUpdate();
		}
		resClose();
		
	}	

}
