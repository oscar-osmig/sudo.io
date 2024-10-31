package com.osmig.lexer;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class Sudo {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader("editor.txt"));
        String line;
        char[] lineChar;
        while ((line = bufferedReader.readLine()) != null){
            Lexer lexer = new Lexer(line);
            List<Token> tokens = lexer.tokenize();

            for (Token token : tokens) {
                System.out.println(token);
            }
        }
//
//        //String sourceCode = ;
//        Lexer lexer = new Lexer(sourceCode);
//        List<Token> tokens = lexer.tokenize();
//
//        for (Token token : tokens) {
//            System.out.println(token);
//        }
    }

}
