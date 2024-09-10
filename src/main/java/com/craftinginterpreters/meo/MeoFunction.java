package com.craftinginterpreters.meo;

import java.util.List;

public class MeoFunction implements MeoCallable {
    private final Stmt.Function declaration;
    private final Enviroment closure;
    private final boolean isInitializer;

    MeoFunction(Stmt.Function declaration, Enviroment closure, boolean isInitializer) {
        this.isInitializer = isInitializer;
        this.declaration = declaration;
        this.closure = closure;
    }

    MeoFunction bind(MeoInstance instance) {
        Enviroment enviroment = new Enviroment(closure);
        enviroment.define("this", instance);
        return new MeoFunction(declaration, enviroment, isInitializer);
    }


    @Override
    public int arity() {
        return declaration.params.size();
    }

    @Override
    public String toString() {
        return "<fn " + declaration.name.lexeme + ">";
    }

    @Override
    public Object call(Interpreter interpreter, List<Object> arguments) {
        Enviroment enviroment = new Enviroment(closure);
        for (int i = 0; i < declaration.params.size(); i++) {
            enviroment.define(declaration.params.get(i).lexeme, arguments.get(i));
        }
        try {
            if (isInitializer) return closure.getAt(0, "this");
            interpreter.executeBlock(declaration.body, enviroment);
        } catch (Return returnValue) {
            return returnValue.value;
        }
        if (isInitializer) return closure.getAt(0, "this");
        return null;
    }
}
