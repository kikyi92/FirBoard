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
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.xiness.dto.BoardVO;
import com.xiness.service.BoardService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	@Inject
	private BoardService service;
	
	@RequestMapping(value = "/")
	public String home(Locale locale, Model model) throws Exception{
		logger.info("home!");
		
		List<BoardVO> BoardList = service.selectBoard();
		model.addAttribute("BoardList",BoardList);
		
		return "home";
	}
	
	@RequestMapping(value = "boardWritePage.do")
	public String boardWritePage(Locale locale, Model model) throws Exception{
		logger.info("boardWritePage!");
		
		return "boardWrite";
	}
	
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
		System.out.println("�׷��ȣ : " + param.getGroupno());
		service.insertBoard(param);
		
		int no = param.getNo();
		System.out.println("---�۹�ȣ >>> : " + no);
		
		List<BoardVO> BoardList = service.selectBoard();
		Gson gson = new Gson();
		String js = gson.toJson(BoardList);
		System.out.println("JSON >>>: " + js);
		
		return js;
	}
	
}