package com.gcs.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.gcs.DTO.MemberDTO;

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

	public ArrayList<MemberDTO> memberList() {
		String sql = "SELECT * FROM member";
		ArrayList<MemberDTO> list = new ArrayList<MemberDTO>();
		
		try {
			ps = con.prepareStatement(sql);//3. 쿼리문 실행
			rs = ps.executeQuery();//4. 결과값 가져오기
					
			while(rs.next()) {//값을 하나씩 꺼냄
				MemberDTO dto = new MemberDTO();//DB 에서 가져온 데이터들을 여기에 담을 예정
				dto.setId(rs.getString("id"));
				dto.setPw(rs.getString("pw"));
				dto.setName(rs.getString("name"));
				dto.setNickName(rs.getString("nickName"));
				dto.setU_email(rs.getString("u_email"));
				dto.setU_email_checked(rs.getBoolean("u_email_checked"));
				list.add(dto);//dto를 list에 넣음
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			resClose();//5. 자원 반납
		}
		
		return list;
		
	}

	public boolean memberDel(String id) {
		String sql = "DELETE FROM member WHERE id=?";
		boolean result = false;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, id);
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

	public void pupload(MemberDTO dto) throws SQLException {
		String sql ="";
		String id = dto.getId();
		System.out.println("ID: "+id);
		if(dto.getOriName()!=null) { // photo 테이블에 데이터 추가
			sql="INSERT INTO photo (photo_no, id, oriName, newName) VALUES (photo_seq.NEXTVAL, ?,?,?)";
			ps = con.prepareStatement(sql);
			ps.setString(1, id);
			ps.setString(2, dto.getOriName());
			ps.setString(3, dto.getNewName());
			ps.executeUpdate();
		}
		resClose();
		
	}

	public int delete(String[] delList) throws SQLException {
		String sql = "DELETE FROM member WHERE id = ?";
		int delCount = 0;
		
			
		for(String del : delList) {
			System.out.println("삭제한 글 번호 : "+del);
			ps = con.prepareStatement(sql);
			ps.setString(1, del);
			delCount += ps.executeUpdate();
		}
		System.out.println("삭제한 갯수 : "+delCount);
		return delCount;
	}

	public ArrayList<MemberDTO> list() throws SQLException {
		ArrayList<MemberDTO> list = new ArrayList<MemberDTO>();
		String sql = "SELECT id, name, nickname, u_email, u_email_checked FROM member";
		ps = con.prepareStatement(sql);
		rs = ps.executeQuery();
		
		while(rs.next()) {
			MemberDTO dto = new MemberDTO();
			dto.setId(rs.getString("id"));
			dto.setName(rs.getString("name"));
			dto.setNickName(rs.getString("nickName"));
			dto.setU_email(rs.getString("u_email"));
			dto.setU_email_checked(rs.getBoolean("u_email_checked"));
			list.add(dto);
		}
		
		return list;
	}
			

}
