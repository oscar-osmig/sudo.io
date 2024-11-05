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

//    public void parse() { // the logic executor
//        parseAssignment();
//
//    }

    public void parseAssignment()
    {
        Token indentifierToken = matchToken(TokenType.IDENTIFIER);
        if (indentifierToken == null){
            error("Expected identifier"); // make error
            return;
        }

        Object  value = parseValue(); // parse value after identifier
        if (value != null){
            symbolTable.assign(indentifierToken.getValue(), value);
            System.out.println("Assigned " + value + " to " + indentifierToken.getValue());
        }else {
            error("Expected value after identifier");
        }


    }

    private Object parseValue(){
        Token nextToken = tokenList.get(currentPosition);

        if(nextToken.getType() == TokenType.KEYWORD && "INT".equals(nextToken.getValue())){
            matchToken(TokenType.KEYWORD); // consume "INT"
            Token numberToken = matchToken(TokenType.NUMBER);
            if (numberToken != null){
                return Integer.parseInt(numberToken.getValue());
            }
        } else if (nextToken.getType() == TokenType.NUMBER) {
            matchToken(TokenType.NUMBER);
            return Double.parseDouble(nextToken.getValue());
        } else if (nextToken.getType() == TokenType.STRING) {
            matchToken(TokenType.STRING);
            return nextToken.getValue();
        }

        // if no match, return null to indicate a parsing error
        return null;

    }

    private void error(String message){
        System.out.println("Parse error: " + message);
    }

    private Token matchToken(TokenType expectedType){
        if (currentPosition < tokenList.size() && tokenList.get(currentPosition).getType() == expectedType){
            return tokenList.get(currentPosition++);
        }
        return null; // indicates a mismatch or missing token
    }

    private void handlePrint() {

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
