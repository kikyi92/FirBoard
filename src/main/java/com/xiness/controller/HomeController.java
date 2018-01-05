package com.xiness.controller;

import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

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
import com.xiness.service.BoardService;

// 답변형진행중
/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	@Inject
	private BoardService service;

	@RequestMapping(value = "/")
	public String home(Locale locale, Model model) throws Exception {
		logger.info("home!");

		List<BoardVO> BoardList = service.selectBoard();
		model.addAttribute("BoardList", BoardList);

		return "home";
	}

	@RequestMapping(value = "boardWritePage.do")
	public String boardWritePage(Locale locale, Model model) throws Exception {
		logger.info("boardWritePage!");

		return "boardWrite";
	}

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

}