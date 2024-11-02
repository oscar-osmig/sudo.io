package com.osmig.lexer;

// Token class with updated terminology (using "label" for clarity)
public class Token {
    TokenType type;
    String value;

    public Token(TokenType type, String value) {
        this.type = type;
        this.value = value;

    }

    @Override
    public String toString() {
        return "Token " +
                "type -> " + type +
                ", value -> " + value ;
    }

    public TokenType getType() {
        return type;
    }

    public void setType(TokenType type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}