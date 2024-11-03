package com.osmig.parser;
import com.osmig.lexer.*;

import java.util.List;

public class Parser {
    private List<String> tokenList;
    private List<TokenType> tokenType;
    private SymbolTable symbolTable;

    public Parser(List<String> tokenList, List<TokenType> tokenType) {
        this.tokenList = tokenList;
        this.tokenType = tokenType;
        this.symbolTable = new SymbolTable();
        parse();
    }

    public void parse() { // the logic executor
        System.out.println("\n");
        for(int i = 0; i<tokenList.size(); i++){
            System.out.println("Token -> " + tokenList.get(i) + ": Type -> " + tokenType.get(i));
        }
    }

    private void handleAssignment(Token identifierToken)
    {


    }


    private void handlePrint() {

    }

}
