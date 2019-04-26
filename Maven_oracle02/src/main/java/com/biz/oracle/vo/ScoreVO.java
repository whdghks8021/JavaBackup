package com.biz.oracle.vo;

import lombok.Data;

// lombok 플러그인 eclipse에 설치해서
// VO 클래스에 getter setter 생성자, toString등의 작성을
// 대신 하도록 만듦
@Data
public class ScoreVO {
	
	private long id ;
	private String st_num ;
	private String sc_sb_code ;
	private int sc_score ;
	
}
