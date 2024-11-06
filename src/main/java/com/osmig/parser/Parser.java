package com.osmig.parser;
import com.osmig.lexer.*;

import java.util.List;

public class Parser {
    private List<Token> tokenList;
    private SymbolTable symbolTable;
    private int currentPosition = 0;

    public Parser(List<Token> tokens) {
        this.tokenList = tokens;
        this.symbolTable = new SymbolTable();
    }

    public void printTokens(List<Token> tokens){
        System.out.println("\n");
        for(int i = 0; i<tokenList.size(); i++){
            System.out.println("Token -> " + ": Type -> " );
        }
    }

    public void parse() {
        //the logic executor
        if (tokenList != null) {
            while (currentPosition < tokenList.size()) {
                Token token = tokenList.get(currentPosition);

                // checking if the token is an identifier to start assignment
                if (token.getType() == TokenType.IDENTIFIER) {
                    parseAssignment();
                } else if (token.getType() == TokenType.KEYWORD && token.getValue().equals("PRINT")) {
                    handlePrint();
                } else {
                    currentPosition++;
                }
            }
        }
    }

    public void parseAssignment() {
        Token identifierToken = matchToken(TokenType.IDENTIFIER);
        Token nextToken = tokenList.get(currentPosition);

        // Check for the INT keyword and match it
        boolean isInt = false;
        if (nextToken.getType() == TokenType.KEYWORD && nextToken.getValue().equals("INT")) {
            matchToken(TokenType.KEYWORD); // Consume the INT token
            isInt = true; // Set flag to indicate INT was specified
        }

        // Match value: it could be either a number or a string
        Token valueToken = matchToken(TokenType.NUMBER, TokenType.STRING);

        // Parse the value (this will now handle both numbers and strings)
        Object value = parseValue(valueToken, isInt);

        // Save the parsed value to the symbol table under the identifier
        symbolTable.assign(identifierToken.getValue(), value);

        // Print the assignment result
        //System.out.println("Assigned: " +   identifierToken.getValue() + " = " + symbolTable.get(identifierToken.getValue()));

    }



    private Object parseValue(Token valueToken, boolean isInt) {
        if (valueToken.getType() == TokenType.NUMBER) {
            String valueStr = valueToken.getValue();

            // If INT was specified, convert to integer
            if (isInt) {
                if (valueStr.contains(".")) {
                    // Truncate to integer by ignoring decimal part
                    return Integer.parseInt(valueStr.substring(0, valueStr.indexOf(".")));
                } else {
                    return Integer.parseInt(valueStr);
                }
            } else {
                // If INT was not specified, treat as a double
                return Double.parseDouble(valueStr);
            }
        }

        // Handle string type
        if (valueToken.getType() == TokenType.STRING) {
            return valueToken.getValue();
        }

        // If no valid type matched, throw an error
        throw new RuntimeException("Invalid value for Assignment");
    }


    private void error(String message){
        System.out.println("Parse error: " + message);
    }

    private Token matchToken(TokenType... expectedTypes){
        Token currentToken = tokenList.get(currentPosition);
        // check if the current token type matches any expected types
        for (TokenType expectedType : expectedTypes){
            if (currentToken.getType() == expectedType){
                currentPosition++;
                return currentToken;
            }
        }
        // If no match is found, return null
        return null; // indicates a mismatch or missing token
    }

    private void handlePrint() {
        matchToken(TokenType.KEYWORD); // consume the PRINT keyword

        Token nextToken = tokenList.get(currentPosition);
        if (nextToken.getType() == TokenType.STRING) {
            System.out.println(nextToken.getValue());
            currentPosition++;
        } else if (nextToken.getType() == TokenType.NUMBER) {
            System.out.println(nextToken.getValue());
            currentPosition++;
        } else {

            // expect identifier after Print
            Token identifierToken = matchToken(TokenType.IDENTIFIER);
            if (identifierToken != null) {
                String variableName = identifierToken.getValue();

                // look up variables value on table
                if (symbolTable.contains(variableName)) {
                    Object value = symbolTable.get(variableName);
                    System.out.println(value);
                } else {
                    System.out.println("Error: Undefined variable -> " + variableName);
                }

            } else {
                System.out.println("Error: Expected an identifier after PRINT ");
            }
        }
    }

    public List<Token> getTokenList() {
        return tokenList;
    }

    public void setTokenList(List<Token> tokenList) {
        this.tokenList = tokenList;
    }

    public SymbolTable getSymbolTable() {
        return symbolTable;
    }

    public void setSymbolTable(SymbolTable symbolTable) {
        this.symbolTable = symbolTable;
    }
}
