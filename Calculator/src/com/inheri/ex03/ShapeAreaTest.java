package com.inheri.ex03;

public class ShapeAreaTest {
	
	public static void main(String[] args) {
		InstanceOfEx.castingMethod(new Circle());
		// Shape sh = new Circle(); > 업캐스팅
		InstanceOfEx.castingMethod(new Square());
		InstanceOfEx.castingMethod(new Triangle());
	}
}
