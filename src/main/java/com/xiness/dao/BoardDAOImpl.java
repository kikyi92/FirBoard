package com.xiness.dao;
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
	
}
