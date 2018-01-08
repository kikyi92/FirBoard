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
	// �˻� 
	public List<BoardVO> searchBoard(String keyword) throws Exception;

	// �Խù� ��� ǥ��(����¡)
	public List<BoardVO> writeList(int offset, int noOfRecords) throws Exception;

	// ��ü �Խù� �� ��ȸ
	public int writeGetCount() throws Exception;
		
	// �Խù� ��� ǥ�� 
	public List<BoardVO> writeInfoList() throws Exception;
}
