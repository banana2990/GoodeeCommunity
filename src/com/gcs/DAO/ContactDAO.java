package com.gcs.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.gcs.DTO.ContactDTO;

public class ContactDAO {
	
	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	public void BoardDAO() {
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
	
	public ArrayList<ContactDTO> contact() {
		String sql = "SELECT contact_no, writer, subject, c_email, content, c_status FROM contact ORDER BY contact_no DESC";
		System.out.println(sql);
		ArrayList<ContactDTO> contact = new ArrayList<ContactDTO>();
		try {		
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()){
				ContactDTO dto = new ContactDTO();
				dto.setContact_no(rs.getInt("contact_no"));
				dto.setWriter(rs.getString("writer"));
				dto.setSubject((rs.getString("subject")));
				dto.setC_email((rs.getString("c_email")));
				dto.setContent((rs.getString("content")));;
				dto.setC_status((rs.getString("c_status")));
				contact.add(dto);  
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			resClose();
		}
		return contact;
	}
}
