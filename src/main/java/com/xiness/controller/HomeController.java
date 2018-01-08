package com.xiness.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.xiness.dto.BoardVO;
import com.xiness.paging.Paging;
import com.xiness.service.BoardService;

@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	@Inject
	private BoardService service;

	// 메인페이지
	@RequestMapping(value = "/")
	public String home(Locale locale, Model model, HttpServletRequest req) throws Exception {
		logger.info("home");

		int currentPageNo = 1; //현재페이지
		int maxPost = 10;	 // 하나의 페이지에 표시할 게시물 갯수
		
		if(req.getParameter("pages") != null) //게시물이 1개도없으면(=페이지가 생성이 안되었으면)이 아니라면 == 페이징이 생성되었다면	 													 
			currentPageNo = Integer.parseInt(req.getParameter("pages"));//pages에있는 string 타입 변수를 int형으로 바꾸어서 currentPageNo에 담는다.
		
		Paging paging = new Paging(currentPageNo, maxPost);  //Paging.java에있는 currentPAgeNo, maxPost를 paging변수에 담는다.
		
		int offset = (paging.getCurrentPageNo() -1) * paging.getmaxPost(); // query.xml에서 select를 할때 사용하기위한 offset 변수의 선언. 
		// 현재 3페이지 이고, 그 페이지에 게시물이 10개가 있다면 offset값은 (3-1) * 10 = 20이 된다.
		/*//test
		//중요공지사항을 사용하기 위한 것
		ArrayList<BoardVO> infolist = new ArrayList<BoardVO>();     	
		infolist = (ArrayList<BoardVO>) service.writeInfoList();
		//test
*/		ArrayList<BoardVO> page = new ArrayList<BoardVO>(); // BoardVO 에 있는 변수들을 ArrayList 타입의 배열로 둔 다음 이를 page라는 변수에 담는다.
		page = (ArrayList<BoardVO>) service.writeList(offset, paging.getmaxPost()); 
		//writeService.java에 있는 writeList 함수를 이용하여 offset값과 maxPost값을 ArrayList 타입의 배열로 담고, 이 배열 자체를 page 변수에 담는다.
		paging.setNumberOfRecords(service.writeGetCount()); // 페이지를 표시하기 위해 전체 게시물 수를 파악하기 위한것
		paging.makePaging(); 

		//List<BoardVO> BoardList = service.selectBoard(); writeList
		//model.addAttribute("BoardList",BoardList); //이부분이 예전에 쓰던거고
		//model.addAttribute("infolist",infolist); //중요공지사항을 사용하기 위한것
		model.addAttribute("page", page); // 여기가 이제 페이징처리하면서 리스트뿌려주는거거든요 근데 이걸로하면 
		model.addAttribute("paging", paging);
		
		return "home";
	}

	// 글입력
	@RequestMapping(value = "boardInsert.do", produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String boardWriteInsert(@ModelAttribute BoardVO param) throws Exception{
		logger.info("boardWriteInsert!");
		
		System.out.println("카테고리 : " + param.getCate());
		System.out.println("글제목 : " + param.getTitle());
		System.out.println("글내용 : " + param.getContent());
		System.out.println("작성자 : " + param.getWriter());
		System.out.println("작성일 : " + param.getDatetime());
		System.out.println("공개범위 : " + param.getPubpriv());
		System.out.println("출처 : " + param.getSource());
		// 글입력
		service.insertBoard(param);

		// groupno 입력
		System.out.println("글번호 >>> : " + param.getNo());
		int no = param.getNo();
		service.updateGroupNo(no);
		
		List<BoardVO> BoardList = service.selectBoard();
		Gson gson = new Gson();
		String js = gson.toJson(BoardList);
		System.out.println("JSON >>>: " + js);
		
		return js;
	}
	
	// 글상세보기
	@RequestMapping(value = "detail.ajax", produces = "application/json; charset=utf-8")
	@ResponseBody
	public String detail(Locale locale, Model model,@RequestParam(value="no") Integer no) throws Exception{
		logger.info("detail...");
		List<BoardVO> datailBoardList = service.detailBoard(no);
			
		model.addAttribute("datailBoardList",datailBoardList);
		System.out.println("detailBoard : >>> "+datailBoardList );
		System.out.println("no : >>>" + no );
			
		Gson gson = new Gson();
		String json = gson.toJson(datailBoardList);
		System.out.println("json : >>>" + json);
		return json;
	}
	
	// 글삭제
	@RequestMapping(value = "boardDelete.do", produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String boardDelete(@RequestParam("no") int no) throws Exception {

		System.out.println("지울 글번호 : " + no);
		service.boardDelete(no);
		
		List<BoardVO> bList = service.selectBoard();
		Gson gson = new Gson();
		String js = gson.toJson(bList);
		System.out.println("JSON >>>: " + js);
		
		return js;
	}
	
	// 글수정
	@RequestMapping(value = "boardUpdate.do", produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String boardUpdate(@ModelAttribute BoardVO param) throws Exception {
		System.out.println("Controller boardUpdate()");
		System.out.println("글번호 : " + param.getNo());
		System.out.println("카테고리 : " + param.getCate());
		System.out.println("제목 : " + param.getTitle());
		System.out.println("내용 : " + param.getContent());
		System.out.println("작성자 : " + param.getWriter());
		System.out.println("작성일 : " + param.getDatetime());
		System.out.println("공개여부 : " + param.getPubpriv());
		System.out.println("출처 : " + param.getSource());
		
		service.boardUpdate(param);
		
		List<BoardVO> bList = service.selectBoard();
		Gson gson = new Gson();
		String js = gson.toJson(bList);
		System.out.println("JSON >>>: " + js);
		
		return js;
	}
	
	// 답글버튼 클릭시
		@RequestMapping(value = "parentInfo.do", produces="text/plain;charset=UTF-8")
		@ResponseBody
		public String parentInfo(@ModelAttribute BoardVO param) throws Exception{
			logger.info("parentInfo!");
			
			System.out.println("부모글번호 : " + param.getNo());
			int parentNo = param.getNo();
			
			// 부모글 정보 조회
			List<BoardVO> parentInfo = service.selectParentInfo(parentNo);
			System.out.println("부모no >>> : " + parentInfo.get(0).getNo());
			System.out.println("부모groupno >>> : " + parentInfo.get(0).getGroupno());
			System.out.println("부모indent >>> : " + parentInfo.get(0).getIndent());
			System.out.println("부모depth >>> : " + parentInfo.get(0).getDepth());
			System.out.println("부모제목 >>> : " + parentInfo.get(0).getTitle());
			
			param.setTitle("[RE]:"+parentInfo.get(0).getTitle());
			param.setGroupno(parentInfo.get(0).getGroupno());
			param.setIndent(parentInfo.get(0).getIndent()+1);
			param.setDepth(parentInfo.get(0).getDepth()+1);
			
			Gson gson = new Gson();
			String js = gson.toJson(param);
			System.out.println("JSON >>>: " + js);
			
			return js;
		}
	
	// 답글입력시
	@RequestMapping(value = "replyInsert.do", produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String replyInsert(@ModelAttribute BoardVO param) throws Exception{
		System.out.println("replyInsert() 답글입력시!");
		
		System.out.println("부모글번호 : " + param.getNo());
		int parentNo = param.getNo();
		
		
		// 부모글 정보 조회
		List<BoardVO> parentInfo = service.selectParentInfo(parentNo);
		System.out.println("답글입력시 참조할 부모정보-------------------------------------");
		System.out.println("부모제목 >>> : " + parentInfo.get(0).getTitle());
		System.out.println("부모no >>> : " + parentInfo.get(0).getNo());
		System.out.println("부모groupno >>> : " + parentInfo.get(0).getGroupno());
		System.out.println("부모indent >>> : " + parentInfo.get(0).getIndent());
		System.out.println("부모depth >>> : " + parentInfo.get(0).getDepth());
		
		// depth 증가
		service.updateDepthNo(parentInfo.get(0));
		
		// 읽어온 답글정보
		System.out.println("답글 카테고리 : " + param.getCate());
		System.out.println("답글 글제목 : " + param.getTitle());
		System.out.println("답글 글내용 : " + param.getContent());
		System.out.println("답글 작성자 : " + param.getWriter());
		System.out.println("답글 작성일 : " + param.getDatetime());
		System.out.println("답글 공개범위 : " + param.getPubpriv());
		System.out.println("답글 출처 : " + param.getSource());
		
		
		System.out.println("부모groupno >>> : " + parentInfo.get(0).getGroupno());
		System.out.println("부모indent >>> : " + parentInfo.get(0).getIndent());
		System.out.println("부모depth >>> : " + parentInfo.get(0).getDepth());
		
		param.setGroupno(parentInfo.get(0).getGroupno());
		param.setIndent(parentInfo.get(0).getIndent());
		param.setDepth(parentInfo.get(0).getDepth());
		
		//답글입력
		service.replyInsert(param);
		

//		// groupno 입력
//		System.out.println("글번호 >>> : " + param.getNo());
//		int no = param.getNo();
//		service.updateGroupNo(no);
//		
		List<BoardVO> BoardList = service.selectBoard();
		Gson gson = new Gson();
		String js = gson.toJson(BoardList);
		System.out.println("JSON >>>: " + js);
		
		return js;
	}
	
	// 검색 
	@RequestMapping(value = "searchAjax.do", produces = "application/json; charset=utf-8")
	@ResponseBody
	public String search(Locale locale, Model model,@RequestParam(value="keyword") String keyword) throws Exception{
		logger.info("search...");
		
		System.out.println("keywordVal >>> : " + keyword);
			
		List<BoardVO> searchBoardList = service.searchBoard(keyword);
			
		Gson gson = new Gson();
		String json = gson.toJson(searchBoardList);
		System.out.println("json : >>>" + json);
		return json;
	}
	

}