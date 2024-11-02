package com.osmig.lexer;

import java.util.HashMap;
import java.util.Map;

public class SymbolTable {
    private Map<String, Object> table;

    public SymbolTable() {
        table = new HashMap<>();
    }

    public void assign(String identifier, Object value) {
        table.put(identifier, value);
    }

    public Object get(String identifier) {
        return table.get(identifier);
    }

    public boolean contains(String identifier) {
        return table.containsKey(identifier);
    }
}

