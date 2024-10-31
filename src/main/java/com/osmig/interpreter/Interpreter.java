package com.osmig.interpreter;
import com.osmig.lexer.*;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Interpreter {
    private final Map<String, Integer> variables = new HashMap<>(); // Store variable names and values
    private final List<Token> tokens;
    private int currentTokenIndex = 0;

    public Interpreter(List<Token> tokens) {
        this.tokens = tokens;
    }

    public void interpret() {
        while (currentTokenIndex < tokens.size()) {
            Token token = tokens.get(currentTokenIndex);

            // Check for PRINT keyword
            if (token.getType() == TokenType.KEYWORD && token.getLabel().equals("PRINT")) {
                currentTokenIndex++;
                print(); // Print the variable
            }
            // Check for IDENTIFIER, indicating variable assignment
            else if (token.getType() == TokenType.IDENTIFIER) {
                String varName = token.getLabel(); // Get the variable name
                currentTokenIndex++;

                // Check for a NUMBER token following the IDENTIFIER
                if (currentTokenIndex < tokens.size() && tokens.get(currentTokenIndex).getType() == TokenType.NUMBER) {
                    assign(varName); // Assign the value to the variable
                }
            }
            // Move to the next token
            else {
                currentTokenIndex++;
            }
        }
    }

    private void assign(String varName) {
        Token valueToken = tokens.get(currentTokenIndex);
        if (valueToken.getType() == TokenType.NUMBER) {
            int value = (int) valueToken.getValue(); // Get the integer value
            variables.put(varName, value); // Store in the variable map
            currentTokenIndex++; // Move to the next token
        }
    }

    private void print() {
        Token nextToken = tokens.get(currentTokenIndex);
        if (nextToken.getType() == TokenType.IDENTIFIER) {
            String varName = nextToken.getLabel(); // Get the variable name
            Integer value = variables.get(varName); // Retrieve its value

            if (value != null) {
                System.out.println(value); // Print the value if it exists
            } else {
                System.out.println("Undefined variable: " + varName); // Handle undefined variable
            }
            currentTokenIndex++; // Move to the next token
        }
    }
}