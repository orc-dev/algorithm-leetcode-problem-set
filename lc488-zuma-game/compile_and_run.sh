#!/bin/bash

# Create bin folder if it doesn't exist
mkdir -p bin

# Remove .class files
rm -f bin/*.class

# Compile Java source files
javac -d bin src/*.java

# Check if compilation was successful
if [ $? -eq 0 ]; then
    # Run the app
    java -cp bin ProblemRunner
else
    echo "Eroror: compilation failed."
fi