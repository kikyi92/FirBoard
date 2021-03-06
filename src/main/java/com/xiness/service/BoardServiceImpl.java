package com.xiness.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
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

	@Override
	public void updateGroupNo(int no) throws Exception {
		dao.updateGroupNo(no);
	}
	
	@Override
	public List<BoardVO> detailBoard(Integer no) throws Exception {
		return dao.detailBoard(no);
	}
	
	@Override
	public void boardDelete(int no) {
		dao.boardDelete(no);
	}

	@Override
	public void boardUpdate(BoardVO param) throws Exception {
		dao.boardUpdate(param);
	}

	@Override
	public List<BoardVO> selectParentInfo(int parentNo) throws Exception {
		return dao.selectParentInfo(parentNo);
	}

	@Override
	public void replyInsert(BoardVO param) throws Exception {
		dao.replyInsert(param);
	}

	@Override
	public void updateDepthNo(BoardVO param) throws Exception {
		dao.updateDepthNo(param);
	}

	// 검색
	@Override
	public List<BoardVO> searchBoard(String keyword) throws Exception {
		return dao.searchBoard(keyword);
	}

	@Override
	public List<BoardVO> writeList(int offset, int noOfRecords) throws Exception {
		return dao.writeList(offset, noOfRecords);
	}

	@Override
	public int writeGetCount() throws Exception {
		return dao.writeGetCount();
	}

	//목록표시
	@Override
	public List<BoardVO> writeInfoList() throws Exception {
		return null;
	}
	
}