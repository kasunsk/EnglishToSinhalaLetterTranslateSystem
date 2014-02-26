package com.kasun.run;

import com.kasun.translate.Translate;

public class Run {

	public static void main(String[] args) {

		Translate translate = new Translate();

		String sentence = "He is eating to rice";
		
		System.out.println(translate.translate(sentence));
	}
}
