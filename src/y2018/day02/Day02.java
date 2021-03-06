package y2018.day02;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import adventOfCode.AdventOfCode;
import adventOfCode.Pair;

public class Day02 extends AdventOfCode{

	public static void main(String[] args) throws Exception {
		new Day02().run();
	}

	@Override
	public List<String> readInput() throws Exception {
		return readFile("src/y2018/day02/day02Input.txt");
	}
	
	@Override
	public void part1() throws Exception {
		
		Map<Character,Integer> counts = new HashMap<>();

		int twos = 0;
		int threes = 0;
		int twosPoint = 0;
		int threesPoint = 0;

		for (String word : input) {
			counts.clear();
			twos = 0;
			threes = 0;

			for (char character : word.toCharArray()) {
				counts.put(character, counts.getOrDefault(character, 0)+1);
				if(counts.get(character) > 1) {
					if(counts.get(character) == 2) {
						twos++;
					}else if(counts.get(character) == 3) {
						twos--;
						threes++;
					}else if(counts.get(character) == 4) {
						threes--;
					}
				}
			}
			if(twos > 0) {
				twosPoint++;
			}
			if(threes > 0) {
				threesPoint++;
			}
		}
		System.out.println("Result = "+(twosPoint*threesPoint));
		return;
	}

	@Override
	public void part2() throws Exception {
		
		Pair<String,Integer> defaultValue = new Pair<>("",0);
		Map<String, Pair<String,Integer>> greatestMatch = new HashMap<>();
		int count = 0;
		
		for (String word : input) {
			
			greatestMatch.put(word, defaultValue);

			for(String compareWith : input) {
				count = 0;
				for (int i = 0;i < word.toCharArray().length; i++) {
					if(word.charAt(i) == compareWith.charAt(i)) {
						count++;
					}
				}
				if(count > greatestMatch.get(word).getB() && count < 26) {
					Pair<String,Integer> newValue = new Pair<>(compareWith,count);
					greatestMatch.replace(word, newValue);
				}
			}
			
		}
		
		int greatestScore = 0;
		String idA = null;
		String idB = null;
		StringBuilder result = new StringBuilder();
		for (Map.Entry<String, Pair<String,Integer>> entry : greatestMatch.entrySet()) {

		    if(entry.getValue().getB() > greatestScore) {
		    	greatestScore = entry.getValue().getB();
		    	idA = entry.getKey();
		    	idB = entry.getValue().getA();
		    }

		}
		for (int i = 0;i < idA.toCharArray().length; i++) {
			if(idA.charAt(i) == idB.charAt(i)) {
				result.append(idA.charAt(i));
				count++;
			}
		}
		System.out.println("Result = "+result);	
	}

}
