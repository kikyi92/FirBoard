package com.xiness.dto;

public class BoardVO {
	private Integer no;	 //湲�踰덊샇
	private String cate; // 遺꾨쪟
	private String title;// �젣紐�
	private String content; // �궡�슜
	private String writer; //�옉�꽦�옄
	private String datetime; //�엯�젰�궇吏�
	private Integer viewcnt; // 議고쉶�닔
	private String source; //�샃�뀡
	private String pubpriv; //怨듦컻�뿬遺�
	
	public Integer getViewcnt() {
		return viewcnt;
	}
	public void setViewcnt(Integer viewcnt) {
		this.viewcnt = viewcnt;
	}
	
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
	public String getDatetime() {
		return datetime;
	}
	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getPubpriv() {
		return pubpriv;
	}
	public void setPubpriv(String pubpriv) {
		this.pubpriv = pubpriv;
	}
	
	}
