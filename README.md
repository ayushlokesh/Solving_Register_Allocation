# Lab 4: Compiler Design

## Introduction
This lab focuses on implementing a compiler for a simple programming language. The compiler consists of two main components: `file_handler.java` and `registerAllocate.java`. `file_handler.java` reads input from a file, processes it, and passes it to `registerAllocate.java`, which performs register allocation. This readme provides instructions on how to compile and run the code.

## Instructions
To compile the code, run the following commands in the terminal:

```bash
javac comp206020/file_handler.java comp206020/registerAllocate.java

To run the compiled code, use the following command:

```bash
java comp206020.file_handler comp206020/input.txt comp206020/output.txt

Replace comp206020/input.txt and comp206020/output.txt with the desired input and output file paths.

## Additional Notes
- Ensure that the input file contains the correct input for the compiler.
- Verify that the output file contains the expected output after running the compiler.
- If the input is in incorrect format or the graph is not colorable, you will see an empty output file generated with an appropriate message on the console.
