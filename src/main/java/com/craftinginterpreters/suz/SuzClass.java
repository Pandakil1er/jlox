package com.craftinginterpreters.suz;

import java.util.List;
import java.util.Map;

class SuzClass implements SuzCallable {
    final String name;
    final SuzClass superclass;

    private final Map<String, SuzFunction> methods;

    SuzClass(String name, SuzClass superclass, Map<String, SuzFunction> methods) {
        this.superclass = superclass;
        this.methods = methods;
        this.name = name;
    }

    SuzFunction findMethod(String name) {
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
        SuzFunction initializer = findMethod("init");
        if (initializer == null) {
            return 0;
        }
        return initializer.arity();

    }

    @Override
    public Object call(Interpreter interpreter, List<Object> arguments) {
        SuzInstance instance = new SuzInstance(this);
        SuzFunction initializer = findMethod("init");
        if (initializer != null) {
            initializer.bind(instance).call(interpreter, arguments);
        }
        return instance;
    }
}
