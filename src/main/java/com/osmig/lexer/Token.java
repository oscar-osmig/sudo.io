package com.osmig.lexer;

enum TokenType {
    IDENTIFIER,
    NUMBER,
    OPERATOR
}

// Token class with updated terminology (using "label" for clarity)
class Token {
    TokenType type;
    String label;
    Object value;

    public Token(TokenType type, String label, Object value) {
        this.type = type;
        this.label = label;
        this.value = value;
    }

    @Override
    public String toString() {
        switch (type) {
            case IDENTIFIER:
            case OPERATOR:
                return type + " -> " + label;
            case NUMBER:
                return type + " -> " + value;
            default:
                return type + " -> " + label;
        }
    }
}