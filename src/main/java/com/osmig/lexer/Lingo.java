package com.osmig.lexer;


import java.io.IOException;
import java.util.List;

public class Lingo {

    public static void main(String[] args) throws IOException {
        Lexer lexer = new Lexer("number ADD 20 MULTIPLY 2 SUBTRACT 10 DIVIDE 2");
        List<Token> tokens = lexer.tokenize();

        for (Token token : tokens) {
            System.out.println(token);
        }
    }

}
