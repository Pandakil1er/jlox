# jmeo 🚀

`jmeo` is a lightweight, dynamically-typed scripting language interpreter written in Java. 

This project is deeply inspired by and based on `jlox` from Robert Nystrom's phenomenal book, [*Crafting Interpreters*](https://craftinginterpreters.com/). It uses a hand-written recursive descent parser and evaluates the Abstract Syntax Tree (AST) directly via a tree-walking interpreter.

## ✨ Features
* **Dynamically Typed**: Variables can hold any type of value and change types at runtime.
* **C-like Syntax**: Familiar syntax for developers coming from C, C++, Java, or JavaScript.
* **First-Class Functions**: Functions are values and can be passed as arguments or returned from other functions.
* **Lexical Scoping**: Closures are fully supported.
* **Object-Oriented**: Classes, instances, methods, and inheritance.
* **Garbage Collected**: Relies on Java's underlying garbage collection for memory management.

## 📦 Getting Started

### Prerequisites
* Java Development Kit (JDK) 11 or higher.

### Building and Running
1. **Clone the repository:**
   ```bash
   git clone [https://github.com/Pandakil1er/jmeo.git](https://github.com/Pandakil1er/jmeo.git)
   cd jmeo
   ```
2. **Compile the Java files:**
   ```bash
   javac -d out src/com/jmeo/*.java
   ```
3. **Run the REPL (Interactive Prompt):**
   ```bash
   java -cp out com.jmeo.jmeo
   ```
4. **Run a script:**
   ```bash
   java -cp out com.jmeo.jmeo script.jmeo
   ```

---

## 📖 Language Syntax Guide

Because `jmeo` is based on the Lox language, its syntax will feel very familiar to anyone who has used JavaScript or Java.

### 1. Data Types
`jmeo` supports a concise set of built-in types:
* **Booleans**: `true` or `false`
* **Numbers**: Double-precision floating point (e.g., `123`, `12.34`)
* **Strings**: Enclosed in double quotes (`"Hello, world!"`)
* **Nil**: Represents the absence of a value (`nil`)

### 2. Variables
Variables are declared using the `var` keyword. If a variable is declared without an initializer, its value defaults to `nil`.

```javascript
var breakfast = "beignets";
var beverage = "cafe au lait";
breakfast = "beignets with " + beverage;

var unassigned; // Defaults to nil
```

### 3. Control Flow
`jmeo` supports standard branching and looping constructs.

**If Statements:**
```javascript
if (condition) {
  print "yes";
} else {
  print "no";
}
```

**While and For Loops:**
```javascript
var a = 1;
while (a < 10) {
  print a;
  a = a + 1;
}

for (var i = 0; i < 10; i = i + 1) {
  print i;
}
```

### 4. Logical Operators
`and` and `or` operate using short-circuit evaluation.

```javascript
if (isTrue and isFalse) {
    // Will not execute
}

if (isTrue or isFalse) {
    // Will execute
}
```

### 5. Functions
Functions are first-class citizens. They are declared using the `fun` keyword. 

```javascript
fun makeCounter() {
  var i = 0;
  fun count() {
    i = i + 1;
    print i;
  }
  return count;
}

var counter = makeCounter();
counter(); // 1
counter(); // 2
```

### 6. Classes & Object-Oriented Programming
`jmeo` supports classes, methods, inheritance, and initialization via the `init` keyword.

```javascript
class Animal {
  speak() {
    print "Animal sound!";
  }
}

class Dog < Animal {
  init(name) {
    this.name = name;
  }

  speak() {
    print this.name + " says woof!";
  }
}

var myDog = Dog("Fido");
myDog.speak(); // "Fido says woof!"
```

## 🙏 Acknowledgements
This project would not be possible without Robert Nystrom's [Crafting Interpreters](https://craftinginterpreters.com/). It serves as an incredible educational resource for anyone looking to understand parsing, interpreting, and language design.

## 📄 License
This project is open-source and available under the MIT License.
