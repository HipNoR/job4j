package ru.job4j.parsing;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Class parser.
 * Searches for pairs of brackets and return their positions.
 * Bracket pairs like "{{}}[]" or "[{}{}]" are valid.
 * Pairs like "{[}]" are invalid.
 * @author Roman Bednyashov (hipnorosva@gmail.com).
 * @since 0.1
 * @version 0.1
 */
public class Parser {
    private int stack = 0;
    private Stack<Bracket> brackets = new Stack<>();
    private ArrayList<BracketPair> pairs = new ArrayList<>();

    /**
     * Method searches for pairs of brackets and print their positions.
     * @param string input String to start searching.
     */
    public void parseString(String string) {
        boolean valid = true;
        char[] chars = string.toCharArray();
        for (int index = 0; index < chars.length; index++) {
            if (chars[index] == '{' || chars[index] == '[' || chars[index] == '(') {
                brackets.push(new Bracket(chars[index], index));
                stack++;
            } else if (chars[index] == '}') {
                if (index == 0 || brackets.get(stack - 1).getName() != '{') {
                    valid = false;
                    break;
                } else {
                    pairs.add(new BracketPair("{}", brackets.pop().getOpen(), index));
                    stack--;
                }
            } else if (chars[index] == ')') {
                if (index == 0 || brackets.get(stack - 1).getName() != '(') {
                    valid = false;
                    break;
                } else {
                    pairs.add(new BracketPair("()", brackets.pop().getOpen(), index));
                    stack--;
                }
            } else if (chars[index] == ']') {
                if (index == 0 || brackets.get(stack - 1).getName() != '[') {
                    valid = false;
                    break;
                } else {
                    pairs.add(new BracketPair("[]", brackets.pop().getOpen(), index));
                    stack--;
                }
            }
        }

        if (!valid) {
            System.out.println("String is invalid!");
        } else {
            for (BracketPair pair : pairs) {
                System.out.println("Pair: " + pair.getName() + ". Opens at position: " + pair.getOpen()
                        + ". Closes at position: " + pair.getClose() + ".");
            }

        }
    }



}
