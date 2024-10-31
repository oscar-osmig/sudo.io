package com.osmig.lexer;

enum TokenType {
    IDENTIFIER,
    NUMBER,
    OPERATOR,
    KEYWORD,
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
            case KEYWORD:
            case IDENTIFIER:
            case OPERATOR:
                return type + " -> " + label;
            case NUMBER:
                return type + " -> " + value;
            default:
                return type + " -> " + label;
        }
    }

    public TokenType getType() {
        return type;
    }

    public void setType(TokenType type) {
        this.type = type;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}