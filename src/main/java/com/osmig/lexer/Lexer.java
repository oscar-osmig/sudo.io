package com.osmig.lexer;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static javax.lang.model.SourceVersion.isIdentifier;

public class Lexer {
    private List<String> lines; // list of line to be tokenized
    private int currentLineIndex = 0; // tracks index of current line
    public int tokenIndex = 0; // tracks current token in line
    private String[] currentTokens; // tokens of current line

    public Lexer(List<String> lines){
        this.lines = lines;
        advanceLine(); // Initialized with the first line of code
    }
    // advances to the next
    private void advanceLine() {
        if(currentLineIndex < lines.size()){
            currentTokens = lines.get(currentLineIndex).split(" "); // splits the line by spaces
            tokenIndex = 0;
            currentLineIndex++;
        }else {
            currentTokens = null;
        }
    }
    private static final Set<String> KEYWORDS = Set.of("PRINT", "INT", "LOOP"); // Add any other keywords here

    public Token getNextToken() {
        if (currentTokens == null) {
            return new Token(TokenType.EOF, ""); // End of file
        }

        // If we still have tokens on the current line
        if (tokenIndex < currentTokens.length) {
            String item = currentTokens[tokenIndex++];

            // Token classification
            if (KEYWORDS.contains(item)) {
                return new Token(TokenType.KEYWORD, item);
            } else if (isNumber(item)) {
                return new Token(TokenType.NUMBER, item);
            }else if (item.startsWith("'")) {
                // Handle strings starting with a single quote
                StringBuilder stringToken = new StringBuilder(item);

                // Keep reading until we find the closing single quote
                while (!item.endsWith("'")) {
                    if (tokenIndex >= currentTokens.length) {
                        advanceLine(); // Move to the next line if end of line reached
                        if (currentTokens == null) {
                            throw new RuntimeException("Unterminated string literal");
                        }
                    }

                    item = currentTokens[tokenIndex++];
                    stringToken.append(" ").append(item);
                }

                // Remove the surrounding single quotes
                String fullString = stringToken.toString();
                return new Token(TokenType.STRING, fullString.replaceAll("^'|'$", ""));
            }else if (isIdentifier(item)) {
                return new Token(TokenType.IDENTIFIER, item);
            } else {
                return new Token(TokenType.UNKNOWN, item);
            }
        } else {
            // No more tokens on this line, move to the next line
            advanceLine();
            return new Token(TokenType.NEWLINE, ""); // Add newline token after each line
        }
    }

//    private boolean isString(String item) {
//        return item.startsWith("'") && item.endsWith("'") && item.length() >= 2;
//    }

    private boolean isIdentifier(String s) {
        return s.matches("[a-zA-Z_][a-zA-Z0-9_]*");
    }

    private boolean isNumber(String s) {
        return s.matches("\\d+(\\.\\d+)?");
    }

}