package ru.job4j.parsing;

import java.util.ArrayList;
import java.util.List;
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

    /**
     * Method searches for pairs of brackets and print their positions.
     * @param string input String to start searching.
     * @return list of bracket pairs.
     * @throws NotValidException if String not valid.
     */
    public List<BracketPair> parseString(String string) throws NotValidException {
        List<Bracket> temp = getBrackets(string);
        List<BracketPair> pairs = new ArrayList<>();
        Stack<Bracket> bracketStack = new Stack<>();
        boolean valid = true;
        for (int index = 0; index < temp.size(); index++) {
            char tempo = temp.get(index).getName();
            if (tempo == '{' || tempo == '[' || tempo == '(') {
                bracketStack.push(temp.get(index));
            } else if (tempo == '}' || tempo == ')' || tempo == ']') {
                if (index == 0) {
                    valid = false;
                    break;
                }
                if (tempo == '}' && bracketStack.peek().getName() == '{') {
                    pairs.add(new BracketPair("{}", bracketStack.pop().getOpen(), temp.get(index).getClose()));
                } else if (tempo == ')' && bracketStack.peek().getName() == '(') {
                    pairs.add(new BracketPair("()", bracketStack.pop().getOpen(), temp.get(index).getClose()));
                } else if (tempo == ']' && bracketStack.peek().getName() == '[') {
                    pairs.add(new BracketPair("[]", bracketStack.pop().getOpen(), temp.get(index).getClose()));
                } else {
                    valid = false;
                    break;
                }
            }
        }
        if (!valid) {
           throw new NotValidException();
        }
        return pairs;
    }

    private List<Bracket> getBrackets(String string) {
        List<Bracket> list = new ArrayList<>();
        char[] chars = string.toCharArray();
        for (int index = 0; index < chars.length; index++) {
            if (chars[index] == '{' || chars[index] == '[' || chars[index] == '(') {
                list.add(new Bracket(chars[index], index, 0));
            }
            if (chars[index] == '}' || chars[index] == ']' || chars[index] == ')') {
                list.add(new Bracket(chars[index], 0, index));
            }
        }
        return list;
    }
}