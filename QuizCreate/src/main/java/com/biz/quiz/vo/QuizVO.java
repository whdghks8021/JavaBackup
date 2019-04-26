package com.biz.quiz.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class QuizVO {
	
	private int cb_num ;
	private String cb_problem ;
	private String cb_answer ;
	private String cb_nanswer1 ;
	private String cb_nanswer2 ;
	private String cb_nanswer3 ;
	private String cb_nanswer4 ;
	
	public QuizVO(String cb_problem, String cb_answer, String cb_nanswer1, String cb_nanswer2, String cb_nanswer3,
			String cb_nanswer4) {
		this(0,cb_problem,cb_answer, cb_nanswer1, cb_nanswer2,
				cb_nanswer3, cb_nanswer4);
		
	}

	public QuizVO(int cb_num, String cb_problem, String cb_answer, String cb_nanswer1, String cb_nanswer2,
			String cb_nanswer3, String cb_nanswer4) {
		this.cb_num = cb_num ;
		this.cb_problem = cb_problem;
		this.cb_answer = cb_answer;
		this.cb_nanswer1 = cb_nanswer1;
		this.cb_nanswer2 = cb_nanswer2;
		this.cb_nanswer3 = cb_nanswer3;
		this.cb_nanswer4 = cb_nanswer4;
	}
	
	
}
