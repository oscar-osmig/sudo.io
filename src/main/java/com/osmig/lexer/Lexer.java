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
            } else if (current == '\'') {  // Check for starting quote of string
                tokens.add(string());
            } else if (current == '\n') {
                tokens.add(new Token(TokenType.NEWLINE, null, null));
                position++;
            } else if (current == ' ') {
                position++;
            } else {
                position++;  // Skip any unrecognized characters
            }
        }

        return tokens;
    }

    private Token number() {
        int start = position;
        while (position < input.length() && Character.isDigit(input.charAt(position)) ||
                position < input.length() && input.contains(".")) {
            position++;
        }
        String numberStr = input.substring(start, position);
        return new Token(TokenType.NUMBER, numberStr, Double.parseDouble(numberStr));
    }

    private Token keywordAsOperator() {
        int start = position;
        while (position < input.length() && Character.isAlphabetic(input.charAt(position))) {
            position++;
        }
        String word = input.substring(start, position);

        if (word.equals("ADD") || word.equals("SUBTRACT") || word.equals("MULTIPLY") || word.equals("DIVIDE")) {
            return new Token(TokenType.OPERATOR, word, null);
        } else if (word.equals("IF") || word.equals("WHILE") || word.equals("FOR") ||
                word.equals("NOT") || word.equals("EQUALS") || word.equals("PRINT") ||
                word.equals("LOOP") || word.equals("LIST") || word.equals("INDEX") ||
                word.equals("FROM") || word.equals("TO") || word.equals("IN") ||
                word.equals("AT")) {
            return new Token(TokenType.KEYWORD, word, null);
        } else if ( word.equals("INT")) {
            return new Token(TokenType.INTEGER, word, null);
        } else {
            return new Token(TokenType.IDENTIFIER, word, null);
        }
    }

    private Token string() {
        position++; // Move past the opening quote
        int start = position;

        // Read until the closing quote or end of input
        while (position < input.length() && input.charAt(position) != '\'') {
            position++;
        }

        String strValue = input.substring(start, position); // Extract the string value
        position++; // Move past the closing quote if present

        return new Token(TokenType.STRING, strValue, strValue);
    }
}