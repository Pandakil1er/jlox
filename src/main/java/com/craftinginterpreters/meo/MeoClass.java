package com.craftinginterpreters.meo;

import java.util.List;
import java.util.Map;

class MeoClass implements MeoCallable {
    final String name;
    final MeoClass superclass;

    private final Map<String, MeoFunction> methods;

    MeoClass(String name, MeoClass superclass, Map<String, MeoFunction> methods) {
        this.superclass = superclass;
        this.methods = methods;
        this.name = name;
    }

    MeoFunction findMethod(String name) {
        if (methods.containsKey(name)) {
            return methods.get(name);
        }
        if (superclass != null) {
            return superclass.findMethod(name);
        }
        return null;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public int arity() {
        MeoFunction initializer = findMethod("init");
        if (initializer == null) {
            return 0;
        }
        return initializer.arity();

    }

    @Override
    public Object call(Interpreter interpreter, List<Object> arguments) {
        MeoInstance instance = new MeoInstance(this);
        MeoFunction initializer = findMethod("init");
        if (initializer != null) {
            initializer.bind(instance).call(interpreter, arguments);
        }
        return instance;
    }
}
