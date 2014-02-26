package com.kasun.run;

import com.kasun.logics.Logics;

public class Run {

	public static void main(String[] args) {
		
		Logics logics = new Logics();
		
		String sentence = "Master Lee is a good man";
		String arr [] = logics.subjectDetector(logics.splitSentence(sentence));
		int i = 0;
		System.out.print("Subject of the sentence : ");
		while(arr.length>i){
			System.out.print(arr[i]+" ");
			i++;
		}
	}
}
