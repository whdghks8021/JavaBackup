package com.animal;

public class Tiger implements CatFamily {

	@Override
	public void eat() {
		System.out.println("멧돼지를 좋아한다.");

	}

	@Override
	public void cry() {
		System.out.println("어흥.");

	}

	@Override
	public void move() {
		System.out.println("밀림.");

	}

}
