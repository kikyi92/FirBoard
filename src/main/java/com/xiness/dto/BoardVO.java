package com.xiness.dto;

public class BoardVO {
	private Integer no;	 //�뼨�먯삕�뵓怨뺣쐡占쎄퉰
	private String cate; // 占쎄껀熬곣뫁泥�
	private String title;// �뜝�럩�젷嶺뚮쪋�삕
	private String content; // �뜝�럡���뜝�럩�뮔
	private String writer; //�뜝�럩�굚�뜝�럡�뎽�뜝�럩�겱
	private String datetime; //�뜝�럩肉��뜝�럩�졑�뜝�럡�뀏嶺뚯쉻�삕
	private Integer viewcnt; // �댖怨뚰�э옙�뤂�뜝�럥�빢
	private String source; //�뜝�럩湲욕뜝�럥占쏙옙
	private String pubpriv; //占썩뫀踰��뚯궪�삕�굢�뉕껀�뜝占�
	private int groupno;
	private int indent;
	private int depth;
	private Integer maxPost; // 하나의 페이지에 표시할 게시물 갯수
	private Integer firstPageNo; // db에 있는 게시물을 화면에 보여줄때 처음 보여지는 페이지가 1페이지 즉 1로 지정
	private Integer prevPageNo;  //1~5페이지에는 이전 글자를 생성하지 않는다. 6~10페이지에선 이전버튼이 생성 
							     //6,7,8,9,10 페이지 어디에 있던 이전버튼을 클릭시 5페이지로 이동, 11,12,13,14,15 에서 이전버튼
							     // 클릭시 10 페이지로 이동하도록 하는 변수이다.
	private Integer startPageNo; //이전버튼을 클릭했을시 prePageNo 에는 1,2,3,4,5가 담기는것이고, startPageNo는 이 1,2,3,4,5 페이지
								 //중 어느 페이지를 표시할건지 나타내는 변수 이다. 만약 7페이지에서 이전을 누른다면 startPageno의 값은
								 //5가 된다. (이전페이지의 마지막 페이지)
	
	private Integer currentPageNo; // 현재페이지
	private Integer endPageNo;  /*게시물 갯수가 81개 이여서 finalPage가 9인데, 1~5페이지에서 '다음'버튼을 누르면 sizeOfPage 변수에
								  따라 5개씩 표시가 되어 총 6~10까지가 나오게되는 오류가 발생합니다. 그렇기에 게시물 수에 따른 
								 finalPage까지만 표시 하기위해 endPageNo라는 변수를 만듭니다.. 만약 endPageNo가 없다면 10까지 
								   표시가 되고 10을 눌러도 9페이지로 이동하게 된다.*/
	private Integer nextPageNo; //prevPageNo 의 기능과 반대로 다음버튼을 클릭했을때의 기능을 수행.
	private Integer finalPageNo; // 게시물 수에 따른 마지막페이지의 숫자를 담는 변수
	
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
	public Integer getMaxPost() {
		return maxPost;
	}
	public void setMaxPost(Integer maxPost) {
		this.maxPost = maxPost;
	}
	public Integer getFirstPageNo() {
		return firstPageNo;
	}
	public void setFirstPageNo(Integer firstPageNo) {
		this.firstPageNo = firstPageNo;
	}
	public Integer getPrevPageNo() {
		return prevPageNo;
	}
	public void setPrevPageNo(Integer prevPageNo) {
		this.prevPageNo = prevPageNo;
	}
	public Integer getStartPageNo() {
		return startPageNo;
	}
	public void setStartPageNo(Integer startPageNo) {
		this.startPageNo = startPageNo;
	}
	public Integer getCurrentPageNo() {
		return currentPageNo;
	}
	public void setCurrentPageNo(Integer currentPageNo) {
		this.currentPageNo = currentPageNo;
	}
	public Integer getEndPageNo() {
		return endPageNo;
	}
	public void setEndPageNo(Integer endPageNo) {
		this.endPageNo = endPageNo;
	}
	public Integer getNextPageNo() {
		return nextPageNo;
	}
	public void setNextPageNo(Integer nextPageNo) {
		this.nextPageNo = nextPageNo;
	}
	public Integer getFinalPageNo() {
		return finalPageNo;
	}
	public void setFinalPageNo(Integer finalPageNo) {
		this.finalPageNo = finalPageNo;
	}
}