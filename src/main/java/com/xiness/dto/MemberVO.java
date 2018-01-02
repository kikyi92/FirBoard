package com.xiness.dto;

public class MemberVO {
	private Integer no;	 //글번호
	private String cate; // 분류
	private String title;// 제목
	private String content; // 내용
	private String writer; //작성자
	private Integer datetime; //입력날짜
	private String option; //옵션
	private String pubpriv; //공개여부
	
	public Integer getNo() {
		return no;
	}
	public void setNo(Integer no) {
		this.no = no;
	}
	public String getCate() {
		return cate;
	}
	public void setCate(String cate) {
		this.cate = cate;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public Integer getDatetime() {
		return datetime;
	}
	public void setDatetime(Integer datetime) {
		this.datetime = datetime;
	}
	public String getOption() {
		return option;
	}
	public void setOption(String option) {
		this.option = option;
	}
	public String getPubpriv() {
		return pubpriv;
	}
	public void setPubpriv(String pubpriv) {
		this.pubpriv = pubpriv;
	}
	
	}
