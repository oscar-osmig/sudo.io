package com.osmig.lexer;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Sudo {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader("editor.txt"));
        String line;
        char[] lineChar;
        List<String> code = new ArrayList<>();
        System.out.println("\n==================== TOKENIZER ====================");
        while ((line = bufferedReader.readLine()) != null){
            code.add(line);
            Lexer lexer = new Lexer(line);
            List<Token> tokens = lexer.tokenize();

            printToken(tokens);

        }
        System.out.println("\n==================== CODE ====================");
        for (String lines : code){
            System.out.println(lines);
        }


    }

    public static void printToken(List<Token> tokens){
        for (Token token : tokens) {
            System.out.println(token);
        }
    }

}
