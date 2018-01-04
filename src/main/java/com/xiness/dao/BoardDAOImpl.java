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

}
