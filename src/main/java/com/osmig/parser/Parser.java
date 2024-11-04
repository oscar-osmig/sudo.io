package com.osmig.parser;
import com.osmig.lexer.*;

import java.util.List;

public class Parser {
    private List<Token> tokenList;
    private SymbolTable symbolTable;

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

    public void parse() { // the logic executor

    }

    private void handleAssignment(Token identifierToken)
    {


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
