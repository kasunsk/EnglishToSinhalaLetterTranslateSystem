package com.kasun.logics;

import java.util.Arrays;

public class Logics {

	private String[] propsitions = { "is", "am", "are", "was", "were", "be" };
	private String[] toect = { "to", "for", "from" };

	public String[] splitSentence(String sentence) {
		String words[];
		words = sentence.split(" ");
		return words;
	}

	public String[] subjectDetector(String[] sentence) {
		int k = 0;
		int i = 0;
		int j = 0;
		while (i < propsitions.length) {
			while (j < sentence.length) {
				if (propsitions[i].equals(sentence[j])) {
					k++;
					break;
				}
				System.out.println("");
				j++;
			}
			if (k == 1) {
				break;
			}
			i++;
			j = 0;
		}
		String[] subject = new String[j];
		;
		int z = 0;
		while (subject.length > z) {
			subject[z] = sentence[z];
			z++;
		}
		return subject;
	}
	
	public String verbDetector(String sentence){
		return null;
	}
}
