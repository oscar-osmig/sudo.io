package com.osmig.lexer;

// Token class with updated terminology (using "label" for clarity)
public class Token {
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
        return "Token " +
                "type -> " + type +
                ", label -> '" + label + '\'' +
                ", value -> " + value +
                '}';
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