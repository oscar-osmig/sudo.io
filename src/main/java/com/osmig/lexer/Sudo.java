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

        List<String> lines = codeScanner();
        Lexer lexer = new Lexer(lines);
        List<Token> tokens = lexer.tokenizeEachLine();
        Parser parser = new Parser(tokens);
        parser.parse();


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

