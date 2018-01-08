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

	// ����������
	@RequestMapping(value = "/")
	public String home(Locale locale, Model model, HttpServletRequest req) throws Exception {
		logger.info("home");

		int currentPageNo = 1; //����������
		int maxPost = 10;	 // �ϳ��� �������� ǥ���� �Խù� ����
		
		if(req.getParameter("pages") != null) //�Խù��� 1����������(=�������� ������ �ȵǾ�����)�� �ƴ϶�� == ����¡�� �����Ǿ��ٸ�	 													 
			currentPageNo = Integer.parseInt(req.getParameter("pages"));//pages���ִ� string Ÿ�� ������ int������ �ٲپ currentPageNo�� ��´�.
		
		Paging paging = new Paging(currentPageNo, maxPost);  //Paging.java���ִ� currentPAgeNo, maxPost�� paging������ ��´�.
		
		int offset = (paging.getCurrentPageNo() -1) * paging.getmaxPost(); // query.xml���� select�� �Ҷ� ����ϱ����� offset ������ ����. 
		// ���� 3������ �̰�, �� �������� �Խù��� 10���� �ִٸ� offset���� (3-1) * 10 = 20�� �ȴ�.
		/*//test
		//�߿���������� ����ϱ� ���� ��
		ArrayList<BoardVO> infolist = new ArrayList<BoardVO>();     	
		infolist = (ArrayList<BoardVO>) service.writeInfoList();
		//test
*/		ArrayList<BoardVO> page = new ArrayList<BoardVO>(); // BoardVO �� �ִ� �������� ArrayList Ÿ���� �迭�� �� ���� �̸� page��� ������ ��´�.
		page = (ArrayList<BoardVO>) service.writeList(offset, paging.getmaxPost()); 
		//writeService.java�� �ִ� writeList �Լ��� �̿��Ͽ� offset���� maxPost���� ArrayList Ÿ���� �迭�� ���, �� �迭 ��ü�� page ������ ��´�.
		paging.setNumberOfRecords(service.writeGetCount()); // �������� ǥ���ϱ� ���� ��ü �Խù� ���� �ľ��ϱ� ���Ѱ�
		paging.makePaging(); 

		//List<BoardVO> BoardList = service.selectBoard(); writeList
		//model.addAttribute("BoardList",BoardList); //�̺κ��� ������ �����Ű�
		//model.addAttribute("infolist",infolist); //�߿���������� ����ϱ� ���Ѱ�
		model.addAttribute("page", page); // ���Ⱑ ���� ����¡ó���ϸ鼭 ����Ʈ�ѷ��ִ°Űŵ�� �ٵ� �̰ɷ��ϸ� 
		model.addAttribute("paging", paging);
		
		return "home";
	}

	// ���Է�
	@RequestMapping(value = "boardInsert.do", produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String boardWriteInsert(@ModelAttribute BoardVO param) throws Exception{
		logger.info("boardWriteInsert!");
		
		System.out.println("ī�װ� : " + param.getCate());
		System.out.println("������ : " + param.getTitle());
		System.out.println("�۳��� : " + param.getContent());
		System.out.println("�ۼ��� : " + param.getWriter());
		System.out.println("�ۼ��� : " + param.getDatetime());
		System.out.println("�������� : " + param.getPubpriv());
		System.out.println("��ó : " + param.getSource());
		// ���Է�
		service.insertBoard(param);

		// groupno �Է�
		System.out.println("�۹�ȣ >>> : " + param.getNo());
		int no = param.getNo();
		service.updateGroupNo(no);
		
		List<BoardVO> BoardList = service.selectBoard();
		Gson gson = new Gson();
		String js = gson.toJson(BoardList);
		System.out.println("JSON >>>: " + js);
		
		return js;
	}
	
	// �ۻ󼼺���
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
	
	// �ۻ���
	@RequestMapping(value = "boardDelete.do", produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String boardDelete(@RequestParam("no") int no) throws Exception {

		System.out.println("���� �۹�ȣ : " + no);
		service.boardDelete(no);
		
		List<BoardVO> bList = service.selectBoard();
		Gson gson = new Gson();
		String js = gson.toJson(bList);
		System.out.println("JSON >>>: " + js);
		
		return js;
	}
	
	// �ۼ���
	@RequestMapping(value = "boardUpdate.do", produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String boardUpdate(@ModelAttribute BoardVO param) throws Exception {
		System.out.println("Controller boardUpdate()");
		System.out.println("�۹�ȣ : " + param.getNo());
		System.out.println("ī�װ� : " + param.getCate());
		System.out.println("���� : " + param.getTitle());
		System.out.println("���� : " + param.getContent());
		System.out.println("�ۼ��� : " + param.getWriter());
		System.out.println("�ۼ��� : " + param.getDatetime());
		System.out.println("�������� : " + param.getPubpriv());
		System.out.println("��ó : " + param.getSource());
		
		service.boardUpdate(param);
		
		List<BoardVO> bList = service.selectBoard();
		Gson gson = new Gson();
		String js = gson.toJson(bList);
		System.out.println("JSON >>>: " + js);
		
		return js;
	}
	
	// ��۹�ư Ŭ����
		@RequestMapping(value = "parentInfo.do", produces="text/plain;charset=UTF-8")
		@ResponseBody
		public String parentInfo(@ModelAttribute BoardVO param) throws Exception{
			logger.info("parentInfo!");
			
			System.out.println("�θ�۹�ȣ : " + param.getNo());
			int parentNo = param.getNo();
			
			// �θ�� ���� ��ȸ
			List<BoardVO> parentInfo = service.selectParentInfo(parentNo);
			System.out.println("�θ�no >>> : " + parentInfo.get(0).getNo());
			System.out.println("�θ�groupno >>> : " + parentInfo.get(0).getGroupno());
			System.out.println("�θ�indent >>> : " + parentInfo.get(0).getIndent());
			System.out.println("�θ�depth >>> : " + parentInfo.get(0).getDepth());
			System.out.println("�θ����� >>> : " + parentInfo.get(0).getTitle());
			
			param.setTitle("[RE]:"+parentInfo.get(0).getTitle());
			param.setGroupno(parentInfo.get(0).getGroupno());
			param.setIndent(parentInfo.get(0).getIndent()+1);
			param.setDepth(parentInfo.get(0).getDepth()+1);
			
			Gson gson = new Gson();
			String js = gson.toJson(param);
			System.out.println("JSON >>>: " + js);
			
			return js;
		}
	
	// ����Է½�
	@RequestMapping(value = "replyInsert.do", produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String replyInsert(@ModelAttribute BoardVO param) throws Exception{
		System.out.println("replyInsert() ����Է½�!");
		
		System.out.println("�θ�۹�ȣ : " + param.getNo());
		int parentNo = param.getNo();
		
		
		// �θ�� ���� ��ȸ
		List<BoardVO> parentInfo = service.selectParentInfo(parentNo);
		System.out.println("����Է½� ������ �θ�����-------------------------------------");
		System.out.println("�θ����� >>> : " + parentInfo.get(0).getTitle());
		System.out.println("�θ�no >>> : " + parentInfo.get(0).getNo());
		System.out.println("�θ�groupno >>> : " + parentInfo.get(0).getGroupno());
		System.out.println("�θ�indent >>> : " + parentInfo.get(0).getIndent());
		System.out.println("�θ�depth >>> : " + parentInfo.get(0).getDepth());
		
		// depth ����
		service.updateDepthNo(parentInfo.get(0));
		
		// �о�� �������
		System.out.println("��� ī�װ� : " + param.getCate());
		System.out.println("��� ������ : " + param.getTitle());
		System.out.println("��� �۳��� : " + param.getContent());
		System.out.println("��� �ۼ��� : " + param.getWriter());
		System.out.println("��� �ۼ��� : " + param.getDatetime());
		System.out.println("��� �������� : " + param.getPubpriv());
		System.out.println("��� ��ó : " + param.getSource());
		
		
		System.out.println("�θ�groupno >>> : " + parentInfo.get(0).getGroupno());
		System.out.println("�θ�indent >>> : " + parentInfo.get(0).getIndent());
		System.out.println("�θ�depth >>> : " + parentInfo.get(0).getDepth());
		
		param.setGroupno(parentInfo.get(0).getGroupno());
		param.setIndent(parentInfo.get(0).getIndent());
		param.setDepth(parentInfo.get(0).getDepth());
		
		//����Է�
		service.replyInsert(param);
		

//		// groupno �Է�
//		System.out.println("�۹�ȣ >>> : " + param.getNo());
//		int no = param.getNo();
//		service.updateGroupNo(no);
//		
		List<BoardVO> BoardList = service.selectBoard();
		Gson gson = new Gson();
		String js = gson.toJson(BoardList);
		System.out.println("JSON >>>: " + js);
		
		return js;
	}
	
	// �˻� 
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