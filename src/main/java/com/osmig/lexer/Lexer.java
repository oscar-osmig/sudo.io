package com.osmig.lexer;

import java.util.ArrayList;
import java.util.List;

public class Lexer {
    private List<String> lines; // list of line to be tokenized
    private int lineIndex = 0; // tracks index of current line
    public int tokenIndex = 0; // tracks current token in line
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

        return null;
    }

    private boolean isString(String item) {
        return item.startsWith("'") && item.endsWith("'") && item.length() > 2;
    }

    private boolean isNumber(String item) {
        return item.matches("\\d+(\\.\\d+)?");
    }


}