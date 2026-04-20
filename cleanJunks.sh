#!/bin/bash

# Delete all .class files in current and subdirectories
find . -type f \( -name "*.class" -o -name "*.out" -o -name "*.exe" \) -exec rm -f -v {} \;

echo "All .class/.out/.exe files have been deleted."
