package com.osmig.lexer;

import java.util.ArrayList;
import java.util.List;

class Lexer {
    private final String input;
    private int position = 0;

    public Lexer(String input) {
        this.input = input;
    }

    public List<Token> tokenize() {
        List<Token> tokens = new ArrayList<>();

        while (position < input.length()) {
            char current = input.charAt(position);

            if (Character.isDigit(current)) {
                tokens.add(number());
            } else if (Character.isAlphabetic(current)) {
                tokens.add(keywordAsOperator());
            } else {
                position++;  // Skip any unrecognized characters (like spaces)
            }
        }

        return tokens;
    }

    private Token number() {
        int start = position;
        while (position < input.length() && Character.isDigit(input.charAt(position))) {
            position++;
        }
        String numberStr = input.substring(start, position);
        return new Token(TokenType.NUMBER, numberStr, Integer.parseInt(numberStr));
    }

    private Token keywordAsOperator() {
        int start = position;
        while (position < input.length() && Character.isAlphabetic(input.charAt(position))) {
            position++;
        }
        String word = input.substring(start, position);

        // Recognize specific keywords as "OPERATOR"
        if (word.equals("ADD") || word.equals("SUBTRACT") || word.equals("MULTIPLY") || word.equals("DIVIDE")) {
            return new Token(TokenType.OPERATOR, word, null);
        } else {
            return new Token(TokenType.IDENTIFIER, word, null);  // Treat other words as identifiers
        }
    }
}