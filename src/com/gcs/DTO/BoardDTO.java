package com.gcs.DTO;

import java.util.Date;

public class BoardDTO {
	private int board_no;
	private int mboard_no;
	private String id;
	private String bo_subject;
	private String bo_content;
	private Date bo_reg_date;
	private int bo_bHit;
	
	private int comment_no;
	private String co_content;
	private Date co_reg_date;
		
	
	public int getBoard_no() {
		return board_no;
	}
	public void setBoard_no(int board_no) {
		this.board_no = board_no;
	}
	public int getMboard_no() {
		return mboard_no;
	}
	public void setMboard_no(int mboard_no) {
		this.mboard_no = mboard_no;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getBo_subject() {
		return bo_subject;
	}
	public void setBo_subject(String bo_subject) {
		this.bo_subject = bo_subject;
	}
	public String getBo_content() {
		return bo_content;
	}
	public void setBo_content(String bo_content) {
		this.bo_content = bo_content;
	}
	public Date getBo_reg_date() {
		return bo_reg_date;
	}
	public void setBo_reg_date(Date bo_reg_date) {
		this.bo_reg_date = bo_reg_date;
	}
	public int getBo_bHit() {
		return bo_bHit;
	}
	public void setBo_bHit(int bo_bHit) {
		this.bo_bHit = bo_bHit;
	}
	public int getComment_no() {
		return comment_no;
	}
	public void setComment_no(int comment_no) {
		this.comment_no = comment_no;
	}
	public String getCo_content() {
		return co_content;
	}
	public void setCo_content(String co_content) {
		this.co_content = co_content;
	}
	public Date getCo_reg_date() {
		return co_reg_date;
	}
	public void setCo_reg_date(Date co_reg_date) {
		this.co_reg_date = co_reg_date;
	}
}
