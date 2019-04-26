package com.inheri.ex02;

public abstract class Calculator {

	protected double num1, num2, result;
	protected char oper;
	
	public double getNum1() {
		return num1;
	}
	public void setNum1(double num1) {
		this.num1 = num1;
	}
	public double getNum2() {
		return num2;
	}
	public void setNum2(double num2) {
		this.num2 = num2;
	}
	public char getOper() {
		return oper;
	}
	public void setOper(char oper) {
		this.oper = oper;
	}
	
	public abstract double calculator(); // 추상메소드
	
}
