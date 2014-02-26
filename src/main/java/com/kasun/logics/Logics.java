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

	public int beDitector(String sentence) {
		String[] sntence = splitSentence(sentence);
		int k = 0;
		int i = 0;
		int j = 0;
		while (i < propsitions.length) {
			while (j < sntence.length) {
				if (propsitions[i].equals(sntence[j])) {
					System.out.println("propsitions[i] "+propsitions[i]);
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
		return j;
	}

	public int[] sentenceDividerNumbers(String sentence) {
		int i = beDitector(sentence);
		int j = toDetectort(sentence);
		int[] arr = { i, j };

		return arr;
	}

	public int toDetectort(String sentence) {
		String[] sntence = splitSentence(sentence);
		int k = 0;
		int i = 0;
		int j = 0;
		while (i < toect.length) {
			while (j < sntence.length) {
				if (toect[i].equals(sntence[j])) {
					System.out.println("toect[i] "+toect[i]);
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
		return j;
	}

	public void sentenceDivide(String sentence) {
		int[] arr = sentenceDividerNumbers(sentence);
		String[] sntence = splitSentence(sentence);
		System.out.print("Sentence Subject : ");
		int i = 0;
		while (i < arr[1]) {
			System.out.print(sntence[i] + " ");
			i++;
		}
		System.out.println();
		System.out.print("Sentence Verb : ");

		System.out.print(sntence[i + 1] + " ");

		i = arr[1];

		System.out.println();
		System.out.print("Sentence Object : ");
		while (i < sntence.length) {
			System.out.print(sntence[i] + " ");
		}

	}
}
