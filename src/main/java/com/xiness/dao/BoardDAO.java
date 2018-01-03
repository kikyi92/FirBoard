package com.xiness.dao;

import java.util.List;

import com.xiness.dto.BoardVO;

public interface BoardDAO {
	public List<BoardVO> selectBoard() throws Exception;
	public void insertBoard(BoardVO param) throws Exception;
}
