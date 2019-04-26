package com.biz.oracle.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor // 멤버변수 생성자
@NoArgsConstructor // 기본 super 생성자
// @Getter // getter만
// @Setter // setter만
// @ToString // toString만
// @Builder // 멤버변수 초기화 생성자를 만들고 기본생성자는 감춰버린다.

public class StdVO {

	private String st_num;
	private String st_name;
	private String st_grade;
	private String st_tel;
	
}
