package com.xiness.service;

import java.util.List;

import com.xiness.dto.BoardVO;

public interface BoardService {
	public List<BoardVO> selectBoard() throws Exception;
}
