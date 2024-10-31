package com.osmig.lexer;
import com.osmig.interpreter.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Sudo {

    public static void main(String[] args) throws IOException {
        StringBuilder inputBuilder = new StringBuilder();
        BufferedReader bufferedReader = new BufferedReader(new FileReader("editor.txt"));
        String line;

        List<String> code = new ArrayList<>();

        // Read the entire file content
        while ((line = bufferedReader.readLine()) != null) {
            code.add(line);
            inputBuilder.append(line).append("\n"); // Append the line and a newline character
        }
        bufferedReader.close();

        // Tokenize the entire input
        String input = inputBuilder.toString(); // Convert to String for lexer
        Lexer lexer = new Lexer(input);
        List<Token> tokens = lexer.tokenize();

        System.out.println("\n==================== CODE ====================");
        for (String lines : code) {
            System.out.println(lines);
        }

        // Interpret all tokens at once
        System.out.println("\n==================== INTERPRETATION OUTPUT ====================");
        Interpreter interpreter = new Interpreter(tokens);
        interpreter.interpret();
    }

}
