package com.xiness.dto;

public class BoardVO {
	private Integer no;	 //疫뀐옙甕곕뜇�깈
	private String cate; // �겫袁⑥첒
	private String title;// 占쎌젫筌륅옙
	private String content; // 占쎄땀占쎌뒠
	private String writer; //占쎌삂占쎄쉐占쎌쁽
	private String datetime; //占쎌뿯占쎌젾占쎄텊筌욑옙
	private Integer viewcnt; // 鈺곌퀬�돳占쎈땾
	private String source; //占쎌긿占쎈��
	private String pubpriv; //�⑤벀而삼옙肉ч겫占�
	private int groupno;
	private int indent;
	private int depth;
	
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
	public int getGroupno() {
		return groupno;
	}
	public void setGroupno(int groupno) {
		this.groupno = groupno;
	}
	public int getIndent() {
		return indent;
	}
	public void setIndent(int indent) {
		this.indent = indent;
	}
	public int getDepth() {
		return depth;
	}
	public void setDepth(int depth) {
		this.depth = depth;
	}
	
}