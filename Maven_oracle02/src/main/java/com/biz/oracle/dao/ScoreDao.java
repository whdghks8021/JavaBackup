package com.biz.oracle.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.biz.oracle.sql.ScoreSQL;
import com.biz.oracle.vo.ScoreVO;

public interface ScoreDao {

	@Select(ScoreSQL.SC_ALL)
	public List<ScoreVO> selectAll() ;
	
	@Select(ScoreSQL.SC_FIND_NUM)
	public List<ScoreVO> findByNum(String st_num) ; // 학번으로 Join해서 보는경우
											// 여러개의 과목이 나오기때문에 List로 리턴
	@Select(ScoreSQL.SC_FIND_ID)
	public ScoreVO findById(long id) ;
	
	@Insert(ScoreSQL.SC_INSERT)
	public int insert(ScoreVO vo) ;
	
	@Update(ScoreSQL.SC_UPDATE)
	public int update(ScoreVO vo) ;
	
	@Delete(ScoreSQL.SC_DELETE)
	public int delete(long id) ;
}
