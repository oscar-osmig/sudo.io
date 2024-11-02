package com.osmig.lexer;

import com.osmig.parser.Parser;

import java.io.BufferedReader;
import java.io.FileReader;
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

        //Token token;

//        System.out.println("Token -> Type -> Value");
//        while ((token = lexer.getNextToken()).getType() != TokenType.EOF){
//            System.out.println("Token -> " + token.getType() + " -> " + token.getValue());
//        }

//        System.out.println("\n============  code  ============");
//        for (String line : lines){
//            System.out.println(line);
//        }
//        System.out.println("============ output ============");
//        Parser parser = new Parser(lexer);
//        parser.parse();
    }
        public static List<String> codeScanner() throws IOException {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("editor.txt"));
            String line;
            List<String> list = new ArrayList<>();
            while ((line = bufferedReader.readLine()) != null){
                list.add(line);
            }

            return list;
        }
}

