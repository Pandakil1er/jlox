package com.craftinginterpreters.meo;

import java.util.List;

interface MeoCallable {
    int arity();

    Object call(Interpreter interpreter, List<Object> arguments);

}
