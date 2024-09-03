package com.craftinginterpreters.suz;

import java.util.List;

interface SuzCallable {
    int arity();

    Object call(Interpreter interpreter, List<Object> arguments);

}
