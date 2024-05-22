#!/bin/bash
# --------------------------------------------------------------------------- #
# File name: compile_and_run.sh
# Date:      May.14 2024
# Description:
#   This script automates the process of compiling and running a Java 
#   application. It assumes all Java source files are located in the 
#   'src' folder. The script:
#       1. Creates a 'bin' directory if it does not already exist.
#       2. Clears any existing compiled .class files from the 'bin' directory.
#       3. Compiles all Java source files from 'src' into 'bin'.
#       4. If compilation is successful, it runs the application.
#       5. If compilation fails, it outputs an error message.
# --------------------------------------------------------------------------- #

# Create bin folder if it doesn't exist
mkdir -p bin

# Remove .class files
rm -f bin/*.class

# Compile Java source files
javac -d bin src/*.java

# Set entry point for running the program
main=Solution

# Check if compilation was successful
if [ $? -eq 0 ]; then
    # Run the app
    java -cp bin $main
else
    echo "Error: compilation failed."
fi