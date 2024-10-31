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

        int spaceCounter = 0;
        while (position < input.length()) {
            char current = input.charAt(position);

            if (Character.isDigit(current)) {
                Token numberToken = number();
                tokens.add(numberToken);
                System.out.println("Tokenized number: " + numberToken); // Debug
            } else if (Character.isAlphabetic(current)) {
                Token keywordOrIdentifier = keywordAsOperator();
                tokens.add(keywordOrIdentifier);
                System.out.println("Tokenized identifier/keyword: " + keywordOrIdentifier); // Debug
            } else if (current == '\n') {
                Token newlineToken = new Token(TokenType.NEWLINE, null, null);
                tokens.add(newlineToken);
                System.out.println("Tokenized newline"); // Debug
                position++;
            } else if (current == ' ') {
                position++;
            } else {
                position++;  // Skip any unrecognized characters (like symbols)
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

        if (word.equals("ADD") || word.equals("SUBTRACT") || word.equals("MULTIPLY") || word.equals("DIVIDE")) {
            return new Token(TokenType.OPERATOR, word, null);
        } else if (word.equals("IF") || word.equals("WHILE") || word.equals("FOR") ||
                word.equals("NOT") || word.equals("EQUALS") || word.equals("PRINT") ||
                word.equals("LOOP") || word.equals("LIST") || word.equals("INDEX") ||
                word.equals("FROM") || word.equals("TO") || word.equals("IN") || word.equals("AT")) {
            return new Token(TokenType.KEYWORD, word, null);
        } else {
            return new Token(TokenType.IDENTIFIER, word, null);
        }
    }
}
