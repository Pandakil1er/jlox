package com.craftinginterpreters.suz;

import java.util.Map;
import java.util.HashMap;

class SuzInstance {
    private SuzClass klass;
    private final Map<String, Object> fields = new HashMap<>();

    SuzInstance(SuzClass klass) {
        this.klass = klass;
    }

    Object get(Token name) {
        if (fields.containsKey(name.lexeme)) {
            return fields.get(name.lexeme);
        }
        SuzFunction method = klass.findMethod(name.lexeme);
        if (method != null) return method.bind(this);

        throw new RuntimeError(name, "Undefined Property: '" + name.lexeme + "'.");
    }

    void set(Token name, Object value) {
        fields.put(name.lexeme, value);
    }

    @Override
    public String toString() {
        return klass.name + " instance.";
    }
}
