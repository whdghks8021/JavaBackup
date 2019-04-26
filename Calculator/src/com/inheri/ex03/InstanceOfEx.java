package com.inheri.ex03;

public class InstanceOfEx {

	public static void castingMethod(Shape sh) {
		double result = 0;
		if(sh instanceof Circle) {
			Circle ce = (Circle)sh; // 다운캐스팅
			ce.radius = 2.6;
			result = sh.area();
		} else if(sh instanceof Square) {
			Square su = (Square)sh;
			su.width = 5.3;
			su.height = 8.1;
			result = sh.area();
		} else if(sh instanceof Triangle) {
			Triangle tr = (Triangle)sh;
			tr.width = 5.3;
			tr.height = 8.1;
			result = sh.area();
		}
		System.out.println("도형의 면적은 " + result + " 입니다.");
	}
	
}
