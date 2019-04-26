package com.inheri.test;

import java.util.Scanner;

import com.inheri.ex02.Add;
import com.inheri.ex02.Divide;
import com.inheri.ex02.Minus;
import com.inheri.ex02.Multi;

public class Calculrator {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		double a = 0;
		double b = 0;
		double result = 0;
		char oper = '+';
		System.out.println("전자계산기");
		System.out.println("두 실수와 연산자를 입력하세요.");
		System.out.print("실수 입력 >");
		a = scan.nextDouble();
		System.out.print("연산자 입력 >");
		oper = scan.next().charAt(0);
		System.out.print("실수 입력 >");
		b = scan.nextDouble();
		
		if(oper == '+') {
			Add ad = new Add();
			ad.setNum1(a);
			ad.setNum2(b);
			ad.setOper(oper);
			result = ad.calculator();
		} else if(oper == '-') {
			Minus minus = new Minus();
			minus.setNum1(a);
			minus.setNum2(b);
			minus.setOper(oper);
			result = minus.calculator();
		} else if(oper == '*') {
			Multi mul = new Multi();
			mul.setNum1(a);
			mul.setNum2(b);
			mul.setOper(oper);
			result = mul.calculator();
		} else if(oper == '/') {
			Divide div = new Divide();
			div.setNum1(a);
			div.setNum2(b);
			div.setOper(oper);
			result = div.calculator();
		} else {
			System.out.println("잘못 입력되었습니다."); 
		}
		System.out.println("결과값은 : " + result);

	}

}
