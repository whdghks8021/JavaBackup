package com.biz.ex02;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.biz.ex02.vo.GradeVO;

public class GradeExec04 {

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		List<GradeVO> gradeList = new ArrayList();		
		for(int i = 0 ; i < 2 ; i ++) {
			GradeVO vo = new GradeVO();
			// GradeVO를 생성해서 매개변수로 전달
			makeScore(vo);
			// 전달했던 vo가 makeScore를 통해 변경되어 gradeList에 추가
			// 객체를 매개변수로 주면 메소드에서 변경이 된 값이 저장이됨
			gradeList.add(vo);
		}
		
		System.out.println("=================================================");
		System.out.println("학번\t국어\t영어\t수학\t총점\t평균");
		System.out.println("-------------------------------------------------");
		
		for(GradeVO vo : gradeList) {
			viewScore(vo);
			
		}
		System.out.println("=================================================");
			
	} // main END
	
	public static void makeScore(GradeVO vo) {
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("학번 >> ");
		String strNum = scanner.nextLine();
		vo.setStrNum(strNum);
		
		System.out.print("국어 >> ");
		String strKor = scanner.nextLine();
		int intKor = Integer.valueOf(strKor);
		vo.setIntKor(intKor);
		
		System.out.print("영어 >> ");
		String strEng = scanner.nextLine();
		int intEng = Integer.valueOf(strEng);
		vo.setIntEng(intEng);
		
		System.out.print("수학 >> ");
		String strMath = scanner.nextLine();
		int intMath = Integer.valueOf(strMath);
		vo.setIntMath(intMath);
		
		int intSum = vo.getIntKor();
		intSum += vo.getIntEng();
		intSum += vo.getIntMath();
		vo.setIntSum(intSum);
		
		vo.setFloatAvg(intSum / 3.0f);
		
	}
	
	public static void viewScore(GradeVO v) {
		
		System.out.print(v.getStrNum() + "\t");
		System.out.print(v.getIntKor() + "\t");
		System.out.print(v.getIntEng() + "\t");
		System.out.print(v.getIntMath() + "\t");
		System.out.print(v.getIntSum() + "\t");
		System.out.println(v.getFloatAvg());
		System.out.println("-------------------------------------------------");
		
	}
		
}
