package com.craftinginterpreters.meo;

import java.util.HashMap;
import java.util.Map;

class Enviroment {
    final Enviroment enclosing;
    private final Map<String, Object> values = new HashMap<>();

    Enviroment() {
        enclosing = null;
    }

    Enviroment(Enviroment enclosing) {
        this.enclosing = enclosing;
    }

    void define(String name, Object value) {
        values.put(name, value);
    }

    Object get(Token name) {
        if (values.containsKey(name.lexeme)) {
            return values.get(name.lexeme);
        }
        if (enclosing != null) {
            return enclosing.get(name);
        }

        throw new RuntimeError(name, "Undefined variable '" + name.lexeme + "''.");
    }

    Object getAt(int distance, String name) {
        return ancestor(distance).values.get(name);
    }

    void assignAt(int distance, Token name, Object value) {
        ancestor(distance).values.put(name.lexeme, value);
    }

    Enviroment ancestor(int distance) {
        Enviroment enviroment = this;
        for (int i = 0; i < distance; i++) {
            enviroment = enviroment.enclosing;
        }
        return enviroment;
    }

    void assign(Token name, Object value) {
        if (values.containsKey(name.lexeme)) {
            values.put(name.lexeme, value);
            return;
        }
        if (enclosing != null) {
            enclosing.assign(name, value);
            return;
        }

        throw new RuntimeError(name, "Undefined variable '" + name.lexeme + "'.");
    }

}
