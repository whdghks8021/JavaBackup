package com.biz.quiz.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.biz.quiz.sql.QuizSQL;
import com.biz.quiz.vo.QuizVO;

public interface QuizDao {
	
	// ibatis를 이용해서 SQL을 전달
	@Select(QuizSQL.CBT_ALL)
	public List<QuizVO> selectAll() ;
	@Select(QuizSQL.CBT_FIND_NUM)
	public QuizVO findByNum(int cb_num) ;
	@Insert(QuizSQL.CBT_INSERT)
	public int insert(QuizVO vo) ;
	@Update(QuizSQL.CBT_UPDATE)
	public int update(QuizVO vo) ;
	@Delete(QuizSQL.CBT_DELETE)
	public int delete(int cb_num) ;
}
