package com.animal;

public class Cat implements CatFamily {

	@Override
	public void eat() {
		System.out.println("생선을 좋아한다.");

	}

	@Override
	public void cry() {
		System.out.println("야옹.");

	}

	@Override
	public void move() {
		System.out.println("방안.");

	}

}
