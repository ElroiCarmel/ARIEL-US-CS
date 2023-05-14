import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.Stack;

/*
 * Given a string with parentheses
 * check if it's a valid string like "({()[]})"
 */

public class EX2 {
	public static void main(String[] args) {
		String test = "[]";
		System.out.println(isValid(test));
//		RangeDS ds = new RangeDS();
//		for (int i=0; i<=100; i++) {
//			double num = Math.random()*10;
//			ds.add(num);
//		}
//		System.out.println(ds);
//		System.out.println(ds.get(4));
//		ds.del(3);
//		System.out.println(ds.getRanges());
		
	}

	// Parentheses allowed: "<,{,(,["
	public static boolean isValid(String s) {
		if (s.length()<2) return false;
		Stack<Character> par = new Stack<Character>();
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if ("<{[(".contains("" + c))
				par.push(c);
			if (">]})".contains("" + c) && par.empty())
				return false;
			if (c == '>' && par.peek() != '<')
				return false;
			else if (c == '>' && par.peek() == '<')
				par.pop();
			if (c == ')' && par.peek() != '(')
				return false;
			else if (c == ')' && par.peek() == '(')
				par.pop();
			if (c == ']' && par.peek() != '[')
				return false;
			else if (c == ']' && par.peek() == '[')
				par.pop();
			if (c == '}' && par.peek() != '{')
				return false;
			else if (c == '}' && par.peek() == '{')
				par.pop();
		}
		return par.empty();
	}
}
