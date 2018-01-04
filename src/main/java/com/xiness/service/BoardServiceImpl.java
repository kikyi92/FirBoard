package com.xiness.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.xiness.dao.BoardDAO;
import com.xiness.dto.BoardVO;

@Service
public class BoardServiceImpl implements BoardService{
	
	@Inject
	private BoardDAO dao;
	
	@Override
	public List<BoardVO> selectBoard() throws Exception {
		return dao.selectBoard();
	}

	@Override
	public void insertBoard(BoardVO param) throws Exception {
		dao.insertBoard(param);
	}

	
}