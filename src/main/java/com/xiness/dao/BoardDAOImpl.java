package com.xiness.dao;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.xiness.dto.BoardVO;

@Repository
public class BoardDAOImpl implements BoardDAO{
	@Inject
	private SqlSession sqlSession;
	private static final String Namespace = "com.xiness.mapper.BoardMapper";
	private Object noOfRecords;
	
	@Override
	public List<BoardVO> selectBoard() throws Exception {
		return sqlSession.selectList(Namespace+".selectBoard");
	}

	@Override
	public void insertBoard(BoardVO param) throws Exception {
		sqlSession.insert(Namespace + ".insertBoard", param);
	}

	@Override
	public void updateGroupNo(int no) throws Exception {
		sqlSession.update(Namespace + ".updateGroupNo", no);
	}

	// 상세보기 페이지
	@Override
	public List<BoardVO> detailBoard(Integer no) throws Exception {
		return sqlSession.selectList(Namespace+".detailBoard", no);
	}

	@Override
	public void boardDelete(int no) {
		sqlSession.delete(Namespace+".boardDelete", no);
	}

	@Override
	public void boardUpdate(BoardVO param) throws Exception {
		sqlSession.update(Namespace+".boardUpdate", param);
	}

	@Override
	public List<BoardVO> selectParentInfo(int parentNo) throws Exception {
		return sqlSession.selectList(Namespace+".selectParentInfo", parentNo);
	}

	@Override
	public void replyInsert(BoardVO param) throws Exception {
		sqlSession.insert(Namespace + ".replyInsert", param);
	}

	@Override
	public void updateDepthNo(BoardVO param) throws Exception {
		sqlSession.update(Namespace+".updateDepthNo", param);
	}

	// 검색 결과 
	@Override
	public List<BoardVO> searchBoard(String keyword) throws Exception {
		return sqlSession.selectList(Namespace+".searchBoard", keyword);
	}

	//목록표시
	@Override
	public List<BoardVO> writeInfoList() throws Exception {
		return sqlSession.selectList("writeInfoList");
	}

	//게시물 목록표시(페이징)
	@Override
	public List<BoardVO> writeList(int offset, int noOfRecords) throws Exception {
			
		List<BoardVO> writeList = new ArrayList<BoardVO>(); 
		
		HashMap<String, Object> params = new HashMap<String, Object>(); 
			
		params.put("offset", offset); 
		params.put("noOfRecords", noOfRecords); 
		
		writeList = sqlSession.selectList("writeList", params); 
		this.noOfRecords = sqlSession.selectOne("writeGetCount");
		
		//return sqlSession.writeList(Namespace+".writeList");
		return writeList;
	}

	//페이징
	@Override
	public int writeGetCount() throws Exception {
		return sqlSession.selectOne("writeGetCount");
	}
	
}