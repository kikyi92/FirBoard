package com.xiness.service;

import java.util.List;

import com.xiness.dto.BoardVO;

public interface BoardService {
	public List<BoardVO> selectBoard() throws Exception;
	public void insertBoard(BoardVO param) throws Exception;
	public void updateGroupNo(int no) throws Exception;
	public List<BoardVO> detailBoard(Integer no) throws Exception;
	public void boardDelete(int no);
	public void boardUpdate(BoardVO param) throws Exception;
	public List<BoardVO> selectParentInfo(int no) throws Exception;
	public void replyInsert(BoardVO param) throws Exception;
	public void updateDepthNo(BoardVO param) throws Exception;
	// 검색 
	public List<BoardVO> searchBoard(String keyword) throws Exception;

	// 게시물 목록 표시(페이징)
	public List<BoardVO> writeList(int offset, int noOfRecords) throws Exception;

	// 전체 게시물 수 조회
	public int writeGetCount() throws Exception;
		
	// 게시물 목록 표시 
	public List<BoardVO> writeInfoList() throws Exception;
}
