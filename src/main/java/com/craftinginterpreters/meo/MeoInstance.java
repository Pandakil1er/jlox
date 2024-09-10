package com.craftinginterpreters.meo;

import java.util.Map;
import java.util.HashMap;

class MeoInstance {
    private MeoClass klass;
    private final Map<String, Object> fields = new HashMap<>();

    MeoInstance(MeoClass klass) {
        this.klass = klass;
    }

    Object get(Token name) {
        if (fields.containsKey(name.lexeme)) {
            return fields.get(name.lexeme);
        }
        MeoFunction method = klass.findMethod(name.lexeme);
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
