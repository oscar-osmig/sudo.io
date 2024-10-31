package com.osmig.lexer;
import com.osmig.interpreter.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Sudo {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader("editor.txt"));
        String line;
        List<Token> allTokens = new ArrayList<>();
        List<String> code = new ArrayList<>();

        // Read and tokenize each line
        while ((line = bufferedReader.readLine()) != null) {
            code.add(line);
            Lexer lexer = new Lexer(line);
            List<Token> tokens = lexer.tokenize();
            allTokens.addAll(tokens);  // Collect tokens from each line
            System.out.println(tokens.toString());
        }

        System.out.println("\n==================== CODE ====================");
        for (String codeLine : code) {
            System.out.println(codeLine);
        }

        System.out.println("\n==================== INTERPRETATION OUTPUT ====================");

        // Pass all tokens to a single Interpreter instance
        Interpreter interpreter = new Interpreter(allTokens);
        interpreter.interpret();
    }

}
