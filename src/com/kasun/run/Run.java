package com.kasun.run;

import com.kasun.logics.Logics;

public class Run {

	public static void main(String[] args) {

		Logics logics = new Logics();

		String sentence = "Master Lee is a good man";
		Logics logic = new Logics();
		
		logic.sentenceDivide(sentence);
	}
}
