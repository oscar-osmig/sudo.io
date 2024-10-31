package com.osmig.lexer;


import java.io.IOException;
import java.util.List;

public class Lingo {

    public static void main(String[] args) throws IOException {
        String sourceCode = "myNum 5 \n PRINT myNum IF EQUALS herNum";
        Lexer lexer = new Lexer(sourceCode);
        List<Token> tokens = lexer.tokenize();

        for (Token token : tokens) {
            System.out.println(token);
        }
    }

}
