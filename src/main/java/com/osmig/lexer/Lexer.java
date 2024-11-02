package com.osmig.lexer;

import java.util.ArrayList;
import java.util.List;

class Lexer {
    private List<String> lines; // list of line to be tokenized
    private int lineIndex = 0; // tracks index of current line
    private int tokenIndex = 0; // tracks current token in line
    private String[] currentTokens; // tokens of current line

    public Lexer(List<String> lines){
        this.lines = lines;
        advanceLine(); // Initialized with the first line of code
    }
    // advances to the next
    private void advanceLine() {
        if(lineIndex < lines.size()){
            currentTokens = lines.get(lineIndex).split(" "); // splits the line by spaces
            tokenIndex = 0;
            lineIndex++;
        }else {
            currentTokens = null;
        }
    }
    // retriving the next token and clasifying it
    public Token getNextToken(){
        if (currentTokens == null){
            return new Token(TokenType.EOF, ""); // end of file token
        }
        if (tokenIndex < currentTokens.length){
            String item = currentTokens[tokenIndex];
            tokenIndex++;

            // determine the type of token base on item context
            if (item.equals("INT") || item.equals("PRINT")){
                return new Token(TokenType.KEYWORD, item);
            } else if (isNumber(item)){
                return new Token(TokenType.NUMBER, item);
            } else if (item.matches("[a-zA-Z_][a-zA-Z0-9_]*")){
                return new Token(TokenType.IDENTIFIER, item);
            }else if (item.equals("\n")) {
                return new Token(TokenType.NEWLINE, "");
            }else {
                return new Token(TokenType.UNKNOWN, item);
            }

        }else {
            // move to next line and retrieve the next token
            advanceLine();
            return getNextToken();
        }
    }

    private boolean isNumber(String item) {
        return item.matches("\\d+(\\.\\d+)?");
    }


}