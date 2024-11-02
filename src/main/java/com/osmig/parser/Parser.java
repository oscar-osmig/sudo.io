package com.osmig.parser;
import com.osmig.lexer.*;

public class Parser {
    private Lexer lexer;
    private SymbolTable symbolTable;

    public Parser(Lexer lexer) {
        this.lexer = lexer;
        this.symbolTable = new SymbolTable();
    }

    public void parse() { // the logic executor


    }

    private void handleAssignment(Token identifierToken)
    {


    }


    private void handlePrint() {

    }

}
