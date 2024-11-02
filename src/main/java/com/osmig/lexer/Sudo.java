package com.osmig.lexer;

import com.osmig.parser.Parser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Sudo {
    public static List<String> lineStack = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        //List<String> codeLines  = codeScanner("editor.txt"); // returns list of each line of code
        //System.out.println(codeLines.getFirst());
        List<String> lines = codeScanner();
        Lexer lexer = new Lexer(lines);
       Token token;

        System.out.println("Token -> Type -> Value");
        while ((token = lexer.getNextToken()).getType() != TokenType.EOF){
            System.out.println("Token -> " + token.getType() + " -> " + token.getValue());
        }

    }
        public static List<String> codeScanner() throws IOException {
            Path filePath = Path.of("editor.txt");  // Set the path to your file
            List<String> codelines;
            codelines = Files.readAllLines(filePath);
            return codelines;
        }

}

